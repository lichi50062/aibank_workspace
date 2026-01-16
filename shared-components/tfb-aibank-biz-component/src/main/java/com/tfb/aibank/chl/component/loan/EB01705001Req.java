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
package com.tfb.aibank.chl.component.loan;

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB0170190Req.java
 * 
 * <p>Description:取得貸款協商紀錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Jackie Chien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB01705001Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 放款帳號 */
    @Schema(description = "放款帳號")
    private String accntNumber1;

    /** 選項. */
    @Schema(description = "選項")
    private String defaultString1;

    public String getAccntNumber1() {
        return accntNumber1;
    }

    public void setAccntNumber1(String accntNumber1) {
        this.accntNumber1 = accntNumber1;
    }

    public String getDefaultString1() {
        return defaultString1;
    }

    public void setDefaultString1(String defaultString1) {
        this.defaultString1 = defaultString1;
    }
}
