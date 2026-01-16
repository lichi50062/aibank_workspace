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

//@formatter:off
/**
* @(#)UpdateDataSyncStatusType.java
* 
* <p>Description: 更新資料彙整種類</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum UpdateDataSyncStatusType implements IEnum {

    UPDATE_SECUR_TYPE(1, "富邦證券彙整註記異動"),

    UPDATE_INSUR_TYPE(2, "富邦人壽彙整註記異動"),

    UPDATE_SECUR_INSUR_TYPE(3, "富邦證券/人壽彙整註記異動");

    private Integer code;

    private String memo;

    private UpdateDataSyncStatusType(Integer code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public static UpdateDataSyncStatusType findByCode(Integer id) {
        return Arrays.stream(values()).filter(udsst -> udsst.getCode().equals(id)).findFirst().orElse(null);
    }
}
