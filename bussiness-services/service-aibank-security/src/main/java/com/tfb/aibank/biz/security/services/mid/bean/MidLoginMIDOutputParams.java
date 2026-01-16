/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2023.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.security.services.mid.bean;

import com.google.gson.annotations.SerializedName;

//@formatter:off
/**
* @(#)MidLoginMIDOutputParams.java
* 
* <p>Description:台網MID驗證 - Login回傳物件 - MIDOutputParams</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/07/06, HankChan   
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class MidLoginMIDOutputParams {

    @SerializedName("Platform")
    private String platform;

    @SerializedName("MIDaOutputParams")
    private MidLoginMIDaOutputParams midaOutputParams;

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform
     *            the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return the midaOutputParams
     */
    public MidLoginMIDaOutputParams getMidaOutputParams() {
        return midaOutputParams;
    }

    /**
     * @param midaOutputParams
     *            the midaOutputParams to set
     */
    public void setMidaOutputParams(MidLoginMIDaOutputParams midaOutputParams) {
        this.midaOutputParams = midaOutputParams;
    }

}
