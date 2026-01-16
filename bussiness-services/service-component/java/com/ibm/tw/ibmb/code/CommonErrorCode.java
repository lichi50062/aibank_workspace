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
package com.ibm.tw.ibmb.code;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.id.IBSystemId;

// @formatter:off
/**
 * @(#)CommonErrorCode.java
 * 
 * <p>Description:在執行服務時，產生的共同錯誤代碼</p>
 * <p>前端範圍：00001 ~ 00050</p>
 * <p>編碼範圍：00051 ~ 00200</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum CommonErrorCode implements IErrorCode {

    TASK_FIELD_ERROR("00001", "欄位檢查失敗"),

    DEVICE_BINDING_ERROR("00002", "裝置綁定失敗，請稍後再試"),

    USER_NOT_LOGIN("00003", "作業逾時或已登出，感謝您的使用，如要再次查詢或交易請重新登入。"),

    ILLEGAL_ACCESS_EXCEPTION("00004", "OAuth 權限不符"),

    DUPLICATED_TXN_TOKEN("00005", "重覆交易錯誤"),

    CONTENT_INCLUDE_HARD_CHAR("00006", "輸入內容有特殊字無法辦識，請重新輸入。"),

    DATE_FORMAT_ERROR("00051", "日期格式錯誤"),

    REDIS_UNAVAIBLE("00052", "Redis Unavailable"),

    SERVICE_UNAVALIBLE("00053", "系統維護中，請稍後再試。"),

    FEIGN_CLIENT_TIMEOUT("00054", "Feign Client request timeout"),

    HYSTRIX_TIMEOUT("00055", "Hystrix command timeout"),

    TASK_DISABLED("00056", "此功能開發中尚未開放使用"),

    INVALID_TASK("00057", "交易設定不存在"),

    FAIL_TO_INIT_GLOBAL_MENU("00058", "選單初始化異常"),

    DATABASE_EXCEPTION("00059", "資料庫發生異常"),

    HYSTRIX_RUNTIME_EXCEPTION("00060", "交易作業逾時，請重新再試，謝謝。"),

    HOST_TIME_OUT("00061", "主機回應逾時! 目前無法取得資料，請稍後再試，謝謝!"),

    TASK_UNAUTHORIZED_ERROR("00062", "目前沒有權限，請洽詢權限設定主管。"),

    E2E_ERROR("00063", "HSM錯誤"),

    DATA_NOT_FOUND("00064", "查無資料"),

    ARGUMENTS_NOT_FOUND("00065", "參數錯誤"),

    XML_EXCEPTION("00066", "XML Exception"),

    MISSION_SCOPE_ANNOTIATION("00067", "Task 未標示 Prototype Scope"),

    PERMISSION_DENIED("00069", "權限被拒絕"),

    BIZ_UNAVALIBLE("00070", "系統維護中，請稍後再試。"),

    SESSION_EXPIRED("00071", "作業已逾時, 請重新登入"),

    INPUT_OR_OUTPUT_EXCEPTION("00072", "輸入或輸出異常"),

    BRANCH_NO_ERROR("00073", "讀取分行代碼錯誤"),

    TERMS_DATA_NOT_FOUND("00074", "條款，查無資料"),

    UNAUTHORIZED_OPERATIONAL("00075", "未授權的操作"),

    BEAN_WRAPPER_ERROR("00076", "BEAN轉換錯誤"),

    TASK_NOT_IN_THE_MENU("00077", "交易代碼不存在適用的選單中，無法使用此功能"),

    DATA_OWNERSHIP_EXCEPTION("00078", "資料歸屬錯誤"),

    DEFAULT_ERROR_DESCRIPTION("00200", "目前系統忙碌中，請稍後再試"),

    UNKNOWN_EXCEPTION("X9999", "系統發生異常，請稍候再試");

    /** 異常資料 */
    private ErrorStatus error = null;

    CommonErrorCode(String errorCode, String memo) {
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
    CommonErrorCode(String errorCode, String memo, SeverityType severity) {
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
