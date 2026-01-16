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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB67115Res.java
 * 
 * <p>Description:取得客戶是否具備FATCA排外註記，電文(EB67115)下行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/04/25,xwr	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "取得客戶是否具備FATCA排外註記，電文(EB67115)下行")
public class EB67115Res implements Serializable {

    private static final long serialVersionUID = 1519744721971415847L;

    /** FATCA排外註記 */
    @Schema(description = "FATCA排外註記")
    private String unfatcaflag;

	public String getUnfatcaflag() {
		return unfatcaflag;
	}

	public void setUnfatcaflag(String unfatcaflag) {
		this.unfatcaflag = unfatcaflag;
	}
    
}
