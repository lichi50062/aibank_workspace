/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai;

import org.apache.xmlbeans.SchemaType;

import tw.com.ibm.mf.eai.TxBodyRsType;

// @formatter:off
/**
 * @(#)EAIOverviewTxBodyRsType.java
 * 
 * <p>Description:總覧電文回覆資料物件</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAIOverviewTxBodyRsType {

    /** 電文代碼 */
    private String txnId;

    /** xmlbeans資料 */
    private TxBodyRsType txBodyRs;

    /** xmlbeans型別 */
    private SchemaType rsSchemaType;

    /**
     * 建構子
     * 
     * @param txnId
     * @param rs
     * @param type
     */
    EAIOverviewTxBodyRsType(String txnId, TxBodyRsType rs, SchemaType type) {
        this.txnId = txnId;
        txBodyRs = rs;
        rsSchemaType = type;
    }

    /**
     * 取得Body資料
     * 
     * @param rqClz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getTxBodyRs(Class<T> rqClz) {
        return (T) txBodyRs.changeType(rsSchemaType);
    }

    /**
     * 取得電文代碼
     * 
     * @return
     */
    public String getTxnId() {
        return txnId;
    }

}
