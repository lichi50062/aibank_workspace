package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW013RSvcRqType;

// @formatter:off
/**
 * @(#)CEW013RRQ.java
 * 
 * <p>Description:申辦信用卡客戶資料查詢上行電文</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/12, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CEW013RRQ extends EAIRequest<CEW013RSvcRqType> {

    private static final long serialVersionUID = 726982304497766046L;

    /**
     * 建構子
     */
    public CEW013RRQ() {
        super("CEW013R");
    }
}
