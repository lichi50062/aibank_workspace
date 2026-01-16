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

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NSTOT015010Rs.java
 * 
 * <p>Description:廣告版位 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT015010Rs implements RsData {

    /**
     * 廣告牌卡
     */
    private AdCardInfo adCardInfo;

    /**
     * HomepageCard
     */
    private HomepageCard homepageCard;

    /**
     * 從cache取
     */
    private boolean fromCache;

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

    public boolean isFromCache() {
        return fromCache;
    }

    public void setFromCache(boolean fromCache) {
        this.fromCache = fromCache;
    }
}
