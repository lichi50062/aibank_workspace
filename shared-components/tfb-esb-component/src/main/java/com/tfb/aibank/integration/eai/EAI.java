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

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpException;
import org.apache.hc.core5.http.io.HttpClientResponseHandler;
import org.apache.hc.core5.http.io.entity.ByteArrayEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.slf4j.MDC;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.biz.component.provider.SequenceProvider;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.log.ITraceLogDataEntity;
import com.ibm.tw.ibmb.component.log.ITraceLogEntity;
import com.ibm.tw.ibmb.component.log.TraceLogPersistenceProvider;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.integration.eai.config.EAIConfigProperties;
import com.tfb.aibank.integration.eai.entity.TraceLogDataEntity;
import com.tfb.aibank.integration.eai.entity.TraceLogEntity;

import tw.com.ibm.mf.eai.TxHeadRqType;

// @formatter:off
/**
 * @(#)EAI.java
 * 
 * <p>Description:電文處理介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@SuppressWarnings("rawtypes")
public final class EAI<RQ extends EAIRequest, RS extends EAIResponse> {

    private static final String EAI_TRANSATION_ENCODING = "UTF-8";

    private static IBLog _logger = IBLog.getLog(EAI.class);

    private SequenceProvider sequenceProvider;

    private TraceLogPersistenceProvider<?, ?> traceLogPersistenceProvider;

    /** 請求xml物件 */
    private RQ request;

    /** 回覆xml物件 */
    private RS response;

    /** 回覆多筆txBody物件 */
    private List<RS> responseList;

    /** 回覆類別 */
    private Class<RS> rsClz;

    /** 要發送的網址 */
    private String postURL = "";

    /** 客製化檢核結果 */
    protected List<String> invalidateMessage = new ArrayList<String>();

    private static EAIConfigProperties EAI_CONFIG;

    /**
     * 建構子
     */
    private EAI(EAIChannel channel, Class<RQ> rqClz, Class<RS> rsClz) {
        this.rsClz = rsClz;
        try {
            request = rqClz.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            request.setChannel(channel, EAI_CONFIG.getChannels().get(channel.name()));
            postURL = EAI_CONFIG.getPostURL();

        }
        catch (Exception e) {
            _logger.error(e.getMessage(), e);
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 建構一個總攬instance
     *
     * @param rqClz
     * @param rsClz
     * @return
     */
    public static <T extends EAIOverviewRequest, V extends EAIOverviewResponse> EAI<T, V> newOverviewInstance(EAIChannel channel, Class<T> rqClz, Class<V> rsClz) {
        return new EAI<T, V>(channel, rqClz, rsClz);
    }

    /**
     * 建構一個instance
     *
     * @param rqClz
     * @param rsClz
     * @return
     */
    public static <T extends EAIRequest, V extends EAIResponse> EAI<T, V> newInstance(EAIChannel channel, Class<T> rqClz, Class<V> rsClz) {
        return new EAI<T, V>(channel, rqClz, rsClz);
    }

    /**
     * 上行電文，只上行一次，不會做conversation
     *
     * @param memo
     * @param hstano
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public RS sendAndReceiveRaw(String memo, String hstano) throws XmlException, EAIException, EAIResponseException {
        return sendAndReceiveRaw(MDC.get(MDCKey.traceId.name()), memo, hstano);
    }

    /**
     * 上行電文，只上行一次，不會做conversation
     *
     * @param traceId
     * @param memo
     * @param hstano
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    @SuppressWarnings("unchecked")
    public RS sendAndReceiveRaw(String traceId, String memo, String hstano) throws XmlException, EAIException, EAIResponseException {
        long startTime = 0; // 電文上行開始時間
        long period = 0; // 電文收送時間間隔
        TraceLogEntity rsTraceLog = null;
        String stano = null;
        try {
            // 檢查訊息格式
            List<XmlError> err = new ArrayList<XmlError>();
            XmlOptions opt = new XmlOptions();
            opt.setErrorListener(err);
            request.getBody().validate(opt);
            StringBuffer validResult = new StringBuffer(0);
            Iterator<XmlError> it = err.iterator();
            while (it.hasNext()) {
                XmlError e = it.next();
                validResult.append(String.format("%s-%s", e.getErrorCode(), e.getMessage())).append('\n');
            }
            // xml檢核失敗
            if (validResult.length() > 0) {
                _logger.error("Request XML檢核失敗: " + validResult);
                throw new XmlException("檢核XML失敗: " + validResult);
            }
            // 客製化檢核
            if (!request.validate()) {
                _logger.error("Request欄位檢核失敗: " + invalidateMessage);
                throw new XmlException("Request欄位檢核失敗: " + invalidateMessage);
            }
            invalidateMessage.clear();
            // 上行欄位轉換

            request.processConvert();
            invalidateMessage.clear();
            TxHeadRqType rqHeader = request.getHeader();

            // 民國年月日(cccMMdd)
            String hsyDay = DateUtils.getROCDateStr(new Date(), StringUtils.EMPTY);
            rqHeader.setHSYDAY(hsyDay);

            if (hstano == null) {
                rqHeader.setHSTANO(this.getEAISequence());
            }
            else {
                rqHeader.setHSTANO(hstano);
            }
            // 發送訊息，將eai namespace移除
            String rqStr = request.getXmlDoc().xmlText();
            rqStr = rqStr.replace("<eai:Tx xsi:type=\"eai:TxRqType\"", "<Tx");
            rqStr = rqStr.replace("</eai:Tx>", "</Tx>");
            _logger.info("[" + traceId + "]發送Request XML: " + rqStr);
            // 寫RQ tracelog
            stano = rqHeader.getHSTANO();
            insertTraceLog(traceId, EAI_CONFIG.getClientSystemId(), request.getTxnId(), stano, memo, rqStr, null, true);
            startTime = System.currentTimeMillis();
            String rsStr = doEAIPost(rqStr);
            period = System.currentTimeMillis() - startTime;
            _logger.info("[" + traceId + "]取得Response XML(" + period + "ms):" + rsStr);
            _logger.info("SYS:MB/TXNID:" + request.getTxnId() + "/STAN:" + stano + "/ACSLOG:" + traceId + "/PERIOD:" + (int) period + "ms)");
            // 寫RS tracelog
            rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, rsStr, (int) period, false);
            RS tempRs = null;
            try {
                tempRs = rsClz.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            }
            catch (Exception e) {
                _logger.error(e.getMessage(), e);
                throw new IllegalArgumentException(e);
            }
            tempRs.setRsXml(rsStr);
            // 檢查電文回覆結果代碼
            boolean validateHERRIDResult = tempRs.validateHERRID(request);
            String errorCode = StringUtils.trimToEmptyEx(tempRs.getErrorCode());
            // body 中的 error code, log 用
            String internalErrorCode = StringUtils.trimToEmptyEx(tempRs.getInternalErrorCode());
            String errorMessage = StringUtils.trimToEmptyEx(tempRs.getErrorMsg());
            String errorSystemId = StringUtils.trimToEmptyEx(tempRs.getSystemId());
            // update trace log
            if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                updateErrorCode(rsTraceLog, errorSystemId, internalErrorCode, errorMessage, rsStr);
            }
            // 檢查電文回覆結果代碼
            if (!validateHERRIDResult) {
                _logger.error("RS回覆錯誤代碼: " + errorCode + "-" + errorMessage);
                throw new EAIResponseException(errorSystemId, errorCode, errorMessage, tempRs);
            }
            if (EAI_CONFIG.isCheckIntegrity()) {
                // 檢核回覆是否正確
                if (!tempRs.isNoData(tempRs.getHeader().getHERRID()) && !tempRs.validateWithRq(request)) {
                    _logger.error("RQ, RS配對失敗: " + invalidateMessage);
                    throw new EAIException(PibEaiErrorCode.MISMATCH_ERROR, "RQ, RS配對失敗: " + invalidateMessage, "TX900");
                }
            }
            // 下行欄位轉換
            tempRs.processConvert();

            invalidateMessage.clear();
            response = tempRs;
            return response;
        }
        catch (EAIException ex) {
            _logger.error("[" + traceId + "]取得Response XML失敗!", ex);
            String fullStackTrace = ExceptionUtils.getFullStackTrace(ex);
            if (rsTraceLog == null) {
                rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, fullStackTrace, (int) period, false);
            }
            ActionException aex = ExceptionUtils.getActionException(ex);
            if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                updateErrorCode(rsTraceLog, aex.getSystemId(), aex.getErrorCode(), aex.getErrorDesc(), fullStackTrace);
            }
            throw ex;
        }

    }

    /**
     * 發送訊息
     *
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public RS sendAndReceive(String memo) throws XmlException, EAIException, EAIResponseException {
        return sendAndReceive(MDC.get(MDCKey.traceId.name()), memo);
    }

    /**
     * 發送訊息
     *
     * @return
     * @throws EAIException
     * @throws EAIResponseException
     */
    @SuppressWarnings("unchecked")
    public RS sendAndReceive(String traceId, String memo) throws XmlException, EAIException, EAIResponseException {

        // 總覽電文須使用sendAndReceiveOverview()
        if (rsClz.equals(EAIOverviewResponse.class)) {
            throw new IllegalStateException("總覽電文請使用sendAndReceiveOverview()");
        }

        // 檢查訊息格式
        List<XmlError> err = new ArrayList<XmlError>();
        XmlOptions opt = new XmlOptions();
        opt.setErrorListener(err);

        request.getBody().validate(opt);
        StringBuffer validResult = new StringBuffer(0);
        Iterator<XmlError> it = err.iterator();
        while (it.hasNext()) {
            XmlError e = it.next();
            validResult.append(String.format("%s-%s", e.getErrorCode(), e.getMessage())).append('\n');
        }

        // xml檢核失敗
        if (validResult.length() > 0) {
            _logger.error("Request XML檢核失敗: " + validResult);
            throw new XmlException("檢核XML失敗: " + validResult);
        }

        // 客製化檢核
        if (!request.validate()) {
            _logger.error("Request欄位檢核失敗: " + invalidateMessage);
            throw new XmlException("Request欄位檢核失敗: " + invalidateMessage);
        }
        invalidateMessage.clear();

        // 上行欄位轉換
        request.processConvert();
        invalidateMessage.clear();

        TraceLogEntity rsTraceLog = null;

        int sendTimes = 0; // 發送次數
        long startTime = 0; // 電文上行開始時間
        long period = 0; // 電文收送時間間隔
        String stano = null;

        try {
            while (response == null || (response.doConversation() && request.allowConversation())) {
                if (sendTimes > EAI_CONFIG.getMaxConversactionTime()) {
                    _logger.error("電文發送超過最大限制次數");
                    throw new EAIException("電文發送超過最大限制次數");
                }

                TxHeadRqType rqHeader = request.getHeader();

                // 民國年月日(cccMMdd)
                String hsyDay = DateUtils.getROCDateStr(new Date(), StringUtils.EMPTY);
                rqHeader.setHSYDAY(hsyDay);

                // 第二次上行，RQ.HRETRN必須為'C'
                if (sendTimes > 0) {
                    rqHeader.setHRETRN("C");
                }

                // 上行電文流水號，多次來回同一批不需使用新流水號
                if (sendTimes == 0) {
                    rqHeader.setHSTANO(this.getEAISequence());
                }

                // 發送訊息，將eai namespace移除
                String rqStr = request.getXmlDoc().xmlText();
                rqStr = rqStr.replace("<eai:Tx xsi:type=\"eai:TxRqType\"", "<Tx");
                rqStr = rqStr.replace("</eai:Tx>", "</Tx>");
                _logger.info("[" + traceId + "]發送Request XML: " + rqStr);
                stano = rqHeader.getHSTANO();
                // 寫RQ tracelog
                insertTraceLog(traceId, EAI_CONFIG.getClientSystemId(), request.getTxnId(), stano, memo, rqStr, null, true);

                startTime = System.currentTimeMillis();
                String rsStr = doEAIPost(rqStr);
                period = System.currentTimeMillis() - startTime;

                _logger.info("[" + traceId + "]取得Response XML(" + period + "ms):" + rsStr);
                _logger.info("SYS:MB/TXNID:" + request.getTxnId() + "/STAN:" + stano + "/ACSLOG:" + traceId + "/PERIOD:" + (int) period + "ms)");

                // 寫RS tracelog
                rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, rsStr, (int) period, false);

                RS tempRs = null;
                try {
                    tempRs = rsClz.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                }
                catch (Exception e) {
                    _logger.error(e.getMessage(), e);
                    throw new IllegalArgumentException(e);
                }
                tempRs.setRsXml(rsStr);

                // 檢查RS格式是否正確
                err.clear();
                tempRs.getBody().validate(opt);
                validResult = new StringBuffer(0);
                if (validResult.length() > 0) {
                    _logger.error("Response XML檢核失敗: " + validResult);
                    // throw new XmlException("Response XML檢核XML失敗: " + validResult);
                }
                // 檢查電文回覆結果代碼
                boolean validateHERRIDResult = tempRs.validateHERRID(request);

                String errorCode = StringUtils.trimToEmptyEx(tempRs.getErrorCode());
                // body 中的 error code, log 用
                String internalErrorCode = StringUtils.trimToEmptyEx(tempRs.getInternalErrorCode());
                String errorMessage = StringUtils.trimToEmptyEx(tempRs.getErrorMsg());
                String errorSystemId = StringUtils.trimToEmptyEx(tempRs.getSystemId());
                // update trace log
                if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                    updateErrorCode(rsTraceLog, errorSystemId, internalErrorCode, errorMessage, rsStr);
                }

                // 檢查電文回覆結果代碼
                if (!validateHERRIDResult) {
                    _logger.error("RS回覆錯誤代碼: " + errorCode + "-" + errorMessage);
                    throw new EAIResponseException(errorSystemId, errorCode, errorMessage, tempRs);
                }

                // CBS客製0188檢核：第一次下行即回0188，則以錯誤處理
                if (ArrayUtils.contains(EAIResponse.CBS_NO_DATA_ERRID, tempRs.getHeader().getHERRID())) {
                    // 第一次下行即回0188且不在NoData錯誤代碼清單中，則以錯誤處理
                    if (sendTimes == 0 && !tempRs.isNoData(tempRs.getHeader().getHERRID())) {
                        _logger.error("RS第一次下行回覆無資料錯誤代碼: " + errorCode + "-" + errorMessage);
                        throw new EAIResponseException(errorSystemId, errorCode, errorMessage, tempRs);
                    }
                    // 非第一次表示此次0188為正常，HERRID設為0000、HFMTID設為前次取得之HFMTID
                    else if (sendTimes > 0) {
                        tempRs.getHeader().setHERRID("0000");
                        tempRs.getHeader().setHFMTID(response.getHeader().getHFMTID());
                        // 2019/12/20 視為正常時清除錯誤代碼與錯誤訊息
                        tempRs.getBody().setEMSGID("");
                        tempRs.getBody().setEMSGTXT("");
                    }
                    // 第一次下行即回0188且在NoData錯誤代碼清單中，不做任何修改，由各功能自行處理下行轉換
                    else {
                        // 不做任何修改，由各功能自行處理下行轉換
                    }
                }

                if (EAI_CONFIG.isCheckIntegrity()) {
                    // 檢核回覆是否正確
                    if (!tempRs.isNoData(tempRs.getHeader().getHERRID()) && !tempRs.validateWithRq(request)) {
                        _logger.error("RQ, RS配對失敗: " + invalidateMessage);
                        throw new EAIException(PibEaiErrorCode.MISMATCH_ERROR, "RQ, RS配對失敗: " + invalidateMessage, "TX900");
                    }
                }

                // 下行欄位轉換
                tempRs.processConvert();

                invalidateMessage.clear();

                // 第二次來回，執行合併
                if (sendTimes == 0) {
                    response = tempRs;
                }
                else {
                    response.mergeRs(tempRs);
                }

                sendTimes++;
                if (response != null) {
                    // 將發送次數設定至 response 供驗證
                    response.setSendTimes(sendTimes);
                }
            }

            return response;
        }
        catch (EAIException ex) {
            _logger.error("[" + traceId + "]取得Response XML失敗!", ex);
            String fullStackTrace = ExceptionUtils.getFullStackTrace(ex);
            if (rsTraceLog == null) {
                rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, fullStackTrace, (int) period, false);
            }
            if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                updateErrorCode(rsTraceLog, "AI", ex.getErrorCode().toString(), ex.getMessage(), fullStackTrace);
            }
            throw ex;
        }
    }

    /**
     * 發送訊息<多個TxBody需重複發送>
     * 
     * @param memo
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    public List<RS> sendAndReceiveForConversationTxBody(String memo) throws XmlException, EAIException, EAIResponseException {
        return sendAndReceiveForConversationTxBody(MDC.get(MDCKey.traceId.name()), memo);
    }

    /**
     * 發送訊息<多個TxBody需重複發送>
     * 
     * @param traceId
     * @param memo
     * @return
     * @throws XmlException
     * @throws EAIException
     * @throws EAIResponseException
     */
    @SuppressWarnings("unchecked")
    public List<RS> sendAndReceiveForConversationTxBody(String traceId, String memo) throws XmlException, EAIException, EAIResponseException {

        // 總覽電文須使用sendAndReceiveOverview()
        if (rsClz.equals(EAIOverviewResponse.class)) {
            throw new IllegalStateException("總覽電文請使用sendAndReceiveOverview()");
        }

        // 檢查訊息格式
        List<XmlError> err = new ArrayList<XmlError>();
        XmlOptions opt = new XmlOptions();
        opt.setErrorListener(err);

        request.getBody().validate(opt);
        StringBuffer validResult = new StringBuffer(0);
        Iterator<XmlError> it = err.iterator();
        while (it.hasNext()) {
            XmlError e = it.next();
            validResult.append(String.format("%s-%s", e.getErrorCode(), e.getMessage())).append('\n');
        }

        // xml檢核失敗
        if (validResult.length() > 0) {
            _logger.error("Request XML檢核失敗: " + validResult);
            throw new XmlException("檢核XML失敗: " + validResult);
        }

        // 客製化檢核
        if (!request.validate()) {
            _logger.error("Request欄位檢核失敗: " + invalidateMessage);
            throw new XmlException("Request欄位檢核失敗: " + invalidateMessage);
        }
        invalidateMessage.clear();

        // 上行欄位轉換
        request.processConvert();
        invalidateMessage.clear();

        TraceLogEntity rsTraceLog = null;

        int sendTimes = 0; // 發送次數
        long startTime = 0; // 電文上行開始時間
        long period = 0; // 電文收送時間間隔
        String stano = null;

        try {
            while (responseList == null || (response.doConversation() && request.allowConversation())) {
                if (sendTimes > EAI_CONFIG.getMaxConversactionTime()) {
                    _logger.error("電文發送超過最大限制次數");
                    throw new EAIException("電文發送超過最大限制次數");
                }

                TxHeadRqType rqHeader = request.getHeader();

                // 民國年月日(cccMMdd)
                String hsyDay = DateUtils.getROCDateStr(new Date(), StringUtils.EMPTY);
                rqHeader.setHSYDAY(hsyDay);

                // 第二次上行，RQ.HRETRN必須為'C'
                if (sendTimes > 0) {
                    rqHeader.setHRETRN("C");
                }

                // 上行電文流水號，多次來回同一批不需使用新流水號
                if (sendTimes == 0) {
                    rqHeader.setHSTANO(this.getEAISequence());
                }

                // 發送訊息，將eai namespace移除
                String rqStr = request.getXmlDoc().xmlText();
                rqStr = rqStr.replace("<eai:Tx xsi:type=\"eai:TxRqType\"", "<Tx");
                rqStr = rqStr.replace("</eai:Tx>", "</Tx>");
                _logger.info("[" + traceId + "]發送Request XML: " + rqStr);
                stano = rqHeader.getHSTANO();
                // 寫RQ tracelog
                insertTraceLog(traceId, EAI_CONFIG.getClientSystemId(), request.getTxnId(), stano, memo, rqStr, null, true);

                startTime = System.currentTimeMillis();
                String rsStr = doEAIPost(rqStr);
                period = System.currentTimeMillis() - startTime;

                _logger.info("[" + traceId + "]取得Response XML(" + period + "ms):" + rsStr);
                _logger.info("SYS:MB/TXNID:" + request.getTxnId() + "/STAN:" + stano + "/ACSLOG:" + traceId + "/PERIOD:" + (int) period + "ms)");

                // 寫RS tracelog
                rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, rsStr, (int) period, false);

                RS tempRs = null;
                try {
                    tempRs = rsClz.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                }
                catch (Exception e) {
                    _logger.error(e.getMessage(), e);
                    throw new IllegalArgumentException(e);
                }

                if (tempRs == null) {
                    throw new IllegalArgumentException("tempRs is null");
                }

                tempRs.setRsXml(rsStr);

                // 檢查RS格式是否正確
                err.clear();
                tempRs.getBody().validate(opt);
                validResult = new StringBuffer(0);
                if (validResult.length() > 0) {
                    _logger.error("Response XML檢核失敗: " + validResult);
                    // throw new XmlException("Response XML檢核XML失敗: " + validResult);
                }
                // 檢查電文回覆結果代碼
                boolean validateHERRIDResult = tempRs.validateHERRID(request);

                String errorCode = StringUtils.trimToEmptyEx(tempRs.getErrorCode());
                // body 中的 error code, log 用
                String internalErrorCode = StringUtils.trimToEmptyEx(tempRs.getInternalErrorCode());
                String errorMessage = StringUtils.trimToEmptyEx(tempRs.getErrorMsg());
                String errorSystemId = StringUtils.trimToEmptyEx(tempRs.getSystemId());
                // update trace log
                try {
                    updateErrorCode(rsTraceLog, errorSystemId, internalErrorCode, errorMessage, rsStr);
                }
                catch (Exception ex) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
                    _logger.warn("更新失敗，不影響程序。", ex);
                }

                // 檢查電文回覆結果代碼
                if (!validateHERRIDResult) {
                    _logger.error("RS回覆錯誤代碼: " + errorCode + "-" + errorMessage);
                    throw new EAIResponseException(errorSystemId, errorCode, errorMessage, tempRs);
                }

                if (EAI_CONFIG.isCheckIntegrity()) {
                    // 檢核回覆是否正確
                    if (!tempRs.isNoData(tempRs.getHeader().getHERRID()) && !tempRs.validateWithRq(request)) {
                        _logger.error("RQ, RS配對失敗: " + invalidateMessage);
                        throw new EAIException(PibEaiErrorCode.MISMATCH_ERROR, "RQ, RS配對失敗: " + invalidateMessage, "TX900");
                    }
                }

                // 下行欄位轉換
                tempRs.processConvert();

                invalidateMessage.clear();

                // 第二次來回，執行合併
                if (sendTimes == 0 && tempRs != null) {
                    responseList = new LinkedList<RS>();
                }
                response = tempRs;
                responseList.add(response);

                sendTimes++;
                if (response != null) {
                    // 將發送次數設定至 response 供驗證
                    response.setSendTimes(sendTimes);
                }
            }

            return responseList;
        }
        catch (EAIException ex) {
            _logger.error("[" + traceId + "]取得Response XML失敗!", ex);
            String fullStackTrace = ExceptionUtils.getFullStackTrace(ex);
            if (rsTraceLog == null) {
                rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, fullStackTrace, (int) period, false);
            }
            ActionException aex = ExceptionUtils.getActionException(ex);
            if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                updateErrorCode(rsTraceLog, aex.getSystemId(), aex.getErrorCode(), aex.getErrorDesc(), fullStackTrace);
            }
            throw ex;
        }
    }

    /**
     * 更新 trace log 中的 error code 等欄位
     * 
     * @param rsTraceLog
     * @param errorSystemId
     * @param errorCode
     * @param errorMessage
     */
    @SuppressWarnings("unchecked")
    private void updateErrorCode(ITraceLogEntity rsTraceLog, String errorSystemId, String errorCode, String errorMessage, String rsXml) {
        rsTraceLog.setErrorSystemId(errorSystemId);
        rsTraceLog.setErrorCode(errorCode);
        rsTraceLog.setErrorDesc(errorMessage);
        if (this.traceLogPersistenceProvider.isMutableAfterSave()) {
            this.traceLogPersistenceProvider.updateMetadata(rsTraceLog);
        }
        else {
            ITraceLogDataEntity data = new TraceLogDataEntity();
            data.setTraceLogData(rsXml);
            this.traceLogPersistenceProvider.saveTraceLog(rsTraceLog, data);
        }
    }

    /**
     * 發送總攬訊息
     *
     * @return
     * @throws XmlException
     * @throws EAIException
     */
    public RS sendAndReceiveOverview(String memo) throws XmlException, EAIException, EAIResponseException {
        return sendAndReceiveOverview(MDC.get(MDCKey.traceId.name()), memo);
    }

    /**
     * 發送總攬訊息
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public RS sendAndReceiveOverview(String traceId, String memo) throws XmlException, EAIException, EAIResponseException {
        // 非總覽電文須使用sendAndReceive()
        if (!EAIOverviewResponse.class.isAssignableFrom(rsClz)) {
            throw new IllegalStateException("非總覽電文請使用sendAndReceive()");
        }

        // 檢查訊息格式
        List<XmlError> err = new ArrayList<XmlError>();
        XmlOptions opt = new XmlOptions();
        opt.setErrorListener(err);

        request.getBody().validate(opt);
        StringBuffer validResult = new StringBuffer(0);
        Iterator<XmlError> it = err.iterator();
        while (it.hasNext()) {
            XmlError e = it.next();
            validResult.append(String.format("%s-%s", e.getErrorCode(), e.getMessage())).append('\n');
        }

        // xml檢核失敗
        if (validResult.length() > 0) {
            _logger.error("Request XML檢核失敗: " + validResult);
            throw new XmlException("檢核XML失敗: " + validResult);
        }

        // 客製化檢核
        if (!request.validate()) {
            _logger.error("Request欄位檢核失敗: " + invalidateMessage);
            throw new XmlException("Request欄位檢核失敗: " + invalidateMessage);
        }
        invalidateMessage.clear();

        // 上行欄位轉換
        request.processConvert();
        invalidateMessage.clear();
        long startTime = 0; // 電文上行開始時間
        long period = 0; // 電文收送時間間隔
        String stano = null;
        TraceLogEntity rsTraceLog = null;
        try {

            // 上行電文流水號
            TxHeadRqType rqHeader = request.getHeader();

            // 民國年月日(cccMMdd)
            String hsyDay = DateUtils.getROCDateStr(new Date(), StringUtils.EMPTY);
            rqHeader.setHSYDAY(hsyDay);

            rqHeader.setHSTANO(this.getEAISequence());

            // 發送訊息，將eai namespace移除
            String rqStr = request.getXmlDoc().xmlText();
            rqStr = rqStr.replace("<eai:Tx xsi:type=\"eai:TxRqType\"", "<Tx");
            rqStr = rqStr.replace("</eai:Tx>", "</Tx>");
            _logger.info("[" + traceId + "]發送Request XML: " + rqStr);

            // 寫RQ tracelog
            stano = rqHeader.getHSTANO();
            insertTraceLog(traceId, EAI_CONFIG.getClientSystemId(), request.getTxnId(), stano, memo, rqStr, null, true);

            startTime = System.currentTimeMillis();
            String rsStr = doEAIPost(rqStr);
            period = System.currentTimeMillis() - startTime;

            _logger.info("[" + traceId + "]取得Response XML(" + period + "ms):" + rsStr);
            _logger.info("SYS:MB/TXNID:" + request.getTxnId() + "/STAN:" + stano + "/ACSLOG:" + traceId + "/PERIOD:" + (int) period + "ms)");

            // 寫RS tracelog
            rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, rsStr, (int) period, false);

            RS tempRs = null;
            try {
                tempRs = rsClz.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
            }
            catch (Exception e) {
                _logger.error(e.getMessage(), e);
                throw new IllegalArgumentException(e);
            }
            tempRs.setRsXml(rsStr);

            // 檢查電文回覆結果代碼
            boolean validateHERRIDResult = tempRs.validateHERRID(request);

            String errorCode = "";
            String internalErrorCode = "";
            String errorMessage = "";
            String errorSystemId = "";
            if (tempRs instanceof EAIOverviewResponse) {
                errorCode = StringUtils.trimToEmptyEx(((EAIOverviewResponse) tempRs).getErrorCode());
                // body 中的 error code, log 用
                internalErrorCode = StringUtils.trimToEmptyEx(tempRs.getInternalErrorCode());
                errorMessage = StringUtils.trimToEmptyEx(((EAIOverviewResponse) tempRs).getErrorMessage());
                errorSystemId = StringUtils.trimToEmptyEx(tempRs.getSystemId());
            }
            else {
                errorCode = StringUtils.trimToEmptyEx(tempRs.getErrorCode());
                // body 中的 error code, log 用
                internalErrorCode = StringUtils.trimToEmptyEx(tempRs.getInternalErrorCode());
                errorMessage = StringUtils.trimToEmptyEx(tempRs.getErrorMsg());
                errorSystemId = StringUtils.trimToEmptyEx(tempRs.getSystemId());
            }
            // update trace log
            if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                updateErrorCode(rsTraceLog, errorSystemId, internalErrorCode, errorMessage, rsStr);
            }

            if (!validateHERRIDResult) {
                _logger.error("RS回覆錯誤代碼: " + errorCode + "-" + errorMessage);
                throw new EAIResponseException(errorSystemId, errorCode, errorMessage, tempRs);
            }

            if (EAI_CONFIG.isCheckIntegrity()) {
                // 檢核回覆是否正確
                if (!tempRs.isNoData(tempRs.getHeader().getHERRID()) && !tempRs.validateWithRq(request)) {
                    _logger.error("RQ, RS配對失敗: " + invalidateMessage);
                    throw new EAIException(PibEaiErrorCode.MISMATCH_ERROR, "RQ, RS配對失敗: " + invalidateMessage, "TX900");
                }
            }

            // 下行欄位轉換
            tempRs.processConvert();

            invalidateMessage.clear();

            return tempRs;
        }
        catch (EAIException ex) {
            _logger.error("[" + traceId + "]取得Response XML失敗!", ex);
            String fullStackTrace = ExceptionUtils.getFullStackTrace(ex);
            if (rsTraceLog == null) {
                rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, fullStackTrace, (int) period, false);
            }
            ActionException aex = ExceptionUtils.getActionException(ex);
            if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                updateErrorCode(rsTraceLog, aex.getSystemId(), aex.getErrorCode(), aex.getErrorDesc(), fullStackTrace);
            }
            throw ex;
        }
    }

    /**
     * 取得上行電文流水號
     * 
     * @return
     * @throws EAIException
     */
    private String getEAISequence() throws EAIException {
        return sequenceProvider.getNextSeq();
    }

    /**
     * 新增Trace Log
     *
     * @param traceId
     * @param fromSystem
     * @param txnId
     * @param hostSeqNo
     * @param memo
     * @param msg
     * @param responseTime
     * @param isRQ
     * @return
     */
    private TraceLogEntity insertTraceLog(String traceId, String fromSystem, String txnId, String hostSeqNo, String memo, String msg, Integer responseTime, boolean isRQ) {
        Date sysTime = new Date();
        TraceLogEntity traceLog = new TraceLogEntity();
        traceLog.setAccessLogKey(traceId);
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

    /**
     * @param traceLog
     * @param data
     * @param isRQ
     */
    @SuppressWarnings("unchecked")
    private void saveTraceLog(ITraceLogEntity traceLog, ITraceLogDataEntity data, boolean isRQ) {
        if (isRQ || this.traceLogPersistenceProvider.isMutableAfterSave()) {
            data.setTraceLogKey(traceLog.getTraceLogKey());
            traceLogPersistenceProvider.saveTraceLog(traceLog, data);
        }
        else {
            traceLog.setTraceLogKey(traceLogPersistenceProvider.generateTraceLogKey());
        }
    }

    /**
     * HTTP發送電文
     *
     * @param rq
     * @return
     * @throws Exception
     */
    private String doEAIPost(String rq) throws EAIException {
        RequestConfig reqCfg = RequestConfig.custom()
                // 設定 timeout 時間
                .setConnectionRequestTimeout(EAI_CONFIG.getConnectionTimeout(), TimeUnit.MILLISECONDS).setResponseTimeout(EAI_CONFIG.getSocketTimeout(), TimeUnit.MILLISECONDS)
                // disable HTTP-1.1 expect-continue for legacy TFB middle
                .setExpectContinueEnabled(false).build();

        HttpClient httpclient = HttpClientBuilder.create()
                // disable retry to prevent multiple request
                .disableAutomaticRetries().setDefaultRequestConfig(reqCfg).build();

        String result = null;
        try {
            HttpPost httpPost = new HttpPost(postURL);
            // convert rq to Big5 String
            ByteArrayEntity entity = new ByteArrayEntity(rq.getBytes(EAI_TRANSATION_ENCODING), ContentType.APPLICATION_XML);
            httpPost.setEntity(entity);
            result = httpclient.execute(httpPost, new HttpClientResponseHandler<String>() {
                @Override
                public String handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
                    if (_logger.isDebugEnabled()) {
                        _logger.debug(String.format("doEAIPost(String) HTTP Status : %d", response.getCode()));
                    }
                    return EntityUtils.toString(response.getEntity(), EAI_TRANSATION_ENCODING);
                }
            });
        }
        catch (SocketTimeoutException ex) {
            _logger.error(ex.getMessage(), ex);
            throw new EAIException(PibEaiErrorCode.TIMEOUT_ERROR, ex.getMessage(), ex);
        }
        catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
            throw new EAIException(PibEaiErrorCode.STATUS_UNKNOWN, ex.getMessage(), ex);
        }
        if (_logger.isDebugEnabled()) {
            _logger.debug(String.format("doEAIPost(String) Response : %s", result));
        }
        return result;
    }

    /**
     * HTTP發送電文
     *
     * @param postURL,
     *            rq
     * @return
     * @throws Exception
     */
    public String doEAIPost(String postURL, String rq) throws ActionException {
        RequestConfig reqCfg = RequestConfig.custom()
                // 設定 timeout 時間
                .setConnectionRequestTimeout(EAI_CONFIG.getConnectionTimeout(), TimeUnit.MILLISECONDS).setResponseTimeout(EAI_CONFIG.getSocketTimeout(), TimeUnit.MILLISECONDS)
                // disable HTTP-1.1 expect-continue for legacy TFB middle
                .setExpectContinueEnabled(false).build();

        HttpClient httpclient = HttpClientBuilder.create()
                // disable retry to prevent multiple request
                .disableAutomaticRetries().setDefaultRequestConfig(reqCfg).build();

        String entity = null;
        try {
            HttpPost httpPost = new HttpPost(postURL);
            httpPost.addHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(URLEncoder.encode(rq, EAI_TRANSATION_ENCODING)));
            entity = httpclient.execute(httpPost, new HttpClientResponseHandler<String>() {
                @Override
                public String handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
                    if (_logger.isDebugEnabled()) {
                        _logger.debug(String.format("doEAIPost(String, String) HTTP Status : %d", response.getCode()));
                    }
                    return EntityUtils.toString(response.getEntity());
                }
            });
        }
        catch (Exception ex) {
            _logger.error(ex.getMessage(), ex);
            throw ExceptionUtils.getActionException(ex);
        }
        if (_logger.isDebugEnabled()) {
            _logger.debug(String.format("doEAIPost(String, String) Response : %s", entity));
        }
        return entity;
    }

    /**
     * 發送訊息
     *
     * @param accessLogKey,
     *            memo, rsXml
     * @return
     * @throws XmlException,
     *             EAIException, EAIResponseException
     */
    @SuppressWarnings("unchecked")
    public RS Receive(String traceId, String memo, String rsXml) throws XmlException, EAIException, EAIResponseException {

        TraceLogEntity rsTraceLog = null;
        int sendTimes = 0; // 發送次數
        long startTime = 0; // 電文上行開始時間
        long period = 0; // 電文收送時間間隔
        String stano = null;

        try {
            while (response == null || (response.doConversation() && request.allowConversation()) && !rsXml.isEmpty()) {
                if (sendTimes > EAI_CONFIG.getMaxConversactionTime()) {
                    _logger.error("電文發送超過最大限制次數");
                    throw new EAIException("電文發送超過最大限制次數");
                }

                TxHeadRqType rqHeader = request.getHeader();

                // 民國年月日(cccMMdd)
                String hsyDay = DateUtils.getROCDateStr(new Date(), StringUtils.EMPTY);
                rqHeader.setHSYDAY(hsyDay);

                // 第二次上行，RQ.HRETRN必須為'C'
                if (sendTimes > 0) {
                    rqHeader.setHRETRN("C");
                }
                // 上行電文流水號，多次來回同一批不需使用新流水號
                if (sendTimes == 0) {
                    rqHeader.setHSTANO(this.getEAISequence());
                }
                startTime = System.currentTimeMillis();
                period = System.currentTimeMillis() - startTime;
                // 寫RS tracelog
                rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, rsXml, (int) period, false);

                RS tempRs = null;
                try {
                    tempRs = rsClz.getConstructor(new Class<?>[0]).newInstance(new Object[0]);
                }
                catch (Exception e) {
                    _logger.error(e.getMessage(), e);
                    throw new IllegalArgumentException(e);
                }
                tempRs.setRsXml(rsXml);

                // 檢查電文回覆結果代碼
                boolean validateHERRIDResult = tempRs.validateHERRID(request);

                String errorCode = StringUtils.trimToEmptyEx(tempRs.getErrorCode());
                // body 中的 error code, log 用
                String internalErrorCode = StringUtils.trimToEmptyEx(tempRs.getInternalErrorCode());
                String errorMessage = StringUtils.trimToEmptyEx(tempRs.getErrorMsg());
                String errorSystemId = StringUtils.trimToEmptyEx(tempRs.getSystemId());
                // update trace log
                if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                    updateErrorCode(rsTraceLog, errorSystemId, internalErrorCode, errorMessage, rsXml);
                }

                // 檢查電文回覆結果代碼
                if (!validateHERRIDResult) {
                    _logger.error("RS回覆錯誤代碼: " + errorCode + "-" + errorMessage);
                    throw new EAIResponseException(errorSystemId, errorCode, errorMessage, tempRs);
                }

                if (EAI_CONFIG.isCheckIntegrity()) {
                    // 檢核回覆是否正確
                    if (!tempRs.isNoData(tempRs.getHeader().getHERRID()) && !tempRs.validateWithRq(request)) {
                        _logger.error("RQ, RS配對失敗: " + invalidateMessage);
                        throw new EAIException(PibEaiErrorCode.MISMATCH_ERROR, "RQ, RS配對失敗: " + invalidateMessage, "TX900");
                    }
                }

                // 下行欄位轉換
                tempRs.processConvert();

                invalidateMessage.clear();

                // 第二次來回，執行合併
                if (sendTimes == 0) {
                    response = tempRs;
                }
                else {
                    response.mergeRs(tempRs);
                }
                sendTimes++;
                // 將發送次數設定至 response 供驗證
                if (response != null) {
                    response.setSendTimes(sendTimes);
                }
            }

            return response;
        }
        catch (EAIException ex) {
            _logger.error("[" + traceId + "]取得Response XML失敗!", ex);
            String fullStackTrace = ExceptionUtils.getFullStackTrace(ex);
            if (rsTraceLog == null) {
                rsTraceLog = insertTraceLog(traceId, "Middleware", request.getTxnId(), stano, "TOTA for " + memo, fullStackTrace, (int) period, false);
            }
            ActionException aex = ExceptionUtils.getActionException(ex);
            if (rsTraceLog != null && rsTraceLog.getTraceLogKey() != null) {
                updateErrorCode(rsTraceLog, aex.getSystemId(), aex.getErrorCode(), aex.getErrorDesc(), fullStackTrace);
            }
            throw ex;
        }
    }

    public RQ getRequest() {
        return request;
    }

    public RS getResponse() {
        return response;
    }

    public SequenceProvider getSequenceProvider() {
        return sequenceProvider;
    }

    public void setSequenceProvider(SequenceProvider provider) {
        this.sequenceProvider = provider;
    }

    public static void setConfig(EAIConfigProperties eaiConfig) {
        EAI_CONFIG = eaiConfig;
    }

    public void setTraceLogPersistenceProvider(TraceLogPersistenceProvider<?, ?> traceLogPersistenceProvider) {
        this.traceLogPersistenceProvider = traceLogPersistenceProvider;
    }

}
