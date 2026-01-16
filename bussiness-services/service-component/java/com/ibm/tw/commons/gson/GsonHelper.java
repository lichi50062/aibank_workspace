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

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.type.I18NEnum;
import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.FieldUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.annotations.FormatBigDecimal;
import com.ibm.tw.ibmb.annotations.FormatDate;
import com.ibm.tw.ibmb.util.IBUtils;

/**
 * Gson 輔助類別
 *
 * @author Edward Tien
 */
public class GsonHelper {

    /** Logger */
    protected static IBLog logger = IBLog.getLog(GsonHelper.class);

    protected static final Class<?>[] NOT_BEAN_TYPES = new Class<?>[] { String.class, Character.class, Short.class, Integer.class, Long.class, Double.class, Byte.class, Float.class, Boolean.class, BigDecimal.class, Date.class, Locale.class };

    private static final String DATE_DEFAULT_PATTERN = "yyyy MM dd HH:mm:ss SSS";

    /**
     * don't allow instance
     */
    private GsonHelper() {
        // dont' allow
    }

    /**
     * 取得 Gson 物件
     *
     * @return
     */
    public static Gson newInstance() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new DateTypeSerializer());
        builder.registerTypeAdapter(Date.class, new DateTypeDeserializer());
        builder.registerTypeAdapter(BigDecimal.class, new BigDecimalTypeDeserializer());
        builder.registerTypeAdapter(Integer.class, new IntegerTypeDeserializer());
        builder.registerTypeHierarchyAdapter(Calendar.class, new CalendarTypeSerializer());
        builder.registerTypeHierarchyAdapter(Calendar.class, new CalendarTypeDeserializer());
        return builder.create();
    }

    /**
     * 將 JavaBean 的資料，轉為 Map 型態
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static Map<String, Object> bean2Map(Object bean) {

        Map<String, Object> dataMap = new HashMap<>();

        Field[] fields = FieldUtils.getAllFields(bean.getClass());

        if (ArrayUtils.isEmpty(fields)) {
            return dataMap;
        }

        for (Field field : fields) {

            // 修飾字非 private 的成員變數，一律排除
            if (field.getModifiers() != Modifier.PRIVATE) {
                continue;
            }

            Object value = null;
            try {
                // 若是 Enum 型別，呼叫 getter method 取值
                if (bean.getClass().isEnum()) {
                    try {
                        // 透過 getter method 取指定欄位的值
                        value = PropertyUtils.getProperty(bean, field.getName());

                        // 若有實作 I18NEnum，取 getI18NMemo() 替換 memo 的值
                        if (bean instanceof I18NEnum && field.getName().equals("memo")) {
                            value = MethodUtils.invokeExactMethod(bean, "getI18NMemo");
                        }
                    }
                    catch (InvocationTargetException | NoSuchMethodException e) {
                        logger.error("Enum 沒有對應 getter method，取值時抛出例外，不影響程序。");
                    }
                }
                // 非 Enum 型別物件，一律從 field 本身取值
                if (value == null) {
                    value = FieldUtils.readField(field, bean, true);
                }
            }
            catch (IllegalAccessException e) {
                if (logger.isDebugEnabled()) {
                    logger.debug("error read field", e);
                }
            }

            // 按照成員變數的資料型態，進行指定的格式轉換
            // 值=空值 by pass
            if (value != null) {
                // 成員變數名稱
                String name = field.getName();
                Object converted = convertInnerType(field, value);
                dataMap.put(name, converted);
            }
        }

        return dataMap;
    }

    /**
     * 按照成員變數的資料型態，進行指定的格式轉換
     *
     * @param field
     * @param value
     * @return
     */
    private static Object convertInnerType(Field field, Object value) {

        if (value == null) {
            return null;
        }

        String type = field.getGenericType().toString();
        Object converted = value;
        // 成員變數的資料型態=基礎資料型態
        if (field.getType().isPrimitive()) {
            return converted;
        }
        // 成員變數的資料型態為數字
        else if (value instanceof Number) {
            // 型態為 BigDecimal，另外處理它
            if (StringUtils.equals(type, "class java.math.BigDecimal")) {
                BigDecimal bigDecimal = (BigDecimal) value;
                // 判斷 annotation 裡的屬性決定轉換格式，若有設置 annotation 則轉換成字串格式
                if (checkAnnotation4FormatBigDecimal(field)) {
                    converted = bigDecimal2String(field, bigDecimal);
                }
                else {
                    converted = bigDecimal;
                }
            }
            else {
                converted = value;
            }
        }
        // 成員變數的資料型態為 List
        else if (value instanceof List) {
            converted = handleListTypeData((List<?>) value);
        }
        // 成員變數的資料型態為 Map
        else if (value instanceof Map) {
            converted = handleMapTypeData((Map<?, ?>) value);
        }
        // 成員變數的資料型態為 JavaBean
        else if (isJavaBean(value)) {
            converted = bean2Map(value);
        }
        // 判斷 annotation 裡的 pattern 決定轉換格式
        else if (StringUtils.equals(type, "class java.util.Date")) {
            converted = date2String(field, (Date) value);
        }
        else {
            logger.trace(String.format("field value not converted: %s, type is %s", value, type));
        }
        return converted;
    }

    /**
     * 檢查是否為JavaBean
     *
     * @param obj
     * @return
     */
    private static boolean isJavaBean(Object obj) {
        if (obj == null) {
            return false;
        }
        boolean check = true;
        for (Class<?> checkType : NOT_BEAN_TYPES) {
            if (obj.getClass().isAssignableFrom(checkType)) {
                check = false;
                break;
            }
        }
        return check;
    }

    /**
     * 展開 List 型態的資料，針對各元素個別處理
     *
     * @param value
     * @return
     * @throws Exception
     */
    private static List<?> handleListTypeData(List<?> value) {
        List<Object> list = new ArrayList<>();
        try {
            for (Object o : (List<?>) value) {
                Object converted = o;
                if (o instanceof List) {
                    converted = handleListTypeData((List<?>) o);
                }
                else if (o instanceof Map) {
                    converted = handleMapTypeData((Map<?, ?>) o);
                }
                else if (isJavaBean(o)) {
                    converted = bean2Map(o);
                }
                list.add(converted);
            }
        }
        catch (IllegalArgumentException ex) {
            logger.error("error convert list", ex);
        }
        return list;
    }

    /**
     * 展開 Map 型態的資料，針對各元素個別處理
     *
     * @param value
     * @return
     * @throws Exception
     */
    private static Map<Object, Object> handleMapTypeData(Map<?, ?> value) {
        Map<Object, Object> map2map = new LinkedHashMap<>();
        try {
            Map<?, ?> map = value;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                Object mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                if (mapValue instanceof List) {
                    map2map.put(mapKey, handleListTypeData((List<?>) mapValue));
                }
                else if (mapValue instanceof Map) {
                    map2map.put(mapKey, handleMapTypeData((Map<?, ?>) mapValue));
                }
                else if (isJavaBean(mapValue)) {
                    map2map.put(mapKey, bean2Map(mapValue));
                }
                else {
                    map2map.put(mapKey, mapValue);
                }
            }
        }
        catch (IllegalArgumentException ex) {
            logger.error("handle Map type", ex);
        }
        return map2map;
    }

    /**
     * 依 annotation 設置，將 java.util.Date 進行字串格式轉換
     *
     * @param field
     * @param date
     * @return
     */
    private static String date2String(Field field, Date date) {
        String str = DateFormatUtils.format(date, DATE_DEFAULT_PATTERN);
        Annotation[] allAnnotations = field.getAnnotations();
        if (allAnnotations != null && allAnnotations.length > 0) {
            for (Annotation annotation : allAnnotations) {
                if (annotation instanceof FormatDate) {
                    String pattern = ((FormatDate) annotation).pattern();
                    if (StringUtils.isNotBlank(pattern)) {
                        str = DateFormatUtils.format(date, pattern);
                    }
                }
            }
        }
        return str;
    }

    /**
     * 依 annotation 設置，將 java.math.BigDeimal 進行字串格式轉換
     *
     * @param field
     * @param value
     * @return
     */
    private static String bigDecimal2String(Field field, BigDecimal value) {
        Annotation[] allAnnotations = field.getAnnotations() != null ? field.getAnnotations() : new Annotation[0];
        for (Annotation annotation : allAnnotations) {
            if (annotation instanceof FormatBigDecimal) {
                FormatBigDecimal anno = (FormatBigDecimal) annotation;
                BigDecimal tmpValue = value;
                // 設置小數位
                if (anno.scale() > -1) {
                    tmpValue = value.setScale(anno.scale(), anno.roundingMode());
                }
                // 有指定千分位
                if (anno.thousand()) {
                    return IBUtils.formatMoneyStr(tmpValue);
                }
                // 沒有指定千分位
                else {
                    return tmpValue.toPlainString();
                }
            }
        }
        return ConvertUtils.bigDecimal2Str(value, value.scale());
    }

    /**
     * 檢查宣告的 Annotation 中，是否包含 {@link com.ibm.tw.ibmb.annotations.FormatBigDecimal}
     * 
     * @param field
     * @return
     */
    private static boolean checkAnnotation4FormatBigDecimal(Field field) {
        Annotation[] allAnnotations = field.getAnnotations() != null ? field.getAnnotations() : new Annotation[0];
        for (Annotation annotation : allAnnotations) {
            if (annotation instanceof FormatBigDecimal) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判斷物件是否是基礎資料型態
     * 
     * @param obj
     * @return
     */
    public static boolean isPrimitive(Object obj) {
        return obj instanceof Boolean || obj instanceof Character || obj instanceof Byte || obj instanceof Short || obj instanceof Integer || obj instanceof Long || obj instanceof Float || obj instanceof Double;
    }
}
