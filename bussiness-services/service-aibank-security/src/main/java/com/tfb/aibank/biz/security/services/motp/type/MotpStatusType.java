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
package com.tfb.aibank.biz.security.services.motp.type;

//@formatter:off
/**
* @(#)MotpStatusType.java
* 
* <p>Description:MOTP API回應碼</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/10, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MotpStatusType {

    MOTP_STATUS_OK("0", "成功", "Success"),

    MOTP_URL_CONNECT_ERROR("26801", "網路連結錯誤", "URL Connect Error"),

    MOTP_GENERAL_ERROR("66001", "一般性錯誤", "General Error"),

    MOTP_ARGUMENT_MISSING("66002", "缺少需要的參數", "Argument Missing"),

    MOTP_ILLEGALE_ARGUMENT("66003", "不合法的參數", "ILLEGALE ARGUMENT"),

    MOTP_NO_PERMISSION_FUNCTION("66007", "應用程式無法使用此函式", "User has no permission of this function"),

    MOTP_CHALLENGE_RESPONSE_TOKEN_EXPIRED("66008", "簽章原文已過期, 請重整網頁以取得新的簽章原文", "Challenge-Response token expired"),

    MOTP_SERVLET_ALREADY_RUNNING("66101", "Servlet服務已經啟動，正常執行中", "Servlet already running"),

    MOTP_SERVLET_NOT_START("66102", "Servlet服務未啟動", "Servlet not start"),

    MOTP_USER_PASSWORD_NOT_CORRECT("66105", "使用者的密碼不正確", "User password not correct"),

    MOTP_SERVER_HAS_NO_AVAILABLE_TOKENS("66108", "伺服器沒有可用的TOKEN", "Server has no available tokens"),

    MOTP_SERVER_DOES_NOT_SUPPORT_THE_FUNCTION("66109", "伺服器未支援此項功能", "Server does not support the function"),

    MOTP_OPERATION_IS_NOT_ALLOWED("66110", "因特定情況不允許此操作(情境不同，訊息不同)", "This operation is not allowed due to certain circumstances"),

    MOTP_USER_ACCOUNT_IS_DELETED("66201", "使用者的帳號已刪除", "User account is deleted"),

    MOTP_USER_ACCOUNT_IS_FREEZED("66202", "登入失敗次數太多，使用者的帳號已凍結", "User account is freezed"),

    MOTP_USER_ACOUNT_NOT_EXIST("66203", "使用者的帳號不存在", "User acount not exit"),

    MOTP_ACCOUNT_ALREADY_EXIST("66204", "帳號已存在", "ACCOUNT_ALREADY_EXIST"),

    MOTP_USER_NOT_LOGIN("66205", "使用者未登入", "User not login"),

    MOTP_ACCOUNT_WAS_SUSPENDED("66206", "此帳號已被暫禁", "The account was suspended"),

    MOTP_AUTHENTICATED_FAILED_TOO_MANY("66207", "OTP驗證失敗次數過多, 待延遲時間過後, 方可再回復正常OTP認證", "The account was authenticated failed too many times, you can not verify OTP until delay time expired"),

    MOTP_ACCOUNT_IS_IN_NORMAL_STATUS("66209", "此帳號尚未初始化", "The account is in normal status"),

    MOTP_ACCOUNT_IS_NOT_OTP_USER("66210", "此帳號非OTP使用者", "This account is not an OTP user"),

    MOTP_ACCOUNT_IS_NOT_INIT_STATUS("66211", "帳戶非INIT狀態不可註冊", "This account is not in INIT status."),

    MOTP_PASSWORD_HAS_BEEN_EXPIRED("66212", "使用者臨時密碼已過期", "The account temporary password has been expired"),

    MOTP_PASSWORD_IS_INCORRECT("66213", "使用者臨時密碼錯誤", "The account temporary password is incorrect"),

    MOTP_OTP_INCORRECT("66214", "此帳號的動態密碼不正確", "OTP Incorrect"),

    MOTP_CHALLENGE_NOT_MATCH("66217", "挑戰碼不符合", "challenge not match"),

    MOTP_INVALID_ACCOUNT_OR_PASSWORD("66218", "無效的使用者帳號或密碼", "Invalid account or password"),

    MOTP_ACCOUNT_HAS_NO_REGISTERED_TOKEN("66221", "使用者沒有已註冊的token", "Account has no registered token"),

    MOTP_USER_HAS_NO_UNUSED_TOKEN("66223", "使用者沒有未啟用的token", "The user has no unused token"),

    MOTP_ACCOUNT_DOES_NOT_APPROVE("66228", "帳號不允許", "The account does not approve"),

    MOTP_AUTH_OTHER_TOKEN_FAILED("66229", "驗證其他token失敗", "Auth other token failed"),

    MOTP_TOKEN_IS_ALREADY_SUSPENDED("66231", "載具被暫禁", "Token is already suspended"),

    MOTP_TOKEN_IS_NOT_IN_SUSPEND_STATUS("66232", "載具未被暫禁", "Token is already suspended"),

    MOTP_ACCOUNT_HAS_NO_REGISTERED_TOKEN_IGNORE_IT("66252", "忽略使用者沒有已註冊的token", "Account has no registered token, but servlet ignore it"),

    MOTP_ACCOUNT_WAS_DISABLED("66253", "此帳號已被停用", "The account was disabled"),

    MOTP_AUTHORIZATION_UNALLOWED_IP("66302", "授權檔的使用IP為無效", "Unallowed IP"),

    MOTP_REQUEST_SERVICE_UNALLOWED_IP("66303", "請求服務的IP未被授權", "Unallowed IP"),

    MOTP_SEQUENCE_CHECK_FAIL("66306", "序號控管錯誤", "Sequence check fail"),

    MOTP_TOKEN_EXPIRED("66320", "載具已過期", "Token expired"),

    MOTP_DATABASE_FAIL("66401", "連結資料庫失敗", "DATABASE_FAIL"),

    MOTP_NO_USER_DATA_IN_DATABASE("66402", "資料庫內沒有找到符合的使用者資料", "No user data in database"),

    MOTP_INVALID_CERTIFICATE("66520", "憑證的格式錯誤", "Invalid certificate"),

    MOTP_CERTIFICATE_IS_NOT_EXIST_IN_SYSTEM("66521", "該憑證不存在系統中", "certificate is not exist in system"),

    MOTP_CERT_BAD_SIGNATURE("66522", "憑證簽章有問題", "CERT_BAD_SIGNATURE"),

    MOTP_RETURN_MSG_FORMAT_ERROR("66601", "回傳訊息格式錯誤", "Return MSG FORMAT ERROR"),

    MOTP_PKCS7_FORMAT_ERROR("66710", "PKCS7格式錯誤", "PKCS7_FORMAT_ERROR"),

    MOTP_VERIFY_BAD_CONTENT("66720", "簽章原文錯誤", "VERIFY_BAD_CONTENT"),

    MOTP_UNMATCH_CERT_PRIKEY("66725", "無法找到對應的金鑰", "UNMATCH_CERT_PRIKEY"),

    MOTP_SERVER_OPERATION_FAILED("66901", "推播伺服器操作失敗", "Server operation failed"),

    UNKNOWN_ERROR("99999", "未知的錯誤", "unknown error");

    private String code;

    private String desc;

    private String enDesc;

    private MotpStatusType(String code, String desc, String enDesc) {
        this.code = code;
        this.desc = desc;
        this.enDesc = enDesc;
    }

    public static MotpStatusType findByCode(String code) {
        for (MotpStatusType status : MotpStatusType.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return UNKNOWN_ERROR;
    }

    /**
     * 取得 code
     * 
     * @return 傳回 code。
     */
    public String getCode() {
        return code;
    }

    /**
     * 取得 desc
     * 
     * @return 傳回 desc。
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 取得 enDesc
     * 
     * @return 傳回 enDesc。
     */
    public String getEnDesc() {
        return enDesc;
    }

}
