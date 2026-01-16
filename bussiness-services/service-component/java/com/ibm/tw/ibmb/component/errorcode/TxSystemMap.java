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
package com.ibm.tw.ibmb.component.errorcode;

import java.util.Date;

// @formatter:off
/**
 * @(#)TxSystemMap.java
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
public class TxSystemMap {

    /**
     * 電文代號
     */
    private String txId;

    /**
     * 錯誤碼系統別
     */
    private String srcId;

    /**
     * 建立時間
     */
    private Date createTime;

    /**
     * 更新時間
     */
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
