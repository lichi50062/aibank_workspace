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
package com.tfb.aibank.chl.component.devicemodel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)DeviceModelCacheManager.java
 * 
 * <p>Description:行動裝置型號對應表 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/13, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class DeviceModelCacheManager extends AbstractCacheManager {

    @Autowired
    private DeviceModelResource deviceModelResource;

    /** 以DeviceModel為鍵值記錄資料 */
    private Map<String, DeviceModelData> dataMap = new LinkedHashMap<>();

    /**
     * 以裝置Model取得DEVICE_MODEL資料
     * 
     * @param model
     * @return
     */
    public DeviceModelData getDeviceModel(String model) {
        if (StringUtils.isNotBlank(model)) {
            return dataMap.get(model);
        }
        return null;
    }

    /**
     * 以裝置Model取得DEVICE_MODEL.MARKETING_NAME資料
     * 
     * @param model
     * @return
     */
    public String getDeviceMarketingName(String model) {
        if (StringUtils.isNotBlank(model) && dataMap.containsKey(model)) {
            return dataMap.get(model).getMarketingName();
        }
        return StringUtils.EMPTY;
    }

    /**
     * 以裝置Model取得DEVICE_MODEL.MARKETING_NAME資料; 為空值則回傳model
     * 
     * @param model
     * @return
     */
    public String getDeviceMarketingNameOrModel(String model) {
        if (StringUtils.isNotBlank(model) && dataMap.containsKey(model)) {
            return dataMap.get(model).getMarketingName();
        }
        return model;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.DEVICE_MODEL_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<DeviceModelData> dataList = deviceModelResource.getAllDeviceModelData();
        Map<String, DeviceModelData> dataMap = new LinkedHashMap<>();
        dataList.forEach(deviceModel -> {
            String model = deviceModel.getDeviceModel();
            if (StringUtils.isNotBlank(model)) {
                dataMap.put(model, deviceModel);
            }
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
