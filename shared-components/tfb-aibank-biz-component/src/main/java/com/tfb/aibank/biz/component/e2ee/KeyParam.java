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
package com.tfb.aibank.biz.component.e2ee;

// @formatter:off
/**
 * @(#)KeyParam.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/11, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class KeyParam {

    private String keyName;

    private String keyType;

    private String keyIv;

    private String keyMode;

    public KeyParam(String keyName, String keyType, String keyIv, String keyMode) {
        this.keyName = keyName;
        this.keyType = keyType;
        this.keyIv = keyIv;
        this.keyMode = keyMode;
    }

    /**
     * @return the keyName
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * @param keyName
     *            the keyName to set
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * @return the keyIv
     */
    public String getKeyIv() {
        return keyIv;
    }

    /**
     * @param keyIv
     *            the keyIv to set
     */
    public void setKeyIv(String keyIv) {
        this.keyIv = keyIv;
    }

    /**
     * @return the keyMode
     */
    public String getKeyMode() {
        return keyMode;
    }

    /**
     * @param keyMode
     *            the keyMode to set
     */
    public void setKeyMode(String keyMode) {
        this.keyMode = keyMode;
    }

    /**
     * @return the keyType
     */
    public String getKeyType() {
        return keyType;
    }

    /**
     * @param keyType
     *            the keyType to set
     */
    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

}
