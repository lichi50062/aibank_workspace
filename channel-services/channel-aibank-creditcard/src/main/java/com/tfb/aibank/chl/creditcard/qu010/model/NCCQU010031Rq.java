package com.tfb.aibank.chl.creditcard.qu010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU010031Rq.java
 * 
 * <p>Description:消費分析 030 類別分析頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU010031Rq implements RqData {

    /** 指定分析月 */
    private String selectedYearMonth;

    /** categoryGroup */
    private String categoryGroup;

    /** 指定項目類別 */
    private String itemCategory;

    /**
     * @return the selectedYearMonth
     */
    public String getSelectedYearMonth() {
        return selectedYearMonth;
    }

    /**
     * @param selectedYearMonth
     *            the selectedYearMonth to set
     */
    public void setSelectedYearMonth(String selectedYearMonth) {
        this.selectedYearMonth = selectedYearMonth;
    }

    /**
     * @return the itemCategory
     */
    public String getItemCategory() {
        return itemCategory;
    }

    /**
     * @param itemCategory
     *            the itemCategory to set
     */
    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * @return the categoryGroup
     */
    public String getCategoryGroup() {
        return categoryGroup;
    }

    /**
     * @param categoryGroup
     *            the categoryGroup to set
     */
    public void setCategoryGroup(String categoryGroup) {
        this.categoryGroup = categoryGroup;
    }

}
