/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

/**
 * @author john
 *
 */
public class FidoPublicKeyRequestBean extends FidoBaseRequestBean {

    public FidoPublicKeyRequestBean() {
        this.setApi("client/public-key");
    }
}