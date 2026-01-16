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
 * @(#)EB382607Req.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=學貸(63)-01就貸)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/15, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB382607Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 公教帳號. */
    @Schema(description = "公教帳號")
    private String acnoOsl;

    /** 學年度-學期. */
    @Schema(description = "學年度-學期")
    private String yrTerm;

    /** 查詢起日. */
    @Schema(description = "查詢起日")
    private Date strDate;

    /** 查詢迄日. */
    @Schema(description = "查詢迄日")
    private Date endDate;


    /**
     * @return {@link #acnoOsl}
     */
    public String getAcnoOsl() {
        return acnoOsl;
    }

    /**
     * @param acnoOsl
     *            {@link #acnoOsl}
     */
    public void setAcnoOsl(String acnoOsl) {
        this.acnoOsl = acnoOsl;
    }

    /**
     * @return {@link #yrTerm}
     */
    public String getYrTerm() {
        return yrTerm;
    }

    /**
     * @param yrTerm
     *            {@link #yrTerm}
     */
    public void setYrTerm(String yrTerm) {
        this.yrTerm = yrTerm;
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
