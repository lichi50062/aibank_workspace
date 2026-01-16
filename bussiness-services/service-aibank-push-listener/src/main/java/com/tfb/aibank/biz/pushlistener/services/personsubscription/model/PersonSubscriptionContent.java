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
package com.tfb.aibank.biz.pushlistener.services.personsubscription.model;

// @formatter:off
/**
 * @(#)PersonSubscription.java
 * 
 * <p>Description: OFFER_CONTENT_DETAIL.CONTENT 欄位 Bean</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/12/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PersonSubscriptionContent {
    /** 推播內文 */
    private String pushMessage;

    /** 推播訊息連結與參數 */
    private String url;

    /** 推播型別 */
    private String type;

    /** 通知標題 */
    private String title;

    /** 通知內文 */
    private String content;

    /** 訊息連結 */
    private String link;

    /** 訊息連結標題 */
    private String linkName;

    /** 訊息參數 */
    private Object param;

    /**
     * @return the pushMessage
     */
    public String getPushMessage() {
        return pushMessage;
    }

    /**
     * @param pushMessage
     *            the pushMessage to set
     */
    public void setPushMessage(String pushMessage) {
        this.pushMessage = pushMessage;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the param
     */
    public Object getParam() {
        return param;
    }

    /**
     * @param param
     *            the param to set
     */
    public void setParam(Object param) {
        this.param = param;
    }

}