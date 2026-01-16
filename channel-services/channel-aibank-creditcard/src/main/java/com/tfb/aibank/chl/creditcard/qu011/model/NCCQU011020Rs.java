package com.tfb.aibank.chl.creditcard.qu011.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU011020Rs.java
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
public class NCCQU011020Rs implements RsData {

    /** 好多金餘額 (順序固定) */
    private List<NCCQU011CostcoBalance> costcoBalanceList = new ArrayList<>();

    /** 期數 */
    private int period;

    public List<NCCQU011CostcoBalance> getCostcoBalanceList() {
        return costcoBalanceList;
    }

    public void setCostcoBalanceList(List<NCCQU011CostcoBalance> costcoBalanceList) {
        this.costcoBalanceList = costcoBalanceList;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

}
