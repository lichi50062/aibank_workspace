package com.tfb.aibank.chl.creditcard.resource.dto;


import java.util.Date;


/**
 * <p>Description: 信用卡電子帳單設定</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Aaron
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:off
//@formatter:on
public class CardEmailBillModel {

    /**
     * 資料鍵值.
     */
    private Long eBillKey;


    /**
     * 公司鍵值.
     */
    private Integer companyKey;

    /**
     * 用戶代碼.
     */
    private String nameCode;


    /**
     * 用戶鍵值.
     */
    private Integer userkey;


    /**
     * 使用者代碼.
     */
    private String userId;


    /**
     * 申請電子帳單服務註記 0：申請 1：取消 .
     */
    private String emailBillFlag;

    /**
     * 實體帳單服務註記 0：恢復 1：取消..
     */
    private String billFlag;

    /**
     * 上送主機交易時間.
     */
    private Date hostTxTime;

    /**
     * 交易狀態 0：交易成功 1：交易失敗 4：交易未明.
     */
    private String txStatus;


    /**
     * 交易錯誤代碼.
     */
    private String txErrorCode;


    /**
     * 交易日期.
     */
    private Date txDate;


    /**
     * 建立日期.
     */
    private Date createTime;

    /**
     * 更新時間.
     */
    private Date updateTime;

    /**
     * 交易種類 0：電子帳單申請 1：電子帳單終止 2：實體帳單終止3：實體帳單終止-外部EDM..
     */
    private String txKind;


    /**
     * The Tx error desc.
     */
    private String txErrorDesc;


    /**
     * The Tx error system id.
     */
    private String txErrorSystemId;


    /**
     * 生日.
     */
    private String birthDay;

    /**
     * 信用卡背面簽名條後3碼.
     */
    private String cardcvv2;


    /**
     * 信用卡卡號前8碼.
     */
    private String cardNoPre8;

    /**
     * The Tx scource.
     */
    private String txScource;

    private String recommodType;

    private String recommodCompany;

    private String recommodPathNo;

    private String recommodIdNo;

    private String recommodStatus;

    /**
     * Gets bill key.
     *
     * @return the bill key
     */
    public Long geteBillKey() {
        return eBillKey;
    }

    /**
     * Sets bill key.
     *
     * @param eBillKey the e bill key
     */
    public void seteBillKey(Long eBillKey) {
        this.eBillKey = eBillKey;
    }

    /**
     * Gets company key.
     *
     * @return the company key
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * Sets company key.
     *
     * @param companyKey the company key
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * Gets name code.
     *
     * @return the name code
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * Sets name code.
     *
     * @param nameCode the name code
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
    }

    /**
     * Gets userkey.
     *
     * @return the userkey
     */
    public Integer getUserkey() {
        return userkey;
    }

    /**
     * Sets userkey.
     *
     * @param userkey the userkey
     */
    public void setUserkey(Integer userkey) {
        this.userkey = userkey;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets email bill flag.
     *
     * @return the email bill flag
     */
    public String getEmailBillFlag() {
        return emailBillFlag;
    }

    /**
     * Sets email bill flag.
     *
     * @param emailBillFlag the email bill flag
     */
    public void setEmailBillFlag(String emailBillFlag) {
        this.emailBillFlag = emailBillFlag;
    }

    /**
     * Gets bill flag.
     *
     * @return the bill flag
     */
    public String getBillFlag() {
        return billFlag;
    }

    /**
     * Sets bill flag.
     *
     * @param billFlag the bill flag
     */
    public void setBillFlag(String billFlag) {
        this.billFlag = billFlag;
    }

    /**
     * Gets host tx time.
     *
     * @return the host tx time
     */
    public Date getHostTxTime() {
        return hostTxTime;
    }

    /**
     * Sets host tx time.
     *
     * @param hostTxTime the host tx time
     */
    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    /**
     * Gets tx status.
     *
     * @return the tx status
     */
    public String getTxStatus() {
        return txStatus;
    }

    /**
     * Sets tx status.
     *
     * @param txStatus the tx status
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * Gets tx error code.
     *
     * @return the tx error code
     */
    public String getTxErrorCode() {
        return txErrorCode;
    }

    /**
     * Sets tx error code.
     *
     * @param txErrorCode the tx error code
     */
    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    /**
     * Gets tx date.
     *
     * @return the tx date
     */
    public Date getTxDate() {
        return txDate;
    }

    /**
     * Sets tx date.
     *
     * @param txDate the tx date
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets update time.
     *
     * @return the update time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets update time.
     *
     * @param updateTime the update time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Gets tx kind.
     *
     * @return the tx kind
     */
    public String getTxKind() {
        return txKind;
    }

    /**
     * Sets tx kind.
     *
     * @param txKind the tx kind
     */
    public void setTxKind(String txKind) {
        this.txKind = txKind;
    }

    /**
     * Gets tx error desc.
     *
     * @return the tx error desc
     */
    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    /**
     * Sets tx error desc.
     *
     * @param txErrorDesc the tx error desc
     */
    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    /**
     * Gets tx error system id.
     *
     * @return the tx error system id
     */
    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    /**
     * Sets tx error system id.
     *
     * @param txErrorSystemId the tx error system id
     */
    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
    }

    /**
     * Gets birth day.
     *
     * @return the birth day
     */
    public String getBirthDay() {
        return birthDay;
    }

    /**
     * Sets birth day.
     *
     * @param birthDay the birth day
     */
    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    /**
     * Gets cardcvv 2.
     *
     * @return the cardcvv 2
     */
    public String getCardcvv2() {
        return cardcvv2;
    }

    /**
     * Sets cardcvv 2.
     *
     * @param cardcvv2 the cardcvv 2
     */
    public void setCardcvv2(String cardcvv2) {
        this.cardcvv2 = cardcvv2;
    }

    /**
     * Gets card no pre 8.
     *
     * @return the card no pre 8
     */
    public String getCardNoPre8() {
        return cardNoPre8;
    }

    /**
     * Sets card no pre 8.
     *
     * @param cardNoPre8 the card no pre 8
     */
    public void setCardNoPre8(String cardNoPre8) {
        this.cardNoPre8 = cardNoPre8;
    }

    /**
     * Gets tx scource.
     *
     * @return the tx scource
     */
    public String getTxScource() {
        return txScource;
    }

    /**
     * Sets tx scource.
     *
     * @param txScource the tx scource
     */
    public void setTxScource(String txScource) {
        this.txScource = txScource;
    }


    public String getRecommodType() {
        return recommodType;
    }

    public void setRecommodType(String recommodType) {
        this.recommodType = recommodType;
    }

    public String getRecommodIdNo() {
        return recommodIdNo;
    }

    public void setRecommodIdNo(String recommodIdNo) {
        this.recommodIdNo = recommodIdNo;
    }

    public String getRecommodPathNo() {
        return recommodPathNo;
    }

    public void setRecommodPathNo(String recommodPathNo) {
        this.recommodPathNo = recommodPathNo;
    }

    public String getRecommodCompany() {
        return recommodCompany;
    }

    public void setRecommodCompany(String recommodCompany) {
        this.recommodCompany = recommodCompany;
    }

    public String getRecommodStatus() {
        return recommodStatus;
    }

    public void setRecommodStatus(String recommodStatus) {
        this.recommodStatus = recommodStatus;
    }
}
