package com.tfb.aibank.chl.creditcard.qu011.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NCCQU011011Rs.java
 * 
 * <p>Description:好多金總覧 011 查看更多</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU011011Rs implements RsData {

    /** 好多金明細 */
    private List<NCCQU011CostcoDetail> costcoDetails;

    /** 紀錄當前發查VB0052的「查詢月份」 */
    private String montyp;

    public String getMontyp() {
        return montyp;
    }

    public void setMontyp(String montyp) {
        this.montyp = montyp;
    }

    public List<NCCQU011CostcoDetail> getCostcoDetails() {
        return costcoDetails;
    }

    public void setCostcoDetails(List<NCCQU011CostcoDetail> costcoDetails) {
        this.costcoDetails = costcoDetails;
    }

}
