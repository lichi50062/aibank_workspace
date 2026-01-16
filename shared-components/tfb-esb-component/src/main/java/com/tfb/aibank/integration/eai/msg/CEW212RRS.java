/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW212RSvcRqType;
import tw.com.ibm.mf.eb.CEW212RSvcRsType;

//@formatter:off
/**
* @(#)CEW212RRS.java
* 
* <p>Description:CEW212RRS 下行電文 紅利積點兌換紀錄查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/09, John
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Converter(customConverters = { @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/VNLSND | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
}, datetimeConverters = { @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMM", fieldXPath = "eai:Tx/TxBody/VNLSND"), @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/VNPCHD | eai:Tx/TxBody/TxRepeat/VNNCCD")
})
//@formatter:on
public class CEW212RRS extends EAIResponse<CEW212RSvcRqType, CEW212RSvcRsType> {
    /**
     * 建構子
     */
    public CEW212RRS() {
        super("CEW212R");
    }

}