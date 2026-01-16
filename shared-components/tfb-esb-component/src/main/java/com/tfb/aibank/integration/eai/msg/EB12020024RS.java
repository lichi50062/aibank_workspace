package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020024SvcRqType;
import tw.com.ibm.mf.eb.EB12020024SvcRsType;


//@formatter:off
/**
* @(#)EB12020024RS.java
* 
* <p>Description:EB12020024 Email資料重複判斷</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/22, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                 // 針對除了IDNO_STS以外的欄位trim掉空白
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/MSG_COD | eai:Tx/TxBody/MSG_TXT | eai:Tx/TxBody/MULTI_NAME | eai:Tx/TxBody/NAME_0001 | eai:Tx/TxBody/DEP_STS | eai:Tx/TxBody/ID_DUP | eai:Tx/TxBody/ACT_ID | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/CELL | eai:Tx/TxBody/EMAIL | eai:Tx/TxBody/APLY_STS | eai:Tx/TxBody/APLY_CNT"), @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/MSG_COD | eai:Tx/TxBody/MSG_TXT | eai:Tx/TxBody/MULTI_NAME | eai:Tx/TxBody/NAME_0001 | eai:Tx/TxBody/DEP_STS | eai:Tx/TxBody/ID_DUP | eai:Tx/TxBody/ACT_ID | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/BDAY | eai:Tx/TxBody/CELL | eai:Tx/TxBody/EMAIL | eai:Tx/TxBody/APLY_STS | eai:Tx/TxBody/APLY_CNT")
        }
)
//@formatter:on
public class EB12020024RS extends EAIResponse<EB12020024SvcRqType, EB12020024SvcRsType> {
    /**
     * 建構子
     */
    public EB12020024RS() {
        super("EB12020024");
    }

}
