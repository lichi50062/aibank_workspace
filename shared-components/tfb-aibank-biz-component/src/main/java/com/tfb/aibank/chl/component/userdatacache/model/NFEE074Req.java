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

import com.tfb.aibank.common.model.TxHeadRq;

// @formatter:off
/**
 * @(#)NFEE074Req.java
 * 
 * <p>Description:NFEE074Req</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/17, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NFEE074Req extends TxHeadRq {

    /** (信託 ID) */
    public static final String FIXED_CUSTID_AS_TRUST_ACCT = "99331241";

    /** 帳號 */
    private String acctId16;
    /** 密碼 */
    private String custPswd32;
    /** 金錢信託ID */
    private String custId;
    /** 使用者代號 */
    private String curAcctId;
    /** 戶名代號 */
    private String curAcctName;
    /** 內容 */
    private String type;
    /** 商品 */
    private String product;
    /** 客戶ID */
    private String custId2;
    /** 使用者代號2 */
    private String curAcctId2;

    public String getAcctId16() {
        return acctId16;
    }

    public void setAcctId16(String acctId16) {
        this.acctId16 = acctId16;
    }

    public String getCustPswd32() {
        return custPswd32;
    }

    public void setCustPswd32(String custPswd32) {
        this.custPswd32 = custPswd32;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCurAcctId() {
        return curAcctId;
    }

    public void setCurAcctId(String curAcctId) {
        this.curAcctId = curAcctId;
    }

    public String getCurAcctName() {
        return curAcctName;
    }

    public void setCurAcctName(String curAcctName) {
        this.curAcctName = curAcctName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCustId2() {
        return custId2;
    }

    public void setCustId2(String custId2) {
        this.custId2 = custId2;
    }

    public String getCurAcctId2() {
        return curAcctId2;
    }

    public void setCurAcctId2(String curAcctId2) {
        this.curAcctId2 = curAcctId2;
    }
}
