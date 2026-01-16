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
package com.tfb.aibank.chl.component.businessday;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)BusinessDayCacheManager.java
 * 
 * <p>Description:營業日 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/07, Leiley
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class BusinessDayCacheManager extends AbstractCacheManager {

    @Autowired
    private BusinessDayResource resource;

    private List<Date> dataList = new ArrayList<Date>();

    private List<Date> taipeiBizDayList = new ArrayList<Date>();

    /**
     * 讀取 DB.BUSINESS_DAY 裡所有營業日(FLAG = 1)
     * 
     * @return
     */
    public List<Date> getAllBusinessDay() {
        return CollectionUtils.isEmpty(dataList) ? Collections.emptyList() : dataList;
    }

    /**
     * 取得區間內所有營業日
     * 
     * @param startDate
     * @param endDate
     * @return
     */
    public List<Date> getBusinessDayInRange(Date startDate, Date endDate) {
        DateUtils.clearTime(startDate);
        DateUtils.clearTime(endDate);
        return dataList.stream().filter(d -> d.compareTo(startDate) >= 0 && d.compareTo(endDate) <= 0).collect(Collectors.toList());
    }

    /**
     * 取得傳入日的之後的第1個營業日
     */
    public Date getNextBusinessDateAfterInputDate(Date inputDate) {
        DateUtils.clearTime(inputDate);
        return dataList.stream().filter(inputDate::before).findFirst().get();
    }

    /**
     * 傳入的日期是否是營業日
     */
    public boolean isBusinessDay(Date date) {
        if (CollectionUtils.isEmpty(dataList)) {
            return false;
        }
        DateUtils.clearTime(date);
        return dataList.contains(date);
    }

    /**
     * 傳入的日期是否是「台北」營業日
     */
    public boolean isTaipeiBusinessDay(Date date) {
        if (CollectionUtils.isEmpty(taipeiBizDayList)) {
            return false;
        }
        DateUtils.clearTime(date);
        return taipeiBizDayList.contains(date);
    }

    /**
     * 取得傳入日期的前一營業日
     */
    public Date getPreviousBusinessDay(Date inputDate) {

        if (CollectionUtils.isEmpty(dataList)) {
            return null;
        }
        int left = 0;
        int right = dataList.size() - 1;
        int resultIndex = -1;
        boolean isMatch = false;
        // 二分搜索
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Date midDate = dataList.get(mid);
            if (midDate.equals(inputDate)) {
                resultIndex = mid;
                isMatch = true;
                break;
            }
            else if (midDate.before(inputDate)) {
                resultIndex = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        // 完全沒匹配或匹配到第一筆
        if (resultIndex == -1 || resultIndex == 0) {
            return null;
        }
        // 匹配到則取前一個營業日
        if (isMatch) {
            resultIndex -= 1;
        }
        // 返回最近的上一個營業日
        return dataList.get(resultIndex);
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.BUSINESS_DAY_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<Date> list = resource.getAllBusinessDay();
        if (CollectionUtils.isNotEmpty(list)) {
            this.dataList = list;
        }
        List<Date> taipeiBizDays = resource.getAllTaipeiBusinessDay();
        if (CollectionUtils.isNotEmpty(taipeiBizDays)) {
            this.taipeiBizDayList = taipeiBizDays;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataList.isEmpty();
    }

    @Override
    protected Duration cacheTimeout() {
        return Duration.ofMinutes(30);
    }
}
