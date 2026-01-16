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
package com.tfb.aibank.chl.type;

import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)RoleType.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum RoleType {
    /** 經辦 */
    AGENT("A", "經辦", "1"),

    /** 主管 */
    MANAGER("M", "主管", "2"),

    DUAL("A,M", "雙身分", null),

    THREE_SIXTY_ONE("P", "361權限設定", "3"),

    GENERAL_USER("GU", "一般用戶", ""),

    READER_USER("CU", "讀卡機用戶", "C"),

    UNKNOWN;

    private String type;

    private String desc;

    private String authUserType;

    RoleType(String type, String desc, String authUserType) {
        this.type = type;
        this.desc = desc;
        this.authUserType = authUserType;
    }

    RoleType() {
    }

    public static RoleType findByType(String type) {
        for (RoleType enumType : RoleType.values()) {
            if (StringUtils.equals(enumType.type, type)) {
                return enumType;
            }
        }
        return UNKNOWN;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    public String getAuthUserType() {
        return authUserType;
    }

}
