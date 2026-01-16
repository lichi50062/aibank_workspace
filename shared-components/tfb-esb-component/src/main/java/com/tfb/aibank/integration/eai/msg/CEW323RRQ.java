package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW323RSvcRqType;

//@formatter:off
/**
 * @(#)CEW323RRQ.java
 * 
 * <p>Description:信用卡行銀推播通知設定 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Leiley    
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class CEW323RRQ extends EAIRequest<CEW323RSvcRqType> {

    private static final long serialVersionUID = -2525813942783136922L;

    /**
     * 建構子
     */
    public CEW323RRQ() {
        super("CEW323R");
    }
}
