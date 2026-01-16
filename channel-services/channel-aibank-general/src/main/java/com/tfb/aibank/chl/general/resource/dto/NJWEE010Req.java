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
package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
/**
 * @(#)NJWEE010Request.java
 * 
 * <p>Description:電文：NJWEE010 上送欄位</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/21, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NJWEE010Req {

    /** 帳號 */
    private String acctId16;

    /** 密碼 */
    private String custPswd32;

    /** 客戶ID */
    private String custId;

    /** 使用者代號 */
    private String curAcctId;

    /** 戶名代號 */
    private String curAcctName;

    /** 內容 */
    private String type;

    /** 商品 */
    private String product;

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
}
