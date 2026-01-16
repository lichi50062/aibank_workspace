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

// @formatter:off
/**
 * @(#)BondOverviewReq.java
 * 
 * <p>Description:[債券帳戶總覽電文]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/12, leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondOverviewReq {
    /** 是否為OBU 1|0 */
    private Boolean isObu;
    /** 客戶ID */
    private String custId;
    /** 使用者代碼 */
    private String userId;
    /** 戶名代號 */
    private String nameCode;
    /** 內容 1:全部 2:庫存 3:在途 */
    private String type;
    /** 商品 1(海外債券) 2(結構型) */
    private String product;

    /**
     * @return the isObu
     */
    public Boolean getIsObu() {
        return isObu;
    }

    /**
     * @param isObu
     *            the isObu to set
     */
    public void setIsObu(Boolean isObu) {
        this.isObu = isObu;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the nameCode
     */
    public String getNameCode() {
        return nameCode;
    }

    /**
     * @param nameCode
     *            the nameCode to set
     */
    public void setNameCode(String nameCode) {
        this.nameCode = nameCode;
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
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * @param product
     *            the product to set
     */
    public void setProduct(String product) {
        this.product = product;
    }

}
