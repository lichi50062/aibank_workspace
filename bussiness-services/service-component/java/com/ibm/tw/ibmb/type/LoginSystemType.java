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
package com.ibm.tw.ibmb.type;

import com.ibm.tw.commons.type.IEnum;

public enum LoginSystemType implements IEnum {

    AIBANK_APP("AIBANK_APP", "AIBank Mobile App"),

    AIBANK_WEB("AIBANK_WEB", "AIBank Desktop Web"),

    UNKNOWN("UNKNOWN", "不明");

    private String channelId;

    private String memo;

    LoginSystemType(String channelId, String memo) {
        this.channelId = channelId;
        this.memo = memo;
    }

    /**
     * @return the channelId
     */
    public String getChannelId() {
        return channelId;
    }

    @Override
    public String getMemo() {

        return memo;
    }

    /**
     * 是否為「行銀」
     * 
     * @return
     */
    public boolean isApp() {
        return equals(AIBANK_APP);
    }

    /**
     * 是否為「網銀」
     * 
     * @return
     */
    public boolean isWeb() {
        return equals(AIBANK_WEB);
    }

}
