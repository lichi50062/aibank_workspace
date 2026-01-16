/**
 *
 */
package com.tfb.aibank.chl.general.resource.dto;

/**
 // @formatter:off
 * @(#)LineSubscribeCheckModel.java
 *
 * <p>Description:訂閱通知查詢</p>
 *
 * <p>Modify History:</p>
 * <ol>v1.0, 2023/11/3, Evan Wang
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class LineSubscribeCheckModel {

    /**
     * 訂閱通知
     */
    private String pushCode;

    /**
     * 訂閱名稱
     */
    private String name;

    /**
     * 是否允許異動
     */
    private String isAllow;

    /**
     * 是否有訂閱
     */
    private String isSub;

    /**
     * @return the pushCode
     */
    public String getPushCode() {
        return pushCode;
    }

    /**
     * @param pushCode
     *            the pushCode to set
     */
    public void setPushCode(String pushCode) {
        this.pushCode = pushCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the isAllow
     */
    public String getIsAllow() {
        return isAllow;
    }

    /**
     * @param isAllow
     *            the isAllow to set
     */
    public void setIsAllow(String isAllow) {
        this.isAllow = isAllow;
    }

    /**
     * @return the isSub
     */
    public String getIsSub() {
        return isSub;
    }

    /**
     * @param isSub
     *            the isSub to set
     */
    public void setIsSub(String isSub) {
        this.isSub = isSub;
    }

}