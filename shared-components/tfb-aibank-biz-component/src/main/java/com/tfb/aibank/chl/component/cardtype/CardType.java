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
package com.tfb.aibank.chl.component.cardtype;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CardType.java
 * 
 * <p>Description:信用卡卡別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/26, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "信用卡卡別")
public class CardType implements Serializable {

    private static final long serialVersionUID = 589013345157227049L;

    /** 卡別 */
    @Schema(description = "卡別")
    private String cardType;

    /** 語系 */
    @Schema(description = "語系")
    private String locale;

    /** CARD_CODE */
    @Schema(description = "CARD_CODE")
    private String cardCode;

    /** 卡別名稱 */
    @Schema(description = "卡別名稱")
    private String cardTypeName;

    /** 卡種 */
    @Schema(description = "卡種")
    private String cardLevel;

    /** 卡種說明 */
    @Schema(description = "卡種說明")
    private String cardLevelDesc;

    /** 紅利代碼 */
    @Schema(description = "紅利代碼")
    private String bonusCode;

    /** 紅利說明 */
    @Schema(description = "紅利說明")
    private String bonusDesc;

    /** 可否線上申請 Y/N(Y：表可以線上申請) */
    @Schema(description = "可否線上申請 Y/N(Y：表可以線上申請)")
    private String netApplyFlag;

    /** 悠遊卡註記 Y/N(Y：表悠遊卡) */
    @Schema(description = " 悠遊卡註記 Y/N(Y：表悠遊卡)")
    private String mrtFlag;

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getCardLevelDesc() {
        return cardLevelDesc;
    }

    public void setCardLevelDesc(String cardLevelDesc) {
        this.cardLevelDesc = cardLevelDesc;
    }

    public String getBonusCode() {
        return bonusCode;
    }

    public void setBonusCode(String bonusCode) {
        this.bonusCode = bonusCode;
    }

    public String getBonusDesc() {
        return bonusDesc;
    }

    public void setBonusDesc(String bonusDesc) {
        this.bonusDesc = bonusDesc;
    }

    public String getNetApplyFlag() {
        return netApplyFlag;
    }

    public void setNetApplyFlag(String netApplyFlag) {
        this.netApplyFlag = netApplyFlag;
    }

    public String getMrtFlag() {
        return mrtFlag;
    }

    public void setMrtFlag(String mrtFlag) {
        this.mrtFlag = mrtFlag;
    }
}
