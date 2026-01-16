/**
 * 
 */
package com.tfb.aibank.chl.general.ot001.task.service;

import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;

//@formatter:off
/**
* @(#)NGNOT001010Task.java 
* 
* <p>Description:登入首頁</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230522, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
public class DeviceBindingCache extends RetriveDeviceStatusResponse {

    // 快登設定
    private int fastLogin;

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

    // 需要進行移轉
    private boolean isTransferNeed;

    /** 不執行圖形設定 */
    private boolean isIgnorePatternLockSetting;

    /** 跳過裝置綁定 */
    private boolean isAlreadySkipBinding;

    /** 行銀是否是快登 */
    private boolean isMBFastLogin;

    /**
     * 綁定狀態 false-未綁 true-已榜
     */
    private boolean bindingStatus;

    /** MOTP裝置綁定鍵值 */
    private Integer motpMidKey;

    /** 是否完成OTP驗證 */
    private boolean isOtpAuthed;

    /**
     * 是否設定所有訊息
     * 
     */
    private boolean notificationAll;

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
     * @return the isIgnorePatternLockSetting
     */
    public boolean isIgnorePatternLockSetting() {
        return isIgnorePatternLockSetting;
    }

    /**
     * @param isIgnorePatternLockSetting
     *            the isIgnorePatternLockSetting to set
     */
    public void setIgnorePatternLockSetting(boolean isIgnorePatternLockSetting) {
        this.isIgnorePatternLockSetting = isIgnorePatternLockSetting;
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
     * @return the motpMidKey
     */
    public Integer getMotpMidKey() {
        return motpMidKey;
    }

    /**
     * @param motpMidKey
     *            the motpMidKey to set
     */
    public void setMotpMidKey(Integer motpMidKey) {
        this.motpMidKey = motpMidKey;
    }

    /**
     * @return the notificationAll
     */
    public boolean isNotificationAll() {
        return notificationAll;
    }

    /**
     * @param notificationAll
     *            the notificationAll to set
     */
    public void setNotificationAll(boolean notificationAll) {
        this.notificationAll = notificationAll;
    }

    /**
     * @return the isOtpAuthed
     */
    public boolean isOtpAuthed() {
        return isOtpAuthed;
    }

    /**
     * @param isOtpAuthed
     *            the isOtpAuthed to set
     */
    public void setOtpAuthed(boolean isOtpAuthed) {
        this.isOtpAuthed = isOtpAuthed;
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

}
