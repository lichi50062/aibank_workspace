/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2025.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.chl.system.ot015.model;

import com.ibm.tw.ibmb.base.model.RqData;

import org.springframework.stereotype.Component;

// @formatter:off
/**
 * @(#)NSTOT015010Rq.java
 * 
 * <p>Description:廣告版位 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/13, Kevin Tung
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
public class NSTOT015010Rq implements RqData {

    /**
     * 交易代號
     */
    private String txn;

    /**
     * 卡片樣式
     */
    private String template;

    public String getTxn() {
        return txn;
    }

    public void setTxn(String txn) {
        this.txn = txn;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
