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
package com.tfb.aibank.chl.component.homepagecard;

// @formatter:off
/**
 * @(#)CardEvent.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/09, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CardEvent {

    public CardEvent(int taType, String taGroup, String cardTemplate, Integer cardKey) {
        this.taType = taType;
        this.taGroup = taGroup;
        this.cardTemplate = cardTemplate;
        this.cardKey = Integer.toString(cardKey);
    }

    /**
     * 客群機制
     */
    private int taType;

    /**
     * 客群
     */
    private String taGroup;

    /**
     * HOMEPAGE：首頁牌卡、 HOMEPAGE_SETTING：首頁牌卡設定 AD_CARD1：廣告小卡1 AD_CARD2：廣告小卡2 TASK_CARD：交易內牌卡
     */
    private String cardTemplate;

    private String cardKey;

    /**
     * @return the taType
     */
    public int getTaType() {
        return taType;
    }

    /**
     * @param taType
     *            the taType to set
     */
    public void setTaType(int taType) {
        this.taType = taType;
    }

    /**
     * @return the taGroup
     */
    public String getTaGroup() {
        return taGroup;
    }

    /**
     * @param taGroup
     *            the taGroup to set
     */
    public void setTaGroup(String taGroup) {
        this.taGroup = taGroup;
    }

    /**
     * @return the cardTemplate
     */
    public String getCardTemplate() {
        return cardTemplate;
    }

    /**
     * @param cardTemplate
     *            the cardTemplate to set
     */
    public void setCardTemplate(String cardTemplate) {
        this.cardTemplate = cardTemplate;
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
