package com.tfb.aibank.chl.creditcard.qu011.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU011020Rq.java
 * 
 * <p>Description:好多金總覧 020 好多金總覧-每期結餘資訊頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU011020Rq implements RqData {

    /** 外部傳入的「期數」 */
    private String billingPeriod;

    public String getBillingPeriod() {
        return billingPeriod;
    }

    public void setBillingPeriod(String billingPeriod) {
        this.billingPeriod = billingPeriod;
    }

}
