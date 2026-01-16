package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.VN099NSvcRqType;

// @formatter:off
/**
 * @(#)VN099NRQ.java
 * 
 * <p>Description:查詢基金營業日(VN099N) 上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/14, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class VN099NRQ extends EAIRequest<VN099NSvcRqType> {

    private static final long serialVersionUID = 1L;

    /**
     * 建構子
     */
    public VN099NRQ() {
        super("VN099N");
    }
}
