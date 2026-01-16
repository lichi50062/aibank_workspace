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
package com.tfb.aibank.component.crypto;

import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyType;
import com.tfb.aibank.component.system.StaticServiceFactory;

// @formatter:off
/**
 * @(#)DBSecretKeyProvider.java
 * 
 * <p>Description:由DB中取得HSM加密後的AES Key, 並交由HSM解密</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, Horance Chou
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class DBSecretKeyProvider implements SecretKeyProvider<byte[]> {

    private byte[] secretKey = new byte[0];

    public DBSecretKeyProvider() {
        // default constructor
    }

    private void initSecretKey() {
        try {
            String data = StaticServiceFactory.getInstance().getSecurityResource().getDBAccessKxy();
            byte[] secretKey2 = data.getBytes();
            secretKey = secretKey2;
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    protected String getKeyCipherHex() {
        return StaticServiceFactory.getInstance().getKeyCipherHex();
    }

    @Override
    public SecretKeyType getSecretKeyType() {
        return SecretKeyType.BYTE_ARRAY;
    }

    @Override
    public byte[] getSecretKey() {
        if (secretKey.length <= 0) {
            initSecretKey();
        }
        return secretKey;
    }

    @Override
    public void resetSecretKey() {
        secretKey = new byte[0];
    }
}
