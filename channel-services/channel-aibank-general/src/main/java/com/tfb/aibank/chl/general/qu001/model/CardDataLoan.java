package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

import java.math.BigDecimal;


// @formatter:off
/**
 * @(#)CardDataLoan.java
 *
 * <p>Description: 功能牌卡-貸款資料</p>
 *
 * <p>Modify History:</p>
 * v1, 2023/05/22, MartyPan
 * <ol>
 *  <li>功能牌卡-貸款資料</li>
 * </ol>
 */
//@formatter:on
public class CardDataLoan extends CardDataParent {

    /**
     * 特殊貸款狀態Flag -> Y/N
     */
    private String unusualFlag = "N";

    /**
     * 貸款總額
     */
    private BigDecimal totalLoanAmt = BigDecimal.ZERO;

    /**
     * 「還可動用」金額加總
     */
    private BigDecimal totalOdavail1 = BigDecimal.ZERO;

    /**
     * 最高增貸
     */
    private Long loanIncreaseMaxAmt;

    /**
     * 有分期型貸款
     */
    private boolean withInstallmentLoan;
    /**
     * 有分期型貸款
     */
    private boolean onlyInstallmentLoan;

    /**
     * 有循環型貸款
     */
    private boolean withRevolvingLoan;

    /**
     * 擁有的貸款類型數量
     */
    private int loanTypeCount;

    public CardDataLoan() {
        super();
    }

    public CardDataLoan(HomepageCard homepageCard) {
        super(homepageCard);
    }

    public String getUnusualFlag() {
        return unusualFlag;
    }

    public void setUnusualFlag(String unusualFlag) {
        this.unusualFlag = unusualFlag;
    }

    public BigDecimal getTotalLoanAmt() {
        return totalLoanAmt;
    }

    public void setTotalLoanAmt(BigDecimal totalLoanAmt) {
        this.totalLoanAmt = totalLoanAmt;
    }

    public BigDecimal getTotalOdavail1() {
        return totalOdavail1;
    }

    public void setTotalOdavail1(BigDecimal totalOdavail1) {
        this.totalOdavail1 = totalOdavail1;
    }

    public Long getLoanIncreaseMaxAmt() {
        return loanIncreaseMaxAmt;
    }

    public void setLoanIncreaseMaxAmt(Long loanIncreaseMaxAmt) {
        this.loanIncreaseMaxAmt = loanIncreaseMaxAmt;
    }

    public boolean isWithInstallmentLoan() {
        return withInstallmentLoan;
    }

    public void setWithInstallmentLoan(boolean withInstallmentLoan) {
        this.withInstallmentLoan = withInstallmentLoan;
    }

    public boolean isWithRevolvingLoan() {
        return withRevolvingLoan;
    }

    public void setWithRevolvingLoan(boolean withRevolvingLoan) {
        this.withRevolvingLoan = withRevolvingLoan;
    }

    public int getLoanTypeCount() {
        return loanTypeCount;
    }

    public void setLoanTypeCount(int loanTypeCount) {
        this.loanTypeCount = loanTypeCount;
    }

    public boolean isOnlyInstallmentLoan() {
        return onlyInstallmentLoan;
    }

    public void setOnlyInstallmentLoan(boolean onlyInstallmentLoan) {
        this.onlyInstallmentLoan = onlyInstallmentLoan;
    }
}
