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
import com.tfb.aibank.biz.security.repository.entities.MotpLogDataEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// @formatter:off
/**
 * @(#)MotpLogDataEntityListener.java
 * 
 * <p>Description:MOTP Log Data - EntityListener</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/07/06, HankChan   
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class MotpLogDataEntityListener {

    private static IBLog logger = IBLog.getLog(MotpLogDataEntityListener.class);

    private AESCipherUtils aesCipherUtils = null;

    public MotpLogDataEntityListener() {
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
        if (!(pc instanceof MotpLogDataEntity)) {
            return;
        }
        MotpLogDataEntity entity = (MotpLogDataEntity) pc;
        try {
            if (entity.getCompanyUid() != null && entity.getCompanyUid().length() < 32) {
                entity.setCompanyUid(aesCipherUtils.encrypt(entity.getCompanyUid()));
            }
            entity.setRqData(aesCipherUtils.encrypt(entity.getRqData()));
            entity.setRsData(aesCipherUtils.encrypt(entity.getRsData()));
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
        if (!(pc instanceof MotpLogDataEntity)) {
            return;
        }
        MotpLogDataEntity entity = (MotpLogDataEntity) pc;
        try {
            entity.setCompanyUid(aesCipherUtils.decryptQuietly(entity.getCompanyUid()));
            entity.setRqData(aesCipherUtils.decryptQuietly(entity.getRqData()));
            entity.setRsData(aesCipherUtils.decryptQuietly(entity.getRsData()));
        }
        catch (Exception e) { // 【Fortify：Poor Error Handling: Overly Broad Throws】需要攔截所有例外
            logger.error("decrypt error", e);
        }
    }

}
