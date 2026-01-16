package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.VDW008RSvcRqType;
import tw.com.ibm.mf.eb.VDW008RSvcRsType;

// @formatter:off
/**
 * @(#)VDW008RRS.java
 * 
 * <p>Description:富御金歷程查詢 VDW008R 下行電文</p>
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
        }
)
// @formatter:on
public class VDW008RRS extends EAIResponse<VDW008RSvcRqType, VDW008RSvcRsType> {
    /**
     * 建構子
     */
    public VDW008RRS() {
        super("VDW008R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V006".equals(errId) || "V005".equals(errId);
    }

}
