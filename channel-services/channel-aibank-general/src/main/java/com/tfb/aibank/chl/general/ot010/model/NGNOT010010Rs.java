/**
 * 
 */
package com.tfb.aibank.chl.general.ot010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT010010Rs.java
*
* <p>Description: Onboarding-無痛移轉</p>
*
* <p>Modify History:</p>
* <ol>v1, 2024/06/04, John Chang
* </ol>
*/
//@formatter:on
@Component
public class NGNOT010010Rs implements RsData {

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    /**
     * 是否啟用無痛移轉
     */
    private boolean transferOnboarding;

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

    /**
     * @return the transferOnboarding
     */
    public boolean isTransferOnboarding() {
        return transferOnboarding;
    }

    /**
     * @param transferOnboarding
     *            the transferOnboarding to set
     */
    public void setTransferOnboarding(boolean transferOnboarding) {
        this.transferOnboarding = transferOnboarding;
    }

}
