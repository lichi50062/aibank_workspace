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

import tw.com.ibm.mf.eb.CEW301RSvcRqType;
import tw.com.ibm.mf.eb.CEW301RSvcRsType;

//@formatter:off
/**
* @(#)CEW301RRS.java
* 
* <p>Description:信用卡戶況查詢下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(customConverters = { 
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*"), 
})
//@formatter:on
public class CEW301RRS extends EAIResponse<CEW301RSvcRqType, CEW301RSvcRsType> {

    /**
     * 建構子
     */
    public CEW301RRS() {
        super("CEW301R");
    }

    @Override
    public boolean validateWithRq(EAIRequest<CEW301RSvcRqType> rq) {
        return StringUtils.equals(StringUtils.trimToEmptyEx(rq.getBody().getID()), StringUtils.trimToEmptyEx(getBody().getACCOUNTID()));
    }
}
