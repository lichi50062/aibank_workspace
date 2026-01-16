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
 * @(#)CM061435CRResRep.java
 * 
 * <p>Description:高資產客戶資訊查詢商品</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CM061435CRResRep {

    /**
     * 法人授權辦理交易人員 商品
     */
    private String legalProd;

    /**
     * 法人授權辦理交易人員 ID
     */
    private String legalId;

    /**
     * @return the legalProd
     */
    public String getLegalProd() {
        return legalProd;
    }

    /**
     * @param legalProd
     *            the legalProd to set
     */
    public void setLegalProd(String legalProd) {
        this.legalProd = legalProd;
    }

    /**
     * @return the legalId
     */
    public String getLegalId() {
        return legalId;
    }

    /**
     * @param legalId
     *            the legalId to set
     */
    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

}
