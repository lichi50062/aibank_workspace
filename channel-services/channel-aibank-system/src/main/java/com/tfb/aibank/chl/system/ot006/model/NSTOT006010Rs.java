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
package com.tfb.aibank.chl.system.ot006.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.homepagecard.CardEvent;

//@formatter:off
/**
* @(#)NSTOT006010Rs.java
* 
* <p>Description:未持有信用卡錯誤頁 - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT006010Rs implements RsData {

    /** 卡牌底色 */
    private String homePageCardBg;
    /** 卡牌內容 */
    private String homePageCardDesc;
    /** 卡牌引導連結 */
    private String homePageCardTarget;

    private CardEvent cardEvent;

    /**
     * @return the homePageCardBg
     */
    public String getHomePageCardBg() {
        return homePageCardBg;
    }

    /**
     * @param homePageCardBg
     *            the homePageCardBg to set
     */
    public void setHomePageCardBg(String homePageCardBg) {
        this.homePageCardBg = homePageCardBg;
    }

    /**
     * @return the homePageCardDesc
     */
    public String getHomePageCardDesc() {
        return homePageCardDesc;
    }

    /**
     * @param homePageCardDesc
     *            the homePageCardDesc to set
     */
    public void setHomePageCardDesc(String homePageCardDesc) {
        this.homePageCardDesc = homePageCardDesc;
    }

    /**
     * @return the homePageCardTarget
     */
    public String getHomePageCardTarget() {
        return homePageCardTarget;
    }

    /**
     * @param homePageCardTarget
     *            the homePageCardTarget to set
     */
    public void setHomePageCardTarget(String homePageCardTarget) {
        this.homePageCardTarget = homePageCardTarget;
    }

    public CardEvent getCardEvent() {
        return cardEvent;
    }

    public void setCardEvent(CardEvent cardEvent) {
        this.cardEvent = cardEvent;
    }

}
