/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2013.
 * 
 * ===========================================================================
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;
import tw.com.ibm.mf.eb.CEW213RSvcRqType;

//@formatter:off
/**
*
* 
* <p>Description:電子帳單設定上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Aaron
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW213RRQ extends EAIRequest<CEW213RSvcRqType> {

    private static final long serialVersionUID = 3469898616860329394L;

    /**
     * 建構子
     */
    public CEW213RRQ() {
        super("CEW213R");
    }

}