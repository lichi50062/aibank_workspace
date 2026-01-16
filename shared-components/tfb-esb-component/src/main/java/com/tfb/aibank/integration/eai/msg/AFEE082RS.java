package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.AFEE082SvcRqType;
import tw.com.ibm.mf.eb.AFEE082SvcRsType;

// @formatter:off
/**
 * @(#)AFEE082RS.java
 * 
 * <p>Description:AFEE082 基金交易明細查詢-清單(OBU)</p>
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
                        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/TrscDate"
                        +" | eai:Tx/TxBody/TxRepeat/PayDate1"
                        +" | eai:Tx/TxBody/TxRepeat/PayDate2"
                        +" | eai:Tx/TxBody/TxRepeat/PayDate3"
                        +" | eai:Tx/TxBody/TxRepeat/PayDate4"
                        +" | eai:Tx/TxBody/TxRepeat/PayDate5"
                        +" | eai:Tx/TxBody/TxRepeat/PayDate6"
                        +" | eai:Tx/TxBody/TxRepeat/AllocateDate"
                        )
        } ,decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/Occur"),
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = 
                            "eai:Tx/TxBody/TxRepeat/TrustAmt"
                            +" | eai:Tx/TxBody/TxRepeat/PayAmt"
                            +" | eai:Tx/TxBody/TxRepeat/Fee"
                            +" | eai:Tx/TxBody/TxRepeat/CurAmt"
                            +" | eai:Tx/TxBody/TxRepeat/TrscAmtNT"
                            +" | eai:Tx/TxBody/TxRepeat/PayAmt01"
                            +" | eai:Tx/TxBody/TxRepeat/PayAmt02"
                            +" | eai:Tx/TxBody/TxRepeat/PayAmt03"
                            +" | eai:Tx/TxBody/TxRepeat/PayAmt04"
                            +" | eai:Tx/TxBody/TxRepeat/PayAmt05"
                            +" | eai:Tx/TxBody/TxRepeat/PayAmt06"
                            +" | eai:Tx/TxBody/TxRepeat/Fee1"
                            +" | eai:Tx/TxBody/TxRepeat/Fee2"
                            +" | eai:Tx/TxBody/TxRepeat/Fee3"
                            +" | eai:Tx/TxBody/TxRepeat/Fee4"
                            +" | eai:Tx/TxBody/TxRepeat/Fee5"
                            +" | eai:Tx/TxBody/TxRepeat/Fee6"
                            +" | eai:Tx/TxBody/TxRepeat/Return"
                            +" | eai:Tx/TxBody/TxRepeat/RedeemAmt"
                            +" | eai:Tx/TxBody/TxRepeat/RcvAmt"
                            +" | eai:Tx/TxBody/TxRepeat/RcvNetAmt"
                            +" | eai:Tx/TxBody/TxRepeat/RedeemFee"
                            +" | eai:Tx/TxBody/TxRepeat/MagtFee"
//                            +" | eai:Tx/TxBody/TxRepeat/ReTrustAmt"
//                            +" | eai:Tx/TxBody/TxRepeat/ReFee"
//                            +" | eai:Tx/TxBody/TxRepeat/RePayAmt"
//                            +" | eai:Tx/TxBody/TxRepeat/ReCurAmt"
                            +" | eai:Tx/TxBody/TxRepeat/AdvFee"
                            +" | eai:Tx/TxBody/TxRepeat/InterestReturn"
                            +" | eai:Tx/TxBody/TxRepeat/BefCashReturn"
                            +" | eai:Tx/TxBody/TxRepeat/DeferreFee"
                            +" | eai:Tx/TxBody/TxRepeat/OutTrustAmt"
                            +" | eai:Tx/TxBody/TxRepeat/InTrustAmt"
                            +" | eai:Tx/TxBody/TxRepeat/TransferFee"
                            +" | eai:Tx/TxBody/TxRepeat/ShortFee"
                            ),
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 4, fieldXPath = "eai:Tx/TxBody/TxRepeat/UntNum"
                            +" | eai:Tx/TxBody/TxRepeat/NetValue"
                            +" | eai:Tx/TxBody/TxRepeat/ExRate"
//                            +" | eai:Tx/TxBody/TxRepeat/ReUntNum"
//                            +" | eai:Tx/TxBody/TxRepeat/ReNetValue"
//                            +" | eai:Tx/TxBody/TxRepeat/ReExRate"
                            +" | eai:Tx/TxBody/TxRepeat/OutUntNum"
                            +" | eai:Tx/TxBody/TxRepeat/InUntNum"
                            +" | eai:Tx/TxBody/TxRepeat/OutNetValue"
                            +" | eai:Tx/TxBody/TxRepeat/InNetValue"
                            ),         
                        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 6, fieldXPath = "eai:Tx/TxBody/TxRepeat/TWDExRate"
                        +" | eai:Tx/TxBody/TxRepeat/Amt"
                        )              
        } 
)
// @formatter:on
public class AFEE082RS extends EAIResponse<AFEE082SvcRqType, AFEE082SvcRsType> {

    public AFEE082RS() {
        super("AFEE082");
    }
}
