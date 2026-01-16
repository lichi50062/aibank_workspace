package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW346RSvcRqType;
import tw.com.ibm.mf.eb.CEW346RSvcRsType;

// @formatter:off
/**
 * @(#)CEW346RRS.java
 * 
 * <p>Description:保費權益設定-變更 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/02/10, LEIPING	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/CRDNO | eai:Tx/TxBody/INSU_TYPEA | eai:Tx/TxBody/INSU_TYPEB | eai:Tx/TxBody/CHANNEL"),
        }       
)
// @formatter:on
public class CEW346RRS extends EAIResponse<CEW346RSvcRqType, CEW346RSvcRsType> {

    /**
     * 建構子
     */
    public CEW346RRS() {
        super("CEW346R");
    }

}
