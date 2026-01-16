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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.Calendar;
import java.util.List;

// @formatter:off
/**
 * @(#)CEW302RRes.java
 * 
 * <p>Description:CEW302R 信用卡卡片總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/01, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW302RRes {

    /**
     * 信用卡電子信箱X(60)
     * */
    private String emailAddress;

    /**
     * 電子帳單申請註記X(1)
     * */
    private String applyEmail;

    /**
     * 實體帳單申請註記X(1)
     * */
    private String applyBill;

    /**
     * 生日
     * */
    private Calendar birthd;

    /**
     * 持卡人ID X(11)
     * */
    private String cardHolderId;

    /**
     * 卡片清單
     * */
    private List<CEW302RResRep> cards;

    /**
     * Gets card holder id.
     *
     * @return the card holder id
     */
    public String getCardHolderId() {
        return cardHolderId;
    }

    /**
     * Sets card holder id.
     *
     * @param cardHolderId
     *            the card holder id
     */
    public void setCardHolderId(String cardHolderId) {
        this.cardHolderId = cardHolderId;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets email address.
     *
     * @param emailAddress
     *            the email address
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets apply email.
     *
     * @return the apply email
     */
    public String getApplyEmail() {
        return applyEmail;
    }

    /**
     * Sets apply email.
     *
     * @param applyEmail
     *            the apply email
     */
    public void setApplyEmail(String applyEmail) {
        this.applyEmail = applyEmail;
    }

    /**
     * Gets apply bill.
     *
     * @return the apply bill
     */
    public String getApplyBill() {
        return applyBill;
    }

    /**
     * Sets apply bill.
     *
     * @param applyBill
     *            the apply bill
     */
    public void setApplyBill(String applyBill) {
        this.applyBill = applyBill;
    }

    /**
     * @return the cards
     */
    public List<CEW302RResRep> getCards() {
        return cards;
    }

    /**
     * @param cards
     *            the cards to set
     */
    public void setCards(List<CEW302RResRep> cards) {
        this.cards = cards;
    }

    /**
     * @return the birthd
     */
    public Calendar getBirthd() {
        return birthd;
    }

    /**
     * @param birthd
     *            the birthd to set
     */
    public void setBirthd(Calendar birthd) {
        this.birthd = birthd;
    }

}
