/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW220RSvcRqType;

//@formatter:off
/**
* @(#)CEW220RRQ.java
* 
* <p>Description:CEW220R(指定消費分期試算交易)上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/10/02, Evan Wang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW220RRQ extends EAIRequest<CEW220RSvcRqType> {

    private static final long serialVersionUID = 404121952403187338L;

    /**
     * 建構子
     */
    public CEW220RRQ() {
        super("CEW220R");
    }
}
