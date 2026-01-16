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
package com.tfb.aibank.chl.general.resource.dto;

import java.util.List;

//@formatter:off
/**
* @(#)RetrieveUserHomePageCardResponse.java
* 
* <p>Description:取得使用者首頁牌卡設定 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class RetrieveUserHomePageCardResponse {

    /** 首頁牌卡設定 */
    private List<UserHomePageCardModel> cards;

    /** 是否已綁定且開啟免登速查(用裝置查詢時才回覆) */
    private boolean isBind;

    /**
     * @return the cards
     */
    public List<UserHomePageCardModel> getCards() {
        return cards;
    }

    /**
     * @param cards
     *            the cards to set
     */
    public void setCards(List<UserHomePageCardModel> cards) {
        this.cards = cards;
    }

    /**
     * @return the isBind
     */
    public boolean isBind() {
        return isBind;
    }

    /**
     * @param isBind
     *            the isBind to set
     */
    public void setBind(boolean isBind) {
        this.isBind = isBind;
    }

}
