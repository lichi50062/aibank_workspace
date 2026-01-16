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
package com.tfb.aibank.chl.component.branch;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)BranchCode.java
 * 
 * <p>Description:分行代碼名稱</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "分行代碼名稱")
public class BranchData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "住址")
    private String address;

    @Schema(description = "分行別")
    private String branchId;

    @Schema(description = "分行名稱")
    private String branchName;

    @Schema(description = "傳真號碼")
    private String fax;

    @Schema(description = "外匯指定分行")
    private String fxBranchId;

    @Schema(description = "語系")
    private String locale;

    @Schema(description = "電話")
    private String tel;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getFxBranchId() {
        return fxBranchId;
    }

    public void setFxBranchId(String fxBranchId) {
        this.fxBranchId = fxBranchId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

}
