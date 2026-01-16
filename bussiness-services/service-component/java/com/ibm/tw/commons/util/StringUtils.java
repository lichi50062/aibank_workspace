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
import java.lang.Character.UnicodeBlock;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.google.common.collect.ImmutableList;
import com.ibm.tw.ibmb.util.IBUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;

/**
 * 字串處理工具集
 * <p>
 * 字串工具集主要功能繼承自{@link org.apache.commons.lang3.StringUtils}， 並增加下列功能：
 * <ul>
 * <li>金額型態字串處理功能</li>
 * <li>字串切割功能</li>
 * </ul>
 * </p>
 *
 * @author Jeff Liu
 * @version 1.0, 2004/10/18
 * @see {@link org.apache.commons.lang3.StringUtils}
 * @since
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    private static final String BIG_CHINESE_NUMBER = "零壹貳叁肆伍陸柒捌玖"; // 大寫
    private static final String[] BIG_CHINESE_UNITS = { "仟佰拾", "角分" }; // 單位
    /** 大寫中文 <-> 小寫中文 */
    private static final Map<String, String> BIG2SMALL_CHINESE_MAP = Map.ofEntries(Map.entry("壹", "一"), Map.entry("貳", "二"), Map.entry("叁", "三"), Map.entry("參", "三"), Map.entry("肆", "四"), Map.entry("伍", "五"), Map.entry("陸", "六"), Map.entry("柒", "七"), Map.entry("捌", "八"), Map.entry("玖", "九"), Map.entry("拾", "十"), Map.entry("佰", "百"), Map.entry("仟", "千"));

    protected static final String[] ASCII_TABLE = { "!", "\"", "#", "$", "%", "&", "\'", "(", ")", "*", "+", ",", "-", ".", "/", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ":", ";", "<", "=", ">", "?", "@", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "[", "\\", "]", "^", "_", "`", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "{", "|", "}", "~", " " };
    protected static final String[] BIG5_TABLE = { "！", "”", "＃", "＄", "％", "＆", "’", "（", "）", "＊", "＋", "，", "－", "．", "／", "０", "１", "２", "３", "４", "５", "６", "７", "８", "９", "：", "；", "＜", "＝", "＞", "？", "＠", "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ", "Ｙ", "Ｚ", "〔", "＼", "〕", "︿", "＿", "｀", "ａ", "ｂ", "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ", "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ", "｛", "｜", "｝", "～", "　" };
    protected static final List<String> REGEX_ESCAPE_CHARACTER_LIST = ImmutableList.of("[", "$", "&", "+", ",", ":", ";", "=", "?", "@", "#", "|", "'", "<", ">", ".", "^", "*", "(", ")", "%", "!", "-", "]");

    /** "-" */
    public static final String DASH = "-";
    public static final String DASHDASH = "--";
    /** "~" */
    public static final String WAVA = "~";
    /** "_" */
    public static final String UNDERSCORE = "_";
    /** "?" */
    public static final String QUESTIONMARK = "?";
    /** "=" */
    public static final String EQUALMARK = "=";
    /** "&" */
    public static final String ANDMARK = "&";
    /** "/" */
    public static final String SLASH = "/";
    /** "Y" */
    public static final String Y = "Y";
    /** "N" */
    public static final String N = "N";
    /** "," */
    public static final String COMMA = ",";

    /**
     * fortify: Code Correctness: Hidden Method
     */
    private StringUtils() {
        // prevent create instant
    }

    /**
     * 根據字串長度切割字串，一個中文字長度是1
     *
     * <pre>
     * StringUtils.getTokens(null, *)         	= []
     * StringUtils.getTokens("", *)           	= []
     * StringUtils.getTokens("abc def", -1) 	= []
     * StringUtils.getTokens("abc def", 0)  	= []
     * StringUtils.getTokens("abc  def", 2) 	= ["ab", "c ", "de", "f"]
     * </pre>
     *
     * @param sData
     *            原始字串
     * @param iLength
     *            切割的長度
     * @return 字串陣列，永不為null
     */
    public static String[] getTokens(String sData, int iLength) {

        if (null == sData || iLength < 1) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        List<String> tokens = new ArrayList<>();

        int iLeft = 0;

        int iDataLen = sData.length();

        while (iLeft < iDataLen) {

            int iRight = (iLeft + iLength) > iDataLen ? iDataLen : iLeft + iLength;

            String sToken = sData.substring(iLeft, iRight);

            iLeft += iLength;

            tokens.add(sToken);
        }

        return tokens.toArray(new String[tokens.size()]);
    }

    /**
     * 根據分隔子切割字串
     *
     * <pre>
     * StringUtils.getTokens(null, *)         	= []
     * StringUtils.getTokens("", *)           	= []
     * StringUtils.getTokens("abc def", null) 	= ["abc", "def"]
     * StringUtils.getTokens("abc def", " ")  	= ["abc", "def"]
     * StringUtils.getTokens("abc  def", " ") 	= ["abc", "def"]
     * StringUtils.getTokens("ab:cd:ef", ":") 	= ["ab", "cd", "ef"]
     * StringUtils.getTokens("ab:cd:ef:", ":") = ["ab", "cd", "ef", ""]
     * </pre>
     *
     * @param sData
     *            原始字串
     * @param iLength
     *            切割的長度(byte)
     * @return 切割後的字串陣列，永不為NULL
     */
    public static String[] getTokens(String sData, String sDelim) {

        if (null == sData) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        List<String> tokens = new ArrayList<>();

        int iDataLen = sData.length();
        int iDelimLen = sDelim.length();

        int iLeft = 0;
        int iRight = sData.indexOf(sDelim);

        while (iRight >= 0) {

            String sToken = sData.substring(iLeft, iRight).trim();
            tokens.add(sToken);
            iLeft = iRight + iDelimLen;
            iRight = sData.indexOf(sDelim, iLeft);
        }

        if (iLeft < iDataLen) {
            String sToken = sData.substring(iLeft, iDataLen);
            tokens.add(sToken);
        }

        // 取最後一個token，如果為delim則加入一個空白("")token
        if (iDataLen >= iDelimLen) {
            String sLastToken = sData.substring(iDataLen - iDelimLen, iDataLen);

            if (sLastToken.equals(sDelim)) {
                tokens.add("");
            }
        }
        return tokens.toArray(new String[tokens.size()]);

    }

    /**
     * 取代字串中的分隔子
     *
     * <pre>
     * StringUtils.replaceDelim(null, null, null)			= null
     * StringUtils.replaceDelim("", null, null)           	= ""
     * StringUtils.replaceDelim("a*b*c*, "*", []) 			= "a*b*c*"
     * StringUtils.replaceDelim("a*b*c*", "*", null)  		= "a*b*c*"
     * StringUtils.replaceDelim("a*b*c*", "*", [1])    	= "a*b*c*"
     * StringUtils.replaceDelim("a*b*c*", "*", [1, 2, 3])  = "a1b2c3"
     * StringUtils.replaceDelim("a*b*c*", "&", [1, 2, 3])  = "a*b*c*"
     * </pre>
     *
     * @param sSource
     *            string to seach and replace in, may be null
     * @param sDelim
     *            the string to search for, may be null
     * @param with
     *            the strings to replace with
     * @return
     */
    public static String replaceDelim(String sSource, String sDelim, String with[]) {

        if (isEmpty(sSource) || isEmpty(sDelim) || ArrayUtils.isEmpty(with)) {
            return sSource;
        }

        String[] tokens = StringUtils.getTokens(sSource, sDelim);

        // token number - 1 == with number
        if ((tokens.length - 1) != with.length) {
            return sSource;
        }

        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < with.length; i++) {

            sb.append(tokens[i]);

            sb.append(with[i]);
        }

        // last token
        sb.append(tokens[tokens.length - 1]);

        return sb.toString();

    }

    /**
     * 將字串轉換為金額的表示方式
     *
     * <pre>
     * StringUtils.getMoneyStr(null) = ""
     * StringUtils.getMoneyStr(" ") = ""
     * StringUtils.getMoneyStr("1234567.89") = "1,234,567.89"
     * StringUtils.getMoneyStr("1234567.00") = "1,234,567"
     * StringUtils.getMoneyStr("1234567.0100") = "1,234,567.01"
     * </pre>
     *
     * @param sMoney
     * @return
     * @see com.ibm.tw.ibmb.util.IBUtils#showMoneyStr(String)
     */
    @Deprecated
    public static String getMoneyStr(String sMoney) {
        return getMoneyStr(sMoney, 0);
    }

    /**
     * 將字串轉換為金額的表示方式
     *
     * <pre>
     * StringUtils.getMoneyStr(null, 1) = ""
     * StringUtils.getMoneyStr(" ", 2) = ""
     * StringUtils.getMoneyStr("1234567.89", 2) = "1,234,567.89"
     * StringUtils.getMoneyStr("1234567.00", 1) = "1,234,567.0";
     * StringUtils.getMoneyStr("1234567.0100", 2) = "1,234,567.01";
     * </pre>
     *
     * @param sMoney
     *            待轉換之字串
     * @param iScale
     *            小數有效位數
     * @return 金額表示型態的字串，永不為null
     * @see com.ibm.tw.ibmb.util.IBUtils#showMoneyStr(String, int)
     */
    @Deprecated
    public static String getMoneyStr(String sMoney, int iScale) {

        if (StringUtils.isBlank(sMoney)) {
            return "";
        }

        String tmpMoneyStr = sMoney.trim();

        StringBuilder sb = new StringBuilder(8192);

        if (tmpMoneyStr.startsWith("+") || tmpMoneyStr.startsWith("-")) {
            String sSign = StringUtils.substring(tmpMoneyStr, 0, 1);
            // '-' 放回去, '+' 濾除
            sb.append("-".equals(sSign) ? sSign : "");
            tmpMoneyStr = StringUtils.substring(tmpMoneyStr, 1);
        }

        // 整數位的字串
        String sInt = "";

        // 小數位的字串
        String sDecimal = "";

        // 小數點
        String sDot = ".";

        int index = tmpMoneyStr.indexOf('.');
        // 傳入的值有小數位
        if (index >= 0) {
            sInt = tmpMoneyStr.substring(0, index);
            sDecimal = StringUtils.substring(tmpMoneyStr, index + 1);
        }
        // 傳入的值沒有小數位
        else {
            sInt = tmpMoneyStr;
        }

        // 整數位的字串，帶千分位
        sInt = getIntMoneyStr(sInt);
        // 記錄「整數位」的值
        sb.append(StringUtils.defaultIfBlank(sInt, "0"));

        // 傳入的 scale 大於 0，表示顯示的字串要有小數位
        if (iScale > 0) {
            sb.append(sDot);
            // sDecimal字串長度 > iScale
            if (StringUtils.length(sDecimal) > iScale) {
                sb.append(StringUtils.substring(sDecimal, 0, iScale));
            }
            // sDecimal字串長度 < iScale，右補0
            else {
                sb.append(StringUtils.rightPad(sDecimal, iScale, "0"));
            }
        }
        // 傳入的 scale 等於 0，若小數位有值，顯示該小數位
        else if (iScale == 0) {
            // 若傳入的值，具備「小數位」
            if (StringUtils.isNotBlank(sDecimal)) {
                // 移除右側的0
                sDecimal = StringUtils.trimRightZero(sDecimal);
                if (StringUtils.isNotBlank(sDecimal)) {
                    sb.append(sDot).append(sDecimal);
                }
            }
        }
        // 不要有小數位
        else {
            // nothing.
        }

        return sb.toString();
    }

    /**
     * @see IBUtils#formatMoneyStr(BigDecimal, int)
     */
    @Deprecated
    public static String getMoneyStr(BigDecimal sMoney, int iScale) {
        return getMoneyStr(String.valueOf(sMoney), iScale);
    }

    /**
     * 將字串轉換為金額表示型態的字串
     *
     * <pre>
     * StringUtils.getIntMoneyStr(&quot;001234567&quot;) = &quot;1,234,567&quot;
     * </pre>
     *
     * @param sInt
     * @return
     *
     */
    private static String getIntMoneyStr(String sInt) {

        if (isBlank(sInt)) {
            return "";
        }

        String tmpIntStr = sInt.trim();

        StringBuilder sb = new StringBuilder(8192);

        tmpIntStr = trimLeftZero(tmpIntStr);
        int iLen = tmpIntStr.length();

        for (int i = 0; i < iLen; i++) {

            char ch = tmpIntStr.charAt(i);

            sb.append(ch);
            // 剩餘長度
            int iRemainLen = iLen - i - 1;

            if ((iRemainLen > 0) && (iRemainLen % 3 == 0)) {
                sb.append(",");
            }

        }
        return sb.toString();

    }

    /**
     * 將數字字串依照指定的小數位數輸出成浮點數字串
     *
     * <pre>
     * parseFloat("12345678", 2) -> "123456.78"
     * parseFloat("12345678", 6) -> "12.345678"
     * parseFloat("12345678", 10) -> "0.0012345678"
     * parseFloat("12345678", 0) -> "12345678"
     * parseFloat("12345678", -1) -> "12345678"
     * parseFloat("a12s5w6dd", 3) -> ""
     * parseFloat("", 3) -> ""
     * parseFloat(null, 3) -> ""
     * </pre>
     *
     * @param sSourceStr
     *            原始字串
     * @param iScale
     *            小數位數
     * @return
     */
    public static String parseFloat(String sSourceStr, int iScale) {

        if (!NumberUtils.isDigits(sSourceStr)) {
            return "";
        }

        if (StringUtils.isBlank(sSourceStr) || iScale < 1) {
            return StringUtils.defaultString(sSourceStr);
        }

        String sInt = "";

        String sDecimal = "";

        int iLength = sSourceStr.length();

        if (iLength > iScale) {
            // 整數長度
            int iIntLength = iLength - iScale;

            sInt = sSourceStr.substring(0, iIntLength);

            sDecimal = sSourceStr.substring(iIntLength);
        }
        else {

            sInt = "0";

            sDecimal = StringUtils.leftPad(sSourceStr, iScale, "0");

        }

        return sInt + "." + sDecimal;
    }

    /**
     * 移除字串左邊的<code>0</code>字元
     *
     * <pre>
     * StringUtils.trimLeftZero(null) = ""
     * StringUtils.trimLeftZero("0012345600") = "12345600"
     * </pre>
     *
     * @param sSource
     *            待處理之字串
     * @return 移除左側<code>0</code>字元後的字串，永不為null
     */
    public static String trimLeftZero(String sSource) {

        if (sSource == null) {
            return "";
        }

        int iLen = sSource.length();
        int index = -1;
        for (int i = 0; i < iLen; i++) {
            char ch = sSource.charAt(i);

            if (ch != '0') {
                index = i;
                break;
            }
        }
        String s = "";
        // 發現非0之數字
        if (index >= 0) {
            s = sSource.substring(index, iLen);
        }

        return s;

    }

    /**
     * StringUtils.trimRightZero("01234500") = "012345"
     *
     * @param sSource
     * @return
     */
    public static String trimRightZero(String sSource) {

        if (sSource == null) {
            return "";
        }

        int iLen = sSource.length();
        int index = -1;
        // 由後至前
        for (int i = (iLen - 1); i >= 0; i--) {
            char ch = sSource.charAt(i);
            if (ch != '0') {
                index = i;
                break;
            }
        }

        String s = "";
        if (index >= 0) {
            s = sSource.substring(0, index + 1);
        }

        return s;
    }

    /**
     * 將字串轉換為百分比顯示型態
     *
     * <pre>
     * StringUtils.getRateStr(null) = ""
     * StringUtils.getRateStr("000122.00100") = "122.001"
     * StringUtils.getRateStr("00.12") = "0.12"
     * StringUtils.getRateStr("00.00") = "0.0"
     * </pre>
     *
     * @param sRate
     * @return 轉換後之百分比顯示型態字串，永不為null
     */
    public static String getRateStr(String sRate) {
        if (StringUtils.isEmpty(sRate)) {
            return EMPTY;
        }

        StringBuilder sb = new StringBuilder(8192);
        int iLen = sRate.length();

        // 整數
        String sInt = "";

        // 小數
        String sDecimal = "";

        // 小數點
        String sDot = "";

        int index = sRate.indexOf('.');

        if (index >= 0) {
            sDot = ".";
            sInt = sRate.substring(0, index);
            if ((index + 1) < iLen) {
                sDecimal = sRate.substring(index + 1, sRate.length());
            }
        }
        else {
            sInt = sRate;
        }

        // 整數
        sInt = trimLeftZero(sInt);

        // 小數
        sDecimal = trimRightZero(sDecimal);

        // 有整數部分
        if (sInt.length() > 0) {
            sb.append(sInt);

            if (sDecimal.length() > 0) {
                sb.append(sDot).append(sDecimal);
            }
        }
        // 沒有整數部分
        else {
            if (sDecimal.length() > 0) {
                sb.append("0.").append(sDecimal);
            }
            else {
                sb.append("0.0");
            }
        }

        return sb.toString();
    }

    /**
     * 取得字串中的整數部分
     *
     * <pre>
     * StringUtils.getRateStr(null) = ""
     * StringUtils.getRateStr("000122.00100") = "122"
     * </pre>
     *
     * @param sRate
     * @return
     */
    public static String getIntPart(String sRate) {

        if (StringUtils.isBlank(sRate)) {
            return "";
        }

        // 整數
        String sInt = "";

        int index = sRate.indexOf('.');

        if (index >= 0) {
            sInt = sRate.substring(0, index);
        }
        else {
            sInt = sRate;
        }

        // 整數
        sInt = trimLeftZero(sInt);

        return sInt;
    }

    /**
     * 主機字串長度
     *
     * char code > 255表示長度2 char code <= 255表示長度1
     *
     * @param str
     * @return
     */
    public static int lengthHost(String str) {
        int length = 0;
        if (str == null) {
            return length;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c > 255) {
                length += 2;
            }
            else {
                length += 1;
            }
        }

        return length;
    }

    /**
     * 依 byte 長度左補足長度，中文以 Big5 encoding 計算
     * 
     * @param sData
     *            原始資料
     * @param iLength
     *            指定長度
     * @param sPadStr
     *            補長度用的字串，若為 null 或空字串則以空白字元補足
     *
     * @return 補足長度的字串
     */
    public static String leftPadByByteLength(String sData, int iLength, String sPadStr) {
        return leftPadByByteLength(sData, iLength, sPadStr, "Big5");
    }

    /**
     * 依 byte 長度左補足長度，中文以傳入的編碼計算
     * 
     * @param sData
     *            原始資料
     * @param iLength
     *            指定長度
     * @param sPadStr
     *            補長度用的字串，若為 null 或空字串則以空白字元補足
     * @param encoding
     *            指定旳中文編碼
     *
     * @return 補足長度的字串
     */
    public static String leftPadByByteLength(String sData, int iLength, String sPadStr, String encoding) {
        boolean left = true;

        return padByByteLength(sData, iLength, sPadStr, encoding, left);
    }

    /**
     * 實際補長度程式
     * 
     * @param sData
     *            原始資料
     * @param iLength
     *            指定長度
     * @param sPadStr
     *            補長度用的字串，若為 null 或空字串則以空白字元補足
     * @param encoding
     *            指定旳中文編碼
     * @param left
     *            是否補左邊 (false 為補右邊)
     * @return
     */
    private static String padByByteLength(String sData, int iLength, String sPadStr, String encoding, boolean left) {
        if (sData == null) {
            return null;
        }
        String tmpPadStr = sPadStr;
        if (StringUtils.isBlank(sPadStr)) {
            tmpPadStr = " ";
        }

        StringBuilder sResult = new StringBuilder(sData);
        int byteLength = ConvertUtils.str2Bytes(sData, encoding).length;
        int padByteLength = ConvertUtils.str2Bytes(sPadStr, encoding).length;
        for (int i = byteLength; i < iLength; i += padByteLength) {
            if (left) {
                sResult.insert(0, tmpPadStr);
            }
            else {
                sResult.append(tmpPadStr);
            }
        }

        return sResult.toString();
    }

    /**
     * 依長度左補足0
     *
     * leftPadZero("00123", 10) => 0000000123
     *
     * @param str
     * @param length
     * @return
     */
    public static String leftPadZero(String str, int length) {
        return StringUtils.leftPad(str, length, "0");
    }

    /**
     * 依 byte 長度右補足長度，中文以 Big5 encoding 計算
     * 
     * @param sData
     *            原始資料
     * @param iLength
     *            指定長度
     * @param sPadStr
     *            補長度用的字串，若為 null 或空字串則以空白字元補足
     *
     * @return 補足長度的字串
     */
    public static String rightPadByByteLength(String sData, int iLength, String sPadStr) {
        return rightPadByByteLength(sData, iLength, sPadStr, "Big5");
    }

    /**
     * 依 byte 長度右補足長度，中文以傳入的編碼計算
     * 
     * @param sData
     *            原始資料
     * @param iLength
     *            指定長度
     * @param sPadStr
     *            補長度用的字串，若為 null 或空字串則以空白字元補足
     * @param encoding
     *            指定旳中文編碼
     *
     * @return 補足長度的字串
     */
    public static String rightPadByByteLength(String sData, int iLength, String sPadStr, String encoding) {
        return padByByteLength(sData, iLength, sPadStr, encoding, false);
    }

    /**
     * 移除字串左右邊之全形及半形空白
     *
     * @param sValue
     * @return
     */
    public static String trimAllBigSpace(String sValue) {

        // 字串內容為空格時，視作有值
        if (StringUtils.isEmpty(sValue)) {
            return sValue;
        }

        sValue = StringUtils.strip(sValue, "\u3000");
        sValue = StringUtils.strip(sValue);
        return sValue;
    }

    /**
     * 移除字串右邊之全形空白
     *
     * @param sValue
     * @return
     */
    public static String trimRightBigSpace(String sValue) {
        String sResult = sValue;

        while (sResult.endsWith("　")) {
            sResult = sResult.substring(0, sResult.length() - 1);
        }

        return sResult;
    }

    /**
     * 將StringArray轉換為SQL的Where In格式 ex : StringUtils.StrArray2WhereInSql(["A", "B", "C"]) = "'A','B','C'"
     *
     * @param array
     * @param sSeparator
     * @return
     */
    public static String strArray2WhereInSql(String[] array) {
        if (array == null) {
            return null;
        }

        String sSeparator = ",";

        StringBuilder sb = new StringBuilder(8192);

        for (int i = 0; i < array.length; i++) {
            if (i != 0) {
                sb.append(sSeparator);
            }
            sb.append("'").append(StringUtils.defaultString(array[i])).append("'");
        }

        return sb.toString();
    }

    /**
     * 是否為全型
     *
     * @param str
     * @return
     */
    public static boolean isAllFullWidth(String str) {
        if (str == null) {
            return true;
        }

        return str.matches("^[^\\x00-\\xff]*$");
    }

    /**
     * 轉全形
     *
     * @param s
     * @return
     */
    public static String toFullChar(String s) {
        if (s == null || "".equalsIgnoreCase(s)) {
            return "";
        }

        char[] ca = s.toCharArray();
        StringBuilder outStr = new StringBuilder(8192);

        for (int a = 0; a < ca.length; a++) {
            String caStr = String.valueOf(ca[a]);
            for (int b = 0; b < ASCII_TABLE.length; b++) {
                if (caStr.equals(ASCII_TABLE[b])) {
                    caStr = BIG5_TABLE[b];
                    break;
                }
            }

            outStr.append(caStr);
        }

        return outStr.toString();
    }

    /**
     * 轉半形
     *
     * @param s
     * @return
     */
    public static String toHalfChar(String s) {
        if (s == null || "".equalsIgnoreCase(s)) {
            return "";
        }

        char[] ca = s.toCharArray();
        StringBuilder outStr = new StringBuilder(8192);

        for (int a = 0; a < ca.length; a++) {
            String caStr = String.valueOf(ca[a]);
            for (int b = 0; b < BIG5_TABLE.length; b++) {
                if (caStr.equals(BIG5_TABLE[b])) {
                    caStr = ASCII_TABLE[b];
                    break;
                }
            }

            outStr.append(caStr);
        }

        return outStr.toString();
    }

    /**
     * 將 HTML 字串，進行 escape 處理
     * 
     * @param html
     * @return
     */
    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    /**
     * 刪除字元
     *
     * @param str
     * @param removeList
     * @return
     */
    public static String remove(String str, String[] removeList) {
        String result = str;
        for (String remove : removeList) {
            result = remove(result, remove);
        }
        return result;
    }

    /**
     * 檢查字串是含有 big5 中文
     *
     * @param s
     * @return
     */
    public static boolean containChinese(String s) {
        if (isEmpty(s)) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(UnicodeBlock.of(s.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根據字串的Byte長度切割字串，
     *
     * <pre>
     * </pre>
     *
     * @param sData
     *            原始字串
     * @param iLength
     *            切割的長度
     * @param encoding
     * @return 字串陣列，永不為null
     * @See {@link com.ibm.tw.commons.util.StringUtils#getTokens(String sData, int iLength)}.
     * @throws UnsupportedEncodingException
     */
    public static String[] getTokensByByteLength(String sData, int iLength, String encoding) throws UnsupportedEncodingException {

        if (null == sData || iLength < 1) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        }

        List<String> tokens = new ArrayList<>();

        int iLeft = 0;

        byte[] bDate = sData.getBytes(encoding);

        int iDataLen = bDate.length;

        while (iLeft < iDataLen) {

            int iRight = (iLeft + iLength) > iDataLen ? iDataLen : iLeft + iLength;

            byte[] bToken = ArrayUtils.subarray(bDate, iLeft, iRight);

            iLeft += iLength;

            tokens.add(new String(bToken, encoding));
        }

        return tokens.toArray(new String[tokens.size()]);
    }

    /**
     * 轉換日期顯示格式 yyyyMMdd -> yyyy/MM/dd
     *
     * @param dateStr
     * @return
     */
    public static String formatDate(String dateStr) {
        if (StringUtils.isBlank(dateStr) || StringUtils.length(dateStr) != 8) {
            return "";
        }
        String year = StringUtils.left(dateStr, 4);
        String month = StringUtils.substring(dateStr, 4, 6);
        String day = StringUtils.right(dateStr, 2);
        return year + "/" + month + "/" + day;
    }

    /**
     * 轉換時間顯示格式 HHmmss -> HH:mm:ss
     *
     * @param timeStr
     * @return
     */
    public static String formatTime(String timeStr) {
        if (StringUtils.isBlank(timeStr) || StringUtils.length(timeStr) != 6) {
            return "";
        }
        String hour = StringUtils.left(timeStr, 2);
        String minute = StringUtils.substring(timeStr, 2, 4);
        String second = StringUtils.right(timeStr, 2);
        return hour + ":" + minute + ":" + second;
    }

    /**
     * 去除括號後面的字
     * 
     * <pre>
     * 	ex:
     * 		臺幣         ->  臺幣
     * 		臺幣(TEST)   ->  臺幣
     * 		臺幣（test）  ->  臺幣
     * </pre>
     *
     * @param str
     * @return trimedStr
     */
    public static String trimAfterBrackets(String str) {
        if (StringUtils.contains(str, "(")) {
            return StringUtils.split(str, "(")[0];
        }
        else if (StringUtils.contains(str, "（")) {
            return StringUtils.split(str, "（")[0];
        }
        else {
            return str;
        }
    }

    public static boolean notEquals(String str1, String str2) {
        return !StringUtils.equals(str1, str2);
    }

    public static boolean notAllEquals(CharSequence string, CharSequence... searchStrings) {
        return !StringUtils.equalsAny(string, searchStrings);
    }

    /**
     * 將字串頭尾的全形空白轉換為半形空白後trim(全形半形混雜時, 無法一次性移除)
     *
     * @param str
     * @return
     */
    public static String trimToEmptyEx(final String str) {
        if (str == null) {
            return EMPTY;
        }
        Pattern p = Pattern.compile("^　+|　+$");
        Matcher m = p.matcher(str);
        return org.apache.commons.lang3.StringUtils.trimToEmpty(m.replaceAll(""));
    }

    /**
     * 去除所有斜線
     * 
     * @param str
     * @return
     */
    public static String removeSlashes(String str) {
        return RegExUtils.replaceAll(str, SLASH, EMPTY);
    }

    /**
     * 把字串中存在的 REGEX 的特殊字元跳脫
     * 
     * @param str
     * @return
     */
    public static String escapeAllRegexSpecial(String str) {
        String resultStr = str;
        for (String specialChar : REGEX_ESCAPE_CHARACTER_LIST) {
            resultStr = resultStr.replace(specialChar, "\\" + specialChar);
        }
        return resultStr;
    }

    /**
     * 將原住民姓名文字取代為問號
     * 
     * @param value
     * @return
     */
    public static String removeNativePeopleName(String value) {
        if (value == null) {
            return "";
        }

        List<Integer> nativeNameCode = Arrays.asList(0x02BC, 0x2303, 0x1e5f, 0x003a, 0x00e9, 0x0268, 0x0289);

        StringBuffer result = new StringBuffer();
        value.codePoints().forEachOrdered((code) -> {
            result.append(nativeNameCode.contains(code) ? "？" : Character.toChars(code)[0]);
        });
        return result.toString();
    }

    public static boolean containsSpaceOrFullSpace(String str) {
        if (isEmpty(str)) {
            return false;
        }

        if (containsWhitespace(str)) {
            return true;
        }

        return str.length() != str.replace("\u3000", "").length();
    }

    /**
     * 計算字串長度(考慮難字)
     * 
     * @param str
     * @return
     */
    public static int getCodePointLength(String str) {
        return str.codePointCount(0, str.length());
    }

    public static String[] splitTrim(String str) {
        return trim(split(str));
    }

    public static String[] splitTrim(String str, char separatorChar) {
        return trim(split(str, separatorChar));
    }

    public static String[] splitTrim(String str, String separatorChars) {
        return trim(split(str, separatorChars));
    }

    public static String[] splitTrim(String str, String separatorChars, int max) {
        return trim(split(str, separatorChars, max));
    }

    public static boolean isY(String str) {
        return equalsIgnoreCase(str, Y);
    }

    public static boolean isN(String str) {
        return equalsIgnoreCase(str, N);
    }

    public static String getNY(boolean trueFalse) {
        return trueFalse ? Y : N;
    }

    private static String[] trim(String[] rawDatas) {
        List<String> cleanDatas = new ArrayList<>();
        for (String rawData : rawDatas) {
            if (StringUtils.isNotBlank(rawData)) {
                cleanDatas.add(rawData);
            }
        }
        return cleanDatas.toArray(new String[cleanDatas.size()]);
    }

    public static String moneyToChinese(double money) {
        String moneyStr = getDecimalStr(money);
        return moneyToChinese(moneyStr);
    }

    /**
     * 雙精度浮點數轉換成字串
     * 
     * @param d
     * @return
     */
    public static String getDecimalStr(double d) {
        // 設置小數點後的精度，保留兩位
        /*
         * 四舍五入結果參考: 0.005,//0.01入 0.015,//0.01舍 0.025,//0.03入 0.035,//0.04入 0.045,//0.04舍 0.055,//0.06入(前一位是5則入)
         */
        String str = BigDecimal.valueOf(d).setScale(2, RoundingMode.HALF_UP).toString();

        // 如果結果是整數，則去掉尾巴
        if (str.endsWith(".00")) {
            str = str.replace(".00", "");
        }
        return str;
    }

    /**
     * 阿拉伯數字金額轉成中文大寫金額
     * 
     * @param moneyStr
     * @return
     */
    public static String moneyToChinese(String moneyStr) {
        String[] parts = moneyStr.split("\\."); // 區別整數、小數部分
        String result = "";

        // 處理整數部分
        int length = parts[0].length(); // 整數部分的位數
        if (length > 15) {
            return "金額太大,不能處理整數部分超過15位的金額！";
        }
        String intPart = parts[0];

        // 填充到16位，因為是分4組，每組4個數字
        while (intPart.length() < 16) {
            intPart = '0' + intPart;
        }
        // 共分四組,右起四位一組,例如：0001,2003,0030,3400
        String[] groups = new String[4];
        for (int i = 0; i < groups.length; i++) {
            int start = intPart.length() - (i + 1) * 4; // 開始位置
            int end = intPart.length() - i * 4; // 結束位置
            groups[i] = intPart.substring(start, end);
            groups[i] = transformGroup(groups[i]); // 當前組的四位數字轉換成大寫
        }

        // 對這四組結果從高位到低位拼接起來，規則：[組4]萬[組3]億[組2]萬[組1]圓
        for (int i = groups.length - 1; i >= 0; i--) {
            if (i == 3) { // 第四組：萬億級
                if (!"零".equals(groups[i])) {
                    result += groups[i] + "萬";
                }
            }
            else if (i == 2) { // 第三組：億級
                if (!"零".equals(groups[i])) {
                    result += groups[i] + "億";
                }
                else {
                    if (result.length() > 0) {
                        result += "億";
                    }
                }
            }
            else if (i == 1) { // 第二組：萬級
                if (!"零".equals(groups[i])) {
                    result += groups[i] + "萬";
                }
                else if (!groups[i].startsWith("零")) {
                    result += groups[i];
                }
            }
            else { // 第一組：千級
                if (!"零".equals(groups[i]) || result.length() == 0) {
                    result += groups[i];
                }
                // result += "圓";
            }
        }
        if (!"零圓".equals(result) && result.startsWith("零")) {
            result = result.substring(1, result.length()); // 最前面的可能出現的“零”去掉
        }

        // 處理小數部分
        if (parts.length == 2) {
            String decimalPart = parts[1]; // 小數部分
            for (int i = 0; i < decimalPart.length(); i++) {
                int num = Integer.valueOf(decimalPart.charAt(i) + ""); // 提取數字，左起
                result += BIG_CHINESE_NUMBER.charAt(num) + "" + BIG_CHINESE_UNITS[1].charAt(i); // 數字變大寫加上單位
            }
            result = result.replace("零角", "零"); // 去掉"零角"的"角"
            result = result.replace("零分", ""); // 去掉"零分"
        }
        else {
            // result += "整"; //沒有小數部分，則加上“整”
        }

        return result;
    }

    /**
     * 處理整數部分的組，右起每四位是一組
     * 
     * @param group
     *            四位數字字符串
     */
    private static String transformGroup(String group) {
        String result = "";
        int length = group.length();
        for (int i = 0; i < length; i++) {
            int digit = Integer.valueOf(group.charAt(i) + ""); // 單個數字，左起
            String unit = ""; // 單位
            if (i != length - 1) {
                unit = BIG_CHINESE_UNITS[0].charAt(i) + "";
            }
            result += BIG_CHINESE_NUMBER.charAt(digit) + unit; // 數字變大寫加上單位
        }

        result = result.replace("零仟", "零");
        result = result.replace("零佰", "零");
        result = result.replace("零拾", "零");

        while (result.contains("零零")) {
            result = result.replace("零零", "零"); // 如果有“零零”則變成一個“零”
        }

        if (!"零".equals(result) && result.endsWith("零")) {
            result = result.substring(0, result.length() - 1); // 最未尾的可能出現的“零”去掉
        }
        return result;
    }

    /**
     * 將中文難字取代為問號
     * 
     * @return
     */
    public static String removeHardChineseWord(String value) {
        return removeHardChineseWord(value, "？");
    }

    /**
     * 將中文難字取代為替換文字.
     *
     * @param value
     *            檢查字串
     * @param replaceStr
     *            替換文字
     * @return 替換後字串
     */
    public static String removeHardChineseWord(String value, String replaceStr) {
        if (value == null) {
            return "";
        }

        StringBuffer result = new StringBuffer();
        value.codePoints().forEachOrdered((code) -> {
            result.append(ValidatorUtility.isHardChar(code) ? replaceStr : Character.toChars(code)[0]);
        });
        return result.toString();
    }

    /**
     * 過濾特殊字符
     * 
     * @param log
     * @return
     */
    public static String escapeLog(String str) {
        if (isBlank(str)) {
            return "";
        }
        else {
            List<String> list = new ArrayList<String>();
            list.add("%0d");
            list.add("\r");
            list.add("%0a");
            list.add("\n");
            String encode = Normalizer.normalize(str, Normalizer.Form.NFKC);
            for (int i = 0; i < list.size(); i++) {
                encode = encode.replace(list.get(i), "");
            }
            return StringEscapeUtils.escapeJava(encode);
        }
    }

    /**
     * 將字串中的大寫中文轉成小寫
     * 
     * @param str
     * @return
     */
    public static String covert2SmallCh(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        for (Map.Entry<String, String> entry : BIG2SMALL_CHINESE_MAP.entrySet()) {
            str = str.replace(entry.getKey(), entry.getValue());
        }
        return str;
    }

    /**
     * 移除前綴 (前綴是英文ignore大小寫)
     * 
     * @param str
     * @param prefix
     * @return
     */
    public static String removePrefix(String str, String prefix) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        else if (StringUtils.isBlank(prefix)) {
            return str;
        }
        if (str.regionMatches(true, 0, prefix, 0, prefix.length())) {
            return str.substring(prefix.length());
        }
        return str;
    }

    /**
     * 移除前綴
     * 
     * @param input
     * @return
     */
    public static String removePrefix(String str, String prefix01, String prefix02) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        if (str.startsWith(prefix01)) {
            str = str.substring(prefix01.length());
        }
        if (str.startsWith(prefix02)) {
            str = str.substring(prefix02.length());
        }
        return str;
    }

    /**
     * 取得字串長度(中文為2，數字為1，半形為1，全形為2)
     *
     * @param value
     * @return
     */
    public static int getStrLength(String value) {
        String str = (value == null) ? "" : value; // 避免null，造成exception
        int length = 0;
        char character;
        for (int i = 0; i < str.length(); i++) {
            character = str.charAt(i);
            // 若不為英文字，或半形時，長度加1(長度算2)
            if (Character.UnicodeBlock.of(character) != Character.UnicodeBlock.BASIC_LATIN || !isHalfWidth(character)) {
                length++;
            }
            length++;
        }
        return length;
    }

    /**
     * 判斷是否為半形
     *
     * @param character
     * @return
     */
    public static boolean isHalfWidth(char c) {
        return '\u0000' <= c && c <= '\u00FF' || '\uFF61' <= c && c <= '\uFFDC' || '\uFFE8' <= c && c <= '\uFFEE';
    }

    /**
     * 判斷是否有難以顯示的字元(只針對難字檢查)
     * 
     *
     * @param chkString
     *            檢測對象字符串
     * @return
     */
    public static boolean checkHardWord(String chkString) {
        return ValidatorUtility.checkHardWord(chkString);
    }

    /**
     * 遍歷傳入字串的每個字元，如果是數字(0-9)，就把該字元轉成全型
     *
     * @return
     */
    public static String numberToFullWidthChar(String value) {
        if (StringUtils.isNotBlank(value)) {
            String[] elements = value.split("");
            value = Stream.of(elements).map(ele -> {
                if (NumberUtils.isDigits(ele)) {
                    ele = StringUtils.toFullChar(ele);
                }
                return ele;
            }).collect(Collectors.joining());
        }
        return value;
    }

    /**
     * 移除字串裡的「千分位」
     * 
     * @param input
     * @return
     */
    public static String removeThousandSeparator(String input) {
        if (isBlank(input)) {
            return input;
        }
        return input.replace(",", "");
    }
}
