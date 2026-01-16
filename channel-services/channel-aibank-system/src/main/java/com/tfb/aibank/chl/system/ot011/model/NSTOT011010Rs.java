package com.tfb.aibank.chl.system.ot011.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RsData;

// @formatter:off
/**
 * @(#)NSTOT011010Rs.java
 * 
 * <p>Description:日終掛牌 010 買匯匯率popup區塊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT011010Rs implements RsData {
    private List<NSTOT011Currency> currencyList;
    private String bizDate;

    /**
     * @return the currencyList
     */
    public List<NSTOT011Currency> getCurrencyList() {
        return currencyList;
    }

    /**
     * @param currencyList the currencyList to set
     */
    public void setCurrencyList(List<NSTOT011Currency> currencyList) {
        this.currencyList = currencyList;
    }

    /**
     * @return the bizDate
     */
    public String getBizDate() {
        return bizDate;
    }

    /**
     * @param bizDate
     *            the bizDate to set
     */
    public void setBizDate(String bizDate) {
        this.bizDate = bizDate;
    }
}
