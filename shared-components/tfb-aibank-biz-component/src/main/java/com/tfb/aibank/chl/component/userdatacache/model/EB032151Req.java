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

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB032151Req.java
 * 
 * <p>Description:電文：EB032151 上送欄位</p>
 * 
 * <p>Modify History:</p>
 * v1.0;2024/02/15; Alex PY Li	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Schema(description = "EB032151 上送欄位 RQ")
public class EB032151Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 功能 */
    @Schema(description = "功能")
    private String func;
    /** 客戶統一編號 */
    @Schema(description = "客戶統一編號")
    private String custNo;

    /**
     * @return the func
     */
    public String getFunc() {
        return func;
    }

    /**
     * @param func
     *            the func to set
     */
    public void setFunc(String func) {
        this.func = func;
    }

    /**
     * @return the custNo
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * @param custNo
     *            the custNo to set
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

}
