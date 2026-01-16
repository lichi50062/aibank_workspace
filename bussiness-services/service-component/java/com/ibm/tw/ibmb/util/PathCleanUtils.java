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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;

// @formatter:off
/**
 * @(#)PathCleanUtils.java
 * 
 * <p>Description:應 Fortify-Path Manipulation 新增檔案路徑處理</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/28, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class PathCleanUtils {

    private static final IBLog logger = IBLog.getLog(PathCleanUtils.class);

    private static final Map<String, String> pathCharWhiteList = new HashMap<>();
    private static final String VALID_CHARACTERS = "[a-zA-Z0-9_\\-\\.\\/\\\\]+";
    private static final Pattern VALID_PATTERN = Pattern.compile(VALID_CHARACTERS);

    static {
        String pathCharWhiteListSource = "abcdefghijklmnopqrstuvwxyz_1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ./\\-+%";
        if (StringUtils.isNotBlank(pathCharWhiteListSource)) {
            for (char c : pathCharWhiteListSource.toCharArray()) {
                String charStr = String.valueOf(c);
                pathCharWhiteList.put(charStr, charStr);
            }
        }
    }

    public static String cleanPath(String aString) {
        if (aString == null)
            return null;
        StringBuilder cleanString = new StringBuilder();
        for (int i = 0; i < aString.length(); ++i) {
            cleanString.append(aString.charAt(i));
        }

        String clearedString = validFilePath(cleanString.toString());

        return clearedString;
    }

    /**
     * 檢查傳入的檔案路徑，<br>
     * 在系統端環境是有效的
     *
     * @param path
     * @return
     */
    public static boolean isLocalPathValid(String path) {
        try {
            Paths.get(cleanPath(path));
        }
        catch (InvalidPathException e) {
            return false;
        }
        return true;
    }

    public static String validFilePath(String filePath) {
        String temp = "";

        boolean isHasDecode = false;
        try {
            filePath = URLEncoder.encode(filePath, "UTF-8");
            isHasDecode = true;
        }
        catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }

        for (int i = 0; i < filePath.length(); ++i) {
            Character curChar = null;
            Character nextChar = null;
            try {
                curChar = filePath.charAt(i);
                if (i < filePath.length() - 1) {
                    nextChar = filePath.charAt(i + 1);
                }
            }
            catch (NullPointerException | IndexOutOfBoundsException e) {
                logger.error(e.getMessage());
            }
            if (pathCharWhiteList.get(filePath.charAt(i) + "") != null && curChar == '\\') {
                String sysFileSeparator = File.separator;
                if (null != sysFileSeparator && sysFileSeparator.equals(curChar + "")) {
                    temp += pathCharWhiteList.get(filePath.charAt(i) + "");
                }
            }
            else if (pathCharWhiteList.get(filePath.charAt(i) + "") != null && curChar != '.') {
                temp += pathCharWhiteList.get(filePath.charAt(i) + "");
            }
            else if (pathCharWhiteList.get(filePath.charAt(i) + "") != null && curChar == '.' && nextChar != '.') {
                temp += pathCharWhiteList.get(filePath.charAt(i) + "");
            }
        }
        filePath = temp;
        try {
            if (isHasDecode) {
                return URLDecoder.decode(filePath, "UTF-8");
            }
            else {
                return filePath;
            }

        }
        catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
            return filePath;
        }
    }

}
