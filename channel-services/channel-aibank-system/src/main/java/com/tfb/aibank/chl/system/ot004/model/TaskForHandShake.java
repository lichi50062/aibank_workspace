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
package com.tfb.aibank.chl.system.ot004.model;

// @formatter:off
/**
 * @(#)ErrorCodeForHandShakeVo.java
 * 
 * <p>Description:handshake 用交易資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TaskForHandShake {
    /** 交易 代號 */
    private String taskNo;
    /** 交易 狀態 */
    private String status;

    /** 交易 timeout */
    private Integer taskTimeout;

    /** 交易支援最小版本 */
    private String minVersion;

    /** 是否為交易支援的最小版本 */
    private boolean checkMinVersion;

    /**
     * 可被記錄(0：不可1：可)
     */
    private int canBeRecord;

    /**
     * @return the taskNo
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * @param taskNo
     *            the taskNo to set
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the taskTimeout
     */
    public Integer getTaskTimeout() {
        return taskTimeout;
    }

    /**
     * @param taskTimeout
     *            the taskTimeout to set
     */
    public void setTaskTimeout(Integer taskTimeout) {
        this.taskTimeout = taskTimeout;
    }

    public int getCanBeRecord() {
        return canBeRecord;
    }

    public void setCanBeRecord(int canBeRecord) {
        this.canBeRecord = canBeRecord;
    }

    /**
     * @return the checkMinVersion
     */
    public boolean isCheckMinVersion() {
        return checkMinVersion;
    }

    /**
     * @param checkMinVersion the checkMinVersion to set
     */
    public void setCheckMinVersion(boolean checkMinVersion) {
        this.checkMinVersion = checkMinVersion;
    }

    /**
     * @return the minVersion
     */
    public String getMinVersion() {
        return minVersion;
    }

    /**
     * @param minVersion the minVersion to set
     */
    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion;
    }
}
