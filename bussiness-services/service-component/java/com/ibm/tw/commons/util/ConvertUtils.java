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

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import com.ibm.tw.commons.log.IBLog;

/**
 * 型別轉換工具集
 * <p>
 * 型別轉換工具集主要功能繼承自{@link org.apache.commons.beanutils.ConvertUtils}， 並增加下列功能：
 * <ul>
 * <li>根據JDBC Type Name 轉換字串值</li>
 * <li>MQ CCSID -> Encoding之轉換</li>
 * <li>各種日期物件間的轉換</li>
 * <li>十六進位字串和Bye Array資料間的轉換</li>
 * <li>字串和數字間的轉換(<code>null</code> safe)</li>
 * <li>字串和BigDecimal間的轉換(<code>null</code> safe)</li>
 * </ul>
 * </p>
 *
 * @author Jeff Liu
 * @version 1.0, 2007/10/19
 * @see {@link org.apache.commons.beanutils.ConvertUtils}
 * @since
 */
public class ConvertUtils extends org.apache.commons.beanutils.ConvertUtils {

    /** 負號 */
    public static final String NEGATIVE_SIGN = "-";

    /** common logger */
    protected static IBLog logger = IBLog.getLog(ConvertUtils.class);

    /**
     * 將byte array資料轉為十六進位字串
     *
     * <pre>
     * ConvertUtils.byteArrayToHexString([0x01, 0x02]) = &quot;0102&quot;
     * </pre>
     *
     * @param raw
     *            待轉換之bye array
     * @return 十六進位字串
     */
    public static String byteArray2HexString(byte[] raw) {
        StringBuilder sHex = new StringBuilder("");

        if (raw == null) {
            return sHex.toString();
        }

        for (byte element : raw) {

            sHex.append(byte2HexString(element));
        }
        return sHex.toString();
    }

    /**
     * 將Byte資料轉換為十六進位字串
     *
     * <pre>
     * ConvertUtils.byte2Hex(0xab) = &quot;ab&quot;
     * ConvertUtils.byte2Hex(0x01) = &quot;01&quot;
     * </pre>
     *
     * @param raw
     * @return
     */
    public static String byte2HexString(byte raw) {

        StringBuilder sHex = new StringBuilder("");
        int iByte = (raw & 0xF0) >> 4;
        sHex.append(Character.forDigit(iByte, 16));
        iByte = raw & 0x0F;
        sHex.append(Character.forDigit(iByte, 16));
        return sHex.toString();

    }

    /**
     * 將long value轉成 十六進位字串
     *
     * <pre>
     * ConvertUtils.longToHexString(1234) = &quot;04D2&quot;
     * ConvertUtils.longToHexString(1) = &quot;01&quot;
     * </pre>
     *
     * @param lValue
     * @return
     */
    public static String long2HexString(long lValue) {
        StringBuilder sb = new StringBuilder(Long.toHexString(lValue));
        if (sb.length() % 2 != 0) {
            sb.insert(0, '0');
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 十六進位字串轉成 byte array
     *
     * <pre>
     * ConvertUtils.hexStringToByteArray(&quot;1234&quot;) = [0x12, 0x34]
     * ConvertUtils.hexStringToByteArray(&quot;0x2e) = [0x2E]
     * ConvertUtils.hexStringToByteArray(&quot;x'2e') = [0x2E]
     * ConvertUtils.hexStringToByteArray(&quot;&quot;) = []
     * ConvertUtils.hexStringToByteArray(&quot;1&quot;) = null
     * ConvertUtils.hexStringToByteArray(&quot;gg&quot;) = null
     * </pre>
     *
     * @param data
     *            十六進位字串
     * @return 十六進位所代表的Bye Array
     */
    public static byte[] hexString2ByteArray(String data) {

        if (StringUtils.isBlank(data)) {
            return new byte[0];
        }

        String str = data;
        // x'2E' format => 2E
        if (str.startsWith("x") || str.startsWith("X")) {
            int start = str.indexOf('\'');
            int end = str.lastIndexOf('\'');
            str = str.substring(start + 1, end);
        }
        // delete leading "0x" or "0X"
        // "0x2f" => "2f
        else if (str.startsWith("0x") || str.startsWith("0X")) {
            str = str.substring(2);
        }

        if (str.length() % 2 != 0) {
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }

        byte[] raw = new byte[str.length() / 2];

        try {
            for (int i = 0; i < str.length() / 2; i++) {

                raw[i] = (byte) Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);

            }
        }
        catch (NumberFormatException e) {

            raw = null;
        }
        return raw;
    }

    /**
     * get HEX value
     *
     * <pre>
     * ConvertUtils.getHexValue('9') = 9
     * ConvertUtils.getHexValue('A') = 10
     * ConvertUtils.getHexValue('G')= Exception
     * </pre>
     *
     * @param c
     * @return HEX Character所表示的數值
     * @throws Exception
     *             非HEX的字元
     */
    public static int getHexValue(char c) throws IllegalArgumentException {

        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - 'A' + 10;
        }

        if (c >= 'a' && c <= 'f') {
            return c - 'a' + 10;
        }

        throw new IllegalArgumentException("char '" + c + "' is not valid hex character!");
    }

    /**
     * convert java.util.Date to java.sql.Date
     *
     * @param dt
     * @return
     */
    public static java.sql.Date date2SQLDate(java.util.Date dt) {

        if (dt == null) {
            return null;
        }

        return new java.sql.Date(dt.getTime());
    }

    /**
     * convert java.sql.Date to java.util.Date
     *
     * @param sdt
     * @return
     */
    public static java.util.Date sqlDate2Date(java.sql.Date sdt) {

        if (sdt == null) {
            return null;
        }

        return new java.util.Date(sdt.getTime());
    }

    /**
     * Convert java.util.Canlendar to java.util.Date
     *
     * @param calendar
     * @return
     */
    public static java.util.Date calendar2Date(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        else {
            return calendar.getTime();
        }
    }

    /**
     * Convert java.util.Canlendar to java.util.Date
     * <p>
     * 將傳入的 Calendar 分別取年月日、時分秒，組成 java.util.Date
     * </p>
     *
     * @param date
     * @param time
     * @return
     */
    public static java.util.Date calendar2Time(Calendar date, Calendar time) {
        if (date == null || time == null) {
            return null;
        }
        else {
            time.set(Calendar.YEAR, date.get(Calendar.YEAR));
            time.set(Calendar.MONTH, date.get(Calendar.MONTH));
            time.set(Calendar.DAY_OF_MONTH, date.get(Calendar.DAY_OF_MONTH));
            return time.getTime();
        }
    }

    /**
     * Convert java.util.Canlendar to java.util.Date
     * <p>
     * 僅取時分秒，年月日預設為 1970/01/01
     * </p>
     *
     * @param time
     * @return
     */
    public static java.util.Date calendar2Time(Calendar time) {
        if (time == null) {
            return null;
        }
        else {
            time.set(Calendar.YEAR, 1970);
            time.set(Calendar.MONTH, Calendar.JANUARY);
            time.set(Calendar.DAY_OF_MONTH, 1);
            return time.getTime();
        }
    }

    /**
     * Convert java.util.Canlendar to java.util.Date
     * 
     * @param calendarByDate
     * @param calendarByTime
     * @return
     */
    public static java.util.Date calendar2DateTime(Calendar calendarByDate, Calendar calendarByTime) {
        if (calendarByDate == null && calendarByTime == null) {
            return null;
        }
        else {
            Calendar newCalendar = Calendar.getInstance();
            if (calendarByDate == null) {
                newCalendar.set(Calendar.YEAR, 1970);
                newCalendar.set(Calendar.MONTH, Calendar.JANUARY);
                newCalendar.set(Calendar.DATE, 1);
                newCalendar.set(Calendar.HOUR, 0);
                newCalendar.set(Calendar.MINUTE, 0);
                newCalendar.set(Calendar.SECOND, 0);
            }
            else {
                newCalendar = calendarByDate;
            }
            if (calendarByTime != null) {
                newCalendar.set(Calendar.HOUR_OF_DAY, calendarByTime.get(Calendar.HOUR_OF_DAY));
                newCalendar.set(Calendar.MINUTE, calendarByTime.get(Calendar.MINUTE));
                newCalendar.set(Calendar.SECOND, calendarByTime.get(Calendar.SECOND));
            }
            return newCalendar.getTime();
        }
    }

    /**
     * Convert java.util.Date to java.util.Calendar
     *
     * @param dt
     * @return
     */
    public static Calendar date2Calendar(java.util.Date dt) {
        if (null == dt) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dt);

        return calendar;
    }

    /**
     * 將字串轉換為數字，如果轉換失敗則回傳預設值
     * <ul>
     * <li>ConvertUtils.str2Int(null) = 0</li>
     * <li>ConvertUtils.str2Int("abc") = 0</li>
     * <li>ConvertUtils.str2Int("1") = 1</li>
     * </ul>
     *
     * @param sValue
     *            待轉換之字串
     * @param iDefaultValue
     *            轉換失敗時之預設值
     * @return 轉換後之數字，失敗則傳回iDefaultValue
     *
     */
    public static int str2Int(String sValue, int iDefaultValue) {

        String str = StringUtils.trim(sValue);

        // add by wayne 去除"*"
        str = StringUtils.remove(str, "*");

        int iValue = iDefaultValue;
        try {
            iValue = Integer.parseInt(str);
        }
        catch (NumberFormatException e) {
            logger.debug("str2Int", e);
        }

        return iValue;
    }

    /**
     * 將字串轉成Integer
     *
     * @param sValue
     *            待轉換的字串
     * @return 轉換後的數字，如果無法轉換時回傳0
     */
    public static int str2Int(String sValue) {
        return ConvertUtils.str2Int(sValue, 0);
    }

    /**
     * 將字串轉成Long
     *
     * @param value
     * @return
     */
    public static Long str2Long(String value) {
        String str = StringUtils.defaultString(value).trim();

        Long retValue = Long.valueOf(0);

        if (!NumberUtils.isCreatable(str) || StringUtils.isBlank(str)) {
            return retValue;
        }

        try {
            retValue = Long.parseLong(str);
        }
        catch (NumberFormatException e) {
            logger.trace("cannot parse long value = " + str, e);
        }

        return retValue;
    }

    /**
     * convert big decimal to string，四捨五入
     *
     * <pre>
     * bigDecimal2Str(null, 2, &quot;2&quot;) = &quot;2&quot;
     * bigDecimal2Str(2.12, 1, &quot;&quot;) = &quot;2.1&quot;
     * bigDecimal2Str(2.15, 1, &quot;&quot;) = &quot;2.2&quot;
     * </pre>
     *
     * @param value
     *            待設定之BigDecimal Value
     * @param iScale
     *            小數有效位數
     * @param sDefaultValue
     *            預設值
     * @return 採用四捨五入制，發生異常時回傳預設值sDefaultValue, never null
     */
    public static String bigDecimal2Str(BigDecimal value, int iScale, String sDefaultValue) {

        if (null == value) {
            return StringUtils.defaultString(sDefaultValue);
        }

        BigDecimal amount = value;
        if (iScale >= 0) {
            amount = NumberUtils.setScale(value, iScale);
        }

        return amount.toString();
    }

    /**
     * convert big decimal to string，自行指定進位方式
     *
     * <pre>
     * bigDecimal2Str(null, 2, &quot;2&quot;) = &quot;2&quot;
     * bigDecimal2Str(2.12, 1, &quot;&quot;) = &quot;2.1&quot;
     * bigDecimal2Str(2.15, 1, &quot;&quot;) = &quot;2.2&quot;
     * </pre>
     * 
     * @param value
     * @param iScale
     * @param roundingMode
     * @param sDefaultValue
     * @return
     */
    public static String bigDecimal2Str(BigDecimal value, int iScale, RoundingMode roundingMode, String sDefaultValue) {

        if (null == value) {
            return StringUtils.defaultString(sDefaultValue);
        }

        BigDecimal amount = value;
        if (iScale >= 0) {
            amount = NumberUtils.setScale(value, iScale, roundingMode);
        }

        return amount.toString();
    }

    // RoundingMode

    /**
     * convert big decimal to string
     *
     * <pre>
     * bigDecimal2Str(null, 2) = &quot;&quot;
     * bigDecimal2Str(2.12, 1) = &quot;2.1&quot;
     * bigDecimal2Str(2.15, 1) = &quot;2.2&quot;
     * </pre>
     *
     * @param value
     *            待設定之BigDecimal Value
     * @param iScale
     *            小數有效位數
     *
     * @return 採用四捨五入制，發生異常時回傳預設值""
     */
    public static String bigDecimal2Str(BigDecimal value, int iScale) {
        return bigDecimal2Str(value, iScale, "");
    }

    public static String bigDecimal2PureDigitalStr(BigDecimal value, int iScale) {
        String tmp = bigDecimal2Str(value, iScale);
        if (StringUtils.isNotBlank(tmp)) {
            tmp = tmp.replaceAll(",", "");
            tmp = tmp.replaceAll("\\.", "");
        }
        return tmp;
    }

    /**
     * convert big decimal to string
     * <ul>
     * <li>bigDecimal2Str(null) = ""</li>
     * <li>bigDecimal2Str(2.12) = "2.12"</li>
     * <li>bigDecimal2Str(2.15) = "2.15"</li>
     * </ul>
     *
     * @param value
     * @return
     */
    public static String bigDecimal2Str(BigDecimal value) {
        return bigDecimal2Str(value, -1, "");
    }

    /**
     * 將字串轉換成 BigDecimal
     * 
     * @param sValue
     * @param iScale
     * @param defaultValue
     * @return
     */
    public static BigDecimal str2BigDecimal(String sValue, int iScale, BigDecimal defaultValue) {
        return str2BigDecimal(sValue, iScale, defaultValue, RoundingMode.HALF_UP);
    }

    /**
     * convert string to big decimal
     *
     * <pre>
     *
     * str2BigDecimal(null, 1, 0) = 0
     * str2BigDecimal(&quot;AA&quot;, 1, 0) = 0
     * str2BigDecimal(&quot;2.12&quot;, 1, 0) = 2.1
     * str2BigDecimal(&quot;2.15&quot;, 1, 0) = 2.2
     * </pre>
     *
     * @param sValue
     * @param iScale
     * @param defaultValue
     * @param roundingMode
     * @return
     */
    public static BigDecimal str2BigDecimal(String sValue, int iScale, BigDecimal defaultValue, RoundingMode roundingMode) {

        if (StringUtils.isBlank(sValue)) {
            return defaultValue;
        }

        try {
            String str = StringUtils.trim(sValue);
            // Leo: 去除","
            str = StringUtils.remove(str, ",");
            // add by wayne 去除"$"
            str = StringUtils.remove(str, "$");
            // add by wayne 去除"*"
            str = StringUtils.remove(str, "*");

            str = StringUtils.trim(str);

            if (StringUtils.isBlank(str) || !NumberUtils.isParsable(str)) {
                return defaultValue;
            }

            BigDecimal value = new BigDecimal(str);

            if (iScale >= 0) {
                value = value.setScale(iScale, roundingMode);
            }

            return value;
        }
        catch (Exception e) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.debug("轉換失敗，不影響程序。");
            logger.warn("str2BigDecimal: " + e.getMessage(), e);

            return defaultValue;
        }
    }

    /**
     * convert string to big decimal
     * <ul>
     * <li>str2BigDecimal(null) = 0</li>
     * <li>str2BigDecimal("AA") = 0</li>
     * <li>str2BigDecimal("2.12") = 2.12</li>
     * </ul>
     *
     * @param sSign
     * @param sValue
     * @return
     */
    public static BigDecimal str2BigDecimal(String sSign, String sValue) {
        BigDecimal aValue = str2BigDecimal(sValue, -1, new BigDecimal(0));
        if (StringUtils.equals(sSign, NEGATIVE_SIGN)) {
            aValue = aValue.multiply(new BigDecimal("-1"));
        }
        return aValue;
    }

    /**
     * convert string to big decimal
     * <ul>
     * <li>str2BigDecimal(null) = 0</li>
     * <li>str2BigDecimal("AA") = 0</li>
     * <li>str2BigDecimal("2.12") = 2.12</li>
     * </ul>
     *
     * @param sSign
     * @param sValue
     * @param scale
     * @return
     */
    public static BigDecimal str2BigDecimal(String sSign, String sValue, int scale) {
        BigDecimal aValue = str2BigDecimal(sValue, scale, new BigDecimal(0));
        if (StringUtils.equals(sSign, NEGATIVE_SIGN)) {
            aValue = aValue.multiply(new BigDecimal("-1"));
        }
        return aValue;
    }

    /**
     * convert string to big decimal
     * <ul>
     * <li>str2BigDecimal(null) = 0</li>
     * <li>str2BigDecimal("AA") = 0</li>
     * <li>str2BigDecimal("2.12") = 2.12</li>
     * </ul>
     *
     * @param sValue2Convert
     * @param scale
     * @return
     */
    public static BigDecimal pureDigiStr2BigDecimal(String sValue2Convert, int scale) {
        return pureDigiStr2BigDecimal("+", sValue2Convert, scale, BigDecimal.ZERO);
    }

    /**
     * convert string to big decimal
     * <ul>
     * <li>str2BigDecimal(null) = 0</li>
     * <li>str2BigDecimal("AA") = 0</li>
     * <li>str2BigDecimal("2.12") = 2.12</li>
     * </ul>
     *
     * @param sSign
     * @param sValue2Convert
     * @param scale
     * @return
     */
    public static BigDecimal pureDigiStr2BigDecimal(String sSign, String sValue2Convert, int scale) {
        return pureDigiStr2BigDecimal(sSign, sValue2Convert, scale, BigDecimal.ZERO);
    }

    /**
     * convert string to big decimal
     * <ul>
     * <li>str2BigDecimal(null) = 0</li>
     * <li>str2BigDecimal("AA") = 0</li>
     * <li>str2BigDecimal("2.12") = 2.12</li>
     * </ul>
     *
     * @param sSign
     * @param sValue2Convert
     * @param scale
     * @param defaultValue
     * @return
     */
    public static BigDecimal pureDigiStr2BigDecimal(String sSign, String sValue2Convert, int scale, BigDecimal defaultValue) {
        BigDecimal aValue = str2BigDecimal(sValue2Convert, scale, defaultValue);
        if (StringUtils.equals(sSign, NEGATIVE_SIGN)) {
            aValue = aValue.multiply(new BigDecimal(-1));
        }
        if (scale > 0) {
            aValue = aValue.divide(BigDecimal.valueOf(Math.pow(10, scale)), scale, RoundingMode.HALF_UP);
        }
        return aValue;
    }

    /**
     * convert string to big decimal
     * <ul>
     * <li>str2BigDecimal(null) = 0</li>
     * <li>str2BigDecimal("AA") = 0</li>
     * <li>str2BigDecimal("2.12") = 2.12</li>
     * </ul>
     *
     * @param sValue
     * @return
     */
    public static BigDecimal str2BigDecimal(String sValue) {
        return str2BigDecimal(sValue, -1, new BigDecimal(0));
    }

    /**
     * convert string to big decimal
     * <ul>
     * <li>str2BigDecimal(null, 2) = 0</li>
     * <li>str2BigDecimal("AA", 2) = 0</li>
     * <li>str2BigDecimal("2.12", 1) = 2.1</li>
     * <li>str2BigDecimal("2.15", 1) = 2.2</li>
     * </ul>
     *
     * @param sValue
     * @return
     */
    public static BigDecimal str2BigDecimal(String sValue, int iScale) {
        return str2BigDecimal(sValue, iScale, new BigDecimal(0));
    }

    /**
     * convert REsourceBundle to Preperties
     *
     * @param rb
     * @return
     */
    public static Properties resouceBundle2Properties(ResourceBundle rb) {

        Properties prop = new Properties();

        Enumeration<String> keys = rb.getKeys();

        while (keys.hasMoreElements()) {

            String sKey = keys.nextElement();

            String sValue = rb.getString(sKey);

            prop.put(sKey, sValue);
        }

        return prop;
    }

    /**
     * convert bytes 2 str (不產生exception)
     *
     * @param data
     * @param encoding
     * @return
     */
    public static String bytes2Str(byte[] data, String encoding) {
        String str = "";

        try {
            str = new String(data, encoding);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("bytes2Str", e);
        }
        return str;
    }

    /**
     * convert str to bytes (不產生exception)
     *
     * @param str
     * @param encoding
     * @return
     */
    public static byte[] str2Bytes(String str, String encoding) {
        byte[] data = null;

        try {
            data = str.getBytes(encoding);
        }
        catch (UnsupportedEncodingException e) {
            logger.error("str2Bytes", e);
        }
        return data;
    }

    /**
     * 將字串轉換為Locale，轉換失敗則傳回Locale.TAIWAN
     *
     * @param str
     * @return
     */
    public static Locale str2Locale(String str) {

        Locale locale = Locale.TRADITIONAL_CHINESE;
        if (StringUtils.equals(str, "zh_CN")) {
            locale = Locale.SIMPLIFIED_CHINESE;
        }
        else if (StringUtils.indexOf(str, "en") > -1) {
            locale = Locale.US;
        }
        else {
            locale = Locale.TRADITIONAL_CHINESE;
        }
        return locale;
    }

    public static int integer2Int(Integer intObj, int defaultValue) {
        return intObj == null ? defaultValue : intObj.intValue();
    }

    public static int integer2Int(Integer intObj) {
        return integer2Int(intObj, 0);
    }

    public static BigDecimal long2BigDecimal(Long bigLongObj) {
        if (null == bigLongObj)
            return BigDecimal.ZERO;
        return NumberUtils.createBigDecimal(Long.toString(bigLongObj));
    }

    public static BigDecimal long2BigDecimal(long longObj) {
        return NumberUtils.createBigDecimal(Long.toString(longObj));
    }

    public static Integer bigInteger2Integer(BigInteger bigInteger) {
        Integer intValue = null;
        if (bigInteger != null) {
            try {
                intValue = bigInteger.intValue();
            }
            catch (ArithmeticException e) {
                logger.warn(e.getMessage(), e);
            }
        }
        return intValue;
    }

    /**
     * 將 IP (nnn.nnn.nnn.nnn)位置轉成 Integer 表示法
     *
     * @param clientIP
     * @return
     */
    public static long ipAddrStrToInt(String clientIP) {
        String[] ipSeg = StringUtils.split(clientIP, ".");
        if (ipSeg == null || ipSeg.length != 4) {
            // unparseble ip
            return 0;
        }
        long ipAddress = 0;
        for (int i = 0; i < ipSeg.length; i++) {
            ipAddress += (Long.parseLong(ipSeg[i]) * Math.pow(256, (3 - i)));
        }
        return ipAddress;
    }

    /**
     * 將具有小數的字串轉成Long
     *
     * @param value
     * @return
     */
    public static Long floatStr2Long(String value) {
        // 去除小數
        if (value.contains(".")) {
            value = value.substring(0, value.indexOf("."));
        }
        return str2Long(value);
    }

    /**
     * Double -> BigDecimal (四捨五入）
     * 
     * @param doubleObj
     * @param scale
     * @return
     */
    public static BigDecimal double2BigDecimal(Double doubleObj, int scale) {
        return double2BigDecimal(doubleObj, scale, RoundingMode.HALF_UP);
    }

    /**
     * Double -> BigDecimal
     * 
     * @param doubleObj
     * @param scale
     * @param round
     * @return
     */
    public static BigDecimal double2BigDecimal(Double doubleObj, int scale, RoundingMode round) {
        if (doubleObj == null) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(doubleObj).setScale(scale, round);

    }

}
