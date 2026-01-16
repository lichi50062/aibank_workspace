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

import tw.com.ibm.mf.eb.CEW223RSvcRqType;
import tw.com.ibm.mf.eb.CEW223RSvcRsType;

//@formatter:off
/**
* @(#)CEW223RRS.java
* 
* <p>Description:信用卡活動登錄/查詢</p>
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
public class CEW223RRS extends EAIResponse<CEW223RSvcRqType, CEW223RSvcRsType> {
    /**
     * 建構子
     */
    public CEW223RRS() {
        super("CEW223R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}