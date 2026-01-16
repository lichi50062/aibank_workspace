package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAIPadRightConverter;

import tw.com.ibm.mf.eb.EB12020002SvcRqType;

// @formatter:off
/**
 * @(#)EB12020002RQ.java
 * 
 * <p>Description:零存整付定存明細查詢 下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/26, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(
                        converter = EAIPadRightConverter.class, padSize = 11, fieldXPath = "eai:Tx/TxBody/IDNO")
        }
)
// @formatter:on
public class EB12020002RQ extends EAIRequest<EB12020002SvcRqType> {

    private static final long serialVersionUID = 3307031552124155835L;

    /**
     * 建構子
     */
    public EB12020002RQ() {
        super("EB12020002");
    }
}
