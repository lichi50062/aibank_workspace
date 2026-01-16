/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot016.model;

import com.ibm.tw.ibmb.base.model.RqData;
import com.tfb.aibank.chl.system.resource.dto.AppInfo;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NSTOT016010Rq.java
 * 
 * <p>Description:雙重驗證登入API 010 發送推播</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/27, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT016010Rq implements RqData {

    /**
     * 更新行為 ("", timeout, fail, success)
     */
    private String updateAction;

    /**
     * person notification record key (rowId)
     */
    private String rowId;

    /**
     * 雙重驗證 AppInfo
     */
    private AppInfo appInfo;

    /**
     * 信任裝置
     */
    private boolean trustDevice;

    public String getUpdateAction() {
        return updateAction;
    }

    public void setUpdateAction(String updateAction) {
        this.updateAction = updateAction;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public boolean isTrustDevice() {
        return trustDevice;
    }

    public void setTrustDevice(boolean trustDevice) {
        this.trustDevice = trustDevice;
    }
}
