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
package com.ibm.tw.ibmb.exception;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.exception.ActionException;

import org.springframework.http.HttpStatus;

import feign.FeignException;

/**
 * <p>
 * FeignClient 異常物件
 * </p>
 * 
 * 由於 Feign Decoder 只能拋 FeignException, 故將原本 ActionException 再進行封裝
 * 
 * @author Winni
 *
 */
@SuppressWarnings("serial")
public class FeignActionException extends FeignException {

    /** 訊息參數 */
    protected String[] params = null;

    /** 回應狀態 */
    protected ErrorStatus status = null;

    /** chain to next exception */
    protected FeignActionException next = null;

    protected String message;

    /**
     * Constructor
     *
     * @param msg
     * @param status
     * @param cause
     */
    public FeignActionException(ActionException actionException) {
        this(actionException.getStatus());
    }

    /**
     * Constructor
     *
     * @param error
     */
    public FeignActionException(ErrorStatus error) {
        this(error, null);
    }

    /**
     * Constructor
     *
     * @param error
     * @param cause
     */
    public FeignActionException(ErrorStatus error, Throwable cause) {
        this(null, error, cause);
    }

    /**
     * Constructor
     *
     * @param msg
     * @param status
     * @param cause
     */
    public FeignActionException(String msg, ErrorStatus status, Throwable cause) {
        this(msg, status, cause, null);
    }

    /**
     * Constructor
     *
     * @param msg
     * @param status
     * @param cause
     * @param params
     */
    public FeignActionException(String msg, ErrorStatus status, Throwable cause, String[] params) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), status.getErrorCode(), cause);
        this.message = msg;
        this.status = status;
        this.params = params;
    }

    /**
     * 取得 狀態資料
     *
     * @return
     */
    public ErrorStatus getStatus() {
        return status;
    }

}
