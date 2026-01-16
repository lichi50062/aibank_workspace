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

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)RoleTaskEntity.java
 * 
 * <p>Description:角色交易清單 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "ROLE_TASK")
@IdClass(com.tfb.aibank.biz.systemconfig.repository.entities.pk.RoleTaskEntityPk.class)
public class RoleTaskEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 角色鍵值 */
    @Id
    @Column(name = "ROLE_KEY")
    private Integer roleKey;

    /** 交易公司鍵值 */
    @Id
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 交易功能代號 */
    @Id
    @Column(name = "TASK_ID")
    private String taskId;

    /** 交易群組代號 */
    @Basic
    @Column(name = "APP_ID")
    private String appId;

    /** 角色所屬公司鍵值 */
    @Basic
    @Column(name = "ROLE_COMPANY_KEY")
    private Integer roleCompanyKey;

    /**
     * 取得交易代碼
     * 
     * @return String 交易代碼
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * 設定交易代碼
     * 
     * @param taskId
     *            要設定的交易代碼
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(Integer roleKey) {
        this.roleKey = roleKey;
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public Integer getRoleCompanyKey() {
        return roleCompanyKey;
    }

    public void setRoleCompanyKey(Integer roleCompanyKey) {
        this.roleCompanyKey = roleCompanyKey;
    }

    /**
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     *            the appId to set
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }
}
