package com.tfb.aibank.chl.creditcard.ag007.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCAG007010Rq.java
 * 
 * <p>Description:一鍵綁定行動支付 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/08, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCAG007010Rq implements RqData {

    /** 綁定類別，LinePay or ApplePay */
    private String addType;

    /** 信用卡鍵值 */
    private String cardKey;

    public String getAddType() {
        return addType;
    }

    public void setAddType(String addType) {
        this.addType = addType;
    }

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

}
