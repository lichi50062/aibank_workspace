package com.tfb.aibank.biz.component.etrans;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.log.ITraceLogDataEntity;
import com.ibm.tw.ibmb.component.log.ITraceLogEntity;
import com.ibm.tw.ibmb.component.log.TraceLogPersistenceProvider;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.biz.component.etrans.E2EEUtils_AIBank.E2EEException;
import com.tfb.aibank.biz.component.etrans.message.BOND8030_ITERATION;
import com.tfb.aibank.biz.component.etrans.message.layout.Field;
import com.tfb.aibank.biz.component.etrans.message.layout.Format;
import com.tfb.aibank.biz.component.etrans.message.layout.Iteration;
import com.tfb.aibank.biz.component.etrans.message.layout.Record;
import com.tfb.aibank.biz.component.etrans.message.layout.Tx;
import com.tfb.aibank.component.proxy.CryptoProxy;
import com.tfb.aibank.integration.eai.entity.TraceLogDataEntity;
import com.tfb.aibank.integration.eai.entity.TraceLogEntity;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

// @formatter:off
/**
 * @(#)MessageUtils.java
 * 
 * <p>Description:提供「e化繳費網」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/16, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ETrans<T> {

    private static IBLog _logger = IBLog.getLog(ETrans.class);

    private TraceLogPersistenceProvider<?, ?> traceLogPersistenceProvider;

    private SystemParamCacheManager systemParamCacheManager;

    private E2EEHsmUtils_AIBank e2EEHsmUtils_JSB;

    public static final String ENCODING = "big5"; // 電文編碼 (for HSMXXXX 晶片卡前置系統)
    public static final String ENCODING_BOND = "MS950"; // 電文編碼(for BONDXXX 富邦產險查詢系統)

    /**
     * 發送電文 SocketClient (for HSMXXXX 晶片卡前置系統)
     * 
     * @param memo
     *            電文備註
     * @param t
     *            電文 Value Object
     * @return
     * @throws ActionException
     */
    public void sendAndReceive(String memo, T t) throws ActionException {
        this.sendAndReceive(null, memo, t);
    }

    /**
     * 發送電文 SocketClient (for HSMXXXX 晶片卡前置系統)
     * 
     * @param accessLogKey
     * @param memo
     *            電文備註
     * @param t
     *            電文 Value Object
     * @return
     * @throws ActionException
     */
    public void sendAndReceive(String accessLogKey, String memo, T t) throws ActionException {

        List<String> stepMessages = new ArrayList<String>();

        RSAResult_JSB rsaResult = new RSAResult_JSB();

        try {
            // 取得電文Format
            Format format = loadMessageFormat(t.getClass().getSimpleName());

            // 將電文RQ Vo 轉換為上傳字串
            byte[] rqBytes = rqMessageToBytes(format, t);

            stepMessages.add(String.format("Request 加密前 rqBytes 長度 : %d", rqBytes.length));
            stepMessages.add(String.format("Request 加密前 rqBytes to HexString : %s", ConvertUtils.byteArray2HexString(rqBytes)));

            // --------------------------------------------------
            // 上傳 資料 HSM加密
            // byte[] macBuffer = new byte[8]; // HSM 在加密之後 會放資料在此bytes，之後解密要拿此 資料去解
            byte[] divData = new byte[6];
            System.arraycopy(rqBytes, 0, divData, 0, 6);
            byte[] rqEncryptBytes = rqBytes;
            // byte[] rqEncryptBytes = CryptoProxy.desEncryptionAndGenMAC(HSMEnvType.FOR_IB_EATM, rqBytes, macBuffer);

            rsaResult.setPlainText(ConvertUtils.byteArray2HexString(rqBytes));
            rsaResult.setHsmEnvType(CryptoProxy.HSMEnvType.FOR_IB_EATM.getCode());
            rsaResult = e2EEHsmUtils_JSB.encryptDataAndGenerateMAC(rsaResult);
            String cipherText = rsaResult.getCipherText();
            rqEncryptBytes = ConvertUtils.hexString2ByteArray(cipherText);

            stepMessages.add(String.format("Request 加密後 rqBytes 長度 : %d", rqEncryptBytes.length));
            stepMessages.add(String.format("Request 加密後 rqEncryptBytes to HexString : %s", ConvertUtils.byteArray2HexString(rqEncryptBytes)));

            // --------------------------------------------------
            // 收發電文 (socket)
            byte[] rsBytes = null;
            long startTime = 0; // 電文上行開始時間
            long period = 0; // 電文收送時間間隔
            SocketClient socketClient = new SocketClient(systemParamCacheManager);
            try {
                // 寫RQ tracelog
                insertTraceLog(accessLogKey, "IB", t.getClass().getSimpleName(), "", memo, ConvertUtils.byteArray2HexString(rqBytes), null, true);
                startTime = System.currentTimeMillis();

                socketClient.createSocket();

                rsBytes = socketClient.doSocket(rqEncryptBytes);

                period = System.currentTimeMillis() - startTime; // 接收時間

            }
            finally {
                socketClient.closeSocket();
            }
            // --------------------------------------------------
            // TAC_LEN(晶片金融卡TAC長度) X(3) 發HSM壓TAC的長度
            stepMessages.add(String.format("Response 解密前 rsBytes 長度 : %d", rsBytes.length));
            stepMessages.add(String.format("Response 解密前 rsBytes to HexString : %s", ConvertUtils.byteArray2HexString(rsBytes)));

            byte[] rsDecryptBytes = rsBytes;
            // byte[] rsDecryptBytes = CryptoProxy.desDecryptionAndVerifyMAC(HSMEnvType.FOR_IB_EATM, rsBytes);

            rsaResult.setCipherText(ConvertUtils.byteArray2HexString(rsDecryptBytes));
            rsaResult = e2EEHsmUtils_JSB.decryptDataAndVerifyMAC(rsaResult);
            String plainText = rsaResult.getPlainText();
            rsDecryptBytes = ConvertUtils.hexString2ByteArray(plainText);

            stepMessages.add(String.format("Response 解密後 rsDecryptBytes 長度 : %d", rsDecryptBytes.length));
            stepMessages.add(String.format("Response 解密後 rsDecryptBytes to HexString : %s", ConvertUtils.byteArray2HexString(rsDecryptBytes)));
            stepMessages.add(String.format("Response 解密後 rsDecryptBytes to String (%s) : %s", ENCODING, new String(rsDecryptBytes, ENCODING)));

            // 寫RS tracelog
            insertTraceLog(accessLogKey, "ICCARD_HSM", t.getClass().getSimpleName(), "", "TOTA for " + memo, ConvertUtils.byteArray2HexString(rsDecryptBytes), (int) period, false);

            // Response 字串 轉 Bean
            rsMessageToVo(format, t, rsDecryptBytes);
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | JAXBException | IOException | XMLStreamException | E2EEException e) {
            _logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
        finally {
            _logger.info("電文類別:{}", t.getClass().getSimpleName());
            // Fortify - LOG forging
            // for (String msg : stepMessages) {
            // _logger.info(msg);
            // }
            // _logger.info(t.toString());
        }
    }

    /**
     * 發送電文 SSLClient (for BONDXXX 產險查詢系統)
     * 
     * @param accessLogKey
     * @param memo
     *            電文備註
     * @param t
     *            電文 Value Object
     * @return
     * @throws ActionException
     */
    public void sendAndReceiveByHttp(String accessLogKey, String memo, T t) throws ActionException {

        List<String> msgList = new ArrayList<String>();
        long startTime = 0; // 電文上行開始時間
        long period = 0; // 電文收送時間間隔
        try {
            // --------------------------------------------------
            // 取得電文Format
            Format format = loadMessageFormat(t.getClass().getSimpleName());

            _logger.debug("========BONDXXX 產險查詢系統 上行RQ電文Log=============================================");

            String rqString = rqMessageToString(format, t);

            _logger.debug("=============================================================");
            msgList.add("rqString            :" + rqString);
            _logger.debug("=============================================================");

            // 寫RQ tracelog
            insertTraceLog(accessLogKey, "IB", t.getClass().getSimpleName(), "", memo, rqString, null, true);
            startTime = System.currentTimeMillis();

            SSLClient sslClient = new SSLClient(systemParamCacheManager);
            sslClient.connect();
            sslClient.send(t.getClass().getSimpleName(), rqString.getBytes(ENCODING_BOND));

            byte[] rsBytes = sslClient.receive(t.getClass().getSimpleName());

            period = System.currentTimeMillis() - startTime; // 接收時間

            _logger.debug("========BONDXXX 產險查詢系統 回傳RS電文Log=============================================");
            _logger.debug("=============================================================");
            msgList.add("rsString            :" + new String(rsBytes, ENCODING_BOND));
            _logger.debug("=============================================================");

            // 寫RS tracelog
            insertTraceLog(accessLogKey, "ICCARD_HSM", t.getClass().getSimpleName(), "", "TOTA for " + memo, new String(rsBytes, ENCODING_BOND), (int) period, false);

            // rs 字串 轉 Vo
            rsMessageToVo4Bond(format, t, rsBytes);

        }
        catch (InvocationTargetException | NoSuchMethodException | JAXBException | IOException | XMLStreamException | IllegalAccessException e) {
            _logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
    }

    /**
     * 將電文Vo 依照 format 轉換為 <字串>
     * 
     * @param format
     * @param t
     * @return
     * @throws ActionException
     */
    private String rqMessageToString(Format format, T t) throws ActionException {

        StringBuffer sbString = new StringBuffer();

        try {
            for (Record record : format.getRecordList()) {
                if (StringUtils.equals("sendToHost", record.getId())) {
                    // 取上行電文 (RECORD id="sendToHost" 的Field)
                    for (Field field : record.getFieldList()) {

                        String beanValue = BeanUtils.getProperty(t, "RQ_" + field.getId());

                        String fieldString = getFieldString(field.getId(), beanValue, field.getMaxLen(), field.getMinLen(), field.getPadding(), field.getAlign(), field.getType(), field.getAttr_default());
                        sbString.append(fieldString);
                    }
                }
            }
        }
        catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | UnsupportedEncodingException e) {
            _logger.error(e.getMessage(), e);
            throw ExceptionUtils.getActionException(e);
        }
        return sbString.toString();
    }

    /**
     * 將電文Vo 依照 format 轉換為 bytes
     * 
     * @param format
     * @param t
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws UnsupportedEncodingException
     */
    private byte[] rqMessageToBytes(Format format, T t) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, UnsupportedEncodingException {

        // 計算 上傳總長度
        int rqByteLength = 0;
        for (Record record : format.getRecordList()) {
            if (StringUtils.equals("sendToHost", record.getId())) {
                for (Field field : record.getFieldList()) {
                    rqByteLength = rqByteLength + Integer.parseInt(field.getMaxLen());
                }
            }
        }

        byte[] rqBytes = new byte[rqByteLength]; // method 回傳結果message bytes

        StringBuffer logString = new StringBuffer(); // 除錯用logger
        StringBuffer logString2 = new StringBuffer(); // 除錯用logger
        int logIndex = 0; // 除錯用logger
        for (Record record : format.getRecordList()) {
            if (StringUtils.equals("sendToHost", record.getId())) {
                // 取上行電文 (RECORD id="sendToHost" 的Field)

                int index = 0; // byte index 本次欄位, 再回傳結果bytes中的 起始位置

                for (Field field : record.getFieldList()) {

                    String beanValue = BeanUtils.getProperty(t, "RQ_" + field.getId());

                    byte[] fieldBytes = getFieldBytes(field.getId(), beanValue, field.getMaxLen(), field.getMinLen(), field.getPadding(), field.getAlign(), field.getType(), field.getAttr_default());

                    // System.arraycopy(來源陣列，起始索引值，目的陣列，起始索引值，複製長度);
                    int copyLength = fieldBytes.length;
                    System.arraycopy(fieldBytes, 0, rqBytes, index, copyLength);
                    index = index + copyLength;

                    // ---------------------------------
                    // log 方便 電文比對 only 無其他作用
                    if (!StringUtils.equals(Field.TYPE_BINARY, field.getType())) {
                        logString.append(new String(fieldBytes, ENCODING));
                    }
                    else {
                        for (int i = 0; i < Integer.parseInt(field.getMaxLen()); i++) {
                            logString.append("L");
                        }
                    }
                    String logMark = logIndex % 2 == 0 ? "O" : "X";
                    for (int i = 0; i < Integer.parseInt(field.getMaxLen()); i++) {
                        logString2.append(logMark);
                    }
                    logIndex++;
                    // ---------------------------------
                }
            }
        }
        _logger.debug("logString :" + logString.toString());
        _logger.debug("logString :" + logString2.toString());
        return rqBytes;
    }

    /**
     * 將電文 回傳結果 轉換為 Vo
     * 
     * @param format
     * @param t
     * @param rsString
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws UnsupportedEncodingException
     */
    private void rsMessageToVo4Bond(Format format, T t, byte[] rsBytes) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, UnsupportedEncodingException {
        for (Record record : format.getRecordList()) {

            if (StringUtils.equals("rcvFromHost", record.getId())) {

                int baseIndex = 0;
                int iterationCount = 0;
                for (Field field : record.getFieldList()) {

                    int fieldLength = Integer.parseInt(field.getMaxLen());
                    // String fieldValue = rsString.substring(index, index + fieldLength);

                    String fieldValue = new String(rsBytes, baseIndex, fieldLength, ENCODING).trim();

                    baseIndex = baseIndex + fieldLength;
                    _logger.debug("fieldID:" + field.getId() + "=" + fieldValue);

                    BeanUtils.copyProperty(t, "RS_" + field.getId(), fieldValue);
                    if (StringUtils.equals("COUNT", field.getId())) {
                        iterationCount = Integer.parseInt(fieldValue);
                    }
                }

                // 目前只有BOND8030 有 ITERATION
                // 如果電文有RS 設定<ITERATION id="ITERATION_LIST" secLen="80">

                if (null != record.getITERATION()) {
                    for (Iteration iteration : record.getITERATION()) {
                        if (StringUtils.equals("ITERATION_LIST", iteration.getId())) {
                            List<BOND8030_ITERATION> list = new ArrayList<BOND8030_ITERATION>();

                            for (int i = 0; i < iterationCount; i++) {
                                BOND8030_ITERATION vo = new BOND8030_ITERATION();
                                _logger.debug("ITERATION_LIST vo 第" + i + "筆 start");
                                for (Field field : iteration.getFieldList()) {

                                    int fieldLength = Integer.parseInt(field.getMaxLen());

                                    String fieldValue = new String(rsBytes, baseIndex, fieldLength, ENCODING).trim();
                                    baseIndex = baseIndex + fieldLength;

                                    _logger.debug("fieldID:" + field.getId() + "=" + fieldValue);

                                    BeanUtils.copyProperty(vo, field.getId(), fieldValue);
                                }
                                _logger.debug("ITERATION_LIST vo 第" + i + "筆 ");
                                list.add(vo);
                            } // end of for(int i=0; i<iterationCount; i++) {
                            BeanUtils.copyProperty(t, "RS_ITERATION_LIST", list);
                        }
                    } // end of for (Iteration iteration : record.getITERATION()) {
                } // end of if (null != record.getITERATION()) {
            }
        }
    }

    /**
     * 將電文 回傳結果 轉換為 Vo
     * 
     * @param format
     * @param t
     * @param rsString
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     * @throws UnsupportedEncodingException
     */
    private void rsMessageToVo(Format format, T t, byte[] rsDecryptBytes) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, UnsupportedEncodingException {

        _logger.debug("-----------------------rsMessageToVo Start ----------------------");
        boolean stopParse = false;
        int index = 0;
        for (Record record : format.getRecordList()) {
            if (StringUtils.equals("rcvFromHost", record.getId())) {

                for (Field field : record.getFieldList()) {

                    int fieldLength = Integer.parseInt(field.getMaxLen());

                    // System.arraycopy(來源陣列，起始索引值，目的陣列，起始索引值，複製長度);
                    byte[] thisFieldByte = new byte[fieldLength];
                    System.arraycopy(rsDecryptBytes, index, thisFieldByte, 0, fieldLength);

                    if (StringUtils.equals(Field.TYPE_BINARY, field.getType())) {

                        String fieldValue = ConvertUtils.byteArray2HexString(thisFieldByte);
                        // 2022/6/20 JingXian 行銀白箱修補, A1 Log Forging (debug)
                        _logger.debug("fieldID:{}={}", StringUtils.escapeLog(field.getId()), StringUtils.escapeLog(fieldValue));

                        BeanUtils.copyProperty(t, "RS_" + field.getId(), fieldValue);
                        index = index + fieldLength;

                        // HSM956X 電文，如果RESP_CODE != 00, RS 長度可能不足 xml定義長度
                        // 依SA要求，RESP_CODE != 00 時，只解析到後面欄位到CHNNEL_ID 欄位, 因為後面可能沒值,長度不夠
                        if (StringUtils.equals("RESP_CODE", field.getId()) && StringUtils.notEquals(fieldValue, "00")) {
                            stopParse = true;
                        }
                        if (stopParse && StringUtils.equals("CHANNEL_ID", field.getId())) {
                            break;
                        }
                    }
                    else if (StringUtils.equals(Field.TYPE_CHARACTER, field.getType())) {
                        String fieldValue = new String(thisFieldByte, ENCODING);
                        // 2022/6/20 JingXian 行銀白箱修補, A1 Log Forging (debug)
                        _logger.debug("fieldID:" + StringUtils.escapeLog(field.getId()) + "=" + StringUtils.escapeLog(fieldValue));

                        BeanUtils.copyProperty(t, "RS_" + field.getId(), fieldValue);
                        index = index + fieldLength;

                        // HSM956X 電文，如果RESP_CODE != 00, RS 長度可能不足 xml定義長度
                        // 依SA要求，RESP_CODE != 00 時，只解析到後面欄位到CHNNEL_ID 欄位, 因為後面可能沒值,長度不夠
                        if (StringUtils.equals("RESP_CODE", field.getId()) && StringUtils.notEquals(fieldValue, "00")) {
                            stopParse = true;
                        }

                        if (stopParse && StringUtils.equals("CHANNEL_ID", field.getId())) {
                            break;
                        }
                    }
                }
            }
        }

        _logger.debug("-----------------------rsMessageToVo End ----------------------");
    }

    /**
     * 將欄位 依照 設定的格式 format
     * 
     * @param fieldID
     * @param fieldValue
     * @param maxLen
     * @param minLen
     * @param padding
     * @param align
     * @param type
     * @param attrDefault
     * @return
     * @throws UnsupportedEncodingException
     */
    private byte[] getFieldBytes(String fieldID, String fieldValue, String maxLen, String minLen, String padding, String align, String type, String attrDefault) throws UnsupportedEncodingException {

        if (StringUtils.equals("TAC_DATA", fieldID)) {
            _logger.debug("TAC_DATA:");
        }
        // _logger.debug("fieldID:" + fieldID + "=" + fieldValue);

        if (StringUtils.isBlank(fieldValue)) {
            fieldValue = attrDefault;
        }

        int intMaxLen = 0;
        if (StringUtils.isNotBlank(maxLen)) {
            intMaxLen = Integer.parseInt(maxLen);
        }
        if (StringUtils.equals(Field.TYPE_CHARACTER, type)) {
            if (fieldValue.length() > intMaxLen) {
                throw new IllegalArgumentException("欄位" + fieldID + "內容有誤, 長度超過" + intMaxLen);
            }
        }
        else if (StringUtils.equals(Field.TYPE_BINARY, type)) {
            if (fieldValue.length() / 2 > intMaxLen) {
                throw new IllegalArgumentException("欄位" + fieldID + "內容有誤, 長度超過" + intMaxLen);
            }
        }

        // 一設定檔 補字串長度
        String aftProcessString = "";
        if (StringUtils.equals(Field.ALIGN_LEFT, align)) {
            aftProcessString = StringUtils.rightPad(fieldValue, intMaxLen, padding);
        }
        else {
            aftProcessString = StringUtils.leftPad(fieldValue, intMaxLen, padding);
        }

        _logger.debug("afterFormat fieldID:" + fieldID + "=" + aftProcessString + ", HEX String =" + ConvertUtils.byteArray2HexString(aftProcessString.getBytes()));

        if (StringUtils.equals(Field.TYPE_CHARACTER, type)) {
            return aftProcessString.getBytes(ENCODING);
        }
        else if (StringUtils.equals(Field.TYPE_BINARY, type)) {
            return hexStringToByteArray(aftProcessString);
        }
        else {
            throw new IllegalArgumentException("欄位" + fieldID + "TYPE_CHARACTER有誤, 必須為" + Field.TYPE_CHARACTER + "或" + Field.TYPE_BINARY);
        }
    }

    /**
     * 將欄位 依照 設定的格式 format String
     * 
     * @param fieldID
     * @param fieldValue
     * @param maxLen
     * @param minLen
     * @param padding
     * @param align
     * @param type
     * @param attrDefault
     * @return
     * @throws UnsupportedEncodingException
     */
    private String getFieldString(String fieldID, String fieldValue, String maxLen, String minLen, String padding, String align, String type, String attrDefault) throws UnsupportedEncodingException {

        if (StringUtils.isBlank(fieldValue)) {
            fieldValue = attrDefault;
        }

        int intMaxLen = 0;
        if (StringUtils.isNotBlank(maxLen)) {
            intMaxLen = Integer.parseInt(maxLen);
        }
        if (StringUtils.equals(Field.TYPE_CHARACTER, type)) {
            if (fieldValue.length() > intMaxLen) {
                throw new IllegalArgumentException("欄位" + fieldID + "內容有誤, 長度超過" + intMaxLen);
            }
        }
        else if (StringUtils.equals(Field.TYPE_BINARY, type)) {
            if (fieldValue.length() / 2 > intMaxLen) {
                throw new IllegalArgumentException("欄位" + fieldID + "內容有誤, 長度超過" + intMaxLen);
            }
        }

        // 一設定檔 補字串長度
        String aftProcessString = "";
        if (StringUtils.equals(Field.ALIGN_LEFT, align)) {
            aftProcessString = StringUtils.rightPad(fieldValue, intMaxLen, padding);
        }
        else {
            aftProcessString = StringUtils.leftPad(fieldValue, intMaxLen, padding);
        }

        _logger.debug("afterFormat fieldID:" + fieldID + "=" + aftProcessString);

        return aftProcessString;

    }

    /**
     * 取得電文Format
     * 
     * .xml檔放在 PIB_RUNTIME\Config\web\extMessage\下面。 用 電文vo class名稱 ex : HSM2262 為 PIB_RUNTIME\Config\web\extMessage\HSM2262.xml
     * 
     * @param configName
     * @return
     * @throws JAXBException
     */
    private static Format loadMessageFormat(String configName) throws JAXBException, IOException, XMLStreamException {

        XMLInputFactory xif = XMLInputFactory.newInstance();
        xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, false);
        xif.setProperty(XMLInputFactory.SUPPORT_DTD, false);

        XMLStreamReader xsr = null;
        InputStream formatIs = null;
        Tx tx = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Tx.class);
            ClassPathResource resource = new ClassPathResource("/config/hsm/" + configName + ".xml");
            formatIs = resource.getInputStream(); // MessageUtils.class.getResourceAsStream("classpath:/config/hsm/" + configName + ".xml");
            xsr = xif.createXMLStreamReader(formatIs);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            tx = (Tx) jaxbUnmarshaller.unmarshal(xsr);
        }
        catch (JAXBException | IOException | XMLStreamException e) {
            _logger.error("讀取失敗" + configName + "電文格式失敗:" + e);
            throw e;
        }
        finally {
            IOUtils.closeQuietly(formatIs);
        }
        return tx.getFormat();
    }

    /**
     * 把hex code表示的字串 轉為 byte[]
     * 
     * @param s
     * @return
     */
    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    // ----------------------------------------------------------------

    public static String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }

    /**
     * 新增Trace Log
     * 
     * @param accessLogKey
     * @param fromSystem
     * @param txnId
     * @param hostSeqNo
     * @param memo
     * @param msg
     * @param responseTime
     * @param isRQ
     * @return
     */
    private TraceLogEntity insertTraceLog(String accessLogKey, String fromSystem, String txnId, String hostSeqNo, String memo, String msg, Integer responseTime, boolean isRQ) {
        Date sysTime = new Date();
        TraceLogEntity traceLog = new TraceLogEntity();
        traceLog.setAccessLogKey(accessLogKey);
        traceLog.setFromSystem(fromSystem);
        traceLog.setTxnId(txnId);
        traceLog.setHostSeqNo(hostSeqNo);
        traceLog.setMemo(memo);
        traceLog.setCreateTime(sysTime);
        traceLog.setResponseTime(responseTime);

        TraceLogDataEntity data = new TraceLogDataEntity();
        data.setTraceLogData(msg);

        saveTraceLog(traceLog, data, isRQ);

        return traceLog;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void saveTraceLog(ITraceLogEntity traceLog, ITraceLogDataEntity data, boolean isRQ) {
        // ETrans 不會再 update, 所以一律寫入
        data.setTraceLogKey(traceLog.getTraceLogKey());
        traceLogPersistenceProvider.saveTraceLog(traceLog, data);
    }

    public void setTraceLogPersistenceProvider(TraceLogPersistenceProvider<?, ?> traceLogPersistenceProvider) {
        this.traceLogPersistenceProvider = traceLogPersistenceProvider;
    }

    public void setSystemParamCacheManager(SystemParamCacheManager systemParamCacheManager) {
        this.systemParamCacheManager = systemParamCacheManager;
    }

    public void setE2EEHsmUtils_JSB(E2EEHsmUtils_AIBank e2eeHsmUtils_JSB) {
        e2EEHsmUtils_JSB = e2eeHsmUtils_JSB;
    }

}
