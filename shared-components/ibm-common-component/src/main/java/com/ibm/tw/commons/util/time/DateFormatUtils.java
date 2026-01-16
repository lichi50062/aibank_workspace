package com.ibm.tw.commons.util.time;

import org.apache.commons.lang3.time.FastDateFormat;

/**
 * 日期格式化工具類別
 * <p>
 * 日期格式化工具類別主要功能繼承自{@link org.apache.commons.lang3.time.DateFormatUtils}
 * <ul>
 * <li>日期物件的各種字串表示方式</li>
 * </ul>
 * </p>
 * 
 * @author Edward Tien
 * @see {@link org.apache.commons.lang3.time.DateFormatUtils}
 */
public class DateFormatUtils extends org.apache.commons.lang3.time.DateFormatUtils {

    public static final FastDateFormat SIMPLE_DATE_FORMAT = FastDateFormat.getInstance("yyyyMMdd");

    public static final FastDateFormat SIMPLE_DATE_TIME_FORMAT = FastDateFormat.getInstance("MM/dd HH:mm");

    public static final FastDateFormat SIMPLE_DATETIME_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss");

    public static final FastDateFormat SIMPLE_DATE_FORMAT_YEAR = FastDateFormat.getInstance("yyyy");

    public static final FastDateFormat SIMPLE_DATE_FORMAT_MONTH = FastDateFormat.getInstance("MM");

    public static final FastDateFormat SIMPLE_DATE_FORMAT_DAY = FastDateFormat.getInstance("dd");

    public static final FastDateFormat SIMPLE_DATE_FORMAT_MONTH_NO_PREFIX = FastDateFormat.getInstance("M");

    public static final FastDateFormat SIMPLE_DATE_FORMAT_YEAR_MONTH = FastDateFormat.getInstance("yyyyMM");

    public static final FastDateFormat SIMPLE_TIME_FORMAT = FastDateFormat.getInstance("HHmmss");

    public static final FastDateFormat SIMPLE_TIME_MILLI_FORMAT = FastDateFormat.getInstance("HHmmssSSS");

    public static final FastDateFormat SIMPLE_TIME_FORMAT_HOUR = FastDateFormat.getInstance("HH");

    public static final FastDateFormat SIMPLE_TIME_FORMAT_MINUTE = FastDateFormat.getInstance("mm");

    public static final FastDateFormat SIMPLE_TIME_FORMAT_HOUR_MINUTE = FastDateFormat.getInstance("HHmm");

    public static final FastDateFormat SIMPLE_ISO_DATETIME_FORMAT = FastDateFormat.getInstance("yyyyMMdd'T'HHmmss");

    public static final FastDateFormat SQL_YEAR_MONTH_FORMAT = FastDateFormat.getInstance("yyyy-MM");

    public static final FastDateFormat SQL_DATE_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd");

    public static final FastDateFormat SQL_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss.SSS");

    public static final FastDateFormat SQL_DATETIME_FORMAT_WITHOUT_MINISECOND = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    public static final FastDateFormat SQL_TIME_FORMAT = FastDateFormat.getInstance("HH:mm:ss.SSS");

    public static final FastDateFormat HOUR_MIN_FORMAT = FastDateFormat.getInstance("HH:mm");

    public static final FastDateFormat CE_DATE_FORMAT = FastDateFormat.getInstance("yyyy/MM/dd");

    public static final FastDateFormat CE_YEAR_MONTH_FORMAT = FastDateFormat.getInstance("yyyy/MM");

    public static final FastDateFormat CE_DATE_FORMAT_MONTH_DAY = FastDateFormat.getInstance("MM/dd");

    public static final FastDateFormat CE_DATETIME_FORMAT = FastDateFormat.getInstance("yyyy/MM/dd HH:mm:ss");

    public static final FastDateFormat CE_DATETIME_FORMAT_WITHOUT_SEC = FastDateFormat.getInstance("yyyy/MM/dd HH:mm");

    public static final FastDateFormat CE_TIME_FORMAT = FastDateFormat.getInstance("HH:mm:ss");

    public static final FastDateFormat CARD_EXPIRED_FORMAT = FastDateFormat.getInstance("MMyy");

    public static final FastDateFormat SIMPLE_HOST_FORMAT = FastDateFormat.getInstance("ddMMyyyy");

    /** yyyy-MM-dd'T'HH:mm:ss.SSSZZ (eg. 2009-01-01T13:10:20.123+08:00) */
    public static final FastDateFormat CLIENTDT_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss.SSSZZ");

    /** yyyyMMddHHmmssSSS (eg. 20090101131020123+08:00) */
    public static final FastDateFormat CLIENTDT_2_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmssSSSZZ");

    /** yyyyMMddHHmmssSSS (eg. 20090101131020123) */
    public static final FastDateFormat CLIENTDT_3_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmssSSS");

    /** yyyy-MM-dd'T'HH;:mm:ssZZ (eg. 2024-04-10T00:00:00+80:00) */
    public static final FastDateFormat CLIENTDT_4_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");

    /** ccc/MM/dd (eg. 099/01/01) */
    public static final String ROC_DATE_PATTERN = "ccc/MM/dd";

    public static final String ROC_DATETIME_PATTERN = "ccc/MM/dd HH:mm:ss";

    public static final String SIMPLE_ROC_DATE_PATTERN = "cccMMdd";

    /** cccMM (eg. 11108) */
    public static final String SIMPLE_ROC_YEAR_MONTH = "cccMM";

    /** 主機教時時間格式 */
    public static final FastDateFormat TXMSRN_FORMATTER = FastDateFormat.getInstance("HHmmss00");

}
