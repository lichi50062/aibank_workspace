/**
 * 
 */
package com.tfb.aibank.chl.creditcard.tx001.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001CardInfo;
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

/**
 * @author john
 *
 */
public class NCCTX001CacheVo {

    public NCCTX001CacheVo(List<NCCTX001CardInfo> cardInfo, List<CreditCard> cards, CreditCard maxCreditCard, double maxBalanceAvailable) {
        this.cardInfo = cardInfo;
        this.cards = cards;
        this.maxCreditCard = maxCreditCard;
        this.maxBalanceAvailable = maxBalanceAvailable;
    }

    /** 顯示卡片資料 */
    private List<NCCTX001CardInfo> cardInfo;

    /** 自行帳戶清單 */
    private List<TransOutAccount> accountList;

    /** 卡片清單 */
    private List<CreditCard> cards;

    /** 最高額卡片 */
    private CreditCard maxCreditCard;

    /** 預借現金金額 */
    private Integer amount;

    /** 總還款金額 */
    private String totalAmount;

    /** 卡片 */
    private String cardKey;

    /** 卡片 */
    private String cardName;

    /** 入帳帳號 */
    private String accountName;

    /** 跨行 */
    private boolean isOtherBank;

    /** 交易卡片 */
    private CreditCard creditCard;

    /** 預借現金手續費 電文 */
    private BigDecimal realFee;

    /** 預借現金手續費 計算 */
    private BigDecimal dispFee;

    // 入帳銀行+分行 7位
    // 0122009 本行
    // or
    // {銀行代碼}+[分行代碼]

    private String bankBranchNo;

    /** 通知的E-mail from CEW311R */
    private String email;

    /**************** 交易資料 **************/
    /* 信用卡 cvv */
    private String cvv;

    /* 入帳帳戶型態 */
    private boolean accountType;

    /** 本行帳戶 Key */
    private String accountKey;

    /** 他行 Bank ID */
    private String bankId;

    /** 他行 分行 ID */
    private String branchId;

    /** 他行 帳戶 */
    private String accountNo;

    /** 最高可藉金額 */
    private double maxBalanceAvailable;

    /** 生日 */
    private Date birthday;

    /**
     * @return the cardInfo
     */
    public List<NCCTX001CardInfo> getCardInfo() {
        return cardInfo;
    }

    /**
     * @param cardInfo
     *            the cardInfo to set
     */
    public void setCardInfo(List<NCCTX001CardInfo> cardInfo) {
        this.cardInfo = cardInfo;
    }

    /**
     * @return the cards
     */
    public List<CreditCard> getCards() {
        return cards;
    }

    /**
     * @param cards
     *            the cards to set
     */
    public void setCards(List<CreditCard> cards) {
        this.cards = cards;
    }

    /**
     * @return the maxCreditCard
     */
    public CreditCard getMaxCreditCard() {
        return maxCreditCard;
    }

    /**
     * @param maxCreditCard
     *            the maxCreditCard to set
     */
    public void setMaxCreditCard(CreditCard maxCreditCard) {
        this.maxCreditCard = maxCreditCard;
    }

    /**
     * @return the amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * @return the totalAmount
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount
     *            the totalAmount to set
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
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
     * @return the bankBranchNo
     */
    public String getBankBranchNo() {
        return bankBranchNo;
    }

    /**
     * @param bankBranchNo
     *            the bankBranchNo to set
     */
    public void setBankBranchNo(String bankBranchNo) {
        this.bankBranchNo = bankBranchNo;
    }

    /**
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     *            the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return the accountList
     */
    public List<TransOutAccount> getAccountList() {
        return accountList;
    }

    /**
     * @param accountList
     *            the accountList to set
     */
    public void setAccountList(List<TransOutAccount> accountList) {
        this.accountList = accountList;
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
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName
     *            the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the isOtherBank
     */
    public boolean isOtherBank() {
        return isOtherBank;
    }

    /**
     * @param isOtherBank
     *            the isOtherBank to set
     */
    public void setOtherBank(boolean isOtherBank) {
        this.isOtherBank = isOtherBank;
    }

    /**
     * @return the card
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * @param card
     *            the card to set
     */
    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    /**
     * @return the cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * @param cvv
     *            the cvv to set
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the accountType
     */
    public boolean isAccountType() {
        return accountType;
    }

    /**
     * @param accountType
     *            the accountType to set
     */
    public void setAccountType(boolean accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the accountKey
     */
    public String getAccountKey() {
        return accountKey;
    }

    /**
     * @param accountKey
     *            the accountKey to set
     */
    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    /**
     * @return the bankId
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * @param bankId
     *            the bankId to set
     */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId
     *            the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the maxBalanceAvailable
     */
    public double getMaxBalanceAvailable() {
        return maxBalanceAvailable;
    }

    /**
     * @param maxBalanceAvailable
     *            the maxBalanceAvailable to set
     */
    public void setMaxBalanceAvailable(double maxBalanceAvailable) {
        this.maxBalanceAvailable = maxBalanceAvailable;
    }

    /**
     * @return the realFee
     */
    public BigDecimal getRealFee() {
        return realFee;
    }

    /**
     * @param realFee
     *            the realFee to set
     */
    public void setRealFee(BigDecimal realFee) {
        this.realFee = realFee;
    }

    /**
     * @return the dispFee
     */
    public BigDecimal getDispFee() {
        return dispFee;
    }

    /**
     * @param dispFee
     *            the dispFee to set
     */
    public void setDispFee(BigDecimal dispFee) {
        this.dispFee = dispFee;
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

}
