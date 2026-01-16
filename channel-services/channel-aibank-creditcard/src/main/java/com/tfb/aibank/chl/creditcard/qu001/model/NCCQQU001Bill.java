package com.tfb.aibank.chl.creditcard.qu001.model;

import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
* @(#)NCCQQU001BillingDetail.java
* 
* <p>Description:信用卡總覽 帳單元件類別</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQQU001Bill {
    /** 識別資訊(唯一值) */
    private String cardKey;
    /** 卡號 */
    private String cardNo;
    /** 單筆分期 */
    private boolean singleInstallment;
    /** 已設定單筆分期 */
    private boolean areadySingleInstallment;
    /** 交易日期 X(7) */
    private Date date;
    /** 款項說明 X(40) */
    private String desc;
    /** 金額 X(9) */
    private BigDecimal amount;
    /** 金額正負號 X(1) */
    private String sign;
    /** 入帳日期 */
    private Date payDay;
    /** 卡片名稱 */
    private String cardName;
    /** 虛擬卡好後四碼 */
    private String token;
    /** 支付方式 */
    private String payName;
    /** 外幣幣別 */
    private String currency;
    /** 外幣幣別名稱 */
    private String currencyName;
    /** 外幣金額 */
    private BigDecimal foreignAmount;
    /** 外幣正負號 */
    private String foreignSign;
    /** 備註 */
    private String memo;
    /** 交易序號 X(7) */
    private String seq;
    /** NCC清算日 yyyy/MM/dd */
    private String closeDate;
    /** 其他費用 */
    private boolean isOtherFee;
    /** 產生組別 */
    private String group;

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     *            the desc to set
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return the amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * @return the sign
     */
    public String getSign() {
        return sign;
    }

    /**
     * @param sign
     *            the sign to set
     */
    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * @return the payDay
     */
    public Date getPayDay() {
        return payDay;
    }

    /**
     * @param payDay
     *            the payDay to set
     */
    public void setPayDay(Date payDay) {
        this.payDay = payDay;
    }

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the payName
     */
    public String getPayName() {
        return payName;
    }

    /**
     * @param payName
     *            the payName to set
     */
    public void setPayName(String payName) {
        this.payName = payName;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency
     *            the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * @return the foreignAmount
     */
    public BigDecimal getForeignAmount() {
        return foreignAmount;
    }

    /**
     * @param foreignAmount
     *            the foreignAmount to set
     */
    public void setForeignAmount(BigDecimal foreignAmount) {
        this.foreignAmount = foreignAmount;
    }

    /**
     * @return the foreignSign
     */
    public String getForeignSign() {
        return foreignSign;
    }

    /**
     * @param foreignSign
     *            the foreignSign to set
     */
    public void setForeignSign(String foreignSign) {
        this.foreignSign = foreignSign;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
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
     * @return the singleInstallment
     */
    public boolean isSingleInstallment() {
        return singleInstallment;
    }

    /**
     * @param singleInstallment
     *            the singleInstallment to set
     */
    public void setSingleInstallment(boolean singleInstallment) {
        this.singleInstallment = singleInstallment;
    }

    /**
     * @return the currencyName
     */
    public String getCurrencyName() {
        return currencyName;
    }

    /**
     * @param currencyName
     *            the currencyName to set
     */
    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     * @return the cardKey
     */
    public String getCardKey() {
        return cardKey;
    }

    /**
     * @param cardKey
     *            the cardKey to set
     */
    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    /**
     * @return the seq
     */
    public String getSeq() {
        return seq;
    }

    /**
     * @param seq
     *            the seq to set
     */
    public void setSeq(String seq) {
        this.seq = seq;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    /**
     * @return the isOtherFee
     */
    public boolean isOtherFee() {
        return isOtherFee;
    }

    /**
     * @param isOtherFee
     *            the isOtherFee to set
     */
    public void setOtherFee(boolean isOtherFee) {
        this.isOtherFee = isOtherFee;
    }

    public boolean isAreadySingleInstallment() {
        return areadySingleInstallment;
    }

    public void setAreadySingleInstallment(boolean areadySingleInstallment) {
        this.areadySingleInstallment = areadySingleInstallment;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
