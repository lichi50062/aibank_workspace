/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW222RSvcRqType;

//@formatter:off
/**
* @(#)CEW222RRQ.java
* 
* <p>Description:CEW222R單筆分期查詢上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/07/20, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW222RRQ extends EAIRequest<CEW222RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW222RRQ() {
        super("CEW222R");
    }
}
