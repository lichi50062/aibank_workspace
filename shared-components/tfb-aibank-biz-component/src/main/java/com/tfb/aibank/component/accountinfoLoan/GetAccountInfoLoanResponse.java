/**
 * 
 */
package com.tfb.aibank.component.accountinfoLoan;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class GetAccountInfoLoanResponse {

    /** 貸款帳號資訊 */
    @Schema(description = "貸款帳號資訊")
    private List<AccountInfoLoanVo> accountInfoLoan;

    /** 貸款帳號名稱資訊 */
    @Schema(description = "貸款帳號名稱資訊")
    private List<AccountInfoLoanNameVo> accountInfoLoanName;

    /**
     * @return the accountInfoLoan
     */
    public List<AccountInfoLoanVo> getAccountInfoLoan() {
        return accountInfoLoan;
    }

    /**
     * @param accountInfoLoan
     *            the accountInfoLoan to set
     */
    public void setAccountInfoLoan(List<AccountInfoLoanVo> accountInfoLoan) {
        this.accountInfoLoan = accountInfoLoan;
    }

    /**
     * @return the accountInfoLoanName
     */
    public List<AccountInfoLoanNameVo> getAccountInfoLoanName() {
        return accountInfoLoanName;
    }

    /**
     * @param accountInfoLoanName
     *            the accountInfoLoanName to set
     */
    public void setAccountInfoLoanName(List<AccountInfoLoanNameVo> accountInfoLoanName) {
        this.accountInfoLoanName = accountInfoLoanName;
    }

}
