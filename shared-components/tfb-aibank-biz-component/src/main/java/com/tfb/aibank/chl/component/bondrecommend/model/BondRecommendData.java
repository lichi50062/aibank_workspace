/*
 * ===========================================================================
 * 
 * WIS Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright WIS Corp. 2025.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.bondrecommend.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ibm.tw.ibmb.annotations.FormatDate;


// @formatter:off
/**
 * @(#)NNFQU005BondData.java
 * 
 * <p>Description:債券項目</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/15, xwr	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BondRecommendData {

    /** 債券代碼 */
    private String bondNo;

    /** 債券名稱 */
    private String bondSName;

    /** 到期日 */
    @FormatDate
    private Date endDate;

    /** 計價幣別 */
    private String curName;

    /** 票面利率 */
    private BigDecimal faceIntRate;

    /** 參考報價日期 */
    @FormatDate
    private Date refPriceDate;

    /** 參考申購報價 */
    private BigDecimal refPrice;

    /** YTM/YTC正負號 */
    private String ytmytcSign;

    /** YTM/YTC */
    private BigDecimal ytmytc;

    /** 最低申購面額 單筆申購面額須大於此數字 */
    private BigDecimal miniBuyAmt;

    /** 風險屬性 */
    private String ramRiskType;

    /** 推薦挑選主題 */
    private List<String> groupName;

    /** 觀察中 */
    private boolean isObserve;

    /** 可申購 */
    private boolean canBuy;
    
    
    /**
	 * 專屬推薦-公司名稱
	 */
	private String companyName;

	
	 /**
     * 專屬推薦-文案內容
     */
    private String content;
    
    /** 債券群組id */
    private String groupId;
    
    /**
     * 專屬推薦-tab頁名稱
     */
    private String listTitle;
    
   /**
    * 專屬推薦-清單名稱
    */
    private String listIntroduction;
    
    /**
     * 專屬推薦-折線圖
     */
    private List<BondRecommendLineGraph> recommendLineGraphList;
    
    public BondRecommendData() {}
    
	public BondRecommendData(String listTitle) {
		this.listTitle = listTitle;
	}

	/**
     * @return {@link #bondNo}
     */
    public String getBondNo() {
        return bondNo;
    }

    /**
     * @param bondNo
     *            {@link #bondNo}
     */
    public void setBondNo(String bondNo) {
        this.bondNo = bondNo;
    }

    /**
     * @return {@link #bondSName}
     */
    public String getBondSName() {
        return bondSName;
    }

    /**
     * @param bondFName
     *            {@link #bondSName}
     */
    public void setBondSName(String bondSName) {
        this.bondSName = bondSName;
    }

    /**
     * @return {@link #endDate}
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            {@link #endDate}
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return {@link #curName}
     */
    public String getCurName() {
        return curName;
    }

    /**
     * @param curName
     *            {@link #curName}
     */
    public void setCurName(String curName) {
        this.curName = curName;
    }

    /**
     * @return {@link #faceIntRate}
     */
    public BigDecimal getFaceIntRate() {
        return faceIntRate;
    }

    /**
     * @param faceIntRate
     *            {@link #faceIntRate}
     */
    public void setFaceIntRate(BigDecimal faceIntRate) {
        this.faceIntRate = faceIntRate;
    }

    /**
     * @return {@link #refPriceDate}
     */
    public Date getRefPriceDate() {
        return refPriceDate;
    }

    /**
     * @param refPriceDate
     *            {@link #refPriceDate}
     */
    public void setRefPriceDate(Date refPriceDate) {
        this.refPriceDate = refPriceDate;
    }

    /**
     * @return {@link #refPrice}
     */
    public BigDecimal getRefPrice() {
        return refPrice;
    }

    /**
     * @param refPrice
     *            {@link #refPrice}
     */
    public void setRefPrice(BigDecimal refPrice) {
        this.refPrice = refPrice;
    }

    /**
     * @return {@link #ytmytcSign}
     */
    public String getYtmytcSign() {
        return ytmytcSign;
    }

    /**
     * @param ytmytcSign
     *            {@link #ytmytcSign}
     */
    public void setYtmytcSign(String ytmytcSign) {
        this.ytmytcSign = ytmytcSign;
    }

    /**
     * @return {@link #ytmytc}
     */
    public BigDecimal getYtmytc() {
        return ytmytc;
    }

    /**
     * @param ytmytc
     *            {@link #ytmytc}
     */
    public void setYtmytc(BigDecimal ytmytc) {
        this.ytmytc = ytmytc;
    }

    /**
     * @return {@link #miniBuyAmt}
     */
    public BigDecimal getMiniBuyAmt() {
        return miniBuyAmt;
    }

    /**
     * @param miniBuyAmt
     *            {@link #miniBuyAmt}
     */
    public void setMiniBuyAmt(BigDecimal miniBuyAmt) {
        this.miniBuyAmt = miniBuyAmt;
    }

    /**
     * @return {@link #ramRiskType}
     */
    public String getRamRiskType() {
        return ramRiskType;
    }

    /**
     * @param ramRiskType
     *            {@link #ramRiskType}
     */
    public void setRamRiskType(String ramRiskType) {
        this.ramRiskType = ramRiskType;
    }

    /**
     * @return {@link #groupName}
     */
    public List<String> getGroupName() {
        return groupName;
    }

    /**
     * @param groupName
     *            {@link #groupName}
     */
    public void setGroupName(List<String> groupName) {
        this.groupName = groupName;
    }

    /**
     * @return {@link #isObserve}
     */
    public boolean isObserve() {
        return isObserve;
    }

    /**
     * @param isObserve
     *            {@link #isObserve}
     */
    public void setObserve(boolean isObserve) {
        this.isObserve = isObserve;
    }

    /**
     * @return {@link #canBuy}
     */
    public boolean isCanBuy() {
        return canBuy;
    }

    /**
     * @param canBuy
     *            {@link #canBuy}
     */
    public void setCanBuy(boolean canBuy) {
        this.canBuy = canBuy;
    }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}


	public String getListTitle() {
		return listTitle;
	}

	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
	}

	public List<BondRecommendLineGraph> getRecommendLineGraphList() {
		return recommendLineGraphList;
	}

	public void setRecommendLineGraphList(List<BondRecommendLineGraph> recommendLineGraphList) {
		this.recommendLineGraphList = recommendLineGraphList;
	}

	public String getListIntroduction() {
		return listIntroduction;
	}

	public void setListIntroduction(String listIntroduction) {
		this.listIntroduction = listIntroduction;
	}
}
