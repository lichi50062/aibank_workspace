/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2022.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.systemconfig.services.errorcode.model;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)TxSystemMapEntity.java
 * 
 * <p>Description:外部系統代號與系統別對應檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "外部系統代號與系統別對應檔")
public class TxSystemMapModel {

    /**
     * 電文代號
     */
    @Schema(description = "電文代號")
    private String txId;

    /**
     * 錯誤碼系統別
     */
    @Schema(description = "錯誤碼系統別")
    private String srcId;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Date updateTime;

    public String getTxId() {
        return txId;
    }

    public void setTxId(String txId) {
        this.txId = txId;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
