/**
 * 
 */
package com.tfb.aibank.chl.creditcard.resource.dto;

import com.tfb.aibank.biz.component.e2ee.PassRuleMsg;

//@formatter:off
/**
* @(#)ValidateWithPasswordRuleResponse.java
* 
* <p>Description:密碼檢核 - Response</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/22, John
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class ValidateWithPasswordRuleResponse {

    /** 檢查結果 */
    private PassRuleMsg passRuleMsg;

    /**
     * @return the passRuleMsg
     */
    public PassRuleMsg getPassRuleMsg() {
        return passRuleMsg;
    }

    /**
     * @param passRuleMsg
     *            the passRuleMsg to set
     */
    public void setPassRuleMsg(PassRuleMsg passRuleMsg) {
        this.passRuleMsg = passRuleMsg;
    }

}
