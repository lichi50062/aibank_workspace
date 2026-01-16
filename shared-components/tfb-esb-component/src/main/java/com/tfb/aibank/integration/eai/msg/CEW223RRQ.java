/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW223RSvcRqType;

//@formatter:off
/**
* @(#)CEW223RRQ.java
* 
* <p>Description:信用卡活動登錄/查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW223RRQ extends EAIRequest<CEW223RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW223RRQ() {
        super("CEW223R");
    }
}
