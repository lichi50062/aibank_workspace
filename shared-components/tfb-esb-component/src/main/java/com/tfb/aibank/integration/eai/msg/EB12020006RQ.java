package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.EB12020006SvcRqType;


//@formatter:off
/**
* @(#)EB12020006RQ.java
* 
* <p>Description:EB12020006 變更客戶基本資料</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(
                        converter = EAIPadLeftConverter.class, padSize = 2, padChar = "0", fieldXPath = "eai:Tx/TxBody/EDU_COD"),
                @CustomConverter(
                        converter = EAIPadLeftConverter.class, padSize = 4, padChar = "0", fieldXPath = "eai:Tx/TxBody/CAR_COD")
        }
)
//@formatter:on
public class EB12020006RQ extends EAIRequest<EB12020006SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
	public EB12020006RQ() {
		super("EB12020006");
	}

}
