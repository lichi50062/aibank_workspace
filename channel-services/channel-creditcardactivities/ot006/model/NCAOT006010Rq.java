package com.tfb.aibank.chl.creditcardactivities.ot006.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCOT006010Rq.java
 * 
 * <p>Description:信用卡活動登錄 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCAOT006010Rq implements RqData {

    /**
     * 重新整理按鈕
     */
    private String reloadButton;

    /**
     * 活動代碼
     * @return
     */
    private String  activityCode;


    public String getReloadButton() {
        return reloadButton;
    }

    public void setReloadButton(String reloadButton) {
        this.reloadButton = reloadButton;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }
}
