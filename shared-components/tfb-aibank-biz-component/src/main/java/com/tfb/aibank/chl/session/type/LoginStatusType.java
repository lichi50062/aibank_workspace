/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.session.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)LoginStatusType.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, John
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum LoginStatusType implements IEnum {

    NEVER(-1, "從未登入"),

    LOCK(-2, "使用鎖定"),

    LOGOUT(0, "正常登出"),

    LOGIN(1, "已經登入"),

    UNKNOWN(-9, "未知");

    /** 代碼 */
    private int code;

    /** 說明 */
    private String memo;

    LoginStatusType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static LoginStatusType find(int code) {

        for (LoginStatusType group : LoginStatusType.values()) {

            if (group.getCode() == code) {
                return group;
            }
        }

        return LoginStatusType.UNKNOWN;

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
     * 是否為從未登入
     *
     * @return
     */
    public boolean isNever() {
        return equals(LoginStatusType.NEVER);
    }

    /**
     * 是否為使用鎖定
     *
     * @return
     */
    public boolean isLock() {
        return equals(LoginStatusType.LOCK);
    }

    /**
     * 是否為正常登出
     *
     * @return
     */
    public boolean isLogout() {
        return equals(LoginStatusType.LOGOUT);
    }

    /**
     * 是否為已經登入
     *
     * @return
     */
    public boolean isLogin() {
        return equals(LoginStatusType.LOGIN);
    }

}
