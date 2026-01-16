package com.tfb.aibank.chl.creditcard.ag005.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG005020Rs.java
 * 
 * <p>Description:保費權益設定 020 設定編輯頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG005020Rs implements RsData {

    /** 保費權益選單 */
    private List<InsurFeeBenefitsType> insurFeeBenefitsList;

    /** 保費權益預設值 */
    private String insurFeeBenefits;

    /** 卡片暱稱/卡別 */
    private String cardName;

    /** 卡片識別鍵值 */
    private String cardKey;
    
    /** 保費權益選單 五萬以下*/
    private List<InsurFeeBenefitsType> insurFeeBenefitsListUnderFive;
    
    /** 保費權益預設值 五萬以下*/
    private String insurFeeBenefitsUnderFive;
    
    /** 保費權益五萬以下Flag*/
    private Boolean underFiveFlag;
    

    public List<InsurFeeBenefitsType> getInsurFeeBenefitsList() {
        return insurFeeBenefitsList;
    }

    public void setInsurFeeBenefitsList(List<InsurFeeBenefitsType> insurFeeBenefitsList) {
        this.insurFeeBenefitsList = insurFeeBenefitsList;
    }

    public String getInsurFeeBenefits() {
        return insurFeeBenefits;
    }

    public void setInsurFeeBenefits(String insurFeeBenefits) {
        this.insurFeeBenefits = insurFeeBenefits;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardKey() {
        return cardKey;
    }

    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

	public List<InsurFeeBenefitsType> getInsurFeeBenefitsListUnderFive() {
		return insurFeeBenefitsListUnderFive;
	}

	public void setInsurFeeBenefitsListUnderFive(List<InsurFeeBenefitsType> insurFeeBenefitsListUnderFive) {
		this.insurFeeBenefitsListUnderFive = insurFeeBenefitsListUnderFive;
	}

	public String getInsurFeeBenefitsUnderFive() {
		return insurFeeBenefitsUnderFive;
	}

	public void setInsurFeeBenefitsUnderFive(String insurFeeBenefitsUnderFive) {
		this.insurFeeBenefitsUnderFive = insurFeeBenefitsUnderFive;
	}

	public Boolean getUnderFiveFlag() {
		return underFiveFlag;
	}

	public void setUnderFiveFlag(Boolean underFiveFlag) {
		this.underFiveFlag = underFiveFlag;
	}
	
}
