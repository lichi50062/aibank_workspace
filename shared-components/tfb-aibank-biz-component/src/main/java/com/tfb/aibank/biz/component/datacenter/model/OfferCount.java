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
package com.tfb.aibank.biz.component.datacenter.model;

// @formatter:off
/**
 * @(#)OfferCount.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, alexlee	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OfferCount {
    /**  */
    private Integer advisoryCount;
    /**  */
    private Integer convenientCount;
    /**  */
    private Integer xsellCount;

    /**
     * @return the advisoryCount
     */
    public Integer getAdvisoryCount() {
        return advisoryCount;
    }

    /**
     * @param advisoryCount
     *            the advisoryCount to set
     */
    public void setAdvisoryCount(Integer advisoryCount) {
        this.advisoryCount = advisoryCount;
    }

    /**
     * @return the convenientCount
     */
    public Integer getConvenientCount() {
        return convenientCount;
    }

    /**
     * @param convenientCount
     *            the convenientCount to set
     */
    public void setConvenientCount(Integer convenientCount) {
        this.convenientCount = convenientCount;
    }

    /**
     * @return the xsellCount
     */
    public Integer getXsellCount() {
        return xsellCount;
    }

    /**
     * @param xsellCount
     *            the xsellCount to set
     */
    public void setXsellCount(Integer xsellCount) {
        this.xsellCount = xsellCount;
    }

}
