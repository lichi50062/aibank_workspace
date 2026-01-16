package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.CEW313RSvcRqType;
import tw.com.ibm.mf.eb.CEW313RSvcRsType;


//@formatter:off
/**
 * @(#)CEW313RRS.java
 *
 * <p>Description:本期帳單剩餘應繳金額</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/07/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/AcctIdub | eai:Tx/TxBody/AcctIdDPayd | eai:Tx/TxBody/AcctIdspub1 | eai:Tx/TxBody/AcctIdspub2")
        })
public class CEW313RRS extends EAIResponse<CEW313RSvcRqType, CEW313RSvcRsType> {
    /**
     * 建構子
     */
    public CEW313RRS() {
        super("CEW313R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V803".equals(errId);
    }
}
