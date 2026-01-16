package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.VDW002RSvcRqType;

//@formatter:off
/**
 * @(#)VDW002RRQ.java
 * 
 * <p>Description:VDW002R 查詢簽帳金融卡帳單明細 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/06, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class VDW002RRQ extends EAIRequest<VDW002RSvcRqType> {

    private static final long serialVersionUID = 8569733785610138558L;

    /**
     * 建構子
     */
    public VDW002RRQ() {
        super("VDW002R");
    }

}
