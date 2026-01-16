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

// @formatter:off
/**
 * @(#)OtpStatusType.java
 * 
 * <p>Description:簡訊OTP驗證狀態</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum OtpStatusType {

    VERIFIED_OK("0", "檢核通過"),

    INIT_OK("1", "初始化成功"),

    ADD_SEED_OK("2", "設定簡訊動態密碼生成因子成功"),

    ACTIVATE_OTP_OK("3", "啟用簡訊動態密碼成功"),

    RESEND("4", "已重送"),

    ERROR("100", "錯誤狀態起號"),

    EXPIRED_ERROR("101", "簡訊動態密碼逾時"),

    BLANK_SEED_ERROR("110", "加入之簡訊動態密碼生成因子為空值"),

    SEED_TOO_LONG_ERROR("111", "簡訊動態密碼生成因子長度超過上限"),

    NO_SEED_ERROR("112", "未加入簡訊動態密碼生成因子"),

    VERIFY_SEED_ERROR("113", "檢核簡訊動態密碼生成因子失敗"),

    GENERATE_TXCODE_ERROR("120", "產生交易代碼失敗"),

    VERIFY_TXCODE_ERROR("121", "檢核交易代碼錯誤"),

    GENERATE_OTP_ERROR("130", "產生交易驗證碼失敗"),

    VERIFY_OTP_ERROR("131", "檢核交易驗證碼錯誤"),

    VERIFY_OTP_OVER_LIMIT_ERROR("132", "檢核交易驗證碼錯誤超過上限"),

    GENERATE_CAPTCHA_ERROR("140", "產生圖形驗證碼失敗"),

    VERIFY_CAPTCHA_ERROR("141", "檢核圖形驗證碼錯誤"),

    VERIFY_CAPTCHA_OVER_LIMIT_ERROR("142", "檢核圖形驗證碼錯誤超過上限"),

    ACTIVATE_OTP_ERROR("150", "啟用簡訊動態密碼失敗"),

    OTP_NOT_ACTIVATED_ERROR("151", "尚未啟用簡訊動態密碼"),

    VERIFY_OTP_PASSWORD_ERROR("160", "檢核簡訊動態密碼失敗"),

    NO_TX_SERIAL_NO_ERROR("500", "尚未設定交易序號"),

    INVALID_TX_SERIAL_NO_ERROR("501", "交易序號格式錯誤"),

    NO_TX_ID_ERROR("510", "尚未設定交易來源"),

    INVALID_TX_ID_ERROR("511", "交易來源格式錯誤"),

    NO_CUST_ID_ERROR("520", "尚未設定身分證統一編號"),

    INVALID_CUST_ID_ERROR("521", "身分證統一編號格式錯誤"),

    NO_NAME_CODE_ERROR("530", "尚未設定戶名代碼"),

    INVALID_NAME_CODE_ERROR("531", "戶名代碼格式錯誤"),

    NO_USER_ID_ERROR("540", "尚未設定使用者代碼"),

    INVALID_USER_ID_ERROR("541", "使用者代碼格式錯誤"),

    NO_MOBILE_NO_ERROR("550", "尚未設定簡訊動態密碼寄送之手機號碼"),

    INVALID_MOBILE_NO_ERROR("551", "簡訊動態密碼寄送之手機號碼格式錯誤"),

    BLANK_MOBILE_NO_ERROR("560", "簡訊動態密碼訊息為空值"),

    SMS_SEND_ERROR("561", "簡訊動態密碼訊息發送失敗"),

    W600_ERROR("570", "W600時產生之新交易序號紀錄(僅紀錄，非重新產生)"),

    W600_NEW_TXNO_ERROR("571", "W600時產生之新交易序號格式錯誤"),

    INSERT_FAILED_ERROR("700", "新增簡訊動態密碼交易紀錄失敗"),

    UPDATE_FAILED_ERROR("701", "更新簡訊動態密碼交易紀錄失敗"),

    UNKNOWN_ERROR("999", "未知的錯誤");

    private String code;

    private String desc;

    private OtpStatusType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OtpStatusType findByCode(String code) {
        for (OtpStatusType status : OtpStatusType.values()) {
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

}
