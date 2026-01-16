package com.tfb.aibank.chl.general.qu006.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.ibm.tw.ibmb.component.menu.Menu;

// @formatter:off
/**
 * @(#)NGNQU006Menu.java
 * 
 * <p>Description:選單物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NGNQU006Data {

    public NGNQU006Data() {
        // default constructor
    }

    public NGNQU006Data(Menu menu, Locale locale) {
        this.menuId = menu.getMenuId();
        this.menuName = menu.getMenuName(locale.toString());
        this.taskNo = menu.getTaskId();
    }

    public NGNQU006Data(String menuId, String menuName, String taskNo) {
        this.menuId = menuId;
        this.menuName = menuName;
        this.taskNo = taskNo;
    }

    /** 選單代碼 */
    private String menuId;
    /** 選單名稱 */
    private String menuName;
    /** 交易代號 */
    private String taskNo;
    /** 是否為根選單 */
    private boolean isRoot;
    /** 狀態描述 */
    private String statusDesc;
    /** 子選單 */
    private List<NGNQU006Data> subMenus = new ArrayList<>();

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean isRoot) {
        this.isRoot = isRoot;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<NGNQU006Data> getSubMenus() {
        return subMenus;
    }

    public void setSubMenus(List<NGNQU006Data> subMenus) {
        this.subMenus = subMenus;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

}
