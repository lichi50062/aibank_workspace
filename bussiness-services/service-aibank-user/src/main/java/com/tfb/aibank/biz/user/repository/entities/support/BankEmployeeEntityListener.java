package com.tfb.aibank.biz.user.repository.entities.support;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.BankEmployeeEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// @formatter:off
/**
 * @(#)BankEmployeeEntityListener.java
 *
 * <p>Description:加解密 BankEmployee 相關欄位</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2023/06/04 Edward Tien
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class BankEmployeeEntityListener {
    private AESCipherUtils aesCipherUtils = null;

    public BankEmployeeEntityListener() {
        super();
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        aesCipherUtils = new AESCipherUtils(provider);
    }

    @PrePersist
    @PreUpdate
    public void encrypt(Object pc) {
        if (!(pc instanceof BankEmployeeEntity)) {
            return;
        }
        BankEmployeeEntity entity = (BankEmployeeEntity) pc;
        try {

            if (StringUtils.length(entity.getEmployeeId()) <= 11) {
                entity.setEmployeeId(aesCipherUtils.encrypt(entity.getEmployeeId()));
            }

        }
        catch (CryptException e) {
            throw new RuntimeException("error doing encryption for BankEmployee", e);
        }
    }

    @PostLoad
    @PostUpdate
    @PostPersist
    public void decrypt(Object pc) {
        if (!(pc instanceof BankEmployeeEntity)) {
            return;
        }
        BankEmployeeEntity entity = (BankEmployeeEntity) pc;

        if (StringUtils.length(entity.getEmployeeId()) > 11) {
            entity.setEmployeeId(aesCipherUtils.decryptQuietly(entity.getEmployeeId()));
        }

    }
}
