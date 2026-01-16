package com.tfb.aibank.chl.system.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NSTOT003053Rq.java
* 
* <p>Description:安控驗證共用 - 驗證交易安控 EMAIL-OTP - RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT003053Rq implements RqData {
    private String userEncTxCode;

    private String userTxHash;

    private String userOtp;

    /** 操作安控的交易代號 */
    private String sourceTxnId;

    /**
     * @return the userEncTxCode
     */
    public String getUserEncTxCode() {
        return userEncTxCode;
    }

    /**
     * @param userEncTxCode
     *            the userEncTxCode to set
     */
    public void setUserEncTxCode(String userEncTxCode) {
        this.userEncTxCode = userEncTxCode;
    }

    /**
     * @return the userTxHash
     */
    public String getUserTxHash() {
        return userTxHash;
    }

    /**
     * @param userTxHash
     *            the userTxHash to set
     */
    public void setUserTxHash(String userTxHash) {
        this.userTxHash = userTxHash;
    }

    /**
     * @return the userOtp
     */
    public String getUserOtp() {
        return userOtp;
    }

    /**
     * @param userOtp
     *            the userOtp to set
     */
    public void setUserOtp(String userOtp) {
        this.userOtp = userOtp;
    }

    /**
     * @return the sourceTxnId
     */
    public String getSourceTxnId() {
        return sourceTxnId;
    }

    /**
     * @param sourceTxnId
     *            the sourceTxnId to set
     */
    public void setSourceTxnId(String sourceTxnId) {
        this.sourceTxnId = sourceTxnId;
    }

}
