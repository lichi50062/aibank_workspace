/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.converter;

// @formatter:off
/**
 * @(#)EAIConverter.java
 * 
 * <p>Description:電文欄位轉換處理介面</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public interface EAIConverter {

    /**
     * 轉換上送訊息
     * 
     * @param value
     * @return
     */
    public String convertRq(Object... value);

    /**
     * 轉換下行訊息
     * 
     * @param value
     * @return
     */
    public String convertRs(Object... value);
}
