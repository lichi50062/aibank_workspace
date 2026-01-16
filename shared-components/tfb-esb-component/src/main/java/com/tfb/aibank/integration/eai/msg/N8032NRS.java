package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.N8032NSvcRqType;
import tw.com.ibm.mf.eb.N8032NSvcRsType;

//@formatter:off
/**
 * @(#)N8032NRS.java
 * 
 * <p>Description: N8032N 海外股票ETF定期定額契約變更</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/05/27, Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class N8032NRS extends EAIResponse<N8032NSvcRqType, N8032NSvcRsType> {

    public N8032NRS() {
        super("N8032N");
    }

}
