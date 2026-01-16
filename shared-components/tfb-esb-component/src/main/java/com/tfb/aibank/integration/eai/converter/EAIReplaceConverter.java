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

import org.apache.commons.lang3.RegExUtils;

// @formatter:off
/**
 * @(#)EAITrimCommaConverter.java
 * 
 * <p>Description:移除內容中的 searchString</p>
 * <p>第一個參數：欄位的值</p>
 * <p>第二個參數：searchString</p>
 * <p>第三個參數：replacement</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAIReplaceConverter implements EAIConverter {

    @Override
    public String convertRq(Object... value) {
        return RegExUtils.replaceAll((String) value[0], (String) value[1], (String) value[2]);
    }

    @Override
    public String convertRs(Object... value) {
        return RegExUtils.replaceAll((String) value[0], (String) value[1], (String) value[2]);
    }

}
