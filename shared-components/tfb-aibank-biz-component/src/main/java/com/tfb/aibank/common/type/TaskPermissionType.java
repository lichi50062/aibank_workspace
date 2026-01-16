package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)TaskPermissionType.java
 * 
 * <p>Description:交易權限類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/25, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum TaskPermissionType implements IEnum {

    NONE(0, "無權限"),

    TYPE0(1, "TYPE0 客戶不檢核權限"),

    RELATION(2, "TYPE0且子公司授權此群組"),

    NOT_CONTROL(11, "此交易不做權限控管"),

    AUTO(12, "使用者自動擁有此功能（如授權中心管理者自動擁有授權中心功能）"),

    ROLE(13, "使用者透過角色功能權限設定，擁有此功能權限"),

    UNKNOWN(UNKNOWN_INT_CODE, "未知");

    /** 代碼 */
    private int code;

    /** 說明 */
    private String memo;

    TaskPermissionType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static TaskPermissionType find(int code) {
        for (TaskPermissionType group : TaskPermissionType.values()) {
            if (group.getCode() == code) {
                return group;
            }
        }
        return TaskPermissionType.UNKNOWN;
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
     * 是否有交易權限
     * 
     * @return
     */
    public boolean hasPermission() {
        return code > 0;
    }

    /**
     * 是否不限制權限
     * 
     * @return
     */
    public boolean isDisable() {
        return (equals(AUTO)) || (equals(NOT_CONTROL));
    }
}
