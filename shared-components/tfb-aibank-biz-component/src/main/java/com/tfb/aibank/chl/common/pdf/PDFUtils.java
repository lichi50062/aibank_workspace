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
package com.tfb.aibank.chl.common.pdf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import com.ibm.tw.commons.log.IBLog;

//@formatter:off
/**
 * @(#)PDFUtils.java
 * 
 * <p>Description:PDF 工具集</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/20, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class PDFUtils {

    private static final IBLog logger = IBLog.getLog(PDFUtils.class);

    private PDFUtils() {
        // Private constructor
    }

    /**
     * 取得 資源檔路徑
     * 
     * @param resourcePathName
     * @return
     * @throws IOException
     */
    public static String getImage2Base64(String resourcePathName, Class<?> obj) throws IOException {
        String encodedImg = "";
        // fortify: null check
        if (obj == null) {
            logger.warn("input base object is null, return empty image");
            return "";
        }
        ClassLoader cl = obj.getClassLoader();
        if (cl == null) {
            logger.error("error getting class loader");
            return "";
        }
        // #4504 0823 Unreleased Resource: Streams 調整
        try (InputStream inputStream = cl.getResourceAsStream("pdf/img/" + resourcePathName)) {
            byte[] imgBytes = inputStream.readAllBytes();
            encodedImg = Base64.getEncoder().encodeToString(imgBytes);
        }
        return encodedImg;
    }

}
