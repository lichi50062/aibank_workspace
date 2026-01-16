/**
 * 
 */
package com.tfb.aibank.biz.user.repository.entities.support;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.LinePNPRecordEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

//@formatter:off
/**
* @(#)CompanyEntityListener.java
* 
* <p>Description:LinePNPRecordEntity Listener</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/06/02, John Chang 
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class LinePNPRecordEntityListener {
    private AESCipherUtils aesCipherUtils = null;

    public LinePNPRecordEntityListener() {
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
        if (!(pc instanceof LinePNPRecordEntity)) {
            return;
        }
        LinePNPRecordEntity linePnpRecord = (LinePNPRecordEntity) pc;
        try {
            if (StringUtils.length(linePnpRecord.getCompanyUid()) <= 10) {
                // 長度小於10, 則表示未加密
                linePnpRecord.setCompanyUid(aesCipherUtils.encrypt(linePnpRecord.getCompanyUid()));
            }
        }
        catch (CryptException e) {
            throw new RuntimeException("error doing encryption for company UID", e);
        }
    }

    /**
     * 解密UID
     */
    @PostLoad
    @PostUpdate
    @PostPersist
    public void decryptUID(Object pc) {
        if (!(pc instanceof LinePNPRecordEntity)) {
            return;
        }
        LinePNPRecordEntity linePnpRecord = (LinePNPRecordEntity) pc;

        if (StringUtils.length(linePnpRecord.getCompanyUid()) > 10) {
            // 長度大於10才需解密
            linePnpRecord.setCompanyUid(aesCipherUtils.decryptQuietly(linePnpRecord.getCompanyUid()));
        }
    }
}