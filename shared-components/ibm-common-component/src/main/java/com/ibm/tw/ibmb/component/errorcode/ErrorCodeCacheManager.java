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
package com.ibm.tw.ibmb.component.errorcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.type.LoginSystemType;

// @formatter:off
/**
 * @(#)ErrorCodeCacheManager.java
 * 
 * <p>Description:錯誤代碼檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class ErrorCodeCacheManager extends AbstractCacheManager {

    private static final String PAGE_ID_DEFAULT = "default";

    @Autowired
    private ErrorCodeResource errorCodeResource;

    /**
     * Map<locale, Map<systemId, Map<errorCode, Map<pageId, Map<channelId, ErrorCodeData>>>>>
     */
    private Map<String, Map<String, Map<String, Map<String, Map<String, ErrorCodeData>>>>> errorCodeMap = new HashMap<>();

    /**
     * Map<locale, Map<systemId, Map<errorCode, Map<pageId, Map<channelId, ErrorInfoData>>>>>
     */
    private Map<String, Map<String, Map<String, Map<String, Map<String, ErrorInfoData>>>>> errorInfoMap = new HashMap<>();

    /**
     * Map<txId, srcId>
     */
    private Map<String, String> txSystemMapMap = new HashMap<>();

    /**
     * 取得指定 ERROR_CODE 物件
     * 
     * @param systemId
     * @param errorCode
     * @param locale
     * @param pageId
     * @param channelId
     * @return
     * @throws ActionException
     */
    public ErrorCodeData getErrorCodeData(IBSystemId systemId, String errorCode, Locale locale, String pageId, String channelId) throws ActionException {
        String localeStr = locale.toString();
        return getErrorCodeData(systemId.getSystemId(), errorCode, localeStr, pageId, channelId);
    }

    /**
     * 取得指定 ERROR_CODE 物件
     * 
     * @param systemId
     * @param errorCode
     * @param localeStr
     * @param pageId
     * @param channelId
     * @return
     * @throws ActionException
     */
    public ErrorCodeData getErrorCodeData(String systemId, String errorCode, String localeStr, String pageId, String channelId) {
        Map<String, Map<String, Map<String, ErrorCodeData>>> errorMap = getErrorCodeMap(systemId, localeStr);
        // 找不到對應 error code map
        if (errorMap == null) {
            return null;
        }
        Map<String, Map<String, ErrorCodeData>> pageMap = errorMap.get(errorCode);
        // 找不到對應的 page map
        if (pageMap == null) {
            return null;
        }
        Map<String, ErrorCodeData> chlIdMap = pageMap.containsKey(pageId) ? pageMap.get(pageId) : pageMap.get(PAGE_ID_DEFAULT);
        // 找不到對應的 channel id map
        if (chlIdMap == null) {
            return null;
        }
        ErrorCodeData value = chlIdMap.get(channelId);
        if (value == null && !chlIdMap.isEmpty()) {
            // 找共用訊息，不存在則找第一筆 TODO: 保留未來擴充使用，當跨通路時，設置共用的錯誤訊息
            value = chlIdMap.containsKey("AIBANK") ? chlIdMap.get("AIBANK") : chlIdMap.values().iterator().next();
        }

        return value;
    }

    /**
     * 取得指定 SYSTEM_ID 底下的所有 ERROR_CODE
     * 
     * @param systemId
     * @param locale
     * @return
     * @throws ActionException
     */
    public Map<String, Map<String, Map<String, ErrorCodeData>>> getErrorCodeMap(String systemId, String locale) {
        // 以 locale 找出對應的所有資料
        Map<String, Map<String, Map<String, Map<String, ErrorCodeData>>>> systemMap = errorCodeMap.containsKey(locale) ? errorCodeMap.get(locale) : errorCodeMap.get(Locale.TAIWAN.toString());
        if (systemMap == null) {
            systemMap = Collections.emptyMap();
        }
        // 以 system 找出對應的所有資料
        Map<String, Map<String, Map<String, ErrorCodeData>>> errorMap = systemMap.containsKey(systemId) ? systemMap.get(systemId) : Collections.emptyMap();
        if (errorMap == null) {
            errorMap = Collections.emptyMap();
        }
        return Collections.unmodifiableMap(errorMap);
    }

    /**
     * 取得指定 SYSTEM_ID 底下的所有 ERROR_INFO
     * 
     * @param systemId
     * @param locale
     * @return
     * @throws ActionException
     */
    public Map<String, Map<String, Map<String, ErrorInfoData>>> getErrorInfoMap(String systemId, String locale) throws ActionException {
        // 以 locale 找出對應的所有資料
        Map<String, Map<String, Map<String, Map<String, ErrorInfoData>>>> systemMap = errorInfoMap.containsKey(locale) ? errorInfoMap.get(locale) : errorInfoMap.get(Locale.TAIWAN.toString());
        if (systemMap == null) {
            systemMap = Collections.emptyMap();
        }
        // 以 system 找出對應的所有資料
        Map<String, Map<String, Map<String, ErrorInfoData>>> errorMap = systemMap.containsKey(systemId) ? systemMap.get(systemId) : Collections.emptyMap();
        if (errorMap == null) {
            errorMap = Collections.emptyMap();
        }
        return Collections.unmodifiableMap(errorMap);
    }

    /**
     * 取得指定 ERROR_INFO 物件
     * 
     * @param systemId
     * @param errorCode
     * @param localeStr
     * @param pageId
     * @param channelId
     * @return
     * @throws ActionException
     */
    public ErrorInfoData getErrorInfoData(String systemId, String errorCode, String localeStr, String pageId, String channelId) throws ActionException {
        Map<String, Map<String, Map<String, ErrorInfoData>>> errorMap = getErrorInfoMap(systemId, localeStr);
        // 找不到對應 error code map
        if (errorMap == null) {
            return null;
        }
        Map<String, Map<String, ErrorInfoData>> pageMap = errorMap.get(errorCode);
        // 找不到對應的 page map
        if (pageMap == null) {
            return null;
        }
        Map<String, ErrorInfoData> chlIdMap = pageMap.containsKey(pageId) ? pageMap.get(pageId) : pageMap.get(PAGE_ID_DEFAULT);
        // 找不到對應的 channel id map
        if (chlIdMap == null) {
            return null;
        }
        ErrorInfoData value = chlIdMap.get(channelId);
        if (value == null) {
            return null;
        }
        return value;
    }

    /**
     * 讀取 TX_SYSTEM_MAP.SRC_ID
     * 
     * @param txId
     * @return
     */
    public String getSrcId(String txId) {
        return txSystemMapMap.get(txId);
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.ERROR_CODE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        // 讀取 ERROR_CODE
        List<ErrorCodeData> errorCodeList = errorCodeResource.getAllErrorCode();

        Map<String, Map<String, Map<String, Map<String, Map<String, ErrorCodeData>>>>> errorCodeMap = new HashMap<>();
        errorCodeList.forEach(data -> {
            // 以 locale 取第一層 system Map
            Map<String, Map<String, Map<String, Map<String, ErrorCodeData>>>> systemMap = errorCodeMap.get(data.getLocale());
            if (systemMap == null) {
                // 沒有就建一個新的
                systemMap = new HashMap<>();
                errorCodeMap.put(data.getLocale(), systemMap);
            }
            // 以 systemId 取第二層 error code map
            Map<String, Map<String, Map<String, ErrorCodeData>>> errorMap = systemMap.get(data.getSystemId());
            if (errorMap == null) {
                errorMap = new HashMap<>();
                systemMap.put(data.getSystemId(), errorMap);
            }
            // 以 error code 取第三層 page map
            Map<String, Map<String, ErrorCodeData>> pageMap = errorMap.get(data.getErrorCode());
            if (pageMap == null) {
                pageMap = new LinkedHashMap<>();
                errorMap.put(data.getErrorCode(), pageMap);
            }
            // 以 page id 取第四層 channel id map
            Map<String, ErrorCodeData> chlIdMap = pageMap.get(data.getPageId());
            if (chlIdMap == null) {
                chlIdMap = new LinkedHashMap<>();
                pageMap.put(data.getPageId(), chlIdMap);
            }
            // 依 channelId 存入，TODO：保留未來網銀擴充使用，目前固定為「AIBANK_APP」
            chlIdMap.put(LoginSystemType.AIBANK_APP.getChannelId(), data);
        });
        this.errorCodeMap = errorCodeMap;

        // 讀取 ERROR_INFO
        List<ErrorInfoData> errorInfoList = errorCodeResource.getAllErrorInfo();
        Map<String, Map<String, Map<String, Map<String, Map<String, ErrorInfoData>>>>> errorInfoMap = new HashMap<>();
        errorInfoList.forEach(data -> {
            // 以 locale 取第一層 system Map
            Map<String, Map<String, Map<String, Map<String, ErrorInfoData>>>> systemMap = errorInfoMap.get(data.getLocale());
            if (systemMap == null) {
                // 沒有就建一個新的
                systemMap = new HashMap<>();
                errorInfoMap.put(data.getLocale(), systemMap);
            }
            // 以 systemId 取第二層 error code map
            Map<String, Map<String, Map<String, ErrorInfoData>>> errorMap = systemMap.get(data.getSystemId());
            if (errorMap == null) {
                errorMap = new HashMap<>();
                systemMap.put(data.getSystemId(), errorMap);
            }
            // 以 error code 取第三層 page map
            Map<String, Map<String, ErrorInfoData>> pageMap = errorMap.get(data.getErrorCode());
            if (pageMap == null) {
                pageMap = new LinkedHashMap<>();
                errorMap.put(data.getErrorCode(), pageMap);
            }
            // 以 page id 取第四層 channel id map
            Map<String, ErrorInfoData> chlIdMap = pageMap.get(data.getPageId());
            if (chlIdMap == null) {
                chlIdMap = new LinkedHashMap<>();
                pageMap.put(data.getPageId(), chlIdMap);
            }
            // 依 channelId 存入，TODO：保留未來網銀擴充使用，目前固定為「AIBANK_APP」
            chlIdMap.put(LoginSystemType.AIBANK_APP.getChannelId(), data);
        });
        this.errorInfoMap = errorInfoMap;

        // 讀取 ERROR_INFO
        List<TxSystemMap> txSystemMapList = errorCodeResource.getAllTxSystemMap();
        Map<String, String> txSystemMapMap = new HashMap<>();
        txSystemMapList.forEach(data -> {
            txSystemMapMap.put(data.getTxId(), data.getSrcId());
        });
        this.txSystemMapMap = txSystemMapMap;

    }

    @Override
    protected boolean isFirstLoad() {
        return errorCodeMap.isEmpty() && errorInfoMap.isEmpty() && txSystemMapMap.isEmpty();
    }

}
