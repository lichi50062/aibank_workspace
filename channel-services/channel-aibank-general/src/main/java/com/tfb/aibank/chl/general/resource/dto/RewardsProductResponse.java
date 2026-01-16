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
 * @(#)RewardsProductResponse.java
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

public class RewardsProductResponse {
    // 熱門紅利兌換
    private List<RewardsProduct> bonusPoints;
    // 熱門哩程兌換
    private List<RewardsProduct> mileagePoints;
    // 熱門福華點兌換
    private List<RewardsProduct> fuhuaPoints;

    public List<RewardsProduct> getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(List<RewardsProduct> bonusPoints) {
        this.bonusPoints = bonusPoints;
    }

    public List<RewardsProduct> getMileagePoints() {
        return mileagePoints;
    }

    public void setMileagePoints(List<RewardsProduct> mileagePoints) {
        this.mileagePoints = mileagePoints;
    }

    public List<RewardsProduct> getFuhuaPoints() {
        return fuhuaPoints;
    }

    public void setFuhuaPoints(List<RewardsProduct> fuhuaPoints) {
        this.fuhuaPoints = fuhuaPoints;
    }
}
