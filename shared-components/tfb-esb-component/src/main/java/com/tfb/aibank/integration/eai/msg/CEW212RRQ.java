/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.CEW212RSvcRqType;

//@formatter:off
/**
* @(#)CEW212RRQ.java
* 
* <p>Description:CEW212RRQ 上行電文 紅利積點兌換紀錄查詢</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/09, John
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CEW212RRQ extends EAIRequest<CEW212RSvcRqType> {

    private static final long serialVersionUID = 1026174408715833819L;

    /**
     * 建構子
     */
    public CEW212RRQ() {
        super("CEW212R");
    }
}