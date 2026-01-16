package com.tfb.aibank.biz.systemconfig.repository.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)RoleEntity.java
 * 
 * <p>Description:角色定義</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/25, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "ROLE")
public class RoleEntity {

    /** 角色鍵值 */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_SEQ")
    @SequenceGenerator(name = "ROLE_SEQ", sequenceName = "ROLE_SEQ", allocationSize = 1)
    @Column(name = "ROLE_KEY")
    private int roleKey;

    /** 角色名稱 */
    @Basic
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色類別
     * <ul>
     * <li>1：授權管理者</li>
     * <li>2：授權放行者（保留）</li>
     * <li>9：自行建立</li>
     * </ul>
     */
    @Basic
    @Column(name = "ROLE_TYPE")
    private Integer roleType = 9;

    /** 隸屬公司鍵值 */
    @Basic
    @Column(name = "COMPANY_KEY")
    private Integer companyKey;

    /** 角色說明 */
    @Basic
    @Column(name = "MEMO")
    private String memo;

    /**
     * 取得角色鍵值
     * 
     * @return
     */
    public Integer getRoleKey() {
        return roleKey;
    }

    /**
     * 設定角色鍵值
     * 
     * @param roleKey
     */
    public void setRoleKey(Integer roleKey) {
        this.roleKey = roleKey;
    }

    /**
     * 取得角色名稱
     * 
     * @return
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 設定角色名稱
     * 
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 取得角色類別
     * <ul>
     * <li>1：授權管理者</li>
     * <li>2：授權放行者（保留）</li>
     * <li>9：自行建立</li>
     * </ul>
     * 
     * @return
     * @see com.ibm.tw.biz.common.type.RoleType
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * 設定角色類別
     * <ul>
     * <li>1：授權管理者</li>
     * <li>2：授權放行者（保留）</li>
     * <li>9：自行建立</li>
     * </ul>
     * 
     * @param roleType
     * @see com.ibm.tw.biz.common.type.RoleType
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    /**
     * 取得隸屬公司鍵值
     * 
     * @return
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * 設定隸屬公司鍵值
     * 
     * @param companyKey
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * 取得隸屬部門鍵值
     * 
     * @return
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 設定隸屬部門鍵值
     * 
     * @param memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

}
