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

/**
 * @(#)FubonStockCommonApiRequest.java
 *
 * <p>Description:富邦證券 API 請求外層資料</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/07/30, Peter
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
public class FubonStockCommonApiRequest<T> {

    T clientRequest;

    public T getClientRequest() {
        return clientRequest;
    }

    public void setClientRequest(T clientRequest) {
        this.clientRequest = clientRequest;
    }

    public FubonStockCommonApiRequest(T clientRequest) {
        this.clientRequest = clientRequest;
    }
}
