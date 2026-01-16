package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB602655ASvcRqType;

//@formatter:off
/**
 * @(#)EB602655ARQ.java
 * 
 * <p>Description:EB602655A 查詢簽帳金融卡卡號 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class EB602655ARQ extends EAIRequest<EB602655ASvcRqType> {

    private static final long serialVersionUID = -777012556653921994L;

    /**
     * 建構子
     */
    public EB602655ARQ() {
        super("EB602655A");
    }

}
