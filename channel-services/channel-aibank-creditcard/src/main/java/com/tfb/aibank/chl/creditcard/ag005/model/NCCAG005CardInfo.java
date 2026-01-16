/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.creditcard.ag005.model;

import java.util.Date;

import com.ibm.tw.ibmb.annotations.FormatDate;

// @formatter:off
/**
 * @(#)NCCAG005CardInfo.java
 * 
 * <p>Description:可進行保費權益設定的卡片資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCAG005CardInfo {

    /** 卡號 */
    private String cardNo;

    /** 卡號(隱碼) */
    private String cardNxMask;

    /** 卡名 */
    private String cardName;

    /** 五萬元以上保費權益 */
    private InsurFeeBenefitsType insurFeeBenefits;
    
    /** 五萬元以下保費權益 */
    private InsurFeeBenefitsType insurFeeBenefitsUnderFive;

    /** 五萬元以上保費權益最近一次變更日期 */
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date changeDate;

    /** 五萬元以下保費權益最近一次變更日期 */
    @FormatDate(pattern = "yyyy/MM/dd")
    private Date changeDateUnderFive;

	/** 卡別 */
    private String cardType;

    /** 識別資訊(唯一值) */
    private String cardKey;
    
    /**顯示保費權益 */
    private String showMemo;

	public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardNxMask() {
        return cardNxMask;
    }

    public void setCardNxMask(String cardNxMask) {
        this.cardNxMask = cardNxMask;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public InsurFeeBenefitsType getInsurFeeBenefits() {
        return insurFeeBenefits;
    }

    public void setInsurFeeBenefits(InsurFeeBenefitsType insurFeeBenefits) {
        this.insurFeeBenefits = insurFeeBenefits;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }
    
	public Date getChangeDateUnderFive() {
		return changeDateUnderFive;
	}

	public void setChangeDateUnderFive(Date changeDateUnderFive) {
		this.changeDateUnderFive = changeDateUnderFive;
	}
	
	public String getShowMemo() {
		return showMemo;
	}

	public void setShowMemo(String showMemo) {
		this.showMemo = showMemo;
	}
	
	public InsurFeeBenefitsType getInsurFeeBenefitsUnderFive() {
		return insurFeeBenefitsUnderFive;
	}

	public void setInsurFeeBenefitsUnderFive(InsurFeeBenefitsType insurFeeBenefitsUnderFive) {
		this.insurFeeBenefitsUnderFive = insurFeeBenefitsUnderFive;
	}

}
