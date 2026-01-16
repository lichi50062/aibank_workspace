/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.services.datasync.model;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)DataSyncStatusApiResponse.java
* 
* <p>Description: 富邦證券/人壽彙整狀態api response</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "富邦證券/人壽彙整狀態api response")
public class DataSyncStatusApiResponse {

    //@formatter:off
    /**
     * 回覆碼 
     * 0000:成功、
     * 1001:系統錯誤、
     * 1002:無效的系統代號、
     * 1003:參數不足、
     * 1004:驗證碼逾期、
     * 1005:無效的驗證碼、
     * 1006:非客戶
     */
    //@formatter:on
    @Schema(description = "富壽 回覆碼")
    private String returnCode;

    /**
     * 回覆訊息
     */
    @Schema(description = "富壽 回覆訊息")
    private String message;

    /**
     * 驗證碼
     */
    @Schema(description = "富壽 驗證碼")
    private String authToken;

    //@formatter:off
    /**
     * 異動結果代碼
     * 異動結果代碼/說明：
     * S000=更新成功（同意/取消）
     * E901=傳入參數格式不符
     * E902=非約定系統別
     * E903=資料異常
     * E904=更新失敗（同意/取消）
     * E401=客戶帳號檢核失敗(非證券戶)
     * E999=異常
     * X401=驗證未通過
     */
    //@formatter:on
    @Schema(description = "富證 回覆碼")
    private String resultCode;

    /**
     * 異動結果說明
     */
    @Schema(description = "富證 回覆訊息")
    private String resultDesc;


    /**
     * 0000 更新成功(同意/取消)
     * QASS9999=系統錯誤
     * QASB0011=非約定系統別 (Fubon+：T_AI_BANK、網路銀行：T_WEB_BANK): 無此channelCode
     * QASB0021=客戶帳號檢核失敗: 未有證券、複委託、國內外期貨任一帳戶
     * QASB0001=token 錯誤/過期: External JWT 驗證失敗
     * QASF9005={欄位名稱} 不可為空
     * QASF9009=資料庫存取錯誤
     */
    //@formatter:on
    @Schema(description = "富證V2 回覆碼")
    private String statusCode;

    /**
     * 異動結果說明
     */
    @Schema(description = "富證V2 回覆訊息")
    private String statusMsg;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getStatusCode() {
        return statusCode;
    }

    // 為了檢查 DataSyncStatusApiResponseType 時不跟人壽的代碼重複
    public String getStatusCode(boolean checkDataSyncStatusType) {
        String tmpStatusCode = "";
        tmpStatusCode = checkDataSyncStatusType &&  statusCode.equals("0000") ? "S000" : statusCode;
        return tmpStatusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }
}
