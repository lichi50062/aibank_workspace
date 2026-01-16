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
package com.tfb.aibank.chl.component.mfdpromocode;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)MfdPromoCodeCacheManager.java
 * 
 * <p>Description:基金平台優惠 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Marty
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MfdPromoCodeCacheManager extends AbstractCacheManager {

    @Autowired
    private MfdPromoCodeResource mfdPromoCodeResource;

    private Map<String, Map<Integer, List<FundProjectDiscount>>> purchaseWayMapByLocale = null;

    private static final String DEFAULT_LOCALE = Locale.TAIWAN.toString();

    public List<FundProjectDiscount> getAllPlateformFundProjectDiscountsByPurchaseWayAndLocale(Integer purchaseWay, String locale) {

        if (purchaseWayMapByLocale != null) {
            Map<Integer, List<FundProjectDiscount>> map;

            if (purchaseWayMapByLocale.containsKey(locale)) {
                map = purchaseWayMapByLocale.get(locale);
            }
            else {
                map = purchaseWayMapByLocale.get(DEFAULT_LOCALE);
            }
            return getAllPlateformFundProjectDiscountsByPurchaseWay(purchaseWay, map);
        }
        return Collections.emptyList();
    }

    private List<FundProjectDiscount> getAllPlateformFundProjectDiscountsByPurchaseWay(Integer purchaseWay, Map<Integer, List<FundProjectDiscount>> purchaseWayMap) {
        if (purchaseWayMap == null) {
            return Collections.emptyList();
        }
        return purchaseWayMap.getOrDefault(purchaseWay, Collections.emptyList());
    }

    /**
     * 回傳 global cache key
     *
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MFD_PROMO_CODE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<FundProjectDiscount> fundProjectDiscounts = mfdPromoCodeResource.getAllEffectiveMfdPromoCodes();
        if (logger.isDebugEnabled()) {
            logger.info("「loadCache」：{}", fundProjectDiscounts);
        }

        if (CollectionUtils.isNotEmpty(fundProjectDiscounts)) {

            Map<String, Map<Integer, List<FundProjectDiscount>>> purchaseWayMapByLocaleTemp = new HashMap<>();

            Map<String, List<FundProjectDiscount>> fundProjectDiscountByLocale =
                    fundProjectDiscounts.stream().collect(Collectors.groupingBy(FundProjectDiscount::getLocale));

            for (var localeGroup : fundProjectDiscountByLocale.entrySet()) {
                List<FundProjectDiscount> discountsInLocale = localeGroup.getValue();

                Map<Integer, List<FundProjectDiscount>> fundProjDiscountsMapByPurchaseWay =
                        discountsInLocale.stream().collect(Collectors.groupingBy(FundProjectDiscount::getPurchaseWay));

                purchaseWayMapByLocaleTemp.put(localeGroup.getKey(), fundProjDiscountsMapByPurchaseWay);
            }

            if (!MapUtils.isEmpty(purchaseWayMapByLocaleTemp)) {
                this.purchaseWayMapByLocale = purchaseWayMapByLocaleTemp;
            }
        }
        else {
            this.purchaseWayMapByLocale = Collections.emptyMap();
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.purchaseWayMapByLocale == null;
    }

}
