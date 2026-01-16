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
package com.tfb.aibank.chl.general.ag002.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.NumberUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.general.ag002.model.NGNAG002CardData;
import com.tfb.aibank.chl.general.ag002.service.model.NGNAG002GetSettingCardsCondition;
import com.tfb.aibank.chl.general.ag002.service.model.NGNAG002GetSettingCardsResult;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.resource.dto.RetrieveUserHomePageCardResponse;
import com.tfb.aibank.chl.general.resource.dto.UserHomePageCardModel;
import com.tfb.aibank.chl.service.AIBankChannelService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)NGNAG002Service.java
 * 
 * <p>Description:首頁牌卡設定 - Service</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/25, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NGNAG002Service extends AIBankChannelService {

    @Autowired
    private UserResource userResource;
    @Autowired
    private HomepageCardCacheManager homepageCardCacheManager;

    /** 可設定牌卡數量上限 */
    public static final int MAX_COUNT = 5;
    /** 無DB設定時預設牌卡數量上限 */
    public static final int DEFAULT_MAX_COUNT = 3;

    /**
     * 取得畫面牌卡設定資料
     * 
     * @param condition
     * @param result
     */
    public void getSettingCards(NGNAG002GetSettingCardsCondition condition, NGNAG002GetSettingCardsResult result) {

        // 使用者已設定之首頁牌卡資料
        List<UserHomePageCardModel> userCards = new ArrayList<>();
        // 取得使用者首頁牌卡設定
        RetrieveUserHomePageCardResponse retrieveUserHomePageCardResponse = retrieveUserHomePageCard(condition.getLoginUser());
        if (retrieveUserHomePageCardResponse != null && CollectionUtils.isNotEmpty(retrieveUserHomePageCardResponse.getCards())) {
            // 有DB設定資料
            userCards.addAll(retrieveUserHomePageCardResponse.getCards());
        }
        // 預設之首頁牌卡資料
        List<HomepageCard> defaultCards = retrieveDefaultHomePageCard();

        // 已設定牌卡
        List<NGNAG002CardData> cards = new ArrayList<>();
        // 其他牌卡
        List<NGNAG002CardData> otherCards = new ArrayList<>();

        // 預設牌卡設定要有資料
        if (CollectionUtils.isNotEmpty(defaultCards)) {

            // 有DB設定資料
            if (CollectionUtils.isNotEmpty(userCards)) {
                List<Integer> cardIds = new ArrayList<>();
                for (UserHomePageCardModel userCard : userCards) {
                    HomepageCard defaultCard = defaultCards.stream().filter(c -> c.getCardId().equals(userCard.getCardId())).findFirst().orElse(null);
                    if (defaultCard != null) {
                        NGNAG002CardData card = convert(defaultCard);
                        cards.add(card);
                        cardIds.add(defaultCard.getCardId());
                        if (cards.size() == MAX_COUNT) {
                            break;
                        }
                    }
                }
                List<Integer> otherCardIds = new ArrayList<>();
                for (HomepageCard defaultCard : defaultCards) {
                    if (!cardIds.contains(defaultCard.getCardId()) && !otherCardIds.contains(defaultCard.getCardId())) {
                        NGNAG002CardData card = convert(defaultCard);
                        otherCards.add(card);
                        otherCardIds.add(defaultCard.getCardId());
                    }
                }
            }
            // 沒有DB設定資料，使用預設順序
            else {
                List<Integer> otherCardIds = new ArrayList<>();
                for (int i = 0; i < defaultCards.size(); i++) {
                    HomepageCard defaultCard = defaultCards.get(i);
                    if (!otherCardIds.contains(defaultCard.getCardId())) {
                        NGNAG002CardData card = convert(defaultCard);
                        if (cards.size() < DEFAULT_MAX_COUNT) {
                            cards.add(card);
                        }
                        else {
                            otherCards.add(card);
                        }
                        otherCardIds.add(defaultCard.getCardId());
                    }
                }
            }
        }
        result.setCards(cards);
        result.setOtherCards(otherCards);
    }

    /**
     * 轉換Model
     * 
     * @param homepageCard
     * @return
     */
    private NGNAG002CardData convert(HomepageCard homepageCard) {
        NGNAG002CardData card = new NGNAG002CardData();
        card.setCardId(homepageCard.getCardId());
        card.setCardName(homepageCard.getCardName());
        card.setCardbg(homepageCard.getCardBg());
        card.setCardIcon(homepageCard.getCardIcon());
        card.setCardDesc(homepageCard.getCardDesc());
        card.setSortSeq(homepageCard.getCardSortDynamic());
        return card;
    }

    /**
     * 取得預設首頁牌卡設定
     * 
     * @return
     */
    private List<HomepageCard> retrieveDefaultHomePageCard() {

        List<HomepageCard> allCards = homepageCardCacheManager.getHomepageCards();
        if (CollectionUtils.isEmpty(allCards)) {
            return new ArrayList<>();
        }

        String defaultHomepageCardIds = systemParamCacheManager.getValue(AIBankConstants.CHANNEL_NAME, "DEFAULT_HOMEPAGE_CARD");
        logger.debug("[defaultHomepageCardIds] : {}", defaultHomepageCardIds);
        List<Integer> cardIdStored = new ArrayList<>();
        if (StringUtils.isNotBlank(defaultHomepageCardIds)) {
            Stream.of(defaultHomepageCardIds.split(",")).forEach(id -> {
                cardIdStored.add(ConvertUtils.str2Int(StringUtils.trim(id)));
            });
        }

        List<HomepageCard> homepageCardTmpList =
                allCards.stream().filter(c -> c.getShowFlag() != null && c.getShowFlag().intValue() == 1).filter(c -> StringUtils.equalsIgnoreCase("NGNAG002", c.getCardUsedTaskId())).collect(Collectors.toList());


        if (CollectionUtils.isNotEmpty(cardIdStored)) {
            homepageCardTmpList.forEach(hcs -> {
                if (cardIdStored.contains(hcs.getCardId())) {
                    int indexInSort = cardIdStored.indexOf(hcs.getCardId());
                    hcs.setCardSortDynamic(indexInSort + 1);
                }
                else {
                    // 沒有比對到預設值時，原值(為了避免cardSort == null，預設20)加上50，讓排序時放在後面
                    hcs.setCardSortDynamic(NumberUtils.defaultValue(hcs.getCardSort(), 20) + 50);
                }
            });
        }
        else {
            homepageCardTmpList.forEach(hct -> {
                hct.setCardSortDynamic(NumberUtils.defaultValue(hct.getCardSort(), 20) + 50);
            });
        }
        return homepageCardTmpList.stream().sorted(Comparator.comparing(HomepageCard::getCardSortDynamic)).collect(Collectors.toList());
    }

    /**
     * 取得使用者首頁牌卡設定
     * 
     * @param loginUser
     * @return
     */
    private RetrieveUserHomePageCardResponse retrieveUserHomePageCard(AIBankUser loginUser) {

        return userResource.retrieveUserHomePageCard(loginUser.getCustId(), loginUser.getUserId(), loginUser.getUidDup(), loginUser.getCompanyKind());
    }

}
