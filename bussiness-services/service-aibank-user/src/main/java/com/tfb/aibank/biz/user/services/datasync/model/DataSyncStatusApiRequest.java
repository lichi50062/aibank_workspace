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
* @(#)DataSyncStatusApiRequest.java
* 
* <p>Description: 欲更新富邦證券/人壽彙整狀態/p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/06, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "富邦證券/人壽彙整狀態")
public class DataSyncStatusApiRequest {

    /**
     * 富邦證券彙整狀態
     */
    @Schema(description = "富邦證券彙整狀態")
    private String secur;

    /**
     * 富邦人壽彙整狀態
     */
    @Schema(description = "富邦人壽彙整狀態")
    private String insur;

    /**
     * 異動情境(證券/富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)
     */
    @Schema(description = "異動情境(證券/富壽)(0:未曾發過API、1:初次設定、2：使用者設定、3：系統自動關閉、4：銀行銷戶)")
    private String useType;

    public String getSecur() {
        return secur;
    }

    public void setSecur(String secur) {
        this.secur = secur;
    }

    public String getInsur() {
        return insur;
    }

    public void setInsur(String insur) {
        this.insur = insur;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }
}
