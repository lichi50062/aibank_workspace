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
package com.tfb.aibank.common.util;

import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.tfb.aibank.common.code.AIBankConstants;

//@formatter:off
/**
* @(#)UserUtils.java
* 
* <p>Description:使用者身分證字號和誤別碼工具集</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/27, HankChan
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
public class UserUtils {
    protected static IBLog logger = IBLog.getLog(UserUtils.class);
    public final static String DEFAULT_CACHE_KEY = "default";
    private static AESCipherUtils aesCipherUtils;

    private UserUtils() {
        super();
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        aesCipherUtils = new AESCipherUtils(provider);
    }

    /**
     * <pre>
     * @Deprecated 回傳 rightPad(UID, 10, ' ') + 誤別碼
     * 'A123456789', '0' --> 'A1234567890'
     * '87178818', '1' --> '87178818 1'
     * </pre>
     * 
     * <p>
     * 改使用 AIBankUser.getCustIdWithDup()
     * </p>
     * 
     * @param companyUid
     * @param uidDup
     * @return
     */
    public static String getCompanyUidDup(String companyUid, String uidDup) {
        return StringUtils.rightPad(companyUid, 10, ' ') + StringUtils.defaultIfBlank(uidDup, "0");
    }

    /**
     * <p>
     * Deprecated 若重覆碼為0, 則取前10位,否則回覆11位:
     * </p>
     * <p>
     * 'A1234567890' --> 'A123456789'
     * </p>
     * <p>
     * '87178818 0' --> '87178818'
     * </p>
     * <p>
     * 'A1234567891' --> 'A1234567891'
     * </p>
     * <p>
     * '87178818 1' --> '87178818 1'
     * </p>
     * 
     * <p>
     * 改使用 AIBankUser.getCustIdAndCheckDup()
     * </p>
     * 
     * @param companyUid
     * @param uidDup
     * @return
     */
    public static String getTrimmedCompanyUidDup(String companyUid, String uidDup) {
        if (StringUtils.equals("0", uidDup)) {
            return companyUid;
        }
        else {
            return getCompanyUidDup(companyUid, uidDup);
        }
    }

    /**
     * 是否有誤別碼
     * 
     * @param uidDup
     * @return
     */
    public static boolean isUidDup(String uidDup) {
        if (StringUtils.equals("0", uidDup)) {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * 加密userKey當cacheKey
     * 
     * @param userKey
     * @param cacheKey
     * @return
     */
    public static String genCacheKeyByUserKey(Integer userKey) {
        String cacheKey = DEFAULT_CACHE_KEY;
        if (userKey == null)
            return cacheKey;
        try {
            cacheKey = aesCipherUtils.encrypt(userKey.toString());
        }
        catch (CryptException e) {
            logger.warn("userkey加密成cacheKey發生了問題", e);
        }
        return cacheKey;
    }

    /**
     * 將傳入字串的前2碼轉換成英文，01表示為A、02表示為B，以此類推
     * 
     * @param str
     *            長度11碼全數字的字串
     * @return
     */
    public static String convertToAlphabet(String str) {

        if (StringUtils.isBlank(str)) {
            return str;
        }

        if (str.toUpperCase().matches("^([0-9]{11})$")) {

            String alphabet = StringUtils.EMPTY;

            String firstTwoWords = str.substring(0, 2);

            switch (firstTwoWords) {
            case "01":
                alphabet = "A";
                break;
            case "02":
                alphabet = "B";
                break;
            case "03":
                alphabet = "C";
                break;
            case "04":
                alphabet = "D";
                break;
            case "05":
                alphabet = "E";
                break;
            case "06":
                alphabet = "F";
                break;
            case "07":
                alphabet = "G";
                break;
            case "08":
                alphabet = "H";
                break;
            case "09":
                alphabet = "I";
                break;
            case "10":
                alphabet = "J";
                break;
            case "11":
                alphabet = "K";
                break;
            case "12":
                alphabet = "L";
                break;
            case "13":
                alphabet = "M";
                break;
            case "14":
                alphabet = "N";
                break;
            case "15":
                alphabet = "O";
                break;
            case "16":
                alphabet = "P";
                break;
            case "17":
                alphabet = "Q";
                break;
            case "18":
                alphabet = "R";
                break;
            case "19":
                alphabet = "S";
                break;
            case "20":
                alphabet = "T";
                break;
            case "21":
                alphabet = "U";
                break;
            case "22":
                alphabet = "V";
                break;
            case "23":
                alphabet = "W";
                break;
            case "24":
                alphabet = "X";
                break;
            case "25":
                alphabet = "Y";
                break;
            case "26":
                alphabet = "Z";
                break;
            }

            String end = str.substring(2, str.length());
            return alphabet + end;
        }
        else {
            return str;
        }
    }

}
