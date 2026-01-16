package com.tfb.aibank.chl.creditcard.qu001.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU001012Rs.java
* 
* <p>Description:信用卡總覽 功能首頁 取得本期未出帳消費明細 RS</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/08, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001015Rs implements RsData {
    /** 信用卡帳務資料發生錯誤 */
    private boolean cew205rError;
    /** 信用卡帳務資料 */
    private List<NCCQQU001Bill> bills;

    /**
     * @return the cew205rError
     */
    public boolean isCew205rError() {
        return cew205rError;
    }

    /**
     * @param cew205rError the cew205rError to set
     */
    public void setCew205rError(boolean cew205rError) {
        this.cew205rError = cew205rError;
    }

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
