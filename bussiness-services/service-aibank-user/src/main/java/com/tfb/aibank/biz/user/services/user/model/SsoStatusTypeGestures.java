package com.tfb.aibank.biz.user.services.user.model;

import com.ibm.tw.commons.type.IEnum;
import com.ibm.tw.commons.util.StringUtils;

public enum SsoStatusTypeGestures implements IEnum {

    SUCCESS("0000", "成功"),

    DEVICE_NOT_BINDED("2000", "尚未綁定快速登入"),

    FAST_LOGIN_FAIL_1("2001", "登入驗證失敗或Timeout_1次"),

    FAST_LOGIN_FAIL_2("2002", "登入驗證失敗或Timeout_2次"),

    FAST_LOGIN_LOACKED("2003", "使用者密碼驗證失敗超過三次，帳戶已鎖定"),

    UNKNOWN("9999", "系統異常，請回原頁面進行驗證")

    ;

    SsoStatusTypeGestures(String statusCode, String memo) {
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

        if (StringUtils.equals(SsoStatusTypeGestures.SUCCESS.getStatusCode(), status)) {
            return true;
        }
        return false;
    }

    public static SsoStatusTypeGestures find(String statusCode) {
        for (SsoStatusTypeGestures group : SsoStatusTypeGestures.values()) {

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
