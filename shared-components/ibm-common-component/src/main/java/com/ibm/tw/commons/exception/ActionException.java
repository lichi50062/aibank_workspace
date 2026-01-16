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

import java.util.Map;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;

/**
 * <p>
 * 異常物件
 * </p>
 *
 *
 * @author jeff
 * @version 1.0, 2005/08/7
 * @see
 * @since
 */
public class ActionException extends Exception {

    private static final int MAX_LINE_LENGTH = 1000000;

    private static final long serialVersionUID = 1268634697821735201L;

    /** 訊息參數 */
    protected String[] params = null;

    /**
     * 附加訊息參數
     * 
     * <pre>
     * 此變數用途為，當拋出錯誤訊息是要到有按鈕的錯誤引導頁時
     * 若目標Task需要帶參數前往，會以此變數置放參數
     * 【使用範例】
     * 當引導頁的按鈕taskId值為「NMFAG004」時，拋出錯誤的方式如下：
     * Map<String, String> extraParams = new HashMap<>();
     * extraParams.put("NMFAG004", "&taskId=NMFTX001");
     * throw new ActionException(AIBankErrorCode.KYC_NOT_DONE, extraParams);
     * </pre>
     */
    protected Map<String, String> extraParamMap = null;

    /** 回應狀態 */
    protected ErrorStatus status = null;

    /**
     * Constructor
     *
     * @param msg
     * @param status
     * @param cause
     * @param params
     */
    public ActionException(String msg, ErrorStatus status, Throwable cause, String[] params) {
        super(msg, cause);
        this.status = status;
        this.params = params;
    }

    /**
     * Constructor
     *
     * @param msg
     * @param status
     * @param cause
     */
    public ActionException(String msg, ErrorStatus status, Throwable cause) {
        this(msg, status, cause, null);
    }

    /**
     * Constructor
     *
     * @param error
     * @param cause
     */
    public ActionException(ErrorStatus error, Throwable cause) {
        this(error.toString(), error, cause);
    }

    /**
     * Constructor
     *
     * @param error
     */
    public ActionException(ErrorStatus error) {
        this(error, null);
    }

    /**
     * Constructor
     *
     * @param error
     */
    public ActionException(ErrorStatus error, Throwable cause, String[] params) {
        this(error, cause);
        this.params = params;
    }

    /**
     * Constructor
     *
     * @param msg
     * @param error
     * @param cause
     */
    public ActionException(String msg, IErrorCode error, Throwable cause) {
        this(msg, error.getError(), cause);

    }

    /**
     * Constructor
     *
     * @param msg
     * @param error
     */
    public ActionException(String msg, IErrorCode error) {
        this(msg, error, null);
    }

    /**
     * Constructor
     *
     * @param error
     * @param cause
     */
    public ActionException(IErrorCode error, Throwable cause) {
        this(error.getError(), cause);
    }

    /**
     * Constructor
     *
     * @param error
     */
    public ActionException(IErrorCode error, String... params) {
        this(error.getError().toString(), error.getError(), null, params);
    }

    /**
     * Constructor
     *
     * @param error
     */
    public ActionException(IErrorCode error) {
        this(error.getError().toString(), error.getError(), null, null);
    }

    /**
     * Constructor
     *
     * @param systemId
     * @param errorCode
     * @param severity
     * @param errorDesc
     * @param cause
     */
    public ActionException(String systemId, String errorCode, SeverityType severity, String errorDesc, Throwable cause) {
        this(errorDesc, new ErrorStatus(systemId, errorCode, severity, errorDesc), cause);
    }

    /**
     * Constructor
     *
     * @param systemId
     * @param errorCode
     * @param severity
     * @param errorDesc
     * @param cause
     */
    public ActionException(String systemId, String errorCode, SeverityType severity, String errorDesc) {
        this(systemId, errorCode, severity, errorDesc, null);
    }

    public ActionException(String systemId, String errorCode, ErrorStatus error, SeverityType severity, String errorDesc) {
        this(systemId, errorCode, severity, errorDesc, null);
        this.setStatus(error);
    }

    /**
     * Constructor 有i18N置入文字及額外參數Map
     * 
     * @param error
     */
    public ActionException(IErrorCode error, Map<String, String> extraParamMap, String... params) {
        this(error.getError().toString(), error.getError(), null, params);
        this.extraParamMap = extraParamMap;
    }

    /**
     * Constructor 有i18N置入文字及額外參數Map
     * 
     * @param error
     */
    public ActionException(IErrorCode error, Map<String, String> extraParamMap) {
        this(error.getError().toString(), error.getError(), null, null);
        this.extraParamMap = extraParamMap;
    }

    /**
     * Constructor
     *
     * @param e
     */
    public ActionException(ActionException e) {
        super(e.getStatus().toString());
        this.status = e.getStatus();
        this.params = e.getParams();
    }

    public ActionException() {
        super();
    }

    /**
     * 設定 狀態資料
     *
     * @param error
     */
    public final void setStatus(ErrorStatus status) {
        this.status = status;
    }

    /**
     * 取得 狀態資料
     *
     * @return
     */
    public final ErrorStatus getStatus() {
        return status;
    }

    /**
     * 取得異常系統代碼
     *
     * @return
     */
    public final String getSystemId() {
        return status.getSystemId();
    }

    /**
     * 取得錯誤代碼
     *
     * @return
     */
    public final String getErrorCode() {
        return status.getErrorCode();
    }

    public final void setErrorCode(String errorcode) {
        status.setErrorCode(errorcode);
    }

    /**
     * 取得錯誤參數
     *
     * @return
     */
    public final String getErrorParams() {
        return (this.params != null) ? JsonUtils.getJson(this.params) : "";
    }

    /**
     * 設定Severity
     *
     * @param severity
     */
    public void setSeverity(SeverityType severity) {
        status.setSeverity(severity);
    }

    /**
     * 取得Severity
     *
     * @return
     */
    public final SeverityType getSeverity() {
        return status.getSeverity();
    }

    /**
     * 是否為TIMEOUT的錯誤
     *
     * @return
     */
    public final boolean isTimeout() {

        return status.isTimeout();
    }

    @Override
    public String toString() {
        // 應印 Denial of Service: StringBuilder 改用 Text Block 模板
        String template = """
                %s, error system id = %s, error code = %s, severity = %s
                """;
        // 處理各欄位的長度限制
        String superString = super.toString().length() > MAX_LINE_LENGTH ? super.toString().substring(0, MAX_LINE_LENGTH) : super.toString();
        String systemId = StringUtils.left(status.getSystemId(), 20);
        String errorCode = StringUtils.left(status.getErrorCode(), 10);
        String severity = StringUtils.left(status.getSeverity().name(), 10);
        return String.format(template, superString, systemId, errorCode, severity);
    }

    /**
     * 取得 params
     *
     * @return 傳回 params。
     * @see #params
     */
    public final String[] getParams() {
        return params;
    }

    /**
     * 設定 params
     *
     * @param params
     *            要設定的 params。
     * @see #params
     */
    public void setParams(String... params) {
        this.params = params;
    }

    public Map<String, String> getExtraParamMap() {
        return extraParamMap;
    }

    public void setExtraParamMap(Map<String, String> extraParamMap) {
        this.extraParamMap = extraParamMap;
    }

    @Override
    public int hashCode() {
        if (null == status) {
            return super.hashCode();
        }
        HashCodeBuilder builder = new HashCodeBuilder(17, 37);
        builder.append(status.getSystemId());
        builder.append(status.getErrorCode());
        return builder.toHashCode();
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

    public String getErrorDesc() {
        if (status == null) {
            return getMessage();
        }
        return getStatus().getErrorDesc();
    }
}
