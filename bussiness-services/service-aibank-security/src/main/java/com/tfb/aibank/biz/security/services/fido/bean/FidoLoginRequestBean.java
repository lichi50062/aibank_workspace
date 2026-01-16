/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

/**
 * @author john
 *
 */
public class FidoLoginRequestBean extends FidoBaseRequestBean {

    public FidoLoginRequestBean() {
        this.setApi("fido/login");
    }
}
