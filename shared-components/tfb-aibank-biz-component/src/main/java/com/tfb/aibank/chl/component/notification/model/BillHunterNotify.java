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
package com.tfb.aibank.chl.component.notification.model;

// @formatter:off
/**
 * @(#)BillHunterNotify.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/23, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BillHunterNotify extends Notify {

    /** login user 取得 COMPANY_UID */
    private String custId;

    /** 帳單月份(轉為民國年月YYYMM) */
    private String yyyMM;

    /** EMAIL */
    private String email;

    /** AI Bank User 取得 BIRTHDAY:YYYYMMDD */
    private String birthday;

    /** 客戶自設密碼 */
    private String secret;

    /** 電子帳單加密註記 */
    private String encryption;

    private String serverUrlType = "";

    private String resultCode;

    private String resultMsg;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getYyyMM() {
        return yyyMM;
    }

    public void setYyyMM(String yyyMM) {
        this.yyyMM = yyyMM;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEncryption() {
        return encryption;
    }

    public void setEncryption(String encryption) {
        this.encryption = encryption;
    }

    public String getServerUrlType() {
        return serverUrlType;
    }

    public void setServerUrlType(String serverUrlType) {
        this.serverUrlType = serverUrlType;
    }

    /**
     * @return the resultCode
     */
    public String getResultCode() {
        return resultCode;
    }

    /**
     * @param resultCode
     *            the resultCode to set
     */
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    /**
     * @return the resultMsg
     */
    public String getResultMsg() {
        return resultMsg;
    }

    /**
     * @param resultMsg
     *            the resultMsg to set
     */
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

}
