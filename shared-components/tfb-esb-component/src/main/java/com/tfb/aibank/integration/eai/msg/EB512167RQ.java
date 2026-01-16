/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB512167SvcRqType;

//@formatter:off
/**
* @(#)EB512167RQ.java
* 
* <p>Description: 無卡提款功能維護 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB512167RQ extends EAIRequest<EB512167SvcRqType> {

    private static final long serialVersionUID = -2073376657694983961L;

    /**
     * 建構子
     */
    public EB512167RQ() {
        super("EB512167");
    }

}