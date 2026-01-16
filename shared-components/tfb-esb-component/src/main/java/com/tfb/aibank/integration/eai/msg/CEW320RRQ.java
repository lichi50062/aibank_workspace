/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW320RSvcRqType;

//@formatter:off
/**
* @(#)CEW320RRQ.java
* 
* <p>Description:CEW320R 預借現金密碼設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/22, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW320RRQ extends EAIRequest<CEW320RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW320RRQ() {
        super("CEW320R");
    }
}
