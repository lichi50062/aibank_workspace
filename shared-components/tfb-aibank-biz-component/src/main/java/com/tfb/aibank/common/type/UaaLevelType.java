/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2010.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)UaaLevelType.java
 * 
 * <p>Description:申請的授權中心類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum UaaLevelType implements IEnum {

    TYPE0(0, "無需授權中心"),

    TYPE1(1, "啟用授權中心，並建立一位授權管理員"),

    TYPE2(2, "啟用授權中心，權限層級為二層"),

    TYPE3(3, "啟用授權中心，權限層級為三層"),

    UNKNOWN(-1, "未知");

    /** 代碼 */
    private int code;

    /** 說明 */
    private String memo;

    UaaLevelType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static UaaLevelType find(int code) {

        for (UaaLevelType group : UaaLevelType.values()) {

            if (group.getCode() == code) {
                return group;
            }
        }

        return UaaLevelType.UNKNOWN;

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 是否為TYPE0
     *
     * @return
     */
    public boolean isType0() {
        return equals(UaaLevelType.TYPE0);
    }

    /**
     * 是否為TYPE1
     *
     * @return
     */
    public boolean isType1() {
        return equals(UaaLevelType.TYPE1);
    }

    /**
     * 是否為TYPE2
     *
     * @return
     */
    public boolean isType2() {
        return equals(UaaLevelType.TYPE2);
    }

    public boolean isType3() {
        return equals(UaaLevelType.TYPE3);
    }

}
