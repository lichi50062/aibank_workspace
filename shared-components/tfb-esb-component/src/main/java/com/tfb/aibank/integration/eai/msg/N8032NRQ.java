package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.N8032NSvcRqType;

//@formatter:off
/**
 * @(#)N8032NRQ.java
 * 
 * <p>Description: N8032N 海外股票ETF定期定額契約變更</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -2, fieldXPath = "eai:Tx/TxBody/EntrustAmt | eai:Tx/TxBody/EntrustAmtAfter")
        }
)
//@formatter:on
public class N8032NRQ extends EAIRequest<N8032NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public N8032NRQ() {
        super("N8032N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
