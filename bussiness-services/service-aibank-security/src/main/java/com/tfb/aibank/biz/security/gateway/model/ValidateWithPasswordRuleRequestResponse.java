/**
 * 
 */
package com.tfb.aibank.biz.security.gateway.model;

import com.tfb.aibank.biz.component.e2ee.PassRuleMsg;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @author john
 *
 */
public class ValidateWithPasswordRuleRequestResponse {

    @Schema(description = "檢查結果")
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
