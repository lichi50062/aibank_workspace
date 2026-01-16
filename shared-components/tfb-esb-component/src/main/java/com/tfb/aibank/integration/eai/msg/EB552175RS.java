package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.ArrayUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB552175SvcRqType;
import tw.com.ibm.mf.eb.EB552175SvcRsType;

//@formatter:off
/**
* @(#)EB552175RS.java
* 
* <p>Description:EB552175檢查是否簽訂基金電子契約 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/06, Leiley    
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
                },
                datetimeConverters = {
                        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/REQ_DATE_01 | eai:Tx/TxBody/CHG_DATE")
                }
        )
//@formatter:on
public class EB552175RS extends EAIResponse<EB552175SvcRqType, EB552175SvcRsType> {

    /**
     * 建構子
     */
    public EB552175RS() {
        super("EB552175");
    }

    /**
     * 下行(IDNO、 NAME_COD)與上行相同
     */
    @Override
    public boolean validateWithRq(EAIRequest<EB552175SvcRqType> rq) {
        if (!StringUtils.trim(rq.getBody().getIDNO()).startsWith(StringUtils.trim(getBody().getIDNO())) || !rq.getBody().getNAMECOD().equals(getBody().getNAMECOD())) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean isNoData(String errId) {
        return "0199".equals(errId) || "EP24".equals(errId) || "X105".equals(errId) || ArrayUtils.contains(CBS_NO_DATA_ERRID, errId);
    }
}
