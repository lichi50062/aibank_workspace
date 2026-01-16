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

// @formatter:off
/**
 * @(#)FubonStockApiRequest.java
 * 
 * <p>Description:富邦證券整註記異動API 上行資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FubonStockApiRequest {

    /**
     * 資產項目代碼
     */
    private String assetcode;

    /**
     * 客戶身分證號
     */
    private String idno;

    /**
     * 客戶生日(西元年)
     */
    private String bdate;

    /**
     * 來源
     */
    private String channel;

    public FubonStockApiRequest() {
    }

    public FubonStockApiRequest(String assetcode, String idno, String bdate, String channel) {
        this.assetcode = assetcode;
        this.idno = idno;
        this.bdate = bdate;
        this.channel = channel;
    }

    public String getAssetcode() {
        return assetcode;
    }

    public void setAssetcode(String assetcode) {
        this.assetcode = assetcode;
    }

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

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "FubonStockApiRequest [assetcode=" + assetcode + ", idno=" + idno + ", bdate=" + bdate + ", channel=" + channel + "]";
    }
}
