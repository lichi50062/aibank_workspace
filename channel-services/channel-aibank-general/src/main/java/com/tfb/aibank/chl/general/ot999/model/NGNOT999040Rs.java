package com.tfb.aibank.chl.general.ot999.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT999040Rs.java 
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
public class NGNOT999040Rs extends NGNOT999Rs implements RsData {

    /** OTP驗證結果 成功:0 */
    public static final String OTP_RESULT_SUCCESS = "0";
    /** OTP驗證結果 驗證失敗:1 */
    public static final String OTP_RESULT_VALIDATE_ERROR = "1";
    /** OTP驗證結果 OTP失敗:2 , 不再繼續驗證 */
    public static final String OTP_RESULT_ERROR = "2";

    /** OTP驗證結果 */
    private String otpResult;

    /** OTP驗證錯誤說明 */
    private String otpErrorDesc;

    /**
     * @return the otpResult
     */
    public String getOtpResult() {
        return otpResult;
    }

    /**
     * @param otpResult
     *            the otpResult to set
     */
    public void setOtpResult(String otpResult) {
        this.otpResult = otpResult;
    }

    /**
     * @return the otpErrorDesc
     */
    public String getOtpErrorDesc() {
        return otpErrorDesc;
    }

    /**
     * @param otpErrorDesc
     *            the otpErrorDesc to set
     */
    public void setOtpErrorDesc(String otpErrorDesc) {
        this.otpErrorDesc = otpErrorDesc;
    }

}
