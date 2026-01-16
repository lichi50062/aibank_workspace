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
package com.ibm.tw.commons.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import com.ibm.tw.commons.log.IBLog;

/**
 * <p>
 * I18NUtils
 * </p>
 *
 * @author Leo
 * @version 1.0, 2012/11/26
 * @see
 * @since
 */
public class I18NUtils {

    private static IBLog logger = IBLog.getLog(I18NUtils.class);

    private static ThreadLocal<Locale> locale = new ThreadLocal<>();

    private static MessageSource messageSource;

    private I18NUtils() {

    }

    /**
     * 取得locale
     *
     * @return 傳回locale
     */
    public static synchronized Locale getLocale() {
        if (null == locale.get()) {
            logger.warn("thread locale = " + locale.get());
            return Locale.TAIWAN;
        }
        else {
            return locale.get();
        }
    }

    /**
     * 設定locale
     *
     * @param locale
     */
    public static synchronized void setLocale(Locale locale) {
        I18NUtils.locale.set(locale);
    }

    /**
     * 根據鍵值取得對應訊息
     * 
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        return getMessage(key, getLocale());
    }

    /**
     * 根據鍵值與語系取得對應訊息
     * 
     * @param key
     * @param locale
     * @return
     */
    public static String getMessage(String key, Locale locale) {
        try {
            if (messageSource != null) {
                return messageSource.getMessage(key, null, locale);
            }
            else {
                return key;
            }
        }
        catch (NoSuchMessageException e) {
            logger.warn("message not found for key : " + key + " , fallback to zh_TW", e);
            try {
                return messageSource.getMessage(key, null, Locale.TAIWAN);
            }
            catch (NoSuchMessageException e1) {
                return key;
            }
        }
    }

    /**
     * 根據鍵值與參數取得對應訊息
     * 
     * @param key
     * @param params
     * @return
     */
    public static String getMessage(String key, Object... params) {

        Locale locale = getLocale();

        return getMessage(key, locale, params);
    }

    /**
     * 根據鍵值、語系與參數取得對應訊息
     * 
     * @param key
     * @param locale
     * @param params
     * @return
     */
    public static String getMessage(String key, Locale locale, Object... params) {
        try {
            if (messageSource != null) {
                return messageSource.getMessage(key, params, locale);
            }
            else {
                return key;
            }
        }
        catch (NoSuchMessageException e) {
            logger.warn("message not found for key : " + key + " , fallback to zh_TW", e);
            try {
                return messageSource.getMessage(key, null, Locale.TAIWAN);
            }
            catch (NoSuchMessageException e1) {
                return key;
            }
        }
    }

    /**
     * @param messageSource
     *            the messageSource to set
     */
    public static void setMessageSource(MessageSource messageSource) {
        I18NUtils.messageSource = messageSource;
    }

    /**
     * 清掉 locale
     */
    public static void resetLocale() {
        I18NUtils.locale.remove();
    }
}
