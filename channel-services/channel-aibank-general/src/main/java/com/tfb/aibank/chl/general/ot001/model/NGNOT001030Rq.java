package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001030Rq.java 
* 
* <p>Description:快登設定頁</p>
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
public class NGNOT001030Rq implements RqData {

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

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
