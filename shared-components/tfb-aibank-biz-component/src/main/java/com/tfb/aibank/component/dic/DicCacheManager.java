package com.tfb.aibank.component.dic;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)DicCacheManager.java
 * 
 * <p>Description:AI Bank DIC 設定檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class DicCacheManager extends AbstractCacheManager {

    @Autowired
    private DicResource dicResource;

    /**
     * Map<CATEGORY, Map<DIC_KEY, DicData>>
     */
    private Map<String, Map<String, DicData>> paramMap = new HashMap<>();

    /**
     * 依指定 CATEGORY，取出所有符合的資料
     * 
     * @param category
     * @return
     */
    public Map<String, DicData> getDicMapByCategory(String category) {
        Map<String, DicData> valueMap = paramMap.get(category);
        return valueMap == null ? Collections.emptyMap() : valueMap;
    }

    /**
     * 依指定 CATEGORY，取出所有符合的資料
     * 
     * @param category
     * @return
     */
    public List<DicData> getDicListByCategory(String category) {
        Map<String, DicData> valueMap = getDicMapByCategory(category);
        return valueMap.values().stream().collect(Collectors.toList());
    }

    /**
     * 依指定 CATEGORY、DIC_KEY，取出符合的紀錄
     * 
     * @param category
     * @param dicKey
     * @return
     */
    public DicData getDicByCategoryAndKey(String category, String dicKey) {
        Map<String, DicData> valueMap = getDicMapByCategory(category);
        return valueMap.get(dicKey);
    }

    /**
     * 依指定 CATEGORY、DIC_KEY，取出 DIC_VALUE
     * 
     * @param category
     * @param dicKey
     * @return
     */
    public String getValue(String category, String dicKey) {
        DicData dic = getDicByCategoryAndKey(category, dicKey);
        return dic == null ? StringUtils.EMPTY : dic.getDicValue();
    }

    public List<DicData> getIndexNoListByCategory(String category) {
        Map<String, DicData> valueMap = getDicMapByCategory(category);
        List<DicData> dicDataList = new ArrayList<>(valueMap.values());

        // 使用 AtomicInteger 為每個 DicData 賦予 indexNo
        AtomicInteger counter = new AtomicInteger(1);
        dicDataList.forEach(dicData ->{
            if (dicData.getDicKey().endsWith("_img")) { // 只針對 "_img" 結尾的項目
                dicData.setIndexNo(counter.getAndIncrement());
            }
        }); // 設定 indexNo

        return dicDataList;
    }


    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.DICTIONARY_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<DicData> dataList = dicResource.getAllDicData();
        Map<String, Map<String, DicData>> paramMap = new HashMap<>();
        dataList.forEach(data -> {
            String category = data.getCategory();
            String dicKey = data.getDicKey();
            Map<String, DicData> categoryMap = paramMap.get(category);
            if (categoryMap == null) {
                categoryMap = new HashMap<>();
                paramMap.put(category, categoryMap);
            }
            categoryMap.put(dicKey, data);
        });
        this.paramMap = paramMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.paramMap.isEmpty();
    }

}