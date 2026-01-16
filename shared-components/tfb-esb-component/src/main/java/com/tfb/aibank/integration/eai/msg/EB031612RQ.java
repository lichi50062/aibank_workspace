package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB031612SvcRqType;

//@formatter:off
/**
 * @(#)EB031612RQ.java
 * <pre>
 * Description:EB031612 上行電文
 *
 * Modify History:
 * v1.0, 2024/01/06, MP, 初版
 * </pre>
 */
//@formatter:on
public class EB031612RQ extends EAIRequest<EB031612SvcRqType> {

    /**
     * 建構子
     */
    public EB031612RQ() {
        super("EB031612");
    }
}
