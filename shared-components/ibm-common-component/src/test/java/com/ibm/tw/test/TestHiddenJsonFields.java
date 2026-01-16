package com.ibm.tw.test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import com.ibm.tw.commons.util.StringUtils;

public class TestHiddenJsonFields {

    private static final String fieldsToHide[] = "aaa,errorCode,ccd".split(",");

    @Test
    public void testHiddenFields() throws IOException {
        // String testjson = Files.readString(Path.of("/Users/horance/Downloads", "test.json"));
        // logger.info("orig: {}", testjson);
        // logger.info("processed: {}", processHiddenFieldsForJSON(testjson));
    }

    public void hideJsonElement(Map<String, Object> element) {
        for (Entry<String, Object> entry : element.entrySet()) {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                if (isNeedHide(name)) {
                    entry.setValue("XXXX");
                }
            }
            else {
                processFieldValue(name, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public void processFieldValue(String name, Object value) {
        if (value == null) {
            return;
        }
        if (value instanceof Map) {
            hideJsonElement((Map<String, Object>) value);
        }
        else if (value instanceof Collection) {
            Collection<Object> list = (Collection<Object>) value;
            for (Object e : list) {
                processFieldValue(name, e);
            }
        }
        else if (value.getClass().isArray()) {
            int length = Array.getLength(value);
            for (int i = 0; i < length; i++) {
                processFieldValue(name, Array.get(value, i));
            }
        }
    }

    public boolean isNeedHide(String name) {
        return StringUtils.equalsAnyIgnoreCase(name, fieldsToHide);
    }

}