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
package com.tfb.aibank.chl.component.datacenter.model;

import java.util.List;

//@formatter:off
/**
* @(#)OfferActionResponse.java
* 
* <p>Description:取得情境版位資料 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class OfferActionResponse {
    /** 版型 */
    private List<OfferAction> offerActions;

    /**
     * @return the offerActions
     */
    public List<OfferAction> getOfferActions() {
        return offerActions;
    }

    /**
     * @param offerActions
     *            the offerActions to set
     */
    public void setOfferActions(List<OfferAction> offerActions) {
        this.offerActions = offerActions;
    }

}
