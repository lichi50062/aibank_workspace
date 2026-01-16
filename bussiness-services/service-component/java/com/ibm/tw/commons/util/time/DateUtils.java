package com.ibm.tw.commons.util.time;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.lang3.LocaleUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;

/**
 * 日期工具集
 * <p>
 * 日期工具集主要功能繼承自{@link org.apache.commons.lang3.time.DateUtils}， 並增加下列功能：
 * <ul>
 * <li>簡易的日期物件產生方式</li>
 * <li>日期物件的各種字串表示方式</li>
 * </ul>
 * </p>
 *
 * @author Jeff Liu
 * @version 1.0, 2004/10/20
 * @see {@link org.apache.commons.lang3.time.DateUtils}
 * @since
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static final double MIL_SECONDS_IN_ONE_DAY = 86400000.0;

    private static IBLog logger = IBLog.getLog(DateUtils.class);

    /** ISO日期格式的分隔子 */
    public static final String ISO_LINK = "-";

    /** 民國年預設之分隔子 */
    public static final String ROC_LINK = "/";

    /** 日期分隔符號清單 */
    protected static final String[] DATE_LINK_LIST = { ISO_LINK, ROC_LINK };

    /** 時間分隔符號清單 */
    protected static final String[] TIME_LINK_LIST = { ":", ".", "/" };

    /**
     * 清除<code>Calendar</code>物件中之各時間欄位值，僅保留日期欄位
     *
     * @param calendar
     * @return
     */
    public static void clearTime(Calendar calendar) {
        if (null == calendar) {
            return;
        }

        int iYear = calendar.get(Calendar.YEAR);
        int iMonth = calendar.get(Calendar.MONTH);
        int iDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.clear();

        calendar.set(Calendar.YEAR, iYear);
        calendar.set(Calendar.MONTH, iMonth);
        calendar.set(Calendar.DAY_OF_MONTH, iDay);
    }

    /**
     * 清除 hour, min, sec, ms...
     *
     * @param calendar
     * @return
     */
    public static void clearTime(Date dt) {
        if (dt == null) {
            return;
        }
        Calendar calendar = ConvertUtils.date2Calendar(dt);
        if (calendar == null) {
            return;
        }
        clearTime(calendar);
        dt.setTime(calendar.getTime().getTime());
    }

    /**
     * 根據西元年/月/日取得<code>Date</code>物件
     *
     * @param iYear
     *            西元年
     * @param iMonth
     *            月
     * @param iDay
     *            日
     * @return
     *
     */
    public static Date getDate(int iYear, int iMonth, int iDay) {

        if (iYear <= 0 || iMonth <= 0 || iDay <= 0) {
            return null;
        }

        if (iMonth > 12) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, iYear);
        calendar.set(Calendar.MONTH, iMonth - 1);

        if (iDay > getMonthOfLastDate(calendar.getTime())) {
            return null;
        }

        calendar.set(Calendar.DAY_OF_MONTH, iDay);
        return calendar.getTime();
    }

    /**
     * 根據西元年/月/日 時/分/秒取得<code>Date</code>物件
     *
     * @param iYear
     *            西元年
     * @param iMonth
     *            月
     * @param iDay
     *            日
     * @param iHour
     *            時 (24小時制)
     * @param iMin
     *            分
     * @param iSec
     *            表
     * @return
     *
     */
    public static Date getDateTime(int iYear, int iMonth, int iDay, int iHour, int iMin, int iSec) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();

        calendar.set(Calendar.YEAR, iYear);
        calendar.set(Calendar.MONTH, iMonth - 1);
        calendar.set(Calendar.DAY_OF_MONTH, iDay);
        calendar.set(Calendar.HOUR_OF_DAY, iHour);
        calendar.set(Calendar.MINUTE, iMin);
        calendar.set(Calendar.SECOND, iSec);

        return calendar.getTime();
    }

    /**
     * 將Date物件轉為 yyyy-MM 字串格式
     *
     * <pre>
     * DateUtils.getYearMonthDateStr(new Date()) = &quot;2024-06&quot;
     * </pre>
     *
     * @param dt
     *            日期物件
     * @return 簡易年月字串
     */
    public static String getYearMonthDateStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }
        return DateFormatUtils.SQL_YEAR_MONTH_FORMAT.format(dt);
    }

    /**
     * 將yyyy-MM字串轉為Date物件
     * 
     * <pre>
     * DateUtils.getYearMonthDate("2024-06") // Date 2024-06
     * </pre>
     *
     * @param str
     *            日期字串
     * @return
     */
    public static Date getYearMonthDate(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return DateFormatUtils.SQL_YEAR_MONTH_FORMAT.parse(str);
        }
        catch (ParseException e) {
            logger.error("error parsing string into year month format, {}", e);
            return null;
        }
    }

    /**
     * 將日期format成字串
     *
     * <pre>
     * DateUtils.getSimpleDateFormatYearMonthStr(Date("2024-06")) // "202406"
     * </pre>
     *
     * @param dt
     *            日期物件
     * @return
     */
    public static String getSimpleDateFormatYearMonthStr(Date dt) {
        if (dt == null)
            return StringUtils.EMPTY;
        return DateFormatUtils.SIMPLE_DATE_FORMAT_YEAR_MONTH.format(dt);
    }

    /**
     * 將字串parse成日物件
     *
     * <pre>
     * DateUtils.getSimpleDateFormatYearMonth("202406"); // Date(2024-06)
     * </pre>
     *
     * @param str
     *            日期字串
     * @return
     */
    public static Date getSimpleDateFormatYearMonth(String str) {
        if (StringUtils.isBlank(str))
            return null;

        try {
            return DateFormatUtils.SIMPLE_DATE_FORMAT_YEAR_MONTH.parse(str);
        }
        catch (ParseException e) {
            logger.error("error parsing string into simple year month date, {}", e);
            return null;
        }
    }

    /**
     * 將日期parse成 yyyy/MM 字串
     *
     * @param dt
     * @return
     */
    public static String getCEYearMonthDateStr(Date dt) {
        if (dt == null) {
            return null;
        }
        return DateFormatUtils.CE_YEAR_MONTH_FORMAT.format(dt);
    }

    /**
     * 將字串yyyy/MM parse成Date物件
     *
     * @param str
     * @return
     */
    public static Date getCEYearMonthDate(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        try {
            return DateFormatUtils.CE_YEAR_MONTH_FORMAT.parse(str);
        }
        catch (ParseException e) {
            logger.error("error parsing string into ce year month date, e", e);
            return null;
        }
    }

    /**
     * 取得簡易ISO格式的日期格式字串
     *
     * <pre>
     * DateUtils.getSimpleDateStr(new Date()) = &quot;20071220&quot;
     * </pre>
     *
     * @param 日期物件
     * @return 簡易的日期格式字串
     *
     */
    public static String getSimpleDateStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }
        return DateFormatUtils.SIMPLE_DATE_FORMAT.format(dt);
    }

    /**
     * 取得簡易ISO格式的時間格式字串
     *
     * <pre>
     * DateUtils.getSimpleTimeStr(new Date()) = &quot;132001&quot;
     * </pre>
     *
     * @param
     * @return 簡易的ISO時間格式字串，日期錯誤時回傳空字串
     *
     */
    public static String getSimpleTimeStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }
        return DateFormatUtils.SIMPLE_TIME_FORMAT.format(dt);
    }

    /**
     * 取得簡易ISO格式的時間(time only) Date物件
     *
     * @param timeStr
     * @return
     */
    public static Date getSimpleTime(String timeStr) {
        if (StringUtils.isBlank(timeStr)) {
            return null;
        }

        try {
            return DateFormatUtils.SIMPLE_TIME_FORMAT.parse(timeStr);
        }
        catch (ParseException e) {
            logger.error("error parsing string into time, e", e);
            return null;
        }
    }

    /**
     * 取得簡易ISO格式的日期和時間字串
     *
     * <pre>
     * DateUtils.getSimpleISODateTimeStr(new Date()) = &quot;yyyyMMddTHHmmss&quot;
     * </pre>
     *
     * @param dt
     * @return
     */
    public static String getSimpleISODateTimeStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }
        return DateFormatUtils.SIMPLE_ISO_DATETIME_FORMAT.format(dt);
    }

    /**
     * 取得ISO格式的日期字串
     *
     * <pre>
     * DateUtils.getSimpleISODateStr(new Date()) = &quot;yyyy-MM-dd&quot;
     * </pre>
     *
     * @param dt
     * @return
     */
    public static String getSimpleISODateStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.format(dt);
    }

    /**
     * 取得CE_DATE格式的日期字串
     *
     * <pre>
     * DateUtils.getISODateStr(new Date()) = &quot;yyyy/MM/dd&quot;
     * </pre>
     *
     * @param dt
     * @return
     */
    public static String getCEDateStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.CE_DATE_FORMAT.format(dt);
    }

    /**
     * 取得日期格式字串，年、月、日之間以分隔子(<code>sLink</code>)連接
     *
     * <pre>
     * DateUtils.getISODateStr(null) = &quot;&quot;
     * DateUtils.getISODateStr(new Date(), &quot;/&quot;) = &quot;yyyy/MM/dd&quot;
     * </pre>
     *
     * @param dt
     * @param sLink
     *            分隔子
     * @return 以分隔子的格式字串，如果傳入日期為<code>null</code>，則回傳空字串
     */
    public static String getDateStr(Date dt, String sLink) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        Calendar ctime = Calendar.getInstance();
        ctime.setTime(dt);

        StringBuilder sb = new StringBuilder();
        sb.append(ctime.get(Calendar.YEAR));
        sb.append(sLink);
        String sMonth = String.valueOf(ctime.get(Calendar.MONTH) + 1);
        sMonth = StringUtils.leftPad(sMonth, 2, "0");
        sb.append(sMonth);
        sb.append(sLink);
        String sDay = String.valueOf(ctime.get(Calendar.DAY_OF_MONTH));
        sDay = StringUtils.leftPad(sDay, 2, "0");
        sb.append(sDay);

        return sb.toString();
    }

    /**
     * 取得ISO格式的時間字串
     *
     * <pre>
     * DateUtils.getISOTimeStr(null) = &quot;&quot;
     * DateUtils.getISOTimeStr(new Date()) = &quot;HH:mm:ss&quot;
     * </pre>
     *
     * @param dt
     * @return ISO格式的時間字串，如果傳入日期為<code>null</code>，則回傳空字串
     */
    public static String getISOTimeStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.ISO_8601_EXTENDED_TIME_FORMAT.format(dt);
    }

    /**
     * 取得ISO格式的日期時間字串
     *
     * <pre>
     * DateUtils.getISODateTimeStr(new Date()) = &quot;yyyy-MM-ddTHH:mm:ss&quot;
     * </pre>
     *
     * @param dt
     * @return 如果傳入日期為<code>null</code>，則回傳空字串
     */
    public static String getISODateTimeStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(dt);
    }

    /**
     * 取得SQL格式的日期時間字串
     *
     * <pre>
     * DateUtils.getSQLDateTimeStr(new Date()) = &quot;yyyy-MM-dd HH:mm:ss&quot;
     * </pre>
     *
     * @param dt
     * @return 如果傳入日期為<code>null</code>，則回傳空字串
     */
    public static String getSQLDateTimeStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.SQL_DATETIME_FORMAT.format(dt);
    }

    /**
     * 將ISO Date字串轉換成 Date
     *
     * <pre>
     * DateUtils.getISODate(&quot;2005-10-10&quot;, &quot;-&quot;) = Date(2005 - 10 - 10)
     * </pre>
     *
     * @param sISODate
     * @param sLink
     * @return
     */
    public static Date getISODate(String sISODate, String sLink) {
        if (StringUtils.isBlank(sISODate)) {
            return null;
        }
        Date dt = null;
        // 有分隔符號
        if (StringUtils.isNotBlank(sLink)) {
            String[] tokens = sISODate.split(sLink);

            if (tokens.length == 3) {

                int iYear = ConvertUtils.str2Int(tokens[0]);
                int iMonth = ConvertUtils.str2Int(tokens[1]);
                int iDay = ConvertUtils.str2Int(tokens[2]);

                dt = DateUtils.getDate(iYear, iMonth, iDay);
            }
        }
        // 沒有分隔符號 YYYYMMDD
        else {

            if (sISODate.length() == 8) {
                int iYear = ConvertUtils.str2Int(sISODate.substring(0, 4));
                int iMonth = ConvertUtils.str2Int(sISODate.substring(4, 6));
                int iDay = ConvertUtils.str2Int(sISODate.substring(6, 8));
                dt = DateUtils.getDate(iYear, iMonth, iDay);
            }
        }
        return dt;
    }

    /**
     * 將ISO DateTime字串轉換成 Date
     *
     * <pre>
     * DateUtils.getISODate(&quot;2005-10-10T12:34:56&quot;, &quot;-&quot;) = Date(2005-10-10T12:34:56)
     * </pre>
     *
     * @param sISODate
     * @param sLink
     * @return
     */
    public static Date getISODateTime(String sISODateTime) {
        if (StringUtils.isBlank(sISODateTime)) {
            return null;
        }

        String sDateTimeLink = "T";
        String sDateLink = "-";
        String sTimeLink = ":";

        boolean isParseOK = true;

        Date dt = null;

        int iYear = 0;
        int iMonth = 0;
        int iDay = 0;
        int iHour = 0;
        int iMin = 0;
        int iSec = 0;

        String[] tokens = sISODateTime.split(sDateTimeLink);

        if (tokens.length == 2) {

            String sDate = tokens[0];
            String sTime = tokens[1];

            String[] dateTokens = sDate.split(sDateLink);

            if (dateTokens.length == 3) {
                iYear = ConvertUtils.str2Int(dateTokens[0]);
                iMonth = ConvertUtils.str2Int(dateTokens[1]);
                iDay = ConvertUtils.str2Int(dateTokens[2]);
            }
            else {
                isParseOK = false;
            }

            String[] timeTokens = sTime.split(sTimeLink);

            if (isParseOK && timeTokens.length == 3) {
                iHour = ConvertUtils.str2Int(timeTokens[0]);
                iMin = ConvertUtils.str2Int(timeTokens[1]);
                iSec = ConvertUtils.str2Int(timeTokens[2]);
            }
            else {
                isParseOK = false;
            }
        }

        if (isParseOK) {
            dt = DateUtils.getDateTime(iYear, iMonth, iDay, iHour, iMin, iSec);
        }

        return dt;
    }

    /**
     * 將ISO Date字串轉換成 Date
     *
     * <pre>
     * DateUtils.getISODate(&quot;2005-10-10&quot;) = Date(2005 - 10 - 10)
     * </pre>
     *
     * @param sISODate
     * @return
     */
    public static Date getISODate(String sISODate) {
        return getISODate(sISODate, ISO_LINK);
    }

    /**
     * 取得民國格式的日期字串
     *
     * <pre>
     * DateUtils.getROCDateStr(new Date()) = &quot;yyy-MM-dd&quot;
     * </pre>
     *
     * @param dt
     * @return
     */
    public static String getROCDateStr(Date dt) {
        return DateUtils.getROCDateStr(dt, DateUtils.ROC_LINK);
    }

    /**
     * 取得民國格式的日期字串，以分隔子連接年、月、日
     *
     * <pre>
     * DateUtils.getROCDateStr(new Date(), &quot;-&quot;) = &quot;yyy-MM-dd&quot;
     * </pre>
     *
     * @param dt
     * @param sLink
     * @return
     */
    public static String getROCDateStr(Date dt, String sLink) {
        if (dt == null) {
            return StringUtils.EMPTY;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);

        // Date
        int iYear = calendar.get(Calendar.YEAR) - 1911;
        String sYear = String.valueOf(iYear);
        sYear = StringUtils.leftPad(sYear, 3, "0");

        int iMonth = calendar.get(Calendar.MONTH) + 1;
        String sMonth = String.valueOf(iMonth);
        sMonth = StringUtils.leftPad(sMonth, 2, "0");

        int iDay = calendar.get(Calendar.DAY_OF_MONTH);
        String sDay = String.valueOf(iDay);
        sDay = StringUtils.leftPad(sDay, 2, "0");

        StringBuilder sb = new StringBuilder();
        sb.append(sYear).append(sLink).append(sMonth).append(sLink).append(sDay);

        return sb.toString();
    }

    /**
     * 取得民國格式的日期字串，以分隔子連接年、月
     * 
     * <pre>
     * <p>DateUtils.getROCYearMonthStr("yyyMM", null) = &quot;yyy年MM月&quot;</p>
     *  <p>DateUtils.getROCYearMonthStr("yyyMM", &quot;-&quot;) = &quot;yyy-MM&quot;</p>
     * </pre>
     * 
     * @param sRocDate
     * @param sLink
     * @return
     */
    public static String getROCYearMonthStr(String sRocDate, String sLink) {
        if (StringUtils.isBlank(sRocDate)) {
            return null;
        }
        StringBuilder dt = new StringBuilder();
        // 有分隔符號
        if (StringUtils.isNotBlank(sLink)) {

            String[] tokens = sRocDate.split(sLink);

            if (tokens.length != 3) {
                return null;
            }

            int iYear = ConvertUtils.str2Int(tokens[0]);
            int iMonth = ConvertUtils.str2Int(tokens[1]);

            dt.append(iYear).append(sLink).append(iMonth);
        }
        // 沒有分隔符號
        else {
            int iLen = sRocDate.length();
            // cccMMdd
            if (iLen == 5) {
                int iMonth = ConvertUtils.str2Int(sRocDate.substring(iLen - 2, iLen));
                int iYear = ConvertUtils.str2Int(sRocDate.substring(0, iLen - 2));
                dt.append(iYear).append("年").append(iMonth).append("月");
            }
        }
        return dt.toString();
    }

    /**
     * 取得民國格式的日期字串，以分隔子連接年、月、日
     *
     * <pre>
     * DateUtils.getROCDateStr(new Date(), &quot;-&quot;) = &quot;yyy-MM-dd&quot;
     * </pre>
     *
     * @param dt
     * @param sLink
     * @return
     */
    public static String getROCDateStrFromCEDateFormat(Date dt) {
        return getROCDateStrIs8(dt, "");
    }

    /**
     * 取得民國格式的日期字串，以分隔子連接年、月、日(八碼，供基金用)
     *
     * <pre>
     * DateUtils.getROCDateStr(new Date(), &quot;-&quot;) = &quot;yyy-MM-dd&quot;
     * </pre>
     *
     * @param dt
     * @param sLink
     * @return
     */
    public static String getROCDateStrIs8(Date dt, String sLink) {
        if (dt == null) {
            return StringUtils.EMPTY;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);

        // Date
        int iYear = calendar.get(Calendar.YEAR) - 1911;
        String sYear = String.valueOf(iYear);
        sYear = StringUtils.leftPad(sYear, 4, "0");

        int iMonth = calendar.get(Calendar.MONTH) + 1;
        String sMonth = String.valueOf(iMonth);
        sMonth = StringUtils.leftPad(sMonth, 2, "0");

        int iDay = calendar.get(Calendar.DAY_OF_MONTH);
        String sDay = String.valueOf(iDay);
        sDay = StringUtils.leftPad(sDay, 2, "0");

        StringBuilder sb = new StringBuilder();
        sb.append(sYear).append(sLink).append(sMonth).append(sLink).append(sDay);

        return sb.toString();
    }

    /**
     * 取得民國格式的日期時間字串
     *
     * <pre>
     * DateUtils.getROCDateTimeStr(new Date) = &quot;yyy/MM/dd HH:mm:ss&quot;;
     * </pre>
     *
     * @param dt
     * @return
     */
    public static String getROCDateTimeStr(Date dt) {
        return DateUtils.getROCDateTimeStr(dt, DateUtils.ROC_LINK);
    }

    /**
     * 取得民國格式的日期時間字串，以分隔子連接年、月、日
     *
     * <pre>
     * DateUtils.getROCDateTimeStr(new Date(), &quot;/&quot;) = &quot;yyy/MM/dd HH:mm:ss&quot;
     * </pre>
     *
     * @param dt
     * @param sLink
     * @return
     */
    public static String getROCDateTimeStr(Date dt, String sLink) {
        String sDate = getROCDateStr(dt, sLink);
        String sTime = getISOTimeStr(dt);

        return sDate + " " + sTime;
    }

    /**
     * 將民國年字串轉換成日期物件(code>Date</code>)
     *
     * <pre>
     * DateUtils.getROCDate(&quot;095/10/10&quot;, &quot;/&quot;) = Date(2005 - 10 - 10)
     * </pre>
     *
     * @param sRocDate
     * @param sLink
     * @return
     */
    public static Date getROCDate(String sRocDate, String sLink) {
        if (StringUtils.isBlank(sRocDate)) {
            return null;
        }
        Date dt = null;
        // 有分隔符號
        if (StringUtils.isNotBlank(sLink)) {

            String[] tokens = StringUtils.split(sRocDate, sLink);

            if (tokens.length != 3) {
                return null;
            }

            int iYear = ConvertUtils.str2Int(tokens[0]) + 1911;
            int iMonth = ConvertUtils.str2Int(tokens[1]);
            int iDay = ConvertUtils.str2Int(tokens[2]);

            dt = DateUtils.getDate(iYear, iMonth, iDay);
        }
        // 沒有分隔符號
        else {
            int iLen = sRocDate.length();
            // 20250618 by Tank 新增能處理ccccMMdd格式的民國年日期
            // cccMMdd or ccccMMdd
            if (iLen == 7 || iLen == 8) {
                int iDay = ConvertUtils.str2Int(sRocDate.substring(iLen - 2));
                int iMonth = ConvertUtils.str2Int(sRocDate.substring(iLen - 4, iLen - 2));
                int iYear = ConvertUtils.str2Int(sRocDate.substring(0, iLen - 4));

                if (iYear > 0) {
                    iYear += 1911;
                    dt = DateUtils.getDate(iYear, iMonth, iDay);
                }
            }
            // cccMM
            else if (iLen == 5) {
                int iMonth = ConvertUtils.str2Int(sRocDate.substring(iLen - 2));
                int iYear = ConvertUtils.str2Int(sRocDate.substring(0, iLen - 2));

                if (iYear > 0) {
                    iYear += 1911;
                    dt = DateUtils.getDate(iYear, iMonth, 1);
                }
            }
        }
        return dt;
    }

    /**
     * 取得簡易格式的日期
     *
     * <pre>
     * DateUtils.getSimpleROCDate(&quot;cccMMdd&quot) = Date(ccc/MM/dd);
     * </pre>
     *
     * @param dt
     * @return
     */
    public static Date getSimpleROCDate(String sRocDate) {
        return getROCDate(sRocDate, "");
    }

    /**
     * 將以民國格式的字串轉成日期物件(<code>Date</code>)
     *
     * <pre>
     * DateUtils.getROCDate(&quot;94/4/18&quot;) = Date(2005 - 4 - 18)
     * </pre>
     *
     * @param sRocDate
     * @return
     */
    public static Date getROCDate(String sRocDate) {
        return getROCDate(sRocDate, ROC_LINK);
    }

    /**
     * 將DateTimeStr字串轉換成 Date
     *
     * <pre>
     * DateUtils.getDateByDateFormat(&quot;20051010123456789&quot;,&quot;yyyyMMddHHmmssSSS&quot;) = Date(2005-10-10 12:34:56789)
     * </pre>
     *
     * <p/>
     * 異常ㄧ律回傳null
     *
     * @param sSimpleDateTimeStr
     * @return
     */
    public static Date getDateByDateFormat(String sDateTimeStr, String sDateFormat) {
        if (StringUtils.isBlank(sDateTimeStr)) {
            return null;
        }

        SimpleDateFormat aDateFormat = null;
        try {
            aDateFormat = new SimpleDateFormat(sDateFormat);
        }
        catch (IllegalArgumentException e) {
            logger.debug("new SimpleDateFormat error: {}", e);
            return null;
        }
        aDateFormat.setLenient(false);
        try {
            return aDateFormat.parse(sDateTimeStr);
        }
        catch (ParseException e) {
            logger.debug("getDateByDateFormat", e);
            return null;
        }
    }

    /**
     * 將 Date物件轉換成String
     *
     * <pre>
     * DateUtils.getDateTimeStrByDateFormat(Date(2005-10-10 12:34:56789),&quot;yyyyMMddHHmmssSSS&quot;) = 20051010123456789
     * </pre>
     *
     * <p/>
     * 異常ㄧ律回傳空字串
     *
     * @param aDt
     * @param sDateTimeFormat
     * @return
     */
    public static String getDateTimeStrByDateFormat(Date aDt, String sDateTimeFormat) {
        if (StringUtils.isBlank(sDateTimeFormat) || aDt == null) {
            return StringUtils.EMPTY;
        }
        try {
            SimpleDateFormat aDateTimeFormat = new SimpleDateFormat(sDateTimeFormat);
            return aDateTimeFormat.format(aDt);
        }
        // fortify : Poor Error Handling: Program Catches NullPointerException
        catch (IllegalArgumentException e) {
            logger.debug("getDateTimeStrByDateFormat", e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 取得簡易格式的日期
     *
     * <pre>
     * DateUtils.getSimpleDate(&quot;yyyyMMdd&quot) = Date(yyyy/MM/dd);
     * </pre>
     *
     * @param dt
     * @return
     */
    public static Date getSimpleDate(String simpleDate) {
        if (null == simpleDate) {
            return null;
        }

        try {
            String sFormat = DateFormatUtils.SIMPLE_DATE_FORMAT.getPattern();

            SimpleDateFormat format = new SimpleDateFormat(sFormat);

            return format.parse(simpleDate);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * 取得日期
     *
     * <pre>
     * DateUtils.getCEDate(&quot;yyyy/MM/dd&quot) = Date();
     * </pre>
     *
     * @param dt
     * @return
     */
    public static Date getCEDate(String ceDate) {
        if (null == ceDate) {
            return null;
        }

        try {
            return DateFormatUtils.CE_DATE_FORMAT.parse(ceDate);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * 取得日期
     *
     * <pre>
     * DateUtils.getCEDatetime(&quot;yyyy/MM/dd HH:mm:ss&quot) = Date();
     * </pre>
     *
     * @param dt
     * @return
     */
    public static Date getCEDatetime(String ceDate) {
        if (null == ceDate) {
            return null;
        }

        try {
            return DateFormatUtils.CE_DATETIME_FORMAT.parse(ceDate);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * 取得簡易格式的日期時間
     *
     * <pre>
     * DateUtils.getSimpleDateTime(new Date()) = &quot;yyyyMMddHHmmss&quot;
     * </pre>
     *
     * @param dt
     * @return
     */
    public static Date getSimpleDateTime(String simpleDateTime) {
        if (null == simpleDateTime) {
            return null;
        }

        try {
            String sFormat = DateFormatUtils.SIMPLE_DATETIME_FORMAT.getPattern();

            SimpleDateFormat format = new SimpleDateFormat(sFormat);

            return format.parse(simpleDateTime);
        }
        catch (ParseException e) {
            return null;
        }
    }

    /**
     * 取得簡易格式的日期時間字串
     *
     * <pre>
     * DateUtils.getSimpleDateTimeStr(new Date()) = &quot;yyyyMMddHHmmss&quot;
     * </pre>
     *
     * @param dt
     * @return
     */
    public static String getSimpleDateTimeStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.SIMPLE_DATETIME_FORMAT.format(dt);
    }

    /**
     * 增減天數 (會將時分秒移除)
     *
     * @param date
     * @param increment
     * @return
     */
    public static Date addDay(Date date, int increment) {
        Calendar cn = ConvertUtils.date2Calendar(date);
        GregorianCalendar gc1 = new GregorianCalendar(cn.get(Calendar.YEAR), cn.get(Calendar.MONTH), cn.get(Calendar.DATE));
        gc1.add(Calendar.DATE, increment);
        return gc1.getTime();
    }

    /**
     * 依據時間單位增減時間<br>
     * 參考 ChronoUnit ：YEARS, MONTHS, WEEKS, DAYS, HOURS, MINUTES
     * 
     * @param date
     * @param unit
     * @param durations
     * @return
     */
    public static Date addDatesByUnit(Date date, ChronoUnit unit, long durations) {
        LocalDateTime ldt = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        ldt = ldt.plus(durations, unit);
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 增減月份 (會將時分秒移除)
     *
     * @param date
     * @param increment
     * @return
     */
    public static Date addMonth(Date date, int increment) {
        Calendar cn = ConvertUtils.date2Calendar(date);
        GregorianCalendar gc1 = new GregorianCalendar(cn.get(Calendar.YEAR), cn.get(Calendar.MONTH), cn.get(Calendar.DATE));
        gc1.add(Calendar.MONTH, increment);
        return gc1.getTime();
    }

    /**
     * 取得DB查詢起日
     *
     * 清除時分秒欄位
     *
     * @param startDate
     * @return
     */
    public static Date getStartDate(Date startDate) {
        Date date = (Date) startDate.clone();
        DateUtils.clearTime(date);
        return date;
    }

    /**
     * 取得系統日
     *
     * 清除時分秒欄位
     *
     * @param startDate
     * @return
     */
    public static Date getToday() {
        Date date = new Date();
        DateUtils.clearTime(date);
        return date;
    }

    /**
     * 取得下一日曆日
     *
     * 清除時分秒欄位
     *
     * @return
     */
    public static Date getNextDay() {
        return addDay(getToday(), 1);
    }

    /**
     * 取得DB查詢迄日
     *
     * 設定為23:59:59
     *
     * @param endDate
     * @return
     */
    public static Date getEndDate(Date endDate) {
        Calendar ca = ConvertUtils.date2Calendar(endDate);
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.MILLISECOND, 999);
        return ConvertUtils.calendar2Date(ca);
    }

    /**
     * 將日期格式(yyyyMMdd)轉成(yyyy/MM/dd)
     *
     * @param date
     * @return
     */
    public static String addDateSlashes(String dateStr) {
        return getDateTimeStrByDateFormat(getSimpleDate(dateStr), "yyyy/MM/dd");
    }

    /**
     * 將日期轉成(yyyyMMdd) string
     *
     * @param date
     * @return
     */
    public static String getSimpleDateFormat(Date date) {
        if (null == date) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.SIMPLE_DATE_FORMAT.format(date);
    }

    /**
     * 取得當月的最後一個日 ex 20120202
     *
     * @param date
     * @return
     */
    public static int getMonthOfLastDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得當月的最後一個日期 ex 20230202 -> 20230228
     * 
     * @param date
     * @return date
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date truncate(Date date, int... fields) {
        Date d = date;

        for (int ii = 0; ii < fields.length; ii++) {
            d = truncate(d, fields[ii]);
        }

        return d;
    }

    /**
     * 將日期轉成(yyyyMMddhhmmssmsmsms) string (毫秒數)
     *
     * @param date
     * @return
     */
    public static String getSimpleAndMsmsmsDateFormat(Date date) {
        if (null == date) {
            return StringUtils.EMPTY;
        }

        String tmp = DateFormatUtils.CLIENTDT_2_FORMAT.format(date);
        tmp = tmp.substring(0, tmp.indexOf('+'));

        return tmp;
    }

    /**
     * 將日期轉成 yyyy/MM/dd HH:mm:ss 格式
     *
     * @param dt
     * @return
     */
    public static String getDateTimeStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.CE_DATETIME_FORMAT.format(dt);
    }

    /**
     * 取得兩個日期的相隔天數，回傳值皆為正值；但如果發生錯誤，則回傳-1。
     *
     * @param beginDate
     *            (date format must be yyyyMMdd)
     * @param endDate
     *            (date format must be yyyyMMdd)
     * @return long
     */
    public static long daysBetween(String beginDate, String endDate) {
        long betweenDate = 0L;

        try {
            Date dBeginDate = DateFormatUtils.SIMPLE_DATE_FORMAT.parse(beginDate);
            Date dEndDate = DateFormatUtils.SIMPLE_DATE_FORMAT.parse(endDate);

            betweenDate = (dEndDate.getTime() - dBeginDate.getTime()) / (1000 * 60 * 60 * 24);
            betweenDate = betweenDate == 0 ? 1 : Math.abs(betweenDate);
        }
        catch (ParseException ex) {
            betweenDate = -1;
        }

        return betweenDate;
    }

    /**
     * 計算兩個日期間的天數, 兩個日期相同時, 傳回 0
     *
     * @author Edward_Tien
     * @param d1
     * @param d2
     * @return
     */
    public static int getDaysBetween(Calendar d1, Calendar d2) {
        Calendar t1 = d1;
        Calendar t2 = d2;
        if (d1.after(d2)) { // 若d1在d2之後, 則兩者互換
            t1 = d2;
            t2 = d1;
        }
        int days = t2.get(Calendar.DAY_OF_YEAR) - t1.get(Calendar.DAY_OF_YEAR);
        int y2 = t2.get(Calendar.YEAR);
        if (t1.get(Calendar.YEAR) != y2) {
            t1 = (java.util.Calendar) t1.clone();
            do {
                days += t1.getActualMaximum(Calendar.DAY_OF_YEAR);
                t1.add(Calendar.YEAR, 1);
            }
            while (t1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 計算兩個日期間隔的月份
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static int getMonthsBetween(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        int diff = 0;
        if (c2.after(c1)) {
            while (c2.after(c1)) {
                c1.add(Calendar.MONTH, 1);
                if (c2.after(c1)) {
                    diff++;
                }
            }
        }
        else if (c2.before(c1)) {
            while (c2.before(c1)) {
                c1.add(Calendar.MONTH, -1);
                if (c1.before(c2)) {
                    diff--;
                }
            }
        }
        return diff;
    }

    /**
     * 獲取兩個日期之前相差月份 (不考慮天數) e.g. 202409, 202408 -> 1
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getMonthsDiff(Date d1, Date d2) {
        LocalDate l1 = d1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate l2 = d2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) Math.abs(ChronoUnit.MONTHS.between(l1, l2));
    }

    /**
     * 計算兩個日期間的天數
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static int getDaysBetween(Date d1, Date d2) {
        Calendar c1 = changeDateToCalendar(d1);
        Calendar c2 = changeDateToCalendar(d2);
        return getDaysBetween(c1, c2);
    }

    /**
     * 取2個Date之間的年差 After java8
     * 
     * @param d1
     * @param d2
     * @return
     */
    public static int getYearsBetween(Date d1, Date d2) {
        return DateUtils.getDateUnitBetween(ChronoUnit.YEARS, d1, d2);
    }

    /**
     * 傳入時間單位以及2個時間，判斷該時間單位長度<br>
     * 參考 ChronoUnit ：YEARS, MONTHS, WEEKS, DAYS, HOURS, MINUTES
     * 
     * @param unit
     * @param d1
     * @param d2
     * @return
     */
    public static int getDateUnitBetween(ChronoUnit unit, Date d1, Date d2) {
        if (null == unit || null == d1 || null == d2) {
            return 0;
        }
        if (d1.after(d2)) {
            Date tmpDate = d1;
            d1 = d2;
            d2 = tmpDate;
        }
        LocalDateTime ldt1 = LocalDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault());
        LocalDateTime ldt2 = LocalDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault());

        return (int) unit.between(ldt1, ldt2);
    }

    public static int calculateAge(Date birthday) {
        return calculateAge(birthday, new Date());
    }

    /**
     * 計算年紀 年齡 計算到日
     * 
     * @param birthDate
     * @param currentDate
     * @return
     */
    public static int calculateAge(Date birthDate, Date currentDate) {
        if (birthDate != null && currentDate != null) {
            LocalDate btdate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate crdate = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return Period.between(btdate, crdate).getYears();
        }
        else {
            return 0;
        }
    }

    /**
     * 轉換 Date 為 java.util.Calendar
     *
     * @author Edward_Tien
     * @param date
     *            日期
     * @return
     */
    public static Calendar changeDateToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * Date string change to another formated string.
     *
     * <pre>
     * DateUtils.changeFormat("20170101", "yyyyMMdd", "yyyy/MM/dd") = "2017/01/01"
     * </pre>
     *
     * @param dateStr
     * @param fromFormat
     * @param toFormat
     * @return formated date string
     */
    public static String changeFormat(String dateStr, String fromFormat, String toFormat) {
        SimpleDateFormat fromFormater = new SimpleDateFormat(fromFormat);
        fromFormater.setLenient(false);
        SimpleDateFormat toFormater = new SimpleDateFormat(toFormat);
        toFormater.setLenient(false);

        try {
            Date date = fromFormater.parse(dateStr);
            return toFormater.format(date);
        }
        catch (ParseException e) {
            logger.error("DateUtils.changeFormat", e);
            return dateStr;
        }
    }

    /**
     * 季度
     *
     * @param dateStr
     * @param pattern
     * @return
     */
    public static String getQuarter(String dateStr, String pattern) {
        Date date = getDateByDateFormat(dateStr, pattern);
        Calendar calendar = changeDateToCalendar(date);
        int month = calendar.get(Calendar.MONTH);
        BigDecimal b = new BigDecimal(month + 1).divide(new BigDecimal("3"), 0, RoundingMode.UP);
        return b.toString();
    }

    /**
     * 將傳入的所選日轉成01相間的長度31的字串 [02, 12, 30] => 010000000001000000000000000010
     *
     * @param dates
     * @return
     * @throws ActionException
     */
    public static String convertToPmtDt(List<Integer> dates) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 31; i++) {
            String s = dates.contains(i) ? "1" : "0";
            sb.append(s);
        }

        return sb.toString();
    }

    /**
     * 檢核 checkDate是否在 dateStart和 dateEnd之間
     * 
     * @param checkDate
     * @param dateStart
     * @param dateEnd
     * @return
     */
    public static boolean between(Date checkDate, Date dateStart, Date dateEnd) {
        if (checkDate != null && dateStart != null && dateEnd != null) {
            if (checkDate.after(dateStart) && checkDate.before(dateEnd)) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    public static int getMonthIntFromDate(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    /**
     * 判斷是否為合法日期
     * 
     * @param inputDate
     * @param format
     * @return
     * @throws ParseException
     */
    public static boolean isValidDateStr(String inputDate, String format) {
        if (StringUtils.isBlank(inputDate)) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(inputDate);
            String newDateStr = sdf.format(date);
            return StringUtils.equals(inputDate, newDateStr);
        }
        catch (ParseException e) {
            logger.warn("error parsing inputDate: {} by {}", inputDate, format);
        }
        return false;
    }

    /**
     * 計算，指定日期是整年度的第幾天
     * 
     * @param date
     * @return
     */
    public static int getDayOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        LocalDate localDate = LocalDate.of(cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH) + 1), cal.get(Calendar.DAY_OF_MONTH));
        return localDate.getDayOfYear();
    }

    /**
     * 中文=>{西元年}+年+{月份}+月+{日}+日
     * 
     * 英文=>{西元年}+/+{月份}+/+{日}
     * 
     * @param date
     * @return
     */
    public static String formatDateToChinese(Date date, Locale locale) {
        if (StringUtils.equals(locale.toString(), Locale.TAIWAN.toString())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            return dateFormat.format(date);
        }
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return dateFormat.format(date);
        }

    }

    /**
     * 中文=>{西元年}+年+{月份}+月+{日}+日
     * 
     * 英文=>{西元年}+/+{月份}+/+{日}
     * 
     * @param date
     * @return
     */
    public static String formatDateToChinese(Date date, String locale) {
        if (StringUtils.equals(locale, Locale.TAIWAN.toString())) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
            return dateFormat.format(date);
        }
        else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            return dateFormat.format(date);
        }

    }

    /**
     * 取得yyyy/MM/dd HH:mm的日期時間字串
     * 
     * @param dt
     * @return 如果傳入日期為<code>null</code>，則回傳空字串
     */
    public static String getCeDateTimeFormatWithoutSec(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }

        return DateFormatUtils.CE_DATETIME_FORMAT_WITHOUT_SEC.format(dt);
    }

    /**
     * 取的當月第一個日期
     * 
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 起迄日是否大於 n 個月
     * 
     * @param startDate
     * @param endDate
     * @param n
     * @return
     */
    public static boolean isMoreThanMonths(Date startDate, Date endDate, int n) {
        // 使用 Calendar 計算差距
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        int startYear = startCalendar.get(Calendar.YEAR);
        int startMonth = startCalendar.get(Calendar.MONTH);

        int endYear = endCalendar.get(Calendar.YEAR);
        int endMonth = endCalendar.get(Calendar.MONTH);

        // 計算差距的月份
        int diffMonths = (endYear - startYear) * 12 + (endMonth - startMonth);

        return diffMonths > n;
    }

    /**
     * 時間是否在 startTimeStr 和 endTimeStr 之間
     * 
     * @param startTimeStr
     * @param endTimeStr
     * @param dateTimeToCheck
     * @return
     * @throws ParseException
     */
    public static boolean isWithinTimeRange(String startTimeStr, String endTimeStr, Date dateTimeToCheck) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        try {
            Date timeToCheck = timeFormat.parse(timeFormat.format(dateTimeToCheck));
            Date startTime = timeFormat.parse(startTimeStr);
            Date endTime = timeFormat.parse(endTimeStr);
            return timeToCheck.after(startTime) && timeToCheck.before(endTime);
        }
        catch (ParseException e) {
            logger.error(e.getLocalizedMessage());
        }
        return false;
    }

    /**
     * 時間是否在startTimeStr 和 endTimeStr 之間 並將跨日條件考慮進去
     *
     * @param startTimeStr
     *            starting time HH:mm
     * @param endTimeStr
     *            ending time HH:mm
     * @param dateTimeToCheck
     *            usually current time
     * @return returns true if time in range
     */
    public static boolean isBetweenTimeRange(String startTimeStr, String endTimeStr, Date dateTimeToCheck) {
        String timeRegex = "[0-2]\\d:[0-5]\\d";
        if (!Pattern.matches(timeRegex, startTimeStr) || !Pattern.matches(timeRegex, endTimeStr)) {
            logger.error("startTimeStr or endTimeStr not in proper format! startTimeStr: {}, endTimeStr: {}", startTimeStr, endTimeStr);
            return false;
        }
        String[] startArr = startTimeStr.split(":");
        String[] endArr = endTimeStr.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTimeToCheck);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        if ((hour == Integer.parseInt(startArr[0]) && min == Integer.parseInt(startArr[1])) || (hour == Integer.parseInt(endArr[0]) && min == Integer.parseInt(endArr[1])))
            return true;

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(startArr[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(startArr[1]));
        Date startTime = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(endArr[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(endArr[1]));
        Date endTime = calendar.getTime();

        calendar.setTime(dateTimeToCheck);
        Date now = calendar.getTime();
        if (startTime.getTime() > endTime.getTime()) {
            calendar.setTime(endTime);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            endTime = calendar.getTime();
        }

        return !now.before(startTime) && !now.after(endTime);
    }

    /**
     * 取得主機Txmsrn的日期格式字串
     *
     * <pre>
     * DateUtils.getSimpleDateStr(new Date()) = &quot;20071220&quot;
     * </pre>
     *
     * @param 日期物件
     * @return 簡易的日期格式字串
     *
     */
    public static String getTxmsrnDateStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }
        return DateFormatUtils.TXMSRN_FORMATTER.format(dt);
    }

    /**
     * 取得兩個時間的相隔分鐘，回傳值皆為正值；但如果發生錯誤，則回傳-1。
     *
     * @param beginDate
     * @param endDate
     * @return long
     */
    public static long getMinuteBetween(Date beginDate, Date endDate) {
        long betweenDate = (beginDate.getTime() - endDate.getTime()) / 1000 / 60;
        return betweenDate;
    }

    /**
     * 取的日期西元年
     * 
     * @param date
     * @return
     */
    public static int getYearIntFromDate(Date date) {
        // 將Date轉換為LocalDate
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return localDate.getYear();
    }

    /**
     * 合併日期和時間的方法
     * 
     * @param date
     * @param time
     * @return
     */
    public static Date mergeDateAndTime(Date date, String time) {
        if (date == null || time == null) {
            return new Date();
        }
        String dateTime = DateUtils.getISODateTimeStr(date);

        String newDateTime = StringUtils.substring(dateTime, 0, 11) + time;

        return DateUtils.getISODateTime(newDateTime);
    }

    /**
     * 把Date 轉成 LocalDateTime
     * 
     * @param dateToConvert
     * @return
     */
    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        if (null == dateToConvert)
            return null;

        return Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 將民國年字串轉換成日期物件(code>java.util.Date</code>)
     *
     * <pre>
     * DateUtils.getROCDateFormat(&quot;0951010&quot;, &quot;cccMMdd&quot;) = Date(2005 - 10 - 10)
     * </pre>
     *
     * @param sRocDate
     * @param sLink
     * @return
     */
    public static Date getROCDateFormat(String sRocDate, String format) {
        if (StringUtils.isBlank(sRocDate) || StringUtils.isBlank(format)) {
            return null;
        }

        Date dt = null;

        int y = 0;
        int m = 0;
        int d = 0;

        if (format.indexOf("cccc") >= 0) {
            y = 4;
        }
        else if (format.indexOf("ccc") >= 0) {
            y = 3;
        }
        else if (format.indexOf("cc") >= 0) {
            y = 2;
        }

        if (format.indexOf("MM") >= 0) {
            m = 2;
        }

        if (format.indexOf("dd") >= 0) {
            d = 2;
        }

        int iYear = ConvertUtils.str2Int(sRocDate.substring(0, y)) + 1911;
        int iMonth = ConvertUtils.str2Int(sRocDate.substring(y, y + m));
        int iDay = ConvertUtils.str2Int(sRocDate.substring(y + m, y + m + d));

        dt = DateUtils.getDate(iYear, iMonth, iDay);

        return dt;
    }

    /**
     * 檢查是否在時間區間
     * 
     * @param checkTime
     * @param startTime
     * @param endTime
     * @return
     */
    public static Boolean checkBetweenTime(String checkTime, String startTime, String endTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime currentTime = LocalTime.parse(checkTime, formatter);
            LocalTime startTimeParsed = LocalTime.parse(startTime, formatter);
            LocalTime endTimeParsed = LocalTime.parse(endTime, formatter);

            if (endTimeParsed.isBefore(startTimeParsed)) {
                logger.debug("over day");
                logger.debug("checkTime " + currentTime);
                logger.debug("start " + startTimeParsed);
                logger.debug("end " + endTimeParsed);
                logger.debug("after start " + currentTime.isAfter(startTimeParsed));
                logger.debug("before end " + currentTime.isBefore(endTimeParsed));
                // 跨越日期的情況
                return currentTime.isAfter(startTimeParsed) || currentTime.isBefore(endTimeParsed);
            }
            else {
                logger.debug("not over day");
                // 同一日期的情況
                return currentTime.isAfter(startTimeParsed) && currentTime.isBefore(endTimeParsed);
            }
        }
        catch (DateTimeParseException e) {
            logger.error(e.getMessage());
            return false;
        }

    }

    /**
     * targetDate 與 compareDate 比較，當 targetDate 小於 compareDate 時 返回 true
     * 
     * @param date
     * @return
     */
    public static boolean isBefore(Date targetDate, Date compareDate) {
        if (targetDate == null || compareDate == null) {
            return false;
        }

        return targetDate.before(compareDate);
    }

    /**
     * 轉換 HH:mm:ss
     * 
     * @param dt
     * @return
     */
    public static String getCeTimeStr(Date dt) {
        if (null == dt) {
            return StringUtils.EMPTY;
        }
        return DateFormatUtils.CE_TIME_FORMAT.format(dt);
    }

    /**
     * 取得月份縮寫, e.g. 1 => Jan
     *
     * @param monthInt
     * @param localeStr
     * @return
     */
    public static String getMonthShortName(Integer monthInt, String localeStr) {
        Locale locale = LocaleUtils.toLocale(localeStr);
        return getMonthShortName(monthInt, locale);
    }

    /**
     * 取得月份縮寫, e.g. 1 => Jan
     *
     * @param monthInt
     * @param locale
     * @return
     */
    public static String getMonthShortName(Integer monthInt, Locale locale) {
        if (Locale.TAIWAN.equals(locale) || Locale.CHINA.equals(locale)) {
            return String.valueOf(monthInt);
        }
        return Month.of(monthInt).getDisplayName(TextStyle.SHORT_STANDALONE, locale);
    }

    /**
     * 取得月份全名, e.g. 1 => January
     *
     * @param monthInt
     * @param locale
     * @return
     */
    public static String getMonthLongName(Integer monthInt, Locale locale) {
        if (Locale.TAIWAN.equals(locale) || Locale.CHINA.equals(locale)) {
            return String.valueOf(monthInt);
        }
        return Month.of(monthInt).getDisplayName(TextStyle.FULL_STANDALONE, locale);
    }

    /**
     * 計算兩個時間的秒數, 兩個時間相同時, 傳回 0
     *
     * @author Edward_Tien
     * @param d1
     * @param d2
     * @return
     */
    public static long getSecondsBetween(Date d1, Date d2) {
        Calendar t1 = changeDateToCalendar(d1);
        Calendar t2 = changeDateToCalendar(d2);
        return getSecondsBetween(t1, t2);
    }

    /**
     * 計算兩個時間的秒數, 兩個時間相同時, 傳回 0
     *
     * @author Edward_Tien
     * @param d1
     * @param d2
     * @return
     */
    public static long getSecondsBetween(Calendar d1, Calendar d2) {
        Calendar t1 = d1;
        Calendar t2 = d2;
        if (d1.after(d2)) { // 若d1在d2之後, 則兩者互換
            t1 = d2;
            t2 = d1;
        }
        return (t2.getTimeInMillis() - t1.getTimeInMillis()) / 1000;
    }
    
    /**
     * 客戶已簽推介：顯示推薦標的，每7天輪播1檔(依DB上傳順序輪播。每月1~7號第一檔，每月8~14號第二檔
     * return 輪播區段
     * @param startDate 活動開始時間
     * @param endDate 活動結束時間
     * @param dayInterval 間隔時間
     * @return
     */
    public static int getIntervalNumber(Date startDate,Date endDate,Integer dayInterval){
    	LocalDateTime current = convertToLocalDateTime(startDate);
    	LocalDateTime today = LocalDateTime.now();
    	int intervalNum = 0;
    	while(!current.isAfter(convertToLocalDateTime(endDate))){
    	    //從開始時間+輪播的天數
    		current = current.plusHours(dayInterval*24);
    		 //如果當日<輪播時間，則退出循環
    		if(today.isBefore(current)){
    			break;
    		}
    		intervalNum++;
    	}
    	return intervalNum;
    }
    
}
