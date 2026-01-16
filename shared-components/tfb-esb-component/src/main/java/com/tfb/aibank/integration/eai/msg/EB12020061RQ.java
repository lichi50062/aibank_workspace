package com.tfb.aibank.integration.eai.msg;


//@formatter:off
import com.tfb.aibank.integration.eai.EAIRequest;import com.tfb.aibank.integration.eai.annotation.Converter;import com.tfb.aibank.integration.eai.annotation.CustomConverter;import com.tfb.aibank.integration.eai.converter.EAIPadLeftConverter;import tw.com.ibm.mf.eb.EB12020006SvcRqType;import tw.com.ibm.mf.eb.EB12020061SvcRqType; /**
 * @(#)EB12020061RQ.java
 *
 * <p>Description:EB12020061 變更客戶基本資料</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2025/04/15, Harry Chen
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                @CustomConverter(
                        converter = EAIPadLeftConverter.class, padSize = 2, padChar = "0", fieldXPath = "eai:Tx/TxBody/EDU_COD"),
                @CustomConverter(
                        converter = EAIPadLeftConverter.class, padSize = 4, padChar = "0", fieldXPath = "eai:Tx/TxBody/CAR_COD")
        }
)
//@formatter:on
public class EB12020061RQ extends EAIRequest<EB12020061SvcRqType> {
    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public EB12020061RQ() {
        super("EB12020061");
    }
}
