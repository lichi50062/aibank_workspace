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
package com.ibm.tw.ibmb.validate;

import java.io.UnsupportedEncodingException;
import java.lang.Character.UnicodeBlock;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;

// @formatter:off
/**
 * @(#)ValidatorUtility.java
 * 
 * <p>Description:檢查函式</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/30, Alex LS Chen	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class ValidatorUtility {

    private static final IBLog logger = IBLog.getLog(ValidatorUtility.class);

    private static final String IDCHK = "0123456789ABCDEFGHJKLMNPQRSTUVXYWZIO"; // 檢查碼

    private static final String PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

    /** 連續數字 */
    private static final String CONSECUTIVE_NUM = "01234567890";

    // 居留證號
    private static final char[] pidCharArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    // 原居留證第一碼英文字應轉換為10~33，十位數*1，個位數*9，這裡直接作[(十位數*1) mod 10] + [(個位數*9) mod 10]
    private static final int[] pidResidentFirstInt = { 1, 10, 9, 8, 7, 6, 5, 4, 9, 3, 2, 2, 11, 10, 8, 9, 8, 7, 6, 5, 4, 3, 11, 3, 12, 10 };

    // 原居留證第二碼英文字應轉換為10~33，並僅取個位數*8，這裡直接取[(個位數*8) mod 10]
    private static final int[] pidResidentSecondInt = { 0, 8, 6, 4, 2, 0, 8, 6, 2, 4, 2, 0, 8, 6, 0, 4, 2, 0, 8, 6, 4, 2, 6, 0, 8, 4 };

    // 外國人證號 統號A
    private static final Map<String, Integer[]> FOREIGNA = new HashMap<String, Integer[]>();

    static {
        FOREIGNA.put("A", new Integer[] { 1, 0 });
        FOREIGNA.put("B", new Integer[] { 1, 1 });
        FOREIGNA.put("C", new Integer[] { 1, 2 });
        FOREIGNA.put("D", new Integer[] { 1, 3 });
        FOREIGNA.put("E", new Integer[] { 1, 4 });
        FOREIGNA.put("F", new Integer[] { 1, 5 });
        FOREIGNA.put("G", new Integer[] { 1, 6 });
        FOREIGNA.put("H", new Integer[] { 1, 7 });
        FOREIGNA.put("J", new Integer[] { 1, 8 });
        FOREIGNA.put("K", new Integer[] { 1, 9 });
        FOREIGNA.put("L", new Integer[] { 2, 0 });
        FOREIGNA.put("M", new Integer[] { 2, 1 });
        FOREIGNA.put("N", new Integer[] { 2, 2 });
        FOREIGNA.put("P", new Integer[] { 2, 3 });
        FOREIGNA.put("Q", new Integer[] { 2, 4 });
        FOREIGNA.put("R", new Integer[] { 2, 5 });
        FOREIGNA.put("S", new Integer[] { 2, 6 });
        FOREIGNA.put("T", new Integer[] { 2, 7 });
        FOREIGNA.put("U", new Integer[] { 2, 8 });
        FOREIGNA.put("V", new Integer[] { 2, 9 });
        FOREIGNA.put("X", new Integer[] { 3, 0 });
        FOREIGNA.put("Y", new Integer[] { 3, 1 });
        FOREIGNA.put("W", new Integer[] { 3, 2 });
        FOREIGNA.put("Z", new Integer[] { 3, 3 });
        FOREIGNA.put("I", new Integer[] { 3, 4 });
        FOREIGNA.put("O", new Integer[] { 3, 5 });
    }

    // 外國人對應特定數b
    private final static int[] FOREIGNERIDB = { 1, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

    public static final String REGEX_PATTERN_YYYY_MM_DD_SLASH = "[0-9]{4}/[0-9]{2}/[0-9]{2}";

    // @formatter:off
    /**
     * 國民身分證統一編號 format: Z999999999 
     * ID 輸入身分證字號 idchk 檢核用字串 
     * n1: 檢核第一位,必為英文字 
     * n2: 檢核第二位,1 or 2 total 加總 
     * sum1 第一位轉換 num 規則性變數 
     * sum2 後9碼規則性加總
     * 
     * @param fieldContent
     *            欄位傳入的數值
     * @return true success else fail
     */
    // @formatter:on
    public static boolean checkID(String fieldContent) {
        int n1 = 0;
        int n2 = 0;
        int num = 0;
        int sum1 = 0;
        int sum2 = 0;

        int len = fieldContent.length();

        if (len != 10) {
            return false;
        }

        // 新增居留證號檢核 add by Ice at 2013/09/16_end
        String tmpfieldContent = fieldContent.toUpperCase();
        n1 = IDCHK.indexOf(tmpfieldContent.charAt(0));
        n2 = IDCHK.indexOf(tmpfieldContent.charAt(1));

        if (n1 < 10 || n2 < 1 || n2 > 2) {
            return false;
        }
        else {
            sum1 = n1 / 10 + (n1 % 10) * 9;
            for (int i = 1; i < 10; i++) {
                num = 9 - i;
                if (num < 1) {
                    num = 1;
                }
                sum2 = sum2 + (IDCHK.indexOf(tmpfieldContent.charAt(i))) * num;
            }
            if ((sum1 + sum2) % 10 != 0) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    /**
     * 事業團體統一編號 format: 99999999
     * 
     * @param input
     *            欄位傳入的數值
     * @return true success else fail
     */
    public static boolean checkBusiness(String input) {
        int y;
        int front = 0;
        int ch;
        if (StringUtils.isBlank(input)) {
            return false;
        } // 非必輸入
        int len = input.length();
        int num[] = stringToNumArray(input);
        if (len != 8 || num == null) {
            return false;
        }
        for (int i = 0; i < 6; i += 2) {
            ch = num[i + 1] * 2;
            front = front + num[i] + ch / 10 + ch % 10;
        }
        ch = num[6] * 4;
        y = front + num[7] + ch / 10 + ch % 10;
        if (y % 10 == 0) {
            return true;
        }
        else if (num[6] == 7) {
            ch = (ch / 10 + ch % 10) / 10;
            y = front + ch + num[7];
            if (y % 10 == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 檢查是否為合法 EMAIL Email中不可含有特殊符號或空白；且不可以『.』為結尾' Email須含有『@』
     * 
     * @param fieldContent欄位傳入的數值
     * @return true success else fail
     */
    public static boolean checkEMail(String fieldContent) {
        // 2018.10.18 去除檢核IP && 空字串也是為不合法
        return StringUtils.isNotBlank(fieldContent) && fieldContent.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@([A-Za-z0-9-])+(\\.[A-Za-z0-9-]+)*((\\.[A-Za-z0-9]{1,})|(\\.[A-Za-z0-9]{1,}\\.[A-Za-z0-9]{1,}))$");
    }

    /**
     * 檢查是否為合法 發票號碼
     * 
     * @param fieldContent欄位傳入的數值
     * @return true success else fail
     */
    public static boolean checkInvoice(String fieldContent) {
        return StringUtils.isNotBlank(fieldContent) && fieldContent.matches("^[A-Z]{2}[-][0-9]{8}$");
    }

    public static boolean validateIP(final String ip) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
    }

    /**
     * 將數字字串 一字字存入int 陣列 提供檢核運算使用 若是字串中含有非數字的字元則發生NumberFormatException 回傳陣列設為null num: int 陣列,存放數字字串 len: int 變數,存放字串長度
     * 
     * @param numString
     *            傳入數值
     * @return int 陣列
     */
    public static int[] stringToNumArray(String numString) {
        int[] num = new int[numString.length()];
        try { // 對非數字進行例外處理
            for (int i = 0; i < numString.length(); i++) {
                num[i] = Integer.parseInt("" + numString.charAt(i));
            }
        }
        catch (NumberFormatException e) {
            return new int[0];
        }
        return num;
    }

    /**
     * 檢查是否在範圍中
     * 
     * @param data
     * @param range
     * @return
     */
    public static boolean rangeCheck(String data, String range) {
        boolean returnCode = false;

        List<String> ranges = getRangeList(range);

        // 將範圍判斷由連續性數值改為可連續也可不連續 edit by Ice at 2013.08.07_start
        for (int i = 0; i < ranges.size(); i++) {
            if (ranges.indexOf(data) >= 0) {
                returnCode = true;
                break;

            }
        }
        // 將範圍判斷由連續性數值改為可連續也可不連續 edit by Ice at 2013.08.07_end

        return returnCode;
    }

    /**
     * 
     * @param range
     * @return
     */
    public static List<String> getRangeList(String range) {
        List<String> result = new ArrayList<String>();
        List<String> resultReal = new ArrayList<String>();
        // range 有 1,3 或 1-2,5
        String[] datas = range.split(",");

        // 將範圍判斷由連續性數值改為可連續也可不連續 edit by Ice at 2013.08.07_start
        for (int i = 0; i < datas.length; i++) {
            if (datas[i].indexOf('-') != -1) {
                String[] conRange = datas[i].split("-");
                for (int j = 0; j < conRange.length; j++) {
                    result.add(conRange[j]);
                }
            }
            else {
                resultReal.add(datas[i]);
            }
        }

        if (!result.isEmpty()) {
            try {
                int start = Integer.parseInt(result.get(0));
                int end = Integer.parseInt(result.get(1));

                for (int i = start; i <= end; i++) {
                    resultReal.add(String.valueOf(i));
                }
            }
            catch (NumberFormatException e) {
                if (logger.isDebugEnabled()) {
                    logger.debug("error checking range", e);
                }
                getRangeListByChar(result, resultReal);
            }
        }
        // 將範圍判斷由連續性數值改為可連續也可不連續 edit by Ice at 2013.08.07_end

        return resultReal;
    }

    private static void getRangeListByChar(List<String> result, List<String> resultReal) {
        // 如果range的連續範圍不為數字形態，則轉型為char來做運算
        char[] start = result.get(0).toCharArray();
        char[] end = result.get(1).toCharArray();

        int startReal = 0;
        int endReal = 0;

        if (start.length > 1 || end.length > 1) {
            throw new IllegalArgumentException("Range設定錯誤(空白、特殊符號)，請檢查程式設定。");
        }
        else {
            for (char item : start) {
                startReal = item;
            }

            for (char item : end) {
                endReal = item;
            }

            for (int i = startReal; i <= endReal; i++) {
                resultReal.add(String.valueOf((char) i));
            }
        }
    }

    /**
     * 
     * 檢核時間是否正確(24小時)
     * 
     * @author alexdai
     * @param String
     * @return boolean
     * 
     * 
     */
    public static boolean timeValidator(String timeStr) {
        if (StringUtils.isEmpty(timeStr)) {
            return false;
        }
        String fiahms = timeStr;
        if (fiahms.matches("^[0-9]*") && fiahms.getBytes().length == 6) {
            int h = Integer.parseInt(fiahms.substring(0, 2));
            int m = Integer.parseInt(fiahms.substring(2, 4));
            int s = Integer.parseInt(fiahms.substring(4, 6));
            if (h == 24 && (m != 0 || s != 0)) {
                return false;
            }
            if (h > 24) {
                return false;
            }
            if (m > 60) {
                return false;
            }
            if (s > 60) {
                return false;
            }
        }
        else {
            return false;
        }
        return true;
    }

    /**
     * 檢查字串是否為相同 ex: 11111 or AAAAA
     *
     * @param s
     */
    public static boolean checkTheSameChar(String s) {
        boolean theSame = true;
        char c = s.charAt(0);
        for (int i = 0; i < s.length(); i++) {
            if (c != s.charAt(i)) {
                theSame = false;
                break;
            }
        }
        return theSame;
    }

    /**
     * 檢查字串是否為連續升冪 ex: 12345 or ABCDEFG
     *
     * @param input
     * @return
     */
    public static boolean checkConsecutiveAsc(String input) {
        String tmpStr = input.toUpperCase();
        boolean continuous = true;
        char c = tmpStr.charAt(0);
        for (int i = 1; i < tmpStr.length(); i++) {
            if (tmpStr.charAt(i) != (c + 1)) {
                continuous = false;
                break;
            }
            else {
                c = tmpStr.charAt(i);
            }
        }
        return continuous;
    }

    /**
     * 檢查字串是否為連續降冪 ex: 54321 or FEDCBA
     *
     * @param sinput
     * @return
     */
    public static boolean checkConsecutiveDesc(String sinput) {
        String tmpStr = sinput.toUpperCase();
        boolean continuous = true;
        char c = tmpStr.charAt(0);
        for (int i = 1; i < tmpStr.length(); i++) {
            if (tmpStr.charAt(i) != (c - 1)) {
                continuous = false;
                break;
            }
            else {
                c = tmpStr.charAt(i);
            }
        }
        return continuous;
    }

    /**
     * <p>
     * 檢查字串是否為連續升冪 ex: 12345 or ABCDEFG
     * </p>
     * <p>
     * 檢查字串是否為連續降冪 ex: 54321 or FEDCBA
     * </p>
     *
     * @param s
     * @return
     */
    public static boolean checkConsecutive(String s) {
        return checkConsecutiveAsc(s) || checkConsecutiveDesc(s);
    }

    /**
     * 檢查字串是否包含中文字
     * 
     * @param s
     * @return
     */
    public static boolean checkContainChinese(String s) {
        if (StringUtils.isBlank(s)) {
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
     * 檢查字串是否為全中文
     *
     * @param s
     * @return
     */
    public static boolean checkChinese(String s) {
        if (StringUtils.isBlank(s)) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(UnicodeBlock.of(s.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 檢查字元是否為難字 難字區間：20000-2A6D6 與 2F800-2FA1D
     * 
     * @param c
     * @return
     */
    public static boolean isHardChar(int charCode) {
        return (charCode >= 0x20000 && charCode <= 0x2A6D6) || (charCode >= 0x2F800 && charCode <= 0x2FA1D);
    }

    /**
     * 檢查字串內容，是否包含難字
     * 
     * @param str
     * @return true:有難字
     */
    public static boolean isHaveHardChar(String str) {
        boolean result = false;
        if (StringUtils.isNotBlank(str)) {
            for (char ch : str.toCharArray()) {
                if (ValidatorUtility.isHardChar(ch)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 檢查字串內容，是否包含難字，若有難字，抛出 CommonErrorCode.CONTENT_INCLUDE_HARD_CHAR
     * 
     * @param str
     * @return true:沒有難字
     * @throws ActionException
     */
    public static boolean validateHaveHardChar(String str) throws ActionException {
        if (isHaveHardChar(str)) {
            throw ExceptionUtils.getActionException(CommonErrorCode.CONTENT_INCLUDE_HARD_CHAR);
        }
        return true;
    }

    /**
     * 檢查字元是否為合法中文姓名(含難字邏輯)
     * 
     * @param s
     * @return
     */
    public static boolean checkChineseName(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }

        return name.codePoints().allMatch((charCode) -> {
            return UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(UnicodeBlock.of(charCode)) || isHardChar(charCode);
        });
    }

    /**
     * 消除中文字
     * 
     * @param s
     * @return
     */
    public static String replaceChinese(String s) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(s)) {
            for (int i = 0; i < s.length(); i++) {
                if (!UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(UnicodeBlock.of(s.charAt(i)))) {
                    sb.append(Character.toString(s.charAt(i)));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 以 UTF-8 編碼檢查是否有難字
     * 
     * @param text
     * @return
     */
    public static boolean containsHardCharacter(String text) {
        // 將傳入字串，轉成 UTF-8 編碼
        byte[] utf8Bytes = text.getBytes(StandardCharsets.UTF_8);

        for (int i = 0; i < utf8Bytes.length; i++) {
            byte b = utf8Bytes[i];

            // 對於UTF-8編碼，如果位元組以0開頭，則表示ASCII字符，不是難字
            if ((b & 0b10000000) == 0) {
                continue; // ASCII字符，不是難字
            }

            // 對於UTF-8編碼，判斷位元組是否在0xC0到0xFD之間，如果是，則表示這是多位元組字元的起始位元組
            if ((b & 0b11100000) == 0b11000000) { // 2位元組字符
                if (i + 1 < utf8Bytes.length && (utf8Bytes[i + 1] & 0b11000000) == 0b10000000) {
                    i++; // 跳過下一個位元組
                    continue;
                }
            }
            else if ((b & 0b11110000) == 0b11100000) { // 3位元組字符
                if (i + 2 < utf8Bytes.length && (utf8Bytes[i + 1] & 0b11000000) == 0b10000000 && (utf8Bytes[i + 2] & 0b11000000) == 0b10000000) {
                    i += 2; // 跳過下兩個位元組
                    continue;
                }
            }
            else if ((b & 0b11111000) == 0b11110000) { // 4位元組字符
                if (i + 3 < utf8Bytes.length && (utf8Bytes[i + 1] & 0b11000000) == 0b10000000 && (utf8Bytes[i + 2] & 0b11000000) == 0b10000000 && (utf8Bytes[i + 3] & 0b11000000) == 0b10000000) {
                    i += 3; // 跳過下三個位元組
                    continue;
                }
            }

            // 如果到達這裡，說明該位元組不符合UTF-8編碼規則，可以認為是難字
            return true;
        }

        return false;
    }

    /**
     * 判斷是否有難以顯示的字元(只針對難字檢查)，以MS950編碼進行判斷
     * 
     *
     * @param chkString
     *            檢測對象字符串
     * @return
     */
    public static boolean checkHardWord(String chkString) {
        if (StringUtils.isBlank(chkString)) {
            return false;
        }
        for (int i = 0; i < chkString.length(); i++) {
            String nowword = chkString.substring(i, i + 1);

            byte[] bytes = null;
            try {
                bytes = nowword.getBytes("MS950");
            }
            catch (UnsupportedEncodingException e) {
                bytes = nowword.getBytes();
            }

            if (bytes.length == 2) {
                int high = 128 * (bytes[0] >> 7) * (-1) + (bytes[0] & 127);
                int low = 128 * (bytes[1] >> 7) * (-1) + (bytes[1] & 127);
                if (checkHardWord(high, low)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判斷字符是否為難以顯示的漢字 (只針對難字檢查)
     * 
     *
     * @param high
     *            字符編碼高字節
     * @param low
     *            字符編碼高字節
     * @return
     */
    private static boolean checkHardWord(int high, int low) {
        // 有四組難字範圍
        int[][] Code = { { 250, 64, 254, 254 }, { 142, 64, 160, 254 }, { 129, 64, 141, 254 }, { 198, 161, 200, 254 } };

        for (int i = 0; i < 4; i++) {
            if ((high >= Code[i][0] && high <= Code[i][2]) && (low >= Code[i][1] && low <= Code[i][3])) {
                return true;
            }
        }

        return false;
    }

    /**
     * 取得輸入字串中的難字
     * 
     * @param strValue
     * @param charset
     * @return
     */
    public static String checkInvalidChars(String strValue, String charset) {
        StringBuffer invalidChar = new StringBuffer();
        if (StringUtils.isBlank(strValue)) {
            return "";
        }
        try {
            String output = new String(strValue.getBytes(charset), charset);
            byte[] utf32Bytes = strValue.getBytes("UTF-32");
            if (!StringUtils.equals(strValue, output)) {
                // 找出不相等的字元
                for (int i = 0; i < utf32Bytes.length / 4; i++) {
                    try {
                        String tmpStr = new String(utf32Bytes, i * 4, 4, "UTF-32");
                        if (!StringUtils.equals(tmpStr, StringUtils.mid(output, i, 1))) {
                            invalidChar.append(tmpStr);
                        }
                    }
                    catch (UnsupportedEncodingException | IndexOutOfBoundsException e) {
                        // 因為有可能發生轉換成 Big5 後字串長度較短, 所以在此 catch exception 並終止迴圈
                        break;
                    }
                }
            }
        }
        catch (UnsupportedEncodingException e) {
            logger.error("exec checkInvalidChars error: ", e);
        }
        return invalidChar.toString();
    }

    /**
     * 檢查字串是否符合統一編號規則
     *
     * @param s
     * @return
     */
    public static boolean checkTaxID(String s) {
        if (StringUtils.isBlank(s) || !s.matches("([\\d]{8})")) {
            return false;
        }
        int[] tbNum = { 1, 2, 1, 2, 1, 2, 4, 1 };
        int tmp = 0;
        int summation = 0;
        for (int i = 0; i < tbNum.length; i++) {
            tmp = Integer.parseInt(Character.toString(s.charAt(i))) * tbNum[i];
            summation += Math.floor(((double) tmp) / 10) + tmp % 10;
        }
        if (summation % 10 == 0 || (summation % 9 == 9 && s.charAt(6) == 7)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 檢查字串中是否包含空白
     *
     * @param s
     * @return
     */
    public static boolean checkWhiteSpace(String s) {
        if (s == null) {
            return false;
        }
        return s.contains(" ");
    }

    /**
     * 檢核不合法字元
     *
     * @param input
     * @param specialChar
     * @return
     */
    public static boolean isValidChar(String input, String specialChar) {
        boolean rtnValue = true;
        if (StringUtils.isBlank(input)) {
            return true;
        }
        String tmpSpecialChar = specialChar;
        if (StringUtils.isBlank(tmpSpecialChar)) {
            tmpSpecialChar = "`~!@#$%^&*()-_+={}[]\\|;:'\"<>?,./";
        }
        for (int i = 0; i < input.length(); i++) {
            if (tmpSpecialChar.indexOf(input.charAt(i)) != -1) {
                rtnValue = false;
            }
        }
        return rtnValue;
    }

    public static boolean isValidChar(String input) {
        return isValidChar(input, null);
    }

    /**
     * 檢核是否英數混雜<br>
     * '1a2b': true, '111': false, 'aaa': false
     * 
     * @param input
     * @return
     */
    public static boolean checkAlphanumeric(String input) {
        Pattern p1 = Pattern.compile("([0-9]+)");
        Pattern p2 = Pattern.compile("([a-zA-Z]+)");
        return p1.matcher(input).find() && p2.matcher(input).find() && !ValidatorUtility.checkWhiteSpace(input);
    }

    /**
     * 是否只為英數字<br>
     * '1a2b': true, '111': true, 'aaa': true
     * 
     * @param input
     * @return
     */
    public static boolean checkEngAndNumber(String input) {
        if (StringUtils.isEmpty(input)) {
            return true;
        }
        return input.matches("^[0-9A-Za-z]+$");
    }

    /**
     * 檢核是否英文
     * 
     * @param input
     * @return
     */
    public static boolean checkAlpha(String input) {
        Pattern p1 = Pattern.compile("([a-zA-Z]+)");
        return p1.matcher(input).matches() && !ValidatorUtility.checkWhiteSpace(input);
    }

    /**
     * 檢核是否數字
     * 
     * @param input
     * @return
     */
    public static boolean checkNumeric(String input) {
        Pattern p1 = Pattern.compile("([0-9]+)");
        return p1.matcher(input).matches() && !ValidatorUtility.checkWhiteSpace(input);
    }

    /**
     * 檢核是否是手機
     * 
     * @param input
     * @return
     */
    public static boolean checkMobile(String input) {
        if (StringUtils.isEmpty(input)) {
            return true;
        }
        return input.matches("^09[0-9]{8}$");
    }

    public static boolean isValidEmailMemo(String input) {
        if (StringUtils.isBlank(input)) {
            return true;
        }
        boolean rtnValue = true;
        String[] specialChar = { "'", "</" };
        for (int j = 0; j < specialChar.length; j++) {
            if (input.indexOf(specialChar[j]) >= 0) {
                rtnValue = false;
                break;
            }
        }
        if (!rtnValue) {
            return rtnValue;
        }
        char[] inputArray = input.toCharArray();
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i] < 0 || inputArray[i] > 65535) {
                rtnValue = false;
                break;
            }
        }
        return rtnValue;
    }

    /**
     * 安控規範 1.「使用者代碼」需為 6 至 10 位，「網路銀行密碼」需為 6 至 16 位（英文字一律視為大寫） 2.「使用者代碼」及「網路銀行密碼」須包括英文及數字；不得為相同的英數字、連續英文字或連號數字。(需英數字混雜) 3.使用者代碼及網路銀行密碼設定，請勿使用個人顯性資料（如生日、身份證、車號、電話號碼、帳號及相關資料號碼），以策安全。 (無法檢核) 4.「使用者代碼」或「網路銀行密碼」不得與「身份證字號╱統一編號」全部或部份重複。
     * 5.「網路銀行密碼」不得與「使用者代碼」全部或部份重複。
     *
     * @param userId
     * @param userHint
     *            return 0 : ok, 10: 使用者代碼不符, 1 : 使用者密碼不符, 11: 皆不符
     */
    public static int checkSecuirtyRules(String uid, String userId, String userHint) {
        boolean userIdOk = true;
        boolean userPwdOk = true;
        // 1.「使用者代碼」需為 6 至 10 位，「網路銀行密碼」需為 6 至 16 位（英文字一律視為大寫）
        int length = StringUtils.length(userId);
        if (length < 6 && length > 10) {
            userIdOk = false;
        }
        length = StringUtils.length(userHint);
        if (length < 6 && length > 16) {
            userPwdOk = false;
        }
        String tmpUid = StringUtils.upperCase(uid);
        String tmpUserId = StringUtils.upperCase(userId);
        String tmpUserHint = StringUtils.upperCase(userHint);
        userIdOk &= checkInputFormat(tmpUserId);
        userPwdOk &= checkInputFormat(tmpUserHint);
        // 「使用者代碼」或「網路銀行密碼」不得與「身份證字號╱統一編號」全部或部份重複。
        userIdOk &= !StringUtils.contains(tmpUid, tmpUserId);
        userPwdOk &= !StringUtils.contains(tmpUid, tmpUserHint);
        // 「網路銀行密碼」不得與「使用者代碼」全部或部份重複。
        userPwdOk &= !StringUtils.contains(tmpUserId, tmpUserHint);
        if (userIdOk && userPwdOk) {
            return 0;
        }
        else {
            return ((userIdOk ? 0 : 10) + (userPwdOk ? 0 : 1));
        }
    }

    private static boolean checkInputFormat(String tmpUserId) {
        // 需為 A-Z 0-9, 且不是只有 A-Z & 也不是只有 0-9
        return tmpUserId.matches("^[A-Z0-9]+$") && !tmpUserId.matches("^[A-Z]+$") && !tmpUserId.matches("^[0-9]+$");
    }

    /**
     * 是否為相同英文字母/數字
     * 
     * @param input
     * @return
     */
    public static boolean checkSameAlphabetNumber(String input) {
        return checkSameAlphabet(input) && checkSameNumber(input);
    }

    /**
     * 是否為相同英文字母
     * 
     * @param input
     * @return
     */
    public static boolean checkSameAlphabet(String input) {
        String tmpStr = input.toUpperCase();
        int count = 1;
        for (int i = 0; i < tmpStr.length() - 1; i++) {
            char c = tmpStr.charAt(i);
            int ic = c;
            if (ic < 65 || ic > 90) { // 不在A-Z
                count = 1;
            }
            else if (c == tmpStr.charAt(i + 1)) {
                count++;
            }
            else {
                count = 1;
            }
        }

        if (tmpStr.length() <= count) {
            return false; // 都是相同英文字母
        }
        return true; // 不都是相同英文字母
    }

    /**
     * 檢核是否為相同數字
     *
     * @param input
     */
    public static boolean checkSameNumber(String input) {
        String tmpStr = input.toUpperCase();
        int count = 1;
        for (int i = 0; i < tmpStr.length() - 1; i++) {
            char c = tmpStr.charAt(i);
            int ic = c;
            if (ic < 48 || ic > 57) { // 不在0-9
                count = 1;
            }
            else if (c == tmpStr.charAt(i + 1)) {
                count++;
            }
            else {
                count = 1;
            }
        }

        if (tmpStr.length() <= count) {
            return false; // 都是相同數字
        }
        return true; // 不是相同數字
    }

    // @formatter:off
    /**
     * <p>判斷長度是否超過限制(中文為2，數字為1，半形為1，全形為2)</p>
     * 
     * @param str
     *            輸入的字串
     * @param limit
     *            限制長度
     * @return true:字串長度小於等於限制長度
     */
    // @formatter:on
    public static boolean checkStrLength(String str, int limit) {
        return StringUtils.getStrLength(str) <= limit;
    }

    /**
     * 檢查字串長度
     * 
     * @param str
     * @param min
     * @param max
     * @return
     */
    public static boolean checkLength(String str, int min, int max) {
        String tmpStr = str;
        if (StringUtils.isBlank(tmpStr)) {
            tmpStr = "";
        }
        int size = tmpStr.length();
        return (size >= min && size <= max);
    }

    /**
     * 字串是否符合「中、英、數字」
     * 
     * @param input
     * @return
     */
    public static boolean checkWithoutSymbol(String input) {
        if (StringUtils.isBlank(input)) {
            return true;
        }
        Pattern p1 = Pattern.compile("([a-zA-Z]+)");
        Pattern p2 = Pattern.compile("([0-9]+)");
        char[] arr = input.toCharArray();
        for (char c : arr) {
            // 是否為中文字
            if (UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS.equals(UnicodeBlock.of(c)) || p1.matcher(Character.toString(c)).find() || p2.matcher(Character.toString(c)).find()) {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static boolean validateByRegex(String value, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(value);
        return matcher.matches();
    }

    /**
     * 檢查是否包含表情符號(emoji)
     * 
     * @param fieldContent欄位傳入的數值
     * @return true:內容包含emoji；false:不包含
     */
    public static boolean checkEmoji(String fieldContent) {
        if (StringUtils.isBlank(fieldContent)) {
            return false;
        }
        for (int i = 0; i < fieldContent.trim().length(); i++) {
            if (!notisEmojiCharacter(fieldContent.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 非emoji表情字符判斷
     * 
     * @param codePoint
     * @return
     */
    private static boolean notisEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
    }

    /**
     * 檢核是否任意六個連續字元相同
     * 
     * @param target
     * @param value
     * @return
     */
    public static boolean compareSixSeqCharEqualsChecking(String target, String value) {
        return compareSeqCharEquals(target, value, 6);
    }

    /**
     * 任一數量字串相符檢查
     * 
     * @param target
     * @param value
     * @param compareLen
     * @return
     */
    private static boolean compareSeqCharEquals(String target, String value, int compareLen) {

        if (StringUtils.isAllBlank(target, value)) {
            logger.error("input str all blank");
            return true;
        }

        // fix for fortify 20220926
        // if (compareLen < 0) {
        // logger.error("compareLen need input positive integer");
        // return false;
        // }

        if (!StringUtils.isAnyBlank(target, value)) {

            String targetTrim = StringUtils.trim(target);
            String valueTrim = StringUtils.trim(value);

            int targetLen = StringUtils.length(targetTrim);
            int valueLen = StringUtils.length(valueTrim);

            if (targetLen < compareLen || valueLen < compareLen) {
                return false;
            }

            boolean targetIsLonger = targetLen > valueLen;
            String longOne = targetIsLonger ? StringUtils.upperCase(targetTrim) : StringUtils.upperCase(valueTrim);
            String compared = targetIsLonger ? StringUtils.upperCase(valueTrim) : StringUtils.upperCase(targetTrim);

            for (int i = 0; i <= longOne.length() - compareLen; i++) {
                String searchStr = longOne.substring(i, i + compareLen);
                if (compared.indexOf(searchStr) > -1) {
                    logger.debug(target + " compare to " + value + " has equals str " + searchStr);
                    return true;
                }
            }
        }

        logger.debug("no equals str");

        return false;
    }

    /**
     * 檢核任意四個字元為連續字元
     * 
     * @param target
     * @return
     */
    public static boolean checkConsecutiveFourChar(String target) {
        return checkConsecutiveByLength(target, 4);
    }

    /**
     * 檢核任意四個字元為相同字元
     * 
     * @param target
     * @return
     */
    public static boolean checkSameFourChar(String target) {
        return checkSameCharByLength(target, 4);
    }

    /**
     * 檢核是否為連續字元
     * 
     * @param target
     * @param compareLen
     * @return
     */
    private static boolean checkConsecutiveByLength(String target, int compareLen) {

        if (StringUtils.isBlank(target)) {
            logger.error("target is blank");
            return false;
        }

        // fix for fortify 20220926
        // if (compareLen < 0) {
        // logger.error("compareLen need input positive integer");
        // return false;
        // }

        String targetTrim = StringUtils.trim(target);
        int targetTrimLen = StringUtils.length(targetTrim);
        if (targetTrimLen < compareLen) {
            logger.debug("target lenght: " + targetTrimLen + " is shorter then compare length: " + compareLen);
            return false;
        }

        for (int i = 0; i <= targetTrimLen - compareLen; i++) {
            String searchStr = StringUtils.substring(targetTrim, i, i + compareLen);

            if (ValidatorUtility.checkConsecutiveAsc(searchStr) || StringUtils.indexOf(CONSECUTIVE_NUM, searchStr) > 0) {
                logger.debug("find consecutive str" + searchStr);
                return true;
            }

        }
        return false;
    }

    /**
     * 檢核是否為連續相同字元
     * 
     * @param target
     * @param compareLen
     * @return
     */
    private static boolean checkSameCharByLength(String target, int compareLen) {
        if (StringUtils.isBlank(target)) {
            logger.error("target is blank");
            return false;
        }
        // fix for fortify 20220926
        // if (compareLen < 0) {
        // logger.error("compareLen need input positive integer");
        // return false;
        // }
        String targetTrim = StringUtils.trim(target);
        int targetTrimLen = StringUtils.length(targetTrim);
        if (targetTrimLen < compareLen) {
            logger.debug("target lenght: " + targetTrimLen + " is shorter then compare length: " + compareLen);
            return false;
        }

        for (int i = 0; i <= targetTrimLen - compareLen; i++) {
            String searchStr = StringUtils.substring(targetTrim, i, i + compareLen);
            if (checkTheSameChar(searchStr)) {
                logger.debug("find consecutive str" + searchStr);
                return true;
            }

        }
        return false;
    }

    /**
     * 兩輸入值是否相似 1. 兩輸入長度皆 <= 6 ，全比 2. 兩輸入長度皆 > 6 (1) 比前六碼 (2) 比後六碼
     * 
     * @param inputA
     * @param inputB
     * @param compareStartAndEnd
     *            : ture 比前後六碼, false 只比前六碼
     * @return
     */
    public static boolean isTwoInputAlike(String inputA, String inputB, boolean compareStartAndEnd) {
        boolean isLike = false;
        if (StringUtils.isBlank(inputA) || StringUtils.isBlank(inputB)) {
            return false;
        }

        int inputALen = StringUtils.length(inputA);
        int inputBLen = StringUtils.length(inputB);
        if (inputALen <= 6 && inputBLen <= 6) {
            if (StringUtils.equals(inputA, inputB)) {
                isLike = true;
            }
        }
        else {
            if (StringUtils.equals(inputA.substring(0, 6), inputB.substring(0, 6))) {
                isLike = true;
            }
            if (compareStartAndEnd) {
                if (StringUtils.equals(inputA.substring(inputALen - 6, inputALen), inputB.substring(inputALen - 6, inputBLen))) {
                    isLike = true;
                }
            }
        }
        return isLike;
    }

    /**
     * 是否有全型字
     * 
     * @param str
     * @return
     */
    public static boolean checkHaveFullWidthCharacters(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        List<String> strList = str.chars().mapToObj(c -> String.valueOf((char) c)).collect(Collectors.toList());

        return strList.stream().anyMatch(s -> s.matches("^[^\\x00-\\xff]*$"));
    }

    /**
     * 判斷是否為外國人「ID」
     * <ol>
     * <li>2碼英文+8碼數字</li>
     * <li>1碼英文+9碼數字，且第二碼為 8 或 9</li>
     * <li>OBU-境外個人-無統一證號：8碼數字+2碼英文</li>
     * </ol>
     * 
     * @param custId
     * @return
     */
    public static boolean isForeignId(String custId) {
        String tmpCustId = StringUtils.trimToEmptyEx(custId);
        if (StringUtils.length(tmpCustId) != 10) {
            return false;
        }
        return isForeignNo(tmpCustId) || (NumberUtils.isParsable(StringUtils.left(tmpCustId, 8)) && StringUtils.isAlpha(StringUtils.right(tmpCustId, 2)));
    }

    /**
     * 判斷是否為外國人「統一證號」
     * <ol>
     * <li>2碼英文+8碼數字</li>
     * <li>1碼英文+9碼數字，且第二碼為 8 或 9</li>
     * </ol>
     * 
     * @param custId
     * @return
     */
    public static boolean isForeignNo(String custId) {
        String tmpCustId = StringUtils.trimToEmptyEx(custId);
        if (StringUtils.length(tmpCustId) != 10) {
            return false;
        }
        // 2碼英文+8碼數字
        if (StringUtils.isAlpha(StringUtils.left(tmpCustId, 2)) && NumberUtils.isParsable(StringUtils.right(tmpCustId, 8))) {
            return true;
        }
        // 1碼英文+9碼數字，且第二碼為 8 或 9
        else if (StringUtils.isAlpha(StringUtils.left(tmpCustId, 1)) && NumberUtils.isParsable(StringUtils.right(tmpCustId, 9))) {
            // 第二碼為 8 or 9
            if (ArrayUtils.contains(new String[] { "8", "9" }, StringUtils.substring(tmpCustId, 1, 2))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 檢核市內電話
     * 
     * @param phone
     *            電話號碼(包含區碼)
     * @return
     */
    public static boolean checkPhone(String phone) {
        if (StringUtils.isEmpty(phone) || StringUtils.length(phone) < 8) {
            return false;
        }

        if (!checkNumeric(phone)) {
            return false;
        }

        String areaCode = StringUtils.substring(phone, 0, 3);
        if (StringUtils.equals(areaCode, "049")) {
            return StringUtils.length(phone) == 10;
        }

        areaCode = phone.substring(0, 2);
        switch (areaCode) {
        case "02":
            return StringUtils.length(phone) == 10;
        case "04":
            return StringUtils.length(phone) >= 9;
        case "03":
        case "05":
        case "06":
        case "07":
        case "08":
            return StringUtils.length(phone) == 9;
        default:
            return StringUtils.length(phone) >= 8;
        }
    }

    /**
     * 檢核市內電話及區碼
     * 
     * @param areaCode
     *            區碼
     * @param phone
     *            電話號碼 (不包含區碼)
     * @return
     */
    public static boolean checkAreaCodeAndPhone(String areaCode, String phone) {
        if (StringUtils.isEmpty(phone) || StringUtils.length(phone) < 8) {
            return false;
        }

        switch (areaCode) {
        case "02":
            return StringUtils.length(phone) == 8;
        case "04":
            return StringUtils.length(phone) == 7 || StringUtils.length(phone) == 8;
        case "03":
        case "05":
        case "06":
        case "07":
        case "08":
        case "049":
            return StringUtils.length(phone) == 7;
        case "037":
        case "089":
            return StringUtils.length(phone) == 6;
        default:
            return StringUtils.length(phone) >= 5 && StringUtils.length(phone) <= 8;
        }

    }

    /**
     * 檢核市內電話
     * 
     * @param areaCode
     *            區碼
     * @param phone
     *            電話號碼(不包含區碼)
     * @return
     */
    public static boolean checkPhone(String areaCode, String phone) {
        if (StringUtils.isBlank(areaCode) || StringUtils.isBlank(phone)) {
            return false;
        }

        int len = StringUtils.length(phone);
        boolean isOK = false;
        switch (areaCode) {
        case "02":
            isOK = len == 8;
            break;
        case "04":
            isOK = len >= 7;
            break;
        case "03":
            isOK = len == 7;
            break;
        case "05":
            isOK = len == 7;
            break;
        case "06":
            isOK = len == 7;
            break;
        case "07":
            isOK = len == 7;
            break;
        case "08":
            isOK = len == 7;
            break;
        case "049":
            isOK = len == 7;
            break;
        case "037":
            isOK = len == 6;
            break;
        case "089":
            isOK = len == 6;
            break;
        default:
            isOK = len >= 5;
        }
        return isOK;
    }

    public static boolean isValidDateFormat(DateTimeFormatter formatter, String dateString) {
        try {
            formatter.parse(dateString);
        }
        catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 驗證 外國人證號
     * 
     * @param userID
     * @return 是否為外國人證號
     */
    public static boolean checkForeignUid(String userID) {

        if (userID.matches("[A-Z]{1}[0-9]{9}")) {

            /* 驗證外國人證號 start */
            Integer sum = 0;

            /**
             * A 8 0 0 0 0 0 0 1 4(身份證字號 最後一碼為檢核碼) 1 0 8 0 0 0 0 0 0 1 1 9 8 7 6 5 4 3 2 1 1 0 64 0 0 0 0 0 0 1 (取個位數) 1 + 4 + 0 + 0 + 0 + 0 + 0 + 0 + 1 = 6 (總計取個位數)
             * 
             * 10 - 6(總計) = 4 (檢核碼)
             */
            for (Integer pos = 0; pos < 9; pos++) {

                if (pos == 0) {

                    Integer[] forA = FOREIGNA.get(userID.substring(0, 1));
                    sum = sum + (forA[0] * FOREIGNERIDB[0] % 10);
                    sum = sum + (forA[1] * FOREIGNERIDB[1] % 10);
                }
                else {
                    sum = sum + (Integer.parseInt(userID.substring(pos, pos + 1)) * FOREIGNERIDB[pos + 1] % 10);
                }
            }

            Integer checksum = 10 - (sum % 10);
            if (checksum == Integer.parseInt(userID.substring(9, 10))) {
                return true;
            }
            /* 驗證外國人證號 end */

        }

        return false;
    }

    /**
     * 驗證是否為 居留證證號
     * 
     * @param userID
     * @return 是否為居留證證號
     */
    public static boolean checkRCNumber(String userId) {

        Integer verifyNum = 0;
        final char[] uesrIdArr = userId.toCharArray();// 字串轉成char陣列
        if (userId.matches("[A-Z]{1}[A-D]{1}[0-9]{8}")) {
            // 第一碼
            verifyNum += pidResidentFirstInt[Arrays.binarySearch(pidCharArray, uesrIdArr[0])];
            // 第二碼
            verifyNum += pidResidentSecondInt[Arrays.binarySearch(pidCharArray, uesrIdArr[1])];
            // 第三~八碼
            for (int i = 2, j = 7; i < 9; i++, j--) {
                verifyNum += Character.digit(uesrIdArr[i], 10) * j;
            }
            // 檢查碼
            verifyNum = (10 - (verifyNum % 10)) % 10;
            return verifyNum == Character.digit(uesrIdArr[9], 10);
        }
        return false;
    }

    /**
     * 驗證是否為 外國人證號 、身份證字號或是居留證證號
     * 
     * @param userID
     * @return 是否
     */
    public static boolean checkIDOrForUidUser(String userID) {

        return checkForeignUid(userID) || checkID(userID) || checkRCNumber(userID);
    }

    /**
     * 是否為整數
     */
    public static boolean isInteger(BigDecimal value) {
        if (value == null) {
            return false;
        }
        return value.scale() == 0 || value.stripTrailingZeros().scale() == 0;
    }

    /**
     * 是否為正數
     */
    public static boolean isPositiveNumber(BigDecimal value) {
        if (value == null) {
            return false;
        }
        return value.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * 是否為正整數
     */
    public static boolean isPositiveInteger(BigDecimal value) {
        return isInteger(value) && isPositiveNumber(value);
    }

    /**
     * 是否英數字及中文字
     * 
     * @return
     */
    public static boolean isEngAndNumberAndChinese(String value) {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        for (int i = 0; i < value.length(); i++) {

            if (!checkEngAndNumber(Character.toString(value.charAt(i))) && !checkChinese(Character.toString(value.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCorpId(String id) {
        if (!checkNumeric(id)) {
            return false;
        }

        int v[] = { 1, 2, 1, 2, 1, 2, 4, 1 };
        int A1[] = new int[8];
        int A2[] = new int[8];
        int B = 0;
        int B1 = 0;
        for (int i = 0; i < v.length; i++) {
            A1[i] = Integer.parseInt(String.valueOf(id.charAt(i))) * v[i];
        }

        for (int i = 0; i < A1.length; i++) {
            if (A1[i] < 10) {
                A2[i] = A1[i];
            }
            else {
                A2[i] = Integer.parseInt(String.valueOf(A1[i]).substring(0, 1)) + Integer.parseInt(String.valueOf(A1[i]).substring(1, 2));
            }
        }

        for (int i = 0; i < A2.length; i++) {
            B = B + A2[i];
        }

        if (B % 10 == 0) {
            return true;

        }
        else {
            if (id.charAt(6) == 7) {
                B = A2[0] + A2[1] + A2[2] + A2[3] + A2[4] + A2[5] + 0 + A2[7];
                B1 = A2[0] + A2[1] + A2[2] + A2[3] + A2[4] + A2[5] + 1 + A2[7];
                if ((B % 10 == 0) || (B1 % 10 == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 檢查外幣金額
     * 
     * <pre>
     * (1)限輸入最多輸入10碼，若有輸入小數點則佔用1碼。 
     * A. 日幣：檢核僅能輸入整數位。 
     * B. 其他外幣：檢核最多僅能輸入2位小數點。
     * </pre>
     */
    public static boolean checkForeignMoney(String value, String curId, int size) {
        if (value == null || size <= 0) {
            return false;
        }
        // 檢查長度
        if (value.length() > size) {
            return false;
        }
        // 檢查格式
        int scaleSize = Arrays.asList("TWD", "JPY").contains(curId) ? 0 : 2;
        String pattern = scaleSize == 0 ? "[0-9]*" : "[0-9]+([.][0-9]{1," + scaleSize + "})?";
        return value.matches(pattern);
    }

    /**
     * 檢核是否英文名字
     *
     * @param input
     * @return
     */
    public static boolean isEnglishName(String input) {
        Pattern p1 = Pattern.compile("^[A-Za-z]+([\\ A-Za-z]+)*");
        return p1.matcher(input).matches();
    }

    /**
     * 計算字元長度是否符合最大長度:<br>
     * 1 個全形字元視為 2 個半形字元<br>
     * 1 個全形注音視為 2 個半形字元<br>
     * 1 個半形空白視為 2 個半形字元<br>
     * 1 個全形空白視為 2 個半形字元<br>
     * 1 個全形標點符號視為 2 個半形字元<br>
     *
     * 超過最大長度回 false 未超過最大長度回 true
     * 
     * 
     * @param value:要檢核的字串
     * @param length:限制長度
     * @returns boolean:是否符合規範
     */
    public static boolean characterLength(String value, int length) {
        if (StringUtils.isBlank(value)) {
            return false;
        }

        if (length < 0) {
            length = 0;
        }

        int valueLength = 0;
        for (int i = 0; i < value.length(); i++) {
            char charCode = value.charAt(i);

            if ((charCode >= 0x4E00 && charCode <= 0x9FA5) || // 全形中文
                    (charCode >= 0x3105 && charCode <= 0x3129) || // 全形注音符號
                    (charCode >= 0x31A0 && charCode <= 0x31BA) || // 注音擴展
                    charCode == 0x20 || // 半形空白
                    charCode == 0x3000 || // 全形空白
                    StringUtils.indexOf("，。、！？：（）「」『』【】《》", value) >= 0 // 全形標點符號
            ) {
                // 全形字元或全形標點符號，計算為 2 個半形字元
                valueLength += 2;
            }
            else {
                // 半形字元，計算為 1 個半形字元
                valueLength += 1;
            }
        }

        return valueLength <= length;
    }

    /**
     * 備註檢核
     * 
     * (A). 全形中文 <br>
     * (B). 半形英文 <br>
     * (C). 半形數字 <br>
     * (D). 全形注音符號 <br>
     * (E). 全形標點符號，限逗號(，)、句號(。)、頓號(、)、驚嘆號(！)、問號(？)、冒號(：)、左右括號(（）)、上下引號(「」“) <br>
     * (F). 除前開正面表列，其餘皆不允許
     * 
     * @param value:要檢核的字串
     * @return boolean:是否符合規範
     */
    public static boolean checkMemo(String value) {
        if (value == null) {
            return false;
        }
        String regex = "^[\u4E00-\u9FFFA-Za-z0-9\u3100-\u312F\uFF0C\u3002\u3001\uFF01\uFF1F\uFF1A\uFF08\uFF09\u300C\u300D\u201C\u201D\u3000\\s]*$";
        return StringUtils.trim(value).matches(regex);
    }

}
