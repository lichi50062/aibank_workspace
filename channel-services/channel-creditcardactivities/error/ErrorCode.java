package com.tfb.aibank.chl.creditcardactivities.error;

import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.id.IBSystemId;

//@formatter:off
/**
 * @(#)ErrorCode.java
 * 
 * <p>Description:信用卡活動(NCA)專用錯誤</p>
 * <p>編碼範圍：02901 ~ 03000</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public enum ErrorCode implements IErrorCode {

    OVER_ACTIVITY_ONLINE_LIMIT("02901", "目前使用人數較多，請稍後再試"),

    NO_ACTIVITY_SELECTED("02902", "沒有選擇登錄的活動"),

    ACTIVITY_REGISTER_ERROR("02903", "活動登錄失敗");

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
