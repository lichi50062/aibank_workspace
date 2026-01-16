/**
 * 
 */
package com.tfb.aibank.integration.eai.msg;

import com.tfb.aibank.integration.eai.EAIRequest;

import tw.com.ibm.mf.eb.EB01705001SvcRqType;

//@formatter:off
/**
* @(#)EB01705001RQ.java
*
* <p>Description 取得貸款協商紀錄</p>
*
* <p>Modify History:</p>
* v1.0, 2023/07/10,
* <ol>
*  <li>初版</li>
* </ol>
*/
public class EB01705001RQ extends EAIRequest<EB01705001SvcRqType> {

    /**
     * 建構子
     */
    public EB01705001RQ() {
        super("EB01705001");
    }
}
