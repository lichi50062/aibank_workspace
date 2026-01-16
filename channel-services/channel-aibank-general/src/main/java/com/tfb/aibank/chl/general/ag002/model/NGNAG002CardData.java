/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.ag002.model;

// @formatter:off
/**
 * @(#)NGNAG002CardData.java
 * 
 * <p>Description:牌卡設定資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNAG002CardData {

    /** 牌卡編號 */
    private int cardId;

    /** 牌卡名稱 */
    private String cardName;

    /** 牌卡底圖路徑 */
    private String cardbg;

    /** 牌卡小圖路徑 */
    private String cardIcon;

    /** 牌卡說明 */
    private String cardDesc;

    private Integer sortSeq = 999;
    /**
     * @return the cardId
     */
    public int getCardId() {
        return cardId;
    }

    /**
     * @param cardId
     *            the cardId to set
     */
    public void setCardId(int cardId) {
        this.cardId = cardId;
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
     * @return the cardbg
     */
    public String getCardbg() {
        return cardbg;
    }

    /**
     * @param cardbg
     *            the cardbg to set
     */
    public void setCardbg(String cardbg) {
        this.cardbg = cardbg;
    }

    /**
     * @return the cardIcon
     */
    public String getCardIcon() {
        return cardIcon;
    }

    /**
     * @param cardIcon
     *            the cardIcon to set
     */
    public void setCardIcon(String cardIcon) {
        this.cardIcon = cardIcon;
    }

    /**
     * @return the cardDesc
     */
    public String getCardDesc() {
        return cardDesc;
    }

    /**
     * @param cardDesc
     *            the cardDesc to set
     */
    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

    public Integer getSortSeq() {
        return sortSeq;
    }

    public void setSortSeq(Integer sortSeq) {
        this.sortSeq = sortSeq;
    }
}
