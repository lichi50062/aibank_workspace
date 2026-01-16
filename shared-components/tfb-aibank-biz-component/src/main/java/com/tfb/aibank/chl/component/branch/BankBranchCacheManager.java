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
package com.tfb.aibank.chl.component.branch;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)BranchCacheManager.java
 * 
 * <p>Description:銀行分行檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class BankBranchCacheManager extends AbstractCacheManager {

    @Autowired
    private BranchResource resource;

    /**
     * Map<LOCALE, Map<BANK_ID + BRANCH_ID, BankBranchData>>
     * 
     */
    private Map<String, Map<String, BankBranchData>> dataMap = new LinkedHashMap<>();

    /**
     * Map<LOCALE, Map<BANK_ID, Map<BRANCH_ID, BankBranchData>>>
     */
    private Map<String, Map<String, Map<String, BankBranchData>>> dataMapByBank = new LinkedHashMap<>();

    public String getBranchName(String bankId, String branchId, Locale locale) {
        return getBranchName(bankId, branchId, locale.toString());
    }

    public String getBranchName(String bankId, String branchId, String locale) {
        String branchIndex = branchId;
        if (StringUtils.isNotBlank(branchId) && branchId.length() == 4) {
            branchIndex = StringUtils.left(branchIndex, 3);
        }
        BankBranchData branch = getDataByLocale(locale).get(bankId + branchIndex);
        return branch == null ? StringUtils.EMPTY : branch.getBranchName();
    }

    /** 取得 Bank 下所有 Branch */
    public Map<String, BankBranchData> getBranchbyBank(String bankId, String locale) {
        return dataMapByBank.get(locale).get(bankId);
    }

    public BankBranchData getBranch(String bankId, String branchId, Locale locale) {
        return getBranch(bankId, branchId, locale.toString());
    }

    public BankBranchData getBranch(String bankId, String branchId, String locale) {
        return getDataByLocale(locale).get(bankId + branchId);
    }

    private Map<String, BankBranchData> getDataByLocale(String locale) {
        Map<String, BankBranchData> map = dataMap.get(locale);
        return MapUtils.isEmpty(map) ? Collections.emptyMap() : map;
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.BANK_BRANCH_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<BankBranchData> dataList = resource.getAllBankBranch();
        Map<String, Map<String, BankBranchData>> dataMap = new LinkedHashMap<>();
        dataList.forEach(branch -> {
            String locale = branch.getLocale();
            String bankId = branch.getBankId();
            String branchId = StringUtils.left(branch.getBranchId(), 3);
            if (dataMap.get(locale) == null) {
                dataMap.put(locale, new HashMap<String, BankBranchData>());
            }
            dataMap.get(locale).put(bankId + branchId, branch);
        });

        Map<String, Map<String, Map<String, BankBranchData>>> tmpDataMapByBank = new LinkedHashMap<>();
        dataList.forEach(branch -> {
            String locale = branch.getLocale();
            String bankId = branch.getBankId();
            String branchId = StringUtils.left(branch.getBranchId(), 3);

            if (tmpDataMapByBank.get(locale) == null) {
                tmpDataMapByBank.put(locale, new HashMap<String, Map<String, BankBranchData>>());
            }
            if (tmpDataMapByBank.get(locale).get(bankId) == null) {
                tmpDataMapByBank.get(locale).put(bankId, new HashMap<String, BankBranchData>());
            }
            tmpDataMapByBank.get(locale).get(bankId).put(branchId, branch);

        });

        this.dataMap = dataMap;
        this.dataMapByBank = tmpDataMapByBank;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty() && this.dataMapByBank.isEmpty();
    }

}
