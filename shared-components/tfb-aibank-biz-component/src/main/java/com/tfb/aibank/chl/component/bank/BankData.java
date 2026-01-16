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
package com.tfb.aibank.chl.component.bank;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)BankData.java
 * 
 * <p>Description:銀行檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "銀行檔")
public class BankData implements Serializable {

    private static final long serialVersionUID = -3216571322992282151L;

    /**
     * 資料鍵值
     */
    @Schema(description = "資料鍵值")
    private Long bankKey;

    /**
     * 群組
     */
    @Schema(description = "群組")
    private String groupIndex;

    /**
     * 銀行代碼
     */
    @Schema(description = "銀行代碼")
    private String bankId;

    /**
     * 語系
     */
    @Schema(description = "語系")
    private String locale;

    /**
     * 銀行名稱
     */
    @Schema(description = "銀行名稱")
    private String bankName;

    public Long getBankKey() {
        return bankKey;
    }

    public void setBankKey(Long bankKey) {
        this.bankKey = bankKey;
    }

    public String getGroupIndex() {
        return groupIndex;
    }

    public void setGroupIndex(String groupIndex) {
        this.groupIndex = groupIndex;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
