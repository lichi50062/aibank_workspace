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
 * @(#)FaqTypeVo.java
 * 
 * <p>Description:常見問題項目大類 (Duplicate From Channel Preference)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan Wang (Duplicate From Channel Preference)
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FaqTypeVo {

    /** 類別圖示 */
    private String iconPath;

    /** 類別名稱 */
    private String txType;

    /** 常見問題大類相關項目 */
    private List<FaqItemVo> faqItems;

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
     * @return the txType
     */
    public String getTxType() {
        return txType;
    }

    /**
     * @param txType
     *            the txType to set
     */
    public void setTxType(String txType) {
        this.txType = txType;
    }

    /**
     * @return the faqItems
     */
    public List<FaqItemVo> getFaqItems() {
        return faqItems;
    }

    /**
     * @param faqItems
     *            the faqItems to set
     */
    public void setFaqItems(List<FaqItemVo> faqItems) {
        this.faqItems = faqItems;
    }

}
