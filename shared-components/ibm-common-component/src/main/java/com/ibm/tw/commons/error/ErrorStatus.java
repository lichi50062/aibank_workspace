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
package com.ibm.tw.commons.error;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)ErrorStatus.java
 * 
 * <p>Description:異常資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "異常資料")
public final class ErrorStatus implements Serializable {

    private static final int MAX_LINE_LENGTH = 1000000;

    private static final long serialVersionUID = 8759713923578730535L;

    /** 系統代碼 */
    @Schema(description = "系統代碼")
    protected String systemId = "";

    /** 錯誤代碼 */
    @Schema(description = "錯誤代碼")
    protected String errorCode = Constants.STATUS_CODE_SUCCESS;

    /** REST服務名稱 */
    @Schema(description = "REST服務名稱")
    protected String controllerName = "";

    /** ESB error 中的 response header 資料 */
    @Schema(description = "error 中其它訊息")
    private Map<String, String> extras = new HashMap<>();

    private HttpStatus httpStatus;

    /** 額外的代碼 ex:電文X開頭的錯誤代碼 */
    private String extraCode = "";

    /**
     * 狀態等級
     * <ul>
     * <li>ERROR</li>
     * <li>WARNING</li>
     * <li>INFO</li>
     * <li>TIMEOUT</li>
     * </ul>
     */
    @Schema(description = "狀態等級")
    protected SeverityType severity = SeverityType.INFO;

    /** 狀態描述 */
    @Schema(description = "狀態描述")
    protected String errorDesc = "";

    /**
     * Constructor
     */
    public ErrorStatus() {
        super();
    }

    public ErrorStatus(String errorCode) {
        this.systemId = "";
        this.errorCode = errorCode;
        this.severity = SeverityType.UNKNOWN;
        this.errorDesc = errorCode;
    }

    public ErrorStatus(String systemId, String errorCode, SeverityType severity, String errorDesc) {
        this.systemId = systemId;
        this.errorCode = errorCode;
        this.severity = severity;
        this.errorDesc = errorDesc;
    }

    public ErrorStatus(String systemId, String errorCode, SeverityType severity, String errorDesc, String controllerName) {
        this.systemId = systemId;
        this.errorCode = errorCode;
        this.severity = severity;
        this.errorDesc = errorDesc;
        this.controllerName = controllerName;
    }

    public ErrorStatus(IErrorCode code) {
        ErrorStatus error = code.getError();
        this.systemId = error.getSystemId();
        this.errorCode = error.getErrorCode();
        this.severity = error.getSeverity();
        this.errorDesc = error.getErrorDesc();
    }

    /**
     * @see #systemId
     * @return
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * @see #systemId
     * @param systemId
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * @see #errorCode
     * @return
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @see #errorCode
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @see #severity
     * @return
     */
    public SeverityType getSeverity() {
        return severity;
    }

    /**
     * @see #severity
     * @param severity
     */
    public void setSeverity(SeverityType severity) {
        this.severity = severity;
    }

    /**
     * @return
     */
    public String getErrorDesc() {
        return errorDesc;
    }

    /**
     * @param controllerName
     *            the controllerName to set
     */
    // #4504 0823 System Information Leak: External 暫時修改
    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    /**
     * @param errorDesc
     *            the errorDesc to set
     */
    // #4504 0823 System Information Leak: External 暫時修改
    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @Schema(hidden = true)
    public String getErrorPrefix() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(this.systemId).append("-").append(this.errorCode).append("]");
        return sb.toString();
    }

    @Schema(hidden = true)
    public boolean isSuccess() {
        return !isError();
    }

    /**
     * 是否為Timeout
     *
     * @return
     */
    @Schema(hidden = true)
    public boolean isTimeout() {
        return getSeverity() == SeverityType.TIMEOUT;
    }

    /**
     * 依據ErrorCode判斷是否為失敗交易
     *
     * 若ErrorCode為null, 空值, 或"0"則為成功 否則為失敗
     *
     * @param errorCode
     * @return
     */
    @Schema(hidden = true)
    public boolean isError() {
        return (!StringUtils.isBlank(errorCode) && !errorCode.matches(Constants.STATUS_CODE_SUCCESS));
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (null == obj) {
            return false;
        }

        if (getClass() != obj.getClass()) {

            return false;
        }

        return hashCode() == obj.hashCode();

    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder(17, 37);

        builder.append(systemId);
        builder.append(errorCode);

        return builder.toHashCode();
    }

    @Override
    public String toString() {
     // @formatter:off
        return "system id = " + StringUtils.left(systemId, 20) 
        + ", error code = " + StringUtils.left(errorCode, 10) 
        + ", severity = " + (severity == null ? "" : StringUtils.left(severity.name(), 10)) 
        + ", desc = " + StringUtils.left(errorDesc, MAX_LINE_LENGTH) 
        + ", controllerName = " + StringUtils.left(controllerName, 100);
     // @formatter:on
    }

    public HttpStatus getHttpStatus() {
        if (httpStatus != null) {
            return httpStatus;
        }
        if (severity == SeverityType.ERROR) {
            return HttpStatus.BAD_REQUEST;
        }
        else if (severity == SeverityType.TIMEOUT) {
            return HttpStatus.SERVICE_UNAVAILABLE;
        }
        else if (severity == SeverityType.INFO) {
            return HttpStatus.OK;
        }
        else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getControllerName() {
        return controllerName;
    }

    /**
     * @return the resHeaderMap
     */
    public Map<String, String> getExtras() {
        return extras;
    }

    /**
     * @param resHeaderMap
     *            the resHeaderMap to set
     */
    public void setExtras(Map<String, String> resHeaderMap) {
        this.extras = resHeaderMap;
    }

    public static ErrorStatus success() {
        return new ErrorStatus(IBSystemId.SVC.getSystemId(), Constants.STATUS_CODE_SUCCESS, SeverityType.INFO, "OK");
    }

    public String getExtraCode() {
        return extraCode;
    }

    public void setExtraCode(String extraCode) {
        this.extraCode = extraCode;
    }
}
