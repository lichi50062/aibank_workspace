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
package com.tfb.aibank.chl.system.ot007.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;
import com.tfb.aibank.chl.component.datacenter.model.OfferAction;

//@formatter:off
/**
* @(#)NSTOT007010Rs.java
* 
* <p>Description:個人化體驗 - RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NSTOT007010Rs implements RsData {
    /** 版型 */
    private List<OfferAction> offerActions;

    /** 交易存取紀錄追蹤編號 */
    private String traceId;

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

    /**
     * @return the traceId
     */
    public String getTraceId() {
        return traceId;
    }

    /**
     * @param traceId
     *            the traceId to set
     */
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
