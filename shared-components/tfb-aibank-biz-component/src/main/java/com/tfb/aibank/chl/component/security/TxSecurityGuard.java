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
package com.tfb.aibank.chl.component.security;

import java.io.Serializable;

import com.ibm.tw.commons.error.ErrorStatus;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthInitData;
import com.tfb.aibank.chl.component.security.motp.bean.MotpAuthKeepData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthInitData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.type.PreAuthType;
import com.tfb.aibank.chl.type.SecurityOtpType;
import com.tfb.aibank.chl.type.TxSecurityStepType;
import com.tfb.aibank.chl.type.TxSecurityType;

// @formatter:off
/**
 * @(#)TxSecurityGuard.java
 * 
 * <p>Description:交易安控驗證資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TxSecurityGuard implements Serializable {

    private static final long serialVersionUID = -4371278115406137358L;

    /** 交易代號 */
    private String taskId;

    /** 裝置序號 */
    private String deviceIxd;

    /** 交易安控機制類別 */
    private TxSecurityType txSecurityType = TxSecurityType.UNKNOWN;

    /** 交易安控機制 - 執行步驟類別 */
    private TxSecurityStepType txSecurityStepType = TxSecurityStepType.UNKNOWN;

    /** OTP使用類型(使用OTP時才有資料) */
    private SecurityOtpType securityOtpType = SecurityOtpType.UNKNOWN;

    /** OTP使用手機號碼(使用OTP時才有資料) */
    private String otpMobile;

    /** OTP驗證服務 - 驗證初始資料(使用OTP時才有資料) */
    private OtpAuthInitData otpAuthInitData;

    /** OTP驗證服務 - 驗證流程暫存資料(使用OTP時才有資料) */
    private OtpAuthKeepData otpAuthKeepData;

    /** MOTP驗證服務 - 驗證初始資料(使用MOTP時才有資料) */
    private MotpAuthInitData motpAuthInitData;

    /** MOTP驗證服務 - 驗證流程暫存資料(使用MOTP時才有資料) */
    private MotpAuthKeepData motpAuthKeepData;

    /** 初始化檢查安控錯誤 */
    private ErrorStatus error;

    /** 延遲初始化 */
    private boolean lazyInit;

    /**
     * 預先驗證 0-否
     * 
     * 1-是
     * 
     * 2-驗證成功
     * 
     * 3-延續交易初始化
     * 
     * 4-
     */
    private PreAuthType preAuth;

    /**
     * 預驗證交易代號
     */
    private String preAuthTaskId;

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the deviceIxd
     */
    public String getDeviceIxd() {
        return deviceIxd;
    }

    /**
     * @param deviceIxd
     *            the deviceIxd to set
     */
    public void setDeviceIxd(String deviceIxd) {
        this.deviceIxd = deviceIxd;
    }

    /**
     * @return the txSecurityType
     */
    public TxSecurityType getTxSecurityType() {
        return txSecurityType;
    }

    /**
     * @param txSecurityType
     *            the txSecurityType to set
     */
    public void setTxSecurityType(TxSecurityType txSecurityType) {
        this.txSecurityType = txSecurityType;
    }

    /**
     * @return the txSecurityStepType
     */
    public TxSecurityStepType getTxSecurityStepType() {
        return txSecurityStepType;
    }

    /**
     * @param txSecurityStepType
     *            the txSecurityStepType to set
     */
    public void setTxSecurityStepType(TxSecurityStepType txSecurityStepType) {
        this.txSecurityStepType = txSecurityStepType;
    }

    /**
     * @return the securityOtpType
     */
    public SecurityOtpType getSecurityOtpType() {
        return securityOtpType;
    }

    /**
     * @param securityOtpType
     *            the securityOtpType to set
     */
    public void setSecurityOtpType(SecurityOtpType securityOtpType) {
        this.securityOtpType = securityOtpType;
    }

    /**
     * @return the otpMobile
     */
    public String getOtpMobile() {
        return otpMobile;
    }

    /**
     * @param otpMobile
     *            the otpMobile to set
     */
    public void setOtpMobile(String otpMobile) {
        this.otpMobile = otpMobile;
    }

    /**
     * @return the otpAuthInitData
     */
    public OtpAuthInitData getOtpAuthInitData() {
        return otpAuthInitData;
    }

    /**
     * @param otpAuthInitData
     *            the otpAuthInitData to set
     */
    public void setOtpAuthInitData(OtpAuthInitData otpAuthInitData) {
        this.otpAuthInitData = otpAuthInitData;
    }

    /**
     * @return the otpAuthKeepData
     */
    public OtpAuthKeepData getOtpAuthKeepData() {
        return otpAuthKeepData;
    }

    /**
     * @param otpAuthKeepData
     *            the otpAuthKeepData to set
     */
    public void setOtpAuthKeepData(OtpAuthKeepData otpAuthKeepData) {
        this.otpAuthKeepData = otpAuthKeepData;
    }

    /**
     * @return the motpAuthInitData
     */
    public MotpAuthInitData getMotpAuthInitData() {
        return motpAuthInitData;
    }

    /**
     * @param motpAuthInitData
     *            the motpAuthInitData to set
     */
    public void setMotpAuthInitData(MotpAuthInitData motpAuthInitData) {
        this.motpAuthInitData = motpAuthInitData;
    }

    /**
     * @return the motpAuthKeepData
     */
    public MotpAuthKeepData getMotpAuthKeepData() {
        return motpAuthKeepData;
    }

    /**
     * @param motpAuthKeepData
     *            the motpAuthKeepData to set
     */
    public void setMotpAuthKeepData(MotpAuthKeepData motpAuthKeepData) {
        this.motpAuthKeepData = motpAuthKeepData;
    }

    /**
     * @return the error
     */
    public ErrorStatus getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(ErrorStatus error) {
        this.error = error;
    }

    /**
     * @return the lazyInit
     */
    public boolean isLazyInit() {
        return lazyInit;
    }

    /**
     * @param lazyInit
     *            the lazyInit to set
     */
    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Task[").append(taskId).append("],");
        sb.append("LazyInit[").append(lazyInit).append("],");
        sb.append("TxSecurityType[").append(txSecurityType.name()).append("],");
        sb.append("SecurityOtpType[").append(securityOtpType.name()).append("],");
        sb.append("TxSecurityStepType[").append(txSecurityStepType.name()).append("]");
        sb.append("PreAuth[").append(preAuth == null ? "" : preAuth.name()).append("]");
        sb.append("PreAuthTaskId[").append( this.preAuthTaskId).append("]");

        return sb.toString();
    }

    /**
     * @return the preAuth
     */
    public PreAuthType getPreAuth() {
        return preAuth;
    }

    /**
     * @param preAuth
     *            the preAuth to set
     */
    public void setPreAuth(PreAuthType preAuth) {
        this.preAuth = preAuth;
    }

    /**
     * @return the preAuthTaskId
     */
    public String getPreAuthTaskId() {
        return preAuthTaskId;
    }

    /**
     * @param preAuthTaskId
     *            the preAuthTaskId to set
     */
    public void setPreAuthTaskId(String preAuthTaskId) {
        this.preAuthTaskId = preAuthTaskId;
    }

}
