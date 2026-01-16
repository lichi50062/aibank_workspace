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

import java.util.List;

// @formatter:off
/**
 * @(#)GuideTypeVo.java
 * 
 * <p>Description:教學類別Vo</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan Wang (Duplicate From Channel Preference)
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class GuideTypeVo {
    /** 交易類型名稱 */
    private String txTypeName;

    /** 交易類型圖示 */
    private String iconPath;

    /** 操作教學項目 */
    private List<GuideItemVo> guideItems;

    /**
     * @return the txTypeName
     */
    public String getTxTypeName() {
        return txTypeName;
    }

    /**
     * @param txTypeName
     *            the txTypeName to set
     */
    public void setTxTypeName(String txTypeName) {
        this.txTypeName = txTypeName;
    }

    /**
     * @return the iconPath
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * @param iconPath
     *            the iconPath to set
     */
    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    /**
     * @return the guideItems
     */
    public List<GuideItemVo> getGuideItems() {
        return guideItems;
    }

    /**
     * @param guideItems
     *            the guideItems to set
     */
    public void setGuideItems(List<GuideItemVo> guideItems) {
        this.guideItems = guideItems;
    }

}
