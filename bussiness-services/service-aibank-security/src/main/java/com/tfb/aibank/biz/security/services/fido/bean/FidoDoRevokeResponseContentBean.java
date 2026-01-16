/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

/**
 * @author john
 *
 */
public class FidoDoRevokeResponseContentBean {

    /** 執行結果代碼 */
    private String returnCode;

    /** 執行結果訊息 */
    private String returnMsg;

    /** 執行結果細節 */
    private String returnRemark;

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode
     *            the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the returnMsg
     */
    public String getReturnMsg() {
        return returnMsg;
    }

    /**
     * @param returnMsg
     *            the returnMsg to set
     */
    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    /**
     * @return the returnRemark
     */
    public String getReturnRemark() {
        return returnRemark;
    }

    /**
     * @param returnRemark
     *            the returnRemark to set
     */
    public void setReturnRemark(String returnRemark) {
        this.returnRemark = returnRemark;
    }

}
