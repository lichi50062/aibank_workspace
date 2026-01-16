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

import java.util.List;

import com.tfb.aibank.common.model.TxHeadRs;

// @formatter:off
/**
 * @(#)EB362611Res.java
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
public class EB362611Res extends TxHeadRs {

    private static final long serialVersionUID = 1L;

    /** 統一編號. */
    private String custNo;

    /** 戶名. */
    private String custName;

    /** 代繳機關代號. */
    private String grpOtAcno;

    /** 代繳機關戶名. */
    private String grpOtName;

    /** 代繳機關備註 . */
    private String rmkAcnoOt;

    /** 第一次上送repeats. */
    private List<LoanDetailBean> repeats;

    /**
     * @return {@link #custNo}
     */
    public String getCustNo() {
        return custNo;
    }

    /**
     * @param custNo
     *            {@link #custNo}
     */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    /**
     * @return {@link #custName}
     */
    public String getCustName() {
        return custName;
    }

    /**
     * @param custName
     *            {@link #custName}
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * @return {@link #grpOtAcno}
     */
    public String getGrpOtAcno() {
        return grpOtAcno;
    }

    /**
     * @param grpOtAcno
     *            {@link #grpOtAcno}
     */
    public void setGrpOtAcno(String grpOtAcno) {
        this.grpOtAcno = grpOtAcno;
    }

    /**
     * @return {@link #grpOtName}
     */
    public String getGrpOtName() {
        return grpOtName;
    }

    /**
     * @param grpOtName
     *            {@link #grpOtName}
     */
    public void setGrpOtName(String grpOtName) {
        this.grpOtName = grpOtName;
    }

    /**
     * @return {@link #rmkAcnoOt}
     */
    public String getRmkAcnoOt() {
        return rmkAcnoOt;
    }

    /**
     * @param rmkAcnoOt
     *            {@link #rmkAcnoOt}
     */
    public void setRmkAcnoOt(String rmkAcnoOt) {
        this.rmkAcnoOt = rmkAcnoOt;
    }

    /**
     * @return {@link #repeats}
     */
    public List<LoanDetailBean> getRepeats() {
        return repeats;
    }

    /**
     * @param repeats
     *            {@link #repeats}
     */
    public void setRepeats(List<LoanDetailBean> repeats) {
        this.repeats = repeats;
    }

}
