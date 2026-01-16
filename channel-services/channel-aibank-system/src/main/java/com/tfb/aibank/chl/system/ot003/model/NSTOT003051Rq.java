package com.tfb.aibank.chl.system.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NSTOT003051Rq.java
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
public class NSTOT003051Rq implements RqData {
    /** 信箱 */
    private String email;

    /** 操作安控的交易代號 */
    private String sourceTxnId;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
