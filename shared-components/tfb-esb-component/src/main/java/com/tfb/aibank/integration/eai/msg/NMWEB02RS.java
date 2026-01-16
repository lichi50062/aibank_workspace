package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB02SvcRqType;
import tw.com.ibm.mf.eb.NMWEB02SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB02RS.java
 *
 * <p>Description:金錢信託關係人查詢 NMWEB02</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/07/10, PCY
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR")
        }
)
public class NMWEB02RS extends EAIResponse<NMWEB02SvcRqType, NMWEB02SvcRsType> {

    public NMWEB02RS() {
        super("NMWEB02");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
