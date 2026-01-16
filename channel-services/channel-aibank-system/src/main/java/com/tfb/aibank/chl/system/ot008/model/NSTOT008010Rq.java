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

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT008010Rq.java
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
public class NSTOT008010Rq implements RqData {

    /** 是否執行變更綁定流程 */
    private boolean changeDevice;

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
