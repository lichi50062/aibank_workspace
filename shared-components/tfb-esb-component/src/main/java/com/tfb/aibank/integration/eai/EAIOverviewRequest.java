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

import tw.com.ibm.mf.eai.TxBodyRqType;
import tw.com.ibm.mf.eai.TxRqType;

// @formatter:off
/**
 * @(#)EAIOverviewRequest.java
 * 
 * <p>Description:總覧電文請求類別</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EAIOverviewRequest<T extends TxBodyRqType> extends EAIRequest<T> {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     * 
     * @param txnId
     */
    protected EAIOverviewRequest(String txnId) {
        super(txnId);
    }

    /**
     * 取得xmlbeans物件
     * 
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getBody() {
        TxRqType rqType = (TxRqType) xmlDoc.getTx().changeType(TxRqType.type);
        return (T) rqType.getTxBody().changeType(rqSchemaType);
    }
}
