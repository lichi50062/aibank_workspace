package com.tfb.aibank.chl.creditcard.error;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.id.IBSystemId;

//@formatter:off
/**
 * @(#)ErrorCode.java
 * 
 * <p>Description:信用卡(NCC)專用錯誤</p>
 * <p>編碼範圍：02001 ~ 02900</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public enum ErrorCode implements IErrorCode {

    NO_BILLING_INFORMATION("02001", "無帳單資訊。"),

    SPECIAL_CARD_USER("02002", "目前無法查詢您的信用卡資料，如有疑問，請洽信用卡客服中心<a href=\"tel:02-8751-1313\">02-8751-1313</a>。"),

    CREDIT_CARD_NOT_FOUND("02003", "本行目前無法查詢您的信用卡資料，如有疑問，請洽本行信用卡客服中心<a href=\"tel:02-8751-1313\">02-8751-1313</a>。"),

    PRIMARY_CARDHOLDER_ONLY("02004", "本服務僅正卡客戶使用。<br>可立即<u>線上申辦</u>信用卡，即可使用此功能，如有任何疑問，請洽信用卡客服<a href=\"tel:02-8751-1313\">02-8751-1313</a>"),

    CARD_NO_NOT_PASSED_IN("02005", "未傳入信用卡卡號"),

    CARD_ACTIVATED("02006", "此卡片已開卡"),

    ALL_CARDS_ACTIVE("02007", "目前您無任何尚未開卡之信用卡"),

    CAN_NOT_USE_APPLY_PAY("02008", "無法使用Apple Pay"),

    DEVICE_CREDIT_CARD_INFO_FAIL("02009", "取得裝置Apple Wallet的信用卡資訊失敗"),

    CREDIT_CARD_CAN_NOT_BIND_APPLY_PAY("02010", "此卡片無法綁定Apple Pay"),

    CREDIT_CARD_BIND_APPLY_PAY_ERROR("02011", "綁定Apple Pay錯誤"),

    CREDIT_CARD_ACCOUNT_INFORMATION_ERROR("02012", "信用卡帳務資料發生錯誤"),

    CHANGE_CREDIT_CARD_NICKNAME_ERROR("02013", "異動信用卡暱稱發生錯誤"),

    NO_QUALIFIED_CREDIT_CARD("02014", "很抱歉，目前您無任何可設定消費通知之信用卡。<br>如有疑問，請來電信用卡客服<a href=\"tel:02-8751-1313\">02-8751-1313</a>，由專員協助處理。"),

    INPUT_CARD_NO_NOT_MATCH("02015", "所輸入之卡號不存在於歸戶，請重新檢視卡號正確性。如有疑問，請洽本行信用卡客服中心<a href=\"tel:02-8751-1313\">02-8751-1313</a>"),

    NO_CARD_FOR_ROADSIDE_ASSIST("02016", "目前查無您可設定道路救援之信用卡資料，如有疑問，請洽信用卡客服<a href=\"tel:02-8751-1313\">02-8751-1313</a>"),

    NO_AVAILABLE_CEEDIT_CARD("02017", "本行目前無法查詢您的信用卡資訊，如有疑問，請洽本行信用卡客服中心<a href=\"tel:02-8751-1313\">02-8751-1313</a>。"),

    QUOTA_APPLY_PRIMARY_CARDHOLDER_ONLY("02018", "信用額度為正附卡人共用，僅限正卡持卡人申請正附卡額度調整，恕不提供附卡持卡人進行額度調整"),

    QUOTA_ADJUSTMENT_IN_PROGRESS_WITH_MESGID("02019", "您的額度調整申請尚在審核中，審核時間約需10~14個工作天，一有結果會與您通知，如有 疑問請洽信用卡客服<a class=\"btn-text\" href=\"tel:02-87511313\">02-8751-1313</a>。{0}"),

    NO_SAVING_ACC_ERROR("02020", "親愛的客戶您好，您尚未申請本服務，建議您可至本行服務據點辦理，謝謝。"),

    UNABLE_TO_ADJUST_THE_AMOUNT("02021", "目前無法調整額度，如有疑問請洽客服02-8751-1313"),

    CREDIT_CARD_VIP_ERROR("02022", "因您為本行信用卡貴賓，請洽信用卡客服<a class=\"btn-text\" href=\"tel:02-8751-1313\">02-8751-1313</a>，由專人為您服務"),

    CREDIT_CARD_EMAIL_ERROR("02023", "提醒您，請您至<a class=\"btn-text\" href=\"NCCAG003\">信用卡Email設定</a>更新您目前使用的Email，以確保您後續可收到調整額度申請結果喔!"),

    NO_MONEY_CREDIT_CARD("02025", "很抱歉，目前您無任何可預借現金之信用卡。如有疑問，請來電本行信用卡客服中心02-8751-1313，由專員協助處理。"),

    NO_TRANSFER_IN_ACCOUNT_AVAILABLE("02026", "很抱歉，目前無法查詢到您有效的入帳帳號，如有疑問，請您洽詢本行的客服中心，謝謝。"),

    CASH_ANVANCED_COMMON_ERROR("02027", "很抱歉，本交易暫時無法執行，請稍後再試或洽信用卡客服02-8751-1313，由專人為您服務。"),

    TRANSFER_NOT_IN_BUSINESS_TIME("02028", "很抱歉！轉入他行帳戶之預借現金營業時間為每個營業日9：00-15：30。"),

    SINGLE_INS_CAL_ERROR("02029", "單筆分期試算錯誤"),

    OVER_ACTIVITY_ONLINE_LIMIT("02030", "目前使用人數較多，請稍後再試"),

    PAYMENT_CHANNEL_AND_DETAILS_ERROR("02031", "最近繳款通路與明細發生錯誤"),

    DATA_QUERY_FAILED("02032", "資料查詢失敗"),

    NO_VALID_CARD_WITH_MESGID("02033", "沒有有效卡，無法選擇此功能，如有疑問請洽信用卡客服<a class=\"btn-text\" href=\"tel:02-87511313\">02-8751-1313</a>。{0}"),

    CHECK_INSURANCE_FEE_BENEFITS("02034", "保費權益僅提供特定卡別設定，點選下方按鈕<a class=\"btn-text\" href=\"NCCOT001\">看相關卡片介紹</a>。如有疑問，請來電信用卡客服<a class=\"btn-text\" href=\"tel:02-87511313\">02-8751-1313</a>，由專人協助處理。"),

    UNABLE_TO_ADJUST_THE_AMOUNT_WITH_MESGID("02035", "目前無法調整額度，如有疑問請洽客服02-8751-1313 {0}"),

    // TODO 02034-02035 資料庫已有對應資料 DB無待確認
    INSTALLMANT_BILL_PROCESS("02036", "審核時間約需 10~14 個工作天，一有結果會與您通知，如有疑問請洽本行信用卡客服 02-8751-6665"),

    INSTALLMANT_BILL_QUERY_ERROR("02037", "帳單分期查詢失敗"),

    NO_ACTIVITY_SELECTED("02038", "沒有選擇登錄的活動"),

    ACTIVITY_REGISTER_ERROR("02039", "活動登錄失敗"),

    NO_INSTALLMANT_BILL_DETAIL_ERROR("02040", ""),

    EXCEEDED_BARCODES_COUNT("02041", "已超過每期帳單可產生的條碼次數"),

    INFORMATION_TEMPORARILY_UNAVAILABLE("02049", "暫時無法取得您的帳單資訊"),

    NO_COSTCO_CARD("02043", "您尚未成為 Costco 聯名卡卡友"),

    COSTCO_IS_NOT_PRIMARY("02044", "僅限 Costco 有效主卡會員使用"),

    COSTCO_NOT_MEMBER("02045", "本服務需為Costco 有效主卡會員方可使用，若您為過期或外國會員暫不提供申請。如有疑問請洽Costco 客服"),

    COSTCO_API_FAIL("02046", "很抱歉，暫時無法申請，如有疑問請洽 Costco客服"),

    COSTCO_SEND_FAIL("02048", "很抱歉，暫時無法申請，請稍後再試"),

	CARD_NO_DATA_ERROR("02050", "查無相關資料，如有疑問，請洽信用卡客服02-8751-1313，由專員協助處理。"),

    SETTING_CARD_ERROR("02052", "出現錯誤(代碼：{0})，請聯絡客服人員。"),

    PRIMARY_CARDHOLDER_ERROR("02053", "此功能僅限正卡持卡人設定"),

    ADDITIONAL_BENEFIT_NOT_FOUND("02054", "您目前持有富邦信用卡無適用之附加權益，歡迎<a class=\"btn-text\" href=\"https://ebank.taipeifubon.com.tw/EXT/common/CAC/Index.faces?fromUrl=https://mobile.taipeifubon.com.tw&fromNo=001\">線上加辦富邦J卡</a>享更多權益優惠，或洽信用卡客服 <a class=\"btn-text\" href=\"tel:02-87511313\" >02-8751-1313</a>，由專人為您服務。");

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
