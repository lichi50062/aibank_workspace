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
package com.tfb.aibank.component.cardpicture;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 卡面資料檔
 * 
 * @author Evan Wang
 */
@Schema(description = "卡面資料")
public class CardPicture implements Serializable {
    private static final long serialVersionUID = -3383941845230637358L;

    /** 信用卡圖片url */
    @Schema(description = "信用卡圖片url")
    private String imageUrl;

    /** 卡別 */
    @Schema(description = "卡別")
    private String cardType;

    /**
     * @return the imageUrl
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl
     *            the imageUrl to set
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return the cardType
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * @param cardType
     *            the cardType to set
     */
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

}
