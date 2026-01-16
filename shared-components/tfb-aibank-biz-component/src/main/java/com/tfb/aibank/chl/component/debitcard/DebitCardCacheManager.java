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
package com.tfb.aibank.chl.component.debitcard;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)DebitCardCacheManager.java
 * 
 * <p>Description:簽帳卡資料檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, EvanWang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class DebitCardCacheManager extends AbstractCacheManager {

    @Autowired
    private DebitCardResource resource;

    /** Map<CARD_TYPE + LOCALE, DebitCard> */
    private Map<String, DebitCard> debitCardMap = null;

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.DEBIT_CARD_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<DebitCard> debitCardModels = resource.getAllDebitCardImg();
        this.debitCardMap = debitCardModels.stream().collect(Collectors.toMap(this::buildCardTypeMapKey, Function.identity()));
    }

    @Override
    protected boolean isFirstLoad() {
        return this.debitCardMap == null;
    }

    private String buildCardTypeMapKey(DebitCard model) {
        return model.getCardType() + model.getLocale();
    }

    /**
     * 取得簽帳卡卡面
     * 
     * @param cardType
     * @param locale
     * @return
     */
    public String getDebitCardImgPath(String cardType, Locale locale) {
        return debitCardMap.get(cardType + locale.toString()).getCardPicture();
    }

    /**
     * 取得簽帳卡卡片資料 沒資料取預設
     * 
     * @param cardType
     * @param locale
     * @return
     */
    public DebitCard getDebitCardInfo(String cardType, Locale locale) {
        return Optional.ofNullable(debitCardMap.get(cardType + locale.toString())).orElse(debitCardMap.get("-1" + locale.toString()));
    }

    /**
     * 取得簽帳卡卡片資料 沒資料取預設
     * 
     * @param cardType
     * @param locale
     * @return
     */
    public DebitCard getDebitCardInfo(int cardType, Locale locale) {
        return Optional.ofNullable(debitCardMap.get(String.valueOf(cardType) + locale.toString())).orElse(debitCardMap.get("-1" + locale.toString()));
    }

}
