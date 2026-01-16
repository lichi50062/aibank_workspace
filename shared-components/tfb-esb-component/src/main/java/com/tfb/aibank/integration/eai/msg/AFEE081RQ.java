package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.DateAndTimeConverter;
import com.tfb.aibank.integration.eai.converter.EAIDateConverter;

import tw.com.ibm.mf.eb.AFEE081SvcRqType;

// @formatter:off
/**
 * @(#)AFEE081RQ.java
 * 
 * <p>Description:AFEE081 基金交易明細查詢-清單(OBU)</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/04/29, Charlie Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        datetimeConverters = {
                @DateAndTimeConverter(converter = EAIDateConverter.class, outputPattern = "cccMMdd", fieldXPath = "eai:Tx/TxBody/StartDt | eai:Tx/TxBody/EndDt")
        }
)
// @formatter:on
public class AFEE081RQ extends EAIRequest<AFEE081SvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public AFEE081RQ() {
        super("AFEE081");
    }
}
