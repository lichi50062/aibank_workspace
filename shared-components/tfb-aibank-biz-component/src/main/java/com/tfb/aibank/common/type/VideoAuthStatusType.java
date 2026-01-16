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

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;

/**
 * VideoAuth status
 */
public enum VideoAuthStatusType implements IErrorCode {

    APP_VERSION_IS_NOT_COMPATIBLE("3001", "目前版本無法使用此功能，請更新富邦行動銀行APP", true),

    USER_HAND_UP_VIDEO_CALL_WITHOUT_CONVERSATION("4001", "使用者未與視訊客服通話，並自行掛斷", true),

    SDK_CONNECT_MEDIA_SERVER_FAIL("5001", "視訊SDK連線MEDIA SERVER異常"),

    SDK_CONNECT_ERR("5002", "視訊SDK連線異常，使用者通話被迫中斷"),

    CAMERA_NOT_FOUND("6001", "無視訊鏡頭", true),

    CAMERA_NOT_AUTHORIZE("6002", "未授權使用視訊鏡頭", true),

    MIC_NOT_FOUND("6003", "無麥克風", true),

    MIC_NOT_AUTHORIZE("6004", "未授權使用麥克風", true),

    CAMERA_AND_MIC_NOT_AUTHORIZE("6005", "未授權使用視訊鏡頭和麥克風", true),

    DEVICE_NOT_SURPPORT("7001", "不支援此裝置", true),

    BROWSER_NOT_SUPPORT("7002", "不支援瀏覽器", true),

    VIDEO_SERVICE_NOT_IN_SERVICE_TIME("8001", "視訊系統目前非服務時間，目前無法提供視訊服務", true),

    VIDEO_SERVICE_EXCEED_WAITING_RESTRICTION("8002", "視訊系統目前超出等待人數上限，請稍後再試", true),

    VIDEO_SERVICE_TMP_UNAVAILABLE("8003", "視訊系統暫時未開放服務，請稍後再試", true),

    SDK_CONNECT_INFO_NOT_FOUND("9001", "視訊取得SDK連線資訊時，發現空值"),

    API_CONNECT_FAIL_CHECK_SERVICE_STATUS("9004", "視訊API連線異常-向視訊系統查詢客服是否在線服務"),

    API_CONNECT_FAIL_CHECK_WAIT_COUNT("9005", "視訊API連線異常-向視訊系統查詢目前等待人數"),

    BASIC_USER_DATA_GENERATE_ERR("9006", "產生VideoAuthRaiseUserData時發生錯誤"),

    DB_INSERT_UPDATE_ERR("9007", "資料表欄位新增/更新時發生錯誤"),

    API_VERIFY_RESULT_CONNECT_FAIL("9010", "視訊API查詢視訊結果-向視訊系統查詢審核結果時，發生連線異常"),

    API_VERIFY_RESULT_EMPTY_APPLY_NO("9011", "視訊API查詢視訊結果-無案編資訊 resultType=O"),

    API_VERIFY_RESULT_CANNOT_ANALYSIS_DATA("9012", "視訊API查詢視訊結果-無法辨識的回覆代碼 resultType"),

    UNKNOWN_FLOW_VIDEO_AUTH_FAIL("9997", "操作流程錯誤，請重新操作！", true),

    UNKNOWN_CALL_END_TYPE("9998", "前端傳入未知掛斷代碼"),

    UNKNOWN_EXCEPTION("9999", "系統遇到一點小狀況，非常抱歉。請您稍候再試唷！");

    /** 異常資料 */
    private ErrorStatus error = null;

    /** 是否顯示客製化訊息 */
    private boolean isNeedThrowCustomDesc = false;

    VideoAuthStatusType(String errorCode, String memo) {
        this(errorCode, memo, SeverityType.ERROR);
    }

    VideoAuthStatusType(String errorCode, String memo, boolean isNeedThrowCustomDesc) {
        this(errorCode, memo, SeverityType.ERROR);
        this.isNeedThrowCustomDesc = isNeedThrowCustomDesc;
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
    VideoAuthStatusType(String errorCode, String memo, SeverityType severity) {
        // 視訊驗證錯誤代碼 VA9999
        error = new ErrorStatus("VA", errorCode, severity, memo);
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
     * 取得備註說明a
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

    public static VideoAuthStatusType findType(String errCode) {
        for (VideoAuthStatusType type : VideoAuthStatusType.values()) {
            if (type.getErrorCode().equals(errCode)) {
                return type;
            }
        }
        return VideoAuthStatusType.UNKNOWN_EXCEPTION;
    }

    public boolean isNeedThrowCustomDesc() {
        return isNeedThrowCustomDesc;
    }
}
