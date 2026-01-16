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
 * Database Exception
 * </p>
 * 
 * @author Kevin
 * @version 1.0, Jul 15, 2008
 * @see
 * @since
 */
public class DatabaseException extends Exception {

    private static final long serialVersionUID = -3671274381181538749L;

    /**
     * Constructor
     */
    public DatabaseException() {
        super();
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     * 
     * @param sMsg
     * @param cause
     */
    public DatabaseException(String sMsg, Throwable cause) {
        super(sMsg, cause);
    }

    /**
     * Constructor
     * 
     * @param sMsg
     */
    public DatabaseException(String sMsg) {
        super(sMsg);
    }

    @Override
    public String toString() {

        Throwable th = getCause();
        StringBuilder sb = new StringBuilder(super.toString());
        if (th != null) {
            sb.append(", caused = " + th);
        }

        return sb.toString();
    }
}
