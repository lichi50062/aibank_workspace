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
package com.tfb.aibank.chl.system.service;

//@formatter:off
/**
* @(#)CardDescModel.java
* 
* <p>Description: 廣告版位卡片model</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/14, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CardDescModel {

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

    /** picUrl */
    private String picUrl;

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
