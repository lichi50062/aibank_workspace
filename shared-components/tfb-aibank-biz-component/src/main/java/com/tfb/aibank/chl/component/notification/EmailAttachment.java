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
package com.tfb.aibank.chl.component.notification;

// @formatter:off
/**
 * @(#)EmailAttachment.java
 * 
 * <p>Description:電子郵件附件檔</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EmailAttachment {

    /** 檔案路徑 */
    private String filePath;
    /** 檔名 */
    private String fileName;

    public EmailAttachment() {
        super();
    }

    public EmailAttachment(String filePath) {
        super();
        this.filePath = filePath;
    }

    public EmailAttachment(String filePath, String fileName) {
        super();
        this.filePath = filePath;
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
