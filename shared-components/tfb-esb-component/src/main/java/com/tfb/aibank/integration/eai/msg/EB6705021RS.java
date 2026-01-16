/**
 *
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB6705021SvcRqType;
import tw.com.ibm.mf.eb.EB6705021SvcRsType;

//@formatter:off
/**
 * @(#)EB6705021RS.java
 *
 * <p>Description:財管會員等級</p>
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
        }
)
//@formatter:on
public class EB6705021RS extends EAIResponse<EB6705021SvcRqType, EB6705021SvcRsType> {

    /**
     * 建構子
     */
    public EB6705021RS() {
        super("EB6705021");
    }

}
