package com.tfb.aibank.biz.systemconfig.repository.entities.pk;

import java.io.Serializable;

// @formatter:off
/**
 * @(#)SystemParamEntityPk.java
 * 
 * <p>Description:系統參數檔 主鍵</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/25, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SystemParamEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 分類 */
    private String category;
    /** 鍵值 */
    private String paramKey;

    public SystemParamEntityPk() {
    }

    public SystemParamEntityPk(String category, String paramKey) {
        this.category = category;
        this.paramKey = paramKey;
    }

    public SystemParamEntityPk(String str) {
        fromString(str);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    @Override
    public String toString() {
        return category + "::" + paramKey;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((category == null) ? 0 : category.hashCode());
        rs = rs * 37 + ((paramKey == null) ? 0 : paramKey.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        SystemParamEntityPk other = (SystemParamEntityPk) obj;
        return ((category == null && other.category == null) || (category != null && category.equals(other.category))) && ((paramKey == null && other.paramKey == null) || (paramKey != null && paramKey.equals(other.paramKey)));
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
            paramKey = null;
        else
            paramKey = str;
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