package com.tfb.aibank.chl.general.qu003.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.general.resource.dto.RewardsProduct;

// @formatter:off
/**
 * @(#)NGNQU003050Rs.java
 * 
 * <p>Description:優惠 050 點數兌換頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/14, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU003050Rs implements RsData {
    // 熱門紅利兌換
    private List<RewardsProduct> bonusPoints;
    // 熱門哩程兌換
    private List<RewardsProduct> mileagePoints;
    // 熱門福華點兌換
    private List<RewardsProduct> fuhuaPoints;
    // 熱門紅利
    private NGNQU003050RsData bonus;
    // 熱門哩程
    private NGNQU003050RsData mileage;
    // 熱門福華
    private NGNQU003050RsData fuhua;

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

    public NGNQU003050RsData getBonus() {
        return bonus;
    }

    public void setBonus(NGNQU003050RsData bonus) {
        this.bonus = bonus;
    }

    public NGNQU003050RsData getMileage() {
        return mileage;
    }

    public void setMileage(NGNQU003050RsData mileage) {
        this.mileage = mileage;
    }

    public NGNQU003050RsData getFuhua() {
        return fuhua;
    }

    public void setFuhua(NGNQU003050RsData fuhua) {
        this.fuhua = fuhua;
    }
}
