package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001070Rs.java 
* 
* <p>Description:移轉設定動畫頁</p>
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
public class NGNOT001070Rs implements RsData {

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    // 需要進行移轉
    private boolean isTransferNeed;

    // 綁定推播動態密碼MOTP
    private boolean isMotpSetting;

    /**
     * @return the isMotpSetting
     */
    public boolean isMotpSetting() {
        return isMotpSetting;
    }

    /**
     * @param isMotpSetting
     *            the isMotpSetting to set
     */
    public void setMotpSetting(boolean isMotpSetting) {
        this.isMotpSetting = isMotpSetting;
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

    /**
     * @return the isTransferNeed
     */
    public boolean isTransferNeed() {
        return isTransferNeed;
    }

    /**
     * @param isTransferNeed
     *            the isTransferNeed to set
     */
    public void setTransferNeed(boolean isTransferNeed) {
        this.isTransferNeed = isTransferNeed;
    }

}
