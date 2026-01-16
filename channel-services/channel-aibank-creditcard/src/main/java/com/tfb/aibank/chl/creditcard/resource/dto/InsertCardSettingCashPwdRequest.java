/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)CardSettingCashPwdRequest.java
* 
* <p>Description:CARD_SETTING_CASH_PWD 資料表新增/更新 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class InsertCardSettingCashPwdRequest {

    private static final long serialVersionUID = -9221230560287015283L;

    /** 身份證字號 */
    private String custId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    private String uidDup;

    /** 使用者代號 */
    private String userId;

    /** Company Kind */
    private Integer companyKind;

    /** 用戶代碼 */
    private String nameCode;

    /** 卡號 */
    private String cardNo;

    /** 信用卡背面簽名條後3碼 */
    private String cardCvv2;

    /** 生日 */
    private Date birthday;

    /** 信用卡有效期限 */
    private String cardNedy;

    /** 上送主機交易時間 */
    private Date hostTxTime;

    /** 交易狀態 */
    private String txStatus;

    /** 交易錯誤代碼 */
    private String txErrorCode;

    /** 交易日期 */
    private Date txDate;

    /** 交易存取記錄鍵值 */
    private Integer accessLogKey;

    /** 電子郵件 */
    private String email;

    /** 交易錯誤訊息 */
    private String txErrorDesc;

    /** 錯誤系統代碼 */
    private String txErrorSystemId;

    /** CLIENT_IP */
    private String clientIp;

    /** 交易來源 */
    private String txSource;

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
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the cardNedy
     */
    public String getCardNedy() {
        return cardNedy;
    }

    /**
     * @param cardNedy
     *            the cardNedy to set
     */
    public void setCardNedy(String cardNedy) {
        this.cardNedy = cardNedy;
    }

    /**
     * @return the hostTxTime
     */
    public Date getHostTxTime() {
        return hostTxTime;
    }

    /**
     * @param hostTxTime
     *            the hostTxTime to set
     */
    public void setHostTxTime(Date hostTxTime) {
        this.hostTxTime = hostTxTime;
    }

    /**
     * @return the txStatus
     */
    public String getTxStatus() {
        return txStatus;
    }

    /**
     * @param txStatus
     *            the txStatus to set
     */
    public void setTxStatus(String txStatus) {
        this.txStatus = txStatus;
    }

    /**
     * @return the txErrorCode
     */
    public String getTxErrorCode() {
        return txErrorCode;
    }

    /**
     * @param txErrorCode
     *            the txErrorCode to set
     */
    public void setTxErrorCode(String txErrorCode) {
        this.txErrorCode = txErrorCode;
    }

    /**
     * @return the txDate
     */
    public Date getTxDate() {
        return txDate;
    }

    /**
     * @param txDate
     *            the txDate to set
     */
    public void setTxDate(Date txDate) {
        this.txDate = txDate;
    }

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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the txErrorDesc
     */
    public String getTxErrorDesc() {
        return txErrorDesc;
    }

    /**
     * @param txErrorDesc
     *            the txErrorDesc to set
     */
    public void setTxErrorDesc(String txErrorDesc) {
        this.txErrorDesc = txErrorDesc;
    }

    /**
     * @return the txErrorSystemId
     */
    public String getTxErrorSystemId() {
        return txErrorSystemId;
    }

    /**
     * @param txErrorSystemId
     *            the txErrorSystemId to set
     */
    public void setTxErrorSystemId(String txErrorSystemId) {
        this.txErrorSystemId = txErrorSystemId;
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
     * @return the txSource
     */
    public String getTxSource() {
        return txSource;
    }

    /**
     * @param txSource
     *            the txSource to set
     */
    public void setTxSource(String txSource) {
        this.txSource = txSource;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
