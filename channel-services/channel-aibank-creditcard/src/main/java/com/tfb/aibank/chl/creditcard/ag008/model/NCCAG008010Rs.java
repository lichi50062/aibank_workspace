package com.tfb.aibank.chl.creditcard.ag008.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.model.creditcard.CreditCard;

//@formatter:off
/**
* @(#)NCCAG008010Rs.java
* 
* <p>Description:信用卡開卡 首頁 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*
*/
//@formatter:on
@Component
public class NCCAG008010Rs implements RsData {

    /** 信用卡名稱 */
    private String cardName;

    /** 信用卡傳入卡號 */
    private String cardNoInput;

    /**
     * 可開卡之正卡資料
     */
    private List<CreditCard> activePrimaryCards;
    /**
     * 可開卡之附卡資料
     */
    private List<CreditCard> activeSupplementaryCards;

    /**
     * @return the cardName
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * @param cardName
     *            the cardName to set
     */
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    /**
     * @return the cardNoInput
     */
    public String getCardNoInput() {
        return cardNoInput;
    }

    /**
     * @param cardNoInput
     *            the cardNoInput to set
     */
    public void setCardNoInput(String cardNoInput) {
        this.cardNoInput = cardNoInput;
    }

    /**
     * @return the activePrimaryCards
     */
    public List<CreditCard> getActivePrimaryCards() {
        return activePrimaryCards;
    }

    /**
     * @param activePrimaryCards
     *            the activePrimaryCards to set
     */
    public void setActivePrimaryCards(List<CreditCard> activePrimaryCards) {
        this.activePrimaryCards = activePrimaryCards;
    }

    /**
     * @return the activeSupplementaryCards
     */
    public List<CreditCard> getActiveSupplementaryCards() {
        return activeSupplementaryCards;
    }

    /**
     * @param activeSupplementaryCards
     *            the activeSupplementaryCards to set
     */
    public void setActiveSupplementaryCards(List<CreditCard> activeSupplementaryCards) {
        this.activeSupplementaryCards = activeSupplementaryCards;
    }

}
