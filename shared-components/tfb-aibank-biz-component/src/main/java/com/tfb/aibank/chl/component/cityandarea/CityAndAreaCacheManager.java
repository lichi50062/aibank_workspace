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
package com.tfb.aibank.chl.component.cityandarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.util.IBUtils;

// @formatter:off
/**
 * @(#)CityAndAreaCacheManager.java
 * 
 * <p>Description:縣市、鄉鎮市區、郵遞區號 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class CityAndAreaCacheManager extends AbstractCacheManager {

    @Autowired
    private CityAndAreaResource resource;

    private Map<String, List<City>> cityMap = new HashMap<>();
    private Map<String, List<CityArea>> cityAreaMap = new HashMap<>();
    private Map<String, List<ZipCode>> zipcodeMap = new HashMap<>();

    /**
     * 讀取指定語系下的所有縣市、鄉鎮市區、郵遞區號
     * 
     * @param userLocale
     * @return
     */
    public List<City> getCityList(Locale userLocale) {
        List<City> cities = cityMap.get(userLocale.toString());
        for (City city : cities) {
            city.setAreas(getCityAreaList(userLocale, city.getCityCode1()));
            city.setZipcodes(getZipcodeList(userLocale, city.getCityCode1()));
        }
        IBUtils.sort(cities, "citySort", false); // 排序
        return cities;
    }

    /**
     * 讀取指定縣市的所有鄉鎮市區
     * 
     * @param userLocale
     * @param cityCode
     * @return
     */
    public List<CityArea> getCityAreaList(Locale userLocale, String cityCode) {
        List<CityArea> areas = cityAreaMap.get(userLocale.toString());
        return areas.stream().filter(x -> StringUtils.equals(x.getCityCode1(), cityCode)).collect(Collectors.toList());
    }

    /**
     * 讀取指定縣市的所有郵遞區號
     * 
     * @param userLocale
     * @param cityCode
     * @return
     */
    public List<ZipCode> getZipcodeList(Locale userLocale, String cityCode) {
        List<ZipCode> zipcodes = zipcodeMap.get(userLocale.toString());
        return zipcodes.stream().filter(x -> StringUtils.equals(x.getCityCode1(), cityCode)).collect(Collectors.toList());
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.CITY_AREA_ZIPCODE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<City> allCityList = resource.getAllCity();
        Map<String, List<City>> cityMap = new HashMap<>();
        allCityList.forEach(city -> {
            String locale = city.getLocale();
            if (cityMap.get(locale) == null) {
                cityMap.put(locale, new ArrayList<>());
            }
            cityMap.get(locale).add(city);
        });

        List<CityArea> allCityAreaList = resource.getAllCityArea();
        Map<String, List<CityArea>> cityAreaMap = new HashMap<>();
        allCityAreaList.forEach(area -> {
            String locale = area.getLocale();
            if (cityAreaMap.get(locale) == null) {
                cityAreaMap.put(locale, new ArrayList<>());
            }
            cityAreaMap.get(locale).add(area);
        });

        List<ZipCode> allZipcodeList = resource.getAllZipcode();
        Map<String, List<ZipCode>> zipcodeMap = new HashMap<>();
        allZipcodeList.forEach(zipcode -> {
            String locale = zipcode.getLocale();
            if (zipcodeMap.get(locale) == null) {
                zipcodeMap.put(locale, new ArrayList<>());
            }
            zipcodeMap.get(locale).add(zipcode);
        });

        this.cityMap = cityMap;
        this.cityAreaMap = cityAreaMap;
        this.zipcodeMap = zipcodeMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.cityMap.isEmpty() && this.cityAreaMap.isEmpty() && this.zipcodeMap.isEmpty();
    }

}
