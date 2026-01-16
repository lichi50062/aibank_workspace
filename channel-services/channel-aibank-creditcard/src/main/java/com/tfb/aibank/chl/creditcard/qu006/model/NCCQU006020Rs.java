package com.tfb.aibank.chl.creditcard.qu006.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

//@formatter:off
/**
* @(#)NCCQU006020Rs.java
*
* <p>Description:點數回饋中心 兌換紀錄頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/08/10 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
public class NCCQU006020Rs implements RsData {

    /** 兌換紀錄 */
    private List<NCCQU006020RsRepeats> exchangeList;

    /** 查詢狀態 0-正常 1-無資料 2-查詢失敗 */
    private int status;

    /**
     * @return the exchangeList
     */
    public List<NCCQU006020RsRepeats> getExchangeList() {
        return exchangeList;
    }

    /**
     * @param exchangeList
     *            the exchangeList to set
     */
    public void setExchangeList(List<NCCQU006020RsRepeats> exchangeList) {
        this.exchangeList = exchangeList;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
