package com.tfb.aibank.biz.user.services.encryptasset;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.common.code.AIBankConstants;

// @formatter:off
/**
 * @(#)EncryptAssetService.java
 * 
 * <p>Description:提供「資源加密」相關服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/05, Evan
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
public class EncryptAssetService {

    public EncryptAssetService() {

    }

    /**
     * AES加密檔案資訊 產生
     * 
     * @param aesInfo
     * @return
     * @throws ActionException
     */
    public String genFileAesInfo(String aesInfo) throws ActionException {
        AESCipherUtils aesCipherUtils = null;
        SecretKeyProvider<?> provider;
        try {
            provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
            aesCipherUtils = new AESCipherUtils(provider);
            return aesCipherUtils.encrypt(aesInfo);
        }
        catch (CryptException e) {
            throw ExceptionUtils.getActionException(e);
        }

    }
}
