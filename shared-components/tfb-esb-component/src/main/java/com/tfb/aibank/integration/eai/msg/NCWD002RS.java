/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.NCWD002SvcRqType;
import tw.com.ibm.mf.eb.NCWD002SvcRsType;

//@formatter:off
/**
* @(#)NCWD002RQ.java
* 
* <p>Description:NCWD002 無卡提款序號查詢與取消 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
public class NCWD002RS extends EAIResponse<NCWD002SvcRqType, NCWD002SvcRsType> {

    /**
     * 建構子
     */
    public NCWD002RS() {
        super("NCWD002");
    }

    @Override
    protected boolean isNoData(String errId) {
        if (super.isNoData(errId)) {
            return true;
        }
        return false;
    }
}
//@formatter:on