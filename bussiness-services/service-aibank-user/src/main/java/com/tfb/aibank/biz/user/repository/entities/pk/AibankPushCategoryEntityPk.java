package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;

public class AibankPushCategoryEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer pushCategoryKey;

    private String locale;

    public AibankPushCategoryEntityPk() {
        // default constructor
    }

    public AibankPushCategoryEntityPk(Integer pushCategoryKey, String locale) {
        this.pushCategoryKey = pushCategoryKey;
        this.locale = locale;
    }

    public AibankPushCategoryEntityPk(String str) {
        fromString(str);
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public int getPushCategoryKey() {
        return pushCategoryKey;
    }

    public void setPushCategoryKey(int pushCategoryKey) {
        this.pushCategoryKey = pushCategoryKey;
    }

    @Override
    public String toString() {
        return locale + "::" + String.valueOf(pushCategoryKey);
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((locale == null) ? 0 : locale.hashCode());
        rs = rs * 37 + pushCategoryKey;
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        AibankPushCategoryEntityPk other = (AibankPushCategoryEntityPk) obj;
        return ((locale == null && other.locale == null) || (locale != null && locale.equals(other.locale))) && (pushCategoryKey == other.pushCategoryKey);
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            locale = null;
        else
            locale = str;
        str = toke.nextToken();
        pushCategoryKey = Integer.parseInt(str);
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