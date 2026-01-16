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
package com.tfb.aibank.chl.component.suspendtask;

import java.util.ArrayList;
import java.util.List;

// @formatter:off
/**
 * @(#)SuspendTaskModel.java
 * 
 * <p>Description:暫停交易</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/22, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SuspendTask {

    public SuspendTask() {
        // default constructor
    }

    /** 交易代碼 */
    private String taskId;

    /** 暫停資訊(暫停時間、訊息...等) */
    private SuspendData suspendData;

    /** 暫停資訊(暫停時間、訊息...等) */
    private List<SuspendData> suspendDataList = new ArrayList<>();

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<SuspendData> getSuspendDataList() {
        return suspendDataList;
    }

    public void setSuspendDataList(List<SuspendData> suspendDataList) {
        this.suspendDataList = suspendDataList;
    }

    public SuspendData getSuspendData() {
        return suspendData;
    }

    public void setSuspendData(SuspendData suspendData) {
        this.suspendData = suspendData;
    }

}
