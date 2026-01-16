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
package com.tfb.aibank.chl.component.mfdinfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)MfdInfoCacheManager.java
 * 
 * <p>Description:基金資訊 cache 物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/24, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MfdInfoCacheManager extends AbstractCacheManager {

    private IBLog logger = IBLog.getLog(MfdInfoCacheManager.class);

    /** 預設語系：繁中 */
    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    @Autowired
    private MfdInfoResource resource;

    private Map<String, Map<String, MfdInfo>> dataMap = new HashMap<>();

    /**
     * 讀取基金資訊
     * 
     * @param fundCode
     *            基金代碼(四碼)
     * @return
     */
    public MfdInfo getMfdInfo(String fundCode) {
        return getMfdInfo(fundCode, DEFAULT_LOCALE);
    }

    public List<MfdInfo> getAllMfdInfo(String locale) {
        return getDataByLocale(locale).values().stream().collect(Collectors.toList());
    }

    public List<MfdInfo> getAllMfdInfo() {
        return getAllMfdInfo(DEFAULT_LOCALE);
    }

    private Map<String, MfdInfo> getDataByLocale(String locale) {
        Map<String, MfdInfo> map = dataMap.get(locale);
        return MapUtils.isEmpty(map) ? Collections.emptyMap() : map;
    }

    /**
     * 讀取基金資訊
     * 
     * @param fundCode
     *            基金代碼(四碼)
     * @return
     */
    public List<MfdInfo> getAllMfdInfo(Locale locale) {
        if (locale == null) {
            return getAllMfdInfo();
        }

        return getAllMfdInfo(locale.toString());
    }

    /**
     * 讀取基金資訊
     * 
     * @param fundCode
     *            基金代碼(四碼)
     * @param locale
     *            語系
     * @return
     */
    public MfdInfo getMfdInfo(String fundCode, String locale) {
        if (StringUtils.isBlank(fundCode) || StringUtils.length(fundCode) != 4) {
            logger.error("基金代碼為空值或長度不等於4，不予處理。FUND_CODE={}", fundCode);
            return null;
        }
        return Optional.ofNullable(getDataByLocale(locale).get(fundCode)).orElse(null);
    }

    /**
     * 讀取基金資訊
     * 
     * @param fundCodes
     *            基金代碼集合(四碼)
     * @return
     */
    public List<MfdInfo> getMfdInfoList(List<String> fundCodes) {
        return getMfdInfoList(fundCodes, DEFAULT_LOCALE);
    }

    /**
     * 讀取基金資訊
     * 
     * @param fundCodes
     *            基金代碼集合(四碼)
     * @param locale
     *            語系
     * @return
     */
    public List<MfdInfo> getMfdInfoList(List<String> fundCodes, String locale) {
        if (CollectionUtils.isEmpty(fundCodes)) {
            logger.error("基金代碼為空值或空集合，不予處理。");
            return null;
        }
        List<MfdInfo> list = new ArrayList<>();
        for (String fundCode : fundCodes) {
            MfdInfo mfdInfo = getMfdInfo(fundCode, locale);
            if (mfdInfo != null) {
                list.add(mfdInfo);
            }
        }
        return list;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MFD_INFO_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<MfdInfo> infoList = resource.getAllMfdInfo();
        Map<String, Map<String, MfdInfo>> dataMap = new HashMap<>();
        infoList.forEach(info -> {
            String locale = info.getLocale();
            String code = info.getOrgFundCode() + info.getSubFundCode();
            if (dataMap.get(locale) == null) {
                dataMap.put(locale, new HashMap<String, MfdInfo>());
            }
            dataMap.get(locale).put(code, info);
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
