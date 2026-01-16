/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.component.whitelist;

// @formatter:off
/**
 * @(#)WhitListCheckResponse.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/29, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class WhitListCheckResponse {

    /**
     * 狀態值
     */
    private String status;
    /**
     * 狀態描述
     */
    private String statusDesc;

    public WhitListCheckResponse() {

    }

    public WhitListCheckResponse(String status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
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
     * @return the statusDesc
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * @param statusDesc
     *            the statusDesc to set
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

}
