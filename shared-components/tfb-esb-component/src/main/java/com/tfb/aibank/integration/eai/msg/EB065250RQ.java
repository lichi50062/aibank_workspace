package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB065250SvcRqType;

// @formatter:off
/**
 * @(#)EB065250RQ.java
 *
 * <p>Description:EB065250RQ 查詢大額換匯申報書電文 上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/01/28,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 10, fieldXPath = "eai:Tx/TxBody/Cust_ID"),
        }
)
// @formatter:on

public class EB065250RQ extends EAIRequest<EB065250SvcRqType> {

    private static final long serialVersionUID = -5864902095197756509L;

    /**
     * 建構子
     */
    public EB065250RQ() {
        super("EB065250");
    }
}
