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
package com.ibm.tw.commons.gson;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ibm.tw.commons.util.time.DateFormatUtils;

/**
 * 針對Gson，將 java.util.Date，轉型為 "yyyy-MM-ddTHH:mm:ss.SSSZ" 日期格式的字串
 * 
 * @author Edward Tien
 */
public class DateTypeSerializer implements JsonSerializer<Date> {

    private static final String GSON_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /** 指定日期格式 */
    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(DateFormatUtils.format(src, GSON_DATE_FORMAT));
    }
}
