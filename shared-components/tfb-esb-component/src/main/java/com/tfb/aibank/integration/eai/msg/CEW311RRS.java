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

import tw.com.ibm.mf.eb.CEW311RSvcRqType;
import tw.com.ibm.mf.eb.CEW311RSvcRsType;

//@formatter:off
/**
* @(#)CEW311RRS.java
* 
* <p>Description:CEW311R 預借現金資料檢核</p>
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
public class CEW311RRS extends EAIResponse<CEW311RSvcRqType, CEW311RSvcRsType> {
    /**
     * 建構子
     */
    public CEW311RRS() {
        super("CEW311R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}