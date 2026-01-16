package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.AFEE082SvcRqType;

// @formatter:off
/**
 * @(#)AFEE082RQ.java
 * 
 * <p>Description:AFEE082 基金交易明細查詢-明細(OBU)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/29, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern ="cccMMdd" , fieldXPath = "eai:Tx/TxBody/TrscDate")
        }
)
// @formatter:on
public class AFEE082RQ extends EAIRequest<AFEE082SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public AFEE082RQ() {
        super("AFEE082");
    }
}
