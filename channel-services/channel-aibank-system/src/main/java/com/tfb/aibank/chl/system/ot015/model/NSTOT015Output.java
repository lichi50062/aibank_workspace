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
package com.tfb.aibank.chl.system.ot015.model;

import com.tfb.aibank.chl.component.devicebinding.model.QuickSearchResponse;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

// @formatter:off
/**
 * @(#)NSTOT015Output.java
 * 
 * <p>Description:廣告版位 輸出專用物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class NSTOT015Output {

    /**
     * 廣告牌卡
     */
    private AdCardInfo adCardInfo;

    /**
     * HomepageCard
     */
    private HomepageCard homepageCard;

    /**
     * 免登速查結果
     */
    private QuickSearchResponse quickSearchResponse;

    /**
     * 廣告版位用戶
     */
    private NSTOT015User adUser;

    public AdCardInfo getAdCardInfo() {
        return adCardInfo;
    }

    public void setAdCardInfo(AdCardInfo homeCardInfo) {
        this.adCardInfo = homeCardInfo;
    }

    public HomepageCard getHomepageCard() {
        return homepageCard;
    }

    public void setHomepageCard(HomepageCard homepageCard) {
        this.homepageCard = homepageCard;
    }

    public QuickSearchResponse getQuickSearchResponse() {
        return quickSearchResponse;
    }

    public void setQuickSearchResponse(QuickSearchResponse quickSearchResponse) {
        this.quickSearchResponse = quickSearchResponse;
    }

    public NSTOT015User getAdUser() {
        return adUser;
    }

    public void setAdUser(NSTOT015User adUser) {
        this.adUser = adUser;
    }
}
