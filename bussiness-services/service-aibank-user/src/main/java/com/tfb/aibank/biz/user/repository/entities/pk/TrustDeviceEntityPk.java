package com.tfb.aibank.biz.user.repository.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.tfb.aibank.biz.component.identity.IdentityData;

public class TrustDeviceEntityPk implements Serializable {

    private static final long serialVersionUID = -8027645029004927994L;

    /** 公司鍵值 */
    private Integer companyKey;

    /** 使用者鍵值 */
    private Integer userKey;

    /** 裝置鍵值 */
    private String deviceId;

    public TrustDeviceEntityPk() {
    }

    public TrustDeviceEntityPk(IdentityData identityData, String deviceId) {
        this.companyKey = identityData.getCompanyKey();
        this.userKey = identityData.getUserKey();
        this.deviceId = deviceId;
    }

    public TrustDeviceEntityPk(String str) {
        fromString(str);
    }

    /**
     * @return the companyKey
     */
    public Integer getCompanyKey() {
        return companyKey;
    }

    /**
     * @param companyKey
     *            the companyKey to set
     */
    public void setCompanyKey(Integer companyKey) {
        this.companyKey = companyKey;
    }

    /**
     * @return the userKey
     */
    public Integer getUserKey() {
        return userKey;
    }

    /**
     * @param userKey
     *            the userKey to set
     */
    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    /**
     * @return the deviceId
     */
    public String getDeviceId() {
        return deviceId;
    }

    /**
     * @param deviceId
     *            the deviceId to set
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return deviceId + "::" + String.valueOf(companyKey) + "::" + String.valueOf(userKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyKey, deviceId, userKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TrustDeviceEntityPk other = (TrustDeviceEntityPk) obj;
        return Objects.equals(companyKey, other.companyKey) && Objects.equals(deviceId, other.deviceId) && Objects.equals(userKey, other.userKey);
    }

    private void fromString(String str) {
        Tokenizer toke = new Tokenizer(str);
        str = toke.nextToken();
        if ("null".equals(str))
            deviceId = null;
        else
            deviceId = str;
        str = toke.nextToken();
        companyKey = Integer.parseInt(str);
        userKey = Integer.parseInt(str);
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