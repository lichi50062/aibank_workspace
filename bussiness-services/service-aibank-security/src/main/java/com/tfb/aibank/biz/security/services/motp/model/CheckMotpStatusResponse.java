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
package com.tfb.aibank.biz.security.services.motp.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CheckMotpStatusResponse.java
 * 
 * <p>Description:使用MOTP驗證前檢查狀態 - Response</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/16, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "使用MOTP驗證前檢查狀態 - Response")
public class CheckMotpStatusResponse {

    /** 檢查狀態類型 */
    @Schema(description = "檢查狀態類型")
    private String motpAuthVerifyType;

    /**
     * @return the motpAuthVerifyType
     */
    public String getMotpAuthVerifyType() {
        return motpAuthVerifyType;
    }

    /**
     * @param motpAuthVerifyType
     *            the motpAuthVerifyType to set
     */
    public void setMotpAuthVerifyType(String motpAuthVerifyType) {
        this.motpAuthVerifyType = motpAuthVerifyType;
    }

}
