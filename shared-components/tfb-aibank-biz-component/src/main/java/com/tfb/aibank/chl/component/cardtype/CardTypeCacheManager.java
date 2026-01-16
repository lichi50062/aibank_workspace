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
package com.tfb.aibank.chl.component.cardtype;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)CardTypeCacheManager.java
 * 
 * <p>Description:卡面資料檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, EvanWang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class CardTypeCacheManager extends AbstractCacheManager {

    @Autowired
    private CardTypeResource resource;

    /** Map<CARD_TYPE + LOCALE, CardTypeModel> */
    private Map<String, CardType> cardTypeMap = null;

    private Map<String, CardType> getCardTypeMap() {
        return this.cardTypeMap;
    }

    public CardType getCardType(String cardType, Locale userLocale) {
        return getCardType(cardType, userLocale.toString());
    }

    public CardType getCardType(String cardType, String locale) {
        return getCardTypeMap().get(cardType + locale);
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.CARD_TYPE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<CardType> cardTypeModels = resource.getCardTypes();
        Map<String, CardType> cardTypeMap = cardTypeModels.stream().collect(Collectors.toMap(this::buildCardTypeMapKey, Function.identity()));
        this.cardTypeMap = cardTypeMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.cardTypeMap == null;
    }

    private String buildCardTypeMapKey(CardType model) {
        return model.getCardType() + model.getLocale();
    }

}
