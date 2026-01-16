/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB067217SvcRqType;

//@formatter:off
/**
* @(#)EB067217RQ.java
* 
* <p>Description:EB067217 取得ID項下所有的誤別碼資訊 上行電文</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/28, John Chang  
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class EB067217RQ extends EAIRequest<EB067217SvcRqType> {

    private static final long serialVersionUID = 602985611802022543L;

    /**
     * 建構子
     */
    public EB067217RQ() {
        super("EB067217");
    }
}