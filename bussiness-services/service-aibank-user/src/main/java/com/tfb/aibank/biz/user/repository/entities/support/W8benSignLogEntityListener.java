/*
 * ===========================================================================
 *
 * IBM Confidential
 *
 * AIS Source Materials
 *
 * (C) Copyright IBM Corp. 2013.
 *
 * ===========================================================================
 */
package com.tfb.aibank.biz.user.repository.entities.support;

import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.W8benSignLogEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
//@formatter:off
/**
 * @(#)W8benSignLogEntityListener.java
 *
 * <p>Description: W-8BEN簽署Log檔 Entity Listener</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/01, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class W8benSignLogEntityListener {
    private AESCipherUtils aesCipherUtils = null;

    public W8benSignLogEntityListener() {
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
        if (!(pc instanceof W8benSignLogEntity)) {
            return;
        }
        W8benSignLogEntity infoLog = (W8benSignLogEntity) pc;
        try {
            infoLog.setEmail(aesCipherUtils.encrypt(infoLog.getEmail()));
        }
        catch (Exception e) {
            throw new RuntimeException("error doing encryption for W8benSignLogEntity email", e);
        }

    }

    /**
     * 解密UID
     */
    @PostLoad
    @PostUpdate
    @PostPersist
    public void decryptUID(Object pc) {
        if (!(pc instanceof W8benSignLogEntity)) {
            return;
        }
        W8benSignLogEntity infoLog = (W8benSignLogEntity) pc;
        try {
            infoLog.setEmail(aesCipherUtils.decryptQuietly(infoLog.getEmail()));
        }
        catch (Exception e) {
            throw new RuntimeException("error doing decryption for W8benSignLogEntity email", e);
        }

    }
}
