/**
 * 
 */
package com.tfb.aibank.component.accountinfoLoan;

/**
 * @author john
 *
 */
public class AccountInfoLoan extends AccountInfoLoanVo {

    /**
     * 類型名稱，Ex: 房貸
     */
    private String productName;

    /**
     * 產品名稱，Ex: 分期型房貸
     */
    private String loanName;

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     *            the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the loanName
     */
    public String getLoanName() {
        return loanName;
    }

    /**
     * @param loanName
     *            the loanName to set
     */
    public void setLoanName(String loanName) {
        this.loanName = loanName;
    }

}
