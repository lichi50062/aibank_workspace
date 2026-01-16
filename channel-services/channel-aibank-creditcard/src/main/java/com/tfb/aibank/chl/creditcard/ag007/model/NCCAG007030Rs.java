package com.tfb.aibank.chl.creditcard.ag007.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCAG007030Rs.java
 * 
 * <p>Description:一鍵綁定行動支付 030 取得持卡人英文姓名</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG007030Rs implements RsData {

    /** 英文姓名 */
    private String engName;

    /** 卡片名稱 */
    private String cardName;

    /** 卡片效期 */
    private String expiryDate;

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

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
     * @return the expiryDate
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate
     *            the expiryDate to set
     */
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}
