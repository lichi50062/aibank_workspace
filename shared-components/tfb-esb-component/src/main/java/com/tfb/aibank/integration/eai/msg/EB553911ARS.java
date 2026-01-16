package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB553911ASvcRqType;
import tw.com.ibm.mf.eb.EB553911ASvcRsType;
//@formatter:off
/**
* @(#)EB553911ARS.java
* 
* <p>Description:開零存整付帳號</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/11/01, Alex PY Li
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
public class EB553911ARS extends EAIResponse<EB553911ASvcRqType, EB553911ASvcRsType> {
    /**
     * 建構子
     */
    public EB553911ARS() {
        super("EB553911A");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "E520".equals(errId);
    }

}
