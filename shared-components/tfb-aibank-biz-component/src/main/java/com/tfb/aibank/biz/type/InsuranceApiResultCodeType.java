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
package com.tfb.aibank.biz.type;

import java.util.Arrays;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)InsuranceApiResultCodeType.java
* 
* <p>Description: 富邦人壽 returnCode enum</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/16, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum InsuranceApiResultCodeType implements IEnum {

    SUCCESS("0000", "成功"),

    SYSTEM_ERROR("1001", "系統錯誤"),

    INVALID_SYSTEM_CODE("1002", "無效的系統代號"),

    INSUFFICIENT_PARAMS("1003", "參數不足"),

    TOKEN_EXPIRED("1004", "驗證碼逾期"),

    INVALID_TOKEN("1005", "無效的驗證碼"),

    NON_CUSTOMER("1006", "非客戶"),

    NOT_APPROVED_BY_CUSTOMER("1007", "客戶未同意資料彙整");

    private String code;

    private String memo;

    private InsuranceApiResultCodeType(String code, String memo) {
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

    public static InsuranceApiResultCodeType findByCode(String code) {
        return Arrays.stream(values()).filter(e -> StringUtils.equals(e.getCode(), code)).findFirst().orElse(null);
    }
}
