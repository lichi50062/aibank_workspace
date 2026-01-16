package com.tfb.aibank.chl.creditcard.qu016.model;

import java.util.Locale;

public class NCCQU016Input {

    // 共用
    Locale locale;



    String key;
    String custId;


    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }
}
