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

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW309RSvcRqType;
import tw.com.ibm.mf.eb.CEW309RSvcRsType;

//@formatter:off
/**
* @(#)CEW309RRS.java
* 
* <p>Description:信用卡費自動扣繳設定下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
})
//@formatter:on
public class CEW309RRS extends EAIResponse<CEW309RSvcRqType, CEW309RSvcRsType> {
    /**
     * 建構子
     */
    public CEW309RRS() {
        super("CEW309R");
    }

    @Override
    public boolean validateWithRq(EAIRequest<CEW309RSvcRqType> rq) {
        // 下行表頭 HFMTID 必須為 0001
        if (StringUtils.notEquals("0001", StringUtils.trimToEmptyEx(getHeader().getHFMTID()))) {
            return false;
        }
        return StringUtils.equals(getHeader().getHERRID(), "0000") && StringUtils.equals(getHeader().getHFMTID(), "0001");
    }
}
