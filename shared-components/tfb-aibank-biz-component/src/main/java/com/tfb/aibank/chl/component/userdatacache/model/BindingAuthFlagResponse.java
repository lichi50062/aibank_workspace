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
 * @(#)BindingAuthFlagResponse.java
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
public class BindingAuthFlagResponse {

    /**
     * 執行狀態 0-成功 1-失敗
     */
    @Schema(description = "執行狀態 0/1")
    private int status;

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
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
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

}
