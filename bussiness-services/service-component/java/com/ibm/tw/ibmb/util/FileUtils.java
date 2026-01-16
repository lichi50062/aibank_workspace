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
package com.ibm.tw.ibmb.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)FileUtils.java
 * 
 * <p>Description:檔案工具類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class FileUtils extends org.apache.commons.io.FileUtils {

    private static IBLog logger = IBLog.getLog(FileUtils.class);

    @SuppressWarnings("deprecation")
    private FileUtils() {
        // can't allow new instance
    }

    /**
     * 儲存字串至指定位置
     * 
     * @param base64Str
     * @param directory
     * @param fileName
     * @return
     */
    public static boolean writeBase64StrToFile(String base64Str, String directory, String fileName) {
        File file = new File(PathCleanUtils.cleanPath(directory), fileName);
        try {
            writeBase64StrToFile(file, base64Str);
            return true;
        }
        catch (IOException ex) {
            logger.error(String.format("儲存檔案失敗。檔案名稱:%s", fileName));
            return false;
        }
    }

    /**
     * 將 base64 字串，存成檔案
     * 
     * @param file
     * @param base64Str
     * @return
     * @throws IOException
     */
    public static void writeBase64StrToFile(File file, String base64Str) throws IOException {
        byte[] data = null;
        if (StringUtils.indexOf(base64Str, ";base64,") != -1) {
            data = Base64.decodeBase64(StringUtils.substringAfter(base64Str, ";base64,"));
        }
        else {
            data = Base64.decodeBase64(base64Str);
        }
        writeByteArrayToFile(file, data);
    }

    /**
     * 檢查檔案是否存在
     * 
     * @param file
     * @return
     */
    public static boolean checkFileIsExists(File file) {
        return file != null && file.exists() && file.isFile();
    }
}
