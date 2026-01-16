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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5556911ResRep.java
 * 
 * <p>Description:約定轉入帳號 下行電文 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "約定轉入帳號 下行電文 Repeat")
public class TrustAcctInResRep implements Serializable {

    private static final long serialVersionUID = -629849725484368504L;

    public TrustAcctInResRep() {
        // default constructor
    }


    /** 銀行別 */
    @Schema(description = "銀行別")
    private String bankNo;

    /** 帳號 */
    @Schema(description = "帳號")
    private String acno;

    /** 轉入帳號戶名 */
    @Schema(description = "轉入帳號戶名")
    private String custName;

    /** 是否為實體帳號 */
    @Schema(description = "是否為實體帳號")
    private String acnoType;

    /** 是否為本人帳號 */
    @Schema(description = "是否為本人帳號")
    private String acnoFlg;

    /** 帳號類別 */
    @Schema(description = "帳號類別")
    private String type;

    /** 數位類型 ''：非數位帳戶 1：第一類 2：第二類 3：第三類 */
    @Schema(description = "數位類型 ''：非數位帳戶 1：第一類 2：第二類 3：第三類 ")
    private String digitType;

    /** 數位存款外幣功能 Y/N */
    @Schema(description = "數位存款外幣功能 Y/N")
    private String fsFlg;

    /** 產品別 */
    @Schema(description = "產品別")
    private String prodCode;

    /** 分行別 */
    @Schema(description = "分行別")
    private String branchNo;

    /** 幣別 */
    @Schema(description = "幣別")
    private String curr;

    /** 產品標識 */
    @Schema(description = "產品標識")
    private String prodInd;

    /** 原銀行別 */
    @Schema(description = "原銀行別")
    private String bankNoOld;

    /** 原帳號 */
    @Schema(description = "原帳號")
    private String acnoOld;

    /** 分行別(自定義 非電文欄位) */
    @Schema(description = "分行別(自定義 非電文欄位)")
    private String displayBranchId;

    /** type8排除邏輯 */
    @Schema(description = "type8排除邏輯")
    private boolean filter;

    /** 產品子類 */
    @Schema(description = "產品子類")
    private String accountSubType;

    /**
     * @return the bankNo
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * @param bankNo
     *            the bankNo to set
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    /**
     * @return the acno
     */
    public String getAcno() {
        return acno;
    }

    /**
     * @param acno
     *            the acno to set
     */
    public void setAcno(String acno) {
        this.acno = acno;
    }

    /**
     * @return the custName
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName
     *            the custName to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return the acnoType
     */
    public String getAcnoType() {
        return acnoType;
    }

    /**
     * @param acnoType
     *            the acnoType to set
     */
    public void setAcnoType(String acnoType) {
        this.acnoType = acnoType;
    }

    /**
     * @return the acnoFlg
     */
    public String getAcnoFlg() {
        return acnoFlg;
    }

    /**
     * @param acnoFlg
     *            the acnoFlg to set
     */
    public void setAcnoFlg(String acnoFlg) {
        this.acnoFlg = acnoFlg;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the digitType
     */
    public String getDigitType() {
        return digitType;
    }

    /**
     * @param digitType
     *            the digitType to set
     */
    public void setDigitType(String digitType) {
        this.digitType = digitType;
    }

    /**
     * @return the fsFlg
     */
    public String getFsFlg() {
        return fsFlg;
    }

    /**
     * @param fsFlg
     *            the fsFlg to set
     */
    public void setFsFlg(String fsFlg) {
        this.fsFlg = fsFlg;
    }

    /**
     * @return the prodCode
     */
    public String getProdCode() {
        return prodCode;
    }

    /**
     * @param prodCode
     *            the prodCode to set
     */
    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    /**
     * @return the branchNo
     */
    public String getBranchNo() {
        return branchNo;
    }

    /**
     * @param branchNo
     *            the branchNo to set
     */
    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    /**
     * @return the curr
     */
    public String getCurr() {
        return curr;
    }

    /**
     * @param curr
     *            the curr to set
     */
    public void setCurr(String curr) {
        this.curr = curr;
    }

    /**
     * @return the prodInd
     */
    public String getProdInd() {
        return prodInd;
    }

    /**
     * @param prodInd
     *            the prodInd to set
     */
    public void setProdInd(String prodInd) {
        this.prodInd = prodInd;
    }

    /**
     * @return the bankNoOld
     */
    public String getBankNoOld() {
        return bankNoOld;
    }

    /**
     * @param bankNoOld
     *            the bankNoOld to set
     */
    public void setBankNoOld(String bankNoOld) {
        this.bankNoOld = bankNoOld;
    }

    /**
     * @return the acnoOld
     */
    public String getAcnoOld() {
        return acnoOld;
    }

    /**
     * @param acnoOld
     *            the acnoOld to set
     */
    public void setAcnoOld(String acnoOld) {
        this.acnoOld = acnoOld;
    }

    /**
     * @return the displayBranchId
     */
    public String getDisplayBranchId() {
        return displayBranchId;
    }

    /**
     * @param displayBranchId
     *            the displayBranchId to set
     */
    public void setDisplayBranchId(String displayBranchId) {
        this.displayBranchId = displayBranchId;
    }

    /**
     * @return the filter
     */
    public boolean isFilter() {
        return filter;
    }

    /**
     * @param filter
     *            the filter to set
     */
    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    /**
     * @return the accountSubType
     */
    public String getAccountSubType() {
        return accountSubType;
    }

    /**
     * @param accountSubType
     *            the accountSubType to set
     */
    public void setAccountSubType(String accountSubType) {
        this.accountSubType = accountSubType;
    }

}
