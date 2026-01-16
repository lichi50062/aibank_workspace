package com.tfb.aibank.chl.creditcard.qu011.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU011021Rs.java
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
public class NCCQU011021Rs implements RsData {

    /** 好多金餘額 */
    private NCCQU011CostcoBalance costcoBalance;

    public NCCQU011CostcoBalance getCostcoBalance() {
        return costcoBalance;
    }

    public void setCostcoBalance(NCCQU011CostcoBalance costcoBalance) {
        this.costcoBalance = costcoBalance;
    }

}
