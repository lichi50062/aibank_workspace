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
package com.tfb.aibank.component.accountinfo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)AccountInfo.java
 * 
 * <p>Description:帳號資訊檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/15, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "帳號資訊檔")
public class AccountInfo implements Serializable {

    private static final long serialVersionUID = -6599578150815895830L;

    @Schema(description = "產品大類")
    private String prodCode;

    @Schema(description = "產品子類")
    private String prodSubCode;

    @Schema(description = "帳號類別")
    private String accountType;

    @Schema(description = "帳號子類別")
    private String accountSubType;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "子描述")
    private String descriptionSub;

    @Schema(description = "帳號名稱")
    private String displayText;

    @Schema(description = "語系")
    private String locale;

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdSubCode() {
        return prodSubCode;
    }

    public void setProdSubCode(String prodSubCode) {
        this.prodSubCode = prodSubCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getAccountSubType() {
        return accountSubType;
    }

    public void setAccountSubType(String accountSubType) {
        this.accountSubType = accountSubType;
    }

    public String getDescriptionSub() {
        return descriptionSub;
    }

    public void setDescriptionSub(String descriptionSub) {
        this.descriptionSub = descriptionSub;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

}
