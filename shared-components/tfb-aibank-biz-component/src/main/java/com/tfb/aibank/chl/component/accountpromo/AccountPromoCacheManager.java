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
package com.tfb.aibank.chl.component.accountpromo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)AccountPromoCacheManager.java
 * 
 * <p>Description:帳戶總覽推薦牌卡 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class AccountPromoCacheManager extends AbstractCacheManager {

    @Autowired
    private AccountPromoResource resource;

    private static final String TAIWAN_LOCALE = Locale.TAIWAN.toString();

    /**
     * KEY => Deposit_Type
     */
    private Map<String, Map<String, AccountPromo>> accountPromoMapByLocale = null;

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.ACCOUNT_PROMO_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<AccountPromo> promos = resource.getAllAccountPromos();
        if (CollectionUtils.isEmpty(promos)) {
            this.accountPromoMapByLocale = Collections.emptyMap();
        }
        else {

            Map<String, List<AccountPromo>> dataListByLocaleMap = promos.stream().collect(Collectors.groupingBy(AccountPromo::getLocale));
            accountPromoMapByLocale = new HashMap<>();

            for (var entry : dataListByLocaleMap.entrySet()) {
                Map<String, AccountPromo> accountPromoMapByDepositType = entry.getValue().stream().collect(Collectors.toMap(AccountPromo::getDepositType, Function.identity(), (existing, replacement) -> existing));
                accountPromoMapByLocale.put(entry.getKey(), accountPromoMapByDepositType);
            }

        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.accountPromoMapByLocale == null;
    }

    public AccountPromo getAccountPromoByDepositType(String depositType, Locale locale) {
        AccountPromo accountPromo = null;
        if (null != this.accountPromoMapByLocale) {
            Map<String, AccountPromo> accountPromoMapByDType = this.accountPromoMapByLocale.get(locale.toString());
            if (null != accountPromoMapByDType) {
                accountPromo = accountPromoMapByDType.get(depositType);
            }
            //非zh_TW + 沒資料時，取zh_TW資料
            if (null == accountPromo && !TAIWAN_LOCALE.equals(locale.toString())) {
                accountPromoMapByDType = this.accountPromoMapByLocale.get(TAIWAN_LOCALE);
                if (null != accountPromoMapByDType) {
                    accountPromo = accountPromoMapByDType.get(depositType);
                }
            }
        }
        return accountPromo;
    }

}
