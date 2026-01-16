package com.tfb.aibank.chl.creditcard.ag008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG008011Rq.java
 * 
 * <p>Description:信用卡開卡 開卡 action Rq</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/22, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on

@Component
public class NCCAG008011Rq implements RqData {

    /**
     * 信用卡卡號
     */
    private String cardNo;
    /**
     * 信用卡背面簽名條後3碼
     */
    private String cardCvv2;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 信用卡有效期限
     */
    private String cardNedy;

    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return the cardCvv2
     */
    public String getCardCvv2() {
        return cardCvv2;
    }

    /**
     * @param cardCvv2
     *            the cardCvv2 to set
     */
    public void setCardCvv2(String cardCvv2) {
        this.cardCvv2 = cardCvv2;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the cardNedy
     */
    public String getCardNedy() {
        return cardNedy;
    }

    /**
     * @param cardNedy
     *            the cardNedy to set
     */
    public void setCardNedy(String cardNedy) {
        this.cardNedy = cardNedy;
    }

}
