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

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.EB555692ASvcRqType;
import tw.com.ibm.mf.eb.EB555692ASvcRsType;

import java.util.stream.Stream;

// @formatter:off
/**
 * @(#)EB555692ARS.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/11/28,
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, isPostSign = true, fieldXPath = "eai:Tx/TxBody/TOTAL_AMT_NTD | eai:Tx/TxBody/TOTAL_AMT_FRG | eai:Tx/TxBody/TxRepeat/AVAL_AMT | eai:Tx/TxBody/TxRepeat/CUR_AMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 3, fieldXPath = "eai:Tx/TxBody/TxRepeat/odavail1"),
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000", isPostSign=true, fieldXPath = "eai:Tx/TxBody/TxRepeat/alertcount | eai:Tx/TxBody/TxRepeat/derivative | eai:Tx/TxBody/TxRepeat/SWINDLE_COUNT | eai:Tx/TxBody/TxRepeat/DECLINE_COUNT | " +
                        "eai:Tx/TxBody/TxRepeat/OTHERS_COUNT | eai:Tx/TxBody/TxRepeat/NO_OF_STOPS | eai:Tx/TxBody/TxRepeat/NO_OF_STOP_WDL")
        }
)
// @formatter:on
public class EB555692ARS extends EAIResponse<EB555692ASvcRqType, EB555692ASvcRsType> {

    /**
     * 建構子
     */
    public EB555692ARS() {
        super("EB555692A");
    }

    // @formatter:off
    /**
     * EB555692 網路銀行歸戶資產查詢
     * b. 主機跑批影響判斷：電文回覆失敗且回覆代碼為以下任一情境：
     * i. EB555692_RS.TxHead.HERRID = X012
     * ii. EB555692_RS.TxHead.HERRID = X201
     * iii. EB555692_RS.TxHead.HERRID = X202
     * c. 查無資料判斷：電文回覆失敗且回覆代碼為以下任一情境：
     * i. EB555692_RS.TxHead.HERRID = E005
     * ii. EB555692_RS.TxHead.HERRID = EBB2
     * iii. EB555692_RS.TxHead.HERRID = EP40
     * d. 取得資料失敗判斷：電文回覆失敗且回覆代碼非上述錯誤代碼情境。 由於只有成功才有資料，因此不額外判斷錯誤代碼
     */
    // @formatter:on
    @Override
    protected boolean isNoData(String errId) {
        boolean noDataCheck = super.isNoData(errId);
        if (noDataCheck)
            return noDataCheck;

        Stream<String> noDataRespCodes = Stream.of(ERRID_AS_NODATA.split(","));
        return noDataRespCodes.anyMatch(errId::equals);
    }

    public static final String ERRID_AS_NODATA = "E005,EBB2,EP40,EBD0";
}
