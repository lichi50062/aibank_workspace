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
package com.ibm.tw.commons.log;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

public class IBLog {

    private static final int MAX_LOG_LENGTH = 100000;

    private Logger logger = null;

    protected IBLog(Class<?> clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    protected IBLog(String loggerName) {
        logger = LoggerFactory.getLogger(loggerName);
    }

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    public void trace(String log) {
        if (logger.isTraceEnabled()) {
            log = ValidateParamUtils.validParam(log);
            log = Encode.forJava(log);
            logger.trace(decodeUnicodeString(log));
        }
    }

    public void trace(String format, Object arg1, Object arg2) {
        if (logger.isTraceEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.trace(decodeUnicodeString(format), unEncodeValidArgument(arg1), unEncodeValidArgument(arg2));
        }
    }

    public void trace(String format, Object... args) {
        if (logger.isTraceEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.trace(decodeUnicodeString(format), unEncodeValidArguments(args));
        }
    }

    public void trace(String logMsg, Throwable e) {
        if (logger.isTraceEnabled()) {
            logMsg = ValidateParamUtils.validParam(logMsg);
            logMsg = Encode.forJava(logMsg);
            logger.trace(decodeUnicodeString(logMsg), e);
        }
    }

    public void debug(String logMsg) {
        if (logger.isDebugEnabled()) {
            logMsg = ValidateParamUtils.validParam(logMsg);
            logMsg = Encode.forJava(logMsg);
            logger.debug(decodeUnicodeString(logMsg));
        }
    }

    public void debug(String format, Object arg1, Object arg2) {
        if (logger.isDebugEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.debug(decodeUnicodeString(format), unEncodeValidArgument(arg1), unEncodeValidArgument(arg2));
        }
    }

    public void debug(String format, Object... args) {
        if (logger.isDebugEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.debug(decodeUnicodeString(format), unEncodeValidArguments(args));
        }
    }

    public void debug(String log, Throwable e) {
        if (logger.isDebugEnabled()) {
            log = ValidateParamUtils.validParam(log);
            log = Encode.forJava(log);
            logger.debug(decodeUnicodeString(log), e);
        }
    }

    public void info(String log) {
        if (logger.isInfoEnabled()) {
            log = ValidateParamUtils.validParam(log);
            log = Encode.forJava(log);
            logger.info(decodeUnicodeString(log));
        }
    }

    public void info(String format, Object arg1, Object arg2) {
        if (logger.isInfoEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.info(decodeUnicodeString(format), unEncodeValidArgument(arg1), unEncodeValidArgument(arg2));
        }
    }

    public void info(String format, Object... arguments) {
        if (logger.isInfoEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.info(decodeUnicodeString(format), unEncodeValidArguments(arguments));
        }
    }

    public void info(String log, Throwable e) {
        if (logger.isInfoEnabled()) {
            log = ValidateParamUtils.validParam(log);
            log = Encode.forJava(log);
            logger.info(decodeUnicodeString(log), e);
        }
    }

    public void warn(String log) {
        if (logger.isWarnEnabled()) {
            log = ValidateParamUtils.validParam(log);
            log = Encode.forJava(log);
            logger.warn(decodeUnicodeString(log));
        }
    }

    public void warn(String format, Object arg1, Object arg2) {
        if (logger.isWarnEnabled()) {
            format = Encode.forJava(format);
            logger.warn(decodeUnicodeString(format), unEncodeValidArgument(arg1), unEncodeValidArgument(arg2));
        }
    }

    public void warn(String format, Object... arguments) {
        if (logger.isWarnEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.warn(decodeUnicodeString(format), unEncodeValidArguments(arguments));
        }
    }

    public void warn(String log, Throwable e) {
        if (logger.isWarnEnabled()) {
            log = ValidateParamUtils.validParam(log);
            log = Encode.forJava(log);
            logger.warn(decodeUnicodeString(log), e);
        }
    }

    public void error(String log) {
        if (logger.isErrorEnabled()) {
            log = ValidateParamUtils.validParam(log);
            log = Encode.forJava(log);
            logger.error(decodeUnicodeString(log));
        }
    }

    public void error(String format, Object arg1, Object arg2) {
        if (logger.isErrorEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.error(decodeUnicodeString(format), unEncodeValidArgument(arg1), unEncodeValidArgument(arg2));
        }
    }

    public void error(String format, Object... arguments) {
        if (logger.isErrorEnabled()) {
            format = ValidateParamUtils.validParam(format);
            format = Encode.forJava(format);
            logger.error(decodeUnicodeString(format), unEncodeValidArguments(arguments));
        }
    }

    public void error(String log, Throwable e) {
        if (logger.isErrorEnabled()) {
            log = ValidateParamUtils.validParam(log);
            log = Encode.forJava(log);
            // fortify: log forging
            if (e != null) {
                logger.error(decodeUnicodeString(log));
                logger.error("santinized stacktrace: {}", decodeUnicodeString(Encode.forJava(ValidateParamUtils.validParam(ExceptionUtils.getStackTrace(e)))));
            }
            else {
                logger.error(decodeUnicodeString(log));
            }
        }
    }

    /**
     * 部分類型 Log ex: traceLog 會因為 encode 處理失效
     * 
     * @param log
     */
    public void debugNoEncode(String logMsg) {
        if (logger.isDebugEnabled()) {
            logMsg = ValidateParamUtils.validParam(logMsg);
            logger.debug(logMsg);
        }
    }

    /**
     * 部分類型 Log ex: traceLog 會因為 encode 處理失效
     * 
     * @param log
     */
    public void errorNoEncode(String log) {
        if (logger.isErrorEnabled()) {
            log = ValidateParamUtils.validParam(log);
            logger.error(log);
        }
    }

    /**
     * 部分類型 Log ex: traceLog 會因為 encode 處理失效
     * 
     * @param log
     */
    public void infoNoEncode(String log) {
        if (logger.isInfoEnabled()) {
            log = ValidateParamUtils.validParam(log);
            logger.info(log);
        }
    }

    /**
     * Log Forging (適用於 log 中的參數)
     * 
     * @param arg
     * @return
     */
    private String unEncodeValidArgument(Object arg) {
        return (arg == null) ? StringUtils.EMPTY : validLog(arg.toString());
    }

    private Object[] unEncodeValidArguments(Object... arguments) {
        if (arguments != null && arguments.length != 0) {
            String[] newArray = new String[arguments.length];
            for (int i = 0; i < arguments.length; i++) {
                newArray[i] = unEncodeValidArgument(arguments[i]);
            }
            return newArray;
        }
        return null;
    }

    /**
     * log Forging 敏感字源檢核
     * 
     * @param log
     * @return
     */
    public static String validLog(String log) {
        List<String> validChars = new ArrayList<>();
        validChars.add("%0d");
        validChars.add("\r");
        validChars.add("%0a");
        validChars.add("\n");
        validChars.add("\t");
        String cleanLog = Normalizer.normalize(log, Normalizer.Form.NFKC);
        for (int i = 0; i < validChars.size(); i++) {
            cleanLog = cleanLog.replaceAll(validChars.get(i), "");
        }
        String encode = Encode.forJava(cleanLog);
        return decodeUnicodeString(encode);
    }

    /**
     * 將中文 Unicode 做解碼
     * 
     * @param stringWithUnicode
     * @return
     */
    public static String decodeUnicodeString(String stringWithUnicode) {
        String decodedString = "";
        Pattern unicodePattern = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher unicodeMatcher = unicodePattern.matcher(stringWithUnicode);

        int lastMatchEnd = 0;
        while (unicodeMatcher.find()) {
            int unicodeStart = unicodeMatcher.start();
            // Add the part of the string before the Unicode match
            decodedString = decodedString.concat(stringWithUnicode.substring(lastMatchEnd, unicodeStart));

            // Decode the Unicode escape sequence
            String unicodeHex = unicodeMatcher.group(1);
            int unicodeValue = Integer.parseInt(unicodeHex, 16);
            char decodedCharacter = (char) unicodeValue;
            decodedString = decodedString.concat(String.valueOf(decodedCharacter));

            lastMatchEnd = unicodeMatcher.end();

            // Fortify: Denial of Service: Limit length
            if (decodedString.length() > MAX_LOG_LENGTH) {
                break;
            }
        }

        // Add the remaining part of the string
        String remaining = stringWithUnicode.substring(lastMatchEnd);
        decodedString = decodedString.concat(StringUtils.left(remaining, MAX_LOG_LENGTH));

        return decodedString;
    }

    /**
     * @param clazz
     * @return
     */
    public static IBLog getLog(Class<?> clazz) {
        return new IBLog(clazz);
    }

    /**
     * @param clazz
     * @return
     */
    public static IBLog getLog(String loggerName) {
        return new IBLog(loggerName);
    }
}
