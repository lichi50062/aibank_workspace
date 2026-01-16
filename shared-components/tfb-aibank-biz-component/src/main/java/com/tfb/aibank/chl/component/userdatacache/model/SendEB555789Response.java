/**
 * 
 */
package com.tfb.aibank.chl.component.userdatacache.model;

import java.util.List;

//@formatter:off
/**
* @(#)SendEB555789Response.java
* 
* <p>Description:EB555789 取得貸款帳號 Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class SendEB555789Response {

    /** 客戶身分證號 */
    private String idno;

    /** 電子通路分戶代碼 */
    private String nameCod;

    /** 授信分行別 */
    private String brhName;

    /** 生日 */
    private String date2;

    /** 婚姻狀況 */
    private String maritalStatus1;

    /** 貸款帳戶清單 */
    private List<LoanAccount> loanAccount;

    /**
     * @return the idno
     */
    public String getIdno() {
        return idno;
    }

    /**
     * @param idno
     *            the idno to set
     */
    public void setIdno(String idno) {
        this.idno = idno;
    }

    /**
     * @return the nameCod
     */
    public String getNameCod() {
        return nameCod;
    }

    /**
     * @param nameCod
     *            the nameCod to set
     */
    public void setNameCod(String nameCod) {
        this.nameCod = nameCod;
    }

    /**
     * @return the brhName
     */
    public String getBrhName() {
        return brhName;
    }

    /**
     * @param brhName
     *            the brhName to set
     */
    public void setBrhName(String brhName) {
        this.brhName = brhName;
    }

    /**
     * @return the date2
     */
    public String getDate2() {
        return date2;
    }

    /**
     * @param date2
     *            the date2 to set
     */
    public void setDate2(String date2) {
        this.date2 = date2;
    }

    /**
     * @return the maritalStatus1
     */
    public String getMaritalStatus1() {
        return maritalStatus1;
    }

    /**
     * @param maritalStatus1
     *            the maritalStatus1 to set
     */
    public void setMaritalStatus1(String maritalStatus1) {
        this.maritalStatus1 = maritalStatus1;
    }

    /**
     * @return the loanAccount
     */
    public List<LoanAccount> getLoanAccount() {
        return loanAccount;
    }

    /**
     * @param loanAccount
     *            the loanAccount to set
     */
    public void setLoanAccount(List<LoanAccount> loanAccount) {
        this.loanAccount = loanAccount;
    }

}
