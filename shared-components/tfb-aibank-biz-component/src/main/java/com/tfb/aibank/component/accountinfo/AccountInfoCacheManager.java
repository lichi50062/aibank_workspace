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
package com.tfb.aibank.component.accountinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)AccountInfoCacheManager.java
 * 
 * <p>Description:帳號資料檔 Cache Manager</p>
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
public class AccountInfoCacheManager extends AbstractCacheManager {

    @Autowired
    private AccountInfoResource resource;

    /** Map<locale, Map<prodCode + prodSubCode, AccountInfo>> */
    private Map<String, Map<String, AccountInfo>> dataMap = new HashMap<>();

    public AccountInfo getAccountInfo(String fullProdCode, String locale) {
        String prodCode = StringUtils.left(fullProdCode, 4);
        String prodSubCode = StringUtils.right(fullProdCode, 4);
        return getAccountInfo(prodCode, prodSubCode, locale);
    }

    public AccountInfo getAccountInfo(String prodCode, String prodSubCode, String locale) {
        return dataMap.get(locale).get(getKey(prodCode, prodSubCode));
    }

    public String getAccountInfoDisplayTxt(String prodCode, String prodSubCode, String locale) {
        return Optional.ofNullable(dataMap.get(locale).get(getKey(prodCode, prodSubCode))).map(AccountInfo::getDisplayText).orElse("");
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.ACCOUNT_INFO_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<AccountInfo> dataList = resource.getAllAccountInfo();
        Map<String, Map<String, AccountInfo>> dataMap = new HashMap<>();
        dataList.forEach(data -> {
            String locale = data.getLocale();
            String prodCode = data.getProdCode();
            String prodSubCode = data.getProdSubCode();
            if (dataMap.get(locale) == null) {
                dataMap.put(locale, new HashMap<>());
            }
            dataMap.get(locale).put(getKey(prodCode, prodSubCode), data);
        });
        this.dataMap = dataMap;
    }

    private String getKey(String prodCode, String prodSubCode) {
        return new StringBuilder(0).append(prodCode).append("_").append(prodSubCode).toString();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.dataMap.isEmpty();
    }

}
