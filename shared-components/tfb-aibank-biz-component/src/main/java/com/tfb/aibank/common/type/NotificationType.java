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
 * @(#)NotificationType.java
 * 
 * <p>Description:通知類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum NotificationType implements IEnum {

    SYSTEM(0, "系統"),

    EMAIL(1, "電子郵件"),

    SMS(2, "簡訊"),

    BILL_HUNTER(3, "Bill Hunter"),

    MAIL_HUNTER(4, "Mail Hunter"),

    FAX(5, "傳真"),

    PERSONAL(6, "個人訊息");

    /** 狀態碼* */
    private int code;

    /** 狀態說明* */
    private String memo;

    /**
     * Constructor
     *
     * @param statusCode
     * @param memo
     */
    NotificationType(int code, String memo) {
        this.code = code;
        this.memo = memo;
    }

    public int getCode() {
        return code;
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

}