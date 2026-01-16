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
 * @(#)OperationGuideItemVo.java
 * 
 * <p>Description:操作教學項目</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan Wang (Duplicate From Channel Preference)
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class GuideItemVo {
    /** 資料鍵值 */
    private Integer itemKey;

    /** 教學項目 */
    private String guideDesc;

    /** 操作教學輔助項目資訊 */
    private List<GuideDetailItemVo> guideDetailItems;

    /**
     * @return the itemKey
     */
    public Integer getItemKey() {
        return itemKey;
    }

    /**
     * @param itemKey
     *            the itemKey to set
     */
    public void setItemKey(Integer itemKey) {
        this.itemKey = itemKey;
    }

    /**
     * @return the guideDesc
     */
    public String getGuideDesc() {
        return guideDesc;
    }

    /**
     * @param guideDesc
     *            the guideDesc to set
     */
    public void setGuideDesc(String guideDesc) {
        this.guideDesc = guideDesc;
    }

    /**
     * @return the guideDetailItems
     */
    public List<GuideDetailItemVo> getGuideDetailItems() {
        return guideDetailItems;
    }

    /**
     * @param guideDetailItems
     *            the guideDetailItems to set
     */
    public void setGuideDetailItems(List<GuideDetailItemVo> guideDetailItems) {
        this.guideDetailItems = guideDetailItems;
    }

}
