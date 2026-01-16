package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITimeConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.VN069LSvcRqType;
import tw.com.ibm.mf.eb.VN069LSvcRsType;

// @formatter:off
/**
 * @(#)VN069LRS.java
 * 
 * <p>Description:信用卡控卡異動結果通知 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"
                        + " | eai:Tx/TxBody/O1SEQN | eai:Tx/TxBody/O1GROP | eai:Tx/TxBody/O1OCRS | eai:Tx/TxBody/O2OCRS")
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "cccMMdd", fieldXPath = "eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/O1CHDY | eai:Tx[./TxHead/HFMTID='0002']/TxBody/TxRepeat/O2PHDY"),
                @DateAndTimeConverter(converter = EAITimeConverter.class, inputPattern = "HHmmss", fieldXPath = "eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/O1CHTM | eai:Tx[./TxHead/HFMTID='0002']/TxBody/TxRepeat/O2PHTM")
        },
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, fieldXPath = "eai:Tx/TxBody/O1OCRS | eai:Tx/TxBody/O2OCRS | eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/O1LPAM | eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/O1FPAM | eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/O1LCAM | eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/O1FCAM | eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/O1LTAM | eai:Tx[./TxHead/HFMTID='0001']/TxBody/TxRepeat/O1FTAM | eai:Tx[./TxHead/HFMTID='0002']/TxBody/TxRepeat/O2TXAM")
        }
)
// @formatter:on
public class VN069LRS extends EAIResponse<VN069LSvcRqType, VN069LSvcRsType> {

    /**
     * 建構子
     */
    public VN069LRS() {
        super("VN069L");
    }
}
