/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NCWD002SvcRqType;

//@formatter:off
/**
* @(#)NCWD002RQ.java
* 
* <p>Description:NCWD002 無卡提款序號查詢與取消 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCWD002RQ extends EAIRequest<NCWD002SvcRqType> {

    private static final long serialVersionUID = 8647551546002210735L;

    /**
     * 建構子
     */
    public NCWD002RQ() {
        super("NCWD002");
    }
}
