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
 * <p>
 * Title: com.ibm.tw.commons.util.crypto.DummySexrectKeyProvider
 * </p>
 * <p>
 * Description: dummy sexret key provider, 回傳固定字串
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
public class DummySecretKeyProvider implements SecretKeyProvider<String> {
    private final String AESKEY = "oiw2cA982S12Ouwb";

    @Override
    public String getSecretKey() {
        return AESKEY;
    }

    @Override
    public SecretKeyType getSecretKeyType() {
        return SecretKeyType.PASSWORD_STRING;
    }

    @Override
    public void resetSecretKey() {
        // do nothing
    }
}
