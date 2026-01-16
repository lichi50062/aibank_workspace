package com.tfb.aibank.chl.creditcard.qu010.model;

// @formatter:off
/**
 * @(#)NCCQU010CreditCardRecommendation.java
 * 
 * <p>Description:消費分析 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NCCQU010CreditCardRecommendation {

    /** 標題 */
    private String title;

    /** 內容 */
    private String content;

    /** 引導連結文字 */
    private String linkMessage;

    /** 引導連結網址 */
    private String linkUrl;

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
     * @return the linkMessage
     */
    public String getLinkMessage() {
        return linkMessage;
    }

    /**
     * @param linkMessage
     *            the linkMessage to set
     */
    public void setLinkMessage(String linkMessage) {
        this.linkMessage = linkMessage;
    }

    /**
     * @return the linkUrl
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * @param linkUrl
     *            the linkUrl to set
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

}
