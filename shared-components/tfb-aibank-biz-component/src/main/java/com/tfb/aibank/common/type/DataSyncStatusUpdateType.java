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
* @(#)DataSyncStatusUpdateType.java
* 
* <p>Description: 更新資料彙整回覆狀態</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/29, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum DataSyncStatusUpdateType implements IEnum {

    SUCCESS("Y", "成功"),

    NON_CUSTOMER("U", "非客戶"),

    FAIL("N", "失敗");

    private String code;

    private String memo;

    private DataSyncStatusUpdateType(String code, String memo) {
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

    public static DataSyncStatusUpdateType findByCode(String code) {
        return Arrays.stream(values()).filter(dss -> StringUtils.equals(dss.getCode(), code)).findFirst().orElse(null);
    }
}
