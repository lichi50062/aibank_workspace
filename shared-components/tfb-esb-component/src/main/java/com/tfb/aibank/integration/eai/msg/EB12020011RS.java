package com.tfb.aibank.integration.eai.msg;

import java.io.Serializable;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020011SvcRqType;
import tw.com.ibm.mf.eb.EB12020011SvcRsType;

//@formatter:off
/**
* @(#)EB12020018RS.java
* 
* <p>Description: 數位存款開戶下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/15, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                 // 針對除了IDNO_STS以外的欄位trim掉空白
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/MSG_COD | eai:Tx/TxBody/MSG_TXT | eai:Tx/TxBody/MULTI_NAME | eai:Tx/TxBody/NAME_0001 | eai:Tx/TxBody/DEP_STS | eai:Tx/TxBody/ID_DUP | eai:Tx/TxBody/ACT_ID | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/CELL | eai:Tx/TxBody/EMAIL | eai:Tx/TxBody/APLY_STS | eai:Tx/TxBody/APLY_CNT")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/BDAY | eai:Tx/TxBody/TxRepeat/SystDt"), @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx/TxBody/TxRepeat/SystTime")
        }
)
//@formatter:on
public class EB12020011RS extends EAIResponse<EB12020011SvcRqType, EB12020011SvcRsType> implements Serializable {

    private static final long serialVersionUID = -5988871733062669549L;

    /**
     * 建構子
     */
    public EB12020011RS() {
        super("EB12020011");
    }
}
