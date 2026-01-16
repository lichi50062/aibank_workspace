package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB12020102SvcRqType;

// @formatter:off
/**
 * @(#)EB12020102RQ.java
 * 
 * <p>Description:一般定存(非零存整付)明細查詢 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(converter = EAIPadRightConverter.class, padSize = 11, fieldXPath = "eai:Tx/TxBody/IDNO")
        }
)
// @formatter:on
public class EB12020102RQ extends EAIRequest<EB12020102SvcRqType> {

    private static final long serialVersionUID = 359580913021856161L;

    /**
     * 建構子
     */
    public EB12020102RQ() {
        super("EB12020102");
    }
}
