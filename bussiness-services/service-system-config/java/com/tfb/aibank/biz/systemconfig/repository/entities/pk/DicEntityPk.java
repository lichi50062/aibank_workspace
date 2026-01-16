package com.tfb.aibank.biz.systemconfig.repository.entities.pk;

import java.io.Serializable;

public class DicEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private String category;
    private String dicKey;

    public DicEntityPk() {
    }

    public DicEntityPk(String str) {
        fromString(str);
    }

    @Override
    public String toString() {
        return category + "::" + dicKey;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((category == null) ? 0 : category.hashCode());
        rs = rs * 37 + ((dicKey == null) ? 0 : dicKey.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        DicEntityPk other = (DicEntityPk) obj;
        return ((category == null && other.category == null) || (category != null && category.equals(other.category))) && ((dicKey == null && other.dicKey == null) || (dicKey != null && dicKey.equals(other.dicKey)));
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
            dicKey = null;
        else
            dicKey = str;

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