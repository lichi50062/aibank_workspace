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
package com.tfb.aibank.chl.component.mfdarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)MfdAreaCacheManager.java
 * 
 * <p>Description:基金地區 CacheManger</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/10, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MfdAreaCacheManager extends AbstractCacheManager {

    @Autowired
    private MfdAreaResource resource;

    /** key: orgFundCode+subFundCode */
    private Map<String, MfdArea> areaDataMap = new HashMap<>();
    /** key: locale */
    private Map<String, List<MfdAreaDesc>> descDescMap = new HashMap<>();

    /**
     * 讀取基金地區名稱
     * 
     * @param fundCode
     *            基金代碼(四碼)
     * @return
     */
    public String getMfdAreaName(String fundCode, String locale) {
        return getMfdArea(fundCode).getAreaName(locale);
    }

    /**
     * 讀取基金地區
     * 
     * @param fundCode
     *            基金代碼(四碼)
     * @return
     */
    public MfdArea getMfdArea(String fundCode) {
        return areaDataMap.get(fundCode);
    }

    /**
     * 讀取所有基金地區
     * 
     * @param fundCode
     *            基金代碼(四碼)
     * @return
     */
    public List<MfdArea> getAllMfdArea() {
        return new ArrayList<>(areaDataMap.values());

    }

    /**
     * 讀取基金地區說明清單
     * 
     * @return
     */
    public List<MfdAreaDesc> getMfdAreaDescList(String locale) {
        return descDescMap.get(locale);
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MFD_AREA_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<MfdArea> dataList = resource.getAllMfdArea();
        if (CollectionUtils.isNotEmpty(dataList)) {
            List<String> duplicate = new ArrayList<>();
            Map<String, MfdArea> areaDataMap = new HashMap<>();
            Map<String, List<MfdAreaDesc>> descDescMap = new HashMap<>();
            for (MfdArea data : dataList) {
                // 採集 MFD_AREA 資料
                areaDataMap.put(data.getOrgFundCode() + data.getSubFundCode(), data);
                // 採集 MFD_AREA_DESC 資訊
                if (!duplicate.contains(data.getAreaCode())) {
                    for (Map.Entry<String, String> entry : data.getAreaNameMap().entrySet()) {
                        if (descDescMap.get(entry.getKey()) == null) {
                            descDescMap.put(entry.getKey(), new ArrayList<>());
                        }
                        descDescMap.get(entry.getKey()).add(new MfdAreaDesc(data.getAreaCode(), entry.getValue()));
                    }
                    duplicate.add(data.getAreaCode()); // 同一個 AREA_CDOE 只需要處理一次
                }
            }
            this.areaDataMap = areaDataMap;
            this.descDescMap = descDescMap;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.areaDataMap.isEmpty();
    }

}
