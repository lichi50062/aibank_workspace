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
import com.tfb.aibank.biz.user.repository.entities.W8benInfoLogEntity;
import com.tfb.aibank.common.code.AIBankConstants;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

//@formatter:off
/**
 * @(#)W8benInfoLogEntityListener.java
 *
 * <p>Description: W-8BEN簽署畫面資料檔 Entity Listener</p>
 *
 * <p>Modify History:</p>
 * v1.0, 2024/05/01, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
//@formatter:on
public class W8benInfoLogEntityListener {
    private AESCipherUtils aesCipherUtils = null;

    public W8benInfoLogEntityListener() {
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
        if (!(pc instanceof W8benInfoLogEntity)) {
            return;
        }
        String column = StringUtils.EMPTY;
        W8benInfoLogEntity infoLog = (W8benInfoLogEntity) pc;
        try {
            column = "engName";
            infoLog.setEngName(aesCipherUtils.encrypt(infoLog.getEngName()));
            column = "busAddr";
            infoLog.setBusAddr(aesCipherUtils.encrypt(infoLog.getBusAddr()));
            column = "currAddr";
            infoLog.setCurrAddr(aesCipherUtils.encrypt(infoLog.getCurrAddr()));
            column = "ftin";
            infoLog.setFtin(aesCipherUtils.encrypt(infoLog.getFtin()));
        }
        catch (Exception e) {
            throw new RuntimeException("error doing encryption for W8benInfoLogEntity " + column, e);
        }

    }

    /**
     * 解密UID
     */
    @PostLoad
    @PostUpdate
    @PostPersist
    public void decryptUID(Object pc) {
        if (!(pc instanceof W8benInfoLogEntity)) {
            return;
        }
        W8benInfoLogEntity infoLog = (W8benInfoLogEntity) pc;
        infoLog.setEngName(encrypt(infoLog.getEngName(), "engName"));
        infoLog.setBusAddr(encrypt(infoLog.getBusAddr(), "busAddr"));
        infoLog.setCurrAddr(encrypt(infoLog.getCurrAddr(), "currAddr"));
        infoLog.setFtin(encrypt(infoLog.getFtin(), "ftin"));

    }

    private String encrypt(String plainText, String column) {
        try {
            return aesCipherUtils.encrypt(plainText);
        }
        catch (CryptException e) {
            throw new RuntimeException("error doing encryption for W8benInfoLogEntity " + column, e);
        }

    }
}
