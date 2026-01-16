package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;

import tw.com.ibm.mf.eb.EB0321871SvcRqType;

// @formatter:off
/**
 * @(#)EB0321871RQ.java
 *
 * <p>Description:客戶資料共同行銷註記維護上行電文處理</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/11, Andy Kuo
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/APLY_DATE_01 | eai:Tx/TxBody/APLY_DATE_02")
        }
)
public class EB0321871RQ extends EAIRequest<EB0321871SvcRqType> {

    private static final long serialVersionUID = -6679548631526581051L;

    /**
     * 建構子
     */
    public EB0321871RQ() {
        super("EB0321871");
    }
}
