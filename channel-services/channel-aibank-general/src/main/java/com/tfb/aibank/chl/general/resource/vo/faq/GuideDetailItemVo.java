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
package com.tfb.aibank.chl.general.resource.vo.faq;

// @formatter:off
/**
 * @(#)GuideDetailItemVo.java
 * 
 * <p>Description:操作教學輔助項目資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan Wang	(Duplicate From Channel Preference)
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GuideDetailItemVo {
    /** 教學項目名稱 */
    private String itemDesc;

    /** 步驟圖片 */
    private String picPath;

    /** 步驟標號 */
    private String detailIndex;

    /** 步驟名稱 */
    private String detailName;

    /** 步驟內容 */
    private String detailDesc;

    /** 按鈕名稱 */
    private String btnName;

    /** 按鈕目標交易 */
    private String btnTaskId;

    /**
     * @return the itemDesc
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * @param itemDesc
     *            the itemDesc to set
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * @return the picPath
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * @param picPath
     *            the picPath to set
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    /**
     * @return the detailIndex
     */
    public String getDetailIndex() {
        return detailIndex;
    }

    /**
     * @param detailIndex
     *            the detailIndex to set
     */
    public void setDetailIndex(String detailIndex) {
        this.detailIndex = detailIndex;
    }

    /**
     * @return the detailName
     */
    public String getDetailName() {
        return detailName;
    }

    /**
     * @param detailName
     *            the detailName to set
     */
    public void setDetailName(String detailName) {
        this.detailName = detailName;
    }

    /**
     * @return the detailDesc
     */
    public String getDetailDesc() {
        return detailDesc;
    }

    /**
     * @param detailDesc
     *            the detailDesc to set
     */
    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    /**
     * @return the btnName
     */
    public String getBtnName() {
        return btnName;
    }

    /**
     * @param btnName
     *            the btnName to set
     */
    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    /**
     * @return the btnTaskId
     */
    public String getBtnTaskId() {
        return btnTaskId;
    }

    /**
     * @param btnTaskId
     *            the btnTaskId to set
     */
    public void setBtnTaskId(String btnTaskId) {
        this.btnTaskId = btnTaskId;
    }

}
