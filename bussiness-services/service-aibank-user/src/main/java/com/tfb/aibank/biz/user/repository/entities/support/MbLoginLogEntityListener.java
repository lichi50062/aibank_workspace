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

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.biz.user.repository.entities.MbLoginLogEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// @formatter:off
/**
 * @(#)MbLoginLogEntityListener.java
 * 
 * <p>Description:[程式說明]</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/02/28, john	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on

public class MbLoginLogEntityListener {
    private AESCipherUtils aesCipherUtils = null;

    public MbLoginLogEntityListener() {
        super();
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        aesCipherUtils = new AESCipherUtils(provider);
    }

    /**
     * 針對「新增前(PrePersist)」、「更新前(PreUpdate)」進行處理
     */
    @PrePersist
    @PreUpdate
    public void encryptUID(Object pc) {
        if (!(pc instanceof CompanyEntity)) {
            return;
        }
        MbLoginLogEntity company = (MbLoginLogEntity) pc;
        try {
            if (StringUtils.length(company.getCompanyUid()) <= 10) {
                // 長度小於10, 則表示未加密
                company.setCompanyUid(aesCipherUtils.encrypt(company.getCompanyUid()));
            }
        }
        catch (Exception e) {
            throw new RuntimeException("error doing encryption for company UID", e);
        }
    }

    /**
     * 針對「查詢後(PostLoad)」、「更新後(PostUpdate)」、「新增後(PostPersist)」進行處理
     */
    @PostLoad
    @PostUpdate
    @PostPersist
    public void decryptUID(Object pc) {
        if (!(pc instanceof CompanyEntity)) {
            return;
        }
        MbLoginLogEntity company = (MbLoginLogEntity) pc;
        try {
            if (StringUtils.length(company.getCompanyUid()) > 10) {
                // 長度大於10才需解密
                company.setCompanyUid(aesCipherUtils.decryptQuietly(company.getCompanyUid()));
            }

        }
        catch (Exception e) {
            throw new RuntimeException("error doing encryption for company UID", e);
        }
    }
}