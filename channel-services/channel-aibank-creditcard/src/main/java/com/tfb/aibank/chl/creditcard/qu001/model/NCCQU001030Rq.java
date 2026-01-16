package com.tfb.aibank.chl.creditcard.qu001.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

//@formatter:off
/**
* @(#)NCCQU001030Rq.java
* 
* <p>Description:信用卡總覽 帳單詳細頁 RQ</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU001030Rq implements RqData {
    /** 信用卡卡號 */
    private String cardNo;
    /** 查詢月份 VQQRYM */
    private int queryMonth;
    /** 來自哪一頁 */
    private String pageFrom;
    /**
     * @return the cardNo
     */
    public String getCardNo() {
        return cardNo;
    }
    
    /**
     * @param cardNo
     *            the cardNo to set
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return the queryMonth
     */
    public int getQueryMonth() {
        return queryMonth;
    }

    /**
     * @param queryMonth
     *            the queryMonth to set
     */
    public void setQueryMonth(int queryMonth) {
        this.queryMonth = queryMonth;
    }

    /**
     * @return the pageFrom
     */
    public String getPageFrom() {
        return pageFrom;
    }

    /**
     * @param pageFrom
     *            the pageFrom to set
     */
    public void setPageFrom(String pageFrom) {
        this.pageFrom = pageFrom;
    }

}
