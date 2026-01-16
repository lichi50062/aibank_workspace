package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.FPAPPLY003SvcRqType;

//@formatter:off
/**
 * @(#)FPAPPLY003RQ.java
 * <pre>
 * Description:FPAPPLY003RQ 上行電文
 *
 * Modify History:
 * v1.0, 2024/03/27, Kevin Tung, 初版
 * </pre>
 */
//@formatter:on
public class FPAPPLY003RQ extends EAIRequest<FPAPPLY003SvcRqType> {

    /**
     * 建構子
     */
    public FPAPPLY003RQ() {
        super("FPAPPLY003");
    }

}
