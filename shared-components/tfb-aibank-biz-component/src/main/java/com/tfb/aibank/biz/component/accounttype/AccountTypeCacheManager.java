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
package com.tfb.aibank.biz.component.accounttype;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)AccountTypeCacheManager.java
 * 
 * <p>Description:帳號科目別(人工維護) Cache Manager</p>
 * <p>限定 Biz Service 使用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class AccountTypeCacheManager extends AbstractCacheManager {

    @Autowired
    private AccountTypeResource resource;

    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    /** key: accountId + locale */
    private Map<String, AccountTypeData> accountIdDataMap = new LinkedHashMap<>();

    /** key: accountTypeCode + locale */
    private Map<String, AccountTypeData> accountTypeCodeDataMap = new LinkedHashMap<>();

    public String getNameById(String accountId) {
        return findById(accountId).getAccountTypeName();
    }

    public String getNameByCode(String accountTypeCode) {
        return findByCode(accountTypeCode).getAccountTypeName();
    }

    public String getNameById(String accountId, String locale) {
        return findById(accountId, locale).getAccountTypeName();
    }

    public String getNameByCode(String accountTypeCode, String locale) {
        return findByCode(accountTypeCode, locale).getAccountTypeName();
    }

    public AccountTypeData findById(String accountId) {
        return findById(accountId, null);
    }

    public AccountTypeData findByCode(String accountTypeCode) {
        return findByCode(accountTypeCode, null);
    }

    public AccountTypeData findById(String accountId, String locale) {
        if (StringUtils.isBlank(locale)) {
            locale = DEFAULT_LOCALE;
        }
        return accountIdDataMap.get(getKey(accountId, locale));
    }

    public AccountTypeData findByCode(String accountTypeCode, String locale) {
        if (StringUtils.isBlank(locale)) {
            locale = DEFAULT_LOCALE;
        }
        return accountTypeCodeDataMap.get(getKey(accountTypeCode, locale));
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.ACCOUNT_TYPE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<AccountTypeData> dataList = resource.getAllAccountType();
        Map<String, AccountTypeData> accountIdDataMap = new LinkedHashMap<>();
        Map<String, AccountTypeData> accountTypeCodeDataMap = new LinkedHashMap<>();
        dataList.forEach(data -> {
            String accountId = data.getAccountId();
            String locale = data.getLocale();
            String accountTypeCode = data.getAccountTypeCode();
            if (accountIdDataMap.get(getKey(accountId, locale)) == null) {
                accountIdDataMap.put(getKey(accountId, locale), data);
            }
            if (accountTypeCodeDataMap.get(getKey(accountTypeCode, locale)) == null) {
                accountTypeCodeDataMap.put(getKey(accountTypeCode, locale), data);
            }
        });
        this.accountIdDataMap = accountIdDataMap;
        this.accountTypeCodeDataMap = accountTypeCodeDataMap;
    }

    private String getKey(String word, String locale) {
        return new StringBuilder(0).append(word).append("_").append(locale).toString();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.accountIdDataMap.isEmpty() && this.accountTypeCodeDataMap.isEmpty();
    }

}
