/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NCWD001SvcRqType;

//@formatter:off
/**
* @(#)NCWD001RQ.java
* 
* <p>Description:NCWD001 申請無卡提款序號 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCWD001RQ extends EAIRequest<NCWD001SvcRqType> {

    private static final long serialVersionUID = 8647551546002210735L;

    /**
     * 建構子
     */
    public NCWD001RQ() {
        super("NCWD001");
    }
}
