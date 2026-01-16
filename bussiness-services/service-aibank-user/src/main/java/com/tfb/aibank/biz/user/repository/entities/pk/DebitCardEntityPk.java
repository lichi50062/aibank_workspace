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
package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)DebitCardEntityPk.java
 * 
 * <p>Description:簽帳卡卡面 Pk</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DebitCardEntityPk implements Serializable {

    private int cardType;
    private String locale;

    public DebitCardEntityPk() {
    }

    public DebitCardEntityPk(String str) {
        fromString(str);
    }

    public int getCardType() {
        return cardType;
    }

    public void setCardType(int cardType) {
        this.cardType = cardType;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    @Override
    public String toString() {
        return String.valueOf(cardType) + "::" + locale;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + cardType;
        rs = rs * 37 + ((locale == null) ? 0 : locale.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        DebitCardEntityPk other = (DebitCardEntityPk) obj;
        return (cardType == other.cardType) && ((locale == null && other.locale == null) || (locale != null && locale.equals(other.locale)));
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        cardType = Integer.parseInt(str);
        str = toke.nextToken();
        if ("null".equals(str))
            locale = null;
        else
            locale = str;
    }

    protected static class Tokenizer {
        private final String str;
        private int last;

        public Tokenizer(String str) {
            this.str = str;
        }

        public String nextToken() {
            int next = str.indexOf("::", last);
            String part;
            if (next == -1) {
                part = str.substring(last);
                last = str.length();
            }
            else {
                part = str.substring(last, next);
                last = next + 2;
            }
            return part;
        }
    }
}
