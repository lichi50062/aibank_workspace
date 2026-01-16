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
package com.tfb.aibank.chl.component.homepagecard;

// @formatter:off
/**
 * @(#)HomepageCardI18nModel.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/09, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class HomepageCardI18nModel {

    private String locale;

    private String cardDesc;

    /**
     * @return the locale
     */
    public String getLocale() {
        return locale;
    }

    /**
     * @param locale
     *            the locale to set
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * @return the cardDesc
     */
    public String getCardDesc() {
        return cardDesc;
    }

    /**
     * @param cardDesc
     *            the cardDesc to set
     */
    public void setCardDesc(String cardDesc) {
        this.cardDesc = cardDesc;
    }

}
