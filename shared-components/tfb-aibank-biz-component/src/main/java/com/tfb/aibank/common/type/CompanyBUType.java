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

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)CompanyBUType.java
 * 
 * <p>Description:公司BU類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum CompanyBUType implements IEnum {

    DBU(1, "DBU"),

    OBU(2, "OBU"),

    UNKNOWN(UNKNOWN_INT_CODE, "未知");

    /** 代碼 */
    private int code;

    /** 說明 */
    private String memo;

    CompanyBUType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static CompanyBUType find(int code) {
        for (CompanyBUType group : CompanyBUType.values()) {
            if (group.getCode() == code) {
                return group;
            }
        }
        return CompanyBUType.UNKNOWN;
    }

    public static CompanyBUType findByMemo(String memo) throws ActionException {
        return Arrays.stream(values()).filter(x -> StringUtils.equals(x.getMemo(), memo)).findAny().orElse(CompanyBUType.UNKNOWN);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * 是否為DBU客戶
     * 
     * @return
     */
    public boolean isDBU() {
        return equals(DBU);
    }

    /**
     * 是否為OBU客戶
     * 
     * @return
     */
    public boolean isOBU() {
        return equals(OBU);
    }

}
