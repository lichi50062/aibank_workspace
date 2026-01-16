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
package com.tfb.aibank.chl.creditcard.resource.dto;

import java.util.Date;
import java.util.List;

// @formatter:off
/**
 * @(#)DebitCardTxnDetailRs.java
 * 
 * <p>Description:簽帳金融卡帳單明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/21, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DebitCardTxnDetailRs {

    /** 出帳明細-帳務年月日 */
    private Date lsnYYYMM;

    /** 出帳明細-帳務年月日 */
    private String bonuslBal;

    /** 未出帳消費明細 是否有未扣款成功的消費 */
    private Boolean isVDW002RNcbSts1;

    /** 查詢簽帳金融卡帳單明細 */
    private List<DebitCardBillRep> debitCardBillRep;

    /**
     * @return the lsnYYYMM
     */
    public Date getLsnYYYMM() {
        return lsnYYYMM;
    }

    /**
     * @param lsnYYYMM
     *            the lsnYYYMM to set
     */
    public void setLsnYYYMM(Date lsnYYYMM) {
        this.lsnYYYMM = lsnYYYMM;
    }

    /**
     * @return the bonuslBal
     */
    public String getBonuslBal() {
        return bonuslBal;
    }

    /**
     * @param bonuslBal
     *            the bonuslBal to set
     */
    public void setBonuslBal(String bonuslBal) {
        this.bonuslBal = bonuslBal;
    }

    /**
     * @return the isVDW002RNcbSts1
     */
    public Boolean getIsVDW002RNcbSts1() {
        return isVDW002RNcbSts1;
    }

    /**
     * @param isVDW002RNcbSts1
     *            the isVDW002RNcbSts1 to set
     */
    public void setIsVDW002RNcbSts1(Boolean isVDW002RNcbSts1) {
        this.isVDW002RNcbSts1 = isVDW002RNcbSts1;
    }

    /**
     * @return the debitCardBillRep
     */
    public List<DebitCardBillRep> getDebitCardBillRep() {
        return debitCardBillRep;
    }

    /**
     * @param debitCardBillRep
     *            the debitCardBillRep to set
     */
    public void setDebitCardBillRep(List<DebitCardBillRep> debitCardBillRep) {
        this.debitCardBillRep = debitCardBillRep;
    }

}
