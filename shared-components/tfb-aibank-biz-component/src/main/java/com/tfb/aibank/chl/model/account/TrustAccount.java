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

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.common.util.AccountUtils;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)TrustAccount.java
 * 
 * <p>Description:信託帳號</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/11, Alex PY Li	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "信託帳號")
public class TrustAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 銀行別 */
    @Schema(description = "銀行別")
    private String bankNo;

    /** 銀行名稱 */
    @Schema(description = "銀行名稱")
    private String bankName;

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
    /** 帳號暱稱 */
    @Schema(description = "帳號暱稱")
    private String acctNickName;

    /** 分行名稱 */
    @Schema(description = "分行名稱")
    private String branchName;

    /** 幣別名稱 */
    @Schema(description = "幣別名稱")
    private String curName;

    /** 識別序列 */
    @Schema(description = "識別序列")
    private int index;

    /**
     * 準備組成帳號清單所需的基礎資訊
     *
     * @param subClazz
     */
    public void prepareAccountInfo(TrustAccount subClazz) {
        subClazz.setBankNo(getBankNo());
        /** 帳號 */
        subClazz.setAcno(getAcno());
        /** 轉入帳號戶名 */
        subClazz.setCustName(getCustName());
        /** 是否為實體帳號 */
        subClazz.setAcnoType(getAcnoType());
        /** 是否為本人帳號 */
        subClazz.setAcnoFlg(getAcnoFlg());
        /** 帳號類別 */
        subClazz.setType(getType());
        /** 數位類型 ''：非數位帳戶 1：第一類 2：第二類 3：第三類 */
        subClazz.setDigitType(getDigitType());
        /** 數位存款外幣功能 Y/N */
        subClazz.setFsFlg(getFsFlg());
        /** 產品別 */
        subClazz.setProdCode(getProdCode());
        /** 分行別 */
        subClazz.setBranchNo(getBranchNo());
        /** 幣別 */
        subClazz.setCurr(getCurr());
        /** 帳號暱稱 */
        subClazz.setAcctNickName(getAcctNickName());
        /** 分行名稱 */
        subClazz.setBranchName(getBranchName());
        /** 幣別名稱 */
        subClazz.setCurName(getCurName());
    }


    public String getAccountLabel1() {
        StringBuilder sb = new StringBuilder(0);
        if( StringUtils.isNotBlank(getDisplayAcctNickname())){
            sb.append(getDisplayAcctNickname());
        }
        sb.append("···").append(StringUtils.right(getAcno(), 5));
        return sb.toString();
    }

    public String getAccountDropdown1() {
        return new StringBuilder(0).append(getDisplayAcctNickname()).append(" ").append(getDisplayAccountId()).toString();
    }

    public String getDisplayAccountId() {
        return AccountUtils.getDisplayAccountId(getBankNo(), getAcno());
    }

    /**
     * 有暱稱時回傳暱稱， 沒有時回帳號名稱
     */
    public String getDisplayAcctNickname() {
        if (StringUtils.isNotBlank(this.acctNickName)) {
            return this.acctNickName;
        }
        return getDisplayAccountName();
    }

    public String getDisplayAccountName() {
        return this.branchName;
    }

    /**
     * @return the bankNo
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * @param bankNo
     *         the bankNo to set
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
     *         the acno to set
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
     *         the custName to set
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
     *         the acnoType to set
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
     *         the acnoFlg to set
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
     *         the type to set
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
     *         the digitType to set
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
     *         the fsFlg to set
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
     *         the prodCode to set
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
     *         the branchNo to set
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
     *         the curr to set
     */
    public void setCurr(String curr) {
        this.curr = curr;
    }

    /**
     * @return the acctNickName
     */
    public String getAcctNickName() {
        return acctNickName;
    }

    /**
     * @param acctNickName
     *         the acctNickName to set
     */
    public void setAcctNickName(String acctNickName) {
        this.acctNickName = acctNickName;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName
     *         the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the curName
     */
    public String getCurName() {
        return curName;
    }

    /**
     * @param curName
     *         the curName to set
     */
    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
