package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DecimalConverter;
import com.tfb.aibank.integration.eai.converter.EAIDecimalConverter;

import tw.com.ibm.mf.eb.NJWEEA42SvcRqType;

//@formatter:off
/**
 * @(#)NJWEEA42RQ.java
 * 
 * <p>Description:NJWEEA42 新增結構型商品交易金額限制檢核電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/05/20, Edward Tien    
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        decimalConverters = {
                @DecimalConverter(converter = EAIDecimalConverter.class, scale = -4, pattern = "000000000000000", fieldXPath = "eai:Tx/TxBody/TX_AMT")
        }
)
//@formatter:on
public class NJWEEA42RQ extends EAIRequest<NJWEEA42SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public NJWEEA42RQ() {
        super("NJWEEA42");
    }

}
