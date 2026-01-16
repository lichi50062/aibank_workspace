package com.tfb.aibank.chl.creditcard.qu008.model;

import org.springframework.stereotype.Component;

import com.ibm.tw.ibmb.base.model.RqData;

// @formatter:off
/**
 * @(#)NCCQU008030Rq.java
 * 
 * <p>Description:信用卡分期管理 030 單筆總覽頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NCCQU008030Rq implements RqData {
    /**
     * 1 只查未請款(已出帳) 2 只查已請款(未出帳) 空 全查
     */
    private String queryFlag;

    /**
     * @return the queryFlag
     */
    public String getQueryFlag() {
        return queryFlag;
    }

    /**
     * @param queryFlag
     *            the queryFlag to set
     */
    public void setQueryFlag(String queryFlag) {
        this.queryFlag = queryFlag;
    }

}
