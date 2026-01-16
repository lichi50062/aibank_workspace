package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.EB67115SvcRqType;
import tw.com.ibm.mf.eb.EB67115SvcRsType;



//@formatter:off
/**
* @(#)EB67115RS.java
* 
* <p>Description:EB67115 取得客戶是否具備FATCA排外註記下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2025/05/02, xwr
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
	     customConverters = { 
	             @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/unfatcaflag ")
	     }
	)public class EB67115RS extends EAIResponse<EB67115SvcRqType, EB67115SvcRsType> {
    /**
     * <code>serialVersionUID</code> 的註解
     */
    private static final long serialVersionUID = -6486386879523948972L;

    /**
     * 建構子
     */
    public EB67115RS() {
        super("EB67115");
    }
}