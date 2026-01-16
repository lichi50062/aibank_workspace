package com.tfb.aibank.chl.general.resource.dto;

/**
 * @author john
 *
 */
public class CheckSecuirtyRulesRequest {

    // 使用者ID
    private String uid;

    // 使用者代號
    private String uuid;

    // 加密的密碼
    private String encodedSecret;

    // 舊的加密的密碼
    private String oldEncodedSecret;

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid
     *            the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the encodedSecret
     */
    public String getEncodedSecret() {
        return encodedSecret;
    }

    /**
     * @param encodedSecret
     *            the encodedSecret to set
     */
    public void setEncodedSecret(String encodedSecret) {
        this.encodedSecret = encodedSecret;
    }

    /**
     * @return the oldEncodedSecret
     */
    public String getOldEncodedSecret() {
        return oldEncodedSecret;
    }

    /**
     * @param oldEncodedSecret
     *            the oldEncodedSecret to set
     */
    public void setOldEncodedSecret(String oldEncodedSecret) {
        this.oldEncodedSecret = oldEncodedSecret;
    }

}
