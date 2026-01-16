package com.tfb.aibank.biz.systemconfig.services.menu.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "選單")
public class MenuModel implements Serializable {

    private static final long serialVersionUID = 808096642956136297L;

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
    @Schema(description = "CAN_BE_SEARCH")
    private int canBeSearch;
    /**
     * MENU連結類型
     */
    @Schema(description = "LINK_TYPE")
    private int linkType;
    /**
     * 開新視窗參數
     */
    @Schema(description = "LINK_PARAM")
    private String linkParam;
    /**
     * MENU_DESC
     */
    @Schema(description = "MENU_DESC")
    private String menuDesc;

    /**
     * 是否為新功能(0：否1：是)
     */
    @Schema(description = "IS_NEW")
    private int isNew;

    /**
     * 是否可使用交易功能(0：否1：是)
     */
    private int isOpen;

    /**
     * 取得選單分類
     * 
     * @return String 選單分類
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * 設定選單分類
     * 
     * @param category
     *            要設定的選單分類
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 取得icon style class name
     * 
     * @return String icon style class name
     */
    public String getIconClass() {
        return this.iconClass;
    }

    /**
     * 設定icon style class name
     * 
     * @param iconClass
     *            要設定的icon style class name
     */
    public void setIconClass(String iconClass) {
        this.iconClass = iconClass;
    }

    /**
     * 取得是否顯示於主選單
     * 
     * @return int 是否顯示於主選單
     */
    public int getIsDisplay() {
        return this.isDisplay;
    }

    /**
     * 設定是否顯示於主選單
     * 
     * @param isDisplay
     *            要設定的是否顯示於主選單
     */
    public void setIsDisplay(int isDisplay) {
        this.isDisplay = isDisplay;
    }

    /**
     * 取得是否為最底層
     * 
     * @return int 是否為最底層
     */
    public int getIsRef() {
        return this.isRef;
    }

    /**
     * 設定是否為最底層
     * 
     * @param isRef
     *            要設定的是否為最底層
     */
    public void setIsRef(int isRef) {
        this.isRef = isRef;
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
     * 取得選單的版本號
     * 
     * @return String 選單的版本號
     */
    public String getMenuVer() {
        return this.menuVer;
    }

    /**
     * 設定選單的版本號
     * 
     * @param menuVer
     *            要設定的選單的版本號
     */
    public void setMenuVer(String menuVer) {
        this.menuVer = menuVer;
    }

    /**
     * 取得選單順序
     * 
     * @return int 選單順序
     */
    public int getOrderNo() {
        return this.orderNo;
    }

    /**
     * 設定選單順序
     * 
     * @param orderNo
     *            要設定的選單順序
     */
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 取得上一層選單代碼
     * 
     * @return String 上一層選單代碼
     */
    public String getParentMenuId() {
        return this.parentMenuId;
    }

    /**
     * 設定上一層選單代碼
     * 
     * @param parentMenuId
     *            要設定的上一層選單代碼
     */
    public void setParentMenuId(String parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    /**
     * 取得交易功能代碼
     * 
     * @return String 交易功能代碼
     */
    public String getTaskId() {
        return this.taskId;
    }

    /**
     * 設定交易功能代碼
     * 
     * @param taskId
     *            要設定的交易功能代碼
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
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

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

}
