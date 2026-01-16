package com.tfb.aibank.chl.creditcardactivities.ot006.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCOT006020Rs.java
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
public class NCAOT006020Rs implements RsData {

    /** 失敗活動 */
    private List<NCAOT006Activity> failactivity;

    /** 成功活動 */
    private List<NCAOT006Activity> successactivity;

    /** 處理時間 */
    private String txTime;

    /** 觸發人數上限 */
    private Boolean isExceedLimit;

    /**
     * @return the failactivity
     */
    public List<NCAOT006Activity> getFailactivity() {
        return failactivity;
    }

    /**
     * @param failactivity
     *            the failactivity to set
     */
    public void setFailactivity(List<NCAOT006Activity> failactivity) {
        this.failactivity = failactivity;
    }

    /**
     * @return the successactivity
     */
    public List<NCAOT006Activity> getSuccessactivity() {
        return successactivity;
    }

    /**
     * @param successactivity
     *            the successactivity to set
     */
    public void setSuccessactivity(List<NCAOT006Activity> successactivity) {
        this.successactivity = successactivity;
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

    public Boolean getExceedLimit() {
        return isExceedLimit;
    }

    public void setExceedLimit(Boolean exceedLimit) {
        isExceedLimit = exceedLimit;
    }
}
