package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW013RSvcRqType;
import tw.com.ibm.mf.eb.CEW013RSvcRsType;

// @formatter:off
/**
 * @(#)CEW013RRS.java
 * 
 * <p>Description:申辦信用卡客戶資料查詢下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }
    )
// @formatter:on
public class CEW013RRS extends EAIResponse<CEW013RSvcRqType, CEW013RSvcRsType> {

    /**
     * 建構子
     */
    public CEW013RRS() {
        super("CEW013R");
    }
}
