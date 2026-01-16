/**
 * 
 */
package com.tfb.aibank.chl.creditcard.ag001.model;

//@formatter:off
/**
* @(#)NCCAG001CardInfo.java
*
* <p>Description:預借現金密碼設定 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCAG001CardInfo {

    /**
     * 卡片Index
     */
    private String cardKey;

    /**
     * 卡名
     */
    private String cardName;

    /**
     * 卡名
     */
    private String cardNo;

    /**
     * 正卡
     */
    private int isPrimary;

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
     * @return the isPrimary
     */
    public int getIsPrimary() {
        return isPrimary;
    }

    /**
     * @param isPrimary
     *            the isPrimary to set
     */
    public void setIsPrimary(int isPrimary) {
        this.isPrimary = isPrimary;
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

}
