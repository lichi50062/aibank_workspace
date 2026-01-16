package com.tfb.aibank.biz.systemconfig.repository.entities.pk;

import java.io.Serializable;

public class MenuNameEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private String locale;
    private String menuId;

    public MenuNameEntityPk() {
    }

    public MenuNameEntityPk(String str) {
        fromString(str);
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return locale + "::" + menuId;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((locale == null) ? 0 : locale.hashCode());
        rs = rs * 37 + ((menuId == null) ? 0 : menuId.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        MenuNameEntityPk other = (MenuNameEntityPk) obj;
        return ((locale == null && other.locale == null) || (locale != null && locale.equals(other.locale))) && ((menuId == null && other.menuId == null) || (menuId != null && menuId.equals(other.menuId)));
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            locale = null;
        else
            locale = str;
        str = toke.nextToken();
        if ("null".equals(str))
            menuId = null;
        else
            menuId = str;
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