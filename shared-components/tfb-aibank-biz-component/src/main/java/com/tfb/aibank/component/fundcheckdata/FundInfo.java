package com.tfb.aibank.component.fundcheckdata;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

//@formatter:off
/**
 * @(#)FundInfoBase.java
 *
 * <p>Description: 基金資料 </p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/2/1, MP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class FundInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fundCode;

    /**
     * 基金公司代號
     */
    private String orgFundCode;

    /**
     * 基金代號
     */
    private String subFundCode;

    private String locale;

    /**
     * "基金名稱"
     */
    private String fundName;

    /**
     * 是否為目標到期型基金
     */
    private String targetFund;

    /**
     * "基金警語"
     */
    private String fundAlert;
    /**
     * "參考淨值"
     */
    private BigDecimal refValue;
    /**
     * "報酬率"
     */
    private BigDecimal returnRate;
    /**
     * "淨值日期"
     */
    private Date assetValueDate;
    /**
     * "商品風險屬性TAG"
     */
    private String productRisk;
    /**
     * "主題關鍵字TAG1"
     */
    private String titleKeyTag1;
    /**
     * "主題關鍵字TAG2"
     */
    private String titleKeyTag2;
    /**
     * "優惠關鍵字TAG"
     */
    private String discountKeyTag;

    private String fundType;
    /**
     * 資產風險別P1：微P2：低P3：中P4：高
     */
    private String ramType;

    private String currencyCode;

    /**
     * 新興市場之非投資等級債券基金
     */
    private String emerging;

    /**
     * 基金種類 1:一次 2:小額 3:全部
     */
    private String fundKind;

    /** 可否承做臺幣信託 ‘’:可以 N:不可以 */
    private String twdTrust;

    /**
     * 定期不定額Flag
     */
    private String nfAmtFlag;

    /**
     * 變更Flag
     */
    private String changeFlag;

    /**
     * 國內基金外幣計價 Y:是 ‘’:不是
     */
    private String fundValuation;

    /**
     * 地區
     */
    private String region;

    /**
     * 是否為目標到期型基金
     */
    private String isTarget;

    /** 整理資料後，是否可移除的標記 */
    private boolean itemToRemoveMark = false;

    public FundInfo() {

    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getOrgFundCode() {
        return orgFundCode;
    }

    public void setOrgFundCode(String orgFundCode) {
        this.orgFundCode = orgFundCode;
    }

    public String getSubFundCode() {
        return subFundCode;
    }

    public void setSubFundCode(String subFundCode) {
        this.subFundCode = subFundCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getFundAlert() {
        return fundAlert;
    }

    public void setFundAlert(String fundAlert) {
        this.fundAlert = fundAlert;
    }

    public BigDecimal getRefValue() {
        return refValue;
    }

    public void setRefValue(BigDecimal refValue) {
        this.refValue = refValue;
    }

    public BigDecimal getReturnRate() {
        return returnRate;
    }

    public void setReturnRate(BigDecimal returnRate) {
        this.returnRate = returnRate;
    }

    public Date getAssetValueDate() {
        return assetValueDate;
    }

    public void setAssetValueDate(Date assetValueDate) {
        this.assetValueDate = assetValueDate;
    }

    public String getProductRisk() {
        return productRisk;
    }

    public void setProductRisk(String productRisk) {
        this.productRisk = productRisk;
    }

    public String getTitleKeyTag1() {
        return titleKeyTag1;
    }

    public void setTitleKeyTag1(String titleKeyTag1) {
        this.titleKeyTag1 = titleKeyTag1;
    }

    public String getTitleKeyTag2() {
        return titleKeyTag2;
    }

    public void setTitleKeyTag2(String titleKeyTag2) {
        this.titleKeyTag2 = titleKeyTag2;
    }

    public String getDiscountKeyTag() {
        return discountKeyTag;
    }

    public void setDiscountKeyTag(String discountKeyTag) {
        this.discountKeyTag = discountKeyTag;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getTargetFund() {
        return targetFund;
    }

    public void setTargetFund(String targetFund) {
        this.targetFund = targetFund;
    }

    public boolean isItemToRemoveMark() {
        return itemToRemoveMark;
    }

    public void setItemToRemoveMark(boolean itemToRemoveMark) {
        this.itemToRemoveMark = itemToRemoveMark;
    }

    public String getEmerging() {
        return emerging;
    }

    public void setEmerging(String emerging) {
        this.emerging = emerging;
    }

    public String getNfAmtFlag() {
        return nfAmtFlag;
    }

    public void setNfAmtFlag(String nfAmtFlag) {
        this.nfAmtFlag = nfAmtFlag;
    }

    public String getFundKind() {
        return fundKind;
    }

    public void setFundKind(String fundKind) {
        this.fundKind = fundKind;
    }

    public String getTwdTrust() {
        return twdTrust;
    }

    public void setTwdTrust(String twdTrust) {
        this.twdTrust = twdTrust;
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }

    public String getFundValuation() {
        return fundValuation;
    }

    public void setFundValuation(String fundValuation) {
        this.fundValuation = fundValuation;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIsTarget() {
        return isTarget;
    }

    public void setIsTarget(String isTarget) {
        this.isTarget = isTarget;
    }
}
