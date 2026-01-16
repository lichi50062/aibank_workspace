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
* @(#)PromoStatsRs.java
* 
* <p>Description:數據中台 API- 取得客戶所屬標籤 - Rs</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/08/07, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*
*/
//@formatter:on
public class PromoStatsRs implements DataCenterBaseRs {

    /** 數據中台端自定義之訊息 */
    private String message;

    /** 結果 */
    private PromoStatsResult result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PromoStatsResult getResult() {
        return result;
    }

    public void setResult(PromoStatsResult result) {
        this.result = result;
    }
}
