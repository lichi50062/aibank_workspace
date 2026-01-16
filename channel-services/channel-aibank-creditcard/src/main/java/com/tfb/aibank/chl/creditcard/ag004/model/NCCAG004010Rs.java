package com.tfb.aibank.chl.creditcard.ag004.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCAG004010Rs.java
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
public class NCCAG004010Rs implements RsData {

    /** 找不到卡片 */
    private boolean isCardNotFound;

    /** 是否沒有 E-mail */
    private boolean isNoEmail;

    /** email */
    private String email;

    /** 卡片清單 */
    private NCCAG004Repeat card;

    /**
     * @return the isCardNotFound
     */
    public boolean isCardNotFound() {
        return isCardNotFound;
    }

    /**
     * @param isCardNotFound
     *            the isCardNotFound to set
     */
    public void setCardNotFound(boolean isCardNotFound) {
        this.isCardNotFound = isCardNotFound;
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
     * @return the card
     */
    public NCCAG004Repeat getCard() {
        return card;
    }

    /**
     * @param card
     *            the card to set
     */
    public void setCard(NCCAG004Repeat card) {
        this.card = card;
    }

}
