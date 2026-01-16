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
package com.tfb.aibank.biz.security.services.motp.bean;

//@formatter:off
/**
* @(#)InitPushDaraRs.java
* 
* <p>Description:全景MOTP - API介接服務 - InitPush RS data</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/10, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class InitPushDaraRs {

    /** 初始金鑰 */
    private String ik;

    /** 加密資料之金鑰 */
    private String pushKey;

    /** 保留欄位 */
    private String flag;

    /** App profile類型 */
    private String type;

    /** 使用者名稱 */
    private String account;

    /**
     * @return the ik
     */
    public String getIk() {
        return ik;
    }

    /**
     * @param ik
     *            the ik to set
     */
    public void setIk(String ik) {
        this.ik = ik;
    }

    /**
     * @return the pushKey
     */
    public String getPushKey() {
        return pushKey;
    }

    /**
     * @param pushKey
     *            the pushKey to set
     */
    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    /**
     * @return the flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag
     *            the flag to set
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
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

}
