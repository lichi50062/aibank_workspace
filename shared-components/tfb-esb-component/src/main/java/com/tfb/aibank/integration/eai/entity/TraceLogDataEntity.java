package com.tfb.aibank.integration.eai.entity;

import java.util.Date;

import com.ibm.tw.ibmb.component.log.ITraceLogDataEntity;

/**
 * <p>
 * Title: com.ibm.tw.pib.persistence.log.entity.TraceLogDataEntity
 * </p>
 * <p>
 * Description: 電文發送記錄訊息
 * </p>
 * <p>
 * Copyright: Copyright IBM Corp. 2013. All Rights Reserved.
 * </p>
 * <p>
 * Company: IBM GBS Team
 * </p>
 * 
 * @author ricky
 * @version 1.0
 */
public class TraceLogDataEntity implements ITraceLogDataEntity<Object, Object> {

    public Object getTraceLogDataKey() {
        return traceLogDataKey;
    }

    public void setTraceLogDataKey(Object traceLogDataKey) {
        this.traceLogDataKey = traceLogDataKey;
    }

    public Object getTraceLogKey() {
        return traceLogKey;
    }

    public void setTraceLogKey(Object traceLogKey) {
        this.traceLogKey = traceLogKey;
    }

    public String getTraceLogData() {
        return traceLogData;
    }

    public void setTraceLogData(String traceLogData) {
        this.traceLogData = traceLogData;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /** 電文訊息鍵值 */
    private Object traceLogDataKey;

    /** 電文訊息鍵值 */
    private Object traceLogKey;

    /** 電文訊息資料 */
    private String traceLogData;

    private Date createTime;

}
