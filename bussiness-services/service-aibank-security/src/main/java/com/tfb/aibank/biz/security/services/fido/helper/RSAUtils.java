/**
 * 
 */
package com.tfb.aibank.biz.security.services.fido.helper;

import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.ibmb.util.IBUtils;

/**
 * @author john
 *
 */
public class RSAUtils {

    private final static IBLog logger = IBLog.getLog(RSAUtils.class);

    private static PublicKey getPublicKey(String publicKxy) {
        PublicKey kxy = null;

        try {
            byte[] publicKxyBytes = Base64.getUrlDecoder().decode(publicKxy);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            kxy = factory.generatePublic(new X509EncodedKeySpec(publicKxyBytes));
            logger.debug("Current Public Key = " + kxy);
        }
        catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            logger.error("Public Key Invalid!", e);
            return null;
        }
        return kxy;

    }

    /**
     * RSA 加密 by Public Key
     * 
     * @param rawData
     * @param publicKxy
     * @return base64 RSA encrypted Data
     */
    public static String encrypy(String rawData, String publicKxy) {

        PublicKey PublicKey = getPublicKey(publicKxy);

        if (PublicKey == null)
            return null;

        Cipher cipher;
        try {
            String transformation = "RSA/ECB/PKCS1Padding";
            // #4504 Weak Encryption: Inadequate RSA Padding 待確認是否生效
            String accessRSAPadding = IBUtils.toDataModel(transformation, String.class);
            cipher = Cipher.getInstance(accessRSAPadding);
        }
        catch (NoSuchPaddingException | NoSuchAlgorithmException | ActionException e) {
            logger.error("Cipher generate fail!", e);
            return "";
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, PublicKey);
        }
        catch (InvalidKeyException e) {
            logger.error("cipher.init fail!", e);
            return null;
        }

        byte[] decryptedBytes;
        try {
            decryptedBytes = cipher.doFinal(rawData.getBytes());
        }
        catch (IllegalBlockSizeException | BadPaddingException e) {
            logger.error("cipher.doFinal fail!", e);
            return null;
        }

        return new String(Base64.getUrlEncoder().encode(decryptedBytes), Charset.defaultCharset()).split("=")[0];
    }
}