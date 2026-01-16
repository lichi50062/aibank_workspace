package com.tfb.aibank.chl.creditcard.qu009.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU009011Rq.java
 * 
 * <p>Description:簽帳金融卡消費明細 011 取資料明細</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/18, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU009011Rq implements RqData {
    /** -1(最新) 1(前一期) 2(前二期) 3(前三期) 4(前四期) 5(前五期) 6(前六期) */
    private String tabId;

    /** 帳號 */
    private String accNo;

    /**
     * @return the tabId
     */
    public String getTabId() {
        return tabId;
    }

    /**
     * @param tabId
     *            the tabId to set
     */
    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    /**
     * @return the accNo
     */
    public String getAccNo() {
        return accNo;
    }

    /**
     * @param accNo
     *            the accNo to set
     */
    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

}
