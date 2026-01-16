/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author john
 *
 */
public class FidoOutputParams {

    @SerializedName("data")
    private String dat;

    /**
     * @return the dat
     */
    public String getDat() {
        return dat;
    }

    /**
     * @param dat
     *            the dat to set
     */
    public void setDat(String dat) {
        this.dat = dat;
    }

}
