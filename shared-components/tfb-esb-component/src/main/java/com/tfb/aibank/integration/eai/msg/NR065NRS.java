package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NR065NSvcRqType;
import tw.com.ibm.mf.eb.NR065NSvcRsType;

//@formatter:off
/**
* @(#)NR065NRS.java
* 
* <p>Description: NR065N 海外股票特別股說明書名單</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/03/08, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = { 
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/Signdate")
        }
)
public class NR065NRS extends EAIResponse<NR065NSvcRqType, NR065NSvcRsType> {

   /**
    * 建構子
    */
   public NR065NRS() {
       super("NR065N");
   }
   
   @Override
   protected boolean isNoData(String errId) {
       return "V003".equals(errId);
   }
}
