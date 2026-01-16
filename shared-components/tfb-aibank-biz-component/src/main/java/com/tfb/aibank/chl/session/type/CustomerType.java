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
 * @(#)CustomerType.java
 *
 * <p>Description: 客戶類型</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyP
 *  <li>客戶類型</li>
 * </ol>
 */
//@formatter:on
public enum CustomerType implements IEnum {

    GENERAL("m1", "一般客戶"),

    CARDMEMBER("m2", "信用卡網路會員"),

    UNKNOWN("unknown", "未知");

    /** 代碼 */
    private String loginType;

    /** 說明 */
    private String memo;

    CustomerType(String loginType, String memo) {
        this.loginType = loginType;
        this.memo = memo;
    }

    public static CustomerType findLoginType(String loginType) {

        for (CustomerType typeValue : CustomerType.values()) {
            if (typeValue.getLoginType().equals(loginType)) {
                return typeValue;
            }
        }
        return CustomerType.UNKNOWN;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    @Override
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 一般會員
     * 
     * @return
     */
    public boolean isGeneral() {
        return equals(CustomerType.GENERAL);
    }

    public static boolean isGeneral(String loginType) {
        return CustomerType.GENERAL.getLoginType().equals(loginType);
    }

    /**
     * 信用卡會員
     * 
     * @return
     */
    public boolean isCardMember() {
        return equals(CustomerType.CARDMEMBER);
    }

    public static boolean isCardMember(String loginType) {
        return CustomerType.CARDMEMBER.getLoginType().equals(loginType);
    }

}
