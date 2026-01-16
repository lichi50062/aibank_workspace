/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW220RSvcRqType;
import tw.com.ibm.mf.eb.CEW220RSvcRsType;

//@formatter:off
/**
* @(#)CEW220RRS.java
* 
* <p>Description:CEW220R(指定消費分期試算交易)下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/02, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
      customConverters = { 
              @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
      }, 
      decimalConverters = { 
              @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Orfamt | eai:Tx/TxBody/Oreamt | eai:Tx/TxBody/Feeamt"),
              @DecimalConverter(converter = EAIDecimalConverter.class, scale=2, fieldXPath = "eai:Tx/TxBody/Feerate"),
      }
)
//@formatter:on
public class CEW220RRS extends EAIResponse<CEW220RSvcRqType, CEW220RSvcRsType> {
    /**
     * 建構子
     */
    public CEW220RRS() {
        super("CEW220R");
    }

}