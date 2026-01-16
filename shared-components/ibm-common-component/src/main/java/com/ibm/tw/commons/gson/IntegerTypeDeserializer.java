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

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;

/**
 * 針對Gson，將符合數字格式的字串，轉型為 java.math.Integer
 * 
 * @author Edward Tien
 */
public class IntegerTypeDeserializer implements JsonDeserializer<Number> {

    /**
     * Logger
     */
    private static final IBLog logger = IBLog.getLog(IntegerTypeDeserializer.class);

    @Override
    public Number deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The integer should be a string value");
        }
        String value = json.getAsJsonPrimitive().getAsString();
        if (StringUtils.isBlank(value)) {
            logger.info("json vlaue is null or empty. return null");
            return null;
        }
        return Integer.valueOf(value);
    }
}
