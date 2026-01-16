package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB553921SvcRqType;
import tw.com.ibm.mf.eb.EB553921SvcRsType;

// @formatter:off
/**
 * @(#)EB553921RS.java
 * 
 * <p>Description:綜合存款轉存綜合定期性存款（外幣）(EB553921) 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                        " | eai:Tx/TxBody/ACNO_FD" +
                        " | eai:Tx/TxBody/ACT_BAL_1 | eai:Tx/TxBody/ACT_BAL_2 | eai:Tx/TxBody/ACT_BAL_3" +
                        " | eai:Tx/TxBody/AVAIL_BAL_1 | eai:Tx/TxBody/AVAIL_BAL_2 | eai:Tx/TxBody/AVAIL_BAL_3" +
                        " | eai:Tx/TxBody/OD_AMT_1 | eai:Tx/TxBody/OD_AMT_2 | eai:Tx/TxBody/OD_AMT_3" +
                        " | eai:Tx/TxBody/REMARK_1 | eai:Tx/TxBody/REMARK_2 | eai:Tx/TxBody/REMARK_3" +
                        " | eai:Tx/TxBody/O_CUR_1 | eai:Tx/TxBody/O_CUR_2 | eai:Tx/TxBody/O_CUR_3" +
                        " | eai:Tx/TxBody/INT_RATE_1 | eai:Tx/TxBody/INT_RATE_2 | eai:Tx/TxBody/INT_RATE_3" +
                        " | eai:Tx/TxBody/CODE_NAME_1 | eai:Tx/TxBody/CODE_NAME_2 | eai:Tx/TxBody/CODE_NAME_3" +
                        " | eai:Tx/TxBody/HOST_SRL_1 | eai:Tx/TxBody/HOST_SRL_2 | eai:Tx/TxBody/HOST_SRL_3")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, isPostSign=true, fieldXPath = "eai:Tx/TxBody/ACT_BAL | eai:Tx/TxBody/AVAIL_BAL | eai:Tx/TxBody/OD_AMT" +
                        " | eai:Tx/TxBody/O_AMT_1 | eai:Tx/TxBody/I_AMT_1 | eai:Tx/TxBody/O_AMT_2" +
                        " | eai:Tx/TxBody/I_AMT_2 | eai:Tx/TxBody/O_AMT_3 | eai:Tx/TxBody/I_AMT_3"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=8, isPostSign=true, fieldXPath = "eai:Tx/TxBody/INT_RATE_1 | eai:Tx/TxBody/INT_RATE_2 | eai:Tx/TxBody/INT_RATE_3"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=6, fieldXPath = "eai:Tx/TxBody/EX_RATE_1 | eai:Tx/TxBody/EX_RATE_2 | eai:Tx/TxBody/EX_RATE_3")
    }
)
// @formatter:on
public class EB553921RS extends EAIResponse<EB553921SvcRqType, EB553921SvcRsType> {
    /**
     * 建構子
     */
    public EB553921RS() {
        super("EB553921");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E520".equals(errId);
    }
}
