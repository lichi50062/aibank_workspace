package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;
import tw.com.ibm.mf.eb.EB182651SvcRqType;
import tw.com.ibm.mf.eb.EB182651SvcRsType;


// @formatter:off
/**
 * @(#)EB182651RS.java
 *
 * <p>Description:查詢續存紀錄下行電文 下行電文</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/05/28, Evan Wang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
				@CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/ACNO_SA | eai:Tx/TxBody/SLIP_NO1 | eai:Tx/TxBody/CUR | eai:Tx/TxBody/AUTO_TR_CNT | eai:Tx/TxBody/TxRepeat/* | eai:Tx/TxBody/EMSGID | eai:Tx/TxBody/EMSGTXT"),
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath="eai:Tx/TxBody/TxRepeat/OPN_DPR_AMT")
        },
        decimalConverters = {
        		@DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000", isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/DPR_MMDD"), //存期
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale = 3,isPostSign = false, fieldXPath = "eai:Tx/TxBody/TxRepeat/OPN_DPR_AMT | eai:Tx/TxBody/TxRepeat/INT_CUR"), //存單金額, 原幣利息
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000", scale = 8,isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/INT_RATE"), //利率
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000", scale = 6,isPostSign = true, fieldXPath = "eai:Tx/TxBody/TxRepeat/EXCG_RATE"), //匯率
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000", scale = 0,fieldXPath = "eai:Tx/TxBody/TxRepeat/INT_NTD | eai:Tx/TxBody/TxRepeat/NTD_H_FEE"), //台幣利息, 台幣代扣健保費
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "00000000000000000", scale = 3,isPostSign = true,fieldXPath = "eai:Tx/TxBody/TxRepeat/TAX_NTD"), //台幣扣稅
                @DecimalConverter(converter = EAIDecimalConverter.class, pattern = "000000000000000000", scale = 0,fieldXPath = "eai:Tx/TxBody/TxRepeat/CUR_H_FEE"), //原幣代扣健保費
                @DecimalConverter(converter = EAIDecimalConverter.class, fieldXPath = "eai:Tx/TxBody/TxRepeat/RENEW_CNT") //轉期次數
        },
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, inputPattern = "ddMMyyyy", fieldXPath = "eai:Tx/TxBody/TxRepeat/BK_VALUE | eai:Tx/TxBody/TxRepeat/DUE_DTE") //起息日, 到期日
        }
)
public class EB182651RS extends EAIResponse<EB182651SvcRqType, EB182651SvcRsType> {
    /**
     * 建構子
     */
    public EB182651RS() {
        super("EB1892651");
    }

    @Override
    protected boolean isNoData(String errId) {
    	 if ("EG06".equals(errId)) {
             return true;
         }
         return false;
    }


}
