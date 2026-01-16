package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NJWEE030SvcRqType;
import tw.com.ibm.mf.eb.NJWEE030SvcRsType;

// @formatter:off
/**
 * @(#)NJWEE030RS.java
 * 
 * <p>Description:債券委託交易查詢-委託中</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/11, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                        " | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "0cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/EndDate | eai:Tx/TxBody/TxRepeat/NetInterestDate | eai:Tx/TxBody/TxRepeat/RefValDate | eai:Tx/TxBody/TxRepeat/RefValDate2 | eai:Tx/TxBody/TxRepeat/StartDate | eai:Tx/TxBody/TxRepeat/TxDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/TxRepeat/FinVal | eai:Tx/TxBody/TxRepeat/TrustVal1 | eai:Tx/TxBody/TxRepeat/TrustVal2 | eai:Tx/TxBody/TxRepeat/TrustVal3 | eai:Tx/TxBody/TxRepeat/TxVal | eai:Tx/TxBody/TxRepeat/ProdMinBuyAmt"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/TxFee1 | eai:Tx/TxBody/TxRepeat/TxFee2"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/RefVal | eai:Tx/TxBody/TxRepeat/Interest | eai:Tx/TxBody/TxRepeat/TrustAmt | eai:Tx/TxBody/TxRepeat/TxAmt2 "),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 5, fieldXPath = "eai:Tx/TxBody/TxRepeat/TxFeeRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/BondRate"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 9, fieldXPath = "eai:Tx/TxBody/TxRepeat/FinPrice | eai:Tx/TxBody/TxRepeat/TxPrice")
        }
)
// @formatter:on
public class NJWEE030RS extends EAIResponse<NJWEE030SvcRqType, NJWEE030SvcRsType> {

    /**
     * 建構子.
     */
    public NJWEE030RS() {
        super("NJWEE030");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
