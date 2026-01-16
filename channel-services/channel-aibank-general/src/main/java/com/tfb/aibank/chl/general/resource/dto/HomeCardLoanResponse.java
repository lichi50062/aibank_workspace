package com.tfb.aibank.chl.general.resource.dto;

import java.math.BigDecimal;
import java.util.List;
import com.tfb.aibank.chl.component.userdatacache.model.LoanAccount;

// @formatter:off
/**
 * @(#)HomeCardLoanResponse.java
 * 
 * <p>Description:首頁貸款牌卡資料查詢HomeCardLoanResponse.</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HomeCardLoanResponse {

    private static final long serialVersionUID = -6807124593174891035L;

    public HomeCardLoanResponse() {
        // default constructor
    }

    /**
     * 是否沒有貸款
     */
    private boolean noLoan = false;

    /**
     * 是特殊貸款狀態
     */
    private boolean specialStatus = false;

    /** 貸款帳戶清單 */
    private List<LoanAccount> loanAccounts;

    /**
     * 最高增貸
     */
    private Long loanIncreaseMaxAmt;


    /**
     * AIB_最高可貸額度(元)
     */
    private Long aibAmt;

    /**
     * AIB_分期型第一段期數
     */
    private Integer aibRate1Period;

    /**
     * AIB_名單分類
     */
    private Integer aibFlag;

    /**
     * AIB_借款期間(總期間)
     */
    private Integer aibPeriod;

    /**
     * AIB_分期攤還區之借款利率(首N期固定)
     */
    private BigDecimal aibRate1;

    /**
     * AIB_分期攤還區之借款利率(第N+1期後即時機動)
     */
    private BigDecimal aibRate2;

    private String errorCode;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Long getLoanIncreaseMaxAmt() {
        return loanIncreaseMaxAmt;
    }

    public void setLoanIncreaseMaxAmt(Long loanIncreaseMaxAmt) {
        this.loanIncreaseMaxAmt = loanIncreaseMaxAmt;
    }

    public Long getAibAmt() {
        return aibAmt;
    }

    public void setAibAmt(Long aibAmt) {
        this.aibAmt = aibAmt;
    }

    public Integer getAibRate1Period() {
        return aibRate1Period;
    }

    public void setAibRate1Period(Integer aibRate1Period) {
        this.aibRate1Period = aibRate1Period;
    }

    public Integer getAibFlag() {
        return aibFlag;
    }

    public void setAibFlag(Integer aibFlag) {
        this.aibFlag = aibFlag;
    }

    public Integer getAibPeriod() {
        return aibPeriod;
    }

    public void setAibPeriod(Integer aibPeriod) {
        this.aibPeriod = aibPeriod;
    }

    public BigDecimal getAibRate1() {
        return aibRate1;
    }

    public void setAibRate1(BigDecimal aibRate1) {
        this.aibRate1 = aibRate1;
    }

    public BigDecimal getAibRate2() {
        return aibRate2;
    }

    public void setAibRate2(BigDecimal aibRate2) {
        this.aibRate2 = aibRate2;
    }

    public boolean isNoLoan() {
        return noLoan;
    }

    public void setNoLoan(boolean noLoan) {
        this.noLoan = noLoan;
    }

    public boolean isSpecialStatus() {
        return specialStatus;
    }

    public void setSpecialStatus(boolean specialStatus) {
        this.specialStatus = specialStatus;
    }

    public List<LoanAccount> getLoanAccounts() {
        return loanAccounts;
    }

    public void setLoanAccounts(List<LoanAccount> loanAccounts) {
        this.loanAccounts = loanAccounts;
    }
}
