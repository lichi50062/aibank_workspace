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
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB552170SvcRqType;

// @formatter:off
/**
 * @(#)EB552170RQ.java
 * 
 * <p>Description:EB552170網路銀行申請資料建檔及維護 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 11, fieldXPath = "eai:Tx/TxBody/IDNO"),
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 10, fieldXPath = "eai:Tx/TxBody/USER_ID")
        }
)
// @formatter:on
public class EB552170RQ extends EAIRequest<EB552170SvcRqType> {

    private static final long serialVersionUID = 3456149212884918570L;

    /**
     * 建構子
     */
    public EB552170RQ() {
        super("EB552170");
    }
}
