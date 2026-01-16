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

import com.ibm.tw.commons.type.IEnum;

// @formatter:off
/**
 * @(#)ResultCodeType.java
 * 
 * <p>Description:交易結果狀態碼</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum ResultCodeType implements IEnum {

    RESULT_OK("OK", "成功", 0),

    RESULT_WARN("WARNING", "警告", 1),

    RESULT_FAILED("FAILED", "失敗", 2),

    RESULT_UNKNOWN("UNKNOWN", "待確認", UNKNOWN_INT_CODE);

    /**
     * 說明
     */
    private String memo;

    /**
     * 結果代碼
     */
    private int resultCode;

    /**
     * ResultCodeType constructor
     * 
     * @param name
     * @param memo
     * @param resultCode
     */
    ResultCodeType(String name, String memo, int resultCode) {
        this.memo = memo;
        this.resultCode = resultCode;
    }

    /**
     * 依傳入代碼找出 enum value
     * 
     * @param code
     * @return
     */
    public static ResultCodeType find(int code) {
        for (ResultCodeType value : ResultCodeType.values()) {
            if (value.getResultCode() == code) {
                return value;
            }
        }
        return ResultCodeType.RESULT_UNKNOWN;
    }

    /**
     * 取得 memo
     * 
     * @return 傳回 memo。
     */
    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * 設定 memo
     * 
     * @param memo
     *            要設定的 memo。
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 取得 resultCode
     * 
     * @return 傳回 resultCode。
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * 設定 resultCode
     * 
     * @param resultCode
     *            要設定的 resultCode。
     */
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

}
