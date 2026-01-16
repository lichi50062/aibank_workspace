package com.tfb.aibank.chl.creditcard.tx001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCTX001030Rs.java
*
* <p>Description:預借現金 確認頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001030Rs implements RsData {

    /** 預借現金金額 */
    private String amount;

    /** 現金手續費 */
    private String fee;

    /** 總還款金額 */
    private String totalAmount;

    /** 卡片 */
    private String cardName;

    /** 入帳帳號 */
    private String accountName;

    /** 跨行 */
    private boolean isOtherBank;

    /** 跨行手續費 */
    private String interbankFee;

    /**
     * @return the amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return the fee
     */
    public String getFee() {
        return fee;
    }

    /**
     * @param fee
     *            the fee to set
     */
    public void setFee(String fee) {
        this.fee = fee;
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
     * @return the interbankFee
     */
    public String getInterbankFee() {
        return interbankFee;
    }

    /**
     * @param interbankFee
     *            the interbankFee to set
     */
    public void setInterbankFee(String interbankFee) {
        this.interbankFee = interbankFee;
    }

}
