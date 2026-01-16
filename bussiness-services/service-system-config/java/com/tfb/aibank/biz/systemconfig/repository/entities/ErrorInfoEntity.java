/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2022.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.systemconfig.repository.entities;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.systemconfig.repository.entities.pk.ErrorInfoEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)ErrorCodeEntityPk.java
 * 
 * <p>Description:錯誤代碼客製化資料表 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "AI_ERROR_INFO")
@IdClass(ErrorInfoEntityPk.class)
public class ErrorInfoEntity implements Serializable {

    private static final long serialVersionUID = -5363767056897691895L;

    /**
     * 錯誤碼系統別
     */
    @Id
    @Column(name = "SYSTEM_ID")
    private String systemId;

    /**
     * 錯誤碼
     */
    @Id
    @Column(name = "ERROR_CODE")
    private String errorCode;

    /**
     * 頁面代碼
     */
    @Id
    @Column(name = "PAGE_ID")
    private String pageId;

    /**
     * 語系
     */
    @Id
    @Column(name = "LOCALE")
    private String locale;

    /**
     * 提示引導說明
     */
    @Basic
    @Column(name = "INFO")
    private String info;

    /**
     * 轉導按鈕名稱1
     */
    @Basic
    @Column(name = "DIRECT_BUTTON_NAME_1")
    private String directButtonName1;

    /**
     * 轉導交易1
     */
    @Basic
    @Column(name = "DIRECT_TASK_ID_1")
    private String directTaskId1;

    /**
     * 轉導按鈕名稱2
     */
    @Basic
    @Column(name = "DIRECT_BUTTON_NAME_2")
    private String directButtonName2;

    /**
     * 轉導交易2
     */
    @Basic
    @Column(name = "DIRECT_TASK_ID_2")
    private String directTaskId2;

    /**
     * 資料建立時間
     */
    @Basic
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 資料更新時間
     */
    @Basic
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 更新者代碼
     */
    @Basic
    @Column(name = "UPDATE_USER_KEY")
    private Integer updateUserKey;

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDirectButtonName1() {
        return directButtonName1;
    }

    public void setDirectButtonName1(String directButtonName1) {
        this.directButtonName1 = directButtonName1;
    }

    public String getDirectTaskId1() {
        return directTaskId1;
    }

    public void setDirectTaskId1(String directTaskId1) {
        this.directTaskId1 = directTaskId1;
    }

    public String getDirectButtonName2() {
        return directButtonName2;
    }

    public void setDirectButtonName2(String directButtonName2) {
        this.directButtonName2 = directButtonName2;
    }

    public String getDirectTaskId2() {
        return directTaskId2;
    }

    public void setDirectTaskId2(String directTaskId2) {
        this.directTaskId2 = directTaskId2;
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

    public Integer getUpdateUserKey() {
        return updateUserKey;
    }

    public void setUpdateUserKey(Integer updateUserKey) {
        this.updateUserKey = updateUserKey;
    }

}
