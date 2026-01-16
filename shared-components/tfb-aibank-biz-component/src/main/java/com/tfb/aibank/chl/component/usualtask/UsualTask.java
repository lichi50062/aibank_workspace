/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.usualtask;

import java.io.Serializable;
import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;

/**
 * 常用功能設定
 */
public class UsualTask implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司鍵值
     */
    @Schema(description = "公司鍵值")
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    @Schema(description = "使用者鍵值")
    private Integer userKey;

    /**
     * 順序
     */
    @Schema(description = "順序")
    private Integer taskSort;

    /**
     * 選單代碼
     */
    @Schema(description = "選單代碼")
    private String menuId;

    /**
     * 選單名稱
     */
    @Schema(description = "選單名稱")
    private String menuName;

    /**
     * 功能代碼
     */
    @Schema(description = "功能代碼")
    private String taskId;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 更新時間
     */
    @Schema(description = "更新時間")
    private Date updateTime;

    /**
     * 功能圖示
     */
    @Schema(description = "功能圖示")
    private String iconClass;

    /**
     * 選單代碼
     */
    @Schema(description = "上一層選單代碼")
    private String parentMenuId;

    /**
     * 選單名稱
     */
    @Schema(description = "上一層選單名稱")
    private String parentMenuMame;

    /**
     * MENU連結類型
     */
    @Column(name = "MENU連結類型")
    private int linkType;

    /**
     * 開新視窗參數
     */
    @Column(name = "開新視窗參數")
    private String linkParam;

    /**
     * 公司鍵值
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 公司鍵值
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * 使用者鍵值
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 使用者鍵值
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * 順序
     */
    public void setTaskSort(Integer taskSort) {
        this.taskSort = taskSort;
    }

    /**
     * 順序
     */
    public Integer getTaskSort() {
        return taskSort;
    }

    /**
     * 選單代碼
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 選單代碼
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 選單名稱
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    /**
     * 選單名稱
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 功能代碼
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 功能代碼
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 建立時間
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 更新時間
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 更新時間
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getParentMenuMame() {
        return parentMenuMame;
    }

    public void setParentMenuMame(String parentMenuMame) {
        this.parentMenuMame = parentMenuMame;
    }

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    public String getLinkParam() {
        return linkParam;
    }

    public void setLinkParam(String linkParam) {
        this.linkParam = linkParam;
    }

    @Override
    public String toString() {
        return "UsualTask{" + "companyKey=" + companyKey + '\'' + "userKey=" + userKey + '\'' + "taskSort=" + taskSort + '\'' + "menuId=" + menuId + '\'' + "menuName=" + menuName + '\'' + "taskId=" + taskId + '\'' + "createTime=" + createTime + '\'' + "updateTime=" + updateTime + '\'' + "parentMenuId=" + parentMenuId + '\'' + "parentMenuMame=" + parentMenuMame + '\'' + '}';
    }

}
