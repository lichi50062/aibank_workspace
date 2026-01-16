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
package com.tfb.aibank.chl.component.notification.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tfb.aibank.chl.component.notification.EmailAttachment;

// @formatter:off
/**
 * @(#)EmailNotify.java
 * 
 * <p>Description:Email通知資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EmailNotify extends Notify {

    /** 寄件人Email */
    private String from;

    /** 寄件人名稱 */
    private String senderName;

    /** 收件人Email */
    private List<String> to;

    /** 郵件主旨 */
    private String subject;

    /** 模板檔案名稱 */
    private String templateName;

    /** 模板使用的參數 */
    private Map<String, Object> templateParams = new HashMap<String, Object>();

    /** 郵件內容 */
    private String message;

    /** 附件的完整檔案路徑 */
    private List<EmailAttachment> attachment;

    public void addAttachment(String filePath) {
        if (this.attachment == null) {
            this.attachment = new ArrayList<EmailAttachment>();
        }
        this.attachment.add(new EmailAttachment(filePath));
    }

    public void addAttachment(EmailAttachment attachment) {
        if (this.attachment == null) {
            this.attachment = new ArrayList<EmailAttachment>();
        }
        this.attachment.add(attachment);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Map<String, Object> getTemplateParams() {
        return templateParams;
    }

    public void setTemplateParams(Map<String, Object> templateParams) {
        this.templateParams = templateParams;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addParam(String key, String value) {
        templateParams.put(key, value);
    }

    public List<EmailAttachment> getAttachment() {
        return attachment;
    }

    /**
     * @param attachment
     *            the attachment to set
     */
    public void setAttachment(List<EmailAttachment> attachment) {
        this.attachment = attachment;
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

}
