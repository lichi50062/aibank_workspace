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

import tw.com.ibm.mf.eb.CEW320RSvcRqType;
import tw.com.ibm.mf.eb.CEW320RSvcRsType;

//@formatter:off
/**
* @(#)CEW320RRS.java
* 
* <p>Description:CEW320R 預借現金密碼設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
      customConverters = { 
              @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/VNLSND | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
      }, 
      datetimeConverters = { 
              @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/DATE")
      }
)
//@formatter:on
public class CEW320RRS extends EAIResponse<CEW320RSvcRqType, CEW320RSvcRsType> {
    /**
     * 建構子
     */
    public CEW320RRS() {
        super("CEW320R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}