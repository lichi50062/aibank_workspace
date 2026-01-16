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
package com.tfb.aibank.chl.component.cardpaytype;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)CardPaytypeCacheManager.java
 * 
 * <p>Description:信用卡支付類型 Cache Manager</p>
 * <p>限定 Biz Service 使用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class CardPaytypeCacheManager extends AbstractCacheManager {

    @Autowired
    private CardPaytypeResource resource;

    /** Map<CARD_TYPE + LOCALE, CardPaytype> */
    private Map<String, CardPaytype> cardPaytypeMap = null;

    private Map<String, CardPaytype> getCardPaytypeMap() {
        return this.cardPaytypeMap;
    }

    /**
     * 取得信用卡卡別資料
     * 
     * @param CardPayType
     * @param locale
     * @return
     */
    public CardPaytype getCardPaytype(String CardPayType, String locale) {
        return getCardPaytypeMap().get(CardPayType + locale);
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.CARD_PAY_TYPE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<CardPaytype> cardPaytypeList = resource.getCardPaytypes();
        Map<String, CardPaytype> cardPaytypeMap = cardPaytypeList.stream().collect(Collectors.toMap(this::buildCardPayTypeMapKey, Function.identity()));
        this.cardPaytypeMap = cardPaytypeMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.cardPaytypeMap == null;
    }

    private String buildCardPayTypeMapKey(CardPaytype model) {
        return model.getPayType() + model.getLocale();
    }

}
