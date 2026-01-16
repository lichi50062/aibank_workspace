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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;

import com.tfb.aibank.chl.general.resource.dto.BankOperationOffer;
import com.tfb.aibank.chl.general.resource.dto.FEP512166Res;
import com.tfb.aibank.chl.general.resource.dto.ServiceAdvisor;
import com.tfb.aibank.chl.model.account.TransOutAccount;

// @formatter:off
/**
 * @(#)FinancialMgmMemberLevel.java
 * 
 * <p>Description:財管會員資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class FinMgmMemberData {

    /**
     * 個人等級註記<pre>
     * (A) 	{個人等級註記}= K，視為穩富理財會員。
     * (B) 	{個人等級註記}= T，視為智富理財會員。
     * (C) 	{個人等級註記}= H，視為恆富理財會員。
     * (D) 	{個人等級註記}= 非上述等級或無資料，視為一般存戶。
     * </pre>
     */
    private String personalFlag = "";
    /**
     * 家庭會員註記<pre>
     * (E) 	{家庭會員註記}= K，視為穩富理財家庭會員。
     * (F) 	{家庭會員註記}= T，視為智富理財家庭會員。
     * (G) 	{家庭會員註記}= H，視為恆富理財家庭會員。
     * (H) 	{家庭會員註記}= 非上述等級或無資料，視為非家庭會員。
     * </pre>
     */
    private String familyFlag = "";

    /**
     * 「是」目前等級
     */
    private boolean currentLevel = true;

    /**
     * 是否為{薪轉會員}
     */
    private boolean salaryAcctMember;

    /**
     * 台幣轉出帳號
     */
    private List<TransOutAccount> transOutAcctsTW;
    /**
     * 外幣轉出帳號
     */
    private List<TransOutAccount> transOutAcctsFR;

    /**
     * 帳號優惠資訊
     */
    private List<AccountFavor> accountFavors = new ArrayList<>();
    /**
     * 跨行(提款)優惠資訊
     */
    private FEP512166Res crossBankTxFavor;
    /**
     * 跨行(轉帳)優惠資訊
     */
    private FEP512166Res crossBankTxTrFavor;
    /**
     * 每月銀行作業優惠-[自選國內作業免手續費]
     */
    private BankOperationOffer bankOperationOffer;

    /**
     * 每月銀行作業優惠-[自選外幣/國外作業免手續費]
     */
    private BankOperationOffer bankOperationOfferOversea;

    private boolean haveDigitalAccount;

    /**
     * 服務專員資料
     */
    private ServiceAdvisor serviceAdvisor;

    public void setOutAccountNos() {
        if (null != transOutAcctsTW) {
            setOutAccountNos(transOutAcctsTW);
        }

        if (null != transOutAcctsFR) {
            setOutAccountNos(transOutAcctsFR);
        }
    }

    /**
     * 依帳號產生帳號優惠資訊物件
     */
    public void setOutAccountNos(List<TransOutAccount> outAccts) {
        if (CollectionUtils.isNotEmpty(outAccts)) {
            for (var acct : outAccts) {
                if (acct.isDigital()) {
                    //任一帳號是數位帳號，此物件資料即標示
                    this.haveDigitalAccount = true;
                }
                List<String> currentAcctNos = this.accountFavors.stream().map(AccountFavor::getAccountNo).collect(Collectors.toList());
                if (!currentAcctNos.contains(acct.getAcno())) {
                    this.accountFavors.add(new AccountFavor(acct));
                }
            }
        }
    }

    /**
     * 標記新轉帳號
     **/
    public void markSalaryAccountNo(String acno) {
        for (var favo : accountFavors) {
            if (favo.getAccountNo().equals(acno)) {
                favo.setSalaryAccount(true);
                break;
            }
        }
    }

    public String getFamilyFlag() {
        return familyFlag;
    }

    public void setFamilyFlag(String familyFlag) {
        this.familyFlag = familyFlag;
    }

    public String getPersonalFlag() {
        return personalFlag;
    }

    public void setPersonalFlag(String personalFlag) {
        this.personalFlag = personalFlag;
    }

    public List<TransOutAccount> getTransOutAcctsFR() {
        return transOutAcctsFR;
    }

    public void setTransOutAcctsFR(List<TransOutAccount> transOutAcctsFR) {
        this.transOutAcctsFR = transOutAcctsFR;
    }

    public List<TransOutAccount> getTransOutAcctsTW() {
        return transOutAcctsTW;
    }

    public void setTransOutAcctsTW(List<TransOutAccount> transOutAcctsTW) {
        this.transOutAcctsTW = transOutAcctsTW;
    }

    public List<AccountFavor> getAccountFavors() {
        return accountFavors;
    }

    public void setAccountFavors(List<AccountFavor> accountFavors) {
        this.accountFavors = accountFavors;
    }

    public boolean isSalaryAcctMember() {
        return salaryAcctMember;
    }

    public void setSalaryAcctMember(boolean salaryAcctMember) {
        this.salaryAcctMember = salaryAcctMember;
    }

    public FEP512166Res getCrossBankTxFavor() {
        return crossBankTxFavor;
    }

    public void setCrossBankTxFavor(FEP512166Res crossBankTxFavor) {
        this.crossBankTxFavor = crossBankTxFavor;
    }

    public BankOperationOffer getBankOperationOffer() {
        return bankOperationOffer;
    }

    public void setBankOperationOffer(BankOperationOffer bankOperationOffer) {
        this.bankOperationOffer = bankOperationOffer;
    }

    public ServiceAdvisor getServiceAdvisor() {
        return serviceAdvisor;
    }

    public void setServiceAdvisor(ServiceAdvisor serviceAdvisor) {
        this.serviceAdvisor = serviceAdvisor;
    }

    public boolean isHaveDigitalAccount() {
        return haveDigitalAccount;
    }

    public void setHaveDigitalAccount(boolean haveDigitalAccount) {
        this.haveDigitalAccount = haveDigitalAccount;
    }

    public boolean isCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(boolean currentLevel) {
        this.currentLevel = currentLevel;
    }

    public BankOperationOffer getBankOperationOfferOversea() {
        return bankOperationOfferOversea;
    }

    public void setBankOperationOfferOversea(BankOperationOffer bankOperationOfferOversea) {
        this.bankOperationOfferOversea = bankOperationOfferOversea;
    }

    public FEP512166Res getCrossBankTxTrFavor() {
        return crossBankTxTrFavor;
    }

    public void setCrossBankTxTrFavor(FEP512166Res crossBankTxTrFavor) {
        this.crossBankTxTrFavor = crossBankTxTrFavor;
    }
}
