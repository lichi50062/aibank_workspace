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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)MenuKeywordMappingEntity.java
 * 
 * <p>Description:選單關鍵字對照表 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "MENU_KEYWORD_MAPPING")
public class MenuKeywordMappingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 資料鍵值
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MENU_KEYWORD_MAPPING_SEQ")
    @SequenceGenerator(name = "MENU_KEYWORD_MAPPING_SEQ", sequenceName = "MENU_KEYWORD_MAPPING_SEQ", allocationSize = 1)
    @Column(name = "MENU_KEYWORD_MAPPING_KEY")
    private Integer menuKeywordMappingKey;

    /**
     * MENU_ID
     */
    @Basic
    @Column(name = "MENU_ID")
    private String menuId;

    /**
     * keyword
     */
    @Basic
    @Column(name = "KEYWORD")
    private String keyword;

    public Integer getMenuKeywordMappingKey() {
        return menuKeywordMappingKey;
    }

    public void setMenuKeywordMappingKey(Integer menuKeywordMappingKey) {
        this.menuKeywordMappingKey = menuKeywordMappingKey;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
