package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.VN069DSvcRqType;

// @formatter:off
/**
 * @(#)VN069DRQ.java
 * 
 * <p>Description:信用卡行銀推播訊息通知</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/10/19, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = 0, fieldXPath = "eai:Tx/TxBody/VQGRSN")
        }
)
// @formatter:on
public class VN069DRQ extends EAIRequest<VN069DSvcRqType> {

    private static final long serialVersionUID = 4806514471059412945L;

    /**
     * 建構子
     */
    public VN069DRQ() {
        super("VN069D");
        this.getHeader().setHDRVQ1("WCC00AHQ");
    }
}
