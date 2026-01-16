package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.SPWEBINQSvcRqType;
import tw.com.ibm.mf.eb.SPWEBINQSvcRsType;

/**
 * <p>
 * Title: com.fubon.tw.pib.tr.message.SPWEBINQRS
 * </p>
 * <p>
 * Description: SPWEBINQ 下行電文
 * </p>
 * <p>
 * Copyright: Copyright (c) IBM Corp. 2013. All Rights Reserved.
 * </p>
 * <p>
 * Company: IBM GBS Team
 * </p>
 *
 * @author Edward Tien
 * @version 1.0
 */
//@formatter:off
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                 		" | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur"),
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT" +
                 		" | eai:Tx/TxBody/SPRefId | eai:Tx/TxBody/AcctId16 | eai:Tx/TxBody/Name | eai:Tx/TxBody/SEX | eai:Tx/TxBody/Occur")
        },
                decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class,scale=2, fieldXPath = "eai:Tx/TxBody/TxRepeat/IVAMT2 | eai:Tx/TxBody/TxRepeat/PLAMT | eai:Tx/TxBody/TxRepeat/RefAmtOri"),
                        @DecimalConverter(converter = EAIDecimalConverter.class,scale=6, fieldXPath = "eai:Tx/TxBody/TxRepeat/DEPRAT")
        }
)
//@formatter:on
public class SPWEBINQRS extends EAIResponse<SPWEBINQSvcRqType, SPWEBINQSvcRsType> {
    /**
     * 建構子
     */
    public SPWEBINQRS() {
        super("SPWEBINQ");
    }
}
