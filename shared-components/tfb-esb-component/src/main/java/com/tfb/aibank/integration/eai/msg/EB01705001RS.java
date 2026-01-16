/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB01705001SvcRqType;
import tw.com.ibm.mf.eb.EB01705001SvcRsType;

//@formatter:off
/**
* @(#)EB01705001RS.java
*
* <p>Description 取得貸款協商紀錄</p>
*
* <p>Modify History:</p>
* v1.0, 2023/07/10,
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(customConverters = { @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" + " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
})
public class EB01705001RS extends EAIResponse<EB01705001SvcRqType, EB01705001SvcRsType> {
    /**
     * 建構子
     */
    public EB01705001RS() {
        super("EB0175001");
    }
}
