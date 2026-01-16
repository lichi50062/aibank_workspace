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

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NSTOT003041Rs.java
* 
* <p>Description:安控驗證共用 - 驗證交易安控MOTP - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/16, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT003041Rs implements RsData {

    /** MOTP驗證結果 成功:0 */
    public static final String MOTP_RESULT_SUCCESS = "0";
    /** MOTP驗證結果 驗證失敗:1 */
    public static final String MOTP_RESULT_VALIDATE_ERROR = "1";
    /** MOTP驗證結果 MOTP失敗:2 , 不再繼續驗證 */
    public static final String MOTP_RESULT_ERROR = "2";

    /** OTP驗證結果 */
    private String motpResult;

    /** OTP驗證錯誤說明 */
    private String motpErrorDesc;

    /**
     * @return the motpResult
     */
    public String getMotpResult() {
        return motpResult;
    }

    /**
     * @param motpResult
     *            the motpResult to set
     */
    public void setMotpResult(String motpResult) {
        this.motpResult = motpResult;
    }

    /**
     * @return the motpErrorDesc
     */
    public String getMotpErrorDesc() {
        return motpErrorDesc;
    }

    /**
     * @param motpErrorDesc
     *            the motpErrorDesc to set
     */
    public void setMotpErrorDesc(String motpErrorDesc) {
        this.motpErrorDesc = motpErrorDesc;
    }

}
