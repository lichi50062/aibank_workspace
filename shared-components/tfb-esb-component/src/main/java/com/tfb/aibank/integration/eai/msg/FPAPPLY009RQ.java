package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.FPAPPLY009SvcRqType;

//@formatter:off
/**
 * @(#)FPAPPLY009RQ.java
 * <pre>
 * Description:FPAPPLY009RQ 上行電文
 *
 * Modify History:
 * v1.0, 2025/05/12, Leiley
 * </pre>
 */
//@formatter:on
public class FPAPPLY009RQ extends EAIRequest<FPAPPLY009SvcRqType> {

    private static final long serialVersionUID = -1615530892071633110L;

    /**
     * 建構子
     */
    public FPAPPLY009RQ() {
        super("FPAPPLY009");
    }

}
