package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE073SvcRqType;
import tw.com.ibm.mf.eb.NFEE073SvcRsType;

// @formatter:off
/**
 * @(#)NFEE073RS.java
 *
 * <p>Description:金錢基金帳戶總覽 NFEE073RS</p>
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
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/NetValueDate | eai:Tx/TxBody/TxRepeat/Strdate" +
                        " | eai:Tx/TxBody/TxRepeat/Date | eai:Tx/TxBody/TxRepeat/InNetValueDate" +
                        " | eai:Tx/TxBody/TxRepeat/TrscDate")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur" +
                        " | eai:Tx/TxBody/TxRepeat/TransferAmt | eai:Tx/TxBody/TxRepeat/TransferAmt_H" +
                        " | eai:Tx/TxBody/TxRepeat/TransferAmt_M | eai:Tx/TxBody/TxRepeat/TransferAmt_L" +
                        " | eai:Tx/TxBody/TxRepeat/TransferCount"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx/TxBody/TxRepeat/CurAmt | eai:Tx/TxBody/TxRepeat/CurBal" +
                        " | eai:Tx/TxBody/TxRepeat/ProfitAndLoss | eai:Tx/TxBody/TxRepeat/Return" +
                        " | eai:Tx/TxBody/TxRepeat/FeeAmt | eai:Tx/TxBody/TxRepeat/TrscAmt" +
                        " | eai:Tx/TxBody/TxRepeat/TrscAmtNT | eai:Tx/TxBody/TxRepeat/RedeemFee" +
                        " | eai:Tx/TxBody/TxRepeat/RefAmt | eai:Tx/TxBody/TxRepeat/TransferFee" +
                        " | eai:Tx/TxBody/TxRepeat/Increase | eai:Tx/TxBody/TxRepeat/AccAllocateRewRate" +
                        " | eai:Tx/TxBody/TxRepeat/CurBalNT | eai:Tx/TxBody/TxRepeat/CurAmtNT" +
                        " | eai:Tx/TxBody/TxRepeat/RefAmtNT | eai:Tx/TxBody/TxRepeat/Stoploss" +
                        " | eai:Tx/TxBody/TxRepeat/Satisfied | eai:Tx/TxBody/TxRepeat/ActiveDisAmt" +
                        " | eai:Tx/TxBody/TxRepeat/AccAllocateRew | eai:Tx/TxBody/TxRepeat/AccAllocateRewNT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, fieldXPath = "eai:Tx/TxBody/TxRepeat/NewFee"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/UntNum" +
                        " | eai:Tx/TxBody/TxRepeat/RefNetValue | eai:Tx/TxBody/TxRepeat/ReferenceRate" +
                        " | eai:Tx/TxBody/TxRepeat/InRefNetValue | eai:Tx/TxBody/TxRepeat/CurUntNum" +
                        " | eai:Tx/TxBody/TxRepeat/ReferenceExchangeRate | eai:Tx/TxBody/TxRepeat/NetValue"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/ExchangeRateNT")
        }
)
public class NFEE073RS extends EAIResponse<NFEE073SvcRqType, NFEE073SvcRsType> {

    public NFEE073RS() {
        super("NFEE073");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId);
    }
}
