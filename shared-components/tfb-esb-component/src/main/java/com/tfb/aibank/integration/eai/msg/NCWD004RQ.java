/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.NCWD004SvcRqType;

//@formatter:off
/**
* @(#)NCWD004RQ.java
* 
* <p>Description:NCWD004 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class NCWD004RQ extends EAIRequest<NCWD004SvcRqType> {

    private static final long serialVersionUID = 8647551546002210735L;

    /**
     * 建構子
     */
    public NCWD004RQ() {
        super("NCWD004");
    }
}
