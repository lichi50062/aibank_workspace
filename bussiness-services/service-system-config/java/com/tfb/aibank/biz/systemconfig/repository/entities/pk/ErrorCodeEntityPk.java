package com.tfb.aibank.biz.systemconfig.repository.entities.pk;

import java.io.Serializable;

public class ErrorCodeEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private String systemId;
    private String errorCode;
    private String pageId;
    private String locale;

    public ErrorCodeEntityPk() {
    }

    public ErrorCodeEntityPk(String str) {
        fromString(str);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    @Override
    public String toString() {
        return systemId + "::" + errorCode + "::" + pageId + "::" + locale;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((errorCode == null) ? 0 : errorCode.hashCode());
        rs = rs * 37 + ((locale == null) ? 0 : locale.hashCode());
        rs = rs * 37 + ((systemId == null) ? 0 : systemId.hashCode());
        rs = rs * 37 + ((pageId == null) ? 0 : pageId.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        ErrorCodeEntityPk other = (ErrorCodeEntityPk) obj;
        return ((errorCode == null && other.errorCode == null) || (errorCode != null && errorCode.equals(other.errorCode))) && ((locale == null && other.locale == null) || (locale != null && locale.equals(other.locale))) && ((systemId == null && other.systemId == null) || (systemId != null && systemId.equals(other.systemId)) && (pageId == null && other.pageId == null) || (pageId != null && pageId.equals(other.pageId)));
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            systemId = null;
        else
            systemId = str;

        str = toke.nextToken();
        if ("null".equals(str))
            errorCode = null;
        else
            errorCode = str;

        str = toke.nextToken();
        if ("null".equals(str))
            pageId = null;
        else
            pageId = str;

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