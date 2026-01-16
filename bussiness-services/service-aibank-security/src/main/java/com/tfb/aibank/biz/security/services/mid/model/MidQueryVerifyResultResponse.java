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
package com.tfb.aibank.biz.security.services.mid.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)MidQueryVerifyResultResponse.java
 * 
 * <p>Description:台網MID驗證 - QueryVerifyResult - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/06, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "台網MID驗證 - QueryVerifyResult - Response")
public class MidQueryVerifyResultResponse {

    /** 交易結果代碼 */
    @Schema(description = "交易結果代碼")
    private String returnCode;

    /** 錯誤訊息 */
    @Schema(description = "錯誤訊息")
    private String returnCodeDesc;

    /** 驗證時間 */
    @Schema(description = "驗證時間")
    private String verifyTime;

    /**
     * @return the returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode
     *            the returnCode to set
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return the returnCodeDesc
     */
    public String getReturnCodeDesc() {
        return returnCodeDesc;
    }

    /**
     * @param returnCodeDesc
     *            the returnCodeDesc to set
     */
    public void setReturnCodeDesc(String returnCodeDesc) {
        this.returnCodeDesc = returnCodeDesc;
    }

    /**
     * @return the verifyTime
     */
    public String getVerifyTime() {
        return verifyTime;
    }

    /**
     * @param verifyTime
     *            the verifyTime to set
     */
    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

}
