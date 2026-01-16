package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.NAPBW7SvcRqType;
import tw.com.ibm.mf.eb.NAPBW7SvcRsType;

//@formatter:off
/**
 * <p>Title: com.fubon.tw.pib.tr.message.NAPBW7RS</p>
 * <p>Description: 員工持股信託退出報告書查詢 下行電文</p>
 * @version 1.0
 */
@Converter(
        customConverters = {
            @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/* | " + 
                                                                              "eai:Tx/TxBody/CTSEQ | " + 
                                                                              "eai:Tx/TxBody/CUST_ID | " + 
                                                                              "eai:Tx/TxBody/CTSEQ | " + 
                                                                              "eai:Tx/TxBody/CUST_ID | " + 
                                                                              "eai:Tx/TxBody/CTNAME | " + 
                                                                              "eai:Tx/TxBody/CUST_NAME | " + 
                                                                              "eai:Tx/TxBody/SUM_AMT_SLF | " + 
                                                                              "eai:Tx/TxBody/SUM_AMT_CMP | " + 
                                                                              "eai:Tx/TxBody/STORAGE_FEE_SHLDPAY | " + 
                                                                              "eai:Tx/TxBody/TRUST_AMT_SUM | " + 
                                                                              "eai:Tx/TxBody/UNITS_SLF | " + 
                                                                              "eai:Tx/TxBody/UNITS_CMP | " + 
                                                                              "eai:Tx/TxBody/UNITS_SUM | " + 
                                                                              "eai:Tx/TxBody/INVEST_NAME | " + 
                                                                              "eai:Tx/TxBody/RECV_UNITS_CNT | " + 
                                                                              "eai:Tx/TxBody/RECV_UNITS_CNT_ACT | " + 
                                                                              "eai:Tx/TxBody/ENTSELL_UNITS_CNT | " + 
                                                                              "eai:Tx/TxBody/SELL_UNIT_AMT | " + 
                                                                              "eai:Tx/TxBody/ENTSELL_AMT | " + 
                                                                              "eai:Tx/TxBody/CASH_OVER | " + 
                                                                              "eai:Tx/TxBody/STORAGE_FEE | " + 
                                                                              "eai:Tx/TxBody/INSURAN_FDD_ADD | " + 
                                                                              "eai:Tx/TxBody/AMT_SHLDPAY | " + 
                                                                              "eai:Tx/TxBody/F_UNRECV_UNITS_CMP | " + 
                                                                              "eai:Tx/TxBody/F_RECV_UNITS_CNT_ACT | " + 
                                                                              "eai:Tx/TxBody/F_RECV_AMT_ACT | " + 
                                                                              "eai:Tx/TxBody/F_POSTEL_FEE | " + 
                                                                              "eai:Tx/TxBody/F_TRANS_AMT | " +
                                                                              "eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"
            )
        },
        datetimeConverters = {
            @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ccccMMdd", fieldXPath = "eai:Tx/TxBody/EXIT_DATE")
        }
)
//@formatter:on
public class NAPBW7RS extends EAIResponse<NAPBW7SvcRqType, NAPBW7SvcRsType> {

    /**
     * 建構子
     */
    public NAPBW7RS() {
        super("NAPBW7");
    }

    @Override
    protected boolean isNoData(String errId) {
        return "V003".equals(errId) || "EA07".equals(errId);
    }
}
