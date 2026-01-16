package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001032Rs.java 
* 
* <p>Description:設定圖形頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230605, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001032Rs implements RsData {

    /**
     * 圖形因子
     */
    private String coefficient;
    /**
     * 遮罩過的ID
     */
    private String maskCustId;

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    /**
     * @return the coefficient
     */
    public String getCoefficient() {
        return coefficient;
    }

    /**
     * @param coefficient
     *            the coefficient to set
     */
    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * @return the maskCustId
     */
    public String getMaskCustId() {
        return maskCustId;
    }

    /**
     * @param maskCustId
     *            the maskCustId to set
     */
    public void setMaskCustId(String maskCustId) {
        this.maskCustId = maskCustId;
    }

    /**
     * @return the onboardingType
     */
    public int getOnboardingType() {
        return onboardingType;
    }

    /**
     * @param onboardingType
     *            the onboardingType to set
     */
    public void setOnboardingType(int onboardingType) {
        this.onboardingType = onboardingType;
    }

}
