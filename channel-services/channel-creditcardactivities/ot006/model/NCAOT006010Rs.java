package com.tfb.aibank.chl.creditcardactivities.ot006.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCOT006010Rs.java
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
public class NCAOT006010Rs implements RsData {

    /** 所有活動 */
    private List<NCAOT006Activity> activity;

    /**
     * 查詢狀態 0-正常 1-無資料 2-查詢失敗
     */
    private int status;

    /**
     * 是否為熱門活動邏輯
     */
    private boolean hotActivity;

    /**
     * 登錄按鈕開關控制
     */
    private String buttonSwitch;

    /**
     * @return the activity
     */
    public List<NCAOT006Activity> getActivity() {
        return activity;
    }

    /**
     * @param activity
     *            the activity to set
     */
    public void setActivity(List<NCAOT006Activity> activity) {
        this.activity = activity;
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

    public boolean isHotActivity() {
        return hotActivity;
    }

    public void setHotActivity(boolean hotActivity) {
        this.hotActivity = hotActivity;
    }

    public String getButtonSwitch() {
        return buttonSwitch;
    }

    public void setButtonSwitch(String buttonSwitch) {
        this.buttonSwitch = buttonSwitch;
    }
}
