/**
 * 
 */
package com.tfb.aibank.chl.creditcard.tx001.model;

//@formatter:off
/**
* @(#)NCCTX001BankInfo.java
*
* <p>Description:預借現金 信用卡清單</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCTX001CardInfo {

    /**
     * 卡片Index
     */
    private String cardKey;

    /** 卡名 */
    private String displayCardName;

    /** 卡名 */
    private String cardName;

    /** 可用餘額 */
    private int availableBalance;

    /** 可用餘額 */
    private String displayAvailableBalance;

    /** 卡號 */
    private String cardNo;

    /** 是否可用 */
    private boolean isAvalable;

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
     * @return the displayCardName
     */
    public String getDisplayCardName() {
        return displayCardName;
    }

    /**
     * @param displayCardName
     *            the displayCardName to set
     */
    public void setDisplayCardName(String displayCardName) {
        this.displayCardName = displayCardName;
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
     * @return the availableBalance
     */
    public int getAvailableBalance() {
        return availableBalance;
    }

    /**
     * @param availableBalance
     *            the availableBalance to set
     */
    public void setAvailableBalance(int availableBalance) {
        this.availableBalance = availableBalance;
    }

    /**
     * @return the displayAvailableBalance
     */
    public String getDisplayAvailableBalance() {
        return displayAvailableBalance;
    }

    /**
     * @param displayAvailableBalance
     *            the displayAvailableBalance to set
     */
    public void setDisplayAvailableBalance(String displayAvailableBalance) {
        this.displayAvailableBalance = displayAvailableBalance;
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
     * @return the isAvalable
     */
    public boolean isAvalable() {
        return isAvalable;
    }

    /**
     * @param isAvalable
     *            the isAvalable to set
     */
    public void setAvalable(boolean isAvalable) {
        this.isAvalable = isAvalable;
    }

}
