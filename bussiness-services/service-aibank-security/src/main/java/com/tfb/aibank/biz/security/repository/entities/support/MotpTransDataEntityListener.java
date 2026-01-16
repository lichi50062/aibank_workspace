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
package com.tfb.aibank.biz.security.repository.entities.support;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.security.repository.entities.MotpTransDataEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// @formatter:off
/**
 * @(#)MotpTransDataEntityListener.java
 * 
 * <p>Description:MOTP交易認證資訊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/14, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MotpTransDataEntityListener {

    private static IBLog logger = IBLog.getLog(MotpMidDataEntityListener.class);

    private AESCipherUtils aesCipherUtils = null;

    public MotpTransDataEntityListener() {
        super();
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        aesCipherUtils = new AESCipherUtils(provider);
    }

    /**
     * 針對「新增前(PrePersist)」、「更新前(PreUpdate)」進行處理
     */
    @PrePersist
    @PreUpdate
    public void encrypt(Object pc) {
        if (!(pc instanceof MotpTransDataEntity)) {
            return;
        }
        MotpTransDataEntity entity = (MotpTransDataEntity) pc;
        try {
            entity.setTxFactor(aesCipherUtils.encrypt(entity.getTxFactor()));
            entity.setChallenge(aesCipherUtils.encrypt(entity.getChallenge()));
        }
        catch (CryptException e) {
            logger.error("encrypt error", e);
        }
    }

    /**
     * 針對「查詢後(PostLoad)」、「更新後(PostUpdate)」、「新增後(PostPersist)」進行處理
     */
    @PostLoad
    @PostUpdate
    @PostPersist
    public void decrypt(Object pc) {
        if (!(pc instanceof MotpTransDataEntity)) {
            return;
        }
        MotpTransDataEntity entity = (MotpTransDataEntity) pc;

        entity.setTxFactor(aesCipherUtils.decryptQuietly(entity.getTxFactor()));
        entity.setChallenge(aesCipherUtils.decryptQuietly(entity.getChallenge()));

    }

}
