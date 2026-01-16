package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.NF032333SvcRqType;

// @formatter:off
/**
 * @(#)NF032333RQ.java
 * 
 * <p>Description:取得分行代碼 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/17, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAIPadLeftConverter.class, padSize = 16, padChar = "0", fieldXPath = "eai:Tx/TxBody/ACCNO")
        }
)
// @formatter:on
public class NF032333RQ extends EAIRequest<NF032333SvcRqType> {

    private static final long serialVersionUID = -4956780205942951030L;

    /**
     * 建構子
     */
    public NF032333RQ() {
        super("NF032333");
    }
}
