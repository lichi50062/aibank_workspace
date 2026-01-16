/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.NCWD004SvcRqType;
import tw.com.ibm.mf.eb.NCWD004SvcRsType;

//@formatter:off
/**
* @(#)NCWD004RQ.java
* 
* <p>Description:NCWD004 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/14, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
/**
 * <p>Title: NCWD004RSRS</p>
 * <p>Description: 取得分行代碼 下行電文</p>
 * <p>Copyright: Copyright (c) IBM Corp. 2014. All Rights Reserved.</p>
 * @version 1.0
 */
public class NCWD004RS extends EAIResponse<NCWD004SvcRqType, NCWD004SvcRsType> {

    /**
     * 建構子
     */
    public NCWD004RS() {
        super("NCWD004");
    }
    
    @Override
    protected boolean isNoData(String errId) {
        if (super.isNoData(errId)) {
            return true;
        }
        return false;
    }
}