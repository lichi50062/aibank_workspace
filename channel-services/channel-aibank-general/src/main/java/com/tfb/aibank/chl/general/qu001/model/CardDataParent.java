package com.tfb.aibank.chl.general.qu001.model;

import com.tfb.aibank.chl.component.homepagecard.CardEvent;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;


// @formatter:off
/**
 * @(#)CardDataParent.java
 *
 * <p>Description: 功能牌卡-共用父類別</p>
 *
 * <p>Modify History:</p>
 * v1, 2023/05/22, MartyPan
 * <ol>
 *  <li>共用父類別</li>
 * </ol>
 */
//@formatter:on
public class CardDataParent {

    /**
     * 個功能牌卡預設資料 <- table [HOMEPAGE_CARD]
     * 當此項目有值時，表示使用者未登入或無免登速查設定，
     * 顯示預設廣告資訊
     * */
    private HomepageCard homepageCard;

    /**
     *
     * 牌卡箭頭icon目的(目標URL)
     * 未登入時放在HomepageCard裡
     * 此牌卡有資料時，單獨放在此處
     */
    private String cardTarget;

    /** CardEvent for Celebrus */
    private CardEvent cardEvent;

    public CardDataParent() {
    }

    public CardDataParent(HomepageCard homepageCard) {
        this.homepageCard = homepageCard;
        if( null != homepageCard ) {
            cardEvent = homepageCard.getCardEvent();
        }
    }

    public HomepageCard getHomepageCard() {
        return homepageCard;
    }

    public void setHomepageCard(HomepageCard homepageCard) {
        this.homepageCard = homepageCard;
    }

    public String getCardTarget() {
        return cardTarget;
    }

    public void setCardTarget(String cardTarget) {
        this.cardTarget = cardTarget;
    }

    public CardEvent getCardEvent() {
        return cardEvent;
    }

    public void setCardEvent(CardEvent cardEvent) {
        this.cardEvent = cardEvent;
    }
}
