/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

/**
 * @author john
 *
 */
public class FidoBaseResponseBean<T> {

    /** 交易序號 */
    private String txid;

    /** 狀態 */
    private String status;

    /** 內容 */
    private T content;

    /** 錯誤碼 */
    private String error;

    /** 錯誤訊息 */
    private String error_description;

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
    public T getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(T content) {
        this.content = content;
    }

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

}
