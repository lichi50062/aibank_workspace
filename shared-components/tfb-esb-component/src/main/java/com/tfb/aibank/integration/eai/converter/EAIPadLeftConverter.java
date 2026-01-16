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
 * @(#)EAIPadStringConverter.java
 * 
 * <p>Description:往字串左側補位</p>
 * <p>第一個參數：欄位的值</p>
 * <p>第二個參數：指定字串長度</p>
 * <p>第三個參數：補位字元</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/29, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAIPadLeftConverter implements EAIConverter {

    @Override
    public String convertRq(Object... value) {
        return StringUtils.leftPadByByteLength((String) value[0], (Integer) value[1], (String) value[2]);
    }

    @Override
    public String convertRs(Object... value) {
        return StringUtils.leftPadByByteLength((String) value[0], (Integer) value[1], (String) value[2]);
    }
}
