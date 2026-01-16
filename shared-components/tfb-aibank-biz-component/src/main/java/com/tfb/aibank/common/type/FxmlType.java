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
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)FxmlType.java
 * 
 * <p>Description:FXML安控類型</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum FxmlType implements IEnum {

    NONE(0, "無"),

    ALL(1, "皆可使用"),

    DEDICATED(2, "專用"),

    SHARE(3, "共用"),

    UNKNOWN(-1, "未知");

    /** 代碼 */
    private int code;

    /** 說明 */
    private String memo;

    FxmlType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static FxmlType find(int code) {

        for (FxmlType group : FxmlType.values()) {

            if (group.getCode() == code) {
                return group;
            }
        }

        return FxmlType.UNKNOWN;

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
     * 是否無FXML安控
     * 
     * @return
     */
    public boolean isNone() {

        return equals(FxmlType.NONE);
    }

    /**
     * 是否為公司憑證皆可使用
     * 
     * @return
     */
    public boolean isAll() {
        return equals(FxmlType.ALL);
    }

    /**
     * 是否為專用公司某一張憑證
     * 
     * @return
     */
    public boolean isDedicated() {
        return equals(FxmlType.DEDICATED);
    }

    /**
     * 是否為共用公司所屬憑證
     * 
     * @return
     */
    public boolean isShare() {
        return equals(FxmlType.SHARE);
    }
}
