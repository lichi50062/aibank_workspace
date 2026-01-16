package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE071SvcRqType;
import tw.com.ibm.mf.eb.NFEE071SvcRsType;

// @formatter:off
/**
 * @(#)NFEE071RS.java
 * 
 * <p>Description:NFEE071 基金OBU帳戶總覽</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/NetValueDate | eai:Tx/TxBody/TxRepeat/Strdate" +
                                " | eai:Tx/TxBody/TxRepeat/Date | eai:Tx/TxBody/TxRepeat/InNetValueDate" +
                                " | eai:Tx/TxBody/TxRepeat/TrscDate" +
                                " | eai:Tx/TxBody/TxRepeat/ComboReturnDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur" +
                        " | eai:Tx/TxBody/TxRepeat/RefAmtNT"  + " | eai:Tx/TxBody/TxRepeat/TransferAmt" ),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/CurAmt | eai:Tx/TxBody/TxRepeat/CurBal" +
                        " | eai:Tx/TxBody/TxRepeat/ProfitAndLoss | eai:Tx/TxBody/TxRepeat/Return" +
                        " | eai:Tx/TxBody/TxRepeat/FeeAmt | eai:Tx/TxBody/TxRepeat/TrscAmt" +
                        " | eai:Tx/TxBody/TxRepeat/TrscAmtNT | eai:Tx/TxBody/TxRepeat/RedeemFee" +
                        " | eai:Tx/TxBody/TxRepeat/RefAmt | eai:Tx/TxBody/TxRepeat/TransferFee" +
                        " | eai:Tx/TxBody/TxRepeat/Increase | eai:Tx/TxBody/TxRepeat/AccAllocateRewRate" +
                        " | eai:Tx/TxBody/TxRepeat/CurAmtNT | eai:Tx/TxBody/TxRepeat/CurBalNT" +
                        " | eai:Tx/TxBody/TxRepeat/RefAmtNT | eai:Tx/TxBody/TxRepeat/Stoploss" + 
                        " | eai:Tx/TxBody/TxRepeat/Satisfied | eai:Tx/TxBody/TxRepeat/AccAllocateRew " +
                        " | eai:Tx/TxBody/TxRepeat/AccAllocateRewNT" +
                        " | eai:Tx/TxBody/TxRepeat/ComboReturn | eai:Tx/TxBody/TxRepeat/BenefitReturnRate1 " +
                        " | eai:Tx/TxBody/TxRepeat/BefDivisCash | eai:Tx/TxBody/TxRepeat/BefDivisCash_TWD " +
                        " | eai:Tx/TxBody/TxRepeat/BefCashReturn " +
                        " | eai:Tx/TxBody/TxRepeat/BenefitReturnRate2 | eai:Tx/TxBody/TxRepeat/BenefitReturnRate3 "),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, fieldXPath = "eai:Tx/TxBody/TxRepeat/NewFee | eai:Tx/TxBody/TxRepeat/DeferreFee"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/UntNum" +
                        " | eai:Tx/TxBody/TxRepeat/RefNetValue | eai:Tx/TxBody/TxRepeat/ReferenceRate" +
                        " | eai:Tx/TxBody/TxRepeat/InRefNetValue | eai:Tx/TxBody/TxRepeat/CurUntNum" +
                        " | eai:Tx/TxBody/TxRepeat/ReferenceExchangeRate | eai:Tx/TxBody/TxRepeat/NetValue"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/ExchangeRateNT" )
        }
)
// @formatter:on
public class NFEE071RS extends EAIResponse<NFEE071SvcRqType, NFEE071SvcRsType> {

    public NFEE071RS() {
        super("NFEE071");
    }
}
