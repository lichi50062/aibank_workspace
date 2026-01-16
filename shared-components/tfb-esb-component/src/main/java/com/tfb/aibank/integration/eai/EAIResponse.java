/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;
import com.tfb.aibank.integration.eai.converter.EAIReplaceConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eai.TxBodyRqType;
import tw.com.ibm.mf.eai.TxBodyRsType;
import tw.com.ibm.mf.eai.TxDocument;
import tw.com.ibm.mf.eai.TxHeadRsType;
import tw.com.ibm.mf.eai.TxRepeatType;
import tw.com.ibm.mf.eai.TxRsType;

// @formatter:off
/**
 * @(#)EAIResponse.java
 * 
 * <p>Description:電文回應類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public abstract class EAIResponse<T extends TxBodyRqType, V extends TxBodyRsType> {

    private static IBLog _logger = IBLog.getLog(EAIRequest.class);

    /** EAI xml namespace */
    protected static final String EAI_NAMESPACE_URI = "http://www.ibm.com.tw/mf/eai";

    /** CBS 視作無資料的代碼, 目前檢查代碼: "0188", "0344", "0868" */
    public static final String[] CBS_NO_DATA_ERRID = { "0188", "0344", "0868" };

    /** 390 視作無資料的代碼, 目前檢查代碼: "EBB2", "E005", "SP01", "SP02" */
    protected static final String[] NO_DATA_ERRID = { "EBB2", "E005", "SP01", "SP02" };

    /** xmlbeans回覆訊息物件 */
    protected TxDocument xmlDoc;

    /** 實際Rs Schema Type */
    private SchemaType rsSchemaType;

    /** 電文代碼 */
    private String txnId;

    private int sendTimes = 0;

    /**
     * 建構子
     */
    @SuppressWarnings("unchecked")
    protected EAIResponse(String txnId) {
        this.txnId = txnId;

        ParameterizedType aParameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<V> rsClz = (Class<V>) aParameterizedType.getActualTypeArguments()[1];

        // 取得實際Rs Type
        try {
            rsSchemaType = (SchemaType) rsClz.getField("type").get(null);
        }
        catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            _logger.error("RS SchemaType 擷取失敗!", e);
        }
    }

    /**
     * 取得Header
     *
     * @return
     */
    public TxHeadRsType getHeader() {
        TxRsType txRsType = (TxRsType) xmlDoc.getTx().changeType(TxRsType.type);
        return txRsType.getTxHead();
    }

    /**
     * 取得xmlbeans物件
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public V getBody() {
        TxRsType rsType = (TxRsType) xmlDoc.getTx().changeType(TxRsType.type);
        return (V) rsType.getTxBodyArray()[0].changeType(rsSchemaType);
    }

    @SuppressWarnings("unchecked")
    public <E extends V> E getBodyAsType(Class<E> expectBodyClass) {
        TxRsType rsType = (TxRsType) xmlDoc.getTx().changeType(TxRsType.type);
        try {
            return (E) rsType.getTxBodyArray()[0].changeType((SchemaType) expectBodyClass.getField("type").get(null));
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <E> List<E> getRepeatAsType(Class<E> expectRepeatClass) {
        ArrayList<Object> array = new ArrayList<>();
        TxRsType rsType = (TxRsType) xmlDoc.getTx().changeType(TxRsType.type);
        TxBodyRsType txBody = rsType.getTxBodyArray()[0];
        TxRepeatType[] repeat = txBody.getTxRepeatArray();
        try {
            for (int i = 0; i < repeat.length; i++) {
                array.add(repeat[i].changeType((SchemaType) expectRepeatClass.getField("type").get(null)));
            }
            return (List<E>) array;
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 取得xmlbeans xml document
     *
     * @return
     */
    public TxDocument getXmlDoc() {
        return xmlDoc;
    }

    /**
     * 設定並解析xml
     *
     * @param xml
     * @throws XmlException
     */
    void setRsXml(String xml) throws XmlException {
        // RS須加上xsi:type，讓xmlbeans看得懂
        xml = xml.replaceAll("xsi:type=\"eai:TxRqType\"", "xsi:type=\"eai:TxRsType\"").replaceAll("<TxBody>", String.format("<TxBody xsi:type=\"eb:%sSvcRsType\" xmlns:eb=\"http://www.ibm.com.tw/mf/eb\">", txnId));

        // 回覆有時會將namespace帶回
        // v1.1 JAR黑箱調整xmlBeans，RS加上xmlns:xsi
        if (xml.indexOf("xmlns:eai=\"http://www.ibm.com.tw/mf/eai\"") == -1) {
            xml = xml.replaceAll("<Tx ", "<eai:Tx xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"eai:TxRsType\" xmlns:eai=\"http://www.ibm.com.tw/mf/eai\" ");
        }
        else {
            xml = xml.replaceAll("<Tx ", "<eai:Tx xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"eai:TxRsType\" ");
        }

        xml = xml.replaceAll("<Tx>", "<eai:Tx xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"eai:TxRsType\" xmlns:eai=\"http://www.ibm.com.tw/mf/eai\">").replaceAll("</Tx>", "</eai:Tx>");
        xmlDoc = (TxDocument) TxDocument.Factory.parse(xml).changeType(TxDocument.type);
    }

    /**
     * 合併RS repeat欄位
     *
     * @param rs
     */
    void mergeRs(EAIResponse<T, V> rs) {
        // 取代Header
        TxRsType txRsType = (TxRsType) xmlDoc.getTx().changeType(TxRsType.type);
        txRsType.setTxHead(rs.getHeader());

        // 合併非repeat
        XmlCursor cursor = rs.getBody().newCursor();
        cursor.selectPath("*");
        XmlCursor newCursor = getBody().newCursor();
        newCursor.selectPath("*");
        newCursor.toNextSelection();
        while (cursor.toNextSelection()) {
            XmlCursor temp = getBody().newCursor();
            temp.selectPath(cursor.getName().toString());
            if (!temp.toNextSelection()) { // 已存在不合併
                newCursor.insertElementWithText(cursor.getName(), cursor.getTextValue());
            }
        }

        // 合併repeat
        TxRepeatType[] repeatOld = getBody().getTxRepeatArray();
        TxRepeatType[] repeatNew = rs.getBody().getTxRepeatArray();

        TxRepeatType[] allRepeat = new TxRepeatType[repeatOld.length + repeatNew.length];
        System.arraycopy(repeatOld, 0, allRepeat, 0, repeatOld.length);
        System.arraycopy(repeatNew, 0, allRepeat, repeatOld.length, repeatNew.length);
        getBody().setTxRepeatArray(allRepeat);
    }

    /**
     * 處理轉換
     *
     * @return
     * @throws EAIException
     */
    void processConvert() throws EAIException {
        long start = System.currentTimeMillis();
        XmlCursor cursor = null;
        try {
            // 取得Annotation設定的converter
            Annotation[] annos = this.getClass().getAnnotations();
            for (Annotation anno : annos) {
                if (anno instanceof Converter) {
                    Converter convert = (Converter) anno;

                    // 預設轉換器
                    CustomConverter[] cConv = convert.customConverters();
                    for (CustomConverter conv : cConv) {
                        // 取得轉換器，進行欄位值轉換
                        EAIConverter converter = conv.converter().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                        cursor = xmlDoc.newCursor();
                        cursor.selectPath(String.format("declare namespace eai='%s'; %s", EAI_NAMESPACE_URI, conv.fieldXPath()));
                        while (cursor.toNextSelection()) {
                            String converted = StringUtils.EMPTY;
                            if (converter instanceof EAITrimConverter) {
                                converted = converter.convertRs(cursor.getTextValue());
                            }
                            else if (converter instanceof EAIReplaceConverter) {
                                converted = converter.convertRs(cursor.getTextValue(), conv.replaceRegex(), conv.replacement());
                            }
                            else if (converter instanceof EAIPadLeftConverter) {
                                converted = converter.convertRq(cursor.getTextValue(), conv.padSize(), conv.padChar());
                            }
                            else if (converter instanceof EAIPadRightConverter) {
                                converted = converter.convertRq(cursor.getTextValue(), conv.padSize(), conv.padChar());
                            }
                            cursor.setTextValue(converted);
                        }
                    }

                    // 日期和時間轉換器
                    DateAndTimeConverter[] dConv = convert.datetimeConverters();
                    for (DateAndTimeConverter conv : dConv) {
                        // 取得轉換器，進行欄位值轉換
                        EAIConverter converter = conv.converter().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                        cursor = xmlDoc.newCursor();
                        cursor.selectPath(String.format("declare namespace eai='%s'; %s", EAI_NAMESPACE_URI, conv.fieldXPath()));
                        while (cursor.toNextSelection()) {
                            String converted = StringUtils.EMPTY;
                            if (converter instanceof EAIDateConverter) {
                                converted = converter.convertRs(cursor.getTextValue(), conv.inputPattern(), conv.outputPattern());
                            }
                            else if (converter instanceof EAITimeConverter) {
                                converted = converter.convertRs(cursor.getTextValue(), conv.inputPattern(), null);
                            }
                            else if (converter instanceof EAIDateTimeConverter) {
                                converted = converter.convertRs(cursor.getTextValue(), conv.inputPattern(), conv.outputPattern());
                            }

                            if (StringUtils.isNotBlank(converted)) {
                                cursor.setTextValue(converted);
                            }
                            else {
                                // 日期欄位資料，若為空字串，會造成「org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException: Invalid date value: wrong type: 」錯誤。
                                _logger.debug("Tag({})欄位資料為空字串，將Tag移除。", cursor.getName().getLocalPart());
                                cursor.removeXml();
                            }
                        }
                    }

                    // 數值轉換器
                    DecimalConverter[] nConv = convert.decimalConverters();
                    for (DecimalConverter conv : nConv) {
                        // 取得轉換器，進行欄位值轉換
                        EAIConverter converter = conv.converter().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                        cursor = xmlDoc.newCursor();
                        _logger.info(conv.fieldXPath());
                        cursor.selectPath(String.format("declare namespace eai='%s'; %s", EAI_NAMESPACE_URI, conv.fieldXPath()));
                        while (cursor.toNextSelection()) {
                            String converted = StringUtils.EMPTY;
                            if (converter instanceof EAIDecimalConverter) {
                                converted = converter.convertRs(cursor.getTextValue(), conv.scale(), conv.pattern(), conv.isPostSign(), conv.showPlusSign(), conv.exclude());
                            }
                            if (StringUtils.isNotBlank(converted)) {
                                cursor.setTextValue(converted);
                            }
                            else {
                                _logger.debug("Tag({})欄位資料為空字串，將Tag移除。", cursor.getName().getLocalPart());
                                cursor.removeXml();
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            String msg = "RS欄位轉換失敗!, cursor: " + (cursor != null ? cursor.xmlText() : "null");
            _logger.error(msg + "\\n" + ex.getMessage(), ex);
            throw new EAIException(msg, ex);
        }
        finally {
            if (_logger.isInfoEnabled()) {
                _logger.info("EAIResponse.convert time: " + (System.currentTimeMillis() - start));
            }
        }
    }

    /**
     * 判斷是否要做電文來回
     *
     * @return
     */
    boolean doConversation() {
        TxHeadRsType header = getHeader();

        // 下行回覆錯誤
        if (!"0000".equals(header.getHERRID())) {
            return false;
        }

        return "C".equals(header.getHRETRN());
    }

    /**
     * 檢查下行電文狀態
     *
     * @return
     * @throws EAIException
     */
    public boolean validateHERRID(EAIRequest<T> request) throws EAIException {
        TxHeadRsType header = getHeader();
        if (header == null) {
            return false;
        }
        String errId = StringUtils.trimToEmptyEx(header.getHERRID());
        if ("0000".equals(errId)) {
            return true;
        }
        else if (isNoData(errId)) {
            // 無資料視為正常回應
            return true;
        }
        else if (StringUtils.isBlank(errId)) {
            // 通訊異常
            String errorMsg = getErrorMsg();
            throw new EAIException(PibEaiErrorCode.STATUS_UNKNOWN, StringUtils.isNotBlank(errorMsg) ? errorMsg : "通訊異常(HERRID為空白)", errId);
        }

        return false;
    }

    /**
     * 檢查是否為無資料，供 {@link EAI.java} 判斷使用
     *
     * @return
     */
    protected boolean isNoData(String errId) {
        return (is390NoData(errId) || isCBSNoData(errId));
    }

    /**
     * 390 視作無資料的代碼, 目前檢查代碼: "EBB2", "E005", "SP01", "SP02"
     *
     * @return
     */
    protected boolean is390NoData(String errId) {
        return ArrayUtils.contains(NO_DATA_ERRID, errId);
    }

    /**
     * CBS 視作無資料的代碼, 目前檢查代碼: "0188", "0344", "0868"
     *
     * @return
     */
    protected boolean isCBSNoData(String errId) {
        return ArrayUtils.contains(CBS_NO_DATA_ERRID, errId);
    }

    /**
     * 取得 sendTimes
     *
     * @return 傳回 sendTimes。
     */
    public int getSendTimes() {
        return sendTimes;
    }

    /**
     * 設定 sendTimes
     *
     * @param sendTimes
     *            要設定的 sendTimes。
     */
    public void setSendTimes(int sendTimes) {
        this.sendTimes = sendTimes;
    }

    /**
     * 取得錯誤訊息
     *
     * @return
     */
    public String getErrorMsg() {
        return StringUtils.trimToEmptyEx(getBody().getEMSGTXT());
    }

    /**
     * 取得錯誤系統代碼
     *
     * @return
     */
    public String getSystemId() {
        return StringUtils.trimToEmptyEx(StringUtils.left(getHeader().getHWSID(), 10));
    }

    /**
     * 取得錯誤代碼
     * 
     * @return
     */
    public String getErrorCode() {
        return StringUtils.trimToEmptyEx(getHeader().getHERRID());
    }

    /**
     * 檢核回復訊息是否合法
     * 
     * @param rs
     * @return
     */
    public boolean validateWithRq(EAIRequest<T> rq) {
        return true;
    }

    /**
     * 取得Body中的 error code (for trace log), 特殊電文(如EB5556981)需自行覆寫
     * 
     * @return
     */
    public String getInternalErrorCode() {
        return getErrorCode();
    }

}
