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

// @formatter:off
/**
 * @(#)EB572667Req.java
 * 
 * <p>Description:查詢貸款繳費明細(貸款類型=存單質借)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/16, Yoyo Lin	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EB572667Req extends TxHeadRq {

    private static final long serialVersionUID = 1L;

    /** 帳號. */
    private String accountNo;

    /** 文件編號. */
    private String docNo;

    /** 查詢起日. */
    private Date startDate;

    /** 查詢迄日. */
    private Date endDate;

    /**
     * @return {@link #accountNo}
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     *            {@link #accountNo}
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return {@link #docNo}
     */
    public String getDocNo() {
        return docNo;
    }

    /**
     * @param docNo
     *            {@link #docNo}
     */
    public void setDocNo(String docNo) {
        this.docNo = docNo;
    }

    /**
     * @return {@link #startDate}
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            {@link #startDate}
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
