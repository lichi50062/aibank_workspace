/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.general.qu003.model;

// @formatter:off
import com.tfb.aibank.chl.model.account.TransOutAccount;
import com.tfb.aibank.common.util.AccountUtils; /**
 * @(#)FinancialMgmMemberLevel.java
 * 
 * <p>Description:帳號優惠資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class AccountFavor {

    private String accountNo;

    private String accountNoMasked;

    private boolean salaryAccount;
    /**
     * 身障優惠
     */
    private boolean disabilityDiscount;

    private boolean digitalAccount;

    private String branchName;
    private String branchId;

    /** 提款減免次數 */
    private Integer csCnt = 0;

    /** 提款剩餘次數 */
    private Integer csCntR = 0;
    /** 自跨轉減免次數 */
    private Integer trCnt = 0;
    /** 自跨轉剩餘次數 */
    private Integer trCntR = 0;
    /** [沒有]自跨轉剩餘次數資料 */
    private boolean noTrCntData = false;

    private boolean sendFEP512161Error = false;

    public AccountFavor() {
    }

    public AccountFavor(TransOutAccount transOutAccount) {
        this.accountNo = transOutAccount.getAcno();
        this.accountNoMasked = AccountUtils.getDisplayAccountIdLastFive(transOutAccount.getAcno(), "···");
        this.digitalAccount = transOutAccount.isDigital();
        this.branchName = transOutAccount.getBranchName();
        this.branchId = transOutAccount.getDisplayBranchId();
    }

    public String getAccountNoMasked() {
        return accountNoMasked;
    }

    public void setAccountNoMasked(String accountNoMasked) {
        this.accountNoMasked = accountNoMasked;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public boolean isSalaryAccount() {
        return salaryAccount;
    }

    public void setSalaryAccount(boolean salaryAccount) {
        this.salaryAccount = salaryAccount;
    }

    public boolean isDisabilityDiscount() {
        return disabilityDiscount;
    }

    public void setDisabilityDiscount(boolean disabilityDiscount) {
        this.disabilityDiscount = disabilityDiscount;
    }

    public boolean isDigitalAccount() {
        return digitalAccount;
    }

    public void setDigitalAccount(boolean digitalAccount) {
        this.digitalAccount = digitalAccount;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Integer getCsCntR() {
        return csCntR;
    }

    public void setCsCntR(Integer csCntR) {
        this.csCntR = csCntR;
    }

    public Integer getCsCnt() {
        return csCnt;
    }

    public void setCsCnt(Integer csCnt) {
        this.csCnt = csCnt;
    }

    public Integer getTrCnt() {
        return trCnt;
    }

    public void setTrCnt(Integer trCnt) {
        this.trCnt = trCnt;
    }

    public Integer getTrCntR() {
        return trCntR;
    }

    public void setTrCntR(Integer trCntR) {
        this.trCntR = trCntR;
    }

    public boolean isNoTrCntData() {
        return noTrCntData;
    }

    public void setNoTrCntData(boolean noTrCntData) {
        this.noTrCntData = noTrCntData;
    }

    public boolean isSendFEP512161Error() {
        return sendFEP512161Error;
    }

    public void setSendFEP512161Error(boolean sendFEP512161Error) {
        this.sendFEP512161Error = sendFEP512161Error;
    }
}
