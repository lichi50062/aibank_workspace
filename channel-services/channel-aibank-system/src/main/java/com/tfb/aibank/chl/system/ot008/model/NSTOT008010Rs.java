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
package com.tfb.aibank.chl.system.ot008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.ibm.tw.ibmb.util.IBUtils.ErrorDescription;

// @formatter:off
/**
 * @(#)NSTOT008010Rs.java
 * 
 * <p>Description:引導裝置綁定頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT008010Rs implements RsData {

    /** 是否執行變更綁定流程 */
    private boolean changeDevice;

    /** FIDO Portal SDK URL */
    // private String fidoPortalUrl;

    /** FIDO Portal 參加單位 */
    // private String businessNo;

    /** 綁定失敗Error資訊 */
    private ErrorDescription bindError;

    /**
     * 綁定方式 0 - 純綁定
     * 
     * 1-含無卡提款授權
     * 
     * 2-含手機門號轉帳授權
     * 
     * 3-含變更轉帳額度授權
     */
    private int authType;

    /**
     * 目前驗證的交易
     */
    private String taskNo;

    /**
     * @return the changeDevice
     */
    public boolean isChangeDevice() {
        return changeDevice;
    }

    /**
     * @param changeDevice
     *            the changeDevice to set
     */
    public void setChangeDevice(boolean changeDevice) {
        this.changeDevice = changeDevice;
    }

    /**
     * @return the bindError
     */
    public ErrorDescription getBindError() {
        return bindError;
    }

    /**
     * @param bindError
     *            the bindError to set
     */
    public void setBindError(ErrorDescription bindError) {
        this.bindError = bindError;
    }

    /**
     * @return the authType
     */
    public int getAuthType() {
        return authType;
    }

    /**
     * @param authType
     *            the authType to set
     */
    public void setAuthType(int authType) {
        this.authType = authType;
    }

    /**
     * @return the taskNo
     */
    public String getTaskNo() {
        return taskNo;
    }

    /**
     * @param taskNo
     *            the taskNo to set
     */
    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

}
