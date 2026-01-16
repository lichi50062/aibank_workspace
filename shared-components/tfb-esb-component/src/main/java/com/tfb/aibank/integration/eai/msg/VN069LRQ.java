package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.VN069LSvcRqType;

// @formatter:off
/**
 * @(#)VN069LRQ.java
 * 
 * <p>Description:信用卡控卡異動結果通知</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/03/26, Edward Tien	
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
public class VN069LRQ extends EAIRequest<VN069LSvcRqType> {

    private static final long serialVersionUID = 4806514471059412945L;

    /**
     * 建構子
     */
    public VN069LRQ() {
        super("VN069L");
    }
}
