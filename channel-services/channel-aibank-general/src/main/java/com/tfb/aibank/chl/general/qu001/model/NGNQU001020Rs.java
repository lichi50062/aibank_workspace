package com.tfb.aibank.chl.general.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

// @formatter:off
/**
 * @(#)NGNQU001020Rs.java
 *
 * <p>Description: NGNQU001020Rs</p>
 *
 * <p>Modify History:</p>
 * <ol>v1, 2023/05/22, MartyPan
 *  <li>[NGNQU001020Rs]</li>
 * </ol>
 */
//@formatter:on
@Component
public class NGNQU001020Rs implements RsData {

    /**
     * 存款資料
     */
    private CardDataDeposit deposit;
    /**
     * 信用卡資料
     */
    private CardDataCreditCard creditCard;

    /**
     * 投資卡片資料
     */
    private CardDataInvestment investment;

    /**
     * 貸款卡片資料
     */
    private CardDataLoan loan;
    /**
     * 保險卡片資料
     */
    private CardDataInsurance insurance;

    /**
     * 證券卡片資料
     */
    private CardDataStock stock;

    private HomepageCard homepageCard;

    public CardDataDeposit getDeposit() {
        return deposit;
    }

    public void setDeposit(CardDataDeposit deposit) {
        this.deposit = deposit;
    }

    public CardDataCreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CardDataCreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public CardDataInvestment getInvestment() {
        return investment;
    }

    public void setInvestment(CardDataInvestment investment) {
        this.investment = investment;
    }

    public CardDataLoan getLoan() {
        return loan;
    }

    public void setLoan(CardDataLoan loan) {
        this.loan = loan;
    }

    public CardDataInsurance getInsurance() {
        return insurance;
    }

    public void setInsurance(CardDataInsurance insurance) {
        this.insurance = insurance;
    }

    public CardDataStock getStock() {
        return stock;
    }

    public void setStock(CardDataStock stock) {
        this.stock = stock;
    }

    public HomepageCard getHomepageCard() {
        return homepageCard;
    }

    public void setHomepageCard(HomepageCard homepageCard) {
        this.homepageCard = homepageCard;
    }
}
