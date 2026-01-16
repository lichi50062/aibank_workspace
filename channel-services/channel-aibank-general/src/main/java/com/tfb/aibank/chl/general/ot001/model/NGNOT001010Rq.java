package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001010Rq.java 
* 
* <p>Description:登入頁</p>
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
public class NGNOT001010Rq implements RqData {

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
    /**雙重驗證成功*/
    private boolean isTwoFactorAuthSuccess;
    /**雙重驗證失敗*/
    private boolean isTwoFactorAuthFail;
    /**訊息*/
    private String twoFactorAuthDesc;

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
 
    public boolean isTwoFactorAuthFail() {
        return isTwoFactorAuthFail;
    }

    public void setTwoFactorAuthFail(boolean isTwoFactorAuthFail) {
        this.isTwoFactorAuthFail = isTwoFactorAuthFail;
    }

    public boolean isTwoFactorAuthSuccess() {
        return isTwoFactorAuthSuccess;
    }

    public void setTwoFactorAuthSuccess(boolean isTwoFactorAuthSuccess) {
        this.isTwoFactorAuthSuccess = isTwoFactorAuthSuccess;
    }

    public String getTwoFactorAuthDesc() {
        return twoFactorAuthDesc;
    }

    public void setTwoFactorAuthDesc(String twoFactorAuthDesc) {
        this.twoFactorAuthDesc = twoFactorAuthDesc;
    }

}
