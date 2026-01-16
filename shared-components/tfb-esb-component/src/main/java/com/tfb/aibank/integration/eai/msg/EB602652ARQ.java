package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import tw.com.ibm.mf.eb.EB602652ASvcRqType;
import tw.com.ibm.mf.eb.EB602655ASvcRqType;

//@formatter:off
/**
 * @(#)EB602652ARQ.java
 * 
 * <p>Description:查詢客戶簽帳卡資料 電文上行</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2025/06/03, Bill Chang
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class EB602652ARQ extends EAIRequest<EB602652ASvcRqType> {

    private static final long serialVersionUID = -777012556653921994L;

    /**
     * 建構子
     */
    public EB602652ARQ() {
        super("EB602652A");
    }

}
