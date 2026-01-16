/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

/**
 * @author john
 *
 */
public class FidoPublicKeyResponseBean extends FidoBaseResponseBean<String> {

    private String publicKey;

    /**
     * @return the publicKey
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * @param publicKey
     *            the publicKey to set
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
