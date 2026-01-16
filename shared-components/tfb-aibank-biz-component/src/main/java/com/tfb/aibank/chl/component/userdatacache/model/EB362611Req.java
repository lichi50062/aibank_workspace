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

import java.util.Date;

import com.tfb.aibank.common.model.TxHeadRq;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)EB362611Req.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=房貸(61)-02公教/03國宅)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB362611Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 公教帳號. */
    @Schema(description = "公教帳號")
    private String acnoOt;

    /** 查詢起日. */
    @Schema(description = "查詢起日")
    private Date strDate;

    /** 查詢迄日. */
    @Schema(description = "查詢迄日")
    private Date endDate;

    /**
     * @return {@link #acnoOt}
     */
    public String getAcnoOt() {
        return acnoOt;
    }

    /**
     * @param acnoOt
     *            {@link #acnoOt}
     */
    public void setAcnoOt(String acnoOt) {
        this.acnoOt = acnoOt;
    }

    /**
     * @return {@link #strDate}
     */
    public Date getStrDate() {
        return strDate;
    }

    /**
     * @param strDate
     *            {@link #strDate}
     */
    public void setStrDate(Date strDate) {
        this.strDate = strDate;
    }

    /**
     * @return {@link #endDate}
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            {@link #endDate}
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
