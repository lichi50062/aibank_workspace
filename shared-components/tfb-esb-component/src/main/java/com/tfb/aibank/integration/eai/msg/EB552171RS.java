package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB552171SvcRqType;
import tw.com.ibm.mf.eb.EB552171SvcRsType;

// @formatter:off
/**
 * @(#)EB552171RS.java
 * 
 * <p>Description:EB552170網路銀行轉出帳號建檔及維護下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/NAME | eai:Tx/TxBody/OTP_MOBILE | eai:Tx/TxBody/OTP_REQ_BRH | eai:Tx/TxBody/OTP_CHG_BRH | eai:Tx/TxBody/TD_TYPE | eai:Tx/TxBody/TxRepeat/*")
        }
)
// @formatter:on
public class EB552171RS extends EAIResponse<EB552171SvcRqType, EB552171SvcRsType> {

    /**
     * 建構子
     */
    public EB552171RS() {
        super("EB552171");
    }

    @Override
    public boolean validateHERRID(EAIRequest<EB552171SvcRqType> request) throws EAIException {

        String errId = StringUtils.trimToEmptyEx(getHeader().getHERRID());

        // 2019/11/21 依指示將0899轉換成EW11與其對應訊息
        if (errId.equals("0899")) {
            getHeader().setHERRID("EW11");
            getBody().setEMSGID("EW11");
            getBody().setEMSGTXT("請洽本行各營業單位辦理");
        }

        return super.validateHERRID(request);
    }
}
