package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB032675SvcRqType;
import tw.com.ibm.mf.eb.EB032675SvcRsType;


// @formatter:off
/**
 * @(#)EB032675RS.java
 *
 * <p>Description:EB032675 下行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/01/28,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*" +
                        " | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/ProductType_1 | eai:Tx/TxBody/TraderID_1" +
                        " | eai:Tx/TxBody/ProductType_2 | eai:Tx/TxBody/TraderID_2 | eai:Tx/TxBody/ProductType_3 | eai:Tx/TxBody/TraderID_3" +
                        " | eai:Tx/TxBody/ProductType_4 | eai:Tx/TxBody/TraderID_4 | eai:Tx/TxBody/ProductType_5 | eai:Tx/TxBody/TraderID_5" +
                        " | eai:Tx/TxBody/ProductType_6 | eai:Tx/TxBody/TraderID_6 | eai:Tx/TxBody/ProductType_7 | eai:Tx/TxBody/TraderID_7" +
                        " | eai:Tx/TxBody/ProductType_8 | eai:Tx/TxBody/TraderID_8 | eai:Tx/TxBody/ProductType_9 | eai:Tx/TxBody/TraderID_9" +
                        " | eai:Tx/TxBody/ProductType_10 | eai:Tx/TxBody/TraderID_10 | eai:Tx/TxBody/ProductType_11 | eai:Tx/TxBody/TraderID_11" +
                        " | eai:Tx/TxBody/ProductType_12 | eai:Tx/TxBody/TraderID_12 | eai:Tx/TxBody/ProductType_13 | eai:Tx/TxBody/TraderID_13" +
                        " | eai:Tx/TxBody/ProductType_14 | eai:Tx/TxBody/TraderID_14 | eai:Tx/TxBody/ProductType_15 | eai:Tx/TxBody/TraderID_15" +
                        " | eai:Tx/TxBody/ProductType_16 | eai:Tx/TxBody/TraderID_16 | eai:Tx/TxBody/ProductType_17 | eai:Tx/TxBody/TraderID_17" +
                        " | eai:Tx/TxBody/ProductType_18 | eai:Tx/TxBody/TraderID_18 | eai:Tx/TxBody/ProductType_19 | eai:Tx/TxBody/TraderID_19" +
                        " | eai:Tx/TxBody/ProductType_20 | eai:Tx/TxBody/TraderID_20")
        }
)
public class EB032675RS extends EAIResponse<EB032675SvcRqType, EB032675SvcRsType> {
    /**
     * 建構子
     */
    public EB032675RS() {
        super("EB032675");
    }
    
    @Override
    protected boolean isNoData(String errId) {
        return "0188".equals(errId);
    }
}
