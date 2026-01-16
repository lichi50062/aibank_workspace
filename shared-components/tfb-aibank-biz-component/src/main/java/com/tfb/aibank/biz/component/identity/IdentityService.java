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
package com.tfb.aibank.biz.component.identity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)IdentityService.java
 * 
 * <p>Description:提供「查詢 COMPANY_KEY 和 USER_KEY」的服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Service
public class IdentityService {

    private static final IBLog logger = IBLog.getLog(IdentityService.class);

    @Autowired
    private IdentityRepository repository;

    private AESCipherUtils aesCipherUtils;

    /**
     * 查詢 COMPANY_KEY & USER_KEY
     * 
     * @param custId
     *            身份證字號
     * @param dup
     *            誤別碼
     * @param userId
     *            使用者代碼
     * @param companyKind
     *            公司類型
     * @return
     * @throws CryptException
     */
    @Cacheable(cacheNames = "IdentityService", keyGenerator = "identityCacheKeyGenerator", unless = "#result == null")
    public IdentityData query(String custId, String dup, String userId, Integer companyKind) throws CryptException {
        String trimCustId = custId;
        if (trimCustId != null) {
            trimCustId = trimCustId.trim();
        }
        return repository.query(encrypt(trimCustId), dup, userId, companyKind);
    }

    /**
     * 取得同ID下不同使用者代號的資料
     * 
     * @param custId
     *            身份證字號
     * @param dup
     *            誤別碼
     * @return
     * @throws CryptException
     */
    public List<IdentityData> queryMultiUser(String custId, String dup) throws CryptException {
        return repository.queryMultiUser(encrypt(custId), dup);
    }

    /**
     * 對資料進行加密
     * 
     * @param plainText
     * @return
     */
    public String encrypt(String plainText) {
        if (this.aesCipherUtils == null) {
            SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
            aesCipherUtils = new AESCipherUtils(provider);
        }
        String cipherText = StringUtils.EMPTY;
        try {
            cipherText = aesCipherUtils.encrypt(plainText);
        }
        catch (CryptException e) {
            logger.error(String.format("error encrypt %s", plainText), e);
        }
        return cipherText;
    }

    /**
     * CustId 不加密,取 Identity
     * 
     * @param custIdEncrypted
     * @param dup
     * @param userId
     * @param companyKind
     * @return
     */
    @Cacheable(cacheNames = "IdentityService", keyGenerator = "identityCacheKeyGenerator", unless = "#result == null")
    public IdentityData queryWithoutEncrypt(String custIdEncrypted, String dup, String userId, Integer companyKind) {
        return repository.query(custIdEncrypted, dup, userId, companyKind);
    }

}
