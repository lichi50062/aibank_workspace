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

import tw.com.ibm.mf.eb.NFEE085SvcRqType;
import tw.com.ibm.mf.eb.NFEE085SvcRsType;

// @formatter:off
/**
 * @(#)NFEE085RS.java
 *
 * <p>Description:NFEE085 基金觀察到價查詢</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/01/30, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT | eai:Tx/TxBody/Occur")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern="cccMMdd", fieldXPath="eai:Tx/TxBody/TxRepeat/NetValueDate | eai:Tx/TxBody/TxRepeat/EnterSdate | eai:Tx/TxBody/TxRepeat/EnterEdate"),
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern="0cccMMdd", fieldXPath="eai:Tx/TxBody/TxRepeat/DateJoined")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale=4, fieldXPath = "eai:Tx/TxBody/TxRepeat/NetValue | eai:Tx/TxBody/TxRepeat/NetValueJoined | eai:Tx/TxBody/TxRepeat/EnterAmt")
        }
)
// @formatter:on
public class NFEE085RS extends EAIResponse<NFEE085SvcRqType, NFEE085SvcRsType> {

    /**
     * 建構子
     */
    public NFEE085RS() {
        super("NFEE085");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equalsAny(errId, "0000", "V003");
    }
}