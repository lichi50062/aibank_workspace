/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

// @formatter:off
/**
 * @(#)Promotion.java
 * 
 * <p>Description:[優惠活動分類]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/16, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PromotionCategory {

    /** 類別 */
    private String category;

    /** 所屬母類別 */
    private String parentCategory;

    /** 類別名稱 */
    private String categoryName;

    /** 排序 */
    private Integer sortNo;

    /** 警語 */
    private String reminder;

    /** 預設類別 */
    private final Boolean isDefault = Boolean.FALSE;

    /** 其他來源分類資料 */
    private List<PromotionCategory> subCategories;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(String parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public List<PromotionCategory> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<PromotionCategory> subCategories) {
        this.subCategories = subCategories;
    }
}
