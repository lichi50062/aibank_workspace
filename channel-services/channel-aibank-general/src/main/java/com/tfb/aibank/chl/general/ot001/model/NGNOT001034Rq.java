package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT001034Rq.java 
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
public class NGNOT001034Rq implements RqData {

    /** 不執行圖形設定 */
    private boolean isIgnorePatternLockSetting;

    /** 跳過裝置綁定 */
    private boolean isAlreadySkipBinding;

    // 原值
    private boolean isDefault;

    // 不進行移轉
    private boolean skipTransfer;

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
     * @return the skipTransfer
     */
    public boolean isSkipTransfer() {
        return skipTransfer;
    }

    /**
     * @param skipTransfer
     *            the skipTransfer to set
     */
    public void setSkipTransfer(boolean skipTransfer) {
        this.skipTransfer = skipTransfer;
    }

}
