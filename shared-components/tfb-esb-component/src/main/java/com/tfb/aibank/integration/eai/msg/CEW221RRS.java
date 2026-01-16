/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW221RSvcRqType;
import tw.com.ibm.mf.eb.CEW221RSvcRsType;

//@formatter:off
/**
* @(#)CEW220RRS.java
* 
* <p>Description:CEW220R(指定消費分期網銀設定交易)下行電文</p>
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
      }
)
//@formatter:on
public class CEW221RRS extends EAIResponse<CEW221RSvcRqType, CEW221RSvcRsType> {
    /**
     * 建構子
     */
    public CEW221RRS() {
        super("CEW221R");
    }

}