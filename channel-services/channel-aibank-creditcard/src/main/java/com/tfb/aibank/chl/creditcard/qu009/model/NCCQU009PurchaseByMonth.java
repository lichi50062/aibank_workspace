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
package com.tfb.aibank.chl.creditcard.qu009.model;

import java.util.List;

// @formatter:off
/**
 * @(#)NCCQU009PurchaseDetail.java
 * 
 * <p>Description:單月消費明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU009PurchaseByMonth {
    /** 顯示月份 ex: 1 */
    private String mm;

    /** 顯示年 ex: 2022 */
    private String yy;

    /** 消費明細 */
    private List<NCCQU009PurchaseDetail> purchaseDetails;

    /**
     * @return the mm
     */
    public String getMm() {
        return mm;
    }

    /**
     * @param mm
     *            the mm to set
     */
    public void setMm(String mm) {
        this.mm = mm;
    }

    /**
     * @return the yy
     */
    public String getYy() {
        return yy;
    }

    /**
     * @param yy
     *            the yy to set
     */
    public void setYy(String yy) {
        this.yy = yy;
    }

    /**
     * @return the purchaseDetails
     */
    public List<NCCQU009PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    /**
     * @param purchaseDetails
     *            the purchaseDetails to set
     */
    public void setPurchaseDetails(List<NCCQU009PurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }

}
