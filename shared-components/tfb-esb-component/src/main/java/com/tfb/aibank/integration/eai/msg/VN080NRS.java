package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.VN080NSvcRqType;
import tw.com.ibm.mf.eb.VN080NSvcRsType;

// @formatter:off
/**
 * @(#)VN080NRS.java
 * 
 * <p>Description:基金申購預約交易電文(VN080N) 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/ShortTerm_Date" +
                                " | eai:Tx/TxBody/ApplyDate | eai:Tx/TxBody/ReferNetDt")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=4, fieldXPath = "eai:Tx/TxBody/RefNetValue | eai:Tx/TxBody/ShortTerm_Unit" +
                        " | eai:Tx/TxBody/LeftUntNum | eai:Tx/TxBody/UntNum"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=2, fieldXPath = "eai:Tx/TxBody/EstShortTerm_Fee | eai:Tx/TxBody/EstimateMagtFee" +
                        " | eai:Tx/TxBody/TransferFee | eai:Tx/TxBody/AcctBal | eai:Tx/TxBody/Fee | eai:Tx/TxBody/Fee_M" +
                        " | eai:Tx/TxBody/Fee_H | eai:Tx/TxBody/BackFee"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=3, fieldXPath = "eai:Tx/TxBody/ShortTerm_Rate | eai:Tx/TxBody/FeeRate" +
                        " | eai:Tx/TxBody/FeeRate_M | eai:Tx/TxBody/FeeRate_H | eai:Tx/TxBody/ReFeeRate | eai:Tx/TxBody/DeferFeeRate | eai:Tx/TxBody/AdvFeeRate")
        }
)
// @formatter:on
public class VN080NRS extends EAIResponse<VN080NSvcRqType, VN080NSvcRsType> {

    /**
     * 建構子
     */
    public VN080NRS() {
        super("VN080N");
    }
}
