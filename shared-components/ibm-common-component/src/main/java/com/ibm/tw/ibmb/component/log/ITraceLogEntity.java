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
 * TransLog entity interface
 * 
 * @author horance
 *
 * @param <ID>
 *            trace log id type
 */
public interface ITraceLogEntity<ID> {
    public abstract ID getTraceLogKey();

    public abstract void setTraceLogKey(ID traceLogKey);

    public abstract String getAccessLogKey();

    public abstract void setAccessLogKey(String accessLogKey);

    public abstract String getFromSystem();

    public abstract void setFromSystem(String fromSystem);

    public abstract String getMemo();

    public abstract void setMemo(String memo);

    public abstract Date getCreateTime();

    public abstract void setCreateTime(Date createTime);

    /**
     * 取得 txnId
     * 
     * @return 傳回 txnId。
     */
    public abstract String getTxnId();

    /**
     * 設定 txnId
     * 
     * @param txnId
     *            要設定的 txnId。
     */
    public abstract void setTxnId(String txnId);

    /**
     * 取得 hostSeqNo
     * 
     * @return 傳回 hostSeqNo。
     */
    public abstract String getHostSeqNo();

    /**
     * 設定 hostSeqNo
     * 
     * @param hostSeqNo
     *            要設定的 hostSeqNo。
     */
    public abstract void setHostSeqNo(String hostSeqNo);

    /**
     * 取得 responseTime
     * 
     * @return 傳回 responseTime。
     */
    public abstract Integer getResponseTime();

    /**
     * 設定 responseTime
     * 
     * @param responseTime
     *            要設定的 responseTime。
     */
    public abstract void setResponseTime(Integer responseTime);

    /**
     * 取得 errorCode
     * 
     * @return 傳回 errorCode。
     */
    public abstract String getErrorCode();

    /**
     * 設定 errorCode
     * 
     * @param errorCode
     *            要設定的 errorCode。
     */
    public abstract void setErrorCode(String errorCode);

    /**
     * 取得 errorDesc
     * 
     * @return 傳回 errorDesc。
     */
    public abstract String getErrorDesc();

    /**
     * 設定 errorDesc
     * 
     * @param errorDesc
     *            要設定的 errorDesc。
     */
    public abstract void setErrorDesc(String errorDesc);

    /**
     * 取得 errorSystemId
     * 
     * @return 傳回 errorSystemId。
     */
    public abstract String getErrorSystemId();

    /**
     * 設定 errorSystemId
     * 
     * @param errorSystemId
     *            要設定的 errorSystemId。
     */
    public abstract void setErrorSystemId(String errorSystemId);
}