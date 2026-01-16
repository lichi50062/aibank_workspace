/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW221RSvcRqType;

//@formatter:off
/**
* @(#)CEW221RRQ.java
* 
* <p>Description:CEW221R(指定消費分期網銀設定交易)上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/02, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW221RRQ extends EAIRequest<CEW221RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW221RRQ() {
        super("CEW221R");
    }
}
