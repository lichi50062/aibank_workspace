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

// @formatter:off
/**
 * @(#)IErrorCode.java
 * 
 * <p>Description:Error Code Interface</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/03, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@FunctionalInterface
public interface IErrorCode {

    /**
     * 取得錯誤資料
     * 
     * @return
     */
    public ErrorStatus getError();

}
