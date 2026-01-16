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

import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyType;
import com.tfb.aibank.chl.service.AIBankChannelService;
import com.tfb.aibank.component.system.StaticServiceFactory;

// @formatter:off
/**
 * @(#)OTPSecretKeyProviderViaE2EE.java
 * 
 * <p>Description:E2EE AES256 SMS encrypt upgrade</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/10/16, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class OTPSecretKeyProviderViaE2EE implements SecretKeyProvider<byte[]> {
    // private static final String KEY_NAME_DB_OTP_AES256 = "EBOTPKEY";

    private byte[] secretKey = new byte[0];

    protected static IBLog logger = IBLog.getLog(AIBankChannelService.class);

    @Override
    public SecretKeyType getSecretKeyType() {
        return SecretKeyType.BYTE_ARRAY;
    }

    @Override
    public byte[] getSecretKey() {
        logger.info("OTPSecretKeyProviderViaE2EE getSecretKey：" + secretKey);
        if (secretKey.length <= 0) {
            initSecretKey();
        }
        return secretKey;
    }

    @Override
    public void resetSecretKey() {
        secretKey = new byte[0];
    }

    private void initSecretKey() {
        try {
            String data = StaticServiceFactory.getInstance().getSecurityResource().getOTPAccessKxy();
            byte[] secretKey2 = data.getBytes();
            secretKey = secretKey2;
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}