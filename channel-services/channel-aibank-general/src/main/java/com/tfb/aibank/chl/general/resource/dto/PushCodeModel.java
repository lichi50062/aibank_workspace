package com.tfb.aibank.chl.general.resource.dto;

// @formatter:off
/**
 * @(#)PushCodeModel.java
 *
 * <p>Description:[推播代碼]</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/08/22, eddy
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PushCodeModel {

    /** 推播類型 */
    private String category;

    /** 推播代碼 */
    private String pushCode;

    /** 顯示名稱 */
    private String name;

    /** 是否為主要項目 */
    private boolean isParent;

    /** 是否已設定 */
    private boolean isSetApp;

    /** 是否不可選 */
    private boolean isDisabled;

    /** 是否已設定 Line */
    private boolean isSetLine;

    /** 是否不可選 Line */
    private boolean isDisabledLine;

    /** 是否顯示 Line checkbox */
    private boolean isShowLine = false;

    /** 推播時段設定 */
    private int notifyPass;

    /**
     * @return the notifyPass
     */
    public int getNotifyPass() {
        return notifyPass;
    }

    /**
     * @return the isDisable
     */
    public boolean isDisabled() {
        return isDisabled;
    }

    /**
     * @param isDisabled
     *            the isDisable to set
     */
    public void setDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    /**
     * @return the isParent
     */
    public boolean isParent() {
        return isParent;
    }

    /**
     * @param isParent
     *            the isParent to set
     */
    public void setParent(boolean isParent) {
        this.isParent = isParent;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the pushCode
     */
    public String getPushCode() {
        return pushCode;
    }

    /**
     * @param pushCode
     *            the pushCode to set
     */
    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the isSetApp
     */
    public boolean isSetApp() {
        return isSetApp;
    }

    /**
     * @param isSetApp
     *            the isSetApp to set
     */
    public void setSetApp(boolean isSetApp) {
        this.isSetApp = isSetApp;
    }

    /**
     * @return the isSetLine
     */
    public boolean isSetLine() {
        return isSetLine;
    }

    /**
     * @param isSetLine
     *            the isSetLine to set
     */
    public void setSetLine(boolean isSetLine) {
        this.isSetLine = isSetLine;
    }

    /**
     * @return the isDisabledLine
     */
    public boolean isDisabledLine() {
        return isDisabledLine;
    }

    /**
     * @param isDisabledLine
     *            the isDisabledLine to set
     */
    public void setDisabledLine(boolean isDisabledLine) {
        this.isDisabledLine = isDisabledLine;
    }

    /**
     * @return the isShowLine
     */
    public boolean isShowLine() {
        return isShowLine;
    }

    /**
     * @param isShowLine
     *            the isShowLine to set
     */
    public void setShowLine(boolean isShowLine) {
        this.isShowLine = isShowLine;
    }

}
