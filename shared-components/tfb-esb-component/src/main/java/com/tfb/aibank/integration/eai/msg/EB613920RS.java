package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB613920SvcRqType;
import tw.com.ibm.mf.eb.EB613920SvcRsType;

//@formatter:off
/**
 * @(#)EB613920RS.java
 *
 * <p>Description:台轉外、外轉台、外轉外預約轉帳上行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/24
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxBody/*")
        },
        decimalConverters = {
                        @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/FEE ")
        },
        datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/ACT_DATE | eai:Tx/TxBody/TX_DATE")
})
public class EB613920RS extends EAIResponse<EB613920SvcRqType, EB613920SvcRsType> {
    /**
     * 建構子
     */
    public EB613920RS() {
        super("EB613920");
    }
    
    @Override
    public String getInternalErrorCode() {
        EB613920SvcRsType body = getBody();
        if (body != null && StringUtils.isNotBlank(body.getERROR())) {
            return StringUtils.trimToEmptyEx(body.getERROR());
        }
        return super.getInternalErrorCode();
    }
    
    @Override
    public boolean validateHERRID(EAIRequest<EB613920SvcRqType> request) throws EAIException {
        String errId = StringUtils.trimToEmptyEx(getHeader().getHERRID());        
        if("0899".equals(errId)){
            //舊程式在此情境中會去取ACC_STS_ERR_CODE資料替換到EMSGTXT
        }
        return super.validateHERRID(request);
    }
}
