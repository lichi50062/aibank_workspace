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
package com.tfb.aibank.chl.component.fxtranscurrencydiscount;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)FxTransCurrencyDiscountCacheManager.java
 * 
 * <p>Description:外幣轉帳優惠 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class FxTransCurrencyDiscountCacheManager extends AbstractCacheManager {

    @Autowired
    private FxTransCurrencyDiscountResource fxTransCurrencyDiscountResource;

    private List<FxTransCurrencyDiscount> fxTransCurrDiscounts = null;

    /**
     * 依傳入條件篩選
     */
    public List<FxTransCurrencyDiscount> getFxTransCurrDiscountsByCondition(Predicate<FxTransCurrencyDiscount> predication) {
        return this.fxTransCurrDiscounts.stream().filter(predication).collect(Collectors.toList());
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.FX_TRANS_CURRENCY_DISCOUNT_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        this.fxTransCurrDiscounts = fxTransCurrencyDiscountResource.getFxTransCurrencyDiscounts();
    }

    @Override
    protected boolean isFirstLoad() {
        return this.fxTransCurrDiscounts == null;
    }

}
