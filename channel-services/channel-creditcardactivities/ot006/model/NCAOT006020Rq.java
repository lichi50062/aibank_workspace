package com.tfb.aibank.chl.creditcardactivities.ot006.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCOT006020Rq.java
 * 
 * <p>Description:信用卡活動登錄 020 結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/12, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCAOT006020Rq implements RqData {

    /** 要登錄的活動 */
    private List<String> activityCode;

    /** 是否為熱門活動 */
    private boolean hotActivity;

    /**
     * @return the activityCode
     */
    public List<String> getActivityCode() {
        return activityCode;
    }

    /**
     * @param activityCode
     *            the activityCode to set
     */
    public void setActivityCode(List<String> activityCode) {
        this.activityCode = activityCode;
    }

    public boolean isHotActivity() {
        return hotActivity;
    }

    public void setHotActivity(boolean hotActivity) {
        this.hotActivity = hotActivity;
    }
}
