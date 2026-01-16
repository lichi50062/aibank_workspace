package com.tfb.aibank.biz.user.repository.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Date;

import com.tfb.aibank.biz.user.repository.entities.pk.UsualTaskEntityPk;

// @formatter:off
/**
 * @(#)UsualTaskEntity.java
 * 
 * <p>Description:常用功能設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "USUAL_TASK")
@IdClass(UsualTaskEntityPk.class)
public class UsualTaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司鍵值
     */
    @Id
    @Column(name = "COMPANY_KEY", nullable = false)
    private Integer companyKey;

    /**
     * 使用者鍵值
     */
    @Id
    @Column(name = "USER_KEY", nullable = false)
    private Integer userKey;

    /**
     * 順序
     */
    @Column(name = "TASK_SORT")
    private Integer taskSort;


    /**
     * 功能代碼
     */
    @Id
    @Column(name = "TASK_ID")
    private String taskId;

    /**
     * 選單代碼
     */
    @Id
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 建立時間
     */
    @Column(name = "CREATE_TIME")
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
