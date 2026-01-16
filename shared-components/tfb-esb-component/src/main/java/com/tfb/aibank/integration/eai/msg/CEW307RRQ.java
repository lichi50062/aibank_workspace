/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW307RSvcRqType;

//@formatter:off
/**
* @(#)CEW307RRQ.java
* 
* <p>Description:CEW307R 信用卡消費訊息通知設定上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/24, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW307RRQ extends EAIRequest<CEW307RSvcRqType> {

    private static final long serialVersionUID = 7187699741438983472L;

    /**
     * 建構子
     */
    public CEW307RRQ() {
        super("CEW307R");
    }

}