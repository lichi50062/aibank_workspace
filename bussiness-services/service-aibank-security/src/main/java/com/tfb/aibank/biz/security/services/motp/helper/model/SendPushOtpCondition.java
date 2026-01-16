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
package com.tfb.aibank.biz.security.services.motp.helper.model;

import com.tfb.aibank.biz.security.repository.entities.MotpTransDataEntity;
import com.tfb.aibank.biz.security.repository.entities.MotpVerifyCarrierEntity;

// @formatter:off
/**
 * @(#)SendPushOtpCondition.java
 * 
 * <p>Description:發送推播OTP認證</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/15, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SendPushOtpCondition {

    /** MOTP交易認證資訊 */
    private MotpTransDataEntity motpTransData;

    /** MOTP交易驗證之載具資訊 */
    private MotpVerifyCarrierEntity carrierData;

    /** 使用者名稱 */
    private String account;

    /** 群組名稱 */
    private String group;

    /**
     * @return the motpTransData
     */
    public MotpTransDataEntity getMotpTransData() {
        return motpTransData;
    }

    /**
     * @param motpTransData
     *            the motpTransData to set
     */
    public void setMotpTransData(MotpTransDataEntity motpTransData) {
        this.motpTransData = motpTransData;
    }

    /**
     * @return the carrierData
     */
    public MotpVerifyCarrierEntity getCarrierData() {
        return carrierData;
    }

    /**
     * @param carrierData
     *            the carrierData to set
     */
    public void setCarrierData(MotpVerifyCarrierEntity carrierData) {
        this.carrierData = carrierData;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     *            the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * @param group
     *            the group to set
     */
    public void setGroup(String group) {
        this.group = group;
    }

}
