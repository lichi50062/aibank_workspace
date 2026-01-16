package com.tfb.aibank.integration.eai.msg;

import com.ibm.tw.commons.util.ArrayUtils;
import com.tfb.aibank.integration.eai.EAIResponse;
import com.tfb.aibank.integration.eai.annotation.Converter;
import com.tfb.aibank.integration.eai.annotation.CustomConverter;
import com.tfb.aibank.integration.eai.converter.EAITrimConverter;

import tw.com.ibm.mf.eb.FC032155SvcRqType;
import tw.com.ibm.mf.eb.FC032155SvcRsType;

//@formatter:off
/**
 * @(#)FC032155RS.java
 * 
 * <p>Description: 本行客戶註記下行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/15, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
@Converter(
        customConverters = {
                // 針對除了IDNO_STS以外的欄位trim掉空白
                @CustomConverter(converter = EAITrimConverter.class, fieldXPath = "eai:Tx/TxHead/* | eai:Tx/TxBody/*")
        }
)
//@formatter:on
public class FC032155RS extends EAIResponse<FC032155SvcRqType, FC032155SvcRsType> {

    /**
     * 建構子
     */
    public FC032155RS() {
        super("FC032155");
    }

    @Override
    protected boolean isNoData(String errId) {
        return ArrayUtils.contains(CBS_NO_DATA_ERRID, errId);
    }

}
