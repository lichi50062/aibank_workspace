/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

/**
 * @author john
 *
 */
public class FidoQueryVerifyResultRequestBean extends FidoBaseRequestBean {

    public FidoQueryVerifyResultRequestBean() {
        this.setApi("fido/query-verify-result");
    }
}
