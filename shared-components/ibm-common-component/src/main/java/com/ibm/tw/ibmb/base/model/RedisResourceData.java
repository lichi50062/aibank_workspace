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
package com.ibm.tw.ibmb.base.model;

/**
 * 將靜態資料存入 redis 用的 BO
 * 
 * @author horance
 *
 */
public class RedisResourceData {
    private String contentType;
    private String base64Content;
    private String downloadFileName;

    /**
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     *            the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * @return the base64Content
     */
    public String getBase64Content() {
        return base64Content;
    }

    /**
     * @param base64Content
     *            the base64Content to set
     */
    public void setBase64Content(String base64Content) {
        this.base64Content = base64Content;
    }

    /**
     * @return the downloadFileName
     */
    public String getDownloadFileName() {
        return downloadFileName;
    }

    /**
     * @param downloadFileName
     *            the downloadFileName to set
     */
    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

}
