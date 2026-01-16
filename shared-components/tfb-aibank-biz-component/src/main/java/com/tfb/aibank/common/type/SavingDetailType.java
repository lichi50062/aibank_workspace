/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import java.util.Arrays;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)SavingDetailType.java
* 
* <p>Description: RTWEBP01 repeat type種類</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum SavingDetailType implements IEnum {

    DETAILS("0003", "明細"),

    ACCUMULATE("0004", "累計"),

    NET_VALUE("0005", "淨值");

    private String code;

    private String memo;

    private SavingDetailType(String code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        return memo;
    }
    
    public static SavingDetailType findByCode(String code) {
        return Arrays.stream(values()).filter(sdt -> StringUtils.equals(sdt.getCode(), code)).findFirst().orElse(null);
    }
}
