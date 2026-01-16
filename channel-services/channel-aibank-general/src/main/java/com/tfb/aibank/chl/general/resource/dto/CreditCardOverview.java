package com.tfb.aibank.chl.general.resource.dto;
import java.math.BigDecimal;

/**
// @formatter:off
 * @(#)CreditCardOverviewModel.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/6/12, Marty P
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CreditCardOverview {


    /**
     * 下期結帳日前已請款消費金額(未出帳單消費金額) (acctIdub)
     */
    private BigDecimal unbilledConsumptionAmount = BigDecimal.ZERO;
    /**
     * 本期帳單剩餘應繳金額 (acctIdDPayd)
     */
    private BigDecimal ccBillRemainAmt = BigDecimal.ZERO;
    /**
     * 分期未入帳金額(消費/特約分期) (AcctIdspub1)
     */
    private BigDecimal specialInstallmentAmt = BigDecimal.ZERO;
    /**
     * 分期未入帳金額(帳單/長循分期) (AcctIdspub2)
     */
    private BigDecimal longTermInstallmentAmt = BigDecimal.ZERO;
    /**
     * 歸戶信用額度
     */
    private BigDecimal acctIdCram = BigDecimal.ZERO;
    /**
     * 歸戶可用餘額
     */
    private BigDecimal acctIdPcbl = BigDecimal.ZERO;
    

    public BigDecimal getUnbilledConsumptionAmount() {
        return unbilledConsumptionAmount;
    }

    public void setUnbilledConsumptionAmount(BigDecimal unbilledConsumptionAmount) {
        this.unbilledConsumptionAmount = unbilledConsumptionAmount;
    }

    public BigDecimal getCcBillRemainAmt() {
        return ccBillRemainAmt;
    }

    public void setCcBillRemainAmt(BigDecimal ccBillRemainAmt) {
        this.ccBillRemainAmt = ccBillRemainAmt;
    }

    public BigDecimal getSpecialInstallmentAmt() { return specialInstallmentAmt; }

    public void setSpecialInstallmentAmt(BigDecimal specialInstallmentAmt) {
        this.specialInstallmentAmt = specialInstallmentAmt;
    }

    public BigDecimal getLongTermInstallmentAmt() { return longTermInstallmentAmt; }

    public void setLongTermInstallmentAmt(BigDecimal longTermInstallmentAmt) {
        this.longTermInstallmentAmt = longTermInstallmentAmt;
    }

	public BigDecimal getAcctIdCram() {
		return acctIdCram;
	}

	public void setAcctIdCram(BigDecimal acctIdCram) {
		this.acctIdCram = acctIdCram;
	}

	public BigDecimal getAcctIdPcbl() {
		return acctIdPcbl;
	}

	public void setAcctIdPcbl(BigDecimal acctIdPcbl) {
		this.acctIdPcbl = acctIdPcbl;
	}
}

