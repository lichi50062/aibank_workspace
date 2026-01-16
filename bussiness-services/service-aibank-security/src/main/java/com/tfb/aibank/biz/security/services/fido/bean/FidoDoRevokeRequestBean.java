/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

/**
 * @author john
 *
 */
public class FidoDoRevokeRequestBean extends FidoBaseRequestBean {

    public FidoDoRevokeRequestBean() {
        this.setApi("fido/do-revoke");
    }
}
