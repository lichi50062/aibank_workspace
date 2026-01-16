package com.tfb.aibank.chl.creditcard.qu010.model;

import com.ibm.tw.ibmb.base.model.RqData;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NCCQU010060Rq.java
 * 
 * <p>Description:消費分析 060 搜尋結果頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU010060Rq implements RqData {
    
    /** 搜尋關鍵字 */
    private String searchKeyword;

    /**
     * @return the searchKeyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * @param searchKeyword
     *            the searchKeyword to set
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
