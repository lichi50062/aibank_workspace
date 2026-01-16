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
package com.ibm.tw.ibmb.type;

// @formatter:off
/**
 * @(#)SessionKey.java
 * 
 * <p>Description:Session 的KEY 請統一放在這裡</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum SessionKey {

    LOGIN_USER,

    ALIVE,

    SESSION_DATA,

    DEVICE_IN_BIO_BLACKLIST,

    TEMP_DATA_TO_PASS,

    ONBOARDING_CACHE_KEY,

    TX_AUTH_DATA,

    PROCESS_NUM_LIST_RELEASED,

    EMAIL_OTP,

    TX_SECURITY_GUARD;
}
