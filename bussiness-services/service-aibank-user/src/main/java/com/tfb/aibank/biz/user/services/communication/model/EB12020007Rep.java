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
package com.tfb.aibank.biz.user.services.communication.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB12020007Rep.java
 * 
 * <p>Description:查詢/變更客戶通訊資料 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/11, Edward	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "查詢/變更客戶通訊資料 Repeat")
public class EB12020007Rep implements Serializable {

    private static final long serialVersionUID = -793046598018090223L;

    @Schema(description = "帳號")
    private String acno;

    @Schema(description = "幣別")
    private String curr;

    @Schema(description = "電子分戶代碼")
    private String subid;

    public String getAcno() {
        return acno;
    }

    public void setAcno(String acno) {
        this.acno = acno;
    }

    public String getCurr() {
        return curr;
    }

    public void setCurr(String curr) {
        this.curr = curr;
    }

    public String getSubid() {
        return subid;
    }

    public void setSubid(String subid) {
        this.subid = subid;
    }

}
