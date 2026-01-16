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
package com.tfb.aibank.chl.creditcard.qu009.model;

import com.tfb.aibank.chl.creditcard.resource.dto.EB602655ARepeat;
import com.tfb.aibank.chl.model.account.TransOutAccount;

// @formatter:off
/**
 * @(#)NCCQU009AccModel.java
 * 
 * <p>Description:帳號資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU009AccModel {

    private static final long serialVersionUID = 1L;

    private String displayAccount1;

    private String accountDropdown1;

    private String accountLabel1;

    private String displayAcctNickname;
    private String acno;

    public NCCQU009AccModel(TransOutAccount account, EB602655ARepeat repeat) {
        account.setAcno(repeat.getAcno());
        account.prepareAccountInfo(account);
        this.acno = account.getAcno();
        this.displayAccount1 = account.getDisplayAccount1();
        this.accountDropdown1 = account.getAccountDropdown1();
        this.accountLabel1 = account.getAccountLabel1();
        this.displayAcctNickname = account.getDisplayAcctNickname();
    }

    /**
     * @return the displayAccount1
     */
    public String getDisplayAccount1() {
        return displayAccount1;
    }

    /**
     * @param displayAccount1
     *            the displayAccount1 to set
     */
    public void setDisplayAccount1(String displayAccount1) {
        this.displayAccount1 = displayAccount1;
    }

    /**
     * @return the accountDropdown1
     */
    public String getAccountDropdown1() {
        return accountDropdown1;
    }

    /**
     * @param accountDropdown1
     *            the accountDropdown1 to set
     */
    public void setAccountDropdown1(String accountDropdown1) {
        this.accountDropdown1 = accountDropdown1;
    }

    /**
     * @return the accountLabel1
     */
    public String getAccountLabel1() {
        return accountLabel1;
    }

    /**
     * @param accountLabel1
     *            the accountLabel1 to set
     */
    public void setAccountLabel1(String accountLabel1) {
        this.accountLabel1 = accountLabel1;
    }

    /**
     * @return the displayAcctNickname
     */
    public String getDisplayAcctNickname() {
        return displayAcctNickname;
    }

    /**
     * @param displayAcctNickname
     *            the displayAcctNickname to set
     */
    public void setDisplayAcctNickname(String displayAcctNickname) {
        this.displayAcctNickname = displayAcctNickname;
    }

    /**
     * @return the acno
     */
    public String getAcno() {
        return acno;
    }

    /**
     * @param acno
     *            the acno to set
     */
    public void setAcno(String acno) {
        this.acno = acno;
    }

}
