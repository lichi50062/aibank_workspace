package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.FEP852835SvcRqType;

//@formatter:off
/**
 * @(#)FEP852835RQ.java
 * 
 * <p>Description:EB602655A 查詢簽帳金融卡卡號對應卡片性質 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class FEP852835RQ extends EAIRequest<FEP852835SvcRqType> {

    private static final long serialVersionUID = -1466690723416849468L;

    /**
     * 建構子
     */
    public FEP852835RQ() {
        super("FEP852835");
    }

}
