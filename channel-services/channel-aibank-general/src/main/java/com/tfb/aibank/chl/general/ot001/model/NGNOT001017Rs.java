package com.tfb.aibank.chl.general.ot001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* 
* <p>Description:k</p>
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
public class NGNOT001017Rs implements RsData {

    private String phone;

    private String email;

    private String txCode;

    private String userEncTxCode;

    private String userTxHash;

    private boolean canResend;

    private String expiryTime;

    /** 驗證OTP */

    /** OTP驗證結果 */
    private String otpResult;

    /** OTP驗證錯誤說明 */
    private String otpErrorDesc;
    /** OTP驗證錯誤說明(無礙礙) */
    private String otpErrorDescTW;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTxCode() {
        return txCode;
    }

    public void setTxCode(String txCode) {
        this.txCode = txCode;
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

    public boolean isCanResend() {
        return canResend;
    }

    public void setCanResend(boolean canResend) {
        this.canResend = canResend;
    }

    public String getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        this.expiryTime = expiryTime;
    }

    public String getOtpResult() {
        return otpResult;
    }

    public void setOtpResult(String otpResult) {
        this.otpResult = otpResult;
    }

    public String getOtpErrorDesc() {
        return otpErrorDesc;
    }

    public void setOtpErrorDesc(String otpErrorDesc) {
        this.otpErrorDesc = otpErrorDesc;
    }

    public String getOtpErrorDescTW() {
        return otpErrorDescTW;
    }

    public void setOtpErrorDescTW(String otpErrorDescTW) {
        this.otpErrorDescTW = otpErrorDescTW;
    }

}
