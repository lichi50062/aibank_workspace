package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* 
* <p>Description:</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20250610, benson
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT001017Rq implements RqData {

    /* null 0 -> init;send , 1 -> resend, 3 -> verify, 4 -> cancel */
    private Integer action;
    /** OTP Verify */
    private String userEncTxCode;
    /** OTP Verify */
    private String userTxHash;
    /** OTP Verify */
    private String userOtp;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getUserEncTxCode() {
        return userEncTxCode;
    }

    public void setUserEncTxCode(String userEncTxCode) {
        this.userEncTxCode = userEncTxCode;
    }

    public String getUserTxHash() {
        return userTxHash;
    }

    public void setUserTxHash(String userTxHash) {
        this.userTxHash = userTxHash;
    }

    public String getUserOtp() {
        return userOtp;
    }

    public void setUserOtp(String userOtp) {
        this.userOtp = userOtp;
    }

}
