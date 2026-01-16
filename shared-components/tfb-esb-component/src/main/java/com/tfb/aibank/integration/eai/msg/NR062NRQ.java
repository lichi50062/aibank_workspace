package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.NR062NSvcRqType;

// @formatter:off
/**
 * @(#)NR062NRQ.java
 * 
 * <p>Description:NR062N 海外股票ETF停損停利交易</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "000000", fieldXPath = "eai:Tx/TxBody/Stoploss | eai:Tx/TxBody/Satisfied")
        }
)
// @formatter:on
public class NR062NRQ extends EAIRequest<NR062NSvcRqType> {

    private static final long serialVersionUID = 5581159086598328087L;

    /**
     * 建構子
     */
    public NR062NRQ() {
        super("NR062N");

        // 固定填入，提供AS400判斷格式式用
        getHeader().setHFMTID("0001");
    }
}
