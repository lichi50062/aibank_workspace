/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW307RSvcRqType;
import tw.com.ibm.mf.eb.CEW307RSvcRsType;

//@formatter:off
/**
* @(#)CEW307RRS.java
* 
* <p>Description:CEW307R 信用卡消費訊息通知設定下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/24,John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Converter(customConverters = { @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
})
public class CEW307RRS extends EAIResponse<CEW307RSvcRqType, CEW307RSvcRsType> {

    /**
     * 建構子
     */
    public CEW307RRS() {
        super("CEW307R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "V003".equals(errId);
    }
}