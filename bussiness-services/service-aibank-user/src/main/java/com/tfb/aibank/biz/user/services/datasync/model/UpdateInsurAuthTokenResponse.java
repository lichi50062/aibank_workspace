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
* @(#)UpdateInsurAuthTokenResponse.java
* 
* <p>Description: 富邦人壽getAuthToken API 下行response</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "富邦人壽getAuthToken API 下行response")
public class UpdateInsurAuthTokenResponse {

    /**
     * 回覆碼 (00: 成功, 01: 系統錯誤, 02: 無效的系統代號, 03: 參數不足)
     */
    @Schema(description = "回覆碼")
    private String returnCode;

    /**
     * 回覆訊息
     */
    @Schema(description = "回覆訊息")
    private String message;

    /**
     * 驗證碼
     */
    @Schema(description = "驗證碼")
    private String authToken;

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
}
