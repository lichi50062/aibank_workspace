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
package com.tfb.aibank.chl.component.userdatacache.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)BindingAuthFlagRequest.java
 * 
 * <p>Description:查詢/設定裝置綁定授權註記</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/24, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BindingAuthFlagRequest {

    /** 使用者ID */
    @Schema(description = "使用者ID")
    private String custId;

    /** 使用者代號 */
    @Schema(description = "使用者代號")
    private String userId;

    /** 公司類型 */
    @Schema(description = "公司類型")
    private Integer companyKind;

    /** 誤別碼 */
    @Schema(description = "誤別碼")
    private String uidDup;

    /** 裝置ID */
    @Schema(description = "裝置ID")
    private String deviceIxd;

    /**
     * 動作 0-查詢 1-更新
     */
    @Schema(description = "動作")
    private int action;

    /**
     * 無卡提款授權註記 0/1
     */
    @Schema(description = "無卡提款授權註記 0/1")
    private Integer withdrawalFlag;

    /**
     * 手機號碼收款授權註記 0/1
     */
    @Schema(description = "手機號碼收款授權註記 0/1")
    private Integer phoneTransferFlag;

    /**
     * 轉帳額度授權註記 0/1
     */
    @Schema(description = "轉帳額度授權註記 0/1")
    private Integer raiseTransferFlag;

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the companyKind
     */
    public Integer getCompanyKind() {
        return companyKind;
    }

    /**
     * @param companyKind
     *            the companyKind to set
     */
    public void setCompanyKind(Integer companyKind) {
        this.companyKind = companyKind;
    }

    /**
     * @return the uidDup
     */
    public String getUidDup() {
        return uidDup;
    }

    /**
     * @param uidDup
     *            the uidDup to set
     */
    public void setUidDup(String uidDup) {
        this.uidDup = uidDup;
    }

    /**
     * @return the action
     */
    public int getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(int action) {
        this.action = action;
    }

    /**
     * @return the withdrawalFlag
     */
    public Integer getWithdrawalFlag() {
        return withdrawalFlag;
    }

    /**
     * @param withdrawalFlag
     *            the withdrawalFlag to set
     */
    public void setWithdrawalFlag(Integer withdrawalFlag) {
        this.withdrawalFlag = withdrawalFlag;
    }

    /**
     * @return the phoneTransferFlag
     */
    public Integer getPhoneTransferFlag() {
        return phoneTransferFlag;
    }

    /**
     * @param phoneTransferFlag
     *            the phoneTransferFlag to set
     */
    public void setPhoneTransferFlag(Integer phoneTransferFlag) {
        this.phoneTransferFlag = phoneTransferFlag;
    }

    /**
     * @return the raiseTransferFlag
     */
    public Integer getRaiseTransferFlag() {
        return raiseTransferFlag;
    }

    /**
     * @param raiseTransferFlag
     *            the raiseTransferFlag to set
     */
    public void setRaiseTransferFlag(Integer raiseTransferFlag) {
        this.raiseTransferFlag = raiseTransferFlag;
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

}
