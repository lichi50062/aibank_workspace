package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.VDW002RSvcRqType;
import tw.com.ibm.mf.eb.VDW002RSvcRsType;

// @formatter:off
/**
 * @(#)VDW002RRS.java
 * 
 * <p>Description:富御金歷程查詢 VDW002R 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/LsnYYYMM | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/BONUSLBAL | eai:Tx/TxBody/OCCUR")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/SRCAMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/NcbTwd")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/NcbPchd | eai:Tx/TxBody/TxRepeat/NcbNccd | eai:Tx/TxBody/TxRepeat/Ncbfcyd"),
        }
)

// @formatter:on
public class VDW002RRS extends EAIResponse<VDW002RSvcRqType, VDW002RSvcRsType> {
    /**
     * 建構子
     */
    public VDW002RRS() {
        super("VDW002R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V006".equals(errId) || "V005".equals(errId);
    }

}
