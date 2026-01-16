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

//@formatter:off
/**
* @(#)NSTOT006010Rs.java
* 
* <p>Description:繳費交易不支援信用卡戶錯誤頁 - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/04/09, Yoyo Lin
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT006020Rs implements RsData {

    /** 卡牌底色 */
    private String homePageCardBg;
    /** 卡牌內容 */
    private String homePageCardDesc;
    /** 卡牌引導連結 */
    private String homePageCardTarget;

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

}
