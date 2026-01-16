package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.EB552175SvcRqType;

//@formatter:off
/**
 * @(#)EB552175RQ.java
 * 
 * <p>Description:EB552175檢查是否簽訂基金電子契約 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Leiley    
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/TxRepeat/*"),
        },
        datetimeConverters = {
                @DateAndTimeConverter(
                        converter = EAIDateConverter.class, inputPattern = "yyyyMMdd", fieldXPath = "eai:Tx/TxBody/REQ_DATE_01")
        }
)
//@formatter:on
public class EB552175RQ extends EAIRequest<EB552175SvcRqType> {

    private static final long serialVersionUID = 5188318030203980993L;

    /**
     * 建構子
     */
    public EB552175RQ() {
        super("EB552175");
    }

}
