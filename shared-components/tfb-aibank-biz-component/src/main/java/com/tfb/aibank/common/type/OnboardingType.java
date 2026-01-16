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

// @formatter:off
/**
 * @(#)OnboardingType.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/27, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum OnboardingType {

    AIBANK(1, "一般用戶旅程"),

    TRANSFER_FROM_MB(2, "原行動銀行導入無痛移轉旅程"),

    TRANSFER_BY_AIBANK(3, "AIBank啟動導入無痛移轉旅程");

    /** 狀態碼* */
    private int code;

    /** 狀態說明* */
    private String memo;

    OnboardingType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public static boolean isTransfer(int code) {
        if (code >= OnboardingType.TRANSFER_FROM_MB.code) {
            return true;
        }
        return false;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

}
