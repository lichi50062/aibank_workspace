package com.tfb.aibank.chl.creditcard.ag005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG005021Rq.java
 * 
 * <p>Description:保費權益設定 021 設定保費權益</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/28, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG005021Rq implements RqData {

    /** 選擇的保費權益 */
    private String insurFeeBenefits;
    
    /** 選擇的保費權益五萬以下 */
    private String insurFeeBenefitsUnderFive;

    public String getInsurFeeBenefits() {
        return insurFeeBenefits;
    }

    public void setInsurFeeBenefits(String insurFeeBenefits) {
        this.insurFeeBenefits = insurFeeBenefits;
    }

	public String getInsurFeeBenefitsUnderFive() {
		return insurFeeBenefitsUnderFive;
	}

	public void setInsurFeeBenefitsUnderFive(String insurFeeBenefitsUnderFive) {
		this.insurFeeBenefitsUnderFive = insurFeeBenefitsUnderFive;
	}

}
