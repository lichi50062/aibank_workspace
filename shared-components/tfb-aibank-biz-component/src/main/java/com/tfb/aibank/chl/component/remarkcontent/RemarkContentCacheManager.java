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
package com.tfb.aibank.chl.component.remarkcontent;

import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.cache.AbstractLRUCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.type.RemarkContentType;

// @formatter:off
/**
 * @(#)RemarkContentCacheManager.java
 * 
 * <p>Description:輔助說明、頁面備註和條款 cache 物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class RemarkContentCacheManager extends AbstractLRUCacheManager {

    /** 預設語系：繁中 */
    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    @Autowired
    private RemarkContentResource resource;

    /**
     * Map<remarkType + remarkKey + locale, RemarkContent>
     */
    private Map<String, RemarkContent> dataMap = new HashMap<>();

    /**
     * 取輔助說明、頁面備註和條款
     * 
     * @param type
     *            類型
     * @param key
     *            文案鍵值
     * @param locale
     *            語系
     * @return
     */
    public RemarkContent getRemarkContent(RemarkContentType type, String key, Locale userlocale) {
        return getRemarkContent(type.getType(), key, userlocale.toString());
    }

    /**
     * 取輔助說明、頁面備註和條款
     * 
     * @param type
     *            類型
     * @param key
     *            文案鍵值
     * @param locale
     *            語系
     * @return
     */
    public RemarkContent getRemarkContent(String type, String key, Locale userlocale) {
        return getRemarkContent(type, key, userlocale.toString());
    }

    /**
     * 取輔助說明、頁面備註和條款
     * 
     * @param type
     *            類型
     * @param key
     *            文案鍵值
     * @param locale
     *            語系
     * @return
     */
    public RemarkContent getRemarkContent(RemarkContentType type, String key, String locale) {
        return getEffectiveRemarkContent(getMapKey(type.getType(), key, locale));
    }

    /**
     * 取輔助說明、頁面備註和條款
     * 
     * @param type
     *            類型
     * @param key
     *            文案鍵值
     * @param locale
     *            語系
     * @return
     */
    public RemarkContent getRemarkContent(String type, String key, String locale) {
        return getEffectiveRemarkContent(getMapKey(type, key, locale));
    }

    /**
     * 取資料時，檢查該筆資料是否依舊有效
     * 
     * @param mapKey
     * @return
     */
    private RemarkContent getEffectiveRemarkContent(String mapKey) {
        RemarkContent remarkContent = dataMap.get(mapKey);
        if (remarkContent != null) {
            if (DateUtils.between(new Date(), remarkContent.getStartTime(), remarkContent.getEndTime())) {
                // 尚未失效，不用處理
            }
            else {
                // 已經失效，從 Cache 裡移除
                dataMap.remove(mapKey);
                remarkContent = null;
            }
        }
        return remarkContent;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.REMARK_CONTENT_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache <br/>
     * 第一個參數：type、第二個參數：key、第三個參數：locale
     */
    @Override
    protected void loadCacheByKey(Object... keys) {

        RemarkContentType type = null;
        if (keys[0] instanceof RemarkContentType) {
            type = (RemarkContentType) keys[0];
        }
        else {
            type = RemarkContentType.findByType((String) keys[0]);
        }

        RemarkContent data = null;
        String locale = DEFAULT_LOCALE;
        if (keys.length == 2) {
            switch (type) {
            case MEMO:
                data = resource.getMemo((String) keys[1]);
                break;
            case REMARK:
                data = resource.getRemark((String) keys[1]);
                break;
            case TERMS:
                data = resource.getTerms((String) keys[1]);
                break;
            case MEMO_EXTENSION:
                data = resource.getMemoExtension((String) keys[1]);
                break;
            default:
                // nothing...
            }
        }
        else {
            locale = keys[2] == null ? DEFAULT_LOCALE : keys[2].toString();
            switch (type) {
            case MEMO:
                data = resource.getMemo((String) keys[1], locale);
                break;
            case REMARK:
                data = resource.getRemark((String) keys[1], locale);
                break;
            case TERMS:
                data = resource.getTerms((String) keys[1], locale);
                break;
            case MEMO_EXTENSION:
                data = resource.getMemoExtension((String) keys[1], locale);
                break;
            default:
                // nothing...
            }
        }

        if (data == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("載入資料為空。{}", keys);
            }
            dataMap.put(getMapKey(type.getType(), (String) keys[1], locale), null);
        }
        else {
            dataMap.put(getMapKey(data.getRemarkType(), data.getRemarkKey(), data.getLocale()), data);
        }

        if (logger.isDebugEnabled()) {
            logger.debug("current content of cached params: {}", IBUtils.attribute2Str(dataMap));
        }
    }

    /**
     * 第一個參數：type、第二個參數：key、第三個參數：locale
     */
    @Override
    protected boolean isFirstLoad(Object... keys) {
        String type = StringUtils.EMPTY;
        if (keys[0] instanceof RemarkContentType) {
            type = ((RemarkContentType) keys[0]).getType();
        }
        else {
            type = (String) keys[0];
        }
        // 若只有二個參數，表示沒有帶入第三個參數(locale)
        if (keys.length == 2) {
            return !dataMap.containsKey(getMapKey(type, (String) keys[1], DEFAULT_LOCALE));
        }
        else {
            return !dataMap.containsKey(getMapKey(type, (String) keys[1], (keys[2] == null ? DEFAULT_LOCALE : keys[2].toString())));
        }
    }

    @Override
    protected void cleanAll() {
        this.dataMap.clear();
    }

    private String getMapKey(String type, String key, String locale) {
        String userLocale = StringUtils.isBlank(locale) ? DEFAULT_LOCALE : locale;
        return new StringBuilder(0).append(type).append("_").append(key).append("_").append(userLocale).toString();
    }

}
