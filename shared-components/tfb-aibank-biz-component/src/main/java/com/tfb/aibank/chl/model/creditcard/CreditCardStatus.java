/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.model.creditcard;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)CreditCardStatus.java
 * 
 * <p>Description:信用卡戶況及註記 (來源:CEW301R)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/26, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CreditCardStatus implements Serializable {

    private static final long serialVersionUID = 7489062122106207644L;

    /** 特殊戶況碼 */
    private String accountStsCode;

    /** 本行信卡戶碼 */
    private String cardholderCode;

    public String getAccountStsCode() {
        return accountStsCode;
    }

    public void setAccountStsCode(String accountStsCode) {
        this.accountStsCode = accountStsCode;
    }

    public String getCardholderCode() {
        return cardholderCode;
    }

    public void setCardholderCode(String cardholderCode) {
        this.cardholderCode = cardholderCode;
    }

}
