/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

/**
 * @author john
 *
 */
public class FidoQueryLogRequestBean extends FidoBaseRequestBean {

    public FidoQueryLogRequestBean() {
        this.setApi("fido/query-log");
    }
}