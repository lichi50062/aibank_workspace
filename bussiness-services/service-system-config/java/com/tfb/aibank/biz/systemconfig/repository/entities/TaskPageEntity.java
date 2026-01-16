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
package com.tfb.aibank.biz.systemconfig.repository.entities;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)TaskEntity.java
 * 
 * <p>Description:交易頁面 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/15, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "TASK_PAGE")
public class TaskPageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 交易功能頁面代號
     */
    @Id
    @Column(name = "TASK_PAGE_ID")
    private String taskPageId;

    /**
     * 交易功能代號
     */
    @Basic
    @Column(name = "TASK_ID")
    private String taskId;

    /**
     * 交易功能頁面名稱
     */
    @Basic
    @Column(name = "TASK_PAGE_NAME")
    private String taskPageName;

    /**
     * 結果頁留存註記，Y：要留存
     */
    @Basic
    @Column(name = "SAVE_FLAG")
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
