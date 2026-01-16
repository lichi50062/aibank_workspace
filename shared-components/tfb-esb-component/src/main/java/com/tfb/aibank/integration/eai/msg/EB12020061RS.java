package com.tfb.aibank.integration.eai.msg;


//@formatter:off
import com.tfb.aibank.integration.eai.EAIResponse;import com.tfb.aibank.integration.eai.annotation.Converter;import com.tfb.aibank.integration.eai.annotation.CustomConverter;import com.tfb.aibank.integration.eai.converter.EAITrimConverter;import tw.com.ibm.mf.eb.EB12020006SvcRqType;import tw.com.ibm.mf.eb.EB12020006SvcRsType;import tw.com.ibm.mf.eb.EB12020061SvcRqType;import tw.com.ibm.mf.eb.EB12020061SvcRsType; /**
 * @(#)EB12020061RS.java
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
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }
)
//@formatter:on
public class EB12020061RS extends EAIResponse<EB12020061SvcRqType, EB12020061SvcRsType> {
    /**
     * 建構子
     */
    public EB12020061RS() {
        super("EB12020061");
    }

    @Override
    protected boolean isNoData(String errId) {
        return super.isNoData(errId) || "EK63".equals(errId) || "0000".equals(errId);
    }
}
