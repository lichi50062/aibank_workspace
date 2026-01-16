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
 * @(#)MenuNameEntity.java
 * 
 * <p>Description:選單名稱 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "MENU_NAME")
@IdClass(com.tfb.aibank.biz.systemconfig.repository.entities.pk.MenuNameEntityPk.class)
public class MenuNameEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 語系
     */
    @Id
    @Column(name = "LOCALE")
    private String locale;

    /**
     * 選單代碼
     */
    @Id
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * 選單名稱
     */
    @Basic
    @Column(name = "MENU_NAME")
    private String menuName;

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
     * 取得選單代碼
     * 
     * @return String 選單代碼
     */
    public String getMenuId() {
        return this.menuId;
    }

    /**
     * 設定選單代碼
     * 
     * @param menuId
     *            要設定的選單代碼
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 取得選單名稱
     * 
     * @return String 選單名稱
     */
    public String getMenuName() {
        return this.menuName;
    }

    /**
     * 設定選單名稱
     * 
     * @param menuName
     *            要設定的選單名稱
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
}
