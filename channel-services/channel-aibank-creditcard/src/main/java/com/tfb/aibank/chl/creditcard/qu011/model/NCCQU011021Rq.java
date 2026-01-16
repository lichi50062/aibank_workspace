package com.tfb.aibank.chl.creditcard.qu011.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU011021Rq.java
 * 
 * <p>Description:好多金總覧 021 每期結餘資訊頁-展開詳細資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/10, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU011021Rq implements RqData {

    /** 「期數」 */
    private String billingPeriod;

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(String billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

}
