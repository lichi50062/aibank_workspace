/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

///@formatter:off
/**
* @(#)QueryLogResponse.java
* 
* <p>Description:FIDO Query-Log Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/09, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class QueryLogResponse {

    /** 交易序號 */
    private String txid;

    /** 狀態 */
    private String status;

    /** 錯誤碼 */
    private String error;

    /** 錯誤訊息 */
    private String error_description;

    /** QueryLogResponseRepeat */
    private List<QueryLogResponseRepeat> data;

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
     * @return the data
     */
    public List<QueryLogResponseRepeat> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(List<QueryLogResponseRepeat> data) {
        this.data = data;
    }

}
