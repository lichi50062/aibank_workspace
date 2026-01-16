package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.VB0052SvcRqType;

// @formatter:off
/**
 * @(#)VB0052RQ.java
 * 
 * <p>Description:富御金歷程查詢 VB0052 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/09, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VB0052RQ extends EAIRequest<VB0052SvcRqType> {

    private static final long serialVersionUID = 7942307208184346734L;

    /**
     * 建構子
     */
    public VB0052RQ() {
        super("VB0052");
    }
}
