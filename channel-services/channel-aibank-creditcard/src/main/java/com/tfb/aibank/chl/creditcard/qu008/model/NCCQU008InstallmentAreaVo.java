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
package com.tfb.aibank.chl.creditcard.qu008.model;

import java.util.List;

// @formatter:off
/**
 * @(#)NCCQU008InstallmentAreaVo.java
 * 
 * <p>Description:010 分期概要區塊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/05, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU008InstallmentAreaVo {

    /**
     * 剩餘分期總額
     */
    private String remainingInstallmentTotal;

    /**
     * 剩餘分期牌卡
     */
    private List<NCCQU008InstallBillCardVo> installBillCards;

    /**
     * @return the remainingInstallmentTotal
     */
    public String getRemainingInstallmentTotal() {
        return remainingInstallmentTotal;
    }

    /**
     * @param remainingInstallmentTotal
     *            the remainingInstallmentTotal to set
     */
    public void setRemainingInstallmentTotal(String remainingInstallmentTotal) {
        this.remainingInstallmentTotal = remainingInstallmentTotal;
    }

    /**
     * @return the installBillCards
     */
    public List<NCCQU008InstallBillCardVo> getInstallBillCards() {
        return installBillCards;
    }

    /**
     * @param installBillCards
     *            the installBillCards to set
     */
    public void setInstallBillCards(List<NCCQU008InstallBillCardVo> installBillCards) {
        this.installBillCards = installBillCards;
    }

}
