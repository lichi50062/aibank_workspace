package com.tfb.aibank.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

// @formatter:off
/**
 * @(#)BondInfo.java
 * 
 * <p>Description:債券資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/07, Leiley	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondInfo implements Serializable {

    private static final long serialVersionUID = -1211838256358706768L;

    /** 債券代碼 */
    private String bondNo;

    /** 語系 */
    private String locale;

    /** 發行幣別 */
    private String bondCcy;

    /** 債券全名 */
    private String bondFName;

    /** 債券種類 */
    private String bondKind;

    /** 債券適格等級 */
    private String bondLevel;

    /** 債券簡稱 */
    private String bondSName;

    /** 建立時間 */
    private Date createTime;

    /** 變更折現迄日 */
    private Date discountEndDate;

    /** 折現註記 (Y/ ) */
    private String discountFlag;

    /** 變更折現起日 */
    private Date discountStartDate;

    /** 到期日期 */
    private Date endDate;

    /** 剩餘年期 */
    private BigDecimal endTime;

    /** 保證機構 */
    private String ensureOrg;

    /** 票面利率 */
    private BigDecimal faceIntRate;

    /** 票面價格 */
    private Integer faceValue;

    /** 限高資產客戶申購(Y/) */
    private String highAssetsSale;

    /** 產業類別 */
    private String industrialType;

    /** 可否電子變更 (Y/N) */
    private String isChange;

    /** 限專業投資人申購(Y/) */
    private String isProfessionSale;

    /** 可否電子贖回 (Y/N) */
    private String isRedemption;

    /** 可否電子單筆申購 (Y/N) */
    private String isSubscription;

    /** 可否電子小額申購 (Y/N) */
    private String isSubscriptionS;

    /** ISIN-CODE */
    private String isinCode;

    /** 發行日期 */
    private Date issueDate;

    /** 發行機構 */
    private String issueOrg;

    /** 最低申購累積面額 單筆申購面額扣除最低申購面額後，需要是此數字倍數 */
    private Long miniBuyAccAmt;

    /** 最低申購面額 單筆申購面額須大於此數字 */
    private Long miniBuyAmt;

    /** 最低贖回累積面額 單筆贖回面額扣除最低贖回面額後，需要是此數字倍數 */
    private Long miniRedeemAccAmt;

    /** 最低贖回面額 單筆贖回面額須大於此數字 */
    private Long miniRedeemAmt;

    /** 預計下次付息日 */
    private String nextInterestDate;

    /** 可否部分贖回 (Y/N) */
    private String parCallBack;

    /** 付息頻率 1：半年 2：年 3：季 4：月 5：不定 */
    private String payFreq;

    /** 商品風險等級 */
    private String prodRiskLv;

    /** RAM風險類別P1：微P2：低P3：中P4：高 */
    private String ramRiskType;

    /** 保本比率 */
    private BigDecimal rate;

    /** 風險預告書(Y/ ) */
    private String riskCode;

    /** 風險屬性 */
    private String riskType;

    /** 限境內/境外交易(D境內/境外/空白表全開 ) */
    private String saleLimit;

    /** 客製化商品(Y/) */
    private String specialCode;

    /** 暫停銷售 */
    private String stopSaleFlag;

    /** 存摺名稱 */
    private String trnInfo;

    /** 信託業務別 Y：只能做外幣信託 N：只能做臺幣信託 */
    private String trustType;

    /** 更新時間 */
    private Date updateTime;

    /** 是否檢核 W-8BEN (Y/ ) */
    private String w8ben;

    /** 配息月份 */
    private String divMonth;

    /** 信用評等 */
    private String creditRating;

    /** YTM/YTC正負號 */
    private String ytmytcSign;

    /** YTM/YTC */
    private BigDecimal ytmytc;
    
    /** 不保本聲明書 */
    private String noBreakEven;

    /** 交易日 */
    private Date tradeDate;

    /** GROUP_ID */
    private String groupId;
    
    /** GROUP_NAME */
    private String groupName;


    /** 發行價 */
    private BigDecimal tradePrice;

    /** 募集日期起日 */
    private Date bondSdate;

    /** 募集日期迄日 */
    private Date bondEdate;

    /** 限制ID */
    private String limitId;

    /** 是否保本 */
    private String breakEvenCode;
    
    /** 限專區申購 */
    private String regionBuy;

    /**
     * @return the tradePrice
     */
    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    /**
     * @param tradePrice
     *            the tradePrice to set
     */
    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    /**
     * @return the bondNo
     */
    public String getBondNo() {
        return bondNo;
    }

    /**
     * @param bondNo
     *            the bondNo to set
     */
    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the bondCcy
     */
    public String getBondCcy() {
        return bondCcy;
    }

    /**
     * @param bondCcy
     *            the bondCcy to set
     */
    public void setBondCcy(String bondCcy) {
        this.bondCcy = bondCcy;
    }

    /**
     * @return the bondFName
     */
    public String getBondFName() {
        return bondFName;
    }

    /**
     * @param bondFName
     *            the bondFName to set
     */
    public void setBondFName(String bondFName) {
        this.bondFName = bondFName;
    }

    /**
     * @return the bondKind
     */
    public String getBondKind() {
        return bondKind;
    }

    /**
     * @param bondKind
     *            the bondKind to set
     */
    public void setBondKind(String bondKind) {
        this.bondKind = bondKind;
    }

    /**
     * @return the bondLevel
     */
    public String getBondLevel() {
        return bondLevel;
    }

    /**
     * @param bondLevel
     *            the bondLevel to set
     */
    public void setBondLevel(String bondLevel) {
        this.bondLevel = bondLevel;
    }

    /**
     * @return the bondSName
     */
    public String getBondSName() {
        return bondSName;
    }

    /**
     * @param bondSName
     *            the bondSName to set
     */
    public void setBondSName(String bondSName) {
        this.bondSName = bondSName;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the discountEndDate
     */
    public Date getDiscountEndDate() {
        return discountEndDate;
    }

    /**
     * @param discountEndDate
     *            the discountEndDate to set
     */
    public void setDiscountEndDate(Date discountEndDate) {
        this.discountEndDate = discountEndDate;
    }

    /**
     * @return the discountFlag
     */
    public String getDiscountFlag() {
        return discountFlag;
    }

    /**
     * @param discountFlag
     *            the discountFlag to set
     */
    public void setDiscountFlag(String discountFlag) {
        this.discountFlag = discountFlag;
    }

    /**
     * @return the discountStartDate
     */
    public Date getDiscountStartDate() {
        return discountStartDate;
    }

    /**
     * @param discountStartDate
     *            the discountStartDate to set
     */
    public void setDiscountStartDate(Date discountStartDate) {
        this.discountStartDate = discountStartDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the endTime
     */
    public BigDecimal getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(BigDecimal endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the ensureOrg
     */
    public String getEnsureOrg() {
        return ensureOrg;
    }

    /**
     * @param ensureOrg
     *            the ensureOrg to set
     */
    public void setEnsureOrg(String ensureOrg) {
        this.ensureOrg = ensureOrg;
    }

    /**
     * @return the faceIntRate
     */
    public BigDecimal getFaceIntRate() {
        return faceIntRate;
    }

    /**
     * @param faceIntRate
     *            the faceIntRate to set
     */
    public void setFaceIntRate(BigDecimal faceIntRate) {
        this.faceIntRate = faceIntRate;
    }

    /**
     * @return the faceValue
     */
    public Integer getFaceValue() {
        return faceValue;
    }

    /**
     * @param faceValue
     *            the faceValue to set
     */
    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    /**
     * @return the highAssetsSale
     */
    public String getHighAssetsSale() {
        return highAssetsSale;
    }

    /**
     * @param highAssetsSale
     *            the highAssetsSale to set
     */
    public void setHighAssetsSale(String highAssetsSale) {
        this.highAssetsSale = highAssetsSale;
    }

    /**
     * @return the industrialType
     */
    public String getIndustrialType() {
        return industrialType;
    }

    /**
     * @param industrialType
     *            the industrialType to set
     */
    public void setIndustrialType(String industrialType) {
        this.industrialType = industrialType;
    }

    /**
     * @return the isChange
     */
    public String getIsChange() {
        return isChange;
    }

    /**
     * @param isChange
     *            the isChange to set
     */
    public void setIsChange(String isChange) {
        this.isChange = isChange;
    }

    /**
     * @return the isProfessionSale
     */
    public String getIsProfessionSale() {
        return isProfessionSale;
    }

    /**
     * @param isProfessionSale
     *            the isProfessionSale to set
     */
    public void setIsProfessionSale(String isProfessionSale) {
        this.isProfessionSale = isProfessionSale;
    }

    /**
     * @return the isRedemption
     */
    public String getIsRedemption() {
        return isRedemption;
    }

    /**
     * @param isRedemption
     *            the isRedemption to set
     */
    public void setIsRedemption(String isRedemption) {
        this.isRedemption = isRedemption;
    }

    /**
     * @return the isSubscription
     */
    public String getIsSubscription() {
        return isSubscription;
    }

    /**
     * @param isSubscription
     *            the isSubscription to set
     */
    public void setIsSubscription(String isSubscription) {
        this.isSubscription = isSubscription;
    }

    /**
     * @return the isSubscriptionS
     */
    public String getIsSubscriptionS() {
        return isSubscriptionS;
    }

    /**
     * @param isSubscriptionS
     *            the isSubscriptionS to set
     */
    public void setIsSubscriptionS(String isSubscriptionS) {
        this.isSubscriptionS = isSubscriptionS;
    }

    /**
     * @return the isinCode
     */
    public String getIsinCode() {
        return isinCode;
    }

    /**
     * @param isinCode
     *            the isinCode to set
     */
    public void setIsinCode(String isinCode) {
        this.isinCode = isinCode;
    }

    /**
     * @return the issueDate
     */
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     * @param issueDate
     *            the issueDate to set
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * @return the issueOrg
     */
    public String getIssueOrg() {
        return issueOrg;
    }

    /**
     * @param issueOrg
     *            the issueOrg to set
     */
    public void setIssueOrg(String issueOrg) {
        this.issueOrg = issueOrg;
    }

    /**
     * @return the miniBuyAccAmt
     */
    public Long getMiniBuyAccAmt() {
        return miniBuyAccAmt;
    }

    /**
     * @param miniBuyAccAmt
     *            the miniBuyAccAmt to set
     */
    public void setMiniBuyAccAmt(Long miniBuyAccAmt) {
        this.miniBuyAccAmt = miniBuyAccAmt;
    }

    /**
     * @return the miniBuyAmt
     */
    public Long getMiniBuyAmt() {
        return miniBuyAmt;
    }

    /**
     * @param miniBuyAmt
     *            the miniBuyAmt to set
     */
    public void setMiniBuyAmt(Long miniBuyAmt) {
        this.miniBuyAmt = miniBuyAmt;
    }

    /**
     * @return the miniRedeemAccAmt
     */
    public Long getMiniRedeemAccAmt() {
        return miniRedeemAccAmt;
    }

    /**
     * @param miniRedeemAccAmt
     *            the miniRedeemAccAmt to set
     */
    public void setMiniRedeemAccAmt(Long miniRedeemAccAmt) {
        this.miniRedeemAccAmt = miniRedeemAccAmt;
    }

    /**
     * @return the miniRedeemAmt
     */
    public Long getMiniRedeemAmt() {
        return miniRedeemAmt;
    }

    /**
     * @param miniRedeemAmt
     *            the miniRedeemAmt to set
     */
    public void setMiniRedeemAmt(Long miniRedeemAmt) {
        this.miniRedeemAmt = miniRedeemAmt;
    }

    /**
     * @return the nextInterestDate
     */
    public String getNextInterestDate() {
        return nextInterestDate;
    }

    /**
     * @param nextInterestDate
     *            the nextInterestDate to set
     */
    public void setNextInterestDate(String nextInterestDate) {
        this.nextInterestDate = nextInterestDate;
    }

    /**
     * @return the parCallBack
     */
    public String getParCallBack() {
        return parCallBack;
    }

    /**
     * @param parCallBack
     *            the parCallBack to set
     */
    public void setParCallBack(String parCallBack) {
        this.parCallBack = parCallBack;
    }

    /**
     * @return the payFreq
     */
    public String getPayFreq() {
        return payFreq;
    }

    /**
     * @param payFreq
     *            the payFreq to set
     */
    public void setPayFreq(String payFreq) {
        this.payFreq = payFreq;
    }

    /**
     * @return the prodRiskLv
     */
    public String getProdRiskLv() {
        return prodRiskLv;
    }

    /**
     * @param prodRiskLv
     *            the prodRiskLv to set
     */
    public void setProdRiskLv(String prodRiskLv) {
        this.prodRiskLv = prodRiskLv;
    }

    /**
     * @return the ramRiskType
     */
    public String getRamRiskType() {
        return ramRiskType;
    }

    /**
     * @param ramRiskType
     *            the ramRiskType to set
     */
    public void setRamRiskType(String ramRiskType) {
        this.ramRiskType = ramRiskType;
    }

    /**
     * @return the rate
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * @return the riskCode
     */
    public String getRiskCode() {
        return riskCode;
    }

    /**
     * @param riskCode
     *            the riskCode to set
     */
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }

    /**
     * @return the riskType
     */
    public String getRiskType() {
        return riskType;
    }

    /**
     * @param riskType
     *            the riskType to set
     */
    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    /**
     * @return the saleLimit
     */
    public String getSaleLimit() {
        return saleLimit;
    }

    /**
     * @param saleLimit
     *            the saleLimit to set
     */
    public void setSaleLimit(String saleLimit) {
        this.saleLimit = saleLimit;
    }

    /**
     * @return the specialCode
     */
    public String getSpecialCode() {
        return specialCode;
    }

    /**
     * @param specialCode
     *            the specialCode to set
     */
    public void setSpecialCode(String specialCode) {
        this.specialCode = specialCode;
    }

    /**
     * @return the stopSaleFlag
     */
    public String getStopSaleFlag() {
        return stopSaleFlag;
    }

    /**
     * @param stopSaleFlag
     *            the stopSaleFlag to set
     */
    public void setStopSaleFlag(String stopSaleFlag) {
        this.stopSaleFlag = stopSaleFlag;
    }

    /**
     * @return the trnInfo
     */
    public String getTrnInfo() {
        return trnInfo;
    }

    /**
     * @param trnInfo
     *            the trnInfo to set
     */
    public void setTrnInfo(String trnInfo) {
        this.trnInfo = trnInfo;
    }

    /**
     * @return the trustType
     */
    public String getTrustType() {
        return trustType;
    }

    /**
     * @param trustType
     *            the trustType to set
     */
    public void setTrustType(String trustType) {
        this.trustType = trustType;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     *            the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return the w8ben
     */
    public String getW8ben() {
        return w8ben;
    }

    /**
     * @param w8ben
     *            the w8ben to set
     */
    public void setW8ben(String w8ben) {
        this.w8ben = w8ben;
    }

    public String getDivMonth() {
        return divMonth;
    }

    public void setDivMonth(String divMonth) {
        this.divMonth = divMonth;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public String getYtmytcSign() {
        return ytmytcSign;
    }

    public void setYtmytcSign(String ytmytcSign) {
        this.ytmytcSign = ytmytcSign;
    }

    public BigDecimal getYtmytc() {
        return ytmytc;
    }

    public void setYtmytc(BigDecimal ytmytc) {
        this.ytmytc = ytmytc;
    }

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
    public String getNoBreakEven() {
        return noBreakEven;
    }

    public void setNoBreakEven(String noBreakEven) {
        this.noBreakEven = noBreakEven;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getBondSdate() {
        return bondSdate;
    }

    public void setBondSdate(Date bondSdate) {
        this.bondSdate = bondSdate;
    }

    public Date getBondEdate() {
        return bondEdate;
    }

    public void setBondEdate(Date bondEdate) {
        this.bondEdate = bondEdate;
    }

    public String getLimitId() {
        return limitId;
    }

    public void setLimitId(String limitId) {
        this.limitId = limitId;
    }

    public String getBreakEvenCode() {
        return breakEvenCode;
    }

    public void setBreakEvenCode(String breakEvenCode) {
        this.breakEvenCode = breakEvenCode;
    }

	public String getRegionBuy() {
		return regionBuy;
	}

	public void setRegionBuy(String regionBuy) {
		this.regionBuy = regionBuy;
	}

}
