package com.tfb.aibank.chl.system.ot010.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NSTOT010030Rq.java
 * 
 * <p>Description:提高非約轉限額</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/02, John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT010030Rq implements RqData {

    /**
     * txKey
     */
    private String txKxy;

    /**
     * 驗證狀態
     */
    private String status;

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the txKxy
     */
    public String getTxKxy() {
        return txKxy;
    }

    /**
     * @param txKxy
     *            the txKxy to set
     */
    public void setTxKxy(String txKxy) {
        this.txKxy = txKxy;
    }

}
