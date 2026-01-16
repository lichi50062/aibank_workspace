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
package com.tfb.aibank.biz.component.datacenter.model;

import java.util.List;

import com.google.gson.JsonObject;

// @formatter:off
/**
 * @(#)Offer.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, alexlee	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class Offer {
    /** 情境之識別碼(暫訂為類別+情境編號) C12-1 */
    private String offerId;
    /** Offer順位 1 */
    private Integer offerRanking;
    /** Offer分數 80 */
    private Integer offerScore;
    /**
     * list of json
     * // @formatter:off
     * {
            "subofferId": "C13-2-2",
            "subofferRanking": "2",
            "subofferScore": "80",
            後面為非固定參數
            "userName": "陳大明",
            "account": "XXX",
            "investType": "奈米投",
            "currency": "台幣",
            "amount": "30000"
          }
        // @formatter:on
     */
    private List<JsonObject> offerInfo;

    /**
     * @return the offerId
     */
    public String getOfferId() {
        return offerId;
    }

    /**
     * @param offerId
     *            the offerId to set
     */
    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    /**
     * @return the offerRanking
     */
    public Integer getOfferRanking() {
        return offerRanking;
    }

    /**
     * @param offerRanking
     *            the offerRanking to set
     */
    public void setOfferRanking(Integer offerRanking) {
        this.offerRanking = offerRanking;
    }

    /**
     * @return the offerScore
     */
    public Integer getOfferScore() {
        return offerScore;
    }

    /**
     * @param offerScore
     *            the offerScore to set
     */
    public void setOfferScore(Integer offerScore) {
        this.offerScore = offerScore;
    }

    /**
     * @return the offerInfo
     */
    public List<JsonObject> getOfferInfo() {
        return offerInfo;
    }

    /**
     * @param offerInfo
     *            the offerInfo to set
     */
    public void setOfferInfo(List<JsonObject> offerInfo) {
        this.offerInfo = offerInfo;
    }


}
