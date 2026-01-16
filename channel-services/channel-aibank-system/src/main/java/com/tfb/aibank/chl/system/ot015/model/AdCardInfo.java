/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot015.model;

//@formatter:off
/**
* @(#)CardInfo.java
* 
* <p>Description: 廣告版位 CardInfo</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AdCardInfo {

    /** TAG **/
    private String tag;

    /** TITLE **/
    private String title;

    /** CONTENT */
    private String content;

    /** CONTENT2 */
    private String content2;

    /** LINK */
    private String link;

    /** CARD_TARGET */
    private String cardTarget;

    /** CARD_TARGET2 */
    private String cardTarget2;

    /** ICON */
    private String icon;

    /** picUrl */
    private String picUrl;

    /** SORT */
    private int sort;

    /** CARD_TEMPLATE */
    private String cardTemplate;

    /**
     * for 廣告版位樣式, 如果true表示整張圖片, false表示套版
     */
    private boolean wholePicture;

    /** linkName */
    private String linkName;

    /** linkName1 */
    private String linkName1;

    /** linkName2 */
    private String linkName2;

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     *            the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the content2
     */
    public String getContent2() {
        return content2;
    }

    /**
     * @param content2
     *            the content2 to set
     */
    public void setContent2(String content2) {
        this.content2 = content2;
    }

    /**
     * @return {@link #link}
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link
     *            {@link #link}
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the cardTarget
     */
    public String getCardTarget() {
        return cardTarget;
    }

    /**
     * @param cardTarget
     *            the cardTarget to set
     */
    public void setCardTarget(String cardTarget) {
        this.cardTarget = cardTarget;
    }

    /**
     * @return the cardTarget2
     */
    public String getCardTarget2() {
        return cardTarget2;
    }

    /**
     * @param cardTarget2
     *            the cardTarget2 to set
     */
    public void setCardTarget2(String cardTarget2) {
        this.cardTarget2 = cardTarget2;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the picUrl
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * @param picUrl
     *            the picUrl to set
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * @return the sort
     */
    public int getSort() {
        return sort;
    }

    /**
     * @param sort
     *            the sort to set
     */
    public void setSort(int sort) {
        this.sort = sort;
    }

    /**
     * @return the cardTemplate
     */
    public String getCardTemplate() {
        return cardTemplate;
    }

    /**
     * @param cardTemplate
     *            the cardTemplate to set
     */
    public void setCardTemplate(String cardTemplate) {
        this.cardTemplate = cardTemplate;
    }

    /**
     * @return the wholePicture
     */
    public boolean isWholePicture() {
        return wholePicture;
    }

    /**
     * @param wholePicture
     *            the wholePicture to set
     */
    public void setWholePicture(boolean wholePicture) {
        this.wholePicture = wholePicture;
    }

    /**
     * @return the linkName
     */
    public String getLinkName() {
        return linkName;
    }

    /**
     * @param linkName
     *            the linkName to set
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    /**
     * @return the linkName1
     */
    public String getLinkName1() {
        return linkName1;
    }

    /**
     * @param linkName1
     *            the linkName1 to set
     */
    public void setLinkName1(String linkName1) {
        this.linkName1 = linkName1;
    }

    /**
     * @return the linkName2
     */
    public String getLinkName2() {
        return linkName2;
    }

    /**
     * @param linkName2
     *            the linkName2 to set
     */
    public void setLinkName2(String linkName2) {
        this.linkName2 = linkName2;
    }

}
