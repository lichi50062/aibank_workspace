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
package com.ibm.tw.ibmb.component.log;

import java.util.Date;

/**
 * 
 * TransLogData entity interface
 * 
 * @author horance
 *
 * @param <ID>
 *            trace log id type
 * @param <LID>
 *            trace log data id type
 */
public interface ITraceLogDataEntity<ID, LID> {
    public abstract ID getTraceLogKey();

    public abstract void setTraceLogKey(ID traceLogKey);

    public abstract String getTraceLogData();

    public abstract void setTraceLogData(String traceLogData);

    /**
     * 取得 traceLogDataKey
     * 
     * @return 傳回 traceLogDataKey。
     */
    public abstract LID getTraceLogDataKey();

    /**
     * 設定 traceLogDataKey
     * 
     * @param traceLogDataKey 要設定的 traceLogDataKey。
     */
    public abstract void setTraceLogDataKey(LID traceLogDataKey);

    /**
     * 取得 createTime
     * 
     * @return 傳回 createTime。
     */
    public abstract Date getCreateTime();

    /**
     * 設定 createTime
     * 
     * @param createTime 要設定的 createTime。
     */
    public abstract void setCreateTime(Date createTime);
}