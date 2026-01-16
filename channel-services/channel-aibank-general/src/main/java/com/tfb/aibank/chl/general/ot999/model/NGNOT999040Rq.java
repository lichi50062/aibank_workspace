package com.tfb.aibank.chl.general.ot999.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NGNOT999040Rq.java 
* 
* <p>Description:快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
public class NGNOT999040Rq implements RqData {
    private String userEncTxCode;

    private String userTxHash;

    private String userOtp;

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
}
