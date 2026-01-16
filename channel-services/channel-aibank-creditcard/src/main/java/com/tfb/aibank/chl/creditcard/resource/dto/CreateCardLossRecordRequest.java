package com.tfb.aibank.chl.creditcard.resource.dto;

/**
 * 信用卡掛失紀錄 rq
 * 
 * @author Evan Wang
 *
 */
public class CreateCardLossRecordRequest {
    /** 身分證號 */
    private String custId;

    /** 公司類型 */
    private Integer companyKind;

    /** 用戶代碼 */
    private String nameCode;

    /** 信用卡卡號 */
    private String cardNo;

    /** 交易存取記錄鍵值 */
    private String accessLogKey;

    /** 用戶代號 */
    private String userId;

    /** 客戶IP */
    private String clientIp;

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
     * @return the accessLogKey
     */
    public String getAccessLogKey() {
        return accessLogKey;
    }

    /**
     * @param accessLogKey
     *            the accessLogKey to set
     */
    public void setAccessLogKey(String accessLogKey) {
        this.accessLogKey = accessLogKey;
    }

    /**
     * @return the clientIp
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * @param clientIp
     *            the clientIp to set
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
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

}
