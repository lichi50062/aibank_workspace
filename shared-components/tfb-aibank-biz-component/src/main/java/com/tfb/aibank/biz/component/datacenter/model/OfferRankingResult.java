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

// @formatter:off
/**
 * @(#)OfferRankingResult.java
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
public class OfferRankingResult {
    private CaseInfo caseInfo;
    private List<Offer> offerList;
    private OfferCount offerCount;
    private String apiCallId;
    private Long timestamp;

    /**
     * @return the caseInfo
     */
    public CaseInfo getCaseInfo() {
        return caseInfo;
    }

    /**
     * @param caseInfo
     *            the caseInfo to set
     */
    public void setCaseInfo(CaseInfo caseInfo) {
        this.caseInfo = caseInfo;
    }

    /**
     * @return the offerList
     */
    public List<Offer> getOfferList() {
        return offerList;
    }

    /**
     * @param offerList
     *            the offerList to set
     */
    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }

    /**
     * @return the offerCount
     */
    public OfferCount getOfferCount() {
        return offerCount;
    }

    /**
     * @param offerCount
     *            the offerCount to set
     */
    public void setOfferCount(OfferCount offerCount) {
        this.offerCount = offerCount;
    }

    /**
     * @return the apiCallId
     */
    public String getApiCallId() {
        return apiCallId;
    }

    /**
     * @param apiCallId
     *            the apiCallId to set
     */
    public void setApiCallId(String apiCallId) {
        this.apiCallId = apiCallId;
    }

    /**
     * @return the timestamp
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp
     *            the timestamp to set
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
