package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.FPAPPLY001SvcRqType;
import tw.com.ibm.mf.eb.FPAPPLY001SvcRsType;

//@formatter:off
/**
 * @(#)FPAPPLY001RS.java
 * <pre>
 * Description:FPAPPLY001 下行電文
 *
 * Modify History:
 * v1.0, 2024/01/06, MP, 初版
 * </pre>
 */
//@formatter:on
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" +
                 		" | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/AMOUNT" +
                        		" | eai:Tx/TxBody/TxRepeat/AMOUNT_H | eai:Tx/TxBody/TxRepeat/AMOUNT_L")
        }
)
public class FPAPPLY001RS extends EAIResponse<FPAPPLY001SvcRqType, FPAPPLY001SvcRsType> {
    /**
     * 建構子
     */
    public FPAPPLY001RS() {
        super("FPAPPLY001");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E005".equals(errId);
    }
}
