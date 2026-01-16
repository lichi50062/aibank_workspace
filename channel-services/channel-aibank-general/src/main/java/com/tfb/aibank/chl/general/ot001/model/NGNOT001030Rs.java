package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001030Rs.java 
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
public class NGNOT001030Rs implements RsData {

    /** 客戶已綁定其他裝置 */
    private boolean isAlreadyBindOther;

    /** 跳過快登設定 */
    private boolean isBypassFastLoginSetting;

    /**
     * FIDO Portal SDK URL
     */
    private String fidoPortalUrl;

    /** 參加單位 */
    private String businessNo;

    /** 使用者 */
    private String memberNo;

    /** 裝置綁定錯誤ErrorStatus */
    private NGNOT001100RsErrorStatus errorStatus;

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    /** 行銀是否是快登 */
    private boolean isMBFastLogin;

    /**
     * @return the isAlreadyBindOther
     */
    public boolean isAlreadyBindOther() {
        return isAlreadyBindOther;
    }

    /**
     * @param isAlreadyBindOther
     *            the isAlreadyBindOther to set
     */
    public void setAlreadyBindOther(boolean isAlreadyBindOther) {
        this.isAlreadyBindOther = isAlreadyBindOther;
    }

    /**
     * @return the isBypassFastLoginSetting
     */
    public boolean isBypassFastLoginSetting() {
        return isBypassFastLoginSetting;
    }

    /**
     * @param isBypassFastLoginSetting
     *            the isBypassFastLoginSetting to set
     */
    public void setBypassFastLoginSetting(boolean isBypassFastLoginSetting) {
        this.isBypassFastLoginSetting = isBypassFastLoginSetting;
    }

    /**
     * @return the fidoPortalUrl
     */
    public String getFidoPortalUrl() {
        return fidoPortalUrl;
    }

    /**
     * @param fidoPortalUrl
     *            the fidoPortalUrl to set
     */
    public void setFidoPortalUrl(String fidoPortalUrl) {
        this.fidoPortalUrl = fidoPortalUrl;
    }

    /**
     * @return the businessNo
     */
    public String getBusinessNo() {
        return businessNo;
    }

    /**
     * @param businessNo
     *            the businessNo to set
     */
    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    /**
     * @return the errorStatus
     */
    public NGNOT001100RsErrorStatus getErrorStatus() {
        return errorStatus;
    }

    /**
     * @param errorStatus
     *            the errorStatus to set
     */
    public void setErrorStatus(NGNOT001100RsErrorStatus errorStatus) {
        this.errorStatus = errorStatus;
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
     * @return the isMBFastLogin
     */
    public boolean isMBFastLogin() {
        return isMBFastLogin;
    }

    /**
     * @param isMBFastLogin
     *            the isMBFastLogin to set
     */
    public void setMBFastLogin(boolean isMBFastLogin) {
        this.isMBFastLogin = isMBFastLogin;
    }

    /**
     * @return the memberNo
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo
     *            the memberNo to set
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

}
