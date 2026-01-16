/*
 * =========================================================================== IBM Confidential AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.ibm.tw.ibmb.type;

// @formatter:off
/**
 * @(#)FileType.java
 * 
 * <p>Description:檔案種類</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/26, Horance Chou	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public enum FileType {

    PDF("application/pdf"),

    EXCEL("application/vnd.ms-excel"),

    CSV("application/text"),

    TEXT("application/text"),

    IMAGE("images/*");

    private String mimeType;

    FileType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

}
