package com.tfb.aibank.chl.creditcard.ag004.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCAG004020Rs.java
* 
* <p>Description:刷卡通知設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCAG004020Rs implements RsData {

    /** email */
    private String email;

    /** 是否沒有 E-mail */
    private boolean isNoEmail;

    /** 卡片清單 */
    private List<NCCAG004Repeat> cards;

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cards
     */
    public List<NCCAG004Repeat> getCards() {
        return cards;
    }

    /**
     * @param cards
     *            the cards to set
     */
    public void setCards(List<NCCAG004Repeat> cards) {
        this.cards = cards;
    }

    /**
     * @return the isNoEmail
     */
    public boolean isNoEmail() {
        return isNoEmail;
    }

    /**
     * @param isNoEmail
     *            the isNoEmail to set
     */
    public void setNoEmail(boolean isNoEmail) {
        this.isNoEmail = isNoEmail;
    }

}
