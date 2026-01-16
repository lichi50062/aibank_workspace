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
import com.tfb.aibank.biz.user.repository.entities.ChangeCustDataRecordEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

//@formatter:off
/**
* @(#)ChangeCustDataRecordEntityListener.java
* 
* <p>Description:[加解密 ChangeCustDataRecordEntity]</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/20, Evan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class ChangeCustDataRecordEntityListener {

    private AESCipherUtils aesCipherUtils = null;

    public ChangeCustDataRecordEntityListener() {
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        aesCipherUtils = new AESCipherUtils(provider);
    }

    @PrePersist
    @PreUpdate
    public void encrypt(Object pc) {
        if (!(pc instanceof ChangeCustDataRecordEntity)) {
            return;
        }
        ChangeCustDataRecordEntity entity = (ChangeCustDataRecordEntity) pc;
        try {
            entity.setCustEmail(aesCipherUtils.encrypt(entity.getCustEmail()));
        }
        catch (CryptException e) {
            throw new RuntimeException("error doing encryption for Cust_Email", e);
        }
    }

    @PostLoad
    @PostUpdate
    @PostPersist
    public void decrypt(Object pc) {
        if (!(pc instanceof ChangeCustDataRecordEntity)) {
            return;
        }
        ChangeCustDataRecordEntity entity = (ChangeCustDataRecordEntity) pc;

        entity.setCustEmail(aesCipherUtils.decryptQuietly(entity.getCustEmail()));

    }
}