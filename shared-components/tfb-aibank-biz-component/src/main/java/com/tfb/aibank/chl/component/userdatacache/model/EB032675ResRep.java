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
 * @(#)EB032675ResRep.java
 * 
 * <p>Description:查詢客戶各類註記(禁銷、FATCA、弱勢、專業投資人法人)，電文(EB032675)下行 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "查詢客戶各類註記(禁銷、FATCA、弱勢、專業投資人法人)，電文(EB032675)下行 Repeat")
public class EB032675ResRep implements Serializable {

    private static final long serialVersionUID = 6387703768808116523L;

    /** 帳號 */
    @Schema(description = "帳號")
    private String acctNo;

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

}
