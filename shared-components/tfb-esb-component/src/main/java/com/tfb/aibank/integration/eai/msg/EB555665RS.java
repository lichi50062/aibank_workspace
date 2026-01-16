/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB555665SvcRqType;
import tw.com.ibm.mf.eb.EB555665SvcRsType;

//@formatter:off
/**
* @(#)EB613930RS.java
* 
* <p>Description:EB613930預約轉帳</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/29, HankChan    
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
     customConverters = { 
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")  
     }, datetimeConverters = {
        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/TX_DATE")
})
//@formatter:on
public class EB555665RS extends EAIResponse<EB555665SvcRqType, EB555665SvcRsType> {
    /**
     * 建構子
     */
    public EB555665RS() {
        super("EB555665");
    }

    @Override
    public boolean validateWithRq(EAIRequest<EB555665SvcRqType> rq) {

        return StringUtils.equals(StringUtils.trimToEmptyEx(rq.getBody().getPREREQSRL()), StringUtils.trimToEmptyEx(getBody().getPREREQSRL()));
    }

    @Override
    public String getInternalErrorCode() {
        EB555665SvcRsType body = getBody();
        if (body != null && StringUtils.isNotBlank(body.getERROR())) {
            return StringUtils.trimToEmptyEx(body.getERROR());
        }
        return super.getInternalErrorCode();
    }
}
