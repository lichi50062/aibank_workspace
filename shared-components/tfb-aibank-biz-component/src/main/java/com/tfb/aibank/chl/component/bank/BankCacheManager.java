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
package com.tfb.aibank.chl.component.bank;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)BankCacheManager.java
 * 
 * <p>Description:銀行檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class BankCacheManager extends AbstractCacheManager {

    @Autowired
    private BankResource bankResource;

    /**
     * Map<LOCALE, Map<BANK_ID, BANK>>
     */
    private Map<String, Map<String, BankData>> dataMap = new LinkedHashMap<>();

    public List<BankData> getAllBanks(Locale locale) {
        return getAllBanks(locale.toString());
    }

    public List<BankData> getAllBanks(String locale) {
        return getDataByLocale(locale).values().stream().collect(Collectors.toList());
    }

    public String getBankName(String code, Locale locale) {
        return getBankName(code, locale.toString());
    }

    public String getBankName(String code, String locale) {
        BankData bank = getDataByLocale(locale).get(code);
        return bank == null ? StringUtils.EMPTY : bank.getBankName();
    }

    public BankData getBankData(String code, Locale locale) {
        return getBankData(code, locale.toString());
    }

    public BankData getBankData(String code, String locale) {
        return getDataByLocale(locale).get(code);
    }

    private Map<String, BankData> getDataByLocale(String locale) {
        Map<String, BankData> map = dataMap.get(locale);
        return MapUtils.isEmpty(map) ? Collections.emptyMap() : map;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.BANK_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<BankData> dataList = bankResource.getAllBankData();
        Map<String, Map<String, BankData>> dataMap = new LinkedHashMap<>();
        dataList.forEach(bank -> {
            String locale = bank.getLocale();
            String code = bank.getBankId();
            if (dataMap.get(locale) == null) {
                dataMap.put(locale, new HashMap<String, BankData>());
            }
            dataMap.get(locale).put(code, bank);
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
