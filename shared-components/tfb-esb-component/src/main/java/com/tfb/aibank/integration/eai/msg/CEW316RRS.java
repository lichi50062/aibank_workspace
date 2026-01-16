package com.tfb.aibank.integration.eai.msg;


import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.CEW316RSvcRqType;
import tw.com.ibm.mf.eb.CEW316RSvcRsType;


//@formatter:off
/**
 * @(#)CEW316RRS.java
 *
 * <p>Description:CEW316R 下行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/09/20 John Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" +
                 		" | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        }
)
public class CEW316RRS extends EAIResponse<CEW316RSvcRqType, CEW316RSvcRsType> {
    /**
     * 建構子
     */
    public CEW316RRS() {
        super("CEW316R");
    }
}
