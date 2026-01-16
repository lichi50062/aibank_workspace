package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001034Rs.java 
* 
* <p>Description:開啟推播設定頁</p>
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
public class NGNOT001034Rs implements RsData {

    /**
     * 遮罩過的電話號碼
     */
    private String maskMobilePhone;

    /**
     * FIDO Portal SDK URL
     */
    private String fidoPortalUrl;

    /** 參加單位 */
    private String businessNo;

    /** 跳過裝置綁定 */
    private boolean isAlreadySkipBinding;

    /** 條款標題 */
    private String contractTitle;
    /** 條款內文 */
    private String contractContent;

    /** 備註事項區塊 */
    private String note;

    /** 裝置綁定錯誤ErrorStatus */
    private NGNOT001100RsErrorStatus errorStatus;

    /** 是否暫時停止進入推播設定頁 */
    private boolean isDisableNotificationSetting;

    /** 是否要進行全部訊息設定(停在034) */
    private boolean isProcess034;

    /** 是否進行轉移 */
    private boolean isTransferNeed;

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    /**
     * 綁定狀態 false-未綁 true-已榜
     */
    private boolean bindingStatus;

    /** 顯示變更密碼提示 */
    private boolean isShowChangeTip;

    /** 登入身份 */
    private String loginType;

    /**
     * @return the contractTitle
     */
    public String getContractTitle() {
        return contractTitle;
    }

    /**
     * @param contractTitle
     *            the contractTitle to set
     */
    public void setContractTitle(String contractTitle) {
        this.contractTitle = contractTitle;
    }

    /**
     * @return the contractContent
     */
    public String getContractContent() {
        return contractContent;
    }

    /**
     * @param contractContent
     *            the contractContent to set
     */
    public void setContractContent(String contractContent) {
        this.contractContent = contractContent;
    }

    /**
     * @return the maskMobilePhone
     */
    public String getMaskMobilePhone() {
        return maskMobilePhone;
    }

    /**
     * @param maskMobilePhone
     *            the maskMobilePhone to set
     */
    public void setMaskMobilePhone(String maskMobilePhone) {
        this.maskMobilePhone = maskMobilePhone;
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
     * @return the isAlreadySkipBinding
     */
    public boolean isAlreadySkipBinding() {
        return isAlreadySkipBinding;
    }

    /**
     * @param isAlreadySkipBinding
     *            the isAlreadySkipBinding to set
     */
    public void setAlreadySkipBinding(boolean isAlreadySkipBinding) {
        this.isAlreadySkipBinding = isAlreadySkipBinding;
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
     * @return the isDisableNotificationSetting
     */
    public boolean isDisableNotificationSetting() {
        return isDisableNotificationSetting;
    }

    /**
     * @param isDisableNotificationSetting
     *            the isDisableNotificationSetting to set
     */
    public void setDisableNotificationSetting(boolean isDisableNotificationSetting) {
        this.isDisableNotificationSetting = isDisableNotificationSetting;
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
     * @return the isProcess034
     */
    public boolean isProcess034() {
        return isProcess034;
    }

    /**
     * @param isProcess034
     *            the isProcess034 to set
     */
    public void setProcess034(boolean isProcess034) {
        this.isProcess034 = isProcess034;
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

    /**
     * @return the bindingStatus
     */
    public boolean isBindingStatus() {
        return bindingStatus;
    }

    /**
     * @param bindingStatus
     *            the bindingStatus to set
     */
    public void setBindingStatus(boolean bindingStatus) {
        this.bindingStatus = bindingStatus;
    }

    /**
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     *            the note to set
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * @return the isShowChangeTip
     */
    public boolean isShowChangeTip() {
        return isShowChangeTip;
    }

    /**
     * @param isShowChangeTip
     *            the isShowChangeTip to set
     */
    public void setShowChangeTip(boolean isShowChangeTip) {
        this.isShowChangeTip = isShowChangeTip;
    }

    /**
     * @return the loginType
     */
    public String getLoginType() {
        return loginType;
    }

    /**
     * @param loginType
     *            the loginType to set
     */
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
