package com.tfb.aibank.chl.system.ot014.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NSTOT014010Rs.java
 * 
 * <p>Description:投資風險偏好輔助說明 010 投資風險偏好輔助說明 010</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/08, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT014010Rs implements RsData {
    /** 投資風險 */
    private String risk;
    /** 投資風險名稱 */
    private String riskTitle;
    /** 投資風險說明 */
    private String riskDesc;
    /** 有效期限至 */
    private String effectiveDate;
    /** 前次測試日 */
    private String testDate;
    /** 冷靜期 */
    private String coolingDate;
    /** 非特定客戶之高資產客戶 */
    private int highAsset;
    /** 次數 */
    private int counts;

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getCoolingDate() {
        return coolingDate;
    }

    public void setCoolingDate(String coolingDate) {
        this.coolingDate = coolingDate;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    public String getRiskDesc() {
        return riskDesc;
    }

    public void setRiskDesc(String riskDesc) {
        this.riskDesc = riskDesc;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }

    public int getHighAsset() {
        return highAsset;
    }

    public void setHighAsset(int highAsset) {
        this.highAsset = highAsset;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }
}
