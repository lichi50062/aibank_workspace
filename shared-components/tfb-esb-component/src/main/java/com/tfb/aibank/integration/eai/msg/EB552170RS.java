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
import com.tfb.aibank.integration.eai.EAIException;
import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB552170SvcRqType;
import tw.com.ibm.mf.eb.EB552170SvcRsType;

// @formatter:off
/**
 * @(#)EB552170RS.java
 * 
 * <p>Description:EB552170網路銀行申請資料建檔及維護 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }, 
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/LOCK_PW_DATE | eai:Tx/TxBody/PW_TRAN_DATE")
        }
)
// @formatter:on
public class EB552170RS extends EAIResponse<EB552170SvcRqType, EB552170SvcRsType> {

    /**
     * 建構子
     */
    public EB552170RS() {
        super("EB552170");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "EP36".equals(errId) || "EP42".equals(errId) || "EP51".equals(errId);
    }

    @Override
    public boolean validateHERRID(EAIRequest<EB552170SvcRqType> request) throws EAIException {

        String errId = StringUtils.trimToEmptyEx(getHeader().getHERRID());

        // 上送的 ITEM_NO != D 的情況下，下行 HERRID = EP42 or EP51，視為失敗
        if (!"D".equals(StringUtils.trimToEmptyEx(request.getBody().getITEMNO()))) {
            return StringUtils.equals("0000", errId);
        }

        // 2019/11/21 依指示將0899轉換成EW11與其對應訊息
        if (errId.equals("0899")) {
            getHeader().setHERRID("EW11");
            getBody().setEMSGID("EW11");
            getBody().setEMSGTXT("請洽本行各營業單位辦理");
        }

        return super.validateHERRID(request);
    }

}
