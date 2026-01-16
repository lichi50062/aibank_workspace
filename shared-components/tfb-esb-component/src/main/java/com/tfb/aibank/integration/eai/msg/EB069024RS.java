/**
 *
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB069024SvcRqType;
import tw.com.ibm.mf.eb.EB069024SvcRsType;

//@formatter:off
/**
 * @(#)EB069024RS.java
 *
 * <p>Description:每月銀行作業優惠</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/08/07, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        decimalConverters = {
                @DecimalConverter(
                        converter = EAIDecimalConverter.class, pattern = "0000", scale = 0, isPostSign = true, fieldXPath = "eai:Tx/TxBody/AccumNo | eai:Tx/TxBody/AccrCount | eai:Tx/TxBody/ResCount | eai:Tx/TxBody/AddSubCount ")

        }
)
//@formatter:on
public class EB069024RS extends EAIResponse<EB069024SvcRqType, EB069024SvcRsType> {

    /**
     * 建構子
     */
    public EB069024RS() {
        super("EB069024");
    }

}
