package com.tfb.aibank.biz.user.services.usualtask.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * 常用功能設定
 */
public class UsualTaskModel implements Serializable {

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
     * 功能代碼
     */
    @Schema(description = "功能代碼")
    private String taskId;

    @Schema(description = "選單代碼")
    private String menuId;

    /**
     * 建立時間
     */
    @Schema(description = "建立時間")
    private Date createTime;

    /**
     * 公司鍵值
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * 公司鍵值
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 使用者鍵值
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * 使用者鍵值
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * 順序
     */
    public Integer getTaskSort() {
        return taskSort;
    }

    /**
     * 順序
     */
    public void setTaskSort(Integer taskSort) {
        this.taskSort = taskSort;
    }

    /**
     * 功能代碼
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 功能代碼
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 建立時間
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 建立時間
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
