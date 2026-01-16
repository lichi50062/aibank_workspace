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

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CE6220RSvcRqType;
import tw.com.ibm.mf.eb.CE6220RSvcRsType;

//@formatter:off
/**
* @(#)CE6220RRS.java
* 
* <p>Description:信用卡卡片總覽下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/CARD_NO | eai:Tx/TxBody/TYPE | eai:Tx/TxBody/CARD_HOLD_ID" +
                        " | eai:Tx/TxBody/CARD_HOLD_NAME | eai:Tx/TxBody/ACCOUNT_ID | eai:Tx/TxBody/CARD_TYPE" +
                        " | eai:Tx/TxBody/AUTHORIZE_BLOCK_CODE | eai:Tx/TxBody/CARD_TYPE2 | eai:Tx/TxBody/CARD_ACTIVE_CODE | eai:Tx/TxBody/CARD_EXPIRED" +
                        " | eai:Tx/TxBody/CARD_STATUS | eai:Tx/TxBody/RETURN_MAIL_CODE | eai:Tx/TxBody/BLACK_LIST | eai:Tx/TxBody/CARD_HOLD_STS" +
                        " | eai:Tx/TxBody/TMSTS_FLAG | eai:Tx/TxBody/BLACK_LIST_REISSUE | eai:Tx/TxBody/EXPIRE_MONTH | eai:Tx/TxBody/M_CARD_NO" +
                        " | eai:Tx/TxBody/TKN_FLAG | eai:Tx/TxBody/HCE_CARD | eai:Tx/TxBody/CONSUME_MESSAGE | eai:Tx/TxBody/APPLY_CONSUME_MESSAGE" +
                        " | eai:Tx/TxBody/INSU_FLAG | eai:Tx/TxBody/CARD_NICKNAME")
        },
        datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/LAST_ACCICENT_DAY | eai:Tx/TxBody/CREATE_DAY | eai:Tx/TxBody/ISSUE_DAY | eai:Tx/TxBody/LAST_AADDR_CHANGED_DATE | eai:Tx/TxBody/CARD_CUT_DAY")
        }
)
//@formatter:on
public class CE6220RRS extends EAIResponse<CE6220RSvcRqType, CE6220RSvcRsType> {

    /**
     * 建構子
     */
    public CE6220RRS() {
        super("CE6220R");
    }

    @Override
    protected boolean isNoData(String errId) {

        return super.isNoData(errId) || "V814".equals(errId);
    }
}
