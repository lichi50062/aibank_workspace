package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB552171SvcRqType;

// @formatter:off
/**
 * @(#)EB552171RQ.java
 * 
 * <p>Description:EB552170網路銀行轉出帳號建檔及維護 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/04, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 11, fieldXPath = "eai:Tx/TxBody/IDNO"),
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 10, fieldXPath = "eai:Tx/TxBody/USER_ID")
        }
)
//@formatter:on
public class EB552171RQ extends EAIRequest<EB552171SvcRqType> {

    private static final long serialVersionUID = -1295688508653128300L;

    /**
     * 建構子
     */
    public EB552171RQ() {
        super("EB552171");
    }
}
