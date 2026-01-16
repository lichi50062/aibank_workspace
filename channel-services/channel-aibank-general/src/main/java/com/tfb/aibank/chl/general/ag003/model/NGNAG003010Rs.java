package com.tfb.aibank.chl.general.ag003.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NGNAG003010Rs.java
 * 
 * <p>Description:大頭貼及暱稱設定 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/19, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NGNAG003010Rs implements RsData {

    /** 頭像代碼 */
    private String defaultAvatar;
    /** 頭像代碼 */
    private String avatar;
    /** 暱稱 */
    private String nickname;

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     *            the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     *            the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the defaultAvatar
     */
    public String getDefaultAvatar() {
        return defaultAvatar;
    }

    /**
     * @param defaultAvatar
     *            the defaultAvatar to set
     */
    public void setDefaultAvatar(String defaultAvatar) {
        this.defaultAvatar = defaultAvatar;
    }

}
