package com.tfb.aibank.integration.eai.msg;

import java.io.Serializable;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020009SvcRqType;
import tw.com.ibm.mf.eb.EB12020009SvcRsType;

//@formatter:off
/**
 * @(#)EB12020009RS.java
 * 
 * <p>Description: 預借現金交易下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/02, John
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*"), 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }
)
//@formatter:on
public class EB12020009RS extends EAIResponse<EB12020009SvcRqType, EB12020009SvcRsType> implements Serializable {

    private static final long serialVersionUID = -5988871733062669549L;

    /**
     * 建構子
     */
    public EB12020009RS() {
        super("EB12020009");
    }
}
