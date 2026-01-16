package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.VN097NSvcRqType;

// @formatter:off
/**
 * @(#)VN097N.java
 * 
 * <p>Description:資料查詢變更交易(VN097N) 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/03/01, Jojo Wei
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VN097NRQ extends EAIRequest<VN097NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public VN097NRQ() {
        super("VN097N");
    }
}
