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
package com.tfb.aibank.chl.general.resource.vo;

//@formatter:off
/**
 * @(#)MenuForHandShakeVo.java
 * 
 * <p>Description: Menu資訊 from NST_OT004_010</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Marty
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class MenuForHandShakeVo {

    /**
     * 選單分類
     */
    private String category;

    /**
     * icon style class name
     */
    private String iconClass;

    /**
     * 交易功能代碼
     */
    private String taskId;

    /**
     * 選單名稱
     */
    private String menuName;

    /**
     * 是否顯示於主選單
     */
    private int isDisplay;

    /**
     * 是否為最底層
     */
    private int isRef;

    /**
     * 選單代碼
     */
    private String menuId;

    /**
     * 選單的版本號
     */
    private String menuVer;

    /**
     * 選單順序
     */
    private int orderNo;

    /**
     * 上一層選單代碼
     */
    private String parentMenuId;

    /**
     * 可以被搜尋
     */
    private int canBeSearch;
    /**
     * MENU連結類型
     */
    private int linkType;
    /**
     * 開新視窗參數
     */
    private String linkParam;
    /**
     * 是否為新功能(0：否1：是)
     */
    private int isNew;

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

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
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


    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

}
