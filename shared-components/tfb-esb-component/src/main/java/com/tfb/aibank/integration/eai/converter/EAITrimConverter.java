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

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)EAITrimStringConverter.java
 * 
 * <p>Description:移除內容前後「全型空白」和「半型空白」</p>
 * <p>第一個參數：欄位的值</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAITrimConverter implements EAIConverter {

    @Override
    public String convertRq(Object... value) {
        return StringUtils.trimAllBigSpace((String) value[0]);
    }

    @Override
    public String convertRs(Object... value) {
        return StringUtils.trimAllBigSpace((String) value[0]);
    }

}
