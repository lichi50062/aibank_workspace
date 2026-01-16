/**
 * 
 */
package com.tfb.aibank.biz.security.gateway.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class CheckSecuirtyRulesRequest {

    @Schema(description = "使用者ID")
    private String uid;

    @Schema(description = "使用者代號")
    private String uuid;

    @Schema(description = "加密的密碼")
    private String encodedSecret;

    @Schema(description = "舊的加密的密碼")
    private String oldEncodedSecret;

    @Schema(description = "是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]")
    private Boolean isPwdWithTime;

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

    public Boolean getIsPwdWithTime() {
        return isPwdWithTime;
    }

    public void setIsPwdWithTime(Boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
    }

}
