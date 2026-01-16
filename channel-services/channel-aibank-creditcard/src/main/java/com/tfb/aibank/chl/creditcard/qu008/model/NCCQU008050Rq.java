package com.tfb.aibank.chl.creditcard.qu008.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU008050Rq.java
 * 
 * <p>Description:信用卡分期管理 050 確認頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008050Rq implements RqData {

    /** 選擇方案之分期 */
    private String period;

    /** 選擇方案之分期 描述 */
    private String periodDesc;

    /**
     * 是否帳單流程
     */
    private Boolean isBillProcess;

    /** 利息計算結果 */
    private List<NCCQU008InterestCalResult> interestCalResults;

    /**
     * @return the period
     */
    public String getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * @return the periodDesc
     */
    public String getPeriodDesc() {
        return periodDesc;
    }

    /**
     * @param periodDesc
     *            the periodDesc to set
     */
    public void setPeriodDesc(String periodDesc) {
        this.periodDesc = periodDesc;
    }

    /**
     * @return the isBillProcess
     */
    public Boolean getIsBillProcess() {
        return isBillProcess;
    }

    /**
     * @param isBillProcess
     *            the isBillProcess to set
     */
    public void setIsBillProcess(Boolean isBillProcess) {
        this.isBillProcess = isBillProcess;
    }

    /**
     * @return the interestCalResults
     */
    public List<NCCQU008InterestCalResult> getInterestCalResults() {
        return interestCalResults;
    }

    /**
     * @param interestCalResults
     *            the interestCalResults to set
     */
    public void setInterestCalResults(List<NCCQU008InterestCalResult> interestCalResults) {
        this.interestCalResults = interestCalResults;
    }

}
