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
package com.tfb.aibank.chl.component.userdatacache.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB5556911ReqRep.java
 * 
 * <p>Description:約定轉入帳號 上行電文 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "約定轉入帳號 上行電文 Repeat")
public class EB5556911ReqRep implements Serializable {

    private static final long serialVersionUID = 6167090156935573045L;

    public EB5556911ReqRep() {
        // default constructor
    }

    public EB5556911ReqRep(String acnoIn) {
        this.acnoIn = acnoIn;
    }

    /** 轉入帳號 */
    @Schema(description = "轉入帳號")
    private String acnoIn;

    public String getAcnoIn() {
        return acnoIn;
    }

    public void setAcnoIn(String acnoIn) {
        this.acnoIn = acnoIn;
    }
}
