/**
 * 
 */
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

/**
 * @author john
 *
 */
// EncodeWithSecretRequest
public class EncodeWithSecretRequest {

    // 加密格式
    private String e2eeHsmType;

    // 使用者ID
    private String uid;

    // 使用者代號
    private String uuid;

    // 加密的密碼
    private String encodedSecret;

    // 加密的密碼
    private List<Character> numberlist;

    // 加密的密碼
    private List<Character> charList;

    // 是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]
    private Boolean isPwdWithTime;

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
        return isPwdWithTime;
    }

    /**
     * @param isPwdWithTime
     *            the isPwdWithTime to set
     */
    public void setIsPwdWithTime(Boolean isPwdWithTime) {
        this.isPwdWithTime = isPwdWithTime;
    }

}
