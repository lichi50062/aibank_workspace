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
package com.tfb.aibank.biz.security.services.mid.type;

//@formatter:off
/**
* @(#)MidStatusType.java
* 
* <p>Description:台網MID驗證回傳代碼</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/25, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum MidStatusType {

    MID_SUCCESS("0", "成功"),

    // 一般錯誤
    MID_PORTAL_PARAMS_ERR("2031000", "參數錯誤"),

    MID_PORTAL_VERIFY_NO_REPEATS("2031001", "交易代碼重複"),

    MID_PORTAL_HASH_KEY_NO_ERR("2031002", "hashKeyNo 錯誤"),

    MID_PORTAL_IDENTITY_NO_ERR("2031003", "驗證碼錯誤"),

    MID_PORTAL_JSON_ACCESS_FAIL("2031004", "JSON 參數讀取失敗"),

    MID_PORTAL_ACCESS_DENY("2031005", "沒有存取權限"),

    MID_PORTAL_CATYPE_ERR("2031006", "CAType 參數錯誤"),

    MID_PORTAL_JSON_SETTING_FAIL("2031010", "JSON 參數設定失敗"),

    MID_PORTAL_GEN_IDENTITY_NO_FAIL("2031011", "產生驗證碼失敗"),

    MID_PORTAL_LOGIN_INSERT_DB_FAIL("2031012", "登入紀錄寫入資料庫失敗"),

    MID_PORTAL_BUSSINESS_NO_DENY("2031013", "應用平台不合法"),

    MID_PORTAL_LOGIN_RETURN_URL_INVALID("2031014", "轉址資訊無效"),

    MID_PORTAL_TOKEN_CANNOT_EXECUTE_THIS_ACTION("2031015", "該 token 不能執行此 action"),

    MID_PORTAL_TOKEN_CANNOT_ALREADY_EXECUTE_THIS_ACTION("2031016", "該 token 已執行過此 action"),

    MID_PORTAL_FETCH_TRANSACTION_DATA_FROM_DB_FAIL("2031017", "存取資料庫的交易紀錄失敗"),

    // 授權驗證錯誤
    MID_PORTAL_NO_LOGIN_DATA("2032000", "無登入紀錄"),

    MID_PORTAL_TOKEN_UNAUTHORIZED("2032001", "Token 未授權"),

    MID_PORTAL_TOKEN_EXPIRE("2032002", "Token 已過期"),

    MID_PORTAL_TOKEN_WRONG_OR_LOGIN_FAIL("2032003", "不正確或登入失敗的 Token"),

    MID_PORTAL_PENDING("2032500", "待處理"),

    MID_PORTAL_SECONED_PHASE_PENDING("2032600", "第一階段完成，第二階段待處理"),

    MID_PORTAL_SECONED_PHASE_PROCESSING("2032700", "第二階段處理中"),

    // MID 作業錯誤
    MID_PORTAL_MID_RQ_DATA_ERR("2035901", "MID 參數錯誤"),

    MID_PORTAL_MID_GEN_SESSION_KEY_FAIL("2035902", "產生 MID Session Key 失敗"),

    MID_PORTAL_MID_QUERY_DATA_FAIL("2035903", "MID 存取交易紀錄失敗"),

    MID_PORTAL_MID_DATA_NOT_FOUND("2035904", "MID 查無交易紀錄"),

    // SDK回覆錯誤
    MID_SDK_APP_AUTH_REJECT("1131000", "APP 執行的權限不足，請先允許開放權限。"),

    MID_SDK_UNSURPPORT_ROOT_JB_DEVICE("1131001", "不支援已 JB 或 Root 的行動裝置"),

    MID_SDK_DEVICE_CONNECT_WITH_WIFI("1131002", "請關閉 WiFi 網路，改用 4G 網路。"),

    MID_SDK_DEVICE_LOST_CONNECTION("1131003", "行動網路未連線，請開啟 4G 網路。"),

    MID_SDK_UNSURPPORT_ISP_OR_ROAMING_MODE("1131004", "使用的電信網路或漫遊網路不支援。"),

    MID_SDK_MIDPROFILE_READING_FAIL("1132000", "下載資訊 midProfile 讀取失敗，請重新操作。"),

    MID_SDK_MIDPROFILE_VALIDATE_FAIL("1132001", "下載資訊 midProfile 驗證失敗，請重新操作。"),

    MID_SDK_ISP_CONNECTION_LOST_CEHCK_AND_RETRY("1132002", "電信業者連線失敗，請確認網路連線後重新操作。"),

    MID_SDK_ISP_MAYBY_UNSURPPORT_ROAMING_MODE("1132003", "電信商驗證失敗，您的電信服務可能不支援在漫遊下驗證。"),

    MID_SDK_DUAL_SIM_VALIDATION_FAIL("1132004", "電信業者驗證失敗(雙卡機中 2 張 SIM 卡皆失敗)"),

    MID_SDK_ISP_UNSURPPORT_ROAMING_MODE("1132005", "電信業者驗證失敗，漫遊服務不支援。"),

    MID_SDK_TAIWAN_STAR_PUBKEY_ERR("1132010", "無法 parse 台星 Base64 公鑰"),

    MID_SDK_APTG_CONNECTION_LOST("1132011", "亞太電信取 IP/Port 連線失敗"),

    MID_SDK_APTG_IP_PORT_FORMAT_ERR("1132012", "亞太電信取 IP/Port 非預期格式"),

    MID_SDK_DEVICE_UNLOCK_PWD_FAIL("1132999", "裝置解鎖驗證失敗，請重新操作。"),

    MID_SDK_DEVICE_NONSETTING_LOCK_PWD("1133000", "需要裝置解鎖驗證，請設置後重新操作。"),

    MID_SDK_USER_CANCEL_UNLOCK_PWD("1133001", "裝置解鎖驗證取消，請重新操作。"),

    MID_SDK_TWID_PORTAL_CONNECTION_FAIL("1133002", "TWID Portal 連線失敗，請確認網路連線後重新操作。"),

    MID_SDK_TWID_PORTAL_MESSAGE_FAIL("1133003", "TWID Portal 訊息無效，請稍後再試。"),

    MID_SDK_TWID_PORTAL_SERVICE_ERR("1133004", "TWID Portal 服務異常，請稍後再試。"),

    MID_SDK_USER_ROCID_CANNOT_EMPTY("1133005", "未提供正確的身分證字號"),

    MID_SDK_USER_MOBILE_NUMBER_CANNOT_EMPTY("1133006", "未提供電話號碼"),

    MID_SDK_APP_UNCHECK_CERTIFICATE_STATUS_ERR("1134005", "APP 作業異常，未確認憑證狀態，請重新操作。"),

    MID_SDK_APP_UNNECESSARY_CHECK_CERTIFICATE_ERR("1134006", "APP 作業異常，無需進行憑證作業，請重新操作。"),

    MID_SDK_APP_PARAMS_ERR("1135005", "APP 作業異常，參數錯誤，請重新操作。"),

    // TWID回覆錯誤
    MID_GATEWAY_PARAM_INVALID_LOST_PARAM("3641100", "檢查參數失敗: 缺少參數"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_VER("3641101", "檢查參數失敗: 參數格式不符(ver)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_REQTYPE("3641102", "檢查參數失敗: 參數格式不符(reqType)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_REQID("3641103", "檢查參數失敗: 參數格式不符(reqId)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_REQSEQ("3641104", "檢查參數失敗: 參數格式不符(reqSeq)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_PAYLOAD("3641105", "檢查參數失敗: 參數格式不符(payload)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_MAC("3641106", "檢查參數失敗: 參數格式不符(mac)"),

    MID_GATEWAY_PARAM_INVALID_REQSEQ_EXPIRE("3641107", "檢查參數失敗: 交易序號 reqSeq 已經逾期"),

    MID_GATEWAY_PARAM_INVALID_REQ_ID("3641110", "檢查參數失敗: 帳戶代碼不符(reqId)"),

    MID_GATEWAY_PARAM_INVALID_VALIDATION_TYPE("3641141", "檢查參數失敗: 錯誤的 validationType"),

    MID_GATEWAY_PARAM_MISSING_VALIDATION_DATA("3641142", "檢查參數失敗: 缺少 validationData"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_CLAUSEVER("3641143", "檢查參數失敗: 參數格式不符(clausever)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_OPERATOR("3641144", "檢查參數失敗: 參數格式不符(operator)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_BIRTHDAY("3641146", "檢查參數失敗: 參數格式不符(birthday)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_MSISDN("3641149", "檢查參數失敗: 參數格式不符(msisdn)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_ROCID("3641150", "檢查參數失敗: 參數格式不符(rocid)"),

    MID_GATEWAY_PARAM_FORMAT_INVALID_VALIDATION_COST("3641151", "檢查參數失敗: 參數格式不符(validationCost)"),

    MID_GATEWAY_SYSTEM_ERR_COMPUTE_SESSION_KEY_FAIL("3642100", "系統異常: 計算 sessionKey 失敗"),

    MID_GATEWAY_SYSTEM_ERR_GEN_MID_REQUEST_FAIL("3643100", "系統異常: 無法產生 mid 驗證請求"),

    MID_GATEWAY_SYSTEM_ERR_SEND_MID_REQUEST_FAIL("3644100", "發送驗證請求失敗"),

    MID_GATEWAY_SYSTEM_ERR_INVALID_MID_RESPONSE("3645100", "系統異常: 檢查驗證回應失敗"),

    MID_GATEWAY_SYSTEM_ERR_MID_OTHER_ERR("3645199", "系統異常: 其他錯誤"),

    // 發送查詢階段錯誤
    // 請聯絡客服人員排除問題。
    MID_SERVER_ISP_RESPONSE_CONNECTION_TIMEOUT("3644011", "網路連線失敗，取得電信業者回應作業逾時"),

    // 請聯絡客服人員排除問題。
    MID_SERVER_ISP_RESPONSE_MISSING_INTERRUPT("3644012", "無法服務: 無法取得電信業者回應"),

    // 該門號電信業者暫停服務。
    MID_SERVER_ISP_SERVICE_SUSPENDED_INTERRUPT("3644013", "無法服務: 電信業者暫停服務"),

    // 查詢門號電信業者暫停服務。
    MID_SERVER_ISP_MID_SERVICE_SUSPENDED_INTERRUPT("3644014", "無法服務: 電信業者暫停門號查詢服務"),

    // 請聯絡客服人員排除問題。
    MID_SERVER_ISP_MID_SERVICE_CONNECTION_FAIL("3644015", "連線失敗，無法連線至門號查詢電信業者"),

    // 該門號電信業者無法提供服務。
    MID_SERVER_ISP_UNSURPPORT_MID_SERVICE("3644021", "門號所屬業者未提供資料核實服務"),

    // 該門號電信業者暫停服務。
    MID_SERVER_ISP_SERVICE_SUSPENDED("3644022", "門號所屬業者暫停服務"),

    // 請聯絡客服人員排除問題。
    MID_SERVER_ISP_NO_RESPONSE_INTERRUPT("3644023", "無法服務: 無法取得電信業者回應"),

    // 資料輸入錯誤次數太多，請 10 分鐘 後再試；或確認目前門號是否能進 行門號認證。
    MID_SERVER_ISP_PARAMS_INVALID_UP_TO_LIMIT("3644029", "資料輸入錯誤次數過多"),

    // 接收電信回覆訊息階段錯誤
    // 請檢查輸入資料是否正確-門號，若使用者使用的是雙卡機， 請使用 者檢查雙卡機使用的行動數據連線 是哪一家電信。
    MID_SERVER_ISP_INVALID_MOBILE_NO_NOT_THIS_ISP("3645002", "輸入的門號不屬於目前進行認證的電信業者"),

    // 請重新輸入該門號申請人身分證字號是否與您輸入的相同。
    MID_SERVER_ISP_INVALID_ROCID_MOBILE_NO_NOT_MATCH("3645011", "身分證字號與門號不符"),

    // 作業逾時，請重新驗證。
    MID_SERVER_ISP_INVALID_AUTHORIZATION_EXPIRE("3645013", "驗證失敗，電信授權逾時"),

    // 請檢查輸入門號資料是否正確， 若使用的是雙卡機， 請使用者檢查雙卡機使用的行動數據連線是哪一家電信。
    MID_SERVER_ISP_INVALID_DUAL_SIM_MOBILE_NO_NOT_MATCH("3645014", "驗證失敗，電信授權門號不符"),

    // 請檢查輸入身分證字號、生日與門 號資料是否正確。
    MID_SERVER_ISP_INVALID_BIRTHDAY("3645016", "電信業者驗證失敗: 門號與 ID 相符，但生日不對。"),

    // 請檢查網路連結狀態， 或確認雙卡機使用的行動數據連線是哪一家電信。
    MID_SERVER_ISP_INVALID_DUAL_SIM_IP_NOT_MATCH("3645017", "電信業者驗證失敗: 目前連線的 IP 和門號不符"),

    // 請聯絡客服人員排除問題。
    MID_SERVER_ISP_INVALID_REGISTER_DATA_LOST("3645019", "電信業者驗證失敗: 遺漏註冊資料"),

    // 請洽電 信業者詢問該門號狀態是否 資格不符導致驗證失敗， 例如 SIM 卡掛失。
    MID_SERVER_ISP_INVALID_SIM_LOST("3645021", "電信業者驗證失敗 : 您的門號無法驗證，例如SIM 卡掛失。"),

    // 電信業者回覆 : 本門號不接受驗 證，如預付卡。
    MID_SERVER_ISP_MOBILE_NO_UNSURPPORT("3645024", "門號不接受驗證"),

    // 請聯絡客服人員排除問題。
    MID_SERVER_ISP_RESPONSE_QUERY_FAIL("3645098", "電信業者回覆: 查詢階段錯誤"),

    // 請聯絡客服人員排除問題。
    MID_SERVER_ISP_RESPONSE_UNKNOW_ERR("3645099", "電信業者回覆: 非預期錯誤"),

    // 產生回覆訊息階段/其他錯誤
    // 請聯絡客服人員排除問題。
    MID_SERVER_SYSTEM_ERR_DB_UPDATE_FAIL("3649998", "系統異常: 將交易寫入 DB 失敗"),

    // 請聯絡客服人員排除問題。
    MID_SERVER_SYSTEM_ERR_OTHER_ERR("3649999", "系統異常: 其他錯誤"),

    MID_UNKNOW_ERR("9999999", "未知錯誤代碼");

    private String code;

    private String desc;

    private MidStatusType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static MidStatusType findByCode(String code) {
        for (MidStatusType status : MidStatusType.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return MID_UNKNOW_ERR;
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
