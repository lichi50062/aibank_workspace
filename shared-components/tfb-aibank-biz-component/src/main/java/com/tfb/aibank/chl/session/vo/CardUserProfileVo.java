package com.tfb.aibank.chl.session.vo;

import java.util.Date;

// @formatter:off
/**
 * @(#)CardUserProfileEntityVo.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/03, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CardUserProfileVo {

    /**
     * 交易存取記錄鍵值
     */
    private Integer accessLogKey;

    /**
     * 信用卡CVV2 AES加密
     */
    private String cardCvv2;

    /**
     * 信用卡有效期限 MM/YYYY
     */
    private String cardExpired;

    /**
     * 主檔鍵值
     */
    private Integer cardKey;

    /**
     * 正附卡別 0：正卡 1：附卡
     */
    private String cardMasterFlag;

    /**
     * 信用卡卡號 AES加密
     */
    private String cardNo;

    /**
     * 公司鍵值
     */
    private Integer companyKey;

    /**
     * 信用卡契約書版次
     */
    private String contractversion;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    /**
     * 使用者鍵值
     */
    private Integer userKey;

    /**
     * @return the accessLogKey
     */
    public Integer getAccessLogKey() {
        return accessLogKey;
    }

    /**
     * @param accessLogKey
     *            the accessLogKey to set
     */
    public void setAccessLogKey(Integer accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * @return the cardCvv2
     */
    public String getCardCvv2() {
        return cardCvv2;
    }

    /**
     * @param cardCvv2
     *            the cardCvv2 to set
     */
    public void setCardCvv2(String cardCvv2) {
        this.cardCvv2 = cardCvv2;
    }

    /**
     * @return the cardExpired
     */
    public String getCardExpired() {
        return cardExpired;
    }

    /**
     * @param cardExpired
     *            the cardExpired to set
     */
    public void setCardExpired(String cardExpired) {
        this.cardExpired = cardExpired;
    }

    /**
     * @return the cardKey
     */
    public Integer getCardKey() {
        return cardKey;
    }

    /**
     * @param cardKey
     *            the cardKey to set
     */
    public void setCardKey(Integer cardKey) {
        this.cardKey = cardKey;
    }

    /**
     * @return the cardMasterFlag
     */
    public String getCardMasterFlag() {
        return cardMasterFlag;
    }

    /**
     * @param cardMasterFlag
     *            the cardMasterFlag to set
     */
    public void setCardMasterFlag(String cardMasterFlag) {
        this.cardMasterFlag = cardMasterFlag;
    }

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the contractversion
     */
    public String getContractversion() {
        return contractversion;
    }

    /**
     * @param contractversion
     *            the contractversion to set
     */
    public void setContractversion(String contractversion) {
        this.contractversion = contractversion;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

}
