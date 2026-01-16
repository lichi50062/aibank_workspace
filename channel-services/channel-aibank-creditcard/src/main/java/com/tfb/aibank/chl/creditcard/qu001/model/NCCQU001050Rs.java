package com.tfb.aibank.chl.creditcard.qu001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001050Rs.java
* 
* <p>Description:信用卡總覽 繳款紀錄頁 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001050Rs implements RsData {
    /** 帳單 */
    private List<NCCQQU001Bill> bills;

    /**
     * @return the bills
     */
    public List<NCCQQU001Bill> getBills() {
        return bills;
    }

    /**
     * @param bills
     *            the bills to set
     */
    public void setBills(List<NCCQQU001Bill> bills) {
        this.bills = bills;
    }
}
