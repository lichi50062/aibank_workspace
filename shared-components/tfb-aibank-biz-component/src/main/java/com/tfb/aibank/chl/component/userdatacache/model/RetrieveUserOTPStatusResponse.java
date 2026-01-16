/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

//@formatter:off
/**
* @(#)RetrieveUserOTPStatusResponse.java
* 
* <p>Description:取得使用者OTP狀態 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class RetrieveUserOTPStatusResponse {

    /** 簡訊OTP申請註記 */
    private String otpFlag;

    /** 接收OTP用行動電話 */
    private String otpMobile;

    /** ＯＴＰ 臨櫃申請/網路申請/ＡＴＭ申請 */
    private String otpMobileEmp;

    /**
     * @return the otpFlag
     */
    public String getOtpFlag() {
        return otpFlag;
    }

    /**
     * @param otpFlag
     *            the otpFlag to set
     */
    public void setOtpFlag(String otpFlag) {
        this.otpFlag = otpFlag;
    }

    /**
     * @return the otpMobile
     */
    public String getOtpMobile() {
        return otpMobile;
    }

    /**
     * @param otpMobile
     *            the otpMobile to set
     */
    public void setOtpMobile(String otpMobile) {
        this.otpMobile = otpMobile;
    }

    /**
     * @return the otpMobileEmp
     */
    public String getOtpMobileEmp() {
        return otpMobileEmp;
    }

    /**
     * @param otpMobileEmp
     *            the otpMobileEmp to set
     */
    public void setOtpMobileEmp(String otpMobileEmp) {
        this.otpMobileEmp = otpMobileEmp;
    }

}
