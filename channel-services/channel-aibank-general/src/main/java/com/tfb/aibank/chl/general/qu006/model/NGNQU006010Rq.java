package com.tfb.aibank.chl.general.qu006.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NGNQU006010Rq.java
 * 
 * <p>Description:設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/13, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNQU006010Rq implements RqData {

    /** 「螢幕截圖設定」狀態 */
    private boolean enableScreenshot;

    public boolean isEnableScreenshot() {
        return enableScreenshot;
    }

    public void setEnableScreenshot(boolean enableScreenshot) {
        this.enableScreenshot = enableScreenshot;
    }

}
