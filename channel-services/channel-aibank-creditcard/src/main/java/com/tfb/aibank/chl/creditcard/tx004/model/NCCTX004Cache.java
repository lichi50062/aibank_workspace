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
package com.tfb.aibank.chl.creditcard.tx004.model;

// @formatter:off
import java.util.List; /**
 * @(#)NCCTX004Cache.java
 * 
 * <p>Description:[NCCTX004Cache]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/13
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCTX004Cache {

    private List<CreditCardData> creditCardDatas;

    public List<CreditCardData> getCreditCardDatas() {
        return creditCardDatas;
    }

    public void setCreditCardDatas(List<CreditCardData> creditCardDatas) {
        this.creditCardDatas = creditCardDatas;
    }
}
