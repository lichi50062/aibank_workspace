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
package com.tfb.aibank.biz.user.services.homepagecard.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

//@formatter:off
/**
* @(#)UpdateUserHomePageCardRequest.java
* 
* <p>Description:更新使用者首頁牌卡設定 - Request</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UpdateUserHomePageCardRequest {

    /** 牌卡編號 */
    @Schema(description = "牌卡編號")
    private List<Integer> cardIds;

    /**
     * @return the cardIds
     */
    public List<Integer> getCardIds() {
        return cardIds;
    }

    /**
     * @param cardIds
     *            the cardIds to set
     */
    public void setCardIds(List<Integer> cardIds) {
        this.cardIds = cardIds;
    }

}
