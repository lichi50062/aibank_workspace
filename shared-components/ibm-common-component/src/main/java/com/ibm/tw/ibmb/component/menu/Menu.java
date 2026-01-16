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
package com.ibm.tw.ibmb.component.menu;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.ibm.tw.commons.util.StringUtils;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;

/**
 * 
 * Menu 選單物件
 * 
 * @author horance
 */
@Schema(description = "選單")
public class Menu implements Serializable {

    private static final long serialVersionUID = 8182201146485085718L;

    /**
     * 選單分類
     */
    @Schema(description = "選單分類")
    private String category;

    /**
     * icon style class name
     */
    @Schema(description = "icon style class name")
    private String iconClass;

    /**
     * 是否顯示於主選單
     */
    @Schema(description = "是否顯示於主選單")
    private int isDisplay;

    /**
     * 是否為最底層
     */
    @Schema(description = "是否為最底層")
    private int isRef;

    /**
     * 選單代碼
     */
    @Schema(description = "選單代碼")
    private String menuId;

    /**
     * 選單的版本號
     */
    @Schema(description = "選單的版本號")
    private String menuVer;

    /**
     * 選單順序
     */
    @Schema(description = "選單順序")
    private int orderNo;

    /**
     * 上一層選單代碼
     */
    @Schema(description = "上一層選單代碼")
    private String parentMenuId;

    /**
     * 交易功能代碼
     */
    @Schema(description = "交易功能代碼")
    private String taskId;

    /**
     * 可以被搜尋
     */
    @Column(name = "CAN_BE_SEARCH")
    private int canBeSearch;
    /**
     * MENU連結類型
     */
    @Column(name = "LINK_TYPE")
    private int linkType;
    /**
     * 開新視窗參數
     */
    @Column(name = "LINK_PARAM")
    private String linkParam;
    /**
     * MENU_DESC
     */
    @Column(name = "MENU_DESC")
    private String menuDesc;

    /**
     * 是否為新功能(0：否1：是)
     */
    private int isNew;

    /**
     * 是否可使用交易功能(0：否1：是)
     */
    private int isOpen;

    /**
     * 選單名稱 Map<String, String>，key:locale，value:name
     */
    @Schema(description = "選單名稱")
    private Map<String, String> menuNameMap = new HashMap<>();

    /**
     * 選單名稱 Map<String, String>，key:locale，value:name
     */
    @Schema(description = "上一層選單選單名稱")
    private Map<String, String> parentMenuNameMap = new HashMap<>();

    /**
     * 選單名稱
     * 
     * @param userLocale
     * @return
     */
    public String getMenuName(String userLocale) {
        if (StringUtils.isBlank(userLocale)) {
            return getMenuCName();
        }
        return this.menuNameMap.get(userLocale);
    }

    /**
     * (繁中)選單名稱
     * 
     * @return
     */
    public String getMenuCName() {
        return this.menuNameMap.get(Locale.TAIWAN.toString());
    }

    /**
     * (英文)選單名稱
     * 
     * @return
     */
    public String getMenuEName() {
        return this.menuNameMap.get(Locale.US.toString());
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    public int getIsDisplay() {
        return isDisplay;
    }

    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    public int getIsRef() {
        return isRef;
    }

    public void setIsRef(int isRef) {
        this.isRef = isRef;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuVer() {
        return menuVer;
    }

    public void setMenuVer(String menuVer) {
        this.menuVer = menuVer;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Map<String, String> getMenuNameMap() {
        return menuNameMap;
    }

    public void setMenuNameMap(Map<String, String> menuNameMap) {
        this.menuNameMap = menuNameMap;
    }

    public Map<String, String> getParentMenuNameMap() {
        return parentMenuNameMap;
    }

    public void setParentMenuNameMap(Map<String, String> parentMenuNameMap) {
        this.parentMenuNameMap = parentMenuNameMap;
    }

    /**
     * 上一層選單選單名稱
     *
     * @param userLocale
     * @return
     */
    public String getParentMenuName(String userLocale) {
        if (StringUtils.isBlank(userLocale)) {
            return getParentMenuCName();
        }
        return null != this.parentMenuNameMap ? this.parentMenuNameMap.get(userLocale) : StringUtils.EMPTY;
    }

    /**
     * (繁中)上一層選單選單名稱
     *
     * @return
     */
    public String getParentMenuCName() {
        return this.parentMenuNameMap.get(Locale.TAIWAN.toString());
    }

    /**
     * (英文)上一層選單選單名稱
     *
     * @return
     */
    public String getParentMenuEName() {
        return this.parentMenuNameMap.get(Locale.US.toString());
    }

    public int getCanBeSearch() {
        return canBeSearch;
    }

    public void setCanBeSearch(int canBeSearch) {
        this.canBeSearch = canBeSearch;
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

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    @Override
    public String toString() {
        return "Menu{" + "category='" + category + '\'' + ", iconClass='" + iconClass + '\'' + ", isDisplay=" + isDisplay + ", isRef=" + isRef + ", menuId='" + menuId + '\'' + ", menuVer='" + menuVer + '\'' + ", orderNo=" + orderNo + ", parentMenuId='" + parentMenuId + '\'' + ", taskId='" + taskId + '\'' + ", canBeSearch=" + canBeSearch + ", linkType=" + linkType + ", linkParam='" + linkParam + '\'' + ", menuDesc='" + menuDesc + '\'' + ", menuNameMap=" + menuNameMap + ", parentMenuNameMap=" + parentMenuNameMap + '}';
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

}
