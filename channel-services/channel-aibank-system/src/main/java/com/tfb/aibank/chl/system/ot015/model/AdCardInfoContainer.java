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

import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

//@formatter:off
/**
* @(#)AdCardInfoContainer.java
* 
* <p>Description: 廣告牌卡ocntainer</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/16, Kevin Tung
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AdCardInfoContainer {

    /**
     * 廣告牌卡
     */
    private AdCardInfo adCardInfo;

    /**
     * HomepageCard
     */
    private HomepageCard homepageCard;

    public AdCardInfo getAdCardInfo() {
        return adCardInfo;
    }

    public void setAdCardInfo(AdCardInfo adCardInfo) {
        this.adCardInfo = adCardInfo;
    }

    public HomepageCard getHomepageCard() {
        return homepageCard;
    }

    public void setHomepageCard(HomepageCard homepageCard) {
        this.homepageCard = homepageCard;
    }
}
