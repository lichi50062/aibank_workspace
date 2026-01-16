/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW311RSvcRqType;

//@formatter:off
/**
* @(#)CEW311RRQ.java
* 
* <p>Description:CEW311RRQ 預借現金資料檢核</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW311RRQ extends EAIRequest<CEW311RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW311RRQ() {
        super("CEW311R");
    }
}
