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
 * @(#)EB5556911Repeat.java
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
public class EB5556911ResRep implements Serializable {

    private static final long serialVersionUID = -6721299042722724287L;

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

    // ================= 以下為資料來源非電文之擴充欄位 =====================
    /** 轉入帳號暱稱 */
    @Schema(description = "轉入帳號暱稱")
    private String accountAlias;

    /** 帳號順序 */
    @Schema(description = "帳號順序")
    private Integer accountSort;

    /** 頭像類型 */
    @Schema(description = "頭像類型")
    private String avatarType;
    // ================= 以上為資料來源非電文之擴充欄位 =====================

    /** type8排除邏輯 */
    @Schema(description = "type8排除邏輯")
    private boolean filter;

    /** 轉帳通知email */
    @Schema(description = "轉帳通知email")
    private String notifyEmail;

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
     * @return the accountAlias
     */
    public String getAccountAlias() {
        return accountAlias;
    }

    /**
     * @param accountAlias
     *            the accountAlias to set
     */
    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    /**
     * @return the accountSort
     */
    public Integer getAccountSort() {
        return accountSort;
    }

    /**
     * @param accountSort
     *            the accountSort to set
     */
    public void setAccountSort(Integer accountSort) {
        this.accountSort = accountSort;
    }

    public String getProdInd() {
        return prodInd;
    }

    public void setProdInd(String prodInd) {
        this.prodInd = prodInd;
    }

    public String getBankNoOld() {
        return bankNoOld;
    }

    public void setBankNoOld(String bankNoOld) {
        this.bankNoOld = bankNoOld;
    }

    public String getAcnoOld() {
        return acnoOld;
    }

    public void setAcnoOld(String acnoOld) {
        this.acnoOld = acnoOld;
    }

    public String getDisplayBranchId() {
        return displayBranchId;
    }

    public void setDisplayBranchId(String displayBranchId) {
        this.displayBranchId = displayBranchId;
    }

    public String getAcnoType() {
        return acnoType;
    }

    public void setAcnoType(String acnoType) {
        this.acnoType = acnoType;
    }

    /**
     * @return the avatarType
     */
    public String getAvatarType() {
        return avatarType;
    }

    /**
     * @param avatarType
     *            the avatarType to set
     */
    public void setAvatarType(String avatarType) {
        this.avatarType = avatarType;
    }

    /**
     * @return {@link #filter}
     */
    public boolean isFilter() {
        return filter;
    }

    /**
     * @param filter
     *            {@link #filter}
     */
    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    /**
     * @return {@link #notifyEmail}
     */
    public String getNotifyEmail() {
        return notifyEmail;
    }

    /**
     * @param notifyEmail
     *            {@link #notifyEmail}
     */
    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

}
