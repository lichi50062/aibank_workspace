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
 * @(#)MidQueryVerifyResultRequest.java
 * 
 * <p>Description:台網MID驗證 - QueryVerifyResult - Request</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/06, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "台網MID驗證 - QueryVerifyResult - Request")
public class MidQueryVerifyResultRequest {

    /** 驗證結果代碼 */
    @Schema(description = "驗證結果代碼")
    private String midAuthCode;

    /** 驗證結果描述 */
    @Schema(description = "驗證結果描述")
    private String midAuthMsg;

    /** 身份證字號 */
    @Schema(description = "身份證字號")
    private String custId;

    /** MOTP裝置綁定鍵值 */
    @Schema(description = "MOTP裝置綁定鍵值")
    private Integer motpMidKey;

    /**
     * @return the midAuthCode
     */
    public String getMidAuthCode() {
        return midAuthCode;
    }

    /**
     * @param midAuthCode
     *            the midAuthCode to set
     */
    public void setMidAuthCode(String midAuthCode) {
        this.midAuthCode = midAuthCode;
    }

    /**
     * @return the midAuthMsg
     */
    public String getMidAuthMsg() {
        return midAuthMsg;
    }

    /**
     * @param midAuthMsg
     *            the midAuthMsg to set
     */
    public void setMidAuthMsg(String midAuthMsg) {
        this.midAuthMsg = midAuthMsg;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId
     *            the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * @return the motpMidKey
     */
    public Integer getMotpMidKey() {
        return motpMidKey;
    }

    /**
     * @param motpMidKey
     *            the motpMidKey to set
     */
    public void setMotpMidKey(Integer motpMidKey) {
        this.motpMidKey = motpMidKey;
    }

}
