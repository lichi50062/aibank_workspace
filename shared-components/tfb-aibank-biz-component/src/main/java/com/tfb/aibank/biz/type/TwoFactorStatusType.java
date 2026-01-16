/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.biz.type;

import java.util.Arrays;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

//@formatter:off
/**
* @(#)UpdateTwoFactorActionType.java
* 
* <p>Description: 更新雙重驗證記錄檔 action type</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/27, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum TwoFactorStatusType implements IEnum {

    TIMEOUT("TIMEOUT", "逾時"),

    FAIL("FAIL", "失敗"),

    SUCCESS("SUCCESS", "成功"),
    
    WAIT("WAIT", "待驗證"),
    
    CANCEL("CANCEL", "取消驗證"),
    
    DEFAULT("", "無代號(查詢)");

    private String type;

    private String memo;

    private TwoFactorStatusType(String type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    public String getType() {
        return type;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public static TwoFactorStatusType findByType(String type) {
        return Arrays.stream(values()).filter(t -> StringUtils.equalsIgnoreCase(t.getType(), type)).findFirst().orElse(DEFAULT);
    }

}
