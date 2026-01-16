package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;

public class GeoIPRangeDataEntityPk implements Serializable {

    private static final long serialVersionUID = 4408749441291607734L;

    private Long endIp;
    private Long startIp;

    public GeoIPRangeDataEntityPk() {
    }

    public GeoIPRangeDataEntityPk(String str) {
        fromString(str);
    }

    public Long getEndIp() {
        return endIp;
    }

    public void setEndIp(Long endIp) {
        this.endIp = endIp;
    }

    public Long getStartIp() {
        return startIp;
    }

    public void setStartIp(Long startIp) {
        this.startIp = startIp;
    }

    @Override
    public String toString() {
        return String.valueOf(endIp) + "::" + String.valueOf(startIp);
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((endIp == null) ? 0 : endIp.hashCode());
        rs = rs * 37 + ((startIp == null) ? 0 : startIp.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        GeoIPRangeDataEntityPk other = (GeoIPRangeDataEntityPk) obj;
        return ((endIp == null && other.endIp == null) || (endIp != null && endIp.equals(other.endIp))) && ((startIp == null && other.startIp == null) || (startIp != null && startIp.equals(other.startIp)));
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            endIp = null;
        else
            endIp = Long.parseLong(str);
        str = toke.nextToken();
        if ("null".equals(str))
            startIp = null;
        else
            startIp = Long.parseLong(str);
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