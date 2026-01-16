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
package com.tfb.aibank.component.cardpicture;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.type.GlobalCacheKey;

// @formatter:off
/**
 * @(#)CardPictureCacheManager.java
 * 
 * <p>Description:卡面資料檔 Cache Manager</p>
 * <p>限定 Biz Service 使用</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, EvanWang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class CardPictureCacheManager extends AbstractCacheManager {

    private static final String DEFAULT_IMG_INDEX = "-1";

    @Autowired
    private CardPictureResource resource;

    /** Map<CARD_TYPE, IMAGE_URL> */
    private Map<String, String> cardPictureMap = new HashMap<>();

    private Map<String, String> getCardPictureMap() {
        return cardPictureMap;
    }

    public String getCardPicture(String cardType) {
        return Optional.ofNullable(getCardPictureMap().get(cardType)).orElse(getCardPictureMap().get(DEFAULT_IMG_INDEX));
    }

    /**
     * 回傳 global cache key
     * 
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.CARD_PICTURE_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {
        List<CardPicture> entities = resource.getCardPictures();
        Map<String, String> cardPictureMap = entities.stream().collect(Collectors.toMap(CardPicture::getCardType, CardPicture::getImageUrl, (existing, replacement) -> existing));
        this.cardPictureMap = cardPictureMap;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.cardPictureMap.isEmpty();
    }

}
