package com.tfb.aibank.chl.general.ot005.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT005010Rq.java 
* 
* <p>Description:登入頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250305, Benson
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
public class NGNOT005021Rq implements RqData {

    /**
     * 用戶旅程註記
     */
    private String onboardingType;

    /**
     * 存取權杖
     */
    private String accessToken;

    /**
     * 自定參數
     */
    private String customParams;

    /** 是否首登 */
    private boolean isFirstLogin;

    /**
     * @return the onboardingType
     */ 
    public String getOnboardingType() {
        return onboardingType;
    }

    /**
     * @param onboardingType
     *            the onboardingType to set
     */
    public void setOnboardingType(String onboardingType) {
        this.onboardingType = onboardingType;
    }

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken
     *            the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return the isFirstLogin
     */
    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    /**
     * @param isFirstLogin
     *            the isFirstLogin to set
     */
    public void setFirstLogin(boolean isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
    }

    /**
     * @return the customParams
     */
    public String getCustomParams() {
        return customParams;
    }

    /**
     * @param customParams
     *            the customParams to set
     */
    public void setCustomParams(String customParams) {
        this.customParams = customParams;
    }

}
