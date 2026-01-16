package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NFEE003SvcRqType;
import tw.com.ibm.mf.eb.NFEE003SvcRsType;
// @formatter:off
/**
 * @(#)NFEE003RS.java
 *
 * <p>Description:NFEE003 專案優惠查詢</p>
 *
 * <p>Modify History:</p>
 *  v1.0, 2023/10/17, MP
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/Count | eai:Tx/TxBody/TxRepeat/UsedCount")
        }
)
public class NFEE003RS extends EAIResponse<NFEE003SvcRqType, NFEE003SvcRsType> {
    /**
     * 建構子
     */
    public NFEE003RS() {
        super("NFEE003");
    }

    @Override
    protected boolean isNoData(String errId) {
        if (StringUtils.equals("V003", errId)) {
            return true;
        }
        return false;
    }
}
