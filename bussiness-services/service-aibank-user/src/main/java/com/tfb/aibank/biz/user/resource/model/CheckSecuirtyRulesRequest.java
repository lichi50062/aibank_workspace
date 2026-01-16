package com.tfb.aibank.biz.user.resource.model;

import io.swagger.v3.oas.annotations.media.Schema;

// @formatter:off
/**
 * @(#)CheckSecuirtyRulesRequest.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CheckSecuirtyRulesRequest {

    @Schema(description = "使用者ID")
    private String uid;

    @Schema(description = "使用者代號")
    private String uuid;

    @Schema(description = "加密的密碼")
    private String encodedSecret;

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
     * 
     * @return
     */
    public Boolean getIsPwdWithTime() {
        return isPwdWithTime;
    }
    /**
     * 
     * @param isPwdWithTime
     */
    public void setIsPwdWithTime(Boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
    }

}
