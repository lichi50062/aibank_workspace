/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW222RSvcRqType;
import tw.com.ibm.mf.eb.CEW222RSvcRsType;

//@formatter:off
/**
* @(#)CEW222RRS.java
* 
* <p>Description:CEW222R單筆分期查詢下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/12, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
      customConverters = { 
              @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
      },
      datetimeConverters = { 
              @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/Pchday"),
              @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/Nccday"),
              @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/Txiday")
      },
      decimalConverters = { 
              @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Desamt | eai:Tx/TxBody/Txuamt | eai:Tx/TxBody/TxRepeat/txstge | eai:Tx/TxBody/TxRepeat/Txbtic"),
              @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/Txrate")
      }
)
//@formatter:on
public class CEW222RRS extends EAIResponse<CEW222RSvcRqType, CEW222RSvcRsType> {
    /**
     * 建構子
     */
    public CEW222RRS() {
        super("CEW222R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }

}