/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

/**
 * @author john
 *
 */
public class LoginResponse {

    /** 交易序號 */
    private String txid;

    /** 狀態 */
    private String status;

    /** 內容 */
    private String content;

    /** 錯誤碼 */
    private String error;

    /** 錯誤訊息 */
    private String error_description;

    /** 驗證編號 */
    private String verifyNo;

    /** 通行證 */
    private String token;

    /** SDK 資料 */
    private String data;

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
     * @return the verifyNo
     */
    public String getVerifyNo() {
        return verifyNo;
    }

    /**
     * @param verifyNo
     *            the verifyNo to set
     */
    public void setVerifyNo(String verifyNo) {
        this.verifyNo = verifyNo;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token
     *            the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

}
