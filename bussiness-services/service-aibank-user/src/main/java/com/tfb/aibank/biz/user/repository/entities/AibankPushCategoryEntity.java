/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository.entities;

import java.io.Serializable;

import com.tfb.aibank.biz.user.repository.entities.pk.AibankPushCategoryEntityPk;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

// @formatter:off
/**
 * @(#)AibankPushCategoryEntity.java
 * 
 * <p>Description:推播通知類別 Entity</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/02/07, Edward Tien    
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Entity
@Table(name = "AIBANK_PUSH_CATEGORY")
@IdClass(AibankPushCategoryEntityPk.class)
public class AibankPushCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 推播鍵值
     */
    @Id
    @Column(name = "PUSH_CATEGORY_KEY")
    private Integer pushCategoryKey;

    /**
     * 語系
     */
    @Id
    @Column(name = "LOCALE")
    private String locale;

    /**
     * 類別
     */
    @Basic
    @Column(name = "CATEGORY")
    private String category;

    /**
     * 啟用 0:未啟用 1:啟用
     */
    @Basic
    @Column(name = "ENABLE")
    private Integer enable;

    /**
     * 是否為群組層級 1:是 0:否
     */
    @Basic
    @Column(name = "ISGROUP")
    private Integer isGroup;

    /**
     * 是否為父類別
     */
    @Basic
    @Column(name = "ISPARENT")
    private Integer isParent;

    /**
     * 推播名稱
     */
    @Basic
    @Column(name = "NAME")
    private String name;

    /**
     * 顯示名稱
     */
    @Basic
    @Column(name = "NAME_SHORT")
    private String nameShort;

    /**
     * 優先序 0:default 1:即時 2:一般 3:大量批次
     */
    @Basic
    @Column(name = "PRIORITY")
    private Integer priority;

    /**
     * 推播代碼
     */
    @Basic
    @Column(name = "PUSH_CODE")
    private String pushCode;

    /**
     * 推播server 1:04P 2:05P 3:06P ,default 3
     */
    @Basic
    @Column(name = "PUSH_SERVER")
    private Integer pushServer;

    /**
     * 排序
     */
    @Basic
    @Column(name = "SORT")
    private String sort;

    /**
     * 0:一般會員 1:信用卡網路會員
     */
    @Basic
    @Column(name = "TYPE")
    private Integer type;

    /**
     * 版本號
     */
    @Basic
    @Column(name = "VERSION")
    private String version;

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version
     *            the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 取得類別
     * 
     * @return String 類別
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * 設定類別
     * 
     * @param category
     *            要設定的類別
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 取得啟用 0:未啟用 1:啟用
     * 
     * @return int 啟用 0:未啟用 1:啟用
     */
    public Integer getEnable() {
        return this.enable;
    }

    /**
     * 設定啟用 0:未啟用 1:啟用
     * 
     * @param enable
     *            要設定的啟用 0:未啟用 1:啟用
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    /**
     * 取得是否為群組層級 1:是 0:否
     * 
     * @return double 是否為群組層級 1:是 0:否
     */
    public Integer getIsGroup() {
        return this.isGroup;
    }

    /**
     * 設定是否為群組層級 1:是 0:否
     * 
     * @param isgroup
     *            要設定的是否為群組層級 1:是 0:否
     */
    public void setIsGroup(Integer isGroup) {
        this.isGroup = isGroup;
    }

    /**
     * 取得是否為父類別
     * 
     * @return int 是否為父類別
     */
    public Integer getIsParent() {
        return this.isParent;
    }

    /**
     * 設定是否為父類別
     * 
     * @param isparent
     *            要設定的是否為父類別
     */
    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }

    /**
     * 取得
     * 
     * @return String
     */
    public String getLocale() {
        return this.locale;
    }

    /**
     * 設定
     * 
     * @param locale
     *            要設定的
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * 取得推播名稱
     * 
     * @return String 推播名稱
     */
    public String getName() {
        return this.name;
    }

    /**
     * 設定推播名稱
     * 
     * @param name
     *            要設定的推播名稱
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 取得顯示名稱
     * 
     * @return String 顯示名稱
     */
    public String getNameShort() {
        return this.nameShort;
    }

    /**
     * 設定顯示名稱
     * 
     * @param nameShort
     *            要設定的顯示名稱
     */
    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    /**
     * 取得優先序 0:default 1:即時 2:一般 3:大量批次
     * 
     * @return int 優先序 0:default 1:即時 2:一般 3:大量批次
     */
    public Integer getPriority() {
        return this.priority;
    }

    /**
     * 設定優先序 0:default 1:即時 2:一般 3:大量批次
     * 
     * @param priority
     *            要設定的優先序 0:default 1:即時 2:一般 3:大量批次
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 取得推播鍵值
     * 
     * @return int 推播鍵值
     */
    public Integer getPushCategoryKey() {
        return this.pushCategoryKey;
    }

    /**
     * 設定推播鍵值
     * 
     * @param pushCategoryKey
     *            要設定的推播鍵值
     */
    public void setPushCategoryKey(Integer pushCategoryKey) {
        this.pushCategoryKey = pushCategoryKey;
    }

    /**
     * 取得推播代碼
     * 
     * @return String 推播代碼
     */
    public String getPushCode() {
        return this.pushCode;
    }

    /**
     * 設定推播代碼
     * 
     * @param pushCode
     *            要設定的推播代碼
     */
    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    /**
     * 取得推播server 1:04P 2:05P 3:06P ,default 3
     * 
     * @return int 推播server 1:04P 2:05P 3:06P ,default 3
     */
    public Integer getPushServer() {
        return this.pushServer;
    }

    /**
     * 設定推播server 1:04P 2:05P 3:06P ,default 3
     * 
     * @param pushServer
     *            要設定的推播server 1:04P 2:05P 3:06P ,default 3
     */
    public void setPushServer(Integer pushServer) {
        this.pushServer = pushServer;
    }

    /**
     * 取得排序
     * 
     * @return String 排序
     */
    public String getSort() {
        return this.sort;
    }

    /**
     * 設定排序
     * 
     * @param sort
     *            要設定的排序
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 取得0:一般會員 1:信用卡網路會員
     * 
     * @return int 0:一般會員 1:信用卡網路會員
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 設定0:一般會員 1:信用卡網路會員
     * 
     * @param type
     *            要設定的0:一般會員 1:信用卡網路會員
     */
    public void setType(Integer type) {
        this.type = type;
    }
}
