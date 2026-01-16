/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NSTOT003041Rq.java
* 
* <p>Description:安控驗證共用 - 驗證交易安控MOTP - RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/16, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT003041Rq implements RqData {

    /** 使用者輸入OTP */
    private String userOtp;

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

}
