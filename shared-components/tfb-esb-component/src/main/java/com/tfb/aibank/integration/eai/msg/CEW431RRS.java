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

import tw.com.ibm.mf.eb.CEW431RSvcRqType;
import tw.com.ibm.mf.eb.CEW431RSvcRsType;

//@formatter:off
/**
* @(#)CEW431RRS.java
* 
* <p>Description:信用卡OTP</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/12, John Chang
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
public class CEW431RRS extends EAIResponse<CEW431RSvcRqType, CEW431RSvcRsType> {
    /**
     * 建構子
     */
    public CEW431RRS() {
        super("CEW431R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}