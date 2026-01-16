package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;

public class OnboardingTransferLogEntityPk implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer companyKey;
    private String deviceUuid;
    private Integer userKey;

    public OnboardingTransferLogEntityPk() {
    }

    public OnboardingTransferLogEntityPk(String str) {
        fromString(str);
    }

    public Integer getCompanyKey() {
        return companyKey;
    }

    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    public String getDeviceUuid() {
        return deviceUuid;
    }

    public void setDeviceUuid(String deviceUuid) {
        this.deviceUuid = deviceUuid;
    }

    public Integer getUserKey() {
        return userKey;
    }

    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return String.valueOf(companyKey) + "::" + deviceUuid + "::" + String.valueOf(userKey);
    }

    @Override
    public int hashCode() {
        int rs = 17;
        rs = rs * 37 + ((companyKey == null) ? 0 : companyKey.hashCode());
        rs = rs * 37 + ((deviceUuid == null) ? 0 : deviceUuid.hashCode());
        rs = rs * 37 + ((userKey == null) ? 0 : userKey.hashCode());
        return rs;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;

        OnboardingTransferLogEntityPk other = (OnboardingTransferLogEntityPk) obj;
        return ((companyKey == null && other.companyKey == null) || (companyKey != null && companyKey.equals(other.companyKey))) && ((deviceUuid == null && other.deviceUuid == null) || (deviceUuid != null && deviceUuid.equals(other.deviceUuid))) && ((userKey == null && other.userKey == null) || (userKey != null && userKey.equals(other.userKey)));
    }

    @SuppressWarnings("removal")
    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            companyKey = null;
        else
            companyKey = new Integer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            deviceUuid = null;
        else
            deviceUuid = str;
        str = toke.nextToken();
        if ("null".equals(str))
            userKey = null;
        else
            userKey = new Integer(str);
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