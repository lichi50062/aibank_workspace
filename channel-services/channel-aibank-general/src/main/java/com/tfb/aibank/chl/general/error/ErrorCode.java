package com.tfb.aibank.chl.general.error;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.id.IBSystemId;

//@formatter:off
/**
 * @(#)ErrorCode.java
 * 
 * <p>Description:通用(NGN)專用錯誤</p>
 * <p>編碼範圍：00501 ~ 01000</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public enum ErrorCode implements IErrorCode {

    FIRST_LOGIN("00501", "首次登入須先變更密碼"),

    IN_CB_TIME("00502", "系統維護中，造成不便請見諒"),

    DUP_LOGIN("00503", "重複登入"),

    REQUIRE_PWD_CHANGE("00504", "密碼到期"),

    USER_NOT_FOUND("00505", "身分證字號或使用者代碼錯誤"),

    PASSWORD_FAIL_COUNT("00506", "密碼輸入錯誤次數超過3次"),

    USER_IS_STOPPED("00507", "該使用者代碼已刪除無法使用"),

    CCUSER_LOGIN_PASSWORD_VALIDATE_ERROR("00508", "密碼輸入錯誤，請重新輸入，若您忘記使用者代碼及密碼，可點選「忘記使用者代碼/密碼」進行重設。"),

    LOGIN_TYPE_ERROR("00509", "登入身份錯誤"),

    IDNUMBER_IS_EMPTY("00510", "請輸入身分證字號"),

    PASSWORD_VALIDATE_ERROR("00511", "密碼驗證時發生錯誤"),

    VALIDATE_COMMON_ERROR("00512", "{0}"),

    PATTERN_NOT_FOUND("00513", "圖形設定發生錯誤"),

    NO_RATE_CURRENCY_DATA("00514", "查無幣別資料"),

    NO_TRANSFER_ACCOUNT_ERROR("01014", "親愛的客戶您好：本服務僅提供持有本行臺幣存款帳戶的行動銀行客戶使用，歡迎您至本行各服務據點申辦。如有疑問，請洽本行客服中心02-8751-6665"),

    UPDATE_USER_CARD_CURRENCY_ERROR("00515", "設定失敗，請稍後再試一次"),

    PATTERN_SETTING_FAIL("00517", "手勢設定失敗，請稍候再試"),

    LOGIN_TYPE_NOT_ALLOW("00518", "登入身份錯誤"),

    CUST_ID_IS_EMPTY("00519", "請輸入身分證字號"),

    CUST_ID_LENGTH_ERROR("00520", "身分證字號長度不符"),

    CUST_ID_ENG_NUM_ONLY("00521", "身分證字號限輸入英數字"),

    CUST_ID_VALIDATE_ERROR("00522", "身分證字號驗證錯誤"),

    USER_ID_IS_EMPTY("00523", "請輸入使用者代碼"),

    USER_ID_LENGTH_ERROR("00524", "使用者代碼長度須為6~10碼"),

    USER_ID_ENG_NUM_ONLY("00525", "使用者代碼限輸入英數字"),

    PWD_IS_EMPTY("00526", "請輸入使用者密碼"),

    USER_ID_LENGTH_FIRST_LOGIN("00527", "使用者代碼長度須為6~10碼；若您為首次登入，請先至網路銀行進行使用者密碼變更"),

    USER_ID_ENG_NUM_FIRST_LOGIN("00528", "使用者代碼限輸入英數字；若您為首次登入，請先至網路銀行進行使用者密碼變更"),

    PWD_LENGTH_FIRST_LOGIN("00529", "使用者密碼長度須為6~16碼；若您為首次登入，請先至網路銀行進行使用者密碼變更"),

    PWD_ENG_NUM_FIRST_LOGIN("00530", "使用者密碼限輸入英數字混雜；若您為首次登入，請先至網路銀行進行使用者密碼變更"),

    LOGIN_NED_CONFIRM("00531", "行銀登入需使用者確認"),

    LOGIN_FAIL("00532", "身分證字號或使用者代碼錯誤。如有疑問，請洽客戶服務專線02-8751-6665"),

    USER_ID_DUPLICATE_WITH_ID("00533", "使用者代碼不可與「身分證字號/統一編號」全部或部分重複"),

    USER_ID_CANOT_SAME("00534", "使用者代碼不可與原使用者代碼相同"),

    FAST_LOGIN_FAIL("00535", "快登檢核失敗"),

    SYSTE_IS_BUSY("00536", "目前系統忙碌中，暫停登入，請稍後再試"),

    INBOUND_DATA_EMPTY("00537", "上行資料遺失"),

    INBOUND_DATA_INVALID("00538", "上行資料超出合理範圍"),

    PWD_LOGIN_REQUIRED("00539", "變更過使用者代碼或使用者密碼註記，需以帳密登入"),

    CORP_PWD_LOGIN_REQUIRED("00540", "變更過使用者代碼或使用者密碼註記，需以帳密登入"),

    CC_USER_NO_ACCT("00545", "請先申辦帳戶, 本服務僅供帳戶使用。可於營業時間至分行辦理"),

    CC_USER_NOT_ALLOWED_ACC("00546", "很抱歉，信用卡會員無法使用多元版行動銀行"),

    TWO_FACTOR_FAILED("00547", "登入驗證失敗"),

    TWO_FACTOR_TIMEOUT("00548", "登入驗證已逾時"),

    NIGHT_MODE_QUERY("00599", "[程式端使用]關帳時段查詢"),

    ;

    /** 異常資料 */
    private ErrorStatus error = null;

    ErrorCode(String errorCode, String memo) {
        this(errorCode, memo, SeverityType.ERROR);
    }

    ErrorCode(String errorCode, String memo, SeverityType severity) {
        error = new ErrorStatus(IBSystemId.SVC.getSystemId(), errorCode, severity, memo);
    }

    /**
     * 取得系統代碼
     *
     * @return
     */
    public String getSystemId() {
        return error.getSystemId();
    }

    /**
     * 取得 錯誤代碼
     *
     * @return
     */
    public String getErrorCode() {
        return error.getErrorCode();
    }

    /**
     * 取得備註說明
     *
     * @return
     */
    public String getMemo() {
        return error.getErrorDesc();
    }

    @Override
    public ErrorStatus getError() {
        return error;
    }
}