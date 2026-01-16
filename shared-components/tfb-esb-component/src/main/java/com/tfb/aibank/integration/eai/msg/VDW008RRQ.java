package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.VDW008RSvcRqType;

//@formatter:off
/**
 * @(#)VDW008RRQ.java
 * 
 * <p>Description:VDW008R 查詢簽帳金融卡帳單明細 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class VDW008RRQ extends EAIRequest<VDW008RSvcRqType> {

    private static final long serialVersionUID = -1874045251170898369L;

    /**
     * 建構子
     */
    public VDW008RRQ() {
        super("VDW008R");
    }

    @Override
    protected boolean allowConversation() {
        return true;
    }
}
