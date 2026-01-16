/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities.support;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.AccountCreditcardCheckEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

//@formatter:off
/**
* @(#)AccountCreditcardCheckListener.java
* 
* <p>Description:信用卡戶黑名單 AccountCreditcardCheck Entity Listener</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/09/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class AccountCreditcardCheckListener {
    private AESCipherUtils aesCipherUtils = null;

    public AccountCreditcardCheckListener() {
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
        if (!(pc instanceof AccountCreditcardCheckEntity)) {
            return;
        }
        AccountCreditcardCheckEntity source = (AccountCreditcardCheckEntity) pc;
        try {
            if (StringUtils.length(source.getCompanyUid()) <= 10) {
                // 長度小於10, 則表示未加密
                source.setCompanyUid(aesCipherUtils.encrypt(source.getCompanyUid()));
            }
        }
        catch (CryptException e) {
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
        if (!(pc instanceof AccountCreditcardCheckEntity)) {
            return;
        }
        AccountCreditcardCheckEntity source = (AccountCreditcardCheckEntity) pc;

        if (StringUtils.length(source.getCompanyUid()) > 10) {
            // 長度大於10才需解密
            source.setCompanyUid(aesCipherUtils.decryptQuietly(source.getCompanyUid()));
        }

    }
}