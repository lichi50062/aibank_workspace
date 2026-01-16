package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW317RSvcRqType;
import tw.com.ibm.mf.eb.CEW317RSvcRsType;

//@formatter:off
/**
 * @(#)CEW317RRS.java
 *
 * <p>Description:CEW317R 帳單分期申請 下行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/09/20 Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                 @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
      //TODO 待測資確認
        datetimeConverters = { 
             @DateAndTimeConverter(converter = EAIDateConverter.class,  inputPattern = "yyyyMM", fieldXPath = "eai:Tx/TxBody/BILL_YYYYMM"),
             @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/BILL_CYCLE_DAY | eai:Tx/TxBody/PAYMENT_DEADLINE")
        }
)
public class CEW317RRS extends EAIResponse<CEW317RSvcRqType, CEW317RSvcRsType> {
    /**
     * 建構子
     */
    public CEW317RRS() {
        super("CEW317R");
    }
}
