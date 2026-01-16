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

import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.type.CompanyKindType;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// @formatter:off
/**
 * @(#)CompanyEntityListener.java
 * 
 * <p>Description:公司資料 Entity Listener</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, John Chang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CompanyEntityListener {
    private AESCipherUtils aesCipherUtils = null;

    public CompanyEntityListener() {
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
        CompanyEntity company = (CompanyEntity) pc;
        try {
            if (StringUtils.length(company.getCompanyUid()) <= 10 && CompanyKindType.WEBATM.getCode() != company.getCompanyKind()) {
                // 長度小於10, 則表示未加密
                company.setCompanyUid(aesCipherUtils.encrypt(company.getCompanyUid()));
            }

            company.setEmails(aesCipherUtils.encrypt(company.getEmails()));
            company.setCompanyName(aesCipherUtils.encrypt(company.getCompanyName()));
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
        CompanyEntity company = (CompanyEntity) pc;

        if (StringUtils.length(company.getCompanyUid()) > 10 && CompanyKindType.WEBATM.getCode() != company.getCompanyKind()) {
            // 長度大於10才需解密
            company.setCompanyUid(aesCipherUtils.decryptQuietly(company.getCompanyUid()));
        }

        company.setEmails(aesCipherUtils.decryptQuietly(company.getEmails()));
        company.setCompanyName(aesCipherUtils.decryptQuietly(company.getCompanyName()));

    }
}
