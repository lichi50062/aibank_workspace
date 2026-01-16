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
package com.tfb.aibank.chl.component.missionwall;

import java.util.ArrayList;
import java.util.Collections;
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
 * @(#)MissionCompleteListCacheManager.java
 * 
 * <p>Description:任務完成列表 CacheManger</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/09/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MissionCompleteListCacheManager extends AbstractCacheManager {

    @Autowired
    private MissionWallResource resource;

    /** key: pageId */
    private Map<String, List<MissionCompleteList>> dataMap = null;

    /**
     * 依 pageId 取得符合的紀錄
     * 
     * @param pageId
     * @return
     */
    public List<MissionCompleteList> getMissionCompleteListByPageId(String pageId) {
        List<MissionCompleteList> list = dataMap.get(pageId);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MISSION_COMPLETE_LIST_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<MissionCompleteList> dataList = resource.getAllMissionCompleteList();
        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, List<MissionCompleteList>> dataMap = new HashMap<>();
            for (MissionCompleteList data : dataList) {
                String pageId = data.getPageId();
                if (dataMap.get(pageId) == null) {
                    dataMap.put(pageId, new ArrayList<>());
                }
                dataMap.get(pageId).add(data);
            }
            this.dataMap = dataMap;
        }
        else {
            this.dataMap = Collections.emptyMap();
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap == null;
    }

}
