package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001039Rq.java 
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
public class NGNOT001039Rq implements RqData {

    /**
     * 加密後的圖形密碼
     */
    private String pinBlock;

    /** 不執行圖形設定 */
    private boolean isIgnorePatternLockSetting;

    /** 跳過裝置綁定 */
    private boolean isAlreadySkipBinding;

    // 原值
    private boolean isDefault;

    // 已綁定訊息通知
    private boolean isNotification;

    // 已綁定免登速查
    private boolean isQuickSearch;

    // 已綁定推播動態密碼MOTP
    private boolean isMotpSetting;

    /**
     * @return the pinBlock
     */
    public String getPinBlock() {
        return pinBlock;
    }

    /**
     * @param pinBlock
     *            the pinBlock to set
     */
    public void setPinBlock(String pinBlock) {
        this.pinBlock = pinBlock;
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
     * @return the isDefault
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     *            the isDefault to set
     */
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
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

}
