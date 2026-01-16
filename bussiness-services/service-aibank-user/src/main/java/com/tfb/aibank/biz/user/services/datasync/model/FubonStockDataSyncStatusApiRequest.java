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
package com.tfb.aibank.biz.user.services.datasync.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)FubonStockDataSyncStatusApiRequest.java
* 
* <p>Description: 欲更新富邦證券 彙整狀態/p>
* 
* <p>Modify History:</p>
* v1.0, 2025/07/30, Peter
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "富邦證券 彙整狀態")
public class FubonStockDataSyncStatusApiRequest {

    /**
     * 客戶生份證號
     */
    @Schema(description = "客戶生份證號")
    private String idno;

    /**
     * 客戶生日
     */
    @Schema(description = "客戶生日")
    private String bdate;

    /**
     * 來源channel
     */
    @Schema(description = "來源channel")
    private String channel;

    /**
     * 異動情境
     */
    @Schema(description = "異動情境")
    private String usetype;

    /**
     * 欲異動彙整註記
     */
    @Schema(description = "欲異動彙整註記")
    private String status;

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

    public String getUsetype() {
        return usetype;
    }

    public void setUsetype(String usetype) {
        this.usetype = usetype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
