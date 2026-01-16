package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW325RSvcRqType;
import tw.com.ibm.mf.eb.CEW325RSvcRsType;

//@formatter:off
/**
* @(#)CEW321RRQ.java
* 
* <p>Description:產生超商繳款條碼</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/04, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
@Converter(
    customConverters = { 
        @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
    },
    decimalConverters = { 
        @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, fieldXPath = "eai:Tx/TxBody/BillAmt ")
    }, 
    datetimeConverters = { 
        @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/TxRepeat/PaymentDeadline ")
    }
)
//@formatter:on
public class CEW325RRS extends EAIResponse<CEW325RSvcRqType, CEW325RSvcRsType> {

    public CEW325RRS() {
        super("CEW325R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals("V804", errId);
    }
}
