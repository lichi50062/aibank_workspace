package com.tfb.aibank.common.type;

import com.ibm.tw.commons.type.IEnum;

/*
//@formatter:off
/**
* @(#) UrlOpenType.java
* 
* <p>Description: URL 開啟方式</p>
* 
* <p>Modify History:</p>
* v1.0, 2024/01/09, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public enum UrlOpenType implements IEnum {

    OPEN_EXTERNAL("O", "AI Bank外開瀏覽器"),

    OPEN_IN_APP_BROWSER("I", "AI Bank內崁"),

    AI_SSO_AUTH("E", "外部網站進AI Bank SSO");

    /** 開啟方式* */
    private String type;

    /** 狀態說明* */
    private String memo;

    UrlOpenType(String type, String memo) {
        this.type = type;
        this.memo = memo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.ibm.tw.commons.type.IEnum#getMemo()
     */
    @Override
    public String getMemo() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    public static boolean isOpenExternal(String type) {
        if (UrlOpenType.OPEN_EXTERNAL.getType().equals(type))
            return true;
        return false;
    }

    public static boolean isOpeInAppBrowser(String type) {
        if (UrlOpenType.OPEN_IN_APP_BROWSER.getType().equals(type))
            return true;
        return false;
    }

    public static boolean isSsoAuth(String type) {
        if (UrlOpenType.AI_SSO_AUTH.getType().equals(type))
            return true;
        return false;
    }

}