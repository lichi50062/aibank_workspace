package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT001060Rs.java 
* 
* <p>Description:自訂項目移轉頁</p>
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
public class NGNOT001060Rs implements RsData {

    private int status;

    // 已綁定訊息通知
    private boolean isNotification;

    // 已綁定免登速查
    private boolean isQuickSearch;

    // 已綁定無卡提款
    private boolean isNoCardwithDraw;

    // 已綁定推播動態密碼MOTP
    private boolean isMotpSetting;

    // 已綁定手機號碼收款
    private boolean isPhoneTransfer;

    // 已綁定提高飛約轉額度
    private boolean isTransferQuota;

    // 需要進行移轉
    private boolean isTransferNeed;

    /**
     * 綁定狀態 false-未綁 true-已榜
     */
    private boolean bindingStatus;

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
     * @return the isNotification
     */
    public boolean isNotification() {
        return isNotification;
    }

    /**
     * @param isNotification
     *            the isNotification to set
     */
    public void setNotification(boolean isNotification) {
        this.isNotification = isNotification;
    }

    /**
     * @return the isQuickSearch
     */
    public boolean isQuickSearch() {
        return isQuickSearch;
    }

    /**
     * @param isQuickSearch
     *            the isQuickSearch to set
     */
    public void setQuickSearch(boolean isQuickSearch) {
        this.isQuickSearch = isQuickSearch;
    }

    /**
     * @return the isNoCardwithDraw
     */
    public boolean isNoCardwithDraw() {
        return isNoCardwithDraw;
    }

    /**
     * @param isNoCardwithDraw
     *            the isNoCardwithDraw to set
     */
    public void setNoCardwithDraw(boolean isNoCardwithDraw) {
        this.isNoCardwithDraw = isNoCardwithDraw;
    }

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
     * @return the isPhoneTransfer
     */
    public boolean isPhoneTransfer() {
        return isPhoneTransfer;
    }

    /**
     * @param isPhoneTransfer
     *            the isPhoneTransfer to set
     */
    public void setPhoneTransfer(boolean isPhoneTransfer) {
        this.isPhoneTransfer = isPhoneTransfer;
    }

    /**
     * @return the isTransferQuota
     */
    public boolean isTransferQuota() {
        return isTransferQuota;
    }

    /**
     * @param isTransferQuota
     *            the isTransferQuota to set
     */
    public void setTransferQuota(boolean isTransferQuota) {
        this.isTransferQuota = isTransferQuota;
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

}
