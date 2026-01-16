/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIResponse;

import tw.com.ibm.mf.eb.EB067217SvcRqType;
import tw.com.ibm.mf.eb.EB067217SvcRsType;

//@formatter:off
/**
* @(#)EB067217RS.java
* 
* <p>Description:EB067217 取得ID項下所有的誤別碼資訊 下行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/28, John Chang  
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB067217RS extends EAIResponse<EB067217SvcRqType, EB067217SvcRsType> {

    /**
     * 建構子
     */
    public EB067217RS() {
        super("EB067217");
    }
}