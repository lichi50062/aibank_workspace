package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;

public class BiometricsBlackListEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private String phoneLabel;
    private String phoneModel;
    private String phoneVersion;

    public BiometricsBlackListEntityPk() {
    }

    public BiometricsBlackListEntityPk(String str) {
        fromString(str);
    }

    public String getPhoneLabel() {
        return phoneLabel;
    }

    public void setPhoneLabel(String phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel;
    }

    public String getPhoneVersion() {
        return phoneVersion;
    }

    public void setPhoneVersion(String phoneVersion) {
        this.phoneVersion = phoneVersion;
    }

    @Override
    public String toString() {
        return phoneLabel + "::" + phoneModel + "::" + phoneVersion;
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((phoneLabel == null) ? 0 : phoneLabel.hashCode());
        rs = rs * 37 + ((phoneModel == null) ? 0 : phoneModel.hashCode());
        rs = rs * 37 + ((phoneVersion == null) ? 0 : phoneVersion.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        BiometricsBlackListEntityPk other = (BiometricsBlackListEntityPk) obj;
        return ((phoneLabel == null && other.phoneLabel == null) || (phoneLabel != null && phoneLabel.equals(other.phoneLabel))) && ((phoneModel == null && other.phoneModel == null) || (phoneModel != null && phoneModel.equals(other.phoneModel))) && ((phoneVersion == null && other.phoneVersion == null) || (phoneVersion != null && phoneVersion.equals(other.phoneVersion)));
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            phoneLabel = null;
        else
            phoneLabel = str;
        str = toke.nextToken();
        if ("null".equals(str))
            phoneModel = null;
        else
            phoneModel = str;
        str = toke.nextToken();
        if ("null".equals(str))
            phoneVersion = null;
        else
            phoneVersion = str;
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
