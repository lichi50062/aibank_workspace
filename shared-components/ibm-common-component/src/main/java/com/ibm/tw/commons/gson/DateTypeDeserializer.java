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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;

/**
 * 針對Gson，將日期格式的字串，轉型為 java.util.Date
 *
 * @author Edward Tien
 */
public class DateTypeDeserializer implements JsonDeserializer<Date> {

    /**
     * Logger
     */
    private static final IBLog logger = IBLog.getLog(DateTypeDeserializer.class);

    /** 指定日期格式 */
    private static final String[] DATE_PATTERNS = { "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy MM dd HH:mm:ss SSS", "yyyyMMdd HH:mm:ss", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd", "yyyy-MM-dd", "yyyyMMdd" };

    @Override
    public Date deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (!(jsonElement instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a string value");
        }

        String strValue = jsonElement.getAsJsonPrimitive().getAsString();

        if (StringUtils.isNumeric(strValue)) {
            long time = ConvertUtils.str2Long(strValue);
            return new Date(time);
        }

        Date date = convert(DATE_PATTERNS, strValue);

        if (date != null) {
            return date;
        }

        return convert(DATE_PATTERNS, strValue, Locale.US);
    }

    private Date convert(String[] patterns, String dateStr) {
        return convert(patterns, dateStr, Locale.TAIWAN);
    }

    private Date convert(String[] patterns, String dateStr, Locale locale) {
        for (String pattern : patterns) {
            try {
                final DateFormat format = new SimpleDateFormat(pattern, locale);
                Date date = format.parse(dateStr);
                if (!checkConsistency(date, pattern, dateStr)) {
                    throw new ServiceException(CommonErrorCode.DATE_FORMAT_ERROR.getSystemId(), CommonErrorCode.DATE_FORMAT_ERROR.getMemo(), SeverityType.ERROR, CommonErrorCode.DATE_FORMAT_ERROR.getErrorCode());
                }
                return date;
            }
            catch (ParseException ignored) {
                logger.debug(String.format("parsing error (ignored)，pattern:%s, dateStr:%s", pattern, dateStr));
            }
        }
        return null;
    }

    /**
     * 檢查日期資料的合理性
     *
     * @param date
     * @param dateStr
     * @return
     */
    private boolean checkConsistency(Date date, String pattern, String dateStr) {
        String s = DateFormatUtils.format(date, pattern);
        return StringUtils.equals(s, dateStr);
    }
}
