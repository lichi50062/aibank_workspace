/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)FinancialMgmMemberLevel.java
 * 
 * <p>Description:財管會員等級</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

@Schema(description = "財管會員等級")
public class FinancialMgmMemberLevel {

    /**
     * 證件號碼
     */
    @Schema(description = "證件號碼")
    private String custNo;

    /**
     * 個人等級註記
     */
    @Schema(description = "個人等級註記")
    private String personalFlag;
    /**
     * 家庭會員註記
     */
    @Schema(description = "家庭會員註記")
    private String familyFlag;

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getFamilyFlag() {
        return familyFlag;
    }

    public void setFamilyFlag(String familyFlag) {
        this.familyFlag = familyFlag;
    }

    public String getPersonalFlag() {
        return personalFlag;
    }

    public void setPersonalFlag(String personalFlag) {
        this.personalFlag = personalFlag;
    }
}
