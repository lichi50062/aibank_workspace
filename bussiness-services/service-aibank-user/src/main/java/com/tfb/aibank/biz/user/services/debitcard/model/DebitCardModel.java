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
package com.tfb.aibank.biz.user.services.debitcard.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)DebitCardPicture.java
 * 
 * <p>Description:[簽帳卡卡面]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/04, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "簽帳卡卡面")
public class DebitCardModel {

    /** 卡別 */
    @Schema(description = "卡別")
    private Integer cardType;

    /** 圖片來源 */
    @Schema(description = "圖片來源")
    private String cardPicture;

    /** 語系 */
    @Schema(description = "語系")
    private String locale;

    /** 卡片中文名稱 */
    @Schema(description = "卡片中文名稱")
    private String cardName;

    /**
     * @return the cardType
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     *            the cardType to set
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * @return the cardPicture
     */
    public String getCardPicture() {
        return cardPicture;
    }

    /**
     * @param cardPicture
     *            the cardPicture to set
     */
    public void setCardPicture(String cardPicture) {
        this.cardPicture = cardPicture;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
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

}
