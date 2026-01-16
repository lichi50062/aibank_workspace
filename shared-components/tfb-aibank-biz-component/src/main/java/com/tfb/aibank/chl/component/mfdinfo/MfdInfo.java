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
package com.tfb.aibank.chl.component.mfdinfo;

import java.util.Date;

// @formatter:off
/**
 * @(#)MfdInfo.java
 * 
 * <p>Description:基金資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MfdInfo {

    /** 語系 */
    private String locale;

    /** 基金公司代號 */
    private String orgFundCode;

    /** 基金代號 */
    private String subFundCode;

    /** 配息類別 */
    private String allocateType;

    /** 授權Flag Y:未核備 N:已核備 */
    private String approveFlag;

    /** 變更Flag */
    private String changeFlag;

    /** 建立時間 */
    private Date createTime;

    /** 幣別代碼 */
    private String currencyCode;

    /** 單筆申轉原幣最低金額 */
    private String fgLimitAmt;

    /** 基金種類 1:一次 2:小額 3:全部 */
    private String fundKind;

    /** 基金簡稱 */
    private String fundSname;

    /** 基金類別 1:股票 3:貨幣 4:債券 5:平衡 */
    private String fundType;

    /** 國內基金外幣計價 Y:是 ‘’:不是 */
    private String fundValuation;

    /** 高收益債券Flag */
    private String highBenefitBound;

    /** 定期不定額Flag */
    private String nfAmtFlag;

    /** 基金公司名稱 */
    private String orgFundName;

    /** 有效小數位數 */
    private String point;

    /** 申購Flag */
    private String purchaseFlag;

    /** 基金RAM屬性 */
    private String ramType;

    /** 贖回Flag */
    private String redeemFlag;

    /** 地區 */
    private String region;

    /** 風險等級 */
    private String riskLevel;

    /** 銷售Flag */
    private String saleFlag;

    /** 基金名稱 */
    private String subFundName;

    /** 目標類別 */
    private String targetType;

    /** 轉換Flag */
    private String transferInFlag;

    /** 轉換Flag */
    private String transferOutFlag;

    /** 單筆申轉最低金額 */
    private String twLimitAmt;

    /** 可否承做臺幣信託 ‘’:可以 N:不可以 */
    private String twdTrust;

    /** 更新時間 */
    private Date updateTime;

    /** 開放OBU申購Flag */
    private String obuSaleFlag;

    /** 未核備OBU可申購Flag */
    private String obuPurchaseFlag;

    /** 未核備OBU可轉入Flag */
    private String obuTransferInFlag;

    /** 是否為後收基金 */
    private String isBackEnd;

    /** 是否為目標到期型基金 */
    private String isTarget;

    /** 新興市場之非投資等級債券基金 */
    private String emerging;

    /** 主題名稱1 */
    private String psSubject1Name;

    /** 主題名稱2 */
    private String psSubject2Name;;

    /** 專案名稱1 */
    private String psProject1Name;
    
    /** 限專區高資產註記 */
    private String regionBuy;

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getFundNo() {
        return getOrgFundCode() + getSubFundCode();
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

    public String getAllocateType() {
        return allocateType;
    }

    public void setAllocateType(String allocateType) {
        this.allocateType = allocateType;
    }

    public String getApproveFlag() {
        return approveFlag;
    }

    public void setApproveFlag(String approveFlag) {
        this.approveFlag = approveFlag;
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getFgLimitAmt() {
        return fgLimitAmt;
    }

    public void setFgLimitAmt(String fgLimitAmt) {
        this.fgLimitAmt = fgLimitAmt;
    }

    public String getFundKind() {
        return fundKind;
    }

    public void setFundKind(String fundKind) {
        this.fundKind = fundKind;
    }

    public String getFundSname() {
        return fundSname;
    }

    public void setFundSname(String fundSname) {
        this.fundSname = fundSname;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType;
    }

    public String getFundValuation() {
        return fundValuation;
    }

    public void setFundValuation(String fundValuation) {
        this.fundValuation = fundValuation;
    }

    public String getHighBenefitBound() {
        return highBenefitBound;
    }

    public void setHighBenefitBound(String highBenefitBound) {
        this.highBenefitBound = highBenefitBound;
    }

    public String getNfAmtFlag() {
        return nfAmtFlag;
    }

    public void setNfAmtFlag(String nfAmtFlag) {
        this.nfAmtFlag = nfAmtFlag;
    }

    public String getOrgFundName() {
        return orgFundName;
    }

    public void setOrgFundName(String orgFundName) {
        this.orgFundName = orgFundName;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getPurchaseFlag() {
        return purchaseFlag;
    }

    public void setPurchaseFlag(String purchaseFlag) {
        this.purchaseFlag = purchaseFlag;
    }

    public String getRamType() {
        return ramType;
    }

    public void setRamType(String ramType) {
        this.ramType = ramType;
    }

    public String getRedeemFlag() {
        return redeemFlag;
    }

    public void setRedeemFlag(String redeemFlag) {
        this.redeemFlag = redeemFlag;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    public String getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(String saleFlag) {
        this.saleFlag = saleFlag;
    }

    public String getSubFundName() {
        return subFundName;
    }

    public void setSubFundName(String subFundName) {
        this.subFundName = subFundName;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTransferInFlag() {
        return transferInFlag;
    }

    public void setTransferInFlag(String transferInFlag) {
        this.transferInFlag = transferInFlag;
    }

    public String getTransferOutFlag() {
        return transferOutFlag;
    }

    public void setTransferOutFlag(String transferOutFlag) {
        this.transferOutFlag = transferOutFlag;
    }

    public String getTwLimitAmt() {
        return twLimitAmt;
    }

    public void setTwLimitAmt(String twLimitAmt) {
        this.twLimitAmt = twLimitAmt;
    }

    public String getTwdTrust() {
        return twdTrust;
    }

    public void setTwdTrust(String twdTrust) {
        this.twdTrust = twdTrust;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getObuSaleFlag() {
        return obuSaleFlag;
    }

    public void setObuSaleFlag(String obuSaleFlag) {
        this.obuSaleFlag = obuSaleFlag;
    }

    public String getObuPurchaseFlag() {
        return obuPurchaseFlag;
    }

    public void setObuPurchaseFlag(String obuPurchaseFlag) {
        this.obuPurchaseFlag = obuPurchaseFlag;
    }

    public String getObuTransferInFlag() {
        return obuTransferInFlag;
    }

    public void setObuTransferInFlag(String obuTransferInFlag) {
        this.obuTransferInFlag = obuTransferInFlag;
    }

    public String getIsBackEnd() {
        return isBackEnd;
    }

    public void setIsBackEnd(String isBackEnd) {
        this.isBackEnd = isBackEnd;
    }

    public String getIsTarget() {
        return isTarget;
    }

    public void setIsTarget(String isTarget) {
        this.isTarget = isTarget;
    }

    public String getEmerging() {
        return emerging;
    }

    public void setEmerging(String emerging) {
        this.emerging = emerging;
    }

    public String getPsSubject1Name() {
        return psSubject1Name;
    }

    public void setPsSubject1Name(String psSubject1Name) {
        this.psSubject1Name = psSubject1Name;
    }

    public String getPsSubject2Name() {
        return psSubject2Name;
    }

    public void setPsSubject2Name(String psSubject2Name) {
        this.psSubject2Name = psSubject2Name;
    }

    public String getPsProject1Name() {
        return psProject1Name;
    }

    public void setPsProject1Name(String psProject1Name) {
        this.psProject1Name = psProject1Name;
    }

	public String getRegionBuy() {
		return regionBuy;
	}

	public void setRegionBuy(String regionBuy) {
		this.regionBuy = regionBuy;
	}

}
