package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import tw.com.ibm.mf.eb.FPAPPLY002SvcRqType;


//@formatter:off
/**
 * @(#)FPAPPLY002RQ.java
 * <pre>
 * Description:FPAPPLY002RQ 上行電文
 *
 * Modify History:
 * v1.0, 2024/01/06, MP, 初版
 * </pre>
 */
//@formatter:on
public class FPAPPLY002RQ extends EAIRequest<FPAPPLY002SvcRqType> {
    /**
     * 建構子
     */
    public FPAPPLY002RQ() {
        super("FPAPPLY002");
    }
}
