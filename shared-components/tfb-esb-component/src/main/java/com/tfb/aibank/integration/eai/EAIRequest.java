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

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.slf4j.MDC;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.config.EAIChannelConfig;
import com.tfb.aibank.integration.eai.converter.EAIConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;
import com.tfb.aibank.integration.eai.converter.EAIReplaceConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eai.FMPConnectionStringType;
import tw.com.ibm.mf.eai.TxBodyRqType;
import tw.com.ibm.mf.eai.TxDocument;
import tw.com.ibm.mf.eai.TxHeadRqType;
import tw.com.ibm.mf.eai.TxRqType;
import tw.com.ibm.mf.eai.TxRqType.Fontremapping;

// @formatter:off
/**
 * @(#)EAIRequest.java
 * 
 * <p>Description:電文請求類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAIRequest<T extends TxBodyRqType> implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    private static IBLog _logger = IBLog.getLog(EAIRequest.class);

    /** 主機教時時間格式 */
    private static final ThreadLocal<SimpleDateFormat> TXMSRN_FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("HHmmss00"));

    /** EAI xml namespace */
    public static final String EAI_NAMESPACE_URI = "http://www.ibm.com.tw/mf/eai";

    /** xmlbeans請求訊息物件 */
    protected TxDocument xmlDoc;

    /** 實際Rq Schema Type */
    protected SchemaType rqSchemaType;

    /** 電文代號 */
    private String txnId;

    /**
     * 建構子
     */
    @SuppressWarnings("unchecked")
    protected EAIRequest(String txnId) {

        this.txnId = txnId;

        // 取得實際Rq Type
        ParameterizedType aParameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        Class<T> rqClz = (Class<T>) aParameterizedType.getActualTypeArguments()[0];

        for (Field f : rqClz.getDeclaredFields()) {
            if (f.getName().equals("type")) {
                try {
                    rqSchemaType = (SchemaType) f.get(null);
                }
                catch (IllegalArgumentException | IllegalAccessException e) {
                    _logger.error("RQ SchemaType 擷取失敗!", e);
                }
            }
        }

        // 新增共用部分
        xmlDoc = (TxDocument) TxDocument.Factory.newInstance().changeType(TxDocument.type);
        TxRqType txRqType = (TxRqType) xmlDoc.addNewTx().changeType(TxRqType.type);
        txRqType.setTxid(txnId);
        txRqType.setEncoding("UTF-8");
        txRqType.setFontremapping(Fontremapping.OFF);

        TxHeadRqType header = txRqType.addNewTxHead();
        header.setHTXTID(txnId);

        txRqType.addNewTxBody();

    }

    /**
     * 設定發送通道
     * 
     * @param channel
     */
    void setChannel(EAIChannel channel, EAIChannelConfig config) {
        String spName = config.getSpName();
        String loginId = config.getLoginId();
        String wsId = getHSWID(config);
        String tlId = config.getTellerId();

        TxRqType txRqType = (TxRqType) xmlDoc.getTx().changeType(TxRqType.type);
        FMPConnectionStringType conn = txRqType.addNewFMPConnectionString();
        conn.setSPName(spName);
        conn.setLoginID(loginId);
        conn.setTxnId(txnId);

        TxHeadRqType header = txRqType.getTxHead();
        if (StringUtils.isBlank(header.getHWSID())) {
            header.setHWSID(wsId);
        }
        if (StringUtils.isBlank(header.getHTLID())) {
            header.setHTLID(tlId);
        }
        header.setTXMSRN(getTXMSRN());
    }

    private String getHSWID(EAIChannelConfig config) {
        String instanceId = MDC.get(MDCKey.springinstanceid.name());
        if (StringUtils.contains(instanceId, ".")) {
            // 若有 . 表示本機開發，取前10碼
            return StringUtils.left(instanceId, 10);
        }
        else if (StringUtils.isNotBlank(config.getGeneratedWsId())) {
            return config.getGeneratedWsId();
        }
        else { // pod name
               // 依開頭判斷
            if (StringUtils.startsWith(instanceId, "batch")) {
                String hwsid = "bat-" + StringUtils.right(instanceId, 5);
                config.setGeneratedWsId(hwsid);
                return hwsid;
            }
            else if (StringUtils.startsWith(instanceId, "biz")) {
                String[] parts = StringUtils.split(instanceId, "-");
                StringBuilder sb = new StringBuilder();
                // ex: biz-push-listener-deployment-asdbakjs-12345 --> puli-12345
                // biz-user-deployment-asdasaf-12345 --> user-12345
                // biz-insurance-deployment-asdasdasd-12346 -->ince-12346
                if (parts.length < 6) {
                    sb.append(StringUtils.left(parts[1], 4));
                }
                else {
                    sb.append(StringUtils.left(parts[1], 2));
                    sb.append(StringUtils.left(parts[2], 2));
                }
                sb.append("-").append(StringUtils.right(parts[parts.length - 1], 5));
                String hwsid = sb.toString();
                config.setGeneratedWsId(hwsid);
                return hwsid;
            }
        }
        // return setting from config file
        return config.getWsId();
    }

    /**
     * 取得 TXNSRN
     *
     * @return
     */
    protected String getTXMSRN() {
        String className = getClass().getSimpleName();
        if (StringUtils.startsWith(className, "EB55") || StringUtils.startsWith(className, "EB61")) {
            Date date = new Date();
            date = DateUtils.addMinutes(date, 3);
            return TXMSRN_FORMATTER.get().format(date);
        }
        return "";
    }

    /**
     * 檢核訊息合法性
     * 
     * @return
     */
    public boolean validate() {
        return true;
    }

    /**
     * 是否允許電文來回(預設允許)
     * 
     * @return
     */
    protected boolean allowConversation() {
        return true;
    }

    /**
     * 處理欄位轉換
     * 
     * @return
     * @throws EAIException
     */
    void processConvert() throws EAIException {
        long start = System.currentTimeMillis();
        String lastNodeName = null;
        try {
            // 取得Annotation設定的converter
            Annotation[] annos = this.getClass().getAnnotations();
            for (Annotation anno : annos) {
                if (anno instanceof Converter) {
                    Converter convert = (Converter) anno;

                    // 自定義轉換器
                    CustomConverter[] cConv = convert.customConverters();
                    for (CustomConverter conv : cConv) {
                        // 取得轉換器，進行欄位值轉換
                        EAIConverter converter = conv.converter().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                        XmlCursor cursor = xmlDoc.newCursor();
                        cursor.selectPath("declare namespace eai='" + EAI_NAMESPACE_URI + "'; " + conv.fieldXPath());
                        while (cursor.toNextSelection()) {
                            lastNodeName = cursor.getDomNode().getNodeName();

                            String converted = StringUtils.EMPTY;
                            if (converter instanceof EAITrimConverter) {
                                converted = converter.convertRq(cursor.getTextValue());
                            }
                            else if (converter instanceof EAIReplaceConverter) {
                                converted = converter.convertRq(cursor.getTextValue(), conv.replaceRegex(), conv.replacement());
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
                        XmlCursor cursor = xmlDoc.newCursor();
                        cursor.selectPath("declare namespace eai='" + EAI_NAMESPACE_URI + "'; " + conv.fieldXPath());
                        while (cursor.toNextSelection()) {
                            lastNodeName = cursor.getDomNode().getNodeName();

                            String converted = StringUtils.EMPTY;
                            if (converter instanceof EAIDateConverter) {
                                converted = converter.convertRq(cursor.getTextValue(), conv.inputPattern(), conv.outputPattern());
                            }
                            else if (converter instanceof EAITimeConverter) {
                                converted = converter.convertRq(cursor.getTextValue(), null, conv.outputPattern());
                            }
                            else if (converter instanceof EAIDateTimeConverter) {
                                converted = converter.convertRq(cursor.getTextValue(), conv.inputPattern(), conv.outputPattern());
                            }
                            cursor.setTextValue(converted);
                        }
                    }

                    // 數值轉換器
                    DecimalConverter[] nConv = convert.decimalConverters();
                    for (DecimalConverter conv : nConv) {
                        // 取得轉換器，進行欄位值轉換
                        EAIConverter converter = conv.converter().getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                        XmlCursor cursor = xmlDoc.newCursor();
                        cursor.selectPath("declare namespace eai='" + EAI_NAMESPACE_URI + "'; " + conv.fieldXPath());
                        while (cursor.toNextSelection()) {
                            lastNodeName = cursor.getDomNode().getNodeName();

                            String converted = StringUtils.EMPTY;
                            if (converter instanceof EAIDecimalConverter) {
                                converted = converter.convertRq(cursor.getTextValue(), conv.scale(), conv.pattern(), conv.isPostSign(), conv.showPlusSign());
                            }
                            cursor.setTextValue(converted);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            _logger.error("RQ欄位轉換失敗! nodeName: " + lastNodeName, ex);
            throw new EAIException("RQ欄位轉換失敗! nodeName: " + lastNodeName, ex);
        }
        finally {
            if (_logger.isInfoEnabled()) {
                _logger.info("EAIRequest.convert time: " + (System.currentTimeMillis() - start));
            }
        }

    }

    /**
     * 取得Header
     * 
     * @return
     */
    public TxHeadRqType getHeader() {
        TxRqType txRqType = (TxRqType) xmlDoc.getTx().changeType(TxRqType.type);
        return txRqType.getTxHead();
    }

    /**
     * 取得xmlbeans物件
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    public T getBody() {
        TxRqType rqType = (TxRqType) xmlDoc.getTx().changeType(TxRqType.type);
        return (T) rqType.getTxBody().changeType(rqSchemaType);
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
     * 取得 txnId
     *
     * @return 傳回 txnId。
     */
    public String getTxnId() {
        return txnId;
    }

}
