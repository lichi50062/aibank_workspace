package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE081SvcRqType;
import tw.com.ibm.mf.eb.NFEE081SvcRsType;

// @formatter:off
/**
 * @(#)NFEE081RS.java
 * 
 * <p>Description:NFEE081 基金交易明細查詢-清單(DBU)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/29, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        }, datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/TrscDate")
        } ,decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/Fee"
                        + " | eai:Tx/TxBody/TxRepeat/TrustAmt"
                        + " | eai:Tx/TxBody/TxRepeat/TrustAmount"
                        + " | eai:Tx/TxBody/TxRepeat/RcvAmt"
                         ),             
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/UntNum " ),             
        } 
)
// @formatter:on
public class NFEE081RS extends EAIResponse<NFEE081SvcRqType, NFEE081SvcRsType> {

    public NFEE081RS() {
        super("NFEE081");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals("V003", errId);
    }
}
