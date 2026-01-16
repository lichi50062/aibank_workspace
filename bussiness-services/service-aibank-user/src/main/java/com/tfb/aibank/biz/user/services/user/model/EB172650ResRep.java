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
package com.tfb.aibank.biz.user.services.user.model;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB172650ResRep.java
 * 
 * <p>Description:EB172650 電文下行 Repeat</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "EB172650 電文下行 Repeat")
public class EB172650ResRep implements Serializable {

    private static final long serialVersionUID = 5133378992122840769L;

    /** 幣別 */
    @Schema(description = "幣別")
    private String cur1;
    /** 最近異動日 */
    @Schema(description = "最近異動日")
    private String txDate1;
    /** 存款餘額 */
    @Schema(description = "存款餘額")
    private String fsBal1;

    public String getCur1() {
        return cur1;
    }

    public void setCur1(String cur1) {
        this.cur1 = cur1;
    }

    public String getTxDate1() {
        return txDate1;
    }

    public void setTxDate1(String txDate1) {
        this.txDate1 = txDate1;
    }

    public String getFsBal1() {
        return fsBal1;
    }

    public void setFsBal1(String fsBal1) {
        this.fsBal1 = fsBal1;
    }

}
