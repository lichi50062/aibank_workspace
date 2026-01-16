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
package com.tfb.aibank.component.fundcheckdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)FundLimitCacheManager.java
 * 
 * <p>Description:基金限額 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/25
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class MfdLimitAmountCacheManager extends AbstractCacheManager {

    @Autowired
    private FundCheckDataResource resource;

    /**
     * 基金申購最低限額 by CurCode
     */
    private Map<String, FundLimitAmount> fundLimitAmtMap = new HashMap<>();

    /**
     * 傳入幣別取得指定 FundLimitAmount 的資料
     */
    public FundLimitAmount getFundLimitAmountByCurCode(String curCode) {

        FundLimitAmount fundLimitAmount = null;

        FundLimitAmount tmpAmount = fundLimitAmtMap.getOrDefault(curCode, null);

        if (tmpAmount != null) {
            fundLimitAmount = new FundLimitAmount();
            fundLimitAmount.setCurrencyCode(tmpAmount.getCurrencyCode());
            // 定期不定額申購
            fundLimitAmount.setPeriodNfamtPurchase(tmpAmount.getPeriodNfamtPurchase());
            // 定額申購
            fundLimitAmount.setPeriodPurchase(tmpAmount.getPeriodPurchase());
            // 單筆申購
            fundLimitAmount.setSinglePurchase(tmpAmount.getSinglePurchase());
            // 後收型申購
            fundLimitAmount.setBackendPurchase(tmpAmount.getBackendPurchase());
            // 更新時間
            fundLimitAmount.setUpdateTime(tmpAmount.getUpdateTime());
            // FUND定額申購
            fundLimitAmount.setFundPeriodPurchase(tmpAmount.getFundPeriodPurchase());
            // FUND定期不定額申購
            fundLimitAmount.setFundPeriodNfamtPurchase(tmpAmount.getFundPeriodNfamtPurchase());
        }

        return fundLimitAmount;
    }

    /**
     * 傳入幣別取得指定 FundLimitAmount 的資料
     */
    public List<FundLimitAmount> getFundLimitAmountsByCurCodes(List<String> curCodes) {
        List<FundLimitAmount> list = new ArrayList<>();
        for (var curCode : curCodes) {
            if (fundLimitAmtMap.containsKey(curCode)) {
                list.add(fundLimitAmtMap.get(curCode));
            }
        }
        return list;
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.MFD_LIMIT_AMOUNT_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<FundLimitAmount> limitAmounts = resource.getAllFundLimitAmounts();
        if (CollectionUtils.isNotEmpty(limitAmounts)) {
            Map<String, FundLimitAmount> fundLimitAmtMap = new HashMap<>();
            for (var limitAmt : limitAmounts) {
                fundLimitAmtMap.put(limitAmt.getCurrencyCode(), limitAmt);
            }
            this.fundLimitAmtMap = fundLimitAmtMap;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.fundLimitAmtMap.isEmpty();
    }

}
