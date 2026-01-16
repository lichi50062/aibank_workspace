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
package com.ibm.tw.commons.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeParameter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.log.IBLog;

/**
 * <p>
 * Json Utils
 * </p>
 * 
 * @author Leo , Alex
 * @version 1.1, 2017/04/08
 * @see
 * @since
 */
public class JsonUtils {

    private static IBLog logger = IBLog.getLog(JsonUtils.class);

    private JsonUtils() {
    }

    /**
     * Json字串轉換為物件
     * 
     * @param <P>
     * @param json
     * @param clazz
     * @return
     */
    public static <P> P getB2EObject(String json, Class<P> clazz) {
        return getObject(json, clazz);
    }

    /**
     * 物件轉換為Json字串
     * 
     * @param <POJO>
     * @param object
     * @return
     */
    public static String getB2EJson(Object object) {
        return getJson(object);
    }

    /**
     * Json字串轉換為物件
     * 
     * @param <P>
     * @param json
     * @param clazz
     * @return
     */
    public static <P> P getObject(String json, Class<P> clazz) {
        try {
            Gson gson = GsonHelper.newInstance();
            return gson.fromJson(json, clazz);
        }
        catch (JsonSyntaxException ex) {
            logger.error("JsonWriter Error", ex);
        }
        return null;
    }

    /**
     * Json字串轉換為物件
     * 
     * @param <T>
     * @param json
     * @param typeOfT
     * @return
     */
    public static <T> T getObject(String json, Type typeOfT) {
        try {
            Gson gson = GsonHelper.newInstance();
            return gson.fromJson(json, typeOfT);
        }
        catch (JsonSyntaxException ex) {
            logger.error("JsonWriter Error", ex);
        }
        return null;
    }

    /**
     * 物件轉換為Json字串
     * 
     * @param object
     * @return
     */
    public static String getJson(Object object) {
        try {
            Gson gson = GsonHelper.newInstance();
            return gson.toJson(object);
        }
        catch (IllegalStateException ex) {
            logger.error("JsonWriter Error", ex);
        }
        return StringUtils.EMPTY;
    }

    public static <P> List<P> getJsonObjectList(String json, Class<P> clazz) {
        Type listType = getType(clazz);
        Gson gson = GsonHelper.newInstance();
        return gson.fromJson(json, listType);
    }

    @SuppressWarnings("serial")
    private static <T> Type getType(Class<T> type) {
        return new TypeToken<ArrayList<T>>() {
        }.where(new TypeParameter<T>() {
        }, type).getType();
    }

    /**
     * 檢查字串是否為 JSON 格式
     * 
     * @param content
     * @return
     */
    public static boolean isJson(String content) {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        try {
            Gson gson = GsonHelper.newInstance();
            JsonElement jsonElement = gson.fromJson(content, JsonElement.class);
            return jsonElement != null;
        }
        catch (JsonSyntaxException ex) {
            return false;
        }
    }
}
