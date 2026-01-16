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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)ETransBankCacheManager.java
 * 
 * <p>Description:全國繳銀行主檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class ETransBankCacheManager extends AbstractCacheManager {

    @Autowired
    private BankResource bankResource;

    /**
     * key : bankId
     */
    private Map<String, ETransBankData> dataMap = new LinkedHashMap<>();

    public List<ETransBankData> getAllBanks() {
        return dataMap.values().stream().collect(Collectors.toList());
    }

    public String getBankName(String bankId) {
        ETransBankData data = dataMap.get(bankId);
        return data == null ? StringUtils.EMPTY : data.getBankName();
    }

    public ETransBankData getBankData(String bankId) {
        return dataMap.get(bankId);
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.E_TRANS_BANK_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<ETransBankData> dataList = bankResource.getAllETransBankData();
        Map<String, ETransBankData> dataMap = new LinkedHashMap<>();
        dataList.forEach(bank -> {
            dataMap.put(bank.getBankId(), bank);
        });
        this.dataMap = dataMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return dataMap.isEmpty();
    }

}
