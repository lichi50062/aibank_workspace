package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.VN069DSvcRqType;
import tw.com.ibm.mf.eb.VN069DSvcRsType;

// @formatter:off
/**
 * @(#)VN069DRS.java
 * 
 * <p>Description:信用卡行銀推播訊息通知 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"
                        + " | eai:Tx/TxBody/SEQN | eai:Tx/TxBody/GROP | eai:Tx/TxBody/Occur | eai:Tx/TxBody/O8SEQN | eai:Tx/TxBody/O8GROP | eai:Tx/TxBody/O8OCRS")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/DATE | eai:Tx[./TxHead/HFMTID='0002']/TxBody/TxRepeat/DATE"
                        + " | eai:Tx[./TxHead/HFMTID='0003']/TxBody/TxRepeat/DATE | eai:Tx[./TxHead/HFMTID='0006']/TxBody/TxRepeat/DATE | eai:Tx/TxBody/TxRepeat/O8DATE"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/TIME | eai:Tx[./TxHead/HFMTID='0002']/TxBody/TxRepeat/TIME"
                        + " | eai:Tx[./TxHead/HFMTID='0003']/TxBody/TxRepeat/TIME | eai:Tx[./TxHead/HFMTID='0006']/TxBody/TxRepeat/TIME | eai:Tx/TxBody/TxRepeat/O8TIME")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, fieldXPath = "eai:Tx/TxBody/Occur | eai:Tx/TxBody/O8OCRS"
                        + " | eai:Tx/TxBody/TxRepeat/TAMT | eai:Tx/TxBody/TxRepeat/O8TAMT"),
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 2, fieldXPath = "eai:Tx[./TxHead/HFMTID='0003']/TxBody/TxRepeat/RATE | eai:Tx/TxBody/TxRepeat/O8RATE | eai:Tx[./TxHead/HFMTID='0003']/TxBody/TxRepeat/SRAM | eai:Tx[./TxHead/HFMTID='0006']/TxBody/TxRepeat/SRAM | eai:Tx/TxBody/TxRepeat/O8SRAM")
        }
)
// @formatter:on
public class VN069DRS extends EAIResponse<VN069DSvcRqType, VN069DSvcRsType> {

    /**
     * 建構子
     */
    public VN069DRS() {
        super("VN069D");
    }

    @Override
    protected boolean isNoData(String errId) {
        return StringUtils.equals(errId, "V003");
    }
}
