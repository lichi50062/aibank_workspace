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
package com.ibm.tw.commons.exception;

import java.util.Collections;
import java.util.Map;

import com.ibm.tw.commons.error.SeverityType;

public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String systemId;
    private String errorDesc;
    private SeverityType severity = SeverityType.INFO;
    private String errorCode;
    private Map<String, String> extraInfo = Collections.emptyMap();

    public ServiceException() {
        // default constructor
    }

    public ServiceException(String systemId, String errorDesc, SeverityType severity, String errorCode) {
        super(errorDesc);
        this.systemId = systemId;
        this.errorDesc = errorDesc;
        this.severity = severity;
        this.errorCode = errorCode;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public SeverityType getSeverity() {
        return severity;
    }

    public void setSeverity(SeverityType severity) {
        this.severity = severity;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the extraInfo
     */
    public Map<String, String> getExtraInfo() {
        return extraInfo;
    }

    /**
     * @param extraInfo
     *            the extraInfo to set
     */
    public void setExtraInfo(Map<String, String> extraInfo) {
        this.extraInfo = extraInfo;
    }

}
