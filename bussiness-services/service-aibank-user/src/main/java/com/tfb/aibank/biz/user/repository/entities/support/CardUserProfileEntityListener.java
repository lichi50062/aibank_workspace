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

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.biz.user.repository.entities.CardUserProfileEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

// @formatter:off
/**
 * @(#)CardUserProfileEntityListener.java
 * 
 * <p>Description:信用卡專區會員資料 Entity Listener</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/06/02, Edward Tien	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class CardUserProfileEntityListener {
    private AESCipherUtils aesCipherUtils = null;

    public CardUserProfileEntityListener() {
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
        if (!(pc instanceof CardUserProfileEntity)) {
            return;
        }
        CardUserProfileEntity cardUser = (CardUserProfileEntity) pc;
        try {
            if (StringUtils.length(cardUser.getCardNo()) <= 16) {
                // 長度小於16, 則表示未加密
                cardUser.setCardNo(aesCipherUtils.encrypt(cardUser.getCardNo()));
            }
        }
        catch (Exception e) {
            throw new RuntimeException("error doing encryption for CardUserProfile CardNo", e);
        }

        try {
            if (StringUtils.length(cardUser.getCardCvv2()) <= 3) {
                // 長度小於3, 則表示未加密
                cardUser.setCardCvv2(aesCipherUtils.encrypt(cardUser.getCardCvv2()));
            }
        }
        catch (CryptException e) {
            throw new RuntimeException("error doing encryption for CardUserProfile CardCvv2", e);
        }

    }

    /**
     * 解密UID
     */
    @PostLoad
    @PostUpdate
    @PostPersist
    public void decryptUID(Object pc) {
        if (!(pc instanceof CardUserProfileEntity)) {
            return;
        }
        CardUserProfileEntity cardUser = (CardUserProfileEntity) pc;

        if (StringUtils.length(cardUser.getCardNo()) > 16) {
            // 長度大於10才需解密
            cardUser.setCardNo(aesCipherUtils.decryptQuietly(cardUser.getCardNo()));
        }

        if (StringUtils.length(cardUser.getCardCvv2()) > 16) {
            // 長度大於10才需解密
            cardUser.setCardCvv2(aesCipherUtils.decryptQuietly(cardUser.getCardCvv2()));
        }
    }
}
