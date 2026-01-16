/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot015.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.JsonUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;
import com.tfb.aibank.chl.component.homepagecard.HomepageCardCacheManager;
import com.tfb.aibank.chl.system.ot015.model.AdCardInfo;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015Output;
import com.tfb.aibank.chl.system.ot015.model.NSTOT015User;
import com.tfb.aibank.chl.system.resource.NotificationResource;
import com.tfb.aibank.chl.system.resource.dto.UserCardViewLogModel;
import com.tfb.aibank.chl.system.resource.dto.UserCardViewLogRequest;
import com.tfb.aibank.chl.system.service.CardDescModel;
import com.tfb.aibank.chl.system.service.NSTService;

// @formatter:off
/**
 * @(#)NSTOT015Service.java
 * 
 * <p>Description:CHL NSTOT015 服務類別，撰寫此交易獨有的「邏輯」程式，所有method必須宣告 void</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class NSTOT015Service extends NSTService {

    public static final String CACHE_KEY = "NSTOT015_CACHE_KEY";

    @Autowired
    private HomepageCardCacheManager homepageCardCacheManager;

    @Autowired
    private NotificationResource notificationResource;

    private static final String CARD_TEMPLATE_POP_UP = "POP_UP";

    /**
     * 獲取廣告版位
     *
     * @param user
     * @param txn
     * @param cardTemplate
     * @param output
     */
    public void getAdCard(NSTOT015User user, String txn, String cardTemplate, NSTOT015Output output) {
        boolean isLoggedIn = StringUtils.isBlank(user.getDeviceUuid());
        Predicate<HomepageCard> adCardPredicate = hc -> StringUtils.equals(txn, hc.getCardUsedTaskId());
        Predicate<HomepageCard> cardTemplatePredicate = hc -> StringUtils.equals(cardTemplate, hc.getCardTemplate());
        Predicate<HomepageCard> isValidPredicate = hc -> {
            Date now = new Date();
            Date now30daysLater = DateUtils.addDay(now, 30);
            Date startTime = Optional.ofNullable(hc.getStartTime()).orElse(now);
            // fetch expired 30 days
            Date endTime = Optional.ofNullable(hc.getEndTime()).map(et -> DateUtils.addDay(et, 30)).orElse(now30daysLater);

            return startTime.compareTo(now) == 0 || endTime.compareTo(now) == 0 || now.after(startTime) && now.before(endTime);
        };
        Predicate<HomepageCard> hasSort = hc -> hc.getCardSort() != null;
        Predicate<HomepageCard> hasIcon = hc -> hc.getCardIcon() != null;

        @SuppressWarnings("unchecked")
        List<HomepageCard> homepageCardList = homepageCardCacheManager.getMulHomePageCardByPredicate(adCardPredicate, isValidPredicate, cardTemplatePredicate, hasSort, hasIcon);
        if (CollectionUtils.isEmpty(homepageCardList)) {
            return;
        }

        if (CollectionUtils.isNotEmpty(homepageCardList)) {
            Predicate<HomepageCard> taType2 = card -> card.getTaType() == 2 && StringUtils.isNotBlank(card.getTaGroup()) && (isLoggedIn ? notificationResource.isInAiCustomerGroup(user.getCustId(), card.getTaGroup()) : notificationResource.isInAiCustomerGroup(user.getDeviceUuid(), card.getTaGroup(), isLoggedIn));
            Predicate<HomepageCard> taType0 = card -> card.getTaType() == 0;

            homepageCardList = homepageCardList.stream() //
                    .filter(card -> taType2.test(card) || taType0.test(card)) //
                    .sorted((a, b) -> b.getTaType() - a.getTaType()) //
                    .sorted((a, b) -> b.getStartTime().compareTo(a.getStartTime())) //
                    .sorted((a, b) -> a.getCardKey() - b.getCardKey()) //
                    .sorted((a, b) -> a.getCardSort() - b.getCardSort()) //
                    .collect(Collectors.toList());
        }

        UserCardViewLogModel lastSeenCard = isLoggedIn ? //
                informationResource.findLastSeenCard(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), txn) : //
                informationResource.findLastSeenCard(user.getDeviceUuid(), txn);
        if (CARD_TEMPLATE_POP_UP.equals(cardTemplate) && lastSeenCard != null) {
            // 登出的要檢查7天內是否已跳過廣告
            // 7天內曾跳過廣告則不顯示
            int diff = DateUtils.getDaysBetween(lastSeenCard.getCreateTime(), new Date());
            if (diff <= 7) {
                return;
            }
        }

        HomepageCard nextAdCard = getNextAdCard(homepageCardList, lastSeenCard);
        AdCardInfo adCardInfo = null;

        // 廣告版位為整張圖片還是套版
        if (nextAdCard != null) {
            CardDescModel cardDesc = JsonUtils.getObject(nextAdCard.getCardDesc(), CardDescModel.class);
            adCardInfo = new AdCardInfo();
            if (cardDesc != null) {
                adCardInfo.setTag(cardDesc.getTag());
                adCardInfo.setTitle(cardDesc.getTitle());
                adCardInfo.setContent(cardDesc.getContent());
                adCardInfo.setContent2(cardDesc.getContent2());
                adCardInfo.setLink(cardDesc.getLink());
                adCardInfo.setIcon(nextAdCard.getCardIcon());
                adCardInfo.setPicUrl(cardDesc.getPicUrl());
                adCardInfo.setLinkName(cardDesc.getLinkName());
                adCardInfo.setLinkName1(cardDesc.getLinkName1());
                adCardInfo.setLinkName2(cardDesc.getLinkName2());
            }
            else {
                adCardInfo.setIcon(nextAdCard.getCardDesc());
            }
            adCardInfo.setCardTarget(nextAdCard.getCardTarget());
            adCardInfo.setCardTarget2(nextAdCard.getCardTarget2());
            adCardInfo.setCardTemplate(nextAdCard.getCardTemplate());
            adCardInfo.setWholePicture(cardDesc == null);
        }

        output.setAdCardInfo(adCardInfo);
        output.setHomepageCard(nextAdCard);
    }

    private HomepageCard getNextAdCard(List<HomepageCard> cardpool, UserCardViewLogModel lastSeenCard) {
        Date now = new Date();
        Date sysDate30DaysLater = DateUtils.addDay(now, 30);
        if (CollectionUtils.isEmpty(cardpool)) {
            return null;
        }
        HomepageCard card = cardpool.get(0);
        Date endTime = Optional.ofNullable(card).map(HomepageCard::getEndTime).orElse(sysDate30DaysLater);

        // 沒有前一張, 回第一張
        // check if expire first before return
        if (lastSeenCard == null && CollectionUtils.isNotEmpty(cardpool)) {
            return now.before(endTime) ? card : null;
        }

        if (CollectionUtils.isNotEmpty(cardpool) && cardpool.size() == 1) {
            if ((lastSeenCard == null || (card.getCardKey().equals(lastSeenCard.getCardKey()) && ConvertUtils.str2Int(card.getCardVersion()) != lastSeenCard.getCardVersion())) && now.before(endTime)) {
                return card;
            }
            else {
                return null;
            }
        }

        for (int i = 0; i < cardpool.size(); i++) {
            HomepageCard innerCard = cardpool.get(i);
            Date innerEndTime = Optional.ofNullable(innerCard).map(HomepageCard::getEndTime).orElse(sysDate30DaysLater);
            if (innerCard.getCardKey().equals(lastSeenCard.getCardKey())) {
                if (ConvertUtils.str2Int(innerCard.getCardVersion()) != lastSeenCard.getCardVersion() && now.before(innerEndTime)) {
                    return innerCard;
                }

                int nextCardIdx = i + 1;
                // 上一次看到的卡片為i, 則取下一張
                // 過期也取下一張
                // 假如以繞一圈都沒有, 不顯示
                while (nextCardIdx % cardpool.size() != i) {
                    HomepageCard nextCard = cardpool.get(nextCardIdx % cardpool.size());
                    innerEndTime = Optional.ofNullable(nextCard).map(HomepageCard::getEndTime).orElse(sysDate30DaysLater);
                    if (now.before(innerEndTime)) {
                        return nextCard;
                    }

                    nextCardIdx++;
                }

                // 繞一圈都沒取到卡片, 不顯示
                return null;
            }
        }

        // 上一次的卡片完全沒比對到, 回第一張
        // check if expired before return
        return now.before(endTime) ? card : null;
    }

    /**
     * 紀錄觀看log
     *
     * @param user
     * @param homepageCard
     */
    public void saveViewLog(NSTOT015User user, HomepageCard homepageCard) {
        boolean isLoggedIn = StringUtils.isBlank(user.getDeviceUuid());

        UserCardViewLogRequest rq = new UserCardViewLogRequest();
        if (isLoggedIn) {
            rq.setCustId(user.getCustId());
            rq.setUidDup(user.getUidDup());
            rq.setUserId(user.getUserId());
            rq.setCompanyKind(user.getCompanyKind());
        }
        else {
            rq.setDeviceUuid(user.getDeviceUuid());
        }

        rq.setCardKey(homepageCard.getCardKey());
        rq.setCardUsedTaskId(homepageCard.getCardUsedTaskId());
        rq.setCardVersion(ConvertUtils.str2Int(homepageCard.getCardVersion()));
        informationResource.saveViewLog(rq);
    }

    /**
     * 紀錄廣告成效點擊
     *
     * @param homepageCard
     */
    public void saveClickLog(HomepageCard homepageCard) {
        Integer cardVersion = ConvertUtils.str2Int(homepageCard.getCardVersion());
        informationResource.saveClickLog(homepageCard.getCardKey(), cardVersion);
    }

}
