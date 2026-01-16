package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;

public class UsualTaskEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 公司鍵值 */
    private Integer companyKey;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 選單代碼 */
    private String taskId;

    /**
     * 選單代碼
     */
    private String menuId;

    public UsualTaskEntityPk() {
    }

    public UsualTaskEntityPk(Integer companyKey, Integer userKey, String taskId, String menuId) {
        this.companyKey = companyKey;
        this.userKey = userKey;
        this.taskId = taskId;
        this.menuId = menuId;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}