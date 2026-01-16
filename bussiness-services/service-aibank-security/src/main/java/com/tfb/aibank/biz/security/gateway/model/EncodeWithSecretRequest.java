/**
 * 
 */
package com.tfb.aibank.biz.security.gateway.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
@Schema(description = "EncodeWithSecretRequest")
public class EncodeWithSecretRequest {

    @Schema(description = "加密格式")
    private String e2eeHsmType;

    @Schema(description = "使用者ID")
    private String uid;

    @Schema(description = "使用者代號")
    private String uuid;

    @Schema(description = "加密的密碼")
    private String encodedSecret;

    @Schema(description = "加密的密碼")
    private List<Character> numberlist;

    @Schema(description = "加密的密碼")
    private List<Character> charList;

    @Schema(description = "是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]")
    Boolean isPwdWithTime;

    /**
     * @return the e2eeHsmType
     */
    public String getE2eeHsmType() {
        return e2eeHsmType;
    }

    /**
     * @param e2eeHsmType
     *            the e2eeHsmType to set
     */
    public void setE2eeHsmType(String e2eeHsmType) {
        this.e2eeHsmType = e2eeHsmType;
    }

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
     * @return the numberlist
     */
    public List<Character> getNumberlist() {
        return numberlist;
    }

    /**
     * @param numberlist
     *            the numberlist to set
     */
    public void setNumberlist(List<Character> numberlist) {
        this.numberlist = numberlist;
    }

    /**
     * @return the charList
     */
    public List<Character> getCharList() {
        return charList;
    }

    /**
     * @param charList
     *            the charList to set
     */
    public void setCharList(List<Character> charList) {
        this.charList = charList;
    }

    /**
     * @return the isPwdWithTime
     */
    public Boolean getIsPwdWithTime() {
        return isPwdWithTime == null ? false : isPwdWithTime;
    }

    /**
     * @param isPwdWithTime
     *            the isPwdWithTime to set
     */
    public void setIsPwdWithTime(Boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
    }

}
