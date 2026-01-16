package com.tfb.aibank.biz.pushlistener.model;

// @formatter:off
/**
 * @(#)UpdateStatusModel.java
 * 
 * <p>Description:承載解析從 IMP 放入 Message Queue 的訊息</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UpdateStatusModel {

    public static final String STATUS_PENDING = "0";
    public static final String STATUS_SENDING = "1";
    public static final String STATUS_SUCCESS = "2";
    public static final String STATUS_ERROR = "3";

    private String rowId = "";
    private String errorCode = "0000";
    private String errorMessage = "";
    /**
     * 狀態 0：未發送, 1:發送中 , 2：已發送成功, 3：已投送失敗
     */
    private String status = "0";

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
