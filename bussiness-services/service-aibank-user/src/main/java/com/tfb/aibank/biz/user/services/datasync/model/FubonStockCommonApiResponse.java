/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.datasync.model;

import com.google.gson.annotations.SerializedName;

/**
 * @(#)FubonStockCommonApiRequest.java
 *
 * <p>Description:富邦證券 API 回應外層資料</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/07/30, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class FubonStockCommonApiResponse<T> {

    @SerializedName("responseTimeStamp")
    private String responseTimeStamp;

    @SerializedName("statusCode")
    private String statusCode;

    @SerializedName("statusMsg")
    private String statusMsg;

    @SerializedName("clientResponse")
    private T clientResponse;

    public String getResponseTimeStamp() {
        return responseTimeStamp;
    }

    public void setResponseTimeStamp(String responseTimeStamp) {
        this.responseTimeStamp = responseTimeStamp;
    }

    public T getClientResponse() {
        return clientResponse;
    }

    public void setClientResponse(T clientResponse) {
        this.clientResponse = clientResponse;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
