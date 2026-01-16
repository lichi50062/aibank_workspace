package com.tfb.aibank.chl.general.qu001.model;

import java.util.Date;

// @formatter:off
/**
 * @(#)CustomizedNotificationRecordEntity.java
 * 
 * <p>Description:系統通知訊息</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/21, Alex PY Li  
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class SystemNotificationRecord {
    /** 前端使用識別碼 */
    private String itemNo;

    /** 業務別 */
    private String type;

    /** 標題訊息 */
    private String titleMessage;

    /** 重要訊息 */
    private String message;

    /** 訊息連結標題 */
    private String messageLinkTitle;

    /** 訊息連結 */
    private String messageLink;

    /** 建立時間 */
    private Date createTime;

    /**
     * @return the itemNo
     */
    public String getItemNo() {
        return itemNo;
    }

    /**
     * @param itemNo
     *            the itemNo to set
     */
    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
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
     * @return the titleMessage
     */
    public String getTitleMessage() {
        return titleMessage;
    }

    /**
     * @param titleMessage
     *            the titleMessage to set
     */
    public void setTitleMessage(String titleMessage) {
        this.titleMessage = titleMessage;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the messageLinkTitle
     */
    public String getMessageLinkTitle() {
        return messageLinkTitle;
    }

    /**
     * @param messageLinkTitle
     *            the messageLinkTitle to set
     */
    public void setMessageLinkTitle(String messageLinkTitle) {
        this.messageLinkTitle = messageLinkTitle;
    }

    /**
     * @return the messageLink
     */
    public String getMessageLink() {
        return messageLink;
    }

    /**
     * @param messageLink
     *            the messageLink to set
     */
    public void setMessageLink(String messageLink) {
        this.messageLink = messageLink;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
