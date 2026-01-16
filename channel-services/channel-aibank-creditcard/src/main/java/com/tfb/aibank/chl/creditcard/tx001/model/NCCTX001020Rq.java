package com.tfb.aibank.chl.creditcard.tx001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCTX001020Rq.java
*
* <p>Description:預借現金 申請資料輸入頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCTX001020Rq implements RqData {

    /** 預借現金金額 */
    private int amount;

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount
     *            the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

}
