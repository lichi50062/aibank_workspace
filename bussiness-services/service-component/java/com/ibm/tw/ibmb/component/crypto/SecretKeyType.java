/*
 * ===========================================================================
 * 
 * IBM Confidential
 * 
 * AIS Source Materials
 * 
 * (C) Copyright IBM Corp. 2024.
 * 
 * ===========================================================================
 */
package com.ibm.tw.ibmb.component.crypto;

/**
 * 
 * <p>
 * Title: com.ibm.tw.commons.util.crypto.SexretKeyType
 * </p>
 * <p>
 * Description: Sexret Key Provider 回傳的 key 類型
 * </p>
 * <p>
 * Copyright: Copyright (c) IBM Corp. 2013. All Rights Reserved.
 * </p>
 * <p>
 * Company: IBM GBS Team
 * </p>
 * 
 * @author horance
 * @version 1.0
 */
public enum SecretKeyType {
    PASSWORD_STRING("PASSWORD", "密碼字串"), HEX_KEY_STRING("HEX", "以HEX編碼的字串"), BYTE_ARRAY("BYTES", "以 byte array 表示之Key值"), SECRET_KEY_SPEC("SECRET_KEY_SPEC", "回傳 Java 之 Secret Key Spec");

    private SecretKeyType(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private String type = "PASSWORD";

    private String desc = "";

    /**
     * 取得 type
     * 
     * @return 傳回 type。
     */
    public String getType() {
        return type;
    }

    /**
     * 設定 type
     * 
     * @param type
     *            要設定的 type。
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 取得 desc
     * 
     * @return 傳回 desc。
     */
    public String getDesc() {
        return desc;
    }

    /**
     * 設定 desc
     * 
     * @param desc
     *            要設定的 desc。
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

}
