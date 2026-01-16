package com.tfb.aibank.chl.general.qu001.model;

import java.io.Serializable;
import java.util.List;

// @formatter:off
/**
 * @(#)MenuVo.java
 *
 * <p>Description: MenuVO</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[常用功能資料VO]</li>
 * </ol>
 */
//@formatter:on
public class MenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 業務大類 menuId
     */
    private String bizCateMenuId;
    /**
     * 業務大類 menu name
     */
    private String bizCateName;

    /**
     * 功能群組 menuId
     */
    private String functionGroupMenuId;

    /**
     * 功能群組 menu name
     */
    private String functionGroupMenuName;

    /**
     * 功能群組 menu iconClass
     */
    private String functionGroupIconClass;

    private int functionNode;

    /**
     * 選單分類
     */
    private String category;

    /**
     * icon style class name
     */
    private String iconClass;

    /**
     * 是否顯示於主選單
     */
    private int isDisplay;

    /**
     * 最底層標籤 0 => 最後一層 1 => 此 node(功能節點) 下有「分類」
     */
    private int nodeFlag;

    /**
     * 選單代碼
     */
    private String menuId;

    /**
     * 選單顯示名稱
     */
    private String menuName;

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
     * 上一層選單名稱
     */
    private String parentMenuName;

    /**
     * 交易功能代碼
     */
    private String taskId;

    /**
     * MENU連結類型
     */
    private int linkType;
    /**
     * 開新視窗參數
     */
    private String linkParam;

    /**
     * 此點為分類，下有細項menus
     */
    private List<MenuVo> nodeMenus;

    private List<String> keywords;

    /**
     * 是否為新功能(0：否1：是)
     */
    private int isNew;

    /**
     * 是否可使用交易功能(0：否1：是)
     */
    private int isOpen;

    public String getBizCateMenuId() {
        return bizCateMenuId;
    }

    public void setBizCateMenuId(String bizCateMenuId) {
        this.bizCateMenuId = bizCateMenuId;
    }

    public String getBizCateName() {
        return bizCateName;
    }

    public void setBizCateName(String bizCateName) {
        this.bizCateName = bizCateName;
    }

    public String getFunctionGroupMenuId() {
        return functionGroupMenuId;
    }

    public void setFunctionGroupMenuId(String functionGroupMenuId) {
        this.functionGroupMenuId = functionGroupMenuId;
    }

    public String getFunctionGroupMenuName() {
        return functionGroupMenuName;
    }

    public void setFunctionGroupMenuName(String functionGroupMenuName) {
        this.functionGroupMenuName = functionGroupMenuName;
    }

    public int getFunctionNode() {
        return functionNode;
    }

    public void setFunctionNode(int functionNode) {
        this.functionNode = functionNode;
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

    public int getNodeFlag() {
        return nodeFlag;
    }

    public void setNodeFlag(int nodeFlag) {
        this.nodeFlag = nodeFlag;
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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public List<MenuVo> getNodeMenus() {
        return nodeMenus;
    }

    public void setNodeMenus(List<MenuVo> nodeMenus) {
        this.nodeMenus = nodeMenus;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
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

    public String getFunctionGroupIconClass() {
        return functionGroupIconClass;
    }

    public void setFunctionGroupIconClass(String functionGroupIconClass) {
        this.functionGroupIconClass = functionGroupIconClass;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

}
