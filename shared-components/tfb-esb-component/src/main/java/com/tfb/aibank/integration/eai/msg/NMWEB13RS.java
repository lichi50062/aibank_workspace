package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMWEB13SvcRqType;
import tw.com.ibm.mf.eb.NMWEB13SvcRsType;

// @formatter:off
/**
 * @(#)NMWEB13RS.java
 * 
 * <p>Description:申請紀錄有權指示人查詢電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/07/31, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/OCCUR")
        }
)
// @formatter:on
public class NMWEB13RS extends EAIResponse<NMWEB13SvcRqType, NMWEB13SvcRsType> {

    public NMWEB13RS() {
        super("NMWEB13");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
