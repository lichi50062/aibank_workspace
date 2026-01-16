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

import com.tfb.aibank.common.type.UpdateDataSyncStatusType;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)UpdateDataSyncRequest.java
* 
* <p>Description: 更新富證富壽資料彙整狀態resource request</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "更新富證富壽資料彙整狀態resource request")
public class UpdateDataSyncResourceRequest {

    /**
     * 使用者
     */
    @Schema(description = "使用者")
    private ApiRequestUser user;

    /**
     * 交易代碼
     */
    @Schema(description = "交易代碼")
    private String txn;

    /**
     * 更新資料彙整種類
     */
    @Schema(description = "更新資料彙整種類")
    private UpdateDataSyncStatusType updateDataSyncStatusType;

    /**
     * 更新資料彙整request
     */
    @Schema(description = "更新資料彙整request")
    private DataSyncStatusApiRequest changeStatusReq;

    public ApiRequestUser getUser() {
        return user;
    }

    public void setUser(ApiRequestUser user) {
        this.user = user;
    }

    public String getTxn() {
        return txn;
    }

    public void setTxn(String txn) {
        this.txn = txn;
    }

    public UpdateDataSyncStatusType getUpdateDataSyncStatusType() {
        return updateDataSyncStatusType;
    }

    public void setUpdateDataSyncStatusType(UpdateDataSyncStatusType updateDataSyncStatusType) {
        this.updateDataSyncStatusType = updateDataSyncStatusType;
    }

    public DataSyncStatusApiRequest getChangeStatusReq() {
        return changeStatusReq;
    }

    public void setChangeStatusReq(DataSyncStatusApiRequest changeStatusReq) {
        this.changeStatusReq = changeStatusReq;
    }
}
