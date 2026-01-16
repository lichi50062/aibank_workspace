/**
 * 
 */
package com.tfb.aibank.chl.system.resource.dto;

/**
 * @author john
 *
 */
public class GenerateTokenRequest {

    /** 身分證字號 */
    private String custId;

    /** 使用者代碼 */
    private String userId;

    /** 誤別碼 */
    private String uidDup;

    /** 公司類型 */
    private Integer companyKind;

    /** 目標平台鍵值 */
    private String channelKey;

    /** 公司類型 */
    private String nameCode;

    /**EB5556981 電文下行*/
    private LoginData loginData;
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
     * @return the channelKey
     */
    public String getChannelKey() {
        return channelKey;
    }

    /**
     * @param channelKey
     *            the channelKey to set
     */
    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }
    /**
     * 
     * @return
     */
    public LoginData getLoginData() {
        return loginData;
    }
    /**
     * 
     * @param loginData
     */
    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }


}
