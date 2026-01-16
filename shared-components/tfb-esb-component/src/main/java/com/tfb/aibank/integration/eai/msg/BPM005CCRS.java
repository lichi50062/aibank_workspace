package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.BPM005SvcRqType;
import tw.com.ibm.mf.eb.BPM005SvcRsType;

// @formatter:off
/**
* @(#)BPM005CCRS.java
* 
* <p>Description: 資產總覽電文 BPM005(下行)</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/02/29, Jojo Wei
* <ol>
*  <li>初版</li>
* </ol>
 */

@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/IDNO  | eai:Tx/TxBody/NAME_COD")
        })
// @formatter:on
public class BPM005CCRS extends BPMBaseCCRS<BPM005SvcRqType, BPM005SvcRsType> {

    /**
     * 建構子
     */
    public BPM005CCRS() {
        super("BPM005");
    }
}
