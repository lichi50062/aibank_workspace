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
 * @(#)UserType.java
 * 
 * <p>Description:使用者類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum UserType implements IEnum {

    UAA_ROOT0(0, "TYPE0預設使用者"),

    UAA_ROOT1(1, "授權管理者"),

    UAA_ROOT2(2, "授權主管"),

    USER_DEFINED(9, "使用者自行定義"),

    UNKNOWN(-1, "未知");

    /** 代碼 */
    private int code;

    /** 說明 */
    private String memo;

    UserType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static UserType find(int code) {

        for (UserType group : UserType.values()) {

            if (group.getCode() == code) {
                return group;
            }
        }

        return UserType.UNKNOWN;

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
     * 是否為TYPE0預設使用者
     * 
     * @return
     */
    public boolean isUaaRoot0() {
        return equals(UserType.UAA_ROOT0);
    }

    /**
     * 是否為授權管理者（TYPE1時編輯者）
     * 
     * @return
     */
    public boolean isUaaRoot1() {
        return equals(UserType.UAA_ROOT1);
    }

    /**
     * 是否為授權主管 （TYPE2時放行者）
     * 
     * @return
     */
    public boolean isUaaRoot2() {
        return equals(UserType.UAA_ROOT2);
    }

    /**
     * 是否為使用者自行定義
     * 
     * @return
     */
    public boolean isUserDefined() {
        return equals(UserType.USER_DEFINED);
    }

}
