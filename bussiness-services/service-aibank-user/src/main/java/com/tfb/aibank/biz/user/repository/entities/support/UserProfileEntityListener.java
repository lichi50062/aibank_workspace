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
package com.tfb.aibank.biz.user.repository.entities.support;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.UserEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// @formatter:off
/**
 * @(#)UserProfileEntityListener.java
 * 
 * <p>Description:加解密</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/27, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class UserProfileEntityListener {
    private AESCipherUtils aesCipherUtils = null;

    public UserProfileEntityListener() {
        super();
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        aesCipherUtils = new AESCipherUtils(provider);
    }

    /**
     * 加密
     */
    @PrePersist
    @PreUpdate
    public void encryptUID(Object pc) {
        if (!(pc instanceof UserEntity)) {
            return;
        }
        UserEntity entity = (UserEntity) pc;
        try {
            entity.setUserCname(aesCipherUtils.encrypt(entity.getUserCname()));
        }
        catch (CryptException e) {
            throw new RuntimeException("error doing encryption for USER_PROFILE", e);
        }
    }

    /**
     * 解密UID
     */
    @PostLoad
    @PostUpdate
    @PostPersist
    public void decryptUID(Object pc) {
        if (!(pc instanceof UserEntity)) {
            return;
        }
        UserEntity entity = (UserEntity) pc;

        entity.setUserCname(aesCipherUtils.decryptQuietly(entity.getUserCname()));

    }
}
