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

// @formatter:off
/**
 * @(#)RewardsProducts.java
 * 
 * <p>Description:點數兌換商品</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/25, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class RewardsProduct {

    /**
     * 活動標題
     */
    private String title;
    /**
     * 商品圖片
     */
    private String picture;
    /**
     * 點數
     */
    private Integer points;

    /**
     * 新上架
     */
    private boolean isNew;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }
}
