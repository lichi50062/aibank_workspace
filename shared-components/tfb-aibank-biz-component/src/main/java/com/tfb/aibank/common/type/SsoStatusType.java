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
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)SsoStatusType.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/25, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum SsoStatusType implements IEnum {

    SUCCESS("0000", "成功"),

    NOT_REGISTERED("1000", "平台資訊無效驗證失敗"),

    ID_NOT_MATCH("1001", "傳入ID與綁定ID不相符"),

    GENERAL_USER_ONLY("1002", "此快速驗證功能僅供一般會員使用"),

    CARD_MEMBER_ONLY("1003", "此快速驗證功能僅供信用卡會員使用"),

    AUTH_TYPE_NOT_MATH("1004", "綁定的快速登入與註冊要求不相符"),

    DEVICE_IN_BLACK_LIST("1005", "此裝置為黑名單裝置"),

    ILLEGAL_AUTH_STATUS("1006", "平台資訊無效驗證失敗"),

    DEVICE_NOT_BINDED("2000", "尚未綁定快速登入"),

    FAST_LOGIN_CANCEL("2001", "你已取消快速登入驗證"),

    FAST_LOGIN_FAIL("2002", "登入驗證失敗或Timeout"),

    FAST_LOGIN_LOACKED("2003", "使用者密碼驗證失敗超過三次，帳戶已鎖定"),

    FAST_LOGIN_NO_OTP("2004", "使用者無留存OTP電話"),

    FAST_LOGIN_OTP_FAIL("2005", "OTP驗證錯誤，錯誤三次"),

    FAST_LOGIN_OTP_CANCEL("2006", "使用者取消OTP驗證"),

    CRYPTO_ERROR("3000", "解密資訊不相符、解密失敗，請回原頁面進行驗證"),

    AUTH_EXPIRED("3001", "授權已過期/重複授權，請回原頁面進行驗證"),

    UNKNOWN("9999", "系統異常，請回原頁面進行驗證")

    ;

    SsoStatusType(String statusCode, String memo) {
        this.statusCode = statusCode;
        this.memo = memo;
    }

    /**
     * 成功?
     * 
     * @param status
     * @return
     */
    public static boolean isSuccessful(String status) {

        if (StringUtils.equals(SsoStatusType.SUCCESS.getStatusCode(), status)) {
            return true;
        }
        return false;
    }

    public static SsoStatusType find(String statusCode) {
        for (SsoStatusType group : SsoStatusType.values()) {

            if (group.getStatusCode().equals(statusCode)) {
                return group;
            }
        }
        return UNKNOWN;
    }

    /** 代碼 */
    private String statusCode;

    /** 說明 */
    private String memo;

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.tw.commons.type.IEnum#getMemo()
     */
    @Override
    public String getMemo() {
        return memo;
    }

    /**
     * @return the statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode
     *            the statusCode to set
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static boolean isSuccess(String statusCode) {
        if (SUCCESS.getStatusCode().equals(statusCode)) {
            return true;
        }
        return false;
    }

}
