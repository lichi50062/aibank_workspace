/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2011.
 *
 * ===========================================================================
 */
package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)CompanyStatusType.java
 * 
 * <p>Description:公司狀態</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum CompanyStatusType implements IEnum {

    ALIVE(1, "正常"),

    PAUSED(0, "暫停"),

    DELETED(-1, "註銷"),

    VIRTUAL(-2, "虛擬公司（未申請網銀的關係戶）"),

    UNKNOWN(UNKNOWN_INT_CODE, "未知");

    /** 代碼 */
    private int code;

    /** 說明 */
    private String memo;

    CompanyStatusType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static CompanyStatusType find(int code) {
        for (CompanyStatusType type : CompanyStatusType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return CompanyStatusType.UNKNOWN;
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
     * 是否正常使用
     * 
     * @return
     */
    public boolean isAlive() {
        return equals(ALIVE);
    }

    /**
     * 是否暫停使用
     * 
     * @return
     */
    public boolean isPaused() {
        return equals(PAUSED);
    }

    /**
     * 是否已經註銷
     * 
     * @return
     */
    public boolean isDeleted() {
        return equals(DELETED);
    }

    /**
     * 是否虛擬公司（未申請網銀的關係戶）
     * 
     * @return
     */
    public boolean isVirtual() {
        return equals(VIRTUAL);
    }
}
