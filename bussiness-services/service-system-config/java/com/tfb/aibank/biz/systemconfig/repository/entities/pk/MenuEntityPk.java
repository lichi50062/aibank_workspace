package com.tfb.aibank.biz.systemconfig.repository.entities.pk;

import java.io.Serializable;

public class MenuEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private String category;
    private String menuId;

    public MenuEntityPk() {
    }

    public MenuEntityPk(String str) {
        fromString(str);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return category + "::" + menuId;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((category == null) ? 0 : category.hashCode());
        rs = rs * 37 + ((menuId == null) ? 0 : menuId.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        MenuEntityPk other = (MenuEntityPk) obj;
        return ((category == null && other.category == null) || (category != null && category.equals(other.category))) && ((menuId == null && other.menuId == null) || (menuId != null && menuId.equals(other.menuId)));
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            category = null;
        else
            category = str;
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