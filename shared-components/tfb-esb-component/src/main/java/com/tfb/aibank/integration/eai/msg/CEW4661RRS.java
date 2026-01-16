/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 *
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.CEW4661RSvcRqType;
import tw.com.ibm.mf.eb.CEW4661RSvcRsType;

// @formatter:off
/**
 * @(#)CEW4661RS.java
 * 
 * <p>Description:[好市多會員自動續約通知]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/08/05, leiley	
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
public class CEW4661RRS extends EAIResponse<CEW4661RSvcRqType, CEW4661RSvcRsType> {

    /**
     * 建構子
     */
    public CEW4661RRS() {
        super("CEW4661R");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || StringUtils.equals(errId, "V848");
    }
}
