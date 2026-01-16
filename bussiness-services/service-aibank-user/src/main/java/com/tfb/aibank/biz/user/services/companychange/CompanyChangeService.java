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
package com.tfb.aibank.biz.user.services.companychange;

import com.ibm.tw.commons.exception.CryptException;
import com.tfb.aibank.biz.component.identity.IdentityData;
import com.tfb.aibank.biz.component.identity.IdentityService;
import com.tfb.aibank.biz.user.repository.CompanyRepository;
import com.tfb.aibank.biz.user.repository.entities.CompanyEntity;

// @formatter:off
/**
 * @(#)CompanyChangeService.java
 * 
 * <p>Description:DB Company 修改服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/11, Evan Wang	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CompanyChangeService {
    private IdentityService identityService;

    private CompanyRepository companyRepository;

    public CompanyChangeService(IdentityService identityService, CompanyRepository companyRepository) {
        this.identityService = identityService;
        this.companyRepository = companyRepository;
    }

    /**
     * 更新Company Email
     * 
     * @param custId
     * @param userId
     * @param companyKind
     * @param newEmail
     * @return
     * @throws CryptException
     */
    public boolean changeCompanyEmail(String custId, String udiDup, String userId, Integer companyKind, String newEmail) throws CryptException {
        IdentityData identityData = identityService.query(custId, udiDup, userId, companyKind);

        CompanyEntity companyEntity = companyRepository.findByCompanyKey(identityData.getCompanyKey());
        companyEntity.setEmails(newEmail);
        companyRepository.save(companyEntity);

        return true;
    }

}
