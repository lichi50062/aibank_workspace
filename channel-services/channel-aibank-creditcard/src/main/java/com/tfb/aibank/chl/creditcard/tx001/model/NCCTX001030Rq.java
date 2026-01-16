package com.tfb.aibank.chl.creditcard.tx001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCTX001030Rq.java
*
* <p>Description:預借現金 確認頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001030Rq implements RqData {

    /* 信用卡 Key */
    private String cardKey;

    /* 信用卡 cvv */
    private String cvv;

    /* 入帳帳戶型態 */
    private boolean accountType;

    /** 本行帳戶 Key */
    private String accountKey;

    /** 他行 Bank ID */
    private String bankId;

    /** 他行 分行 ID */
    private String branchId;

    /** 他行 帳戶 */
    private String accountNo;

    /**
     * @return the cardKey
     */
    public String getCardKey() {
        return cardKey;
    }

    /**
     * @param cardKey
     *            the cardKey to set
     */
    public void setCardKey(String cardKey) {
        this.cardKey = cardKey;
    }

    /**
     * @return the cvv
     */
    public String getCvv() {
        return cvv;
    }

    /**
     * @param cvv
     *            the cvv to set
     */
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    /**
     * @return the accountType
     */
    public boolean isAccountType() {
        return accountType;
    }

    /**
     * @param accountType
     *            the accountType to set
     */
    public void setAccountType(boolean accountType) {
        this.accountType = accountType;
    }

    /**
     * @return the accountKey
     */
    public String getAccountKey() {
        return accountKey;
    }

    /**
     * @param accountKey
     *            the accountKey to set
     */
    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }

    /**
     * @return the bankId
     */
    public String getBankId() {
        return bankId;
    }

    /**
     * @param bankId
     *            the bankId to set
     */
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId
     *            the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo
     *            the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

}
