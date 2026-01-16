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
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)FundCheckDataCacheManager.java
 * 
 * <p>Description:基金檢核用DBU/OBU可購買基金代碼 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/25,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class FundCheckDataCacheManager extends AbstractCacheManager {

    @Autowired
    private FundCheckDataResource resource;

    /**
     * 開放申購的基金代碼-DBU
     */
    private List<FundInfo> fundCodesForPurchaseDBU = new ArrayList<>();
    /**
     * 開放申購的基金代碼-OBU
     */
    private List<FundInfo> fundCodesForPurchaseOBU = new ArrayList<>();

    /**
     * 取得所有開放DBU申購的基金代碼
     * 
     * @return
     */
    public List<FundInfo> getFundCodesOpenForPurchaseDBU() {
        return fundCodesForPurchaseDBU;
    }

    /**
     * 取得所有開放OBU申購的基金代碼
     * 
     * @return
     */
    public List<FundInfo> getFundCodesOpenForPurchaseOBU() {
        return fundCodesForPurchaseOBU;
    }

    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.FUND_CHECK_DATA_CACHE_KEY;
    }

    @Override
    protected void loadCache() {
        List<FundInfo> fundCodeCanBuyDBU = resource.queryFundOpenPurchaseDBU(Locale.TRADITIONAL_CHINESE.toString());
        if (CollectionUtils.isNotEmpty(fundCodeCanBuyDBU)) {
            if (logger.isDebugEnabled()) {
                List<String> regions = fundCodeCanBuyDBU.stream().map(bd -> bd.getFundCode() + "REGION[" + bd.getRegion() + "], fundValuation[" + bd.getFundValuation() + "] ,CUR[" + bd.getCurrencyCode() + "]").collect(Collectors.toUnmodifiableList());
                logger.debug("regions: {}", regions);
            }
            this.fundCodesForPurchaseDBU = fundCodeCanBuyDBU;
        }

        List<FundInfo> fundCodeCanBuyOBU = resource.queryFundOpenPurchaseOBU(Locale.TRADITIONAL_CHINESE.toString());
        if (CollectionUtils.isNotEmpty(fundCodeCanBuyOBU)) {
            if (logger.isDebugEnabled()) {
                List<String> regions = fundCodeCanBuyOBU.stream().map(bd -> bd.getFundCode() + "REGION[" + bd.getRegion() + "], fundValuation[" + bd.getFundValuation() + "] ,CUR[" + bd.getCurrencyCode() + "]").collect(Collectors.toUnmodifiableList());
                logger.debug("regions: {}", regions);
            }
            this.fundCodesForPurchaseOBU = fundCodeCanBuyOBU;
        }
    }

    @Override
    protected boolean isFirstLoad() {
        return this.fundCodesForPurchaseDBU.isEmpty() || this.fundCodesForPurchaseOBU.isEmpty();
    }

}
