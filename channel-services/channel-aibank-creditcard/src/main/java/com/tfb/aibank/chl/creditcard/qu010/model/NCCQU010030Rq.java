package com.tfb.aibank.chl.creditcard.qu010.model;

import com.ibm.tw.ibmb.base.model.RqData;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCQU010030Rq.java
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
public class NCCQU010030Rq implements RqData {

    /** 指定消費類別 */
    private String categoryGroup;

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
