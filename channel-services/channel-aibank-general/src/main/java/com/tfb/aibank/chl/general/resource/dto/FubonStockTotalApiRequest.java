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
package com.tfb.aibank.chl.general.resource.dto;

/**
 * @(#)FubonStockTotalApiRequest.java
 *
 * <p>Description:富邦台市值總計API 下行資料</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/07/30, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class FubonStockTotalApiRequest{

    /**
     * 客戶身分證號
     */
    private String idno;

    /**
     * 客戶生日(西元年) YYYYMMDD
     */
    private String bdate;

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }
}
