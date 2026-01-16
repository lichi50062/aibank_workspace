package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU008021Rq.java
 * 
 * <p>Description:信用卡分期管理 021 條款頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008021Rq implements RqData {
    /**
     * 是否帳單分期流程
     */
    private Boolean isBillProcess;

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

}
