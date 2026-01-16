/**
 * 
 */
package com.tfb.aibank.chl.general.ot003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NGNOT003010Rs.java
*
* <p>Description: 收付 準備參數去轉帳</p>
*
* <p>Modify History:</p>
* <ol>v1, 2023/06/15, Alex PY Li
*  <li>[新增說明]</li>
* </ol>
*/
//@formatter:on
@Component
public class NGNOT003011Rs implements RsData {
    /** 轉出帳號 */
    private String payerAccount;

    /** 轉入銀行 */
    private String payeeBank;

    /** 轉入帳號 */
    private String payeeAccount;

    /**
     * @return the payerAccount
     */
    public String getPayerAccount() {
        return payerAccount;
    }

    /**
     * @param payerAccount
     *            the payerAccount to set
     */
    public void setPayerAccount(String payerAccount) {
        this.payerAccount = payerAccount;
    }

    /**
     * @return the payeeBank
     */
    public String getPayeeBank() {
        return payeeBank;
    }

    /**
     * @param payeeBank
     *            the payeeBank to set
     */
    public void setPayeeBank(String payeeBank) {
        this.payeeBank = payeeBank;
    }

    /**
     * @return the payeeAccount
     */
    public String getPayeeAccount() {
        return payeeAccount;
    }

    /**
     * @param payeeAccount
     *            the payeeAccount to set
     */
    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

}
