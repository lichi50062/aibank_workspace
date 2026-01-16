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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.ibm.tw.commons.util.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)信託契約.java
 * 
 * <p>Description:信託契約</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/09, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "信託契約")
public class TrustContract {
    /** 契約編號 */
    @Schema(description = "契約編號")
    private String contractNo;
    /** 信託幣 */
    @Schema(description = "信託幣")
    private String contractCur;
    /** 專戶帳號1 */
    @Schema(description = "專戶帳號1")
    private String acctId01;
    /** 帳號幣別1 */
    @Schema(description = "帳號幣別1")
    private String acctCur01;
    /** 主運用帳號註記1 */
    @Schema(description = "主運用帳號註記1")
    private String acct01tag;
    /** 專戶帳號2 */
    @Schema(description = "專戶帳號2")
    private String acctId02;
    /** 帳號幣別2 */
    @Schema(description = "帳號幣別2")
    private String acctCur02;
    /** 主運用帳號註記2 */
    @Schema(description = "主運用帳號註記2")
    private String acct02tag;
    /** 專戶帳號3 */
    @Schema(description = "專戶帳號3")
    private String acctId03;
    /** 帳號幣別3 */
    @Schema(description = "帳號幣別3")
    private String acctCur03;
    /** 主運用帳號註記3 */
    @Schema(description = "主運用帳號註記3")
    private String acct03tag;
    /** 專戶帳號4 */
    @Schema(description = "專戶帳號4")
    private String acctId04;
    /** 帳號幣別4 */
    @Schema(description = "帳號幣別4")
    private String acctCur04;
    /** 主運用帳號註記4 */
    @Schema(description = "主運用帳號註記4")
    private String acct04tag;
    /** 角色 */
    @Schema(description = "角色")
    private String role;
    /** 契約迄日 */
    @Schema(description = "契約迄日")
    private Date contractEndDate;

    /** 專戶帳號任一筆有值得帳號並過濾相同帳號 */
    public List<String> getSpecialAccount() {
        return Stream.of(acctId01, acctId02, acctId03, acctId04)
                .filter(StringUtils::isNotBlank)
                .distinct()
                .collect(Collectors.toList());
    }

    /** 取得主帳號 */
    public List<String> getMainAcct() {
        List<String> mainAccount = new ArrayList<>();
        if (StringUtils.equals("1", this.acct01tag)) {
            mainAccount.add(this.acctId01);
        }
        if (StringUtils.equals("1", this.acct02tag)) {
            mainAccount.add(this.acctId02);
        }
        if (StringUtils.equals("1", this.acct03tag)) {
            mainAccount.add(this.acctId03);
        }
        if (StringUtils.equals("1", this.acct04tag)) {
            mainAccount.add(this.acctId04);
        }
        return mainAccount;
    }

    /** 取得帳號集合 */
    public List<String> getAccountIdList() {
        List<String> mainAccount = new ArrayList<>();
        if (StringUtils.isNotBlank(this.acctId01)) {
            mainAccount.add(this.acctId01);
        }
        if (StringUtils.isNotBlank(this.acctId02)) {
            mainAccount.add(this.acctId02);
        }
        if (StringUtils.isNotBlank(this.acctId03)) {
            mainAccount.add(this.acctId03);
        }
        if (StringUtils.isNotBlank(this.acctId04)) {
            mainAccount.add(this.acctId04);
        }
        return mainAccount;
    }

    /**
     * @return the contractNo
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * @param contractNo
     *            the contractNo to set
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    /**
     * @return the contractCur
     */
    public String getContractCur() {
        return contractCur;
    }

    /**
     * @param contractCur
     *            the contractCur to set
     */
    public void setContractCur(String contractCur) {
        this.contractCur = contractCur;
    }

    /**
     * @return the acctId01
     */
    public String getAcctId01() {
        return acctId01;
    }

    /**
     * @param acctId01
     *            the acctId01 to set
     */
    public void setAcctId01(String acctId01) {
        this.acctId01 = acctId01;
    }

    /**
     * @return the acctCur01
     */
    public String getAcctCur01() {
        return acctCur01;
    }

    /**
     * @param acctCur01
     *            the acctCur01 to set
     */
    public void setAcctCur01(String acctCur01) {
        this.acctCur01 = acctCur01;
    }

    /**
     * @return the acct01tag
     */
    public String getAcct01tag() {
        return acct01tag;
    }

    /**
     * @param acct01tag
     *            the acct01tag to set
     */
    public void setAcct01tag(String acct01tag) {
        this.acct01tag = acct01tag;
    }

    /**
     * @return the acctId02
     */
    public String getAcctId02() {
        return acctId02;
    }

    /**
     * @param acctId02
     *            the acctId02 to set
     */
    public void setAcctId02(String acctId02) {
        this.acctId02 = acctId02;
    }

    /**
     * @return the acctCur02
     */
    public String getAcctCur02() {
        return acctCur02;
    }

    /**
     * @param acctCur02
     *            the acctCur02 to set
     */
    public void setAcctCur02(String acctCur02) {
        this.acctCur02 = acctCur02;
    }

    /**
     * @return the acct02tag
     */
    public String getAcct02tag() {
        return acct02tag;
    }

    /**
     * @param acct02tag
     *            the acct02tag to set
     */
    public void setAcct02tag(String acct02tag) {
        this.acct02tag = acct02tag;
    }

    /**
     * @return the acctId03
     */
    public String getAcctId03() {
        return acctId03;
    }

    /**
     * @param acctId03
     *            the acctId03 to set
     */
    public void setAcctId03(String acctId03) {
        this.acctId03 = acctId03;
    }

    /**
     * @return the acctCur03
     */
    public String getAcctCur03() {
        return acctCur03;
    }

    /**
     * @param acctCur03
     *            the acctCur03 to set
     */
    public void setAcctCur03(String acctCur03) {
        this.acctCur03 = acctCur03;
    }

    /**
     * @return the acct03tag
     */
    public String getAcct03tag() {
        return acct03tag;
    }

    /**
     * @param acct03tag
     *            the acct03tag to set
     */
    public void setAcct03tag(String acct03tag) {
        this.acct03tag = acct03tag;
    }

    /**
     * @return the acctId04
     */
    public String getAcctId04() {
        return acctId04;
    }

    /**
     * @param acctId04
     *            the acctId04 to set
     */
    public void setAcctId04(String acctId04) {
        this.acctId04 = acctId04;
    }

    /**
     * @return the acctCur04
     */
    public String getAcctCur04() {
        return acctCur04;
    }

    /**
     * @param acctCur04
     *            the acctCur04 to set
     */
    public void setAcctCur04(String acctCur04) {
        this.acctCur04 = acctCur04;
    }

    /**
     * @return the acct04tag
     */
    public String getAcct04tag() {
        return acct04tag;
    }

    /**
     * @param acct04tag
     *            the acct04tag to set
     */
    public void setAcct04tag(String acct04tag) {
        this.acct04tag = acct04tag;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the contractEndDate
     */
    public Date getContractEndDate() {
        return contractEndDate;
    }

    /**
     * @param contractEndDate
     *            the contractEndDate to set
     */
    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

}
