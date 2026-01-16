package com.tfb.aibank.chl.creditcard.qu001.model;

import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;

//@formatter:off
/**
* @(#)NCCQQU001PaymentData.java
* 
* <p>Description:信用卡總覽 帳單明細</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCCQQU001PaymentData extends CEW303RResponse {

    /** 帳務年月 X(5) */
    private String LsnYYYMM;
    /** 遲繳狀況 X(1) */
    private String AIDLQS;

    /**
     * @return the lsnYYYMM
     */
    public String getLsnYYYMM() {
        return LsnYYYMM;
    }

    /**
     * @param lsnYYYMM
     *            the lsnYYYMM to set
     */
    public void setLsnYYYMM(String lsnYYYMM) {
        LsnYYYMM = lsnYYYMM;
    }

    /**
     * @return the aIDLQS
     */
    public String getAIDLQS() {
        return AIDLQS;
    }

    /**
     * @param aIDLQS
     *            the aIDLQS to set
     */
    public void setAIDLQS(String aIDLQS) {
        AIDLQS = aIDLQS;
    }

}
