/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities.support;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.CardMemberRecordEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

//@formatter:off
/**
* @(#)CardMemberRecordEntityListener.java
* 
* <p>Description:信用卡會員資料 Entity Listener</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, Edward Tien    
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class CardMemberRecordEntityListener {

    private AESCipherUtils aesCipherUtils = null;

    public CardMemberRecordEntityListener() {
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
        if (!(pc instanceof CardMemberRecordEntity)) {
            return;
        }
        CardMemberRecordEntity entity = (CardMemberRecordEntity) pc;
        try {
            entity.setCompanyUid(aesCipherUtils.encrypt(entity.getCompanyUid()));
            entity.setCsName(aesCipherUtils.encrypt(entity.getCsName()));
        }
        catch (CryptException e) {
            throw new RuntimeException("error doing encryption for company UID", e);
        }
    }

    /**
     * 解密UID
     */
    @PostLoad
    @PostPersist
    @PostUpdate
    public void decryptUID(Object pc) {
        if (!(pc instanceof CardMemberRecordEntity)) {
            return;
        }
        CardMemberRecordEntity entity = (CardMemberRecordEntity) pc;

        entity.setCompanyUid(aesCipherUtils.decryptQuietly(entity.getCompanyUid()));
        entity.setCsName(aesCipherUtils.decryptQuietly(entity.getCsName()));

    }
}
