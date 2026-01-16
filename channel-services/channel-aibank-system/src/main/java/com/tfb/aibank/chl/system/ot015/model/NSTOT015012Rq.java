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

import com.ibm.tw.ibmb.base.model.RqData;
import com.tfb.aibank.chl.component.homepagecard.HomepageCard;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NSTOT015012Rq.java
 * 
 * <p>Description:廣告版位 012 廣告成效記錄</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/14, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT015012Rq implements RqData {

    /**
     * homepageCard
     */
    private HomepageCard homepageCard;

    public HomepageCard getHomepageCard() {
        return homepageCard;
    }

    public void setHomepageCard(HomepageCard homepageCard) {
        this.homepageCard = homepageCard;
    }
}
