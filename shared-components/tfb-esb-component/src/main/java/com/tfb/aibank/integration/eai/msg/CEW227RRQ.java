/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW227RSvcRqType;

//@formatter:off
/**
* @(#)CEW227RRQ.java
* 
* <p>Description:(指定消費分期試算交易)上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/12, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW227RRQ extends EAIRequest<CEW227RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW227RRQ() {
        super("CEW227R");
    }
}
