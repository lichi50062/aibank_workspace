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
package com.tfb.aibank.chl.component.task;

import java.util.Date;

// @formatter:off
/**
 * @(#)TaskPage.java
 * 
 * <p>Description:交易頁面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/15, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class TaskPage {

    /**
     * 交易功能頁面代號
     */
    private String taskPageId;

    /**
     * 交易功能代號
     */
    private String taskId;

    /**
     * 交易功能頁面名稱
     */
    private String taskPageName;

    /**
     * 結果頁留存註記，Y：要留存
     */
    private String saveFlag;

    public String getTaskPageId() {
        return taskPageId;
    }

    public void setTaskPageId(String taskPageId) {
        this.taskPageId = taskPageId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskPageName() {
        return taskPageName;
    }

    public void setTaskPageName(String taskPageName) {
        this.taskPageName = taskPageName;
    }

    public String getSaveFlag() {
        return saveFlag;
    }

    public void setSaveFlag(String saveFlag) {
        this.saveFlag = saveFlag;
    }
}
