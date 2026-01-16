/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;

//@formatter:off
/**
* @(#)insertCardBorrowCashRequest.java
* 
* <p>Description:CARD_BORROW_CASH 資料表新增 Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class insertCardBorrowCashRequest {

    /** 身份證字號 */
    private String custId;

    /** [誤別碼] 公司統編或身份證字號誤別碼 */
    private String uidDup;

    /** 使用者代號 */
    private String userId;

    /** Company Kind */
    private Integer companyKind;

    /**
     * 用戶代碼
     */
    private String nameCode;

    /**
     * 卡號
     */
    private String cardNo;

    /**
     * 信用卡背面簽名條後3碼
     */
    private String cardCvv2;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 信用卡有效期限
     */
    private String cardNedy;

    /**
     * 持卡人姓名
     */
    private String cardOwnerName;

    /**
     * 交易類型
     */
    private String txType;

    /**
     * 入款帳號
     */
    private String payeeAccount;

    /**
     * 入款帳號銀行代碼
     */
    private String payeeBank;

    /**
     * 預借現金金額
     */
    private Integer txAmount;

    /**
     * 預借現金手續費
     */
    private Integer txFee;

    /**
     * 匯款手續費
     */
    private Integer remitFee;

    /**
     * 交易結果通知EMAIL
     */
    private String email;

    /**
     * 安控類型
     */
    private String securityType;

    /**
     * 帳務日期
     */
    private Date accountDate;

    /**
     * 交易日期
     */
    private Date txDate;

    /**
     * 客戶IP
     */
    private String clientIp;

    /**
     * 交易來源
     */
    private String txSource;

    /**
     * 上送主機交易時間
     */
    private Date hostTxTime;

    /**
     * 交易狀態
     */
    private String txStatus;

    /**
     * 交易錯誤代碼
     */
    private String txErrorCode;

    /**
     * 交易錯誤訊息
     */
    private String txErrorDesc;

    /**
     * 錯誤系統代碼
     */
    private String txErrorSystemId;

    /** 交易存取記錄追蹤編號 */
    private String traceId;

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
     * @return the cardOwnerName
     */
    public String getCardOwnerName() {
        return cardOwnerName;
    }

    /**
     * @param cardOwnerName
     *            the cardOwnerName to set
     */
    public void setCardOwnerName(String cardOwnerName) {
        this.cardOwnerName = cardOwnerName;
    }

    /**
     * @return the txType
     */
    public String getTxType() {
        return txType;
    }

    /**
     * @param txType
     *            the txType to set
     */
    public void setTxType(String txType) {
        this.txType = txType;
    }

    /**
     * @return the payeeAccount
     */
    public String getPayeeAccount() {
        return payeeAccount;
    }

    /**
     * @param payeeAccount
     *            the payeeAccount to set
     */
    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    /**
     * @return the payeeBank
     */
    public String getPayeeBank() {
        return payeeBank;
    }

    /**
     * @param payeeBank
     *            the payeeBank to set
     */
    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }

    /**
     * @return the txAmount
     */
    public Integer getTxAmount() {
        return txAmount;
    }

    /**
     * @param txAmount
     *            the txAmount to set
     */
    public void setTxAmount(Integer txAmount) {
        this.txAmount = txAmount;
    }

    /**
     * @return the txFee
     */
    public Integer getTxFee() {
        return txFee;
    }

    /**
     * @param txFee
     *            the txFee to set
     */
    public void setTxFee(Integer txFee) {
        this.txFee = txFee;
    }

    /**
     * @return the remitFee
     */
    public Integer getRemitFee() {
        return remitFee;
    }

    /**
     * @param remitFee
     *            the remitFee to set
     */
    public void setRemitFee(Integer remitFee) {
        this.remitFee = remitFee;
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
     * @return the securityType
     */
    public String getSecurityType() {
        return securityType;
    }

    /**
     * @param securityType
     *            the securityType to set
     */
    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    /**
     * @return the accountDate
     */
    public Date getAccountDate() {
        return accountDate;
    }

    /**
     * @param accountDate
     *            the accountDate to set
     */
    public void setAccountDate(Date accountDate) {
        this.accountDate = accountDate;
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
     * @return the traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            the traceId to set
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
