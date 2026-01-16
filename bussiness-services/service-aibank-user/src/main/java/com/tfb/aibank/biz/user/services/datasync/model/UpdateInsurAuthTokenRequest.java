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
* @(#)UpdateInsurAuthTokenRequest.java
* 
* <p>Description: 富邦人壽getAuthToken 上行request</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/07, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Schema(description = "富邦人壽getAuthToken 上行request")
public class UpdateInsurAuthTokenRequest {

    /**
     * 來源系統代碼
     */
    @Schema(description = "來源系統代碼")
    private String sysId;

    /**
     * 來源交易編號
     */
    @Schema(description = "來源交易編號")
    private String sysCaseNo;

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getSysCaseNo() {
        return sysCaseNo;
    }

    public void setSysCaseNo(String sysCaseNo) {
        this.sysCaseNo = sysCaseNo;
    }
}
