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
package com.ibm.tw.ibmb.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.comparators.ComparableComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.lang3.RegExUtils;
import org.slf4j.MDC;
import org.springframework.core.env.Environment;
import org.springframework.util.ReflectionUtils;

import com.google.gson.Gson;
import com.ibm.tw.commons.error.ErrorStatus;
import com.ibm.tw.commons.error.IErrorCode;
import com.ibm.tw.commons.error.SeverityType;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.base.Constants;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.component.context.ExecutionContext;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.component.errorcode.ErrorCodeCacheManager;
import com.ibm.tw.ibmb.component.errorcode.ErrorCodeData;
import com.ibm.tw.ibmb.component.errorcode.ErrorInfoData;
import com.ibm.tw.ibmb.type.LoginSystemType;
import com.ibm.tw.ibmb.type.NotBeanType;

// @formatter:off
/**
 * @(#)IBUtils.java
 * 
 * <p>Description:IB 共用工具集</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/05, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 * v1.1, 2025/05/05, Edward Tien    
 * <ol>
 *  <li>數字(Number)轉換成金錢格式的字串，請使用 formatMoneyStr 系列</li>
 *  <li>數字(Number)轉換成金錢格式的字串，前綴幣別，請使用 displayMoneyStr 系列</li>
 *  <li>字串轉換成金錢格式的字串，請使用 showMoneyStr 系列</li>
 * </ol>
 */
// @formatter:on
public class IBUtils {

    protected static IBLog logger = IBLog.getLog(IBUtils.class);

    /** 負號 */
    public static final String NEGATIVE_SIGN = "-";

    private static ThreadLocal<String> channelIdThreadLocal = new ThreadLocal<>();
    private static final ThreadLocal<ExecutionContext> EXEC_CTX_THREAD_LOCAL = new ThreadLocal<>();

    static {
        channelIdThreadLocal.set(LoginSystemType.AIBANK_APP.getChannelId());
    }

    protected IBUtils() {
    }

    /**
     * 身份證號檢核
     *
     * @return
     */
    public static boolean checkPersonUid(String uid) {
        String upperCaseTable = "ABCDEFGHJKLMNPQRSTUVXYWZIO"; // 請注意W的位置,I和O二字母置最未
        int d1 = upperCaseTable.indexOf(uid.substring(0, 1)) + 10;
        int d2 = upperCaseTable.indexOf(uid.substring(1, 2)) % 10;
        String tmpUid = uid;
        // 身份證字號 (國人1,2) || 外國人新式身分證 (8,9)
        if (tmpUid.matches("^[A-Z][1,2,8,9][0-9]{8}$")) {
            tmpUid = String.valueOf(d1) + tmpUid.substring(1, 10);
        }
        // 統一證號 (外國人)
        else if (tmpUid.matches("^[A-Z][A-D][0-9]{8}$")) {
            tmpUid = String.valueOf(d1) + String.valueOf(d2) + tmpUid.substring(2, 10);
        }
        else {
            return false;
        }
        // checksum
        int checksumId = ConvertUtils.str2Int(tmpUid.substring(0, 1));
        for (int i = 1; i <= 9; i++) {
            checksumId += ConvertUtils.str2Int(tmpUid.substring(i, i + 1)) * (10 - i);
        }
        checksumId = checksumId % 10; // 若除10餘0，則取0
        if (checksumId > 0) {
            checksumId = 10 - checksumId;
        }
        return checksumId == ConvertUtils.str2Int(tmpUid.substring(10, 11));
    }

    /**
     * 統一格式, 取得顯示用的金額(含幣別) 輸出樣式 -> NTD12,234,555.03
     *
     * <pre>
     * formatMoney(null, null, null) = &quot;&quot;
     * formatMoney(&quot;&quot;, &quot;1234&quot;, &quot;&quot;) = 1,234
     * formatMoney(&quot;NTD&quot;, &quot;012345.0120&quot;, &quot; &quot;) = NTD 12,345.012
     * </pre>
     *
     * @param currencyId
     * @param money
     * @param delim
     * @return
     * @see IBUtils#displayMoneyStr(String, BigDecimal, int, String)
     */
    @Deprecated
    public static String formatMoney(String currencyId, BigDecimal money, int scale, String delim) {
        String moneyStr = ConvertUtils.bigDecimal2Str(money, scale);
        String sMoneyString = StringUtils.getMoneyStr(moneyStr, scale);
        if (StringUtils.isNotBlank(sMoneyString)) {
            return currencyId + delim + sMoneyString;
        }
        else {
            return sMoneyString;
        }

    }

    /**
     * 統一格式, ${delim} 在正負號與金額之間 -> -$12,234,555.03 or $12,234,555.03
     *
     * <pre>
     * formatMoney(123.1, 2, "$") = -$123.10
     * formatMoney(123.1, 0, "$") = $123.1
     * </pre>
     *
     * @param currencyId
     * @param money
     * @param delim
     * @return
     * @see IBUtils#displayMoneyStr(BigDecimal, int, String)
     */
    @Deprecated
    public static String formatMoney(BigDecimal money, int scale, String delim) {
        if (money == null) {
            money = BigDecimal.ZERO;
        }
        String moneyStr = ConvertUtils.bigDecimal2Str(money.abs(), scale);
        String sMoneyString = StringUtils.getMoneyStr(moneyStr, scale);
        if (money.intValue() < 0) {
            return NEGATIVE_SIGN + delim + sMoneyString;
        }
        else {
            return delim + sMoneyString;
        }
    }

    /**
     * 取得顯示用的金額(含幣別) 輸出樣式 -> NTD 12,234,555.03
     *
     * <pre>
     * formatMoney(null, null) = &quot;&quot;
     * formatMoney(&quot;&quot;, &quot;1234&quot;) = 1,234
     * formatMoney(&quot;NTD&quot;, &quot;012345.0120&quot;) = NTD 12,345.012
     * </pre>
     *
     * @param currencyId
     * @param money
     * @return
     * @see IBUtils#displayMoneyStr(String, BigDecimal)
     */
    @Deprecated
    public static String formatMoney(String currencyId, BigDecimal money) {
        return formatMoney(currencyId, money, -1, " ");
    }

    /**
     * 取得顯示用的金額 輸出樣式 -> 12,234,555.03
     *
     * @param money
     * @return
     * @see IBUtils#formatMoneyStr(BigDecimal)
     */
    @Deprecated
    public static String formatMoney(BigDecimal money) {
        return formatMoney(money, (money == null ? 0 : money.scale()));
    }

    /**
     * 取得顯示用的金額輸出樣式 -> 12,234,555.03，採四捨五入進位
     * <p>
     * 臺幣顯示整數，外幣顯示小數兩位
     * </p>
     * 
     * @param money
     * @param curCode
     *            幣別，臺幣顯示整數，外幣顯示小數兩位
     * @return
     * @see IBUtils#formatMoneyStr(BigDecimal, String)
     */
    @Deprecated
    public static String formatMoney(BigDecimal money, String curCode) {
        return formatMoney(money, curCode, RoundingMode.HALF_UP);
    }

    /**
     * 取得顯示用的金額輸出樣式 -> 12,234,555.03，自行指定進位方式
     * <p>
     * 臺幣顯示整數，外幣顯示小數兩位
     * </p>
     * 
     * @param money
     * @param curCode
     * @param roundingMode
     * @return
     * @see IBUtils#formatMoneyStr(BigDecimal, String, RoundingMode)
     */
    @Deprecated
    public static String formatMoney(BigDecimal money, String curCode, RoundingMode roundingMode) {
        int scale = getScaleWithCurCode(curCode);
        BigDecimal value = money;
        if (scale >= 0) {
            value = NumberUtils.setScale(money, scale, roundingMode);
        }
        return formatMoney(value, scale);
    }

    /**
     * 取得顯示用的金額輸出樣式 -> 12,234,555.03
     *
     * <pre>
     * formatMoney(null, 1) = &quot;&quot;
     * formatMoney(1234567.89, 2) = &quot;1,234,567.89&quot;
     * formatMoney(1234567.00, 1) = &quot;1,234,567.0&quot;;
     * formatMoney(1234567.0100, 2) = &quot;1,234,567.01&quot;;
     * </pre>
     *
     * @param money
     * @param iScale
     * @return
     * @see IBUtils#formatMoneyStr(BigDecimal, int)
     */
    @Deprecated
    public static String formatMoney(BigDecimal money, int iScale) {
        return formatMoney("", money, iScale, "");
    }

    /**
     * 從 ERROR_CODE 取得錯誤訊息描述(EXTERNAL_DESC)
     * 
     * @param errorCodeCacheManager
     * @param code
     * @param locale
     * @param pageId
     * @return
     */
    public static String getErrorDesc(ErrorCodeCacheManager errorCodeCacheManager, IErrorCode code, Locale locale, String pageId) {
        // 錯誤訊息
        String errorDesc = StringUtils.EMPTY;
        // 從 error code cache 中找出對應的錯誤紀錄
        ErrorCodeData errorCodeData = null;
        try {
            String systemId = code.getError().getSystemId();
            String errorCode = code.getError().getErrorCode();
            ErrorCodeData data = errorCodeCacheManager.getErrorCodeData(systemId, errorCode, locale.toString(), pageId, channelIdThreadLocal.get());
            // #4018 U-13154，增加判斷 DISPLAY_FLAG 規則，值為「0」，視作有取得資料
            if (data != null && ConvertUtils.integer2Int(data.getDisplayFlag()) == 0) {
                errorCodeData = data;
            }
        }
        // fortify: Poor Error Handling: Program Catches NullPointerException (需不影響程運作，因此 cache 所有 exception)
        catch (Exception e) { // 這邊可能會有錯的只有nullpoint
            logger.warn("error getting decription, return original desc", e);
        }
        if (errorCodeData != null) {
            errorDesc = StringUtils.defaultIfEmpty(errorCodeData.getExternalDesc(), errorCodeData.getInternalDesc());
        }
        return errorDesc;
    }

    /**
     * 從 ERROR_CODE 取得錯誤訊息描述(EXTERNAL_DESC)
     * 
     * @param errorCodeCacheManager
     * @param systemId
     * @param errorCode
     * @param locale
     * @param pageId
     * @return
     */
    public static String getErrorDesc(ErrorCodeCacheManager errorCodeCacheManager, String systemId, String errorCode, Locale locale, String pageId) {
        // 錯誤訊息
        String errorDesc = StringUtils.EMPTY;
        // 從 error code cache 中找出對應的錯誤紀錄
        ErrorCodeData errorCodeData = null;
        try {
            ErrorCodeData data = errorCodeCacheManager.getErrorCodeData(systemId, errorCode, locale.toString(), pageId, channelIdThreadLocal.get());
            // #4018 U-13154，增加判斷 DISPLAY_FLAG 規則，值為「0」，視作有取得資料
            if (data != null && ConvertUtils.integer2Int(data.getDisplayFlag()) == 0) {
                errorCodeData = data;
            }
        }
        // fortify: Poor Error Handling: Program Catches NullPointerException (需不影響程運作，因此 cache 所有 exception)
        catch (Exception e) {
            logger.warn("error getting decription, return original desc", e);
        }
        if (errorCodeData != null) {
            errorDesc = StringUtils.defaultIfEmpty(errorCodeData.getExternalDesc(), errorCodeData.getInternalDesc());
        }
        return errorDesc;
    }

    /**
     * 若訊息內容夾帶參數，將參數與字串整併成完整內容
     * 
     * @param desc
     * @param params
     * @return
     */
    public static String getDescWithParams(String desc, Object... params) {
        if (StringUtils.isBlank(desc) || params == null || params.length == 0) {
            return desc;
        }
        String tmpDesc = desc;
        if (params != null) {
            // replace ' with '', since MessageFormat.format() will remove them
            tmpDesc = tmpDesc.replaceAll("'", "''");
            tmpDesc = MessageFormat.format(tmpDesc, params);
        }
        return tmpDesc;
    }

    /**
     * 從 ERROR_CODE 取得錯誤訊息描述
     *
     * @param errorCodeCacheManager
     * @param status
     * @param locale
     * @param params
     * @param channelId
     * @return
     */
    public static ErrorDescription getErrorDescMessage(ErrorCodeCacheManager errorCodeCacheManager, ErrorStatus status, String channelId, Locale locale, String pageId, Object... params) {
        // 系統代碼
        String systemId = status.getSystemId();
        // 錯誤代碼
        String errorCode = status.getErrorCode();
        // 狀態等級
        SeverityType severity = status.getSeverity();
        // 錯誤訊息
        // #4504 0823 System Information Leak: External 暫時修改
        String errorDesc = status.getErrorDesc();
        // 額外資訊
        Map<String, String> extras = status.getExtras();
        // 錯誤訊息
        String errorDetail = StringUtils.EMPTY;

        // 從 error code cache 中找出對應的錯誤紀錄
        ErrorCodeData errorCodeData = null;
        try {
            ErrorCodeData data = errorCodeCacheManager.getErrorCodeData(systemId, errorCode, locale.toString(), pageId, channelId);
            // #4018 U-13154，增加判斷 DISPLAY_FLAG 規則，值為「0」，視作有取得資料
            // fortify:
            if (data != null && ConvertUtils.integer2Int(data.getDisplayFlag()) == 0) {
                errorCodeData = data;
            }
        }
        // fortify: Poor Error Handling: Program Catches NullPointerException (需不影響程運作，因此 cache 所有 exception)
        catch (Exception e) {
            logger.warn("error getting decription, return original desc", e);
        }

        if (errorCodeData != null) {
            errorDesc = StringUtils.defaultIfEmpty(errorCodeData.getExternalDesc(), status.getErrorDesc());
            errorDetail = StringUtils.defaultString(errorCodeData.getExternalDesc());
        }
        else {
            IBSystemId systemIdType = IBSystemId.bySystemId(systemId);
            // 在 DB.AI_ERROR_CODE 查無資料且 IBSystemId 不屬於「忽略不替換系統預設」，將錯誤訊息改成【預設錯誤】訊息
            if (!systemIdType.ignoreMsgReplacement()) {
                errorDesc = getErrorDesc(errorCodeCacheManager, CommonErrorCode.DEFAULT_ERROR_DESCRIPTION, locale, pageId);
                if (StringUtils.isNotBlank(errorDesc)) {
                    String traceId = MDC.get(MDCKey.traceId.name());
                    // UAT redmine #8564 在訊息後綴添加 TRACE_ID
                    if (StringUtils.equals(StringUtils.right(errorDesc, 1), "。")) {
                        errorDesc = errorDesc + traceId;
                    }
                    else {
                        errorDesc = errorDesc + "；" + traceId;
                    }
                }
                errorDetail = errorDesc;
                params = null; // errorDesc 已經置換，所以將 params 重設為空值
            }
        }
        // 如果有帶參數，進行 format
        if (params != null) {
            errorDesc = getDescWithParams(errorDesc, params);
            if (StringUtils.isNotBlank(errorDetail)) {
                errorDetail = getDescWithParams(errorDetail, params);
            }
        }

        // ErrorCodeData 為空值時，表示DB查無對應錯誤資料，以 ErrorStatus 為基準，設定錯誤資訊
        if (errorCodeData == null) {
            errorCodeData = new ErrorCodeData();
            errorCodeData.setSystemId(systemId);
            errorCodeData.setErrorCode(errorCode);
            errorCodeData.setPageId(pageId);
            errorCodeData.setLocale(locale.toString());
            errorCodeData.setSeverity(severity.getValue());
            errorCodeData.setExternalDesc(errorDesc);
            errorCodeData.setInternalDesc(errorDesc);
        }

        // 讀取提示文字、關聯交易方式
        ErrorInfoData errorInfoData = getErrorInfoData(errorCodeCacheManager, systemId, errorCode, locale, pageId, channelId);

        // 錯誤系統別 不為 「SVC」、「MB」時，要從 TX_SYSTEM_MAP 讀取對應 SRC_ID 作為顯示用，若查無紀錄，則將 SYSTEM_ID 設為空字串
        if (!StringUtils.equalsAny(status.getSystemId(), IBSystemId.SVC.getSystemId(), IBSystemId.MB.getSystemId())) {
            String txId = extras.get(Constants.SERVICE_CODE_REFERENCE);
            if (StringUtils.isNotBlank(txId)) {
                String srcId = errorCodeCacheManager.getSrcId(txId);
                if (StringUtils.equalsAny(srcId, IBSystemId.APP.getSystemId(), IBSystemId.IMP.getSystemId())) {
                    logger.info("APP、IMP為前台系統保留關鍵字，不能作為替換的系統別。");
                }
                else {
                    logger.info("經過 TX_SYSTEM_MAP 轉換，SYSTEM_ID 從 {} 變成 {}", systemId, srcId);
                    errorCodeData.setSystemId(StringUtils.defaultIfBlank(srcId, systemId));
                }
            }
        }

        return new ErrorDescription(errorDesc, errorDetail, errorCodeData, errorInfoData);
    }

    public static ErrorDescription getErrorDescMessage(ErrorCodeCacheManager errorCodeCacheManager, ErrorStatus status, String channelId, Locale locale, String pageId) {
        return getErrorDescMessage(errorCodeCacheManager, status, channelId, locale, pageId, (Object[]) null);
    }

    public static ErrorDescription getErrorDescMessage(ErrorCodeCacheManager errorCodeCacheManager, ActionException exception, String channelId, Locale locale, String pageId) {
        return getErrorDescMessage(errorCodeCacheManager, exception.getStatus(), channelId, locale, pageId, (Object[]) exception.getParams());
    }

    /**
     * 錯誤訊息的擴充資訊
     * 
     * @param errorCodeCacheManager
     * @param systemId
     * @param errorCode
     * @param userLocale
     * @param pageId
     * @param channelId
     * @return
     */
    public static ErrorInfoData getErrorInfoData(ErrorCodeCacheManager errorCodeCacheManager, String systemId, String errorCode, Locale userLocale, String pageId, String channelId) {
        // 讀取提示文字、關聯交易方式
        ErrorInfoData errorInfoData = null;
        try {
            errorInfoData = errorCodeCacheManager.getErrorInfoData(systemId, errorCode, userLocale.toString(), pageId, channelId);
        }
        catch (ActionException e) {
            logger.warn("error getting decription, return original desc", e);
        }
        return errorInfoData;
    }

    /**
     * 複數欄位排序，將傳入的List<T>，進行排序
     *
     * @param <T>
     *            elements type
     * @param list
     *            要被排序的List
     * @param sortProperties
     *            String[]，elements's property，依此欄位排序
     * @param reverses
     *            boolean[]，是否反轉，true:降冪; false:升冪
     * @param isNullHigh
     *            值為null時，是否視為最大值
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> sort(List<T> list, String[] sortProperties, boolean[] reverses, boolean isNullHigh) {
        // 檢查傳入的參數
        if (list == null) {
            return new ArrayList<T>();
        }
        if (sortProperties == null || sortProperties.length == 0) {
            return list;
        }
        boolean[] tmpReverses = reverses;
        if (tmpReverses == null || tmpReverses.length == 0) {
            tmpReverses = new boolean[sortProperties.length];
        }

        // 排除null的元素(Elements)
        for (Iterator<T> it = list.iterator(); it.hasNext();) {
            if (it.next() == null) {
                it.remove();
            }
        }
        // 設定排序時，允許排序的值為null
        Comparator<T> comparator = ComparableComparator.getInstance();
        // 排序
        ComparatorChain multiSort = new ComparatorChain();
        for (int i = 0; i < sortProperties.length; i++) {
            if (isNullHigh) {
                comparator = ComparatorUtils.nullHighComparator(comparator);
            }
            else {
                comparator = ComparatorUtils.nullLowComparator(comparator);
            }
            @SuppressWarnings("rawtypes")
            BeanComparator beanComparator = new BeanComparator(sortProperties[i], comparator);
            multiSort.addComparator(beanComparator, tmpReverses[i]);
        }

        Collections.sort(list, multiSort);
        return list;
    }

    /**
     * 複數欄位排序，將傳入的List<T>，進行排序，其值為 null 時，視作最小值
     *
     * @param <T>
     *            elements type
     * @param list
     *            要被排序的List
     * @param sortProperties
     *            String[]，elements's property，依此欄位排序
     * @param reverses
     *            boolean[]，是否反轉，true:降冪; false:升冪
     *
     * @return
     */
    public static <T> List<T> sort(List<T> list, String[] sortProperties, boolean[] reverses) {
        return sort(list, sortProperties, reverses, false);
    }

    /**
     * 單一欄位排序，將傳入的List<T>，進行排序
     *
     * @param <T>
     *            elements type
     * @param list
     *            要被排序的List
     * @param sortProperty
     *            elements's property，依此欄位排序
     * @param reverse
     *            是否反轉，true:降冪; false:升冪
     * @param isNullHigh
     *            值為null時，是否視為最大值
     * @return
     */
    public static <T> List<T> sort(List<T> list, String sortProperty, boolean reverse, boolean isNullHigh) {
        // 檢查傳入的參數
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        if (StringUtils.isBlank(sortProperty)) {
            return Collections.emptyList();
        }
        String[] sortProperties = { sortProperty };
        boolean[] reverses = { reverse };
        return sort(list, sortProperties, reverses, isNullHigh);
    }

    /**
     * 單一欄位排序，將傳入的List<T>，進行排序，其值為 null 時，視作最小值
     *
     * @param list
     *            要被排序的List
     * @param sortProperty
     *            elements's property，依此欄位排序
     * @param reverse
     *            是否反轉，true:降冪; false:升冪
     * @return
     */
    public static <T> List<T> sort(List<T> list, String sortProperty, boolean reverse) {
        return sort(list, sortProperty, reverse, false);
    }

    /**
     * 印出物件中所有的屬性值
     *
     * @param instance
     * @return
     */
    public static String attribute2Str(Object instance) {
        if (instance == null) {
            return "";
        }
        Gson gson = GsonHelper.newInstance();
        return gson.toJson(instance);
    }

    /**
     * 以目前 channelId 取得錯誤訊息
     * 
     * @param errorCodeCacheManager
     * @param exception
     * @param locale
     * @return
     */
    public static ErrorDescription getErrorDescMessage(ErrorCodeCacheManager errorCodeCacheManager, ActionException exception, Locale locale, String pageId) {
        return getErrorDescMessage(errorCodeCacheManager, exception, channelIdThreadLocal.get(), locale, pageId);
    }

    /**
     * 以目前 channelId 取得錯誤訊息
     * 
     * @param errorCodeCacheManager
     * @param errorStatus
     * @param locale
     * @param pageId
     * @return
     */
    public static ErrorDescription getErrorDescMessage(ErrorCodeCacheManager errorCodeCacheManager, ErrorStatus errorStatus, Locale locale, String pageId) {
        return getErrorDescMessage(errorCodeCacheManager, errorStatus, channelIdThreadLocal.get(), locale, pageId);
    }

    /**
     * 將目前的 channelId 寫入 ThreadLocal
     * 
     * @param channelId
     *            目前 request 的 channelId
     */
    public static synchronized void setChannelId(String channelId) {
        IBUtils.channelIdThreadLocal.set(channelId);
    }

    public static synchronized String getChannelId() {
        return IBUtils.channelIdThreadLocal.get();
    }

    /**
     * 比較版本碼
     * 
     * @param reqVersion
     * @param pilotVersion
     * @return
     */
    public static boolean isNewerOrEqualsVersion(String reqVersion, String pilotVersion) {
        String[] reqVers = StringUtils.split(reqVersion, ".", 3);
        String[] pilotVers = StringUtils.split(pilotVersion, ".", 3);
        String[] reqVersBase = { "0", "0", "0" };
        String[] pilotVersBase = { "0", "0", "0" };
        System.arraycopy(reqVers, 0, reqVersBase, 0, reqVers.length);
        System.arraycopy(pilotVers, 0, pilotVersBase, 0, pilotVers.length);

        for (int i = 0; i < 3; i++) {
            int reqVer = ConvertUtils.str2Int(reqVersBase[i], 0);
            int pilotVer = ConvertUtils.str2Int(pilotVersBase[i], 0);
            // 同一位比較小，就直接回傳 false
            if (reqVer < pilotVer) {
                return false;
            }
            else if (reqVer > pilotVer) {
                // 比較大時直接回傳 true
                return true;
            }
            // 其它狀況比下一碼
        }
        // 跑到這裡表示三碼都相同，回傳 true
        return true;
    }

    public static <T> int indexOf(List<T> list, Predicate<? super T> predicate) {
        return IntStream.range(0, list.size()).filter(idx -> predicate.test(list.get(idx))).findFirst().orElse(-1);
    }

    /**
     * 查看參數是否是在urlencode的情況，把所有%排除
     * 
     * @param param
     * @return
     */
    public static String decodeParam(String param) {
        if (!StringUtils.contains(param, "%")) {
            return param;
        }
        try {
            param = URLDecoder.decode(param, "UTF-8");
            return decodeParam(param);
        }
        catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
            return param;
        }
    }

    public static synchronized ExecutionContext getExecContext() {
        ExecutionContext executionContext = EXEC_CTX_THREAD_LOCAL.get();
        return executionContext == null ? new ExecutionContext() : executionContext;
    }

    public static synchronized void setExecContext(ExecutionContext context) {
        EXEC_CTX_THREAD_LOCAL.set(context);
    }

    public static void clearExecContext() {
        EXEC_CTX_THREAD_LOCAL.remove();
    }

    // @formatter:off
    /**
     * @(#)IBUtils.java
     * 
     * <p>Description:包裝 錯誤資料 的物件</p>
     * 
     * <p>Modify History:</p>
     * v1.0, 2023/06/05, Edward Tien	
     * <ol>
     *  <li>初版</li>
     * </ol>
     */
    // @formatter:on
    public static class ErrorDescription {

        /** 錯誤系統 */
        private String sys;

        /** 錯誤代號 */
        private String code;

        /** 錯誤訊息 */
        private String desc;

        /** 錯誤訊息 */
        private String errorDesc;

        /** 錯誤訊息 */
        private String errorDetail;

        /** 錯誤註記，0:引導；1:錯誤 */
        private int errorFlag = 1;

        /** 錯誤標題 */
        private String title = "交易錯誤";

        /** 提示文字區塊 */
        private String info;

        /** 轉導按鈕名稱1 */
        private String directButtonName1;

        /** 轉導交易1 */
        private String directTaskId1;

        /** 轉導按鈕名稱2 */
        private String directButtonName2;

        /** 轉導交易2 */
        private String directTaskId2;

        public ErrorDescription(String errorDesc, String errorDetail, ErrorCodeData errorCodeData, ErrorInfoData errorInfoData) {
            this.desc = errorDesc;
            this.errorDesc = errorDesc;
            this.errorDetail = errorDetail;
            if (errorCodeData != null) {
                this.sys = errorCodeData.getSystemId();
                this.code = errorCodeData.getErrorCode();
                this.errorFlag = errorCodeData.getErrorFlag() == null ? 1 : errorCodeData.getErrorFlag();
                // #7671 [英文版]NDSAG001_常用與約定帳號管理：共通錯誤頁應顯示英文
                // 前端再用預設多語系文字取代
                this.title = StringUtils.defaultString(errorCodeData.getTitle());
            }
            if (errorInfoData != null) {
                this.info = errorInfoData.getInfo();
                this.directButtonName1 = errorInfoData.getDirectButtonName1();
                this.directTaskId1 = errorInfoData.getDirectTaskId1();
                this.directButtonName2 = errorInfoData.getDirectButtonName2();
                this.directTaskId2 = errorInfoData.getDirectTaskId2();
            }
        }

        public String getSys() {
            return sys;
        }

        public void setSys(String sys) {
            this.sys = sys;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getErrorDesc() {
            return errorDesc;
        }

        public void setErrorDesc(String errorDesc) {
            this.errorDesc = errorDesc;
        }

        public String getErrorDetail() {
            return errorDetail;
        }

        public void setErrorDetail(String errorDesc) {
            this.errorDetail = errorDesc;
        }

        public int getErrorFlag() {
            return errorFlag;
        }

        public void setErrorFlag(int errorFlag) {
            this.errorFlag = errorFlag;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDirectButtonName1() {
            return directButtonName1;
        }

        public void setDirectButtonName1(String directButtonName1) {
            this.directButtonName1 = directButtonName1;
        }

        public String getDirectTaskId1() {
            return directTaskId1;
        }

        public void setDirectTaskId1(String directTaskId1) {
            this.directTaskId1 = directTaskId1;
        }

        public String getDirectButtonName2() {
            return directButtonName2;
        }

        public void setDirectButtonName2(String directButtonName2) {
            this.directButtonName2 = directButtonName2;
        }

        public String getDirectTaskId2() {
            return directTaskId2;
        }

        public void setDirectTaskId2(String directTaskId2) {
            this.directTaskId2 = directTaskId2;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    /**
     * 取得本機 host name 作為 instance id
     * 
     * @return
     */
    public static String getLocalHostName() {
        String hostName = "localhost";
        try {
            String ip = ValidateParamUtils.validParam(InetAddress.getLocalHost().getHostAddress()); // fortify Often Misused: Authentication: ip not used for authentication, not an issue
            InetAddress addr = InetAddress.getByName(ValidateParamUtils.validParam(ip)); // fortify Often Misused: Authentication: address not used for authentication, not an issue
            hostName = ValidateParamUtils.validParam(addr.getCanonicalHostName()); // fortify Often Misused: Authentication: hostname not used for authentication, not an issue
        }
        catch (UnknownHostException e) {
            logger.debug("this should not happend", e);
        }
        return hostName;
    }

    /**
     * 用 hostname + appname + port 作為 instanceid
     * 
     * @param environment
     * @return
     */
    public static String getInstanceId(Environment environment) {
        String podName = environment.getProperty("POD_NAME");
        if (StringUtils.isNotBlank(podName)) {
            return podName;
        }
        return IBUtils.getLocalHostName() + "_" + environment.getProperty("spring.application.name", "unknown") + "_" + environment.getProperty("server.port", "-");
    }

    public static void resetThreadLocal() {
        IBUtils.channelIdThreadLocal.remove();
        IBUtils.EXEC_CTX_THREAD_LOCAL.remove();
    }

    /**
     * 針對電文回傳欄位做特定截取
     * 
     * @param str
     * @param n
     * @return String
     */
    public static String getCbsString(String str, int n) {
        try {
            if (StringUtils.isBlank(str)) {
                str = StringUtils.EMPTY;
            }
            else if (str.getBytes("BIG5_HKSCS").length > n) {
                str = new String(ArrayUtils.subarray(str.getBytes("BIG5_HKSCS"), 0, n), "BIG5_HKSCS");
            }
        }
        catch (UnsupportedEncodingException e) {
            logger.warn("error convert cbs string", e);
        }
        // 若為最後一位為英數字 顯示第17位字元
        if (str.length() > 0 && (Character.isDigit(str.charAt(str.length() - 1)) || Character.isLetter(str.charAt(str.length() - 1)))) {
            return StringUtils.trim(str);
        }
        // 否則會有亂數 第17位字元不顯示
        return StringUtils.trim(StringUtils.substring(str, 0, str.length() - 1));
    }

    /**
     * 取得幣別小數點位數
     * 
     * getMoneyScale("TWD") -> 0
     * 
     * getMoneyScale("JPY") -> 2
     * 
     * getMoneyScale("USD") -> 2
     * 
     * @see 共通業務邏輯 台幣僅能輸入整數位規則
     * @param currencyId
     * @return
     */
    public static int getMoneyScale(String currencyId) {
        return StringUtils.equals(currencyId, "TWD") ? 0 : 2;
    }

    /**
     * 深拷貝
     * 
     * @param <T>
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T deepCopy(T object) {
        Gson gson = new Gson();
        String json = gson.toJson(object);
        return gson.fromJson(json, (Class<T>) object.getClass());
    }

    /**
     * <p>
     * 比較APP版本號的大小
     * </p>
     * <p>
     * 以【.】為分隔符號，轉換成陣列。陣列中的每個元素，先比長度再比字符。則都相同再比陣列長度。
     * </p>
     * <ul>
     * <li>version1 大則返回 1</li>
     * <li>version2 大則返回 -1</li>
     * <li>相等則返回 0</li>
     * </ul>
     * 
     * @param version1
     * @param version2
     * @return
     */
    public static int compareAppVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            throw new RuntimeException("版本號不得為空");
        }
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        // 取陣列長度最小的值
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int diff = 0;
        // 先比較長度，再比較字符
        while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0 && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            ++idx;
        }
        // 如果已經分出大小，則直接返回，如果未分出大小，則再比較位數，有子版本的為大
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 比對目前版本與可用版本，若目前版本為空，則視為可用
     * 
     * @param currentVer
     * @param compareVer
     * @return
     */
    public static boolean checkIsValidVersion(String currentVer, String compareVer) {
        if (StringUtils.isBlank(currentVer)) {
            return true;
        }

        // If all components are equal up to the length of the shorter version
        int result = compareAppVersion(currentVer, compareVer);

        return result <= 0;
    }

    /**
     * 將來源資料轉換為目標資料
     * 
     * @param source
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <M> M toDataModel(Object source, Class<M> clazz) throws ActionException {
        M target;
        try {
            NotBeanType notBeanType = NotBeanType.findByClazz(clazz);
            if (notBeanType != null) {
                target = (M) notBeanType.wrapper(source);
            }
            else {
                target = clazz.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(target, source);
            }
        }
        catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
            throw new ActionException(String.format("dataModel[%s] no args constrcut not found...", clazz), CommonErrorCode.BEAN_WRAPPER_ERROR);
        }
        return target;
    }

    /** 允許操作Reflection的類別名稱 */
    private static final Set<String> ALLOWED_CLASSES_PREFIX = Set.of("org.springframework.data.redis.core");
    /** 允許操作Reflection的方法名稱 */
    private static final Set<String> ALLOWED_METHODS = Set.of("get", "set");

    /**
     * 安全地執行指定方法
     * 
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeSafeMethod(Object obj, Method method, Object... args) throws ActionException {
        Class<?> clazz = obj.getClass();
        String className = clazz.getName();
        if (!ALLOWED_CLASSES_PREFIX.stream().anyMatch(x -> StringUtils.startsWith(className, x))) {
            throw new ActionException(String.format("Unauthorized class：%s", className), CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
        }

        if (!ALLOWED_METHODS.stream().anyMatch(x -> StringUtils.equals(x, method.getName()))) {
            throw new ActionException(String.format("Unauthorized method：%s", method.getName()), CommonErrorCode.UNAUTHORIZED_OPERATIONAL);
        }
        if (args == null) {
            return ReflectionUtils.invokeMethod(method, obj);
        }
        else {
            return ReflectionUtils.invokeMethod(method, obj, args);
        }
    }

    /**
     * Parses a query string passed from the client to the server and builds a <code>HashTable</code> object with key-value pairs. The query string should be in the form of a string packaged by the GET or POST method, that is, it should have key-value
     * pairs in the form <i>key=value</i>, with each pair separated from the next by a &amp; character.
     *
     * <p>
     * A key can appear more than once in the query string with different values. However, the key appears only once in the hashtable, with its value being an array of strings containing the multiple values sent by the query string.
     *
     * <p>
     * The keys and values in the hashtable are stored in their decoded form, so any + characters are converted to spaces, and characters sent in hexadecimal notation (like <i>%xx</i>) are converted to ASCII characters.
     *
     * @param s
     *            a string containing the query to be parsed
     *
     * @return a <code>HashTable</code> object built from the parsed key-value pairs
     *
     * @exception IllegalArgumentException
     *                if the query string is invalid
     */
    public static Hashtable<String, String[]> parseQueryString(String s) {

        String valArray[] = null;

        if (s == null) {
            throw new IllegalArgumentException();
        }

        Hashtable<String, String[]> ht = new Hashtable<>();
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(s, "&");
        while (st.hasMoreTokens()) {
            String pair = st.nextToken();
            int pos = pair.indexOf('=');
            if (pos == -1) {
                // XXX
                // should give more detail about the illegal argument
                throw new IllegalArgumentException();
            }
            String key = parseName(pair.substring(0, pos), sb);
            String val = parseName(pair.substring(pos + 1, pair.length()), sb);
            if (ht.containsKey(key)) {
                String oldVals[] = ht.get(key);
                valArray = new String[oldVals.length + 1];
                for (int i = 0; i < oldVals.length; i++) {
                    valArray[i] = oldVals[i];
                }
                valArray[oldVals.length] = val;
            }
            else {
                valArray = new String[1];
                valArray[0] = val;
            }
            ht.put(key, valArray);
        }

        return ht;
    }

    /*
     * Parse a name in the query string.
     */
    private static String parseName(String s, StringBuilder sb) {
        sb.setLength(0);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
            case '+':
                sb.append(' ');
                break;
            case '%':
                try {
                    sb.append((char) Integer.parseInt(s.substring(i + 1, i + 3), 16));
                    i += 2;
                }
                catch (NumberFormatException e) {
                    // XXX
                    // need to be more specific about illegal arg
                    throw new IllegalArgumentException();
                }
                catch (StringIndexOutOfBoundsException e) {
                    String rest = s.substring(i);
                    sb.append(rest);
                    if (rest.length() == 2)
                        i++;
                }

                break;
            default:
                sb.append(c);
                break;
            }
        }

        return sb.toString();
    }

    /**
     * 將字串轉換成顯示金額格式(帶千分位)的字串
     * 
     * @param moneyStr
     *            數字字串
     * @return
     */
    public static String showMoneyStr(String moneyStr) {
        return showMoneyStr(moneyStr, 0, RoundingMode.HALF_UP);
    }

    /**
     * 將字串轉換成顯示金額格式(帶千分位)的字串
     *
     * @param moneyStr
     *            數字字串
     * @param scale
     *            小數有效位數，0 即整數位
     * @return
     */
    public static String showMoneyStr(String moneyStr, int scale) {
        return showMoneyStr(moneyStr, scale, RoundingMode.HALF_UP);
    }

    /**
     * 將字串轉換成顯示金額格式(帶千分位)的字串
     * 
     * @param moneyStr
     *            數字字串
     * @param scale
     *            小數位數
     * @param roundingMode
     *            進位方式
     * @return
     */
    public static String showMoneyStr(String moneyStr, int scale, RoundingMode roundingMode) {

        String sMoneyTrim = moneyStr.trim();

        // 若字串前綴為「+」，移除後繼續
        if (sMoneyTrim.startsWith("+")) {
            sMoneyTrim = StringUtils.substring(sMoneyTrim, 1);
        }

        BigDecimal money = ConvertUtils.str2BigDecimal((sMoneyTrim), scale, BigDecimal.ZERO, roundingMode);

        return formatMoneyStr(money, scale, roundingMode);
    }

    /**
     * 將數字轉換為金額型態(帶千分位)的字串，輸出樣式 -> 12,234,555.03
     * 
     * @param money
     *            金額
     * @return
     */
    public static String formatMoneyStr(BigDecimal money) {
        if (money == null) {
            return StringUtils.EMPTY;
        }
        return formatMoneyStr(money, money.scale());
    }

    /**
     * 將數字轉換為金額型態(帶千分位)的字串，輸出樣式 -> 12,234,555.03
     * 
     * @param money
     *            金額
     * @param scale
     *            小數位數
     * @return
     */
    public static String formatMoneyStr(BigDecimal money, int scale) {
        if (money == null) {
            return StringUtils.EMPTY;
        }
        return formatMoneyStr(money, scale, RoundingMode.HALF_UP);
    }

    /**
     * 將數字轉換為金額型態(帶千分位)的字串，輸出樣式 -> 12,234,555.03
     * 
     * @param money
     *            金額
     * @param scale
     *            小數位數
     * @param defaultString
     *            金額異常時，採用的預設值
     * @return
     */
    public static String formatMoneyStr(BigDecimal money, int scale, String defaultString) {
        if (money == null) {
            return defaultString;
        }
        return formatMoneyStr(money, scale, RoundingMode.HALF_UP);
    }

    /**
     * 將數字轉換為金額型態(帶千分位)的字串，輸出樣式 -> 12,234,555.03
     * 
     * @param money
     *            金額
     * @param curCode
     *            幣別代碼，臺幣顯示整數，外幣顯示小數兩位
     * @return
     */
    public static String formatMoneyStr(BigDecimal money, String curCode) {
        if (money == null) {
            return StringUtils.EMPTY;
        }
        return formatMoneyStr(money, getScaleWithCurCode(curCode), RoundingMode.HALF_UP);
    }

    /**
     * 將數字轉換為金額型態(帶千分位)的字串，輸出樣式 -> 12,234,555.03
     * 
     * @param money
     *            金額
     * @param curCode
     *            幣別代碼，臺幣顯示整數，外幣顯示小數兩位
     * @param roundingMode
     *            進位方式
     * @return
     */
    public static String formatMoneyStr(BigDecimal money, String curCode, RoundingMode roundingMode) {
        if (money == null) {
            return StringUtils.EMPTY;
        }
        return formatMoneyStr(money, getScaleWithCurCode(curCode), roundingMode);
    }

    /**
     * 將數字轉換為金額型態(帶千分位)的字串，輸出樣式 -> 12,234,555.03
     * 
     * @param money
     *            金額
     * @param scale
     *            小數位數
     * @param roundingMode
     *            進位方式
     * @return
     */
    public static String formatMoneyStr(BigDecimal money, int scale, RoundingMode roundingMode) {
        if (money == null) {
            return StringUtils.EMPTY;
        }

        StringBuilder patterns = new StringBuilder(0);
        patterns.append("#,##0");
        if (scale > 0) {
            patterns.append(".");
            for (int i = 0; i < scale; i++) {
                patterns.append("0");
            }
        }

        if (roundingMode == null) {
            roundingMode = RoundingMode.HALF_UP;
        }

        // 將數字格式化，採四捨五入方式進位
        DecimalFormat decimalFormat = new DecimalFormat(patterns.toString());
        decimalFormat.setRoundingMode(roundingMode);
        return decimalFormat.format(money);
    }

    /**
     * 顯示用的金額(含幣別)，輸出樣式 -> TWD 12,234,555.03 or 臺幣 12,234,555.03
     * 
     * @param currency
     *            幣別代碼 或 幣別名稱
     * @param money
     *            金額
     * @return
     */
    public static String displayMoneyStr(String currency, BigDecimal money) {
        return displayMoneyStr(currency, money, money.scale());
    }

    /**
     * 顯示用的金額(含幣別)，輸出樣式 -> TWD 12,234,555.03 or 臺幣 12,234,555.03
     * 
     * @param currency
     *            幣別代碼 或 幣別名稱
     * @param money
     *            金額
     * @return
     */
    public static String displayMoneyStr(String currency, BigDecimal money, int scale) {
        return displayMoneyStr(currency, money, scale, StringUtils.EMPTY);
    }

    /**
     * 顯示用的金額(含幣別)，輸出樣式 -> TWD 12,234,555.03 or 臺幣 12,234,555.03
     * 
     * @param currency
     *            幣別代碼 或 幣別名稱
     * @param money
     *            金額
     * @param scale
     *            小數位數
     * @param delim
     *            分隔符號 ex:$
     * @return
     */
    public static String displayMoneyStr(String currency, BigDecimal money, int scale, String delim) {
        if (money == null) {
            money = BigDecimal.ZERO;
        }
        StringBuilder sb = new StringBuilder(0);
        sb.append(currency).append(StringUtils.SPACE).append(displayMoneyStr(money, scale, RoundingMode.HALF_UP, delim));
        return sb.toString();
    }

    /**
     * 顯示用的金額(含幣別)，輸出樣式 -> TWD 12,234,555.03 or 臺幣 12,234,555.03
     * 
     * @param currency
     *            幣別代碼 或 幣別名稱
     * @param money
     *            金額
     * @param scale
     *            小數位數
     * @param roundingMode
     *            進位方式
     * @param delim
     *            分隔符號 ex:$
     * @return
     */
    public static String displayMoneyStr(String currency, BigDecimal money, int scale, RoundingMode roundingMode, String delim) {
        if (money == null) {
            money = BigDecimal.ZERO;
        }
        StringBuilder sb = new StringBuilder(0);
        sb.append(currency).append(StringUtils.SPACE).append(displayMoneyStr(money, scale, roundingMode, delim));
        return sb.toString();
    }

    /**
     * 顯示用的金額，將分隔符號置於「正負號」與「金額」之間，輸出樣式 -> -$12,234,555.03 or $12,234,555.03
     * 
     * @param money
     *            金額
     * @param scale
     *            小數位數
     * @param delim
     *            分隔符號 ex:$
     * @return
     */
    public static String displayMoneyStr(BigDecimal money, int scale, String delim) {
        return displayMoneyStr(money, scale, RoundingMode.HALF_UP, delim);
    }

    /**
     * 顯示用的金額，將分隔符號置於「正負號」與「金額」之間，輸出樣式 -> -$12,234,555.03 or $12,234,555.03
     * 
     * @param money
     *            金額
     * @param scale
     *            小數位數
     * @param roundingMode
     *            進位方式
     * @param delim
     *            分隔符號 ex:$
     * @return
     */
    public static String displayMoneyStr(BigDecimal money, int scale, RoundingMode roundingMode, String delim) {
        if (money == null) {
            money = BigDecimal.ZERO;
        }
        if (StringUtils.isBlank(delim)) {
            return formatMoneyStr(money, scale, roundingMode);
        }
        else {
            String moneyStr = formatMoneyStr(money, scale, roundingMode);
            if (money.intValue() < 0) {
                moneyStr = moneyStr.substring(1);
                return NEGATIVE_SIGN + delim + moneyStr;
            }
            else {
                return delim + moneyStr;
            }
        }
    }

    /**
     * 依幣別決定小數位數
     * 
     * @param curCode
     *            幣別代碼
     * @return
     */
    public static int getScaleWithCurCode(String curCode) {
        int scale = 0;
        if (StringUtils.notEquals(curCode, "TWD")) {
            scale = 2;
        }
        return scale;
    }

    /**
     * 日本行動電話處理，是否含【】符號，若有則需移除符號
     * 
     * @param mobileNumber
     * @param smsMsg
     * @return
     */
    public static String handleJapaneseMobilePhone(String mobileNumber, String smsMsg) {
        String msg = StringUtils.trimToEmptyEx(smsMsg);
        if (StringUtils.startsWithAny(mobileNumber, "817", "818", "819") && StringUtils.length(mobileNumber) == 12) {
            // 若為「日本行動電話」，判斷簡訊內容是否含【】符號，若有則需移除
            msg = RegExUtils.replaceAll(msg, "【", StringUtils.EMPTY);
            msg = RegExUtils.replaceAll(msg, "】", StringUtils.EMPTY);
        }
        return msg;
    }

    /**
     * ios -> iOS, android -> Android
     * 
     * @param platform
     * @return
     */
    public static String displayPlatform(String platform) {
        String mobileOS = StringUtils.trimToEmptyEx(platform);
        if (StringUtils.equalsIgnoreCase(mobileOS, "ios")) {
            mobileOS = "iOS";
        }
        else if (StringUtils.equalsIgnoreCase(mobileOS, "android")) {
            mobileOS = "Android";
        }
        return mobileOS;
    }

}
