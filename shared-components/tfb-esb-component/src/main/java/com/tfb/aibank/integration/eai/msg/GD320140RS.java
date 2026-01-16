/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.GD320140SvcRqType;
import tw.com.ibm.mf.eb.GD320140SvcRsType;

// @formatter:off
/**
 * @(#)GD320140RS.java
 * 
 * <p>Description:GD320140 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/ACNO "
                         + "| eai:Tx/TxBody/TxRepeat/CUR | eai:Tx/TxBody/TxRepeat/BAL | eai:Tx/TxBody/TxRepeat/BRD_PRICE "
                         + "| eai:Tx/TxBody/TxRepeat/BRD_DATE | eai:Tx/TxBody/TxRepeat/BRD_TIME | eai:Tx/TxBody/TxRepeat/P_VALUE "
                         + "| eai:Tx/TxBody/TxRepeat/AVG_COST | eai:Tx/TxBody/TxRepeat/YIELD_S | eai:Tx/TxBody/TxRepeat/YIELD_AMT "
                         + "| eai:Tx/TxBody/TxRepeat/INV_AMT | eai:Tx/TxBody/TxRepeat/YIELD | eai:Tx/TxBody/TxRepeat/M_AMT "
                         + "| eai:Tx/TxBody/TxRepeat/M_STS_COD | eai:Tx/TxBody/TxRepeat/M_STS | eai:Tx/TxBody/TxRepeat/M_FEE_AMT "
                         + "| eai:Tx/TxBody/TxRepeat/DETA_AMT | eai:Tx/TxBody/TxRepeat/OPN_DATE | eai:Tx/TxBody/TxRepeat/MTN_DATE "
                         + "| eai:Tx/TxBody/TxRepeat/UNIT | eai:Tx/TxBody/TxRepeat/SDATE | eai:Tx/TxBody/TxRepeat/EDATE "
                         + "| eai:Tx/TxBody/TxRepeat/PROD_CATG | eai:Tx/TxBody/TxRepeat/M_UPSET_PRICE | eai:Tx/TxBody/TxRepeat/M_INCREASE "
                         + "| eai:Tx/TxBody/TxRepeat/PAUSE1 | eai:Tx/TxBody/TxRepeat/PAUSE2 | eai:Tx/TxBody/TxRepeat/M_OPEN "
                         + "| eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale=2, fieldXPath = "eai:Tx/TxBody/TxRepeat/BAL " +
                                "| eai:Tx/TxBody/TxRepeat/BRD_PRICE " +
                                "| eai:Tx/TxBody/TxRepeat/P_VALUE " +
                                "| eai:Tx/TxBody/TxRepeat/AVG_COST " +
                                "| eai:Tx/TxBody/TxRepeat/YIELD_AMT " +
                                "| eai:Tx/TxBody/TxRepeat/INV_AMT " +
                                "| eai:Tx/TxBody/TxRepeat/YIELD" + 
                                "| eai:Tx/TxBody/TxRepeat/M_AMT " +
                                "| eai:Tx/TxBody/TxRepeat/M_FEE_AMT " +
                                "| eai:Tx/TxBody/TxRepeat/DETA_AMT " +
                                "| eai:Tx/TxBody/TxRepeat/M_UPSET_PRICE " +
                                "| eai:Tx/TxBody/TxRepeat/M_INCREASE "
                                )
        },
        datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/OPN_DATE | " +
                                "eai:Tx/TxBody/TxRepeat/MTN_DATE | " +
                                "eai:Tx/TxBody/TxRepeat/BRD_DATE | " +
                                "eai:Tx/TxBody/TxRepeat/SDATE |" +
                                "eai:Tx/TxBody/TxRepeat/EDATE"),
                        @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/TxRepeat/BRD_TIME")
        }
)
// @formatter:on
public class GD320140RS extends EAIResponse<GD320140SvcRqType, GD320140SvcRsType> {

    /**
     * 建構子
     */
    public GD320140RS() {
        super("GD320140");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equalsAny(errId, "EBB2", "E005", "SP01", "SP02", "G003");
    }

}
