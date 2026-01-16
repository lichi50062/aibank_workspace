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

/**
 * <p>
 * 加解密異常物件
 * </p>
 *
 * @author jeff
 * @version 1.0, 2011/4/12
 * @see
 * @since
 */
public class CryptException extends Exception {

    /** <code>serialVersionUID</code> 的註解 */
    private static final long serialVersionUID = 4751841272300462557L;

    public CryptException(String msg) {
        super(msg);
    }

    public CryptException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
