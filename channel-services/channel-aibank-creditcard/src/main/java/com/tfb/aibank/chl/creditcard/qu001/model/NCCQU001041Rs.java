package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001040Rs.java
* 
* <p>Description:信用卡總覽 歷史帳單頁 續傳資料 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/30, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001041Rs implements RsData {

    /**
     * 歷史帳單
     */
    private NCCQQU001HistoricalBill historicalBill;

    /**
     * costco錯誤
     */
    private boolean costcoError;

    /**
     * @return the historicalBill
     */
    public NCCQQU001HistoricalBill getHistoricalBill() {
        return historicalBill;
    }

    /**
     * @param historicalBill
     *            the historicalBill to set
     */
    public void setHistoricalBill(NCCQQU001HistoricalBill historicalBill) {
        this.historicalBill = historicalBill;
    }

    /**
     * @return the costcoError
     */
    public boolean isCostcoError() {
        return costcoError;
    }

    /**
     * @param costcoError the costcoError to set
     */
    public void setCostcoError(boolean costcoError) {
        this.costcoError = costcoError;
    }

}
