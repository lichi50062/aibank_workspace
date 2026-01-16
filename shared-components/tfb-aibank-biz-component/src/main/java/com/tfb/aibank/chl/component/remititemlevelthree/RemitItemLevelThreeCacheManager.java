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
package com.tfb.aibank.chl.component.remititemlevelthree;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)RemitItemLevelThreeCacheManager.java
 * 
 * <p>Description:匯款性質檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Marty
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class RemitItemLevelThreeCacheManager extends AbstractCacheManager {

    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    @Autowired
    private RemitItemLevelThreeResource remitItemLevelThreeResource;

    @Autowired
    private SystemParamCacheManager systemParamCM;

    /**
     * Map<LOCALE, Map<BRANCH_ID, BranchData>>
     */
    private Map<String, List<RemitItemLevelThree>> localeMap = new HashMap<>();
    /**
     * REMIT_ITEM_LEVEL_THREE_BAK 資料來源為 BAK
     */
    private Map<String, List<RemitItemLevelThree>> bakLocaleMap = new HashMap<>();

    private Map<String, List<RemitItemLevelThree>> localeMapIn = new HashMap<>();

    private Map<String, List<RemitItemLevelThree>> bakLocaleMapIn = new HashMap<>();

    private Map<String, List<RemitItemLevelOne>> levelOneMap = new HashMap<>();

    private Map<String, List<RemitItemLevelTwo>> levelTwoMap = new HashMap<>();

    public List<RemitItemLevelThree> getAllRemitItemInLevelThrees(Locale locale) {
        if (locale == null) {
            return getRemitItemInLevel3ByLocale(DEFAULT_LOCALE);
        }
        return getRemitItemInLevel3ByLocale(locale.toString());
    }

    public List<RemitItemLevelThree> getRemitItemLevelThrees(String locale) {
        return getDataByLocale(locale);
    }

    public List<RemitItemLevelThree> getRemitItemLevelThreesByType(String remitType, String locale) {
        return getDataByLocale(locale).stream().filter(createPredicate(remitType, null)).collect(Collectors.toList());
    }

    public List<RemitItemLevelThree> getRemitItemLevelThreesByTypeAndCode(String remitType, String remitCode, String locale) {
        List<RemitItemLevelThree> remitLv3s = new ArrayList<>();
        if (null != getDataByLocale(locale)) {
            List<RemitItemLevelThree> remitLv3sInLocale = getDataByLocale(locale);
            for (var three : remitLv3sInLocale) {
                if (StringUtils.equals(remitType, three.getRemitType()) && StringUtils.equals(remitCode, three.getRemitCode())) {
                    remitLv3s.add(three);
                }
            }
        }
        return remitLv3s;
    }

    /**
     * 取得 全部 匯款性質大項說明 REMIT_ITEM_LEVEL_ONE
     * 
     * @param locale
     * @return
     */
    public List<RemitItemLevelOne> getAllRemitItemLevelOne(String locale) {
        List<RemitItemLevelOne> list = this.levelOneMap.get(locale);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list;
    }

    /**
     * 取得 全部 匯款性質大項說明 REMIT_ITEM_LEVEL_ONE
     * 
     * @param userLocale
     * @return
     */
    public List<RemitItemLevelOne> getAllRemitItemLevelOne(Locale userLocale) {
        List<RemitItemLevelOne> list = this.levelOneMap.get(userLocale.toString());
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list;
    }

    /**
     * 依 LEVEL_ONE_NO 取得 匯款性質大項說明 REMIT_ITEM_LEVEL_ONE
     * <p>
     * 以 LEVEL_ONE_NO 由小至大排序
     * </p>
     * 
     * @param userLocale
     * @param levelOneNo
     * @return
     */
    public List<RemitItemLevelOne> getRemitItemLevelOneByLevelOneNo(Locale userLocale, Integer... levelOneNo) {
        List<RemitItemLevelOne> list = this.levelOneMap.get(userLocale.toString());
        if (list != null) {
            return list.stream().sorted(Comparator.comparingInt(RemitItemLevelOne::getLevelOneNo)).filter(x -> Arrays.stream(levelOneNo).anyMatch(y -> y.equals(x.getLevelOneNo()))).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 取得 全部 匯款性質中項說明 REMIT_ITEM_LEVEL_TWO
     * 
     * @param locale
     * @return
     */
    public List<RemitItemLevelTwo> getAllRemitItemLevelTwo(String locale) {
        List<RemitItemLevelTwo> list = this.levelTwoMap.get(locale);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list;
    }

    /**
     * 取得 全部 匯款性質中項說明 REMIT_ITEM_LEVEL_TWO
     * 
     * @param userLocale
     * @return
     */
    public List<RemitItemLevelTwo> getAllRemitItemLevelTwo(Locale userLocale) {
        List<RemitItemLevelTwo> list = this.levelTwoMap.get(userLocale.toString());
        return CollectionUtils.isEmpty(list) ? Collections.emptyList() : list;
    }

    private Predicate<RemitItemLevelThree> createPredicate(String remitType, String remitCode) {
        if (StringUtils.isEmpty(remitCode)) {
            return three -> remitType.equals(three.getRemitType());
        }
        else {
            return three -> remitType.equals(three.getRemitType()) && remitCode.equals(three.getRemitCode());
        }
    }

    private boolean useBakData() {
        String remitStartDtStr = systemParamCM.getValue(AIBankConstants.CHANNEL_NAME, "REMIT_START_DATE");

        if (StringUtils.isBlank(remitStartDtStr)) {
            return false;
        }
        // 預期的value => 『yyyy/MM/dd HH:mm:ss』
        remitStartDtStr = StringUtils.trimToEmpty(remitStartDtStr);

        Date checkDate = new Date();
        try {
            // 如果日期長度只有10，應是只有 yyyy/MM/dd
            if (remitStartDtStr.length() == 10) {
                checkDate = DateFormatUtils.CE_DATE_FORMAT.parse(remitStartDtStr);
            }
            else {
                checkDate = DateFormatUtils.CE_DATETIME_FORMAT.parse(remitStartDtStr);
            }
        }
        catch (ParseException e) {
            logger.error("＊＊ Parse [REMIT_START_DATE] in system param fail ＊＊, e: ", e);
        }
        Date currentDate = new Date();
        return currentDate.before(checkDate);
    }

    private List<RemitItemLevelThree> getDataByLocale(String locale) {
        if (this.useBakData()) {
            return MapUtils.isEmpty(bakLocaleMap) ? Collections.emptyList() : bakLocaleMap.get(locale);
        }
        else {
            return MapUtils.isEmpty(localeMap) ? Collections.emptyList() : localeMap.get(locale);
        }
    }

    private List<RemitItemLevelThree> getRemitItemInLevel3ByLocale(String locale) {
        if (this.useBakData()) {
            return MapUtils.isEmpty(bakLocaleMapIn) ? Collections.emptyList() : bakLocaleMapIn.get(locale);
        }
        else {
            return MapUtils.isEmpty(localeMapIn) ? Collections.emptyList() : localeMapIn.get(locale);
        }
    }

    /**
     * 回傳 global cache key
     *
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.REMIT_ITEM_LEVEL_THREE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<RemitItemLevelThree> dataList = remitItemLevelThreeResource.getAllRemitItemLevelThree(false);
        if (CollectionUtils.isNotEmpty(dataList)) {
            Map<String, List<RemitItemLevelThree>> localeMap = dataList.stream().collect(Collectors.groupingBy(RemitItemLevelThree::getLocale));
            this.localeMap = localeMap;
        }

        List<RemitItemLevelThree> dataListBak = remitItemLevelThreeResource.getAllRemitItemLevelThree(true);
        if (CollectionUtils.isNotEmpty(dataListBak)) {
            Map<String, List<RemitItemLevelThree>> bakLocaleMap = dataListBak.stream().collect(Collectors.groupingBy(RemitItemLevelThree::getLocale));
            this.bakLocaleMap = bakLocaleMap;
        }

        List<RemitItemLevelThree> remitItemInLevelThreeList = remitItemLevelThreeResource.getAllRemitItemInLevelThree(false);
        if (CollectionUtils.isNotEmpty(remitItemInLevelThreeList)) {
            Map<String, List<RemitItemLevelThree>> localeMapIn = remitItemInLevelThreeList.stream().collect(Collectors.groupingBy(RemitItemLevelThree::getLocale));
            this.localeMapIn = localeMapIn;
        }

        List<RemitItemLevelThree> remitItemInLevelThreeBakList = remitItemLevelThreeResource.getAllRemitItemInLevelThree(true);
        if (CollectionUtils.isNotEmpty(remitItemInLevelThreeBakList)) {
            Map<String, List<RemitItemLevelThree>> bakLocaleMapIn = remitItemInLevelThreeBakList.stream().collect(Collectors.groupingBy(RemitItemLevelThree::getLocale));
            this.bakLocaleMapIn = bakLocaleMapIn;
        }

        List<RemitItemLevelOne> remitItemLevelOneList = remitItemLevelThreeResource.getAllRemitItemLevelOne();
        if (CollectionUtils.isNotEmpty(remitItemLevelOneList)) {
            Map<String, List<RemitItemLevelOne>> levelOneMap = remitItemLevelOneList.stream().collect(Collectors.groupingBy(RemitItemLevelOne::getLocale));
            this.levelOneMap = levelOneMap;
        }

        List<RemitItemLevelTwo> remitItemLevelTwoList = remitItemLevelThreeResource.getAllRemitItemLevelTwo();
        if (CollectionUtils.isNotEmpty(remitItemLevelTwoList)) {
            Map<String, List<RemitItemLevelTwo>> levelTwoMap = remitItemLevelTwoList.stream().collect(Collectors.groupingBy(RemitItemLevelTwo::getLocale));
            this.levelTwoMap = levelTwoMap;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return localeMap.isEmpty();
    }

}
