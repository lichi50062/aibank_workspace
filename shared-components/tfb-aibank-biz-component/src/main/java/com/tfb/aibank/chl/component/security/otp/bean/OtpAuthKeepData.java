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
package com.tfb.aibank.chl.component.security.otp.bean;

import java.io.Serializable;

import com.tfb.aibank.chl.component.security.otp.model.OtpModel;

// @formatter:off
/**
 * @(#)OtpAuthKeepData.java
 * 
 * <p>Description:OTP驗證服務 - 驗證流程暫存資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OtpAuthKeepData implements Serializable {

    private static final long serialVersionUID = 2596541709068271759L;

    /** OTP驗證初始資料 */
    private OtpAuthInitData initData;

    /** AES session key */
    private String aesSessionKey = null;

    /** 交易生成因子 */
    private String totalFactors = null;

    /** 加密後的交易代碼 (HEX) */
    private String encTxCode = null;

    /** 伺服器端產生的雜湊交易資料 */
    private String hashTxFactor = null;

    /** 自訂簡訊樣板 */
    private String smsMsgTemplate = null;

    /** OTP 紀錄 */
    private OtpModel model;

    /**
     * @return the initData
     */
    public OtpAuthInitData getInitData() {
        return initData;
    }

    /**
     * @param initData
     *            the initData to set
     */
    public void setInitData(OtpAuthInitData initData) {
        this.initData = initData;
    }

    /**
     * @return the aesSessionKey
     */
    public String getAesSessionKey() {
        return aesSessionKey;
    }

    /**
     * @param aesSessionKey
     *            the aesSessionKey to set
     */
    public void setAesSessionKey(String aesSessionKey) {
        this.aesSessionKey = aesSessionKey;
    }

    /**
     * @return the totalFactors
     */
    public String getTotalFactors() {
        return totalFactors;
    }

    /**
     * @param totalFactors
     *            the totalFactors to set
     */
    public void setTotalFactors(String totalFactors) {
        this.totalFactors = totalFactors;
    }

    /**
     * @return the encTxCode
     */
    public String getEncTxCode() {
        return encTxCode;
    }

    /**
     * @param encTxCode
     *            the encTxCode to set
     */
    public void setEncTxCode(String encTxCode) {
        this.encTxCode = encTxCode;
    }

    /**
     * @return the hashTxFactor
     */
    public String getHashTxFactor() {
        return hashTxFactor;
    }

    /**
     * @param hashTxFactor
     *            the hashTxFactor to set
     */
    public void setHashTxFactor(String hashTxFactor) {
        this.hashTxFactor = hashTxFactor;
    }

    /**
     * @return the smsMsgTemplate
     */
    public String getSmsMsgTemplate() {
        return smsMsgTemplate;
    }

    /**
     * @param smsMsgTemplate
     *            the smsMsgTemplate to set
     */
    public void setSmsMsgTemplate(String smsMsgTemplate) {
        this.smsMsgTemplate = smsMsgTemplate;
    }

    /**
     * @return the model
     */
    public OtpModel getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
     */
    public void setModel(OtpModel model) {
        this.model = model;
    }

}
