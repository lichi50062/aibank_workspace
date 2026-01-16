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

import com.tfb.aibank.biz.systemconfig.repository.entities.pk.ErrorCodeEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)ErrorCodeEntity.java
 * 
 * <p>Description:錯誤代碼 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "AI_ERROR_CODE")
@IdClass(ErrorCodeEntityPk.class)
public class ErrorCodeEntity implements Serializable {

    private static final long serialVersionUID = 6239614460284546197L;

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
     * 嚴重程度
     */
    @Basic
    @Column(name = "SEVERITY")
    private String severity;

    /**
     * 錯誤註記，0:引導；1:錯誤
     */
    @Basic
    @Column(name = "ERROR_FLAG")
    private Integer errorFlag;

    /**
     * 錯誤標題
     */
    @Basic
    @Column(name = "TITLE")
    private String title;

    /**
     * 內部錯誤訊息(客服看)
     */
    @Basic
    @Column(name = "INTERNAL_DESC")
    private String internalDesc;

    /**
     * 外部錯誤訊息(使用者看)
     */
    @Basic
    @Column(name = "EXTERNAL_DESC")
    private String externalDesc;

    /**
     * 顯示方式 0: 顯示DB錯誤訊息 1: 顯示主機錯誤訊息
     */
    @Basic
    @Column(name = "DISPLAY_FLAG")
    private Integer displayFlag;

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

    /**
     * 取得資料建立時間
     * 
     * @return Date 資料建立時間
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 設定資料建立時間
     * 
     * @param createTime
     *            要設定的資料建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得顯示方式 0: 顯示DB錯誤訊息 1: 顯示主機錯誤訊息
     * 
     * @return Integer 顯示方式 0: 顯示DB錯誤訊息 1: 顯示主機錯誤訊息
     */
    public Integer getDisplayFlag() {
        return this.displayFlag;
    }

    /**
     * 設定顯示方式 0: 顯示DB錯誤訊息 1: 顯示主機錯誤訊息
     * 
     * @param displayFlag
     *            要設定的顯示方式 0: 顯示DB錯誤訊息 1: 顯示主機錯誤訊息
     */
    public void setDisplayFlag(Integer displayFlag) {
        this.displayFlag = displayFlag;
    }

    /**
     * 取得錯誤碼
     * 
     * @return String 錯誤碼
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * 設定錯誤碼
     * 
     * @param errorCode
     *            要設定的錯誤碼
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 取得外部錯誤訊息(使用者看)
     * 
     * @return String 外部錯誤訊息(使用者看)
     */
    public String getExternalDesc() {
        return this.externalDesc;
    }

    /**
     * 設定外部錯誤訊息(使用者看)
     * 
     * @param externalDesc
     *            要設定的外部錯誤訊息(使用者看)
     */
    public void setExternalDesc(String externalDesc) {
        this.externalDesc = externalDesc;
    }

    /**
     * 取得內部錯誤訊息(客服看)
     * 
     * @return String 內部錯誤訊息(客服看)
     */
    public String getInternalDesc() {
        return this.internalDesc;
    }

    /**
     * 設定內部錯誤訊息(客服看)
     * 
     * @param internalDesc
     *            要設定的內部錯誤訊息(客服看)
     */
    public void setInternalDesc(String internalDesc) {
        this.internalDesc = internalDesc;
    }

    /**
     * 取得語系
     * 
     * @return String 語系
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * 設定語系
     * 
     * @param locale
     *            要設定的語系
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得嚴重程度
     * 
     * @return String 嚴重程度
     */
    public String getSeverity() {
        return this.severity;
    }

    /**
     * 設定嚴重程度
     * 
     * @param severity
     *            要設定的嚴重程度
     */
    public void setSeverity(String severity) {
        this.severity = severity;
    }

    /**
     * 取得錯誤碼系統別
     * 
     * @return String 錯誤碼系統別
     */
    public String getSystemId() {
        return this.systemId;
    }

    /**
     * 設定錯誤碼系統別
     * 
     * @param systemId
     *            要設定的錯誤碼系統別
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * 取得資料更新時間
     * 
     * @return Date 資料更新時間
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 設定資料更新時間
     * 
     * @param updateTime
     *            要設定的資料更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 取得更新者代碼
     * 
     * @return int 更新者代碼
     */
    public Integer getUpdateUserKey() {
        return this.updateUserKey;
    }

    /**
     * 設定更新者代碼
     * 
     * @param updateUserKey
     *            要設定的更新者代碼
     */
    public void setUpdateUserKey(Integer updateUserKey) {
        this.updateUserKey = updateUserKey;
    }

    public Integer getErrorFlag() {
        return errorFlag;
    }

    public void setErrorFlag(Integer errorFlag) {
        this.errorFlag = errorFlag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

}
