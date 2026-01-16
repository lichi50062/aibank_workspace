package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.NFEE084SvcRqType;

// @formatter:off
/**
 * @(#)NFEE084RQ.java
 * 
 * <p>Description:NFEE084 基金觀察到價設定</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(decimalConverters = { @DecimalConverter(converter = EAIDecimalConverter.class, scale = -4, fieldXPath = "eai:Tx/TxBody/EnterAmt") })
public class NFEE084RQ extends EAIRequest<NFEE084SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NFEE084RQ() {
        super("NFEE084");
    }
}
