package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW323RSvcRqType;
import tw.com.ibm.mf.eb.CEW323RSvcRsType;

//@formatter:off
/**
* @(#)CEW323RRS.java
* 
* <p>Description:信用卡行銀推播通知設定 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/06, Leiley    
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
public class CEW323RRS extends EAIResponse<CEW323RSvcRqType, CEW323RSvcRsType> {

    /**
     * 建構子
     */
    public CEW323RRS() {
        super("CEW323R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V804".equals(errId) || "V805".equals(errId);
    }
}
