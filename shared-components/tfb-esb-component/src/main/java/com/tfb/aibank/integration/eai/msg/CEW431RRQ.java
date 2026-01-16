/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW431RSvcRqType;

//@formatter:off
/**
* @(#)CEW431RRQ.java
* 
* <p>Description:信用卡OTP</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/12, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW431RRQ extends EAIRequest<CEW431RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW431RRQ() {
        super("CEW431R");
    }
}
