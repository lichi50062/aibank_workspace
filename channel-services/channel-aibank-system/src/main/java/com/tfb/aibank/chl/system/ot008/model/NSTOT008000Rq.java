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
 * @(#)NSTOT008000Rq.java
 * 
 * <p>Description:裝置綁定檢核頁面初始初始</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT008000Rq implements RqData {

    /** 交易代號 */
    private String taskId;

    /** 狀態檢視 */
    private boolean isCheckOnly;

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId
     *            the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the isCheckOnly
     */
    public boolean isCheckOnly() {
        return isCheckOnly;
    }

    /**
     * @param isCheckOnly
     *            the isCheckOnly to set
     */
    public void setCheckOnly(boolean isCheckOnly) {
        this.isCheckOnly = isCheckOnly;
    }

}
