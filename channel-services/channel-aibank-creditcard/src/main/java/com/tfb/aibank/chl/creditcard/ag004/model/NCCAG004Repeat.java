/**
 * 
 */
package com.tfb.aibank.chl.creditcard.ag004.model;

import java.util.List;

//@formatter:off
/**
* @(#)NCCAG004020Rs.java
* 
* <p>Description:刷卡通知設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCAG004Repeat {

    /** 鍵值 */
    private String cardKey;

    /** 卡號 */
    private String cardNo;

    /** 卡片名稱 */
    private String cardName;

    /** 卡面 */
    private String cardFace;

    /** 開關值 */
    private boolean radio;

    /** 附卡 */
    private List<String> suppleCardNo;

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
     * @return the cardFace
     */
    public String getCardFace() {
        return cardFace;
    }

    /**
     * @param cardFace
     *            the cardFace to set
     */
    public void setCardFace(String cardFace) {
        this.cardFace = cardFace;
    }

    /**
     * @return the radio
     */
    public boolean isRadio() {
        return radio;
    }

    /**
     * @param radio
     *            the radio to set
     */
    public void setRadio(boolean radio) {
        this.radio = radio;
    }

    /**
     * @return the suppleCardNo
     */
    public List<String> getSuppleCardNo() {
        return suppleCardNo;
    }

    /**
     * @param suppleCardNo
     *            the suppleCardNo to set
     */
    public void setSuppleCardNo(List<String> suppleCardNo) {
        this.suppleCardNo = suppleCardNo;
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

}
