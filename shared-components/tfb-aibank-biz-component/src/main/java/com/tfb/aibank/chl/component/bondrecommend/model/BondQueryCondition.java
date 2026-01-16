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
package com.tfb.aibank.chl.component.bondrecommend.model;

import java.math.BigDecimal;
import java.util.List;

// @formatter:off
/**
 * @(#)BondQueryCondition.java
 * 
 * <p>Description:債券好神選 查詢條件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/18, Lyon Xie
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondQueryCondition {

    /** 語系 */
    private String locale;

    /** 債券類別 */
    private List<String> bondKinds;
    /** 商品代號 */
    private List<String> bondNos;
    /** 商品名稱 */
    private String bondFName;
    /** 商品幣別 */
    private List<String> bondCcys;
    /** 產業類別 */
    private List<String> industrialTypes;
    /** 票面利率 */
    private List<String> faceIntRates;
    /** 票面利率-自行輸入(上限) */
    private BigDecimal faceIntRateUpper;
    /** 票面利率-自行輸入(下限) */
    private BigDecimal faceIntRateLower;
    /** 配息頻率 */
    private List<String> payFreqs;
    /** 配息頻率(2次：半年領息) */
    private String divMonth;
    /** 到期/提前買回殖利率 */
    private List<String> ytmytcs;
    /** 到期/提前買回殖利率-自行輸入(上限) */
    private BigDecimal ytmytcUpper;
    /** 到期/提前買回殖利率-自行輸入(下限) */
    private BigDecimal ytmytcLower;
    /** 剩餘年期 */
    private List<String> endTimes;
    /** 剩餘年期-自行輸入(上限) */
    private BigDecimal endTimeUpper;
    /** 剩餘年期-自行輸入(下限) */
    private BigDecimal endTimeLower;
    /** 信用評等 */
    private List<String> creditRatings;
    /** 最低申購面額 */
    private List<String> miniBuyAmts;
    /** 商品風險等級 */
    private List<String> ramRiskTypes;
    /** 群組代碼 **/
    private List<String> groupIds;
    
    /** 是否查詢可申購產品*/
    private boolean isSubscribeBond;
    
    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<String> getBondKinds() {
        return bondKinds;
    }

    public void setBondKinds(List<String> bondKinds) {
        this.bondKinds = bondKinds;
    }

    public List<String> getBondNos() {
        return bondNos;
    }

    public void setBondNos(List<String> bondNos) {
        this.bondNos = bondNos;
    }

    public String getBondFName() {
        return bondFName;
    }

    public void setBondFName(String bondFName) {
        this.bondFName = bondFName;
    }

    public List<String> getBondCcys() {
        return bondCcys;
    }

    public void setBondCcys(List<String> bondCcys) {
        this.bondCcys = bondCcys;
    }

    public List<String> getIndustrialTypes() {
        return industrialTypes;
    }

    public void setIndustrialTypes(List<String> industrialTypes) {
        this.industrialTypes = industrialTypes;
    }

    public BigDecimal getFaceIntRateUpper() {
        return faceIntRateUpper;
    }

    public void setFaceIntRateUpper(BigDecimal faceIntRateUpper) {
        this.faceIntRateUpper = faceIntRateUpper;
    }

    public BigDecimal getFaceIntRateLower() {
        return faceIntRateLower;
    }

    public void setFaceIntRateLower(BigDecimal faceIntRateLower) {
        this.faceIntRateLower = faceIntRateLower;
    }

    public List<String> getPayFreqs() {
        return payFreqs;
    }

    public void setPayFreqs(List<String> payFreqs) {
        this.payFreqs = payFreqs;
    }

    public String getDivMonth() {
        return divMonth;
    }

    public void setDivMonth(String divMonth) {
        this.divMonth = divMonth;
    }

    public BigDecimal getYtmytcUpper() {
        return ytmytcUpper;
    }

    public void setYtmytcUpper(BigDecimal ytmytcUpper) {
        this.ytmytcUpper = ytmytcUpper;
    }

    public BigDecimal getYtmytcLower() {
        return ytmytcLower;
    }

    public void setYtmytcLower(BigDecimal ytmytcLower) {
        this.ytmytcLower = ytmytcLower;
    }

    public BigDecimal getEndTimeUpper() {
        return endTimeUpper;
    }

    public void setEndTimeUpper(BigDecimal endTimeUpper) {
        this.endTimeUpper = endTimeUpper;
    }

    public BigDecimal getEndTimeLower() {
        return endTimeLower;
    }

    public void setEndTimeLower(BigDecimal endTimeLower) {
        this.endTimeLower = endTimeLower;
    }

    public List<String> getCreditRatings() {
        return creditRatings;
    }

    public void setCreditRatings(List<String> creditRatings) {
        this.creditRatings = creditRatings;
    }

    public List<String> getRamRiskTypes() {
        return ramRiskTypes;
    }

    public void setRamRiskTypes(List<String> ramRiskTypes) {
        this.ramRiskTypes = ramRiskTypes;
    }

    public List<String> getFaceIntRates() {
        return faceIntRates;
    }

    public void setFaceIntRates(List<String> faceIntRates) {
        this.faceIntRates = faceIntRates;
    }

    public List<String> getYtmytcs() {
        return ytmytcs;
    }

    public void setYtmytcs(List<String> ytmytcs) {
        this.ytmytcs = ytmytcs;
    }

    public List<String> getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(List<String> endTimes) {
        this.endTimes = endTimes;
    }

    public List<String> getMiniBuyAmts() {
        return miniBuyAmts;
    }

    public void setMiniBuyAmts(List<String> miniBuyAmts) {
        this.miniBuyAmts = miniBuyAmts;
    }

	public List<String> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}

	public boolean isSubscribeBond() {
		return isSubscribeBond;
	}

	public void setSubscribeBond(boolean isSubscribeBond) {
		this.isSubscribeBond = isSubscribeBond;
	}
}
