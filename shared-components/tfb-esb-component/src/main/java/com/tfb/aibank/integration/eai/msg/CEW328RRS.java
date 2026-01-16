package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW328RSvcRqType;
import tw.com.ibm.mf.eb.CEW328RSvcRsType;

// @formatter:off
/**
 * @(#)CEW328RRS.java
 * 
 * <p>Description:保費權益設定-變更 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*"),
        }       
)
// @formatter:on
public class CEW328RRS extends EAIResponse<CEW328RSvcRqType, CEW328RSvcRsType> {

    /**
     * 建構子
     */
    public CEW328RRS() {
        super("CEW328R");
    }

}
