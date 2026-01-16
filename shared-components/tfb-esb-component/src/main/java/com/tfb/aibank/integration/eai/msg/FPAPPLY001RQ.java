package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import tw.com.ibm.mf.eb.FPAPPLY001SvcRqType;

//@formatter:off
/**
 * @(#)FPAPPLY001RQ.java
 * <pre>
 * Description:FPAPPLY001 上行電文
 *
 * Modify History:
 * v1.0, 2024/01/06, MP, 初版
 * </pre>
 */
//@formatter:on
public class FPAPPLY001RQ extends EAIRequest<FPAPPLY001SvcRqType> {

    /**
     * 建構子
     */
    public FPAPPLY001RQ() {
        super("FPAPPLY001");
    }
}
