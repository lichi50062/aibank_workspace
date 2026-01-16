/*
 * =========================================================================== IBM Confidential AIS Source Materials (C) Copyright IBM Corp. 2021. ===========================================================================
 */
package com.tfb.aibank.biz.security.services.fido.bean;

import java.io.Serializable;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;

/**
 * <p>
 * 共用WebServiceRsBean
 * </p>
 * 
 * @author HankChan
 * @version 1.0, Mar 23, 2021
 * @see
 * @since
 */
public abstract class BaseWebServiceRsBean implements Serializable {

    /** 序列版本Id */
    private static final long serialVersionUID = 1L;

    /** 與後端連線Timeout */
    private boolean isConnectionTimeout;

    /** 與後端連線錯誤 */
    private boolean isServerError;

    /** Client端錯誤 */
    private boolean isClientError;

    /** Error */
    private ActionException proxyError;

    /**
     * 取得isConnectionTimeout
     * 
     * @return isConnectionTimeout
     * @see #isConnectionTimeout
     */
    public boolean isConnectionTimeout() {
        return isConnectionTimeout;
    }

    /**
     * 設定 isConnectionTimeout
     * 
     * @param isConnectionTimeout
     * @see #isConnectionTimeout
     */
    public void setConnectionTimeout(boolean isConnectionTimeout) {
        this.isConnectionTimeout = isConnectionTimeout;
    }

    /**
     * 取得isServerError
     * 
     * @return isServerError
     * @see #isServerError
     */
    public boolean isServerError() {
        return isServerError;
    }

    /**
     * 設定 isServerError
     * 
     * @param isServerError
     * @see #isServerError
     */
    public void setServerError(boolean isServerError) {
        this.isServerError = isServerError;
    }

    /**
     * 取得isClientError
     * 
     * @return isClientError
     * @see #isClientError
     */
    public boolean isClientError() {
        return isClientError;
    }

    /**
     * 設定 isClientError
     * 
     * @param isClientError
     * @see #isClientError
     */
    public void setClientError(boolean isClientError) {
        this.isClientError = isClientError;
    }

    /**
     * 取得proxyError
     * 
     * @return proxyError
     * @see #proxyError
     */
    public ActionException getProxyError() {
        return proxyError;
    }

    /**
     * 設定 proxyError
     * 
     * @param proxyError
     * @see #proxyError
     */
    public void setProxyError(ActionException proxyError) {
        this.proxyError = proxyError;
    }

    /**
     * 是否為成功
     * 
     * @return
     */
    public abstract boolean isSuccess();

    /**
     * 是否為逾時
     * 
     * @return
     */
    public abstract boolean isTimeout();

    /**
     * 是否為失敗
     * 
     * @return
     */
    public abstract boolean isFail();

    /**
     * 錯誤訊息代碼
     */
    protected abstract String getErrorCode();

    /**
     * 錯誤訊息
     */
    protected abstract String getErrorDesc();

    protected abstract String getErrorSystemId();

    /**
     * 取得錯誤物件
     * 
     * @return
     */
    public ErrorStatus getErrorStatus() {

        // 任何非主機回傳的錯誤
        if (proxyError != null) {

            return proxyError.getStatus();
        }

        // 交易錯誤
        if (!isSuccess()) {

            // 不為成功又沒有錯誤代碼
            if (StringUtils.isBlank(getErrorCode())) {

                return CommonErrorCode.UNKNOWN_EXCEPTION.getError();
            }

            return new ErrorStatus(getErrorSystemId(), getErrorCode(), SeverityType.ERROR, getErrorDesc());
        }

        return null;
    }

}
