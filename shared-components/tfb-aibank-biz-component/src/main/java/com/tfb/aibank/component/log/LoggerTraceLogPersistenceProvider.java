/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.component.log;

import java.util.Date;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.log.AsyncLogProcessor;
import com.ibm.tw.ibmb.component.log.ITraceLogDataEntity;
import com.ibm.tw.ibmb.component.log.ITraceLogEntity;
import com.ibm.tw.ibmb.component.log.TraceLogPersistenceProvider;

/**
 * logger implementation for trace log
 * 
 * @author horance
 *
 */
@Component
public class LoggerTraceLogPersistenceProvider implements TraceLogPersistenceProvider<String, String> {

    @Autowired
    private AsyncLogProcessor asyncLogProcessor;

    @Override
    public boolean isMutableAfterSave() {
        return false;
    }

    @Override
    public void updateMetadata(ITraceLogEntity<String> rsTraceLog) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public String generateTraceLogKey() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void saveTraceLog(ITraceLogEntity<String> traceLog, ITraceLogDataEntity<String, String> traceLogData) {
        traceLog.setAccessLogKey(MDC.get(MDCKey.traceId.name()));
        if (StringUtils.isBlank(traceLog.getTraceLogKey())) {
            traceLog.setTraceLogKey(generateTraceLogKey());
        }
        if (StringUtils.isBlank(traceLogData.getTraceLogDataKey())) {
            traceLogData.setTraceLogDataKey(UUID.randomUUID().toString());
        }
        // trace log & log 寫在一起即可
        asyncLogProcessor.doTraceLogMetaData(combileLog(traceLog, traceLogData));
    }

    private ITraceLogEntity<String> combileLog(ITraceLogEntity<String> traceLog, ITraceLogDataEntity<String, String> traceLogData) {
        TraceLogCombined combined = new TraceLogCombined();
        combined.setAccessLogKey(traceLog.getAccessLogKey());
        combined.setCreateTime(traceLog.getCreateTime());
        combined.setErrorCode(traceLog.getErrorCode());
        combined.setErrorDesc(traceLog.getErrorDesc());
        combined.setErrorSystemId(traceLog.getErrorSystemId());
        combined.setFromSystem(traceLog.getFromSystem());
        combined.setHostSeqNo(traceLog.getHostSeqNo());
        combined.setMemo(traceLog.getMemo());
        combined.setResponseTime(traceLog.getResponseTime());
        combined.setTraceLogData(traceLogData.getTraceLogData());
        combined.setTraceLogDataKey(traceLogData.getTraceLogDataKey());
        combined.setTraceLogKey(traceLog.getTraceLogKey());
        combined.setTxnId(traceLog.getTxnId());
        return combined;
    }

    class TraceLogCombined implements ITraceLogEntity<String>, ITraceLogDataEntity<String, String> {

        private String traceLogKey;
        /** ACCESS LOG鍵值 */
        private String accessLogKey;
        /** 發送電文來源系統別 */
        private String fromSystem;
        /**
         * 交易電文代號
         */
        private String txnId;
        /**
         * 電文發送序號
         */
        private String hostSeqNo;
        /** 額外註記 */
        private String memo;
        /** 訊息記錄時間 */
        private Date createTime;
        /** 主機回應時間 */
        private Integer responseTime;
        /**
         * 錯誤代碼
         */
        private String errorCode;
        /**
         * 錯誤訊息描述
         */
        private String errorDesc;
        /**
         * 錯誤系統代碼
         */
        private String errorSystemId;
        /** 電文訊息鍵值 */
        private String traceLogDataKey;
        /** 電文訊息資料 */
        private String traceLogData;

        public String getTraceLogKey() {
            return traceLogKey;
        }

        public void setTraceLogKey(String traceLogKey) {
            this.traceLogKey = traceLogKey;
        }

        public String getAccessLogKey() {
            return accessLogKey;
        }

        public void setAccessLogKey(String accessLogKey) {
            this.accessLogKey = accessLogKey;
        }

        public String getFromSystem() {
            return fromSystem;
        }

        public void setFromSystem(String fromSystem) {
            this.fromSystem = fromSystem;
        }

        public String getTxnId() {
            return txnId;
        }

        public void setTxnId(String txnId) {
            this.txnId = txnId;
        }

        public String getHostSeqNo() {
            return hostSeqNo;
        }

        public void setHostSeqNo(String hostSeqNo) {
            this.hostSeqNo = hostSeqNo;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }

        public Integer getResponseTime() {
            return responseTime;
        }

        public void setResponseTime(Integer responseTime) {
            this.responseTime = responseTime;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorDesc() {
            return errorDesc;
        }

        public void setErrorDesc(String errorDesc) {
            this.errorDesc = errorDesc;
        }

        public String getErrorSystemId() {
            return errorSystemId;
        }

        public void setErrorSystemId(String errorSystemId) {
            this.errorSystemId = errorSystemId;
        }

        public String getTraceLogDataKey() {
            return traceLogDataKey;
        }

        public void setTraceLogDataKey(String traceLogDataKey) {
            this.traceLogDataKey = traceLogDataKey;
        }

        public String getTraceLogData() {
            return traceLogData;
        }

        public void setTraceLogData(String traceLogData) {
            this.traceLogData = traceLogData;
        }

    }

}
