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

//@formatter:off
/**
* @(#)OfferRankingRs.java
* 
* <p>Description:數據中心基本欄位 - Rs</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/08, Alex PY Li 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class OfferRankingRs implements DataCenterBaseRs {
    /** 數據中台端自定義之訊息 */
    private String message;

    /** 結果 */
    private OfferRankingResult result;

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the result
     */
    public OfferRankingResult getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(OfferRankingResult result) {
        this.result = result;
    }

}
