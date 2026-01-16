/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.chl.component.datacenter.model;

// @formatter:off
/**
 * @(#)OfferAction.java
 * 
 * <p>Description:情境版位資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/13, alexlee	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OfferAction {
    /** 情境之識別碼 */
    private String offerId;
    /** 版型 */
    private String templateId;
    /** 標題 */
    private String title;
    /** 文字 */
    private String content;
    /** 導引操作 */
    private String link;
    /** 導引操作名稱 */
    private String linkName;
    /** 圖片 */
    private String picture;

    /**
     * @return the templateId
     */
    public String getTemplateId() {
        return templateId;
    }

    /**
     * @param templateId
     *            the templateId to set
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link
     *            the link to set
     */
    public void setLink(String link) {
        this.link = link;
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
     * @return the picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * @param picture
     *            the picture to set
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    /**
     * @return the offerId
     */
    public String getOfferId() {
        return offerId;
    }

    /**
     * @param offerId
     *            the offerId to set
     */
    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
}
