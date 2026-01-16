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
 * @(#)ETransBankData.java
 * 
 * <p>Description:全國繳銀行主檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "全國繳銀行主檔")
public class ETransBankData implements Serializable {

    private static final long serialVersionUID = 3712918367534284267L;

    /**
     * 銀行代碼
     */
    @Schema(description = "銀行代碼")
    private String bankId;

    /**
     * 銀行名稱
     */
    @Schema(description = "銀行名稱")
    private String bankName;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
