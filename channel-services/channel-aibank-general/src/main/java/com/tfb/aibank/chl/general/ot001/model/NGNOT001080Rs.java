package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001080Rs.java 
* 
* <p>Description:移轉設定成功頁</p>
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
public class NGNOT001080Rs implements RsData {

    // 快登設定
    private int fastLogin;

    // 狀態
    // 1 - 失敗
    private int status;

    // 交易時間
    private String txTime;

    // 已綁定訊息通知
    private int notification;

    // 已綁定免登速查
    private int quickSearch;

    // 已綁定推播動態密碼MOTP
    private int motpSetting;

    // 已綁定無卡提款
    private int noCardwithDraw;

    // 已綁定手機號碼收款
    private int phoneTransfer;

    // 已綁定提高非約轉額度
    private int transferQuota;

    /** 登入身份 */
    private String loginType;

    /**
     * 是否需顯示錯誤
     */
    private boolean isAnyItemFail;

    /**
     * 用戶旅程註記
     */
    private int onboardingType;

    /** 顯示變更密碼提示 */
    private boolean isShowChangeTip;

    /**
     * @return the notification
     */
    public int getNotification() {
        return notification;
    }

    /**
     * @param notification
     *            the notification to set
     */
    public void setNotification(int notification) {
        this.notification = notification;
    }

    /**
     * @return the quickSearch
     */
    public int getQuickSearch() {
        return quickSearch;
    }

    /**
     * @param quickSearch
     *            the quickSearch to set
     */
    public void setQuickSearch(int quickSearch) {
        this.quickSearch = quickSearch;
    }

    /**
     * @return the motpSetting
     */
    public int getMotpSetting() {
        return motpSetting;
    }

    /**
     * @param motpSetting
     *            the motpSetting to set
     */
    public void setMotpSetting(int motpSetting) {
        this.motpSetting = motpSetting;
    }

    /**
     * @return the noCardwithDraw
     */
    public int getNoCardwithDraw() {
        return noCardwithDraw;
    }

    /**
     * @param noCardwithDraw
     *            the noCardwithDraw to set
     */
    public void setNoCardwithDraw(int noCardwithDraw) {
        this.noCardwithDraw = noCardwithDraw;
    }

    /**
     * @return the phoneTransfer
     */
    public int getPhoneTransfer() {
        return phoneTransfer;
    }

    /**
     * @param phoneTransfer
     *            the phoneTransfer to set
     */
    public void setPhoneTransfer(int phoneTransfer) {
        this.phoneTransfer = phoneTransfer;
    }

    /**
     * @return the transferQuota
     */
    public int getTransferQuota() {
        return transferQuota;
    }

    /**
     * @param transferQuota
     *            the transferQuota to set
     */
    public void setTransferQuota(int transferQuota) {
        this.transferQuota = transferQuota;
    }

    /**
     * @return the isAnyItemFail
     */
    public boolean isAnyItemFail() {
        return isAnyItemFail;
    }

    /**
     * @param isAnyItemFail
     *            the isAnyItemFail to set
     */
    public void setAnyItemFail(boolean isAnyItemFail) {
        this.isAnyItemFail = isAnyItemFail;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the txTime
     */
    public String getTxTime() {
        return txTime;
    }

    /**
     * @param txTime
     *            the txTime to set
     */
    public void setTxTime(String txTime) {
        this.txTime = txTime;
    }

    /**
     * @return the fastLogin
     */
    public int getFastLogin() {
        return fastLogin;
    }

    /**
     * @param fastLogin
     *            the fastLogin to set
     */
    public void setFastLogin(int fastLogin) {
        this.fastLogin = fastLogin;
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
