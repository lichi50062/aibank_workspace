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
package com.tfb.aibank.chl.model.account;

import java.io.Serializable;

import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.util.AccountUtils;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)AgreedInAccount.java
 * 
 * <p>Description:約定轉入帳號</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "約定轉入帳號")
public class AgreedInAccount implements ITransInAccount, Serializable {

    private static final long serialVersionUID = 3079201914750444705L;

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

    // ================== 以下為擴充欄位 ==============================
    /** 銀行名稱 */
    @Schema(description = "銀行名稱")
    private String bankName;

    /** 分行名稱 */
    @Schema(description = "分行名稱")
    private String branchName;

    /** 幣別名稱 */
    @Schema(description = "幣別名稱")
    private String curName;

    /** 帳號順序 */
    @Schema(description = "帳號順序")
    private Integer accountSort;

    /** 頭像類型 */
    @Schema(description = "頭像類型")
    private String avatarType;

    /** 暱稱 */
    @Schema(description = "暱稱")
    private String accountAlias;

    /** 指定類型 1:常用，2:約定 */
    @Schema(description = "指定類型 1:常用，2:約定")
    private String designateType;

    /** 分行別(自定義 非電文欄位) */
    @Schema(description = "分行別(自定義 非電文欄位)")
    private String displayBranchId;

    /** 轉帳通知email */
    @Schema(description = "轉帳通知email")
    private String notifyEmail;
    // ================== 以上為擴充欄位 ==============================

    @Override
    public String getAccountId() {
        return this.acno;
    }

    @Override
    public String getAccountName() {
        return this.custName;
    }

    @Override
    public String getDisplayAccountId() {
        return AccountUtils.getDisplayAccountId(getBankNo(), getAccountId());
    }

    @Override
    public String getAccountLabel() {
        return getAccountName() + " " + getDisplayAccountId();
    }

    @Override
    public String getAccountDropdown() {
        return getDisplayAccountId();
    }

    @Override
    public String getCurCode() {
        return this.curr;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAcnoFlg() {
        return acnoFlg;
    }

    public void setAcnoFlg(String acnoFlg) {
        this.acnoFlg = acnoFlg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDigitType() {
        return digitType;
    }

    public void setDigitType(String digitType) {
        this.digitType = digitType;
    }

    public String getFsFlg() {
        return fsFlg;
    }

    public void setFsFlg(String fsFlg) {
        this.fsFlg = fsFlg;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    @Override
    public String getBranchNo() {
        return branchNo;
    }

    public void setBranchNo(String branchNo) {
        this.branchNo = branchNo;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    @Override
    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getAccountSort() {
        return accountSort;
    }

    public void setAccountSort(Integer accountSort) {
        this.accountSort = accountSort;
    }

    /**
     * 顯示帳號名稱
     * 
     * @return
     */
    @Override
    public String getDisplayAccountName() {
        if (StringUtils.isNotBlank(this.accountAlias)) {
            return this.accountAlias;
        }
        if (StringUtils.equals(AIBankConstants.TFB_BANK_CODE, this.bankNo)) {
            return DataMaskUtil.maskCustName(this.custName);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 顯示帳號名稱(戶名隱碼)
     * 
     * @return
     */
    public String getDisplayAccountNameMaskCustName() {
        if (StringUtils.isNotBlank(this.accountAlias)) {
            return this.accountAlias;
        }
        if (StringUtils.equals(AIBankConstants.TFB_BANK_CODE, this.bankNo)) {
            return DataMaskUtil.maskCustName(this.custName);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 顯示暱稱
     */
    @Override
    public String getDisplayAcctNickname() {
        if (StringUtils.isNotBlank(this.accountAlias)) {
            return this.accountAlias;
        }
        if (StringUtils.equals(AIBankConstants.TFB_BANK_CODE, this.bankNo)) {
            return this.custName;
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String getAccountAlias() {
        return accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    @Override
    public String getAvatarType() {
        if (StringUtils.isBlank(this.avatarType)) {
            return AIBankConstants.AVATAR_CHARACTER_DEFAULT;
        }
        return avatarType;
    }

    public void setAvatarType(String avatarType) {
        this.avatarType = avatarType;
    }

    @Override
    public String getDesignateType() {
        return designateType;
    }

    public void setDesignateType(String designateType) {
        this.designateType = designateType;
    }

    public String getAcnoType() {
        return acnoType;
    }

    public void setAcnoType(String acnoType) {
        this.acnoType = acnoType;
    }

    public String getDisplayBranchId() {
        return displayBranchId;
    }

    public void setDisplayBranchId(String displayBranchId) {
        this.displayBranchId = displayBranchId;
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
