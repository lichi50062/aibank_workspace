/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author john
 *
 */
public class FidoQueryLogResponseContentFidoParamsBean {

    /** 錯誤碼 */
    @SerializedName("VerifyCode")
    private String verifyCode;

    /** 錯誤訊息 */
    @SerializedName("VerifyMsg")
    private String verifyMsg;

    /**
     * @return the verifyCode
     */
    public String getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode
     *            the verifyCode to set
     */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * @return the verifyMsg
     */
    public String getVerifyMsg() {
        return verifyMsg;
    }

    /**
     * @param verifyMsg
     *            the verifyMsg to set
     */
    public void setVerifyMsg(String verifyMsg) {
        this.verifyMsg = verifyMsg;
    }

}
