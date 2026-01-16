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
 * @(#)NSTOT008020Rq.java
 * 
 * <p>Description:裝置綁定條款頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT008020Rq implements RqData {

    private String remark;

    private String taskNo;

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
