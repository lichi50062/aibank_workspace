/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NMP8YBSvcRqType;
import tw.com.ibm.mf.eb.NMP8YBSvcRsType;

// @formatter:off
/**
 * @(#)NMP8YBRS.java
 * 
 * <p>Description:NMP8YB 奈米投電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* ")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=0, fieldXPath = "eai:Tx/TxBody/TxRepeat/CurBalNT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=2, fieldXPath = 
                        "eai:Tx/TxBody/TxRepeat/TargetAmt|"
                        + "eai:Tx/TxBody/TxRepeat/IncreaseAmtBas|"
                        + "eai:Tx/TxBody/TxRepeat/IncreaseCharge|"
                        + "eai:Tx/TxBody/TxRepeat/MarketValBas|"
                        + "eai:Tx/TxBody/TxRepeat/ProfitAndLossBas|"
                        + "eai:Tx/TxBody/TxRepeat/RetrunBas|"
                        + "eai:Tx/TxBody/TxRepeat/TargetRate|"
                        + "eai:Tx/TxBody/TxRepeat/RetrunTwd|"
                        + "eai:Tx/TxBody/TxRepeat/Dividendamount|"
                        + "eai:Tx/TxBody/TxRepeat/Interestrateofreturn"
                        )
        }
)
// @formatter:on
public class NMP8YBRS extends EAIResponse<NMP8YBSvcRqType, NMP8YBSvcRsType> {

    /**
     * 建構子
     */
    public NMP8YBRS() {
        super("NMP8YB");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || StringUtils.equals(errId, "B001") || StringUtils.equals(errId, "B006");
    }
}
