/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)QueryVerifyResultResponse.java
* 
* <p>Description:FIDO QueryVerifyResult Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/09, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class QueryVerifyResultResponse {

    /** 交易序號 */
    @Schema(description = "交易序號")
    private String txid;

    /** 狀態 */
    @Schema(description = "狀態")
    private String status;

    /** 內容 */
    @Schema(description = "內容")
    private String content;

    /** 錯誤碼 */
    @Schema(description = "錯誤碼")
    private String error;

    /** 錯誤訊息 */
    @Schema(description = "錯誤訊息")
    private String error_description;

    /** 應用組合 */
    @Schema(description = "應用組合")
    private String action;

    /** 選擇的驗證方式 */
    @Schema(description = "選擇的驗證方式")
    private String selectType;

    /** 驗證時間 */
    @Schema(description = "驗證時間")
    private String verifyTime;

    /** 簽章內文 */
    @Schema(description = "簽章內文")
    private String plaintext;

    /** 錯誤碼 */
    @Schema(description = "錯誤碼")
    private String verifyCode;

    /** 錯誤訊息 */
    @Schema(description = "錯誤訊息")
    private String verifyMsg;

    /**
     * @return the txid
     */
    public String getTxid() {
        return txid;
    }

    /**
     * @param txid
     *            the txid to set
     */
    public void setTxid(String txid) {
        this.txid = txid;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * @return the error_description
     */
    public String getError_description() {
        return error_description;
    }

    /**
     * @param error_description
     *            the error_description to set
     */
    public void setError_description(String error_description) {
        this.error_description = error_description;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the selectType
     */
    public String getSelectType() {
        return selectType;
    }

    /**
     * @param selectType
     *            the selectType to set
     */
    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    /**
     * @return the verifyTime
     */
    public String getVerifyTime() {
        return verifyTime;
    }

    /**
     * @param verifyTime
     *            the verifyTime to set
     */
    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    /**
     * @return the plaintext
     */
    public String getPlaintext() {
        return plaintext;
    }

    /**
     * @param plaintext
     *            the plaintext to set
     */
    public void setPlaintext(String plaintext) {
        this.plaintext = plaintext;
    }

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
