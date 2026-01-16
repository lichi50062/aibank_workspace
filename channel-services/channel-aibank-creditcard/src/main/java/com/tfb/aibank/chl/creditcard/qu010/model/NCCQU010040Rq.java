package com.tfb.aibank.chl.creditcard.qu010.model;

import java.util.List;

import com.ibm.tw.ibmb.base.model.RqData;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCQU010040Rq.java
 * 
 * <p>Description:消費分析 040 月曆消費分析頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU010040Rq implements RqData {
    
    /** 指定分析月 */
    private String selectedYearMonth;

    /**
     * @return the selectedYearMonth
     */
    public String getSelectedYearMonth() {
        return selectedYearMonth;
    }

    /**
     * @param selectedYearMonth
     *            the selectedYearMonth to set
     */
    public void setSelectedYearMonth(String selectedYearMonth) {
        this.selectedYearMonth = selectedYearMonth;
    }
}
