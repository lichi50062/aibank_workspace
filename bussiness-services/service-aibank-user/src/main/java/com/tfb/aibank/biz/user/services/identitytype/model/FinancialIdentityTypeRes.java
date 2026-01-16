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
package com.tfb.aibank.biz.user.services.identitytype.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB032179Res.java
 * 
 * <p>Description:金融同業身分別 RS</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/29, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "金融同業身分別 Rs")
public class FinancialIdentityTypeRes {
    /** 客戶中文姓名 */
    @Schema(description = "客戶中文姓名")
    private String custName;

    /** 客戶身份別 */
    @Schema(description = "客戶身份別")
    private String custTyp;

    /** 客戶身份別中文說明 */
    @Schema(description = "客戶身份別中文說明")
    private String custTyp_Name;

    /** 8項/10項作業交易 */
    @Schema(description = "8項/10項作業交易")
    private String num01;

    /** 郵電費 */
    @Schema(description = "郵電費")
    private String num02;

    /** 跨行提款 */
    @Schema(description = "跨行提款")
    private String num03;

    /** 跨行轉帳 */
    @Schema(description = "跨行轉帳")
    private String num04;

    /** 保管箱 */
    @Schema(description = "保管箱")
    private String num05;

    /** 停車時數 */
    @Schema(description = "停車時數")
    private String num06;

    /** 應收金額 */
    @Schema(description = "應收金額")
    private String txAmt;

    /** 實收金額 */
    @Schema(description = "實收金額")
    private String actAmt;

    /** 分潤金額 */
    @Schema(description = "分潤金額")
    private String feeAmt;

    /** 交易序號 */
    @Schema(description = "交易序號")
    private String txSrl;

    /** 行業別 */
    @Schema(description = "行業別")
    private String iduCod;

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
     * @return the custTyp
     */
    public String getCustTyp() {
        return custTyp;
    }

    /**
     * @param custTyp
     *            the custTyp to set
     */
    public void setCustTyp(String custTyp) {
        this.custTyp = custTyp;
    }

    /**
     * @return the custTyp_Name
     */
    public String getCustTyp_Name() {
        return custTyp_Name;
    }

    /**
     * @param custTyp_Name
     *            the custTyp_Name to set
     */
    public void setCustTyp_Name(String custTyp_Name) {
        this.custTyp_Name = custTyp_Name;
    }

    /**
     * @return the num01
     */
    public String getNum01() {
        return num01;
    }

    /**
     * @param num01
     *            the num01 to set
     */
    public void setNum01(String num01) {
        this.num01 = num01;
    }

    /**
     * @return the num02
     */
    public String getNum02() {
        return num02;
    }

    /**
     * @param num02
     *            the num02 to set
     */
    public void setNum02(String num02) {
        this.num02 = num02;
    }

    /**
     * @return the num03
     */
    public String getNum03() {
        return num03;
    }

    /**
     * @param num03
     *            the num03 to set
     */
    public void setNum03(String num03) {
        this.num03 = num03;
    }

    /**
     * @return the num04
     */
    public String getNum04() {
        return num04;
    }

    /**
     * @param num04
     *            the num04 to set
     */
    public void setNum04(String num04) {
        this.num04 = num04;
    }

    /**
     * @return the num05
     */
    public String getNum05() {
        return num05;
    }

    /**
     * @param num05
     *            the num05 to set
     */
    public void setNum05(String num05) {
        this.num05 = num05;
    }

    /**
     * @return the num06
     */
    public String getNum06() {
        return num06;
    }

    /**
     * @param num06
     *            the num06 to set
     */
    public void setNum06(String num06) {
        this.num06 = num06;
    }

    /**
     * @return the txAmt
     */
    public String getTxAmt() {
        return txAmt;
    }

    /**
     * @param txAmt
     *            the txAmt to set
     */
    public void setTxAmt(String txAmt) {
        this.txAmt = txAmt;
    }

    /**
     * @return the actAmt
     */
    public String getActAmt() {
        return actAmt;
    }

    /**
     * @param actAmt
     *            the actAmt to set
     */
    public void setActAmt(String actAmt) {
        this.actAmt = actAmt;
    }

    /**
     * @return the feeAmt
     */
    public String getFeeAmt() {
        return feeAmt;
    }

    /**
     * @param feeAmt
     *            the feeAmt to set
     */
    public void setFeeAmt(String feeAmt) {
        this.feeAmt = feeAmt;
    }

    /**
     * @return the txSrl
     */
    public String getTxSrl() {
        return txSrl;
    }

    /**
     * @param txSrl
     *            the txSrl to set
     */
    public void setTxSrl(String txSrl) {
        this.txSrl = txSrl;
    }

    /**
     * @return the iduCod
     */
    public String getIduCod() {
        return iduCod;
    }

    /**
     * @param iduCod
     *            the iduCod to set
     */
    public void setIduCod(String iduCod) {
        this.iduCod = iduCod;
    }

}
