package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB12020006SvcRqType;
import tw.com.ibm.mf.eb.EB12020006SvcRsType;

//@formatter:off
/**
 * @(#)EB12020006RS.java
 * 
 * <p>Description:EB12020006 變更客戶基本資料</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
      customConverters = {
              @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
      }
)
//@formatter:on
public class EB12020006RS extends EAIResponse<EB12020006SvcRqType, EB12020006SvcRsType> {
    /**
     * 建構子
     */
    public EB12020006RS() {
        super("EB12020006");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "EK63".equals(errId) || "0000".equals(errId);
    }
}
