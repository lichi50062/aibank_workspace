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
package com.tfb.aibank.chl.component.homepagecard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.cache.AbstractCacheManager;
import com.ibm.tw.ibmb.component.context.MDCKey;
import com.ibm.tw.ibmb.type.GlobalCacheKey;
import com.ibm.tw.ibmb.util.IBUtils;

// @formatter:off
/**
 * @(#)HomepageCardCacheManager.java
 * 
 * <p>Description:首頁牌卡設定檔 Cache Manager</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, Marty
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class HomepageCardCacheManager extends AbstractCacheManager {

    @Autowired
    private HomepageCardResource homepageCardResource;

    @Autowired
    private AICustomerGroupResource aiCustomerGroupResource;

    private Map<String, List<HomepageCard>> homepageCards = new HashMap<>();

    public List<HomepageCard> getHomepageCards() {
        List<HomepageCard> card = this.getHomepageCards(MDC.get(MDCKey.locale.toString()));

        if (card == null) {
            card = this.getHomepageCards(Locale.TAIWAN.toString());
        }
        return card;
    }

    public List<HomepageCard> getHomepageCards(String locale) {
        return this.homepageCards.get(locale);
    }

    /**
     * 以 cardId 和 cardUsed 找牌卡設定檔
     * 
     * @param cardId
     * @param cardUsed
     * @return
     */
    public HomepageCard getHomePageCardByPk(Integer cardId, Integer cardUsed) {
        return this.getHomepageCards().stream().filter(card -> Objects.equals(card.getCardId(), cardId) && Objects.equals(card.getCardUsed(), cardUsed)).findFirst().orElse(new HomepageCard());
    }

    /**
     * 依條件取得牌卡設定檔
     * 
     * @param predicate
     * @return
     */
    public HomepageCard getHomePageCardByPredicate(Predicate<HomepageCard> predicate) {
        return this.getHomepageCards().stream()//
                .filter(predicate)// 交易類別篩選
                .filter(c -> isValid(c)) // 下架時間篩選
                .findFirst().orElse(null);
    }

    /**
     * 依條件取得牌卡設定檔
     * 
     * @param predicate
     * @return
     */
    public List<HomepageCard> getMulHomePageCardByPredicate(Predicate<HomepageCard> predicate) {
        return this.getHomepageCards().stream() //
                .filter(predicate)// 交易類別篩選
                .filter(c -> isValid(c)) // 下架時間篩選
                .sorted(Comparator.comparingInt(HomepageCard::getCardSort)).collect(Collectors.toList());
    }

    /**
     * 依條件取得牌卡設定檔
     * 
     * @param customFilters
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<HomepageCard> getMulHomePageCardByPredicate(Predicate<HomepageCard>... customFilters) {
        Stream<HomepageCard> homepageCardStream = this.getHomepageCards().stream();
        if (customFilters != null && customFilters.length > 0) {
            for (Predicate<HomepageCard> p : customFilters) {
                homepageCardStream = homepageCardStream.filter(p);
            }
        }

        return homepageCardStream.sorted(Comparator.comparingInt(HomepageCard::getCardSort)).sorted(Comparator.comparingInt(HomepageCard::getCardKey)).collect(Collectors.toList());
    }

    /**
     * 依條件取得牌卡設定檔，並過濾 TA_TYPE
     * 
     * @param predicate
     * @return
     */
    public List<HomepageCard> getMulHomePageCardByPredicate(Predicate<HomepageCard> predicate, String custId) {
        List<HomepageCard> filterCards = this.getHomepageCards().stream() //
                .filter(predicate) // 交易類別篩選
                .filter(c -> isValid(c)) // 下架時間篩選
                .filter(c -> isMatchTaType(c, custId)) // 客群篩選
                .collect(Collectors.toList());

        int limit = getlimit(filterCards);
        return sortCard(filterCards).stream().limit(limit).collect(Collectors.toList());
    }

    /**
     * 是否還在架上
     * 
     * @param card
     * @return
     */
    private boolean isValid(HomepageCard card) {
        Date now = new Date();
        Date startTime = Optional.ofNullable(card.getStartTime()).orElse(now);
        // set endTime to an absurd future since it's not set
        Date endTime = Optional.ofNullable(card.getEndTime()).orElseGet(() -> new Date(Long.MAX_VALUE));

        return startTime.compareTo(now) == 0 || endTime.compareTo(now) == 0 || now.after(startTime) && now.before(endTime);
    }

    /**
     * 筆數判斷
     * 
     * (1) 若 TMP_HOMEPAGE_CARD.CARD_TEMPLATE = 'TASK_CARD'，回傳第一筆資料。
     * 
     * (2) 若 TMP_HOMEPAGE_CARD.CARD_TEMPLATE = 'AD_CARD1'、'AD_CARD2'，回傳前10筆資料。
     * 
     * @param cards
     * @return
     */
    private int getlimit(List<HomepageCard> cards) {
        if (cards != null && cards.size() > 0) {
            HomepageCard card = cards.get(0);
            if (StringUtils.equals("TASK_CARD", card.getCardTemplate())) {
                return 1;
            }
        }
        return 10;
    }

    /**
     * (1) 若 TA_TYPE = 0，表示全客群，無需排除。(未登入時只回TA_TYPE = 0）
     * 
     * (2) 若 TA_TYPE = 1，表示功能自定義客群，需要依照原規格書判斷ID對應的客群顯示，並置換對應參數。(無需排除)
     * 
     * (3) 若 TA_TYPE = 2，表示 Unica 定義客群，需再至 DB AI_CUSTOMER_GROUP (CUSTOMER_ID = AI Bank User 的 CUSTOMER_UUID AES 加密 and TREATMENT_CODE = DB HOMEPAGE_CARD.TA_GROUP)。
     * 
     * @param card
     * @param custId
     * @return
     */
    private boolean isMatchTaType(HomepageCard card, String custId) {

        if (StringUtils.isBlank(MDC.get(MDCKey.custid.toString()))) {
            if (card.getTaType() == 0 || card.getTaType() == 1) {
                return true;
            }
            return false;
        }

        if (card.getTaType() == 0 || card.getTaType() == 1)
            return true;

        // 以下為 Unica 名單
        if (StringUtils.isEmpty(card.getTaGroup())) {
            return false;
        }

        if (aiCustomerGroupResource.isInAiCustomerGroup(custId, card.getTaGroup())) {
            return true;
        }
        return false;
    }

    /**
     * 排序
     * 
     * (1) TA_TYPE = 2 >>>> TA_TYPE = 1 >>>> TA_TYPE = 0。
     * 
     * (2) 若有相同的 TA_TYPE，則依序 START_TIME 由近到遠排序。
     * 
     * @param cards
     * @return
     */
    private List<HomepageCard> sortCard(List<HomepageCard> cards) {

        if (cards == null || cards.size() == 0 || cards.size() == 1)
            return cards;

        IBUtils.sort(cards, "startTime", true);

        List<HomepageCard> newCardsList = new ArrayList<HomepageCard>();

        for (HomepageCard c : cards) {
            if (c.getTaType() == 1) {
                newCardsList.add(c);
            }
        }
        for (HomepageCard c : cards) {
            if (c.getTaType() == 2) {
                newCardsList.add(c);
            }
        }
        for (HomepageCard c : cards) {
            if (c.getTaType() == 0) {
                newCardsList.add(c);
            }
        }

        return newCardsList;
    }

    /**
     * 回傳 global cache key
     *
     * @return
     */
    @Override
    protected GlobalCacheKey globalCacheKey() {
        return GlobalCacheKey.HOMEPAGE_CARD_CACHE_KEY;
    }

    /**
     * 載入資料並存入 cache
     */
    @Override
    protected void loadCache() {

        List<HomepageCardVo> cards = homepageCardResource.getHomepageCards();

        Map<String, List<HomepageCard>> tmpCard = new HashMap<>();
        List<HomepageCard> twCards = new ArrayList<>();
        List<HomepageCard> usCards = new ArrayList<>();
        List<HomepageCard> cnCards = new ArrayList<>();
        cards.forEach(card -> {
            HomepageCard tw = copy(card);
            HomepageCard us = copy(card);
            HomepageCard cn = copy(card);

            for (HomepageCardI18nModel i18 : card.getCardDescs()) {
                if (StringUtils.equals(Locale.TAIWAN.toString(), i18.getLocale())) {
                    tw.setCardDesc(i18.getCardDesc());
                    twCards.add(tw);
                }
                if (StringUtils.equals(Locale.US.toString(), i18.getLocale())) {
                    us.setCardDesc(i18.getCardDesc());
                    usCards.add(us);
                }
                if (StringUtils.equals(Locale.CHINA.toString(), i18.getLocale())) {
                    cn.setCardDesc(i18.getCardDesc());
                    cnCards.add(cn);
                }
            }
        });

        tmpCard.put(Locale.TAIWAN.toString(), twCards);
        tmpCard.put(Locale.US.toString(), usCards);
        tmpCard.put(Locale.CHINA.toString(), cnCards);

        this.homepageCards = tmpCard;
    }

    private HomepageCard copy(HomepageCardVo source) {
        if (source == null)
            return null;

        HomepageCard target = new HomepageCard();
        target.setCardBg(source.getCardBg());
        target.setCardFold(source.getCardFold());
        target.setCardIcon(source.getCardIcon());
        target.setCardId(source.getCardId());
        target.setCardKey(source.getCardKey());
        target.setCardName(source.getCardName());
        target.setCardParam(source.getCardParam());
        target.setCardSort(source.getCardSort());
        target.setCardTarget(source.getCardTarget());
        target.setCardTemplate(source.getCardTemplate());
        target.setCardUsed(source.getCardUsed());
        target.setCreateTime(source.getCreateTime());
        target.setCardUsedTaskId(source.getCardUsedTaskId());
        target.setEndTime(source.getEndTime());
        target.setQuery(source.getQuery());
        target.setShowFlag(source.getShowFlag());
        target.setStartTime(source.getStartTime());
        target.setTaGroup(source.getTaGroup());
        target.setTaType(source.getTaType());
        target.setUpdateTime(source.getUpdateTime());
        target.setFromNoApplyOnline(source.getFromNoApplyOnline());
        target.setFromNoCallback(source.getFromNoCallback());
        target.setCardVersion(source.getCardVersion());
        target.setCardTarget2(source.getCardTarget2());

        return target;
    }

    @Override
    protected boolean isFirstLoad() {
        return this.homepageCards.isEmpty();
    }

}
