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
package com.tfb.aibank.biz.user.services.linebindcheck.model;

// @formatter:off
/**
 * @(#)LineBindStatusResponse.java
 * 
 * <p>Description:Line綁定狀態檢查</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/18, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LineBindStatusResponse {

    /** LINEBC API errorCode */
    private String errorCode;

    /** LINEBC API errorMessage */
    private String errorMessage;

    /** 綁定狀態 */
    private String bindStatus;

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the bindStatus
     */
    public String getBindStatus() {
        return bindStatus;
    }

    /**
     * @param bindStatus
     *            the bindStatus to set
     */
    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

}
