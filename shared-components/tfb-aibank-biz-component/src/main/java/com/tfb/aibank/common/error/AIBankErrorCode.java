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
package com.tfb.aibank.common.error;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.id.IBSystemId;

// @formatter:off
/**
 * @(#)AIBankErrorCode.java
 * 
 * <p>Description:在執行服務時，產生的共同錯誤代碼，主要定義 AIBank 系統中共用的錯誤碼</p>
 * <p>編碼範圍：00201 ~ 00400</p>
 * <p>專案開發過程中，如有將 CHL ErrorCode 移進來，請註解說明</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum AIBankErrorCode implements IErrorCode {

    USER_INFORMATION_NOT_FOUND("00201", "從資料庫查無使用者資訊"),

    TIME_FACTOR_ERROR("00202", "時間因子驗證失敗"),

    DEVICE_BINDING_FAILED("00203", "設備認證失敗，請您洽分行，由服務人員為您處理，謝謝！"),

    DEVICE_BINDING_STATUS_INVALID("00205", "您尚未啟用指紋/臉部登入或已停用，請改用代號密碼登入後重新啟用"),

    TX_SECURITY_INIT_ERROR("00206", "交易安控機制取得失敗"),

    TX_SECURITY_INIT_OTP_ONSITE_ERROR("00207", "未申請OTP"),

    TX_SECURITY_INIT_OTP_CIF_ERROR("00208", "請至分行進行行動電話的留存"),

    TX_SECURITY_INIT_MOTP_ERROR("00209", "未申請MOTP"),

    TX_SECURITY_DATA_CHECK_ERROR("00210", "交易安控資料錯誤"),

    USER_LOGINED_IN_BANK("00211", "不同IP , 5 分鐘後才可登入"),

    USER_LOGIN_DUPLICATE("00212", "重複登入"),

    USER_LOGINED_IN_OTHER("00213", "使用者已登入其他系統，請登出後再進行行動銀行登入"),

    USER_ID_LEVEL_ERROR("00214", "您目前登入權限為媒體薪轉上傳權限，無法登入網路銀行。請至<a href=\"https://esrv.taipeifubon.com.tw/salup/html/pages/jsp/index.jsp\" target=\"_blank\">媒體薪轉專區</a>登入，謝謝。"),

    USER_NOT_FOUND("00215", "身分證字號或使用者代碼錯誤"),

    PASSWORD_FAIL_COUNT("00216", "密碼輸入錯誤已達三次"),

    USER_IS_STOPPED("00217", "該使用者代碼已刪除無法使用"),

    CCUSER_LOGIN_PASSWORD_VALIDATE_ERROR("00218", "密碼輸入錯誤（連續錯誤三次將被鎖定"),

    HSM_RESPONSE_ERROR("00220", "HSM 裝置回應錯誤"),

    // EAI_RESPONSE_UNKNOWN("00221", "欲要編新的代碼，歡迎替換。"),

    OPEN_API_SERVICE_ERROR("00222", "OpenAPI介接錯誤"),

    DEVICE_LIMITATION_ERROR("00223", "ID-NOALLOW 此ID無法在目前網域IP中登入！"),

    TWID_API_SERVICE_ERROR("00225", "TWID API執行失敗"),

    GESTURE_LOGIN_PASSWORD_LOCK_ERROR("00226", "錯誤代碼+手勢密碼連續輸入錯誤三次已被鎖定，請您先用使用者密碼方式登入後重新進行手勢密碼設定，如有疑問，請洽客戶服務專線02-8751-6665。"),

    GESTURE_LOGIN_PASSWORD_VALIDATE_ERROR1("00227", "錯誤代碼+手勢密碼輸入錯誤一次，如有疑問，請洽客戶服務專線02-8751-6665。"),

    GESTURE_LOGIN_PASSWORD_VALIDATE_ERROR2("00228", "錯誤代碼+手勢密碼連續輸入錯誤兩次(連續錯誤三次將被鎖定)，若您已遺忘手勢密碼，可先用使用者密碼登入後至【個人服務>密碼設定/變更>行動銀行手勢密碼設定/變更】中取消手勢密碼，再重新進行手勢密碼設定，如有疑問，請洽客戶服務專線02-8751-6665。"),

    MOTP_API_SERVICE_ERROR("00229", "MOTP API介接錯誤"),

    MOTP_SERVICE_UNAVALIBLE("00230", "推播動態密碼服務維護中，請稍後重新操作。(00230)"),

    MOTP_BINDING_DEVICE_NOT_FOUND("00231", "查無綁定裝置資料"),

    MOTP_BINDING_STATUS_ABNORMAL("00232", "推播動態密碼綁定狀態異常"),

    MOTP_SEND_OTP_FAILED("00233", "推播動態密碼發送失敗，請稍後重新操作。(00233)"),

    MOTP_SEND_OTP_UNKNOWN_ERROR("00234", "推播動態密碼服務維護中，請稍後重新操作。(00234)"),

    MOTP_TRANS_DATA_NOT_FOUND("00235", "查無交易認證資訊"),

    MOTP_CARRIER_DATA_NOT_FOUND("00236", "查無交易驗證之載具資訊"),

    MOTP_TRANS_DATA_STATUS_ERROR("00237", "交易認證資訊狀態有誤"),

    MOTP_VERIFY_OTP_UNKNOWN_ERROR("00238", "推播動態密碼服務維護中，請稍後重新操作。(00238)"),

    DEVICE_BINDING_NOT_SAME_USER("00239", "此手機已由{0}綁定，若欲設定本服務，請由上述客戶之裝置刪除此手機後再行新增"),

    DEVICE_BINDING_NEED_UPDATE("00240", "您的裝置尚未綁定或需變更綁定，請先確認綁定裝置後再進行操作"),

    PAYER_ACCOUNTS_IS_EMPTY("00241", "無轉出帳號"),

    ARG_NOT_PASS_IN("00242", "未傳入參數"),

    MOTP_MID_VERIFY_KEY_LOST("00243", "操作有誤，請稍後再試"),

    NO_EMAIL_ADDRESS_RESERVED("00244", "目前無留存行內Email信箱，請先進行設定"),

    ALREADY_BIND_BY_OTHER_USER_ERROR("00245", "請先登入 {0} {1} ({2}) 並解除與此裝置的綁定後，再進行設定"),

    MULTI_USER_BIND_OTHER_DEVICE_ERROR("00247", "很抱歉，登入ID已於另一裝置被其他使用者代碼綁定，請先以原綁定代碼解除，再重新設定"),

    MULTI_USER_BIND_ERROR("00248", "很抱歉，登入ID已被其他使用者代碼綁定"),

    TX_SECURITY_INIT_OTP_AS400_ERROR("00249", "請至分行進行行動電話的留存"),

    DATA_NOT_FOUND("00259", "如果有任何疑問請撥02-8751-6665，有專人為你服務"),

    SESSION_UPDATE_FAIL("00257", "作業階段處理錯誤"),

    DATA_CENTER_API_ERROR("00250", "資料查詢失敗，如有疑問，請洽數位客服。"),

    FUTURES_API_SERVICE_ERROR("00251", "期貨API介接錯誤"),

    TASK_TIME_ERROR("00252", "判斷營業時間發生異常"),

    SPECIFIC_TXN_TIMEOUT("00253", "主機回傳後端時Timeout"),

    PASSWORD_FAIL_COUNT1("00254", "密碼輸入錯誤 可嘗試次數剩餘2次"),

    PASSWORD_FAIL_COUNT2("00255", "密碼輸入錯誤 可嘗試次數剩餘1次"),

    USER_DEVICE_BINDING_INVALID("00256", "您的帳號非綁定目前登入裝置，請先認證裝置後再使用本服務"),

    ACCOUNT_INFORMATION_PROCESSING_ERROR("00257", "帳號資料處理異常，請與客服人員聯繫"),

    NO_EMAIL_FOUND("00258", "您目前尚未於本行留存通知電子郵件信箱資料，故無法繼續本項服務，如您希望使用本項服務，請<a href=\"NPSAG001\">設定</a>您的電子郵件信箱資料。"),

    RESERVE_DATA_NOT_FOUND("00260", "查無預約交易"),

    UIDDUP_TASK_NOT_ALLOW("00261", "ID為誤別碼(ID重號)"),

    FIDO_SDK_ERROR("00262", "裝置綁定失敗，請稍後再試"),

    FIDO_UNREGISTERED("00263", "FIDO資料已更新，請您重新設定快速登入"),

    SUSPEND_TASK("00264", "暫停交易"),

    SSO_DATA_ERROR("00265", "身分認證平台資料錯誤"),

    EBill_API_SERVICE_ERROR("00266", "全國繳費網連線失敗"),

    CLEARINGHOUSE_API_SERVICE_ERROR("00267", "票交所連線失敗"),

    INSURANCE_INFO_API_SERVICE_ERROR("00269", "保單資料連線失敗"),

    INSURANCE_INFO_API_SERVICE_SEND_ERROR("00270", "保單資料發查失敗"),

    LONG_IDLE_TO_RELOAD("00277", "系統閒置過久，請重新開啟APP"),

    BARCODE_GATEWAY_ERROR("00278", "載具驗證平台連線失敗"),

    FUBON_SECURITIES_API_ERROR("00279", "富邦證券連線失敗"),

    FUBON_INSURANCE_API_ERROR("00280", "富邦人壽連線失敗"),

    FBS_BANK_KEY_ERROR("00281", "富證 BankKey 取得失敗"),

    CLEARHOUSE_API_TIMEOUT("00282", "票交API讀取TIME_OUT"),

    SSO_AUTH_DATA_ERROR("00283", "身份認證資料錯誤"),

    TRANSFER_ALL_ERROR("00284", "無痛移轉全部失敗"),

    NOT_USUAL_USER("00275", "本服務限個人戶使用，如有任何疑問，請與您所屬客戶經理聯繫，或洽本行24小時客服中心02-8751-6665。"),

    NOT_SINGLE_ACCT_NAME("00276", "本服務限電子交易往來已歸戶之客戶使用，如有任何疑問，請與您所屬客戶經理聯繫，或洽本行24小時客服中心02-8751-6665。"),

    CARD_PROMOTE_API_ERROR("00285", "信用卡優惠網介接錯誤"),

    TWCA_API_SERVICE_ERROR("00286", "台網驗證連線失敗"),

    TWCA_API_IDENTIFY_NO_ERROR("00287", "台網 IDENTIFY_NO 驗證失敗"),

    TIME_FACTOR_SETTING_ERROR("00288", "時間因子驗證方式錯誤"),

    CC_MEMBER_NOT_ALLOWED_ACC_ERROR("00289", "很抱歉，信用卡會員無法使用多元版行動銀行。"),

    TWO_FACTOR_API_VERIFY_FAILED("00290", "驗證資料錯誤"),

    TWO_FACTOR_API_NOTBINDED("00291", "使用者尚未綁定裝置"),
    
    TWO_FACTOR_API_DISABLE_NOTIFYFLAG("00292", "使用者未開啟推播設定"),

    APPLY_SHORTENER_URL_NOT_SET("00293", "短網址URL未設定"),

    CHECK_WHITE_LIST_AND_LIMIT_FAIL("00294", "信用卡活動登錄未符合白名單或次數限制"),

    // @formatter:off
    // 以下代碼從 channel service 移進來，請按照代碼順序排列
    // 系統(NST)，編碼範圍：00401 ~ 00500
    
    // 存款(NDS)，編碼範圍：01001 ~ 02000
    ONLY_FOR_SINGLE_ACCOUNT("01009", "很抱歉，因您有多組使用者代碼，請攜帶身分證，於分行刪除已不使用之網銀帳號後，即可重新申請。"),
    NO_TXN_OUT_ACCOUNT("01012", "親愛的客戶您好，由於您名下未持有符合本服務的轉出帳號，故無法使用本服務，請洽本行各<u>服務據點</u>辦理，如有其他問題，請洽本行客服中心<a href=\"tel:02-8751-6665\">02-8751-6665</a>。"),
    CONTRACT_NO_WRONG("01205", "很抱歉，您查詢的契約編號有誤，如有任何疑問，請與您所屬客戶經理聯繫，或洽本行24小時客服中心02-8751-6665。"),
    NO_EFFECTIVE_CONTRACT("01214", "您目前無可查詢之金錢信託契約，如有任何疑問，請與您所屬客戶經理聯繫，或洽本行24小時客服中心02-8751-6665。"),
    UNABLE_TO_PROVIDE_SERVICE("01227", "目前系統暫時無法提供服務，請稍後再試，若有相關問題請來電02-8751-6665將有專人為您服務，謝謝"),
    NO_ENGLISH_NAME("01228", "很抱歉，由於您尚未於本行留有英文姓名，無法執行本交易，請洽本行各營業單位辦理"),
    // 信用卡(NCC)，編碼範圍：02001 ~ 02900
    NO_BILLING_INFORMATION("02001", "無帳單資訊。"),
    SPECIAL_CARD_USER("02002", "目前無法查詢您的信用卡資料，如有疑問，請洽信用卡客服中心<a href=\"tel:02-8751-1313\">02-8751-1313</a>。"),
    CREDIT_CARD_NOT_FOUND("02003", "本行目前無法查詢您的信用卡資料，如有疑問，請洽本行信用卡客服中心<a href=\"tel:02-8751-1313\">02-8751-1313</a>。"),
    PRIMARY_CARDHOLDER_ONLY("02004", "本服務僅正卡客戶使用。<br>可立即<u>線上申辦</u>信用卡，即可使用此功能，如有任何疑問，請洽信用卡客服<a href=\"tel:02-8751-1313\">02-8751-1313</a>"),
    CREDIT_CARD_DATA_UPDATING("02051", "系統資料更新中，請稍後查詢。"),
    // 信用卡活動(NCA)，編碼範圍：02901 ~ 03000

    // 繳費(NPY)，編碼範圍：03001 ~ 04000
    FISC_SERVICE_ERROR("03001","FISC財金資訊平台回應錯誤"),

    // 基金(NMF)，編碼範圍：04001 ~ 05000
    NOT_FUND_TX_TIME("04001", "很抱歉，目前系統暫停受理電子交易，如有疑問，請洽客服<a href=\"tel:02-8751-6665\">02-8751-6665</a>。"),
    ALIEN_RESIDENT_CERT_EXPIRED("04008", "外國人持護照或居留證已過期，請至各服務據點辦理此交易。"),
    // 通用(NGN)，編碼範圍：00501 ~ 01000
    UPDATE_DATA_SYNC_STATUS_FAILED("00543", "更新資料彙整註記失敗"),
    // 投資(NNF)，編碼範圍：05001 ~ 06000
    KYC_NOT_DONE("05001", "親愛的客戶您好，依主管機關規定，銀行應依客戶投資風險屬性及承受度銷售或推介客戶適合之商品。請立即進行<a href=\"NMFAG004\">KYC投資風險承受度測試</a>，測試結果將作為您日後投資適合度評量之參考依據。"),
    KYC_EXPIRED("05002", "親愛的客戶您好，您於本行KYC投資風險承受度測試記錄已過期，請立即進行KYC投資風險承受度測試，測試結果將作為您日後投資適合度評量之參考依據。"),
    KYC_RETEST("05003", "親愛的客戶您好，您的KYC投資風險承受度需重新檢測後方可進行交易，如有疑問請洽您的客戶經理或客服02-8751-6665查詢。"),
    ELDERLY_COGNITION_NOT_PASS("05004", "近期總體經濟變動大，投資人應審慎評估。此項服務暫不提供使用，如有疑問，請洽客服<a href=\"tel:02-8751-6665\">02-8751-6665</a>，造成不便，敬請見諒。"),
    FATCA_SPECIAL_STS("05005", "因您尚未提供開戶文件、身分證明文件等相關文件，故無法承作此商品，如有疑問請洽詢您的客戶經理或數位客服。"),
    CUST_RISK_PROP_NOT_MATCH_PROD("05006", "您的風險承受度為{0}，可選購{1}等級商品，此商品風險等級為{2}，無法進行投資。"),
    CUST_RISK_PROP_NOT_MATCH_PROD_HIGHNETWORTH("05022", "您為高資產客戶，您的風險偏好為{0}，可選購{1}等級商品，此商品風險等級為{2}，無法進行投資。"),
    BOND_SUSPEND_ELEC("05033", "很抱歉，目前系統暫停受理電子交易，如有疑問，請洽客戶經理或數位客服"),
    BOND_INVALID_BUTYPE("05034", "目前未開放境外金融中心(OBU)之客戶贖回。如有疑問，請洽客戶經理或數位客服"),
    BOND_NO_ONLINE_SERVICE("05035", "您尚未申請海外債券電子化服務，請洽各服務據點辦理，如有疑問，請洽客服02-8751-6665。"),
    BOND_NO_INACCOUNT("05036", "親愛的客戶您好，由於您名下未持有符合本服務的入帳帳號，故無法使用本服務，如有疑問，請洽客服02-8751-6665"),
    BOND_NO_INVENTORY_DATA("05037", " 您目前尚無可贖回海外債券資料，如有疑問，請洽客戶經理或數位客服。"),
    BOND_UNABLE_REDEEM_ONLINE("05038", "該債券無法於線上贖回，如有疑問，請洽客戶經理或數位客服"),
    BOND_TRADING_SUSPEND("05039", "該債券暫停交易，如有疑問，請洽客戶經理或數位客服"),
    BOND_SUSPEND_ELEC_TX("05040", "目前系統暫停受理電子交易，如有疑問，請洽客戶經理或數位客服"),
    BOND_NO_TXN_INACCOUNT("05041", "目前查無可入帳的存款帳號，如有疑問，請洽客戶經理或數位客服"),
    BOND_INVALID_REALTIME("05042", "當日單交易服務時間為營業日 {0} 至 {1}，請改採預約單/長效單交易"),
    NO_PRODUCT_DATA("05054", "目前查無該筆商品資料，如有疑問，請洽<a class=\"btn-text\" href=\"aibank.fubon://DSERVICE\">數位客服</a>"),
    NO_ETF_DATA("05071", "您目前尚無可變更海外ETF股票定期定額資料；如有疑問，請洽客戶經理或<a class=\"btn-text\" href=\"aibank.fubon://DSERVICE\">數位客服</a>"),
    // 資產負債(NBO)，編碼範圍：06001 ~ 07000
    NO_SERVICE_NIGHTTIME("06006", "每日台北時間23:00起至隔日08:00止，為系統資料更新時間，暫不提供投資總覽查詢服務，造成不便，尚祈見諒"),
    NOT_SINGLE_ACCOUNT("06009", "本服務限電子交易往來已歸戶之客戶使用，如有任何疑問，請與您所屬客戶經理聯繫，或洽本行24小時客服中心02-8751-6665。"),
    NO_VALID_DATA("06030", "目前查無有權指示人同意之給付項目，如有任何疑問，請與您所屬客戶經理聯繫，或洽本行24小時客服中心02-8751-6665。"),
    // 貸款(NLN)，編碼範圍：07001 ~ 08000
    LOAN_STATUS_ERROR("07005", "您的貸款往來情形請洽本行客服"),
    LOAN_ACCOUNT_NOTFOUND_ERROR("07006", "資料查詢失敗+<換行>+如有任何疑問，請洽本行客服中心02-8751-6665"),
    // 個人服務(NPS)，編碼範圍：08001 ~ 09000
    PASSWORD_FAIL_3COUNT("08002", "密碼輸入錯誤已達三次"),
    PASSWORD_CHANGE_FAIL("08003", "變更密碼失敗"),
    LOAD_BRANCHMAP_FAIL("08004", "服務據點資料取得失敗"),
    // 點數(NPO)，編碼範圍：09001 ~ 10000
    
    // @formatter:on

    SERVICE_NOT_AVAILABLE("X9998", "系統暫時停止服務");

    /** 異常資料 */
    private ErrorStatus error = null;

    AIBankErrorCode(String errorCode, String memo) {
        this(errorCode, memo, SeverityType.ERROR);
    }

    /**
     * 使用於Timeout錯誤
     *
     * Constructor
     *
     * @param errorCode
     * @param memo
     * @param severity
     */
    AIBankErrorCode(String errorCode, String memo, SeverityType severity) {
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

    /**
     * 傳入的錯誤碼與此Enum錯誤碼相同
     *
     * @return
     */
    public boolean errorCodeEquals(String errorCode) {
        return error.getErrorCode().equals(errorCode);
    }
}
