package com.tfb.aibank.biz.component.etrans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.ibm.tw.ibmb.util.ValidateParamUtils;

/**
 * 使用全景E2EE點對點加密系統, 對登入密碼進行加密
 */
@Component
public class E2EEUtils_AIBank {

    private static final int MAX_LINE_SIZE = 1000000;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    private IBLog logger = IBLog.getLog(E2EEUtils_AIBank.class);

    private final String CATEGORY = "AIBANK";

    // [各平台必要參數] 使用平台，各自依環境名稱自行定義
    private final String FUBON_APPLICATION_NAME = "AIBANK";

    // [各平台必要參數] 是否啟用E2EE加密機制
    private final String E2EE_AVALIBLE_FLAG = "E2EE_" + FUBON_APPLICATION_NAME + "_AVALIBLE_FLAG";
    private final String E2EE_UID_UUID_AVALIBLE_FLAG = "E2EE_" + FUBON_APPLICATION_NAME + "_UID_UUID_AVALIBLE_FLAG";

    // [各平台必要參數] 使用平台註冊在SS平台的名稱
    private final String E2EE_API_APPLICATION_NAME = "E2EE_" + FUBON_APPLICATION_NAME + "_API_APPLICATION_NAME";

    // [各平台必要參數] 使用平台註冊在SS平台的API_KEY < API_KEY 因為key太長, 所以拆成兩段 >
    private final String SYSTEM_PARAM_NAME_1 = "E2EE_" + FUBON_APPLICATION_NAME + "_API_KEY_1";
    private final String SYSTEM_PARAM_NAME_2 = "E2EE_" + FUBON_APPLICATION_NAME + "_API_KEY_2";

    // [各平台必要參數] 全景SS主機參數
    private final String E2EE_API_CLUSTER = "E2EE_" + FUBON_APPLICATION_NAME + "_API_CLUSTER";// 全景SS主機連線叢集
    private final String E2EE_API_RSA_KEY_NAME = "E2EE_" + FUBON_APPLICATION_NAME + "_API_RSA_KEY_NAME";// RSA公鑰名稱

    // 以下為PIB共用連線參數
    private final String E2EE_API_URL = "E2EE_" + FUBON_APPLICATION_NAME + "_API_URL";// 全景SS主機url
    private final String E2EE_API_KEY_NAME = "E2EE_API_KEY_NAME";// 登入加密金鑰名稱
    private final String E2EE_API_KEY_NAME_DB = "E2EE_API_KEY_NAME_DB";// DB登入加密金鑰名稱

    // 全景加密機器，PIB共用連線參數
    public String API_URL;
    public String CLUSTER;
    public String KEY_NAME;
    public String KEY_NAME_DB;
    public String RSA_KEY_NAME;

    // 全景API路徑
    public final String TOKEN_URI = "/token/login";// 取得Token
    public final String RSA_PUBLIC_KEY_URI = "/AsymmetricKey/publickey";// 取得RSA公鑰
    public final String CHECK_CC_URI = "/e2e/checkPasswordRule";// 檢核信用卡會員登入密碼
    public final String VALIDATE_GENERAL_URI = "/AsymmetricKey/TFB/checkpassword"; // 檢核一般會員登入密碼
    public final String ENCODE_URI = "/AsymmetricKey/TFB/encode";// 密碼轉換與加密，EB5556981為主，提供CP1047/CP937特殊加密邏輯
    public final String DECODE_RSA_URI = "/AsymmetricKey/decrypt";// 將前端PKCS-7 加密資料解密
    public final String ENCODE_URI_JSB = "/AsymmetricKey/JSB/encode";// 日盛密碼轉換與加密

    // HTTP 連線參數
    public final String HTTP_METHOD_GET = "GET";
    public final String HTTP_METHOD_POST = "POST";
    public final String HTTP_HEADER_CONTENTTYPE = "Content-Type";
    public final String HTTP_HEADER_AUTH = "Authorization";
    public final String HTTP_HEADER_AUTH_BEARER = "Bearer ";
    public final String HTTP_CONTENTTYPE_JSON_UTF8 = "application/json;charset=utf8";
    public final int HTTP_CONNECT_TIMEOUT = 10000;
    public final int HTTP_READ_TIMEOUT = 15000;

    // 資料型態參數
    public final int DATA_TYPE_NONE = 0;// 0: 沒加密，1: PKCS1，2: PKCS7
    public final int DATA_TYPE_PKCS1 = 1;// 0: 沒加密，1: PKCS1，2: PKCS7
    public final int DATA_TYPE_PKCS7 = 2;// 0: 沒加密，1: PKCS1，2: PKCS7

    // 全景密碼加密參數[/e2e/checkCodeRule]
    public final String SYS_KEY = KEY_NAME_DB;// EBANK.DATA.EBDBKEY
    public final String SYS_KEY_MODE = "32";// 32:CBC
    public final String PADDING_WITH_KEY_MODE_256 = "1";// CBC_ZEROPAD padding = 1 with sysKeyMode = 32
    public final String SYS_KEY_IV = "AAAAAAAAAAA=";// AAAAAAAAAAA ==>沒有iv

    // 全景密碼加密參數[/AsymmetricKey/TFB/encode]
    public final String SYS_KEY_EB5556981 = KEY_NAME;// WKKEY_1
    public final String SYS_KEY_TYPE_EB5556981 = "2";// 2
    public final String SYS_KEY_MODE_EB5556981 = "256";// 32:CBC, 256:CBC_ZEROPAD
    public final String SYS_KEY_IV_EB5556981 = "AAAAAAAAAAA=";

    // RSA公鑰時請重新下上util
    private String publicKey = null;
    // API連線時，所需token
    private TokenVo tokenVo = new TokenVo();
    // RSA公鑰wrapper
    public final String PUBLIC_KEY_WRAPPER = "-----BEGIN RSA PUBLIC KEY-----%s-----END RSA PUBLIC KEY-----";

    // 動態鍵盤
    public final Character[] NUMBER_ARR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    public final Character[] LETTER_ARR = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    private final int MAX_COUNT = 1000;

    public enum E2EEHsmType {
        /**
         * 日盛一般用戶登入，主機電文格式 請取用cipher
         */
        JSB_PWD_CP1047("CP1047"),

        /**
         * 日盛一般用戶登入，主機電文格式 請取用cipher
         */
        JSB_DB_3DES_UTF8("UTF8"),
        /**
         * 舊有一般用戶登入,主機電文 return EB5556981.CODE@EB5556981.ACNOID
         */
        PWD_EB5556981_CP1047("CP1047"),
        /**
         * 舊有一般用戶登入,主機電文EB5556981.CODE/EB5556981.NEWPXSSWXRD
         */
        PWD_MPVV_CP1047("CP1047"),
        /**
         * 一般用戶登入密碼or修改使用者代號/密碼,主機電文EB5556981.ACNOID or EB5556981.REMARK
         */
        PWD_3DES_CP1047("CP1047"),
        /**
         * WebATM重設帳號密碼 EB552170.CODE
         */
        PWD_MPVV_CP937("CP937"),
        /**
         * FINI(CEH)/CES/CST登入
         */
        DB_MD5_UTF8("UTF8"),
        /**
         * 信用卡會員登入/修改密碼 EXT/B2C/BFS/MBANK
         */
        DB_3DES_UTF8("UTF8"),
        /**
         * 手勢登入手勢密碼加密與DB驗證
         */
        ENCRYPT_3DES_UTF8("UTF8"),
        /**
         * 生物辨識公鑰解密
         */
        DECRYPT_3DES_UTF8("UTF8");

        String encoding;

        E2EEHsmType() {

        }

        E2EEHsmType(String encoding) {
            this.encoding = encoding;
        }

        public String getEncoding() {
            return encoding;
        }
    }

    public enum PassRuleMsg {

        SUCCESS(null, null),

        // 一般登入驗證邏輯
        PASSWORD_BLANK_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.LOGIN_RULE_NORMAL, PassRuleType.LOGIN_RULE_4PASS, PassRuleType.LOGIN_RULE_INITIAL, PassRuleType.CHANGE_UUID_RULE)), "001,002", null), PASSWORD_LENGTH_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.LOGIN_RULE_NORMAL, PassRuleType.LOGIN_RULE_4PASS, PassRuleType.LOGIN_RULE_INITIAL, PassRuleType.CHANGE_UUID_RULE)), "003,004", "length"), PASSWORD_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.LOGIN_RULE_4PASS, PassRuleType.LOGIN_RULE_INITIAL)), "005,006", "containNumber"), PASSWORD_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.LOGIN_RULE_NORMAL, PassRuleType.CHANGE_UUID_RULE)), "013,014", "containEngUp,containNumber"),

        // 變更密碼或忘記密碼
        OLD_PASSWORD_BLANK_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "001", null), NEW_PASSWORD_BLANK_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_FIRST_APPLY)), "002", null), OLD_PASSWORD_LENGTH_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_IS_FIRST_LOGIN, PassRuleType.CHECK_FIRST_APPLY)), "003", null), NEW_PASSWORD_LENGTH_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_FIRST_APPLY)), "004", null), OLD_PASSWORD_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "005", null), OLD_PASSWORD_ENG_NUM_ONLY_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_IS_FIRST_LOGIN)), "013", null), NEW_PASSWORD_ENG_NUM_ONLY_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_FIRST_APPLY)), "014", null), NEW_PASSWORD_ENG_NUM_MIX_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_IS_FIRST_LOGIN, PassRuleType.CHECK_FIRST_APPLY)), "008", null), COMPARE_UID_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_IS_FIRST_LOGIN, PassRuleType.CHECK_FIRST_APPLY)), "011", null), COMPARE_UUID_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHANGE_UUID_RULE, PassRuleType.CHECK_IS_FIRST_LOGIN, PassRuleType.CHECK_FIRST_APPLY)), "012", null), COMPARE_OLD_PASSWORD_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE)), "009", null), COMPARE_CONFIRM_PASSWORD_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_FIRST_APPLY)), "010", null),

        // 新密碼連續4碼英數&重複4碼英數字
        // 015:原網銀密碼不可有重複英數字
        // 016:新的網銀密碼不可有重複英數字
        // 017:原網銀密碼不可有連續英數字
        // 018:新的網銀密碼不可有連續英數字
        OLD_PASSWORD_REPEAT_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_FIRST_APPLY)), "015", null), NEW_PASSWORD_REPEAT_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_FIRST_APPLY)), "016", null), OLD_PASSWORD_CONSECUTIVE_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_FIRST_APPLY)), "017", null), NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR(new ArrayList<PassRuleType>(Arrays.asList(PassRuleType.CHANGE_PWD_RULE, PassRuleType.CHECK_FIRST_APPLY)), "018", null),

        UNDEFINED_ERR(null, null);

        // SUCCESS(null,null),
        // BLANK_ERR("001,002",null),
        // LENGTH_ERR("003,004","length"),
        // NUM_ERR("005,006",null),
        // ENG_NUM_ERR("007,008","containEngUp,containNumber"),
        // UID_ERR("011","compareString:uid"),
        // UUID_ERR("012","compareString:uuid"),
        // OLD_CODE_ERR("009","compareOldPass:oldPwd"),
        // CONFIRM_CODE_ERR("010",null),
        // UNDEFINED_ERR(null,null);

        // SUCCESS(),
        // LENGTH_ERR("length"),
        // ENG_NUM_ERR("containEngUp,containNumber"),
        // UID_ERR("compareString:uid"),
        // UUID_ERR("compareString:uuid"),
        // OLD_CODE_ERR("compareOldPass:oldPwd"),
        // CONFIRM_CODE_ERR,
        // UNDEFINED_ERR;

        List<PassRuleType> passRuleTypes;
        String failCodes;
        String invalidTagName;

        PassRuleMsg(List<PassRuleType> passRuleTypes, String failCodes, String invalidTagName) {
            this.passRuleTypes = passRuleTypes;
            this.failCodes = failCodes;
            this.invalidTagName = invalidTagName;
        }

        PassRuleMsg(String failCodes, String invalidTagName) {
            this.failCodes = failCodes;
            this.invalidTagName = invalidTagName;
        }

        public static PassRuleMsg getPassRuleErrMsg(PassRuleType checkRule, JsonObject rsbody) throws E2EEException {
            String failCode = rsbody.get("failCode").getAsString();
            String failMessage = rsbody.get("failMessage").getAsString();

            // logger.error("密碼驗證時發生錯誤" + failCode + ":" + failMessage);
            for (PassRuleMsg errType : PassRuleMsg.values()) {
                if (errType.passRuleTypes != null && errType.passRuleTypes.contains(checkRule)) {
                    for (String fail : errType.failCodes.split(",")) {
                        if (fail.equals(failCode)) {
                            return errType;
                        }
                    }
                }
            }
            throw new RuntimeException("密碼驗證時發生錯誤" + failCode + ":" + failMessage);
        }

        public PassRuleMsg getPassRuleErrMsg_OLD(JsonObject reason) throws E2EEException {
            String ruleName = reason.get("ruleName").getAsString();
            String param = reason.get("param").getAsString();
            if (StringUtils.isNotEmpty(param)) {
                ruleName = ruleName + ":" + param;
            }
            for (PassRuleMsg code : PassRuleMsg.values()) {
                for (String rulename : code.invalidTagName.split(",")) {
                    if (ruleName.equals(rulename)) {
                        return code;
                    }
                }
            }
            // logger.error("密碼驗證時發生錯誤" + ruleName + ":" + param);
            throw new RuntimeException("密碼驗證時發生錯誤" + ruleName + ":" + param);
        }
    }

    public enum PassRuleType {
        NO_RULE("[]"), LOGIN_RULE_NORMAL("[[{ \"oldIsBlank\": { \"flag\": true } }, { \"newIsBlank\": { \"flag\": false } }, { \"oldPwdLength\": { \"min\": 6 , \"max\": 16 } }, { \"newPwdLength\": { \"flag\": false , \"min\": 6 , \"max\": 16 } }, { \"oldisNumber\": { \"flag\": false } }, { \"newisNumber\": { \"flag\": false } }, { \"oldNonSpecialSymbol\": { \"flag\": true } }, { \"newNonSpecialSymbol\": { \"flag\": false } }, { \"oldAlphanumeric\": { \"flag\": false } }, { \"newAlphanumeric\": { \"flag\": false } }, { \"checkNewOldCompare\": { \"flag\": false } }, { \"checkNewComfireCompare\": { \"flag\": false } }, { \"checkNewUidContains\": { \"flag\": false } }, { \"checkNewUuidContains\": { \"flag\": false } }] ]"), LOGIN_RULE_INITIAL("[[{ \"oldIsBlank\": { \"flag\": true } }, { \"newIsBlank\": { \"flag\": false } }, { \"oldPwdLength\": { \"min\": 6 , \"max\": 6 } }, { \"newPwdLength\": { \"flag\": false , \"min\": 6 , \"max\": 16 } }, { \"oldisNumber\": { \"flag\": true } }, { \"newisNumber\": { \"flag\": false } }, { \"oldNonSpecialSymbol\": { \"flag\": true } }, { \"newNonSpecialSymbol\": { \"flag\": false } }, { \"oldAlphanumeric\": { \"flag\": false } }, { \"newAlphanumeric\": { \"flag\": false } }, { \"checkNewOldCompare\": { \"flag\": false } }, { \"checkNewComfireCompare\": { \"flag\": false } }, { \"checkNewUidContains\": { \"flag\": false } }, { \"checkNewUuidContains\": { \"flag\": false } }] ]"), LOGIN_RULE_4PASS("[[{ \"oldIsBlank\": { \"flag\": true } }, { \"newIsBlank\": { \"flag\": false } }, { \"oldPwdLength\": { \"min\": 4 , \"max\": 4 } }, { \"newPwdLength\": { \"flag\": false , \"min\": 4 , \"max\": 4 } }, { \"oldisNumber\": { \"flag\": true } }, { \"newisNumber\": { \"flag\": false } }, { \"oldNonSpecialSymbol\": { \"flag\": true } }, { \"newNonSpecialSymbol\": { \"flag\": false } }, { \"oldAlphanumeric\": { \"flag\": false } }, { \"newAlphanumeric\": { \"flag\": false } }, { \"checkNewOldCompare\": { \"flag\": false } }, { \"checkNewComfireCompare\": { \"flag\": false } }, { \"checkNewUidContains\": { \"flag\": false } }, { \"checkNewUuidContains\": { \"flag\": false } }] ]"),
        // 2021.11.12 新增檢核新密碼不能包含4碼連續或重複應數字
        CHANGE_PWD_RULE("[[{ \"oldIsBlank\": { \"flag\": true } }, { \"newIsBlank\": { \"flag\": true } }, { \"oldPwdLength\": { \"min\": 4 , \"max\": 16 } }, { \"newPwdLength\": { \"flag\": true , \"min\": 6 , \"max\": 16 } }, { \"oldisNumber\": { \"flag\": false } }, { \"newisNumber\": { \"flag\": false } }, { \"oldNonSpecialSymbol\": { \"flag\": true } }, { \"newNonSpecialSymbol\": { \"flag\": true } }, { \"oldAlphanumeric\": { \"flag\": false } }, { \"newAlphanumeric\": { \"flag\": true } }, {\"oldIsRepeat\": {\"flag\": false,\"num\" : 4}}, {\"newIsRepeat\": {\"flag\": true,\"num\" : 4}}, {\"oldIsSeq\": {\"flag\": false,\"num\" : 4}}, {\"newIsSeq\": {\"flag\": true,\"num\" : 4}}, { \"checkNewOldCompare\": { \"flag\": true } }, { \"checkNewComfireCompare\": { \"flag\": true } }, { \"checkNewUidContains\": { \"flag\": true } }, { \"checkNewUuidContains\": { \"flag\": true } } ] ]"),
        // CHANGE_PWD_RULE ("[[{ \"oldIsBlank\": { \"flag\": true } }, { \"newIsBlank\": { \"flag\": true } }, { \"oldPwdLength\": { \"min\": 4 , \"max\": 16 } }, { \"newPwdLength\": { \"flag\": true , \"min\": 6 , \"max\": 16 } }, { \"oldisNumber\": {
        // \"flag\": false } }, { \"newisNumber\": { \"flag\": false } }, { \"oldNonSpecialSymbol\": { \"flag\": true } }, { \"newNonSpecialSymbol\": { \"flag\": true } }, { \"oldAlphanumeric\": { \"flag\": false } }, { \"newAlphanumeric\": { \"flag\":
        // true } }, { \"checkNewOldCompare\": { \"flag\": true } }, { \"checkNewComfireCompare\": { \"flag\": true } }, { \"checkNewUidContains\": { \"flag\": true } }, { \"checkNewUuidContains\": { \"flag\": true } } ] ]"),
        CHANGE_UUID_RULE("[[{ \"oldIsBlank\": { \"flag\": true } }, { \"newIsBlank\": { \"flag\": true } }, { \"oldPwdLength\": {  \"min\": 4 , \"max\": 16 } }, { \"newPwdLength\": { \"flag\": false , \"min\": 4 , \"max\": 16 } }, { \"oldisNumber\": { \"flag\": false } }, { \"newisNumber\": { \"flag\": false } }, { \"oldNonSpecialSymbol\": { \"flag\": true } }, { \"newNonSpecialSymbol\": { \"flag\": true } }, { \"oldAlphanumeric\": { \"flag\": false } }, { \"newAlphanumeric\": { \"flag\": false } }, { \"checkNewOldCompare\": { \"flag\": false } }, { \"checkNewComfireCompare\": { \"flag\": false } }, { \"checkNewUidContains\": { \"flag\": false } }, { \"checkNewUuidContains\": { \"flag\": true } }] ]"), DB_LOGIN_RULE_NORMAL("[[{\"length\": {\"min\": 6,\"max\": 16}}],[{ \"containEngUp\": { \"least\": 1 } }], [{ \"containNumber\": { \"least\": 1 }}]]"), DB_CHANGE_PWD_RULE("[[{\"length\": {\"min\": 6,\"max\": 16}}],[{ \"containEngUp\": { \"least\": 1 } }], [{ \"containNumber\": { \"least\": 1 } }], [{\"compareOldPass\": [{ \"name\":\"oldPwd\" \"hashAlg\":[], \"encryption\":{ \"symKey\":\"%(symKey)\", \"symKeyMode\":\"%(symKeyMode)\", \"symKeyIv\":\"%(symKeyIv)\", \"prefix\":\"%(prefix)\", \"postfix\":\"%(postfix)\", },\"data\": \"%(oldPwd)\" } ] }], [{\"compareString\": [ { \"name\":\"uid\", \"data\":{ \"isEnc\":false, \"encData\":\"%(uid)\" }, \"mode\":2, \"ignoreCase\":true } ] }], [{\"compareString\": [ { \"name\":\"uuid\", \"data\":{ \"isEnc\":false, \"encData\":\"%(uuid)\" }, \"mode\":2, \"ignoreCase\":true } ] }]]"),
        // 登入後檢核是否符合規則, 密碼長度6~16、英數混雜、不可為身份證字號之一部分、不可為使用者代號之一部分, 若否則須強制變更使用者代號或密碼
        CHECK_IS_FIRST_LOGIN("[[{ \"oldIsBlank\": { \"flag\": false } }, { \"newIsBlank\": { \"flag\": false } }, { \"oldPwdLength\": { \"min\": 6 , \"max\": 16 } }, { \"newPwdLength\": { \"flag\": true , \"min\": 6 , \"max\": 16 } }, { \"oldisNumber\": { \"flag\": false } }, { \"newisNumber\": { \"flag\": false } }, { \"oldNonSpecialSymbol\": { \"flag\": true } }, { \"newNonSpecialSymbol\": { \"flag\": true } }, { \"oldAlphanumeric\": { \"flag\": true } }, { \"newAlphanumeric\": { \"flag\": true } }, { \"checkNewOldCompare\": { \"flag\": false } }, { \"checkNewComfireCompare\": { \"flag\": false } }, { \"checkNewUidContains\": { \"flag\": true } }, { \"checkNewUuidContains\": { \"flag\": true } } ] ]"),
        // 初次申請網銀檢核, 使用此rule時新舊密碼都傳入新密碼即可, 另外需要再比對全景encode之後的密碼和DB的舊密碼是否一致
        CHECK_FIRST_APPLY("[[{ \"oldIsBlank\": { \"flag\": false } }, { \"newIsBlank\": { \"flag\": true } }, { \"oldPwdLength\": { \"min\": 6 , \"max\": 16 } }, { \"newPwdLength\": { \"flag\": true , \"min\": 6 , \"max\": 16 } }, { \"oldisNumber\": { \"flag\": false } }, { \"newisNumber\": { \"flag\": false } }, { \"oldAlphanumeric\": { \"flag\": false } }, { \"newAlphanumeric\": { \"flag\": true } }, {\"newIsRepeat\": {\"flag\": true,\"num\" : 4}}, {\"oldIsSeq\": {\"flag\": false,\"num\" : 4}}, {\"newIsSeq\": {\"flag\": true,\"num\" : 4}}, { \"checkNewOldCompare\": { \"flag\": false } }, { \"checkNewComfireCompare\": { \"flag\": true } }, { \"checkNewUidContains\": { \"flag\": true } }, { \"checkNewUuidContains\": { \"flag\": true } } ] ]");
        // private String ALL_RULES = "[ { \"oldIsBlank\": { \"flag\": false } }, { \"newIsBlank\": { \"flag\": false } }, { \"oldPwdLength\": { \"flag\": false , \"min\": 6 , \"max\": 16 } }, { \"newPwdLength\": { \"flag\": false , \"min\": 6 , \"max\":
        // 16 } }, { \"oldisNumber\": { \"flag\": false } }, { \"newisNumber\": { \"flag\": false } }, { \"oldAlphanumeric\": { \"flag\": false } }, { \"newAlphanumeric\": { \"flag\": false } }, {\"oldIsRepeat\": {\"flag\": false,\"num\" : 4}},
        // {\"newIsRepeat\": {\"flag\": true,\"num\" : 4}}, {\"oldIsSeq\": {\"flag\": false,\"num\" : 4}}, {\"newIsSeq\": {\"flag\": true,\"num\" : 4}}, { \"checkNewOldCompare\": { \"flag\": false } }, { \"checkNewComfireCompare\": { \"flag\": false } }, {
        // \"checkNewUidContains\": { \"flag\": false } }, { \"checkNewUuidContains\": { \"flag\": false } } ]";

        String rules;

        PassRuleType(String rules) {
            this.rules = rules;
        }

        public String getRules() {
            return this.rules;
        }
    }

    private synchronized void init() {

        API_URL = systemParamCacheManager.getValue(CATEGORY, E2EE_API_URL); // E2EE_JSB_API_URL
        CLUSTER = systemParamCacheManager.getValue(CATEGORY, E2EE_API_CLUSTER); // ETOEE
        KEY_NAME = systemParamCacheManager.getValue(CATEGORY, E2EE_API_KEY_NAME); // EBANK.LOGIN.WKKEY
        KEY_NAME_DB = systemParamCacheManager.getValue(CATEGORY, E2EE_API_KEY_NAME_DB); // EBANK.DATA.EBDBKEY
        RSA_KEY_NAME = systemParamCacheManager.getValue(CATEGORY, E2EE_API_RSA_KEY_NAME); // TEST_RSA
    }

    /**
     * 取得給前端用的RSA公鑰
     * 
     * @return
     * @throws IOException
     */
    public String getPublicRSAKey() throws IOException {

        if (!isE2EEAvaliable()) {
            return StringUtils.EMPTY;
        }

        // 如果已經取得公鑰，直接回傳
        if (publicKey != null) {
            return String.format(PUBLIC_KEY_WRAPPER, publicKey);
        }

        init();

        HttpURLConnection con = null;
        // 初始化 http connection
        String urlstr = API_URL + RSA_PUBLIC_KEY_URI + "?cluster=" + CLUSTER + "&keyName=" + RSA_KEY_NAME;
        logger.info("[取的公鑰] url" + urlstr);
        URL url = new URL(urlstr);
        con = (HttpURLConnection) url.openConnection();
        // 設定 http method "POST"
        con.setRequestMethod(HTTP_METHOD_GET);
        // 設定 http header, ContentType為json, 編碼為utf-8
        con.setRequestProperty(HTTP_HEADER_CONTENTTYPE, HTTP_CONTENTTYPE_JSON_UTF8);

        con.setRequestProperty(HTTP_HEADER_AUTH, HTTP_HEADER_AUTH_BEARER + getToken());
        // 設定連線逾時，送到Server的時間
        con.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
        // 設定回應逾時，送到Server，但Server沒回, AP不想等, 想中斷的時間
        con.setReadTimeout(HTTP_READ_TIMEOUT);
        con.setDoOutput(true);

        InputStreamReader streamReader = null;
        // BufferedReader in = null;
        // 確認回傳HTTP status為200
        int status = con.getResponseCode();
        if (status == 200) {
            // 200 的話是讀取input stream
            streamReader = new InputStreamReader(con.getInputStream());
            // 將stream放入buffer reader
            // in = new BufferedReader(streamReader);
            String line;
            StringBuffer content = new StringBuffer(0);
            // 一次讀取一行並加入到content後方
            try (BufferedReader in = new BufferedReader(streamReader)) {
                int i = 0;
                while (true) {
                    i++;
                    // fortify: Denial of Service: readLine read MAX_LINE_SIZE
                    line = StringUtils.left(in.readLine(), MAX_LINE_SIZE);

                    if (line == null || i > MAX_COUNT) {
                        break;
                    }
                    // fortify: Denial of Service: StringBuilder - limit size
                    content.append(StringUtils.left(line, MAX_LINE_SIZE));
                }
            }

            // 輸出結果
            logger.info("E2EEUtils點對點加密, 取得公鑰成功...");
            logger.info(content.toString());

            // 解析結果
            JsonElement jelement = new JsonParser().parse(content.toString());
            JsonObject data = jelement.getAsJsonObject();
            data = data.getAsJsonObject("data");
            publicKey = data.get("pubKey").getAsString();

            if (StringUtils.isBlank(publicKey)) {
                throw new RuntimeException("E2EEUtils點對點加密, 取得公鑰失敗...");
            }

            return String.format(PUBLIC_KEY_WRAPPER, publicKey);
        }
        else {
            // 非200 讀取error stream
            streamReader = new InputStreamReader(con.getErrorStream());
            // 將stream放入buffer reader
            String line;
            StringBuffer content = new StringBuffer(0);
            // 一次讀取一行並加入到content後方
            try (BufferedReader in = new BufferedReader(streamReader)) {
                int i = 0;
                while (true) {
                    i++;
                    // fortify: Denial of Service: readLine read MAX_LINE_SIZE
                    line = StringUtils.left(in.readLine(), MAX_LINE_SIZE);

                    if (line == null || i > MAX_COUNT) {
                        break;
                    }
                    // fortify: Denial of Service: StringBuilder - limit size
                    content.append(StringUtils.left(line, MAX_LINE_SIZE));
                }
            }
            // 輸出結果
            logger.info("E2EEUtils點對點加密, 取得公鑰失敗...");
            logger.info(content.toString());
            throw new RuntimeException("E2EEUtils點對點加密, 取得公鑰失敗...");
        }
    }

    private String getToken() {
        logger.info("[取得token]讀取token：" + tokenVo);
        Calendar now = Calendar.getInstance();
        logger.info("[取得token]讀取token：now" + now + ", ExpiredTime" + tokenVo.getExpiredTime());
        logger.info("[取得token]讀取token：now.after(tokenVo.getExpiredTime())" + now.after(tokenVo.getExpiredTime()));
        if (tokenVo.getExpiredTime() != null && now.getTime().after(tokenVo.getExpiredTime())) {
            logger.info("[取得token]token已過期");
            // 過期了, 重新取得token
            // TODO refresh有問題
            loadTokenVo(false);
        }
        if (StringUtils.isBlank(tokenVo.getAccessToken())) {
            logger.info("[取得token]token為空，重新取得token");
            loadTokenVo(false);
        }
        return tokenVo.getAccessToken();
    }

    /**
     * 取得Token, 系統初始化時呼叫
     * 
     * @return
     * @throws Exception
     */
    private synchronized void loadTokenVo(Boolean isRefresh) {
        logger.info("[取得token]開始讀取/更新token");

        init();

        InputStreamReader streamReader = null;
        BufferedReader in = null;
        HttpURLConnection con = null;
        try {
            JsonObject tobeSignData = new JsonObject();
            tobeSignData.addProperty("applicationName", systemParamCacheManager.getValue(CATEGORY, E2EE_API_APPLICATION_NAME));
            tobeSignData.addProperty("signTime", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
            // JSONObject tobeSignData = new JSONObject();
            // tobeSignData.put("applicationName", APPLICATION_NAME);
            // tobeSignData.put("signTime", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
            logger.info("[取得token]準備簽章");
            String tobeSignStr = tobeSignData.toString();
            logger.info("[取得token] tobeSignStr" + tobeSignStr);
            byte[] signature = sign(tobeSignStr, getPrivateKey());

            logger.info("[取得token]開啟連線");
            /**
             * 取得token 使用上一步驟之簽章值
             **
             **/
            // 初始化 http connection
            URL url = new URL(API_URL + TOKEN_URI);
            con = (HttpURLConnection) url.openConnection();
            // 設定 http method "POST"
            con.setRequestMethod(HTTP_METHOD_POST);
            // 設定 http header, ContentType為json, 編碼為utf-8
            con.setRequestProperty(HTTP_HEADER_CONTENTTYPE, HTTP_CONTENTTYPE_JSON_UTF8);
            if (isRefresh) {
                // refresh token要帶Bearer Token
                con.setRequestProperty(HTTP_HEADER_AUTH, HTTP_HEADER_AUTH_BEARER + tokenVo.getAccessToken());
            }
            // 設定連線逾時，送到Server的時間
            con.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
            // 設定回應逾時，送到Server，但Server沒回, AP不想等, 想中斷的時間
            con.setReadTimeout(HTTP_READ_TIMEOUT);
            con.setDoOutput(true);

            logger.info("[取得token]header參數設定完畢");
            JsonObject bodyGetToken = new JsonObject();
            // bodyGetToken.addProperty("signature", Base64.getEncoder().encodeToString(signature));
            // JSONObject bodyGetToken = new JSONObject();
            // bodyGetToken.addProperty("signature", Base64.getEncoder().encodeToString(signature));
            if (!isRefresh) {
                // 第一次取得TOKEN
                bodyGetToken.addProperty("signature", Base64.encodeBase64String(signature));
                bodyGetToken.add("data", tobeSignData);
                // bodyGetToken.put("signature", Base64.encodeBase64String(signature));
                // bodyGetToken.put("data", tobeSignData);
            }
            else {
                // refresh token
                bodyGetToken.addProperty("accessToken", tokenVo.getAccessToken());
                bodyGetToken.addProperty("refreshToken", tokenVo.getRefreshToken());
                // bodyGetToken.put("accessToken", tokenVo.getAccessToken());
                // bodyGetToken.put("refreshToken", tokenVo.getRefreshToken());
            }

            logger.info("[取得token] bodyGetToken:");
            logger.info(bodyGetToken.toString());
            // #4504 0823 Unreleased Resource: Streams 調整
            try (OutputStream os = con.getOutputStream()) {
                os.write(bodyGetToken.toString().getBytes("UTF-8"));
            }

            // // 確認回傳HTTP status為200
            int status = con.getResponseCode();
            logger.info("[取得token] 有回應了");
            if (status == 200) {
                // 200 的話是讀取input stream
                streamReader = new InputStreamReader(con.getInputStream());
                // 將stream放入buffer reader
                in = new BufferedReader(streamReader);
                String line;
                StringBuffer content = new StringBuffer(0);
                // 一次讀取一行並加入到content後方
                try {
                    int i = 0;
                    while (true) {
                        i++;
                        // fortify: Denial of Service: readLine read MAX_LINE_SIZE
                        line = StringUtils.left(in.readLine(), MAX_LINE_SIZE);

                        if (line == null || i > MAX_COUNT) {
                            break;
                        }
                        // fortify: Denial of Service: StringBuilder - limit size
                        content.append(StringUtils.left(line, MAX_LINE_SIZE));
                    }
                }
                finally {
                    if (in != null) {
                        in.close();
                    }
                }
                // 輸出結果
                logger.info("[取得token] E2EEUtils點對點加密, 取得TOKEN成功...");
                logger.info(content.toString());

                // 解析結果

                // GetTokenResponseModel response = gson.fromJson(content.toString(), GetTokenResponseModel.class);
                // accessToken = response.getData().getAccessToken();

                JsonElement jelement = new JsonParser().parse(content.toString());
                JsonObject data = jelement.getAsJsonObject();
                data = data.getAsJsonObject("data");
                tokenVo.setAccessToken(data.get("accessToken").getAsString());
                tokenVo.setRefreshToken(data.get("refreshToken").getAsString());
                logger.info("tokenVo:" + tokenVo);
                // JSONObject response = JSONObject.parse(content.toString());
                // JSONObject data = (JSONObject)response.get("data");
                // tokenVo.setAccessToken(data.get("accessToken").toString());
                // tokenVo.setRefreshToken(data.get("refreshToken").toString());
                // 計算token過期的時間, 為了避免時間誤差, 所以早一分鐘先refreshToken
                Calendar expiredTime = Calendar.getInstance();
                expiredTime.add(Calendar.MINUTE, Integer.valueOf(data.get("expiresIn").toString()));
                expiredTime.add(Calendar.MINUTE, -1);
                tokenVo.setExpiredTime(expiredTime.getTime());
                logger.info("tokenVo setExpiredTime:" + tokenVo);
            }
            else {
                // 非200 讀取error stream
                streamReader = new InputStreamReader(con.getErrorStream());
                // 將stream放入buffer reader
                in = new BufferedReader(streamReader);
                String line;
                StringBuffer content = new StringBuffer(0);
                // 一次讀取一行並加入到content後方
                try {
                    int i = 0;
                    while (true) {
                        i++;
                        // fortify: Denial of Service: readLine read MAX_LINE_SIZE
                        line = StringUtils.left(in.readLine(), MAX_LINE_SIZE);

                        if (line == null || i > MAX_COUNT) {
                            break;
                        }
                        // fortify: Denial of Service: StringBuilder - limit size
                        content.append(StringUtils.left(line, MAX_LINE_SIZE));
                    }
                }
                finally {
                    if (in != null) {
                        in.close();
                    }
                }
                // 輸出結果
                logger.info("[取得token] E2EEUtils點對點加密, 取得TOKEN失敗");
                logger.info(content.toString());
                throw new RuntimeException("E2EEUtils點對點加密, 取得TOKEN失敗...");
            }
        }
        catch (IOException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e) {
            logger.warn("", e);
            logger.info("[取得token] E2EEUtils點對點加密, 取得TOKEN失敗..., 拋出Exception");
            throw new RuntimeException(e);
        }
        finally {
            // Close InputStreamReader
            try {
                if (streamReader != null)
                    streamReader.close();
            }
            catch (IOException e) {
                logger.warn("streamReader close failed", e);
            }
            // Close BufferedReader
            try {
                if (in != null)
                    in.close();
            }
            catch (IOException e) {
                logger.warn("BufferedReader close failed", e);
            }
            // Close HttpURLConnection
            if (con != null)
                con.disconnect();
        }
    }

    /**
     * 從系統參數內取得apiKey
     * 
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
        logger.info("[取得 apiKey]");
        String apiKey = systemParamCacheManager.getValue(CATEGORY, SYSTEM_PARAM_NAME_1) + systemParamCacheManager.getValue(CATEGORY, SYSTEM_PARAM_NAME_2);
        logger.info("[取得 apiKey]" + apiKey);
        logger.info("[取得 apiKey] PKCS8EncodedKeySpec");
        logger.info("[取得 apiKey] Base64" + Base64.decodeBase64(apiKey));
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decodeBase64(apiKey));
        logger.info("[取得 apiKey] spec:" + spec);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        logger.info("[取得 apiKey]generatePrivate:" + kf);
        PrivateKey pkey = kf.generatePrivate(spec);
        logger.info("[取得 apiKey]PrivateKey:" + pkey);
        return pkey;
    }

    /**
     * 全景後臺設定的RSA PrivateKey進行數位簽章
     * 
     * @param data
     * @param priKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws SignatureException
     */
    private byte[] sign(String data, PrivateKey priKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        logger.info("[sign] start");
        logger.info("[sign] rsa");
        Signature rsa = Signature.getInstance("SHA256withRSA");
        logger.info("[sign] rsa:" + rsa);
        rsa.initSign(priKey);
        logger.info("[sign] data:" + data);
        rsa.update(data.getBytes("UTF-8"));
        logger.info("[sign] updated");
        byte[] signature = rsa.sign();
        logger.info("[sign] signature:" + signature);
        return signature;
    }

    private class TokenVo {
        private String accessToken;
        private String refreshToken;
        // token過期時間
        private Date expiredTime;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public Date getExpiredTime() {
            return expiredTime;
        }

        public void setExpiredTime(Date expiredTime) {
            this.expiredTime = expiredTime;
        }

        @Override
        public String toString() {
            return "TokenVo [accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", expiredTime=" + expiredTime + "]";
        }

    }

    public static class E2EEException extends Exception {
        private final long serialVersionUID = 1L;

        public E2EEException(String message, Throwable o) {
            super(message, o);
        }

        public E2EEException(String message) {
            super(message);
        }
    }

    /**
     * E2EE是否啟用
     * 
     * @return true/ false
     */
    public Boolean isE2EEAvaliable() {
        Boolean E2EEFlag = Boolean.parseBoolean(systemParamCacheManager.getValue(CATEGORY, E2EE_AVALIBLE_FLAG));
        logger.info(E2EE_AVALIBLE_FLAG + "是否啟用=" + E2EEFlag);
        return E2EEFlag;
    }

    /**
     * 是否啟用E2E身分證、使用者代碼加密機制
     * 
     * @return true/ false
     */
    public Boolean isE2EEUidAndUuidAvaliable() {
        // Boolean E2EEUidFlag = Boolean.parseBoolean(PibUtils.getSystemProperty().getValue("PIB", E2EE_UID_UUID_AVALIBLE_FLAG));
        // Boolean E2EEFlag = isE2EEAvaliable();
        // logger.info(E2EE_UID_UUID_AVALIBLE_FLAG+"是否啟用="+E2EEUidFlag);
        // if(!E2EEFlag && E2EEUidFlag) {
        // logger.warn(E2EE_AVALIBLE_FLAG+"尚未啟用，需設定為啟用"+ E2EE_AVALIBLE_FLAG +"=true 則"+E2EE_UID_UUID_AVALIBLE_FLAG+"=true 才會生效");
        // }
        // return E2EEFlag && E2EEUidFlag;
        return isE2EEAvaliable();
    }

    /**
     * 解密動態鍵盤輸入資料 [由DynamicKeyboardUtils.decryptInput複製過來]
     * 
     * @param cipherText
     * @param numberList
     * @param charList
     * @return
     */
    public String dynamicKeyboardDecrypt(String cipherText, List<Character> numberList, List<Character> charList) {
        StringBuilder sb = new StringBuilder();
        String encPwdKeyStr = cipherText;
        for (int i = 0; i < encPwdKeyStr.length(); i++) {
            char encPwdKey = encPwdKeyStr.charAt(i);
            if (Character.isDigit(encPwdKey)) {
                sb.append(numberList.get(Character.getNumericValue(encPwdKey)));
            }
            else {

                sb.append(charList.get(Arrays.binarySearch(LETTER_ARR, Character.valueOf(encPwdKey))));
            }
        }
        return sb.toString();
    }

    /**
     * 含有舊有密碼檢核邏輯 [供網路銀行/EXT使用]
     * 
     * @param platform
     *            APP平台
     * @param deviceInfo
     *            裝置資訊
     * @param checkRule
     *            密碼檢核邏輯
     * @param uid
     *            身分證字號/統編
     * @param uuid
     *            使用者代碼
     * @param pwd
     *            原文「密碼/新密碼」/RSA加密後的「密碼/新密碼」
     * @param oldPwd
     *            原文「舊密碼」/RSA加密後的「舊密碼」[更改密碼時必填/更改使用者代碼時請帶入與pwd相同值即可]
     * @param confirmedPwd
     *            原文「新密碼的確認密碼」/RSA加密後的「新密碼的確認密碼」
     * @return
     * @throws E2EEException
     */
    public PassRuleMsg validatePasswordOld(PassRuleType checkRule, String uid, String uuid, String pwd, String oldPwd, String confirmedPwd /** List<Character> numberList, List<Character> charList */
    ) throws E2EEException {
        logger.info("使用檢核規則：" + checkRule);
        if (StringUtils.isNotBlank(pwd)) {
            pwd = pwd.toUpperCase();
        }
        if (StringUtils.isNotBlank(oldPwd)) {
            oldPwd = oldPwd.toUpperCase();
        }
        if (StringUtils.isNotBlank(confirmedPwd)) {
            confirmedPwd = confirmedPwd.toUpperCase();
        }

        // if (numberList != null && charList != null) {
        // if (StringUtils.isNotBlank(pwd)) {
        // pwd = dynamicKeyboardDecrypt(pwd, numberList, charList);
        // }
        // if (StringUtils.isNotBlank(oldPwd)) {
        // oldPwd = dynamicKeyboardDecrypt(oldPwd, numberList, charList);
        // }
        // if (StringUtils.isNotBlank(confirmedPwd)) {
        // confirmedPwd = dynamicKeyboardDecrypt(confirmedPwd, numberList, charList);
        // }
        // }
        // else if (numberList == null && charList != null || numberList != null && charList == null) {
        // throw new E2EEException("動態鍵盤輸入值有問題！");
        // }

        if (checkRule == PassRuleType.LOGIN_RULE_NORMAL || checkRule == PassRuleType.LOGIN_RULE_4PASS || checkRule == PassRuleType.DB_LOGIN_RULE_NORMAL) {
            // logger.info("LOGIN_RULE_NORMAL/LOGIN_RULE_4PASS!!! uid:"+uid+", uuid:"+uuid+", pwd:"+pwd+", oldPwd:"+oldPwd+", confirmedPwd:"+confirmedPwd);
            if (StringUtils.isBlank(pwd)) {
                logger.info("檢核失敗：LOGIN_PASSWORD_BLANK_ERR");
                return PassRuleMsg.PASSWORD_BLANK_ERR;
            }
            // 若長度小於6位，錯誤訊息「使用者密碼長度須為6~16碼；若您為首次登入，請先至網路銀行進行使用者密碼變更」
            if (pwd.length() < 6 || pwd.length() > 16) {
                logger.info("檢核失敗：LOGIN_PASSWORD_LENGTH_ERR");
                return PassRuleMsg.PASSWORD_LENGTH_ERR;
            }
            // 若輸入不為英數字，錯誤訊息顯示「使用者密碼限輸入英數字；若您為首次登入，請先至網路銀行進行使用者密碼變更」
            if (!pwd.matches("^[A-Z0-9]+$")) {
                logger.info("檢核失敗：LOGIN_PASSWORD_ENG_NUM_ERR");
                return PassRuleMsg.PASSWORD_ENG_NUM_ERR;
            }
            logger.info("檢核成功!!!");
            return PassRuleMsg.SUCCESS;
        }
        if (checkRule == PassRuleType.CHANGE_PWD_RULE || checkRule == PassRuleType.DB_CHANGE_PWD_RULE) {
            // logger.info("CHANGE_PWD_RULE/DB_CHANGE_PWD_RULE!!! uid:"+uid+", uuid:"+uuid+", pwd:"+pwd+", oldPwd:"+oldPwd+", confirmedPwd:"+confirmedPwd);
            if (StringUtils.isNotBlank(uid)) {
                uid = uid.toUpperCase();
            }
            if (StringUtils.isNotBlank(uuid)) {
                uuid = uuid.toUpperCase();
            }
            if (StringUtils.isNotBlank(oldPwd)) {
                oldPwd = oldPwd.toUpperCase();
            }
            if (StringUtils.isNotBlank(confirmedPwd)) {
                confirmedPwd = confirmedPwd.toUpperCase();
            }
            if (StringUtils.isBlank(pwd)) {
                logger.info("檢核失敗：NEW_PASSWORD_BLANK_ERR");
                return PassRuleMsg.NEW_PASSWORD_BLANK_ERR;
            }
            if (StringUtils.isBlank(oldPwd)) {
                logger.info("檢核失敗：OLD_PASSWORD_BLANK_ERR");
                return PassRuleMsg.OLD_PASSWORD_BLANK_ERR;
            }
            if (pwd.length() < 6 || pwd.length() > 16) {
                logger.info("檢核失敗：NEW_PASSWORD_LENGTH_ERR");
                return PassRuleMsg.NEW_PASSWORD_LENGTH_ERR;
            }
            if (!pwd.matches("^[A-Z0-9]+$")) {
                logger.info("檢核失敗：NEW_PASSWORD_ENG_NUM_ONLY_ERR");
                return PassRuleMsg.NEW_PASSWORD_ENG_NUM_ONLY_ERR;
            }
            if (pwd.toUpperCase().matches("^[A-Z]+$") || pwd.toUpperCase().matches("^[0-9]+$")) {
                logger.info("檢核失敗：NEW_PASSWORD_ENG_NUM_MIX_ERR");
                return PassRuleMsg.NEW_PASSWORD_ENG_NUM_MIX_ERR;
            }
            // 新密碼不能含有4碼相同的英文或數字
            // if (ValidatorUtils.checkTheSameChar(pwd, 4)) {
            // logger.info("檢核失敗：NEW_PASSWORD_REPEAT_ENG_NUM_ERR");
            // return PassRuleMsg.NEW_PASSWORD_REPEAT_ENG_NUM_ERR;
            // }
            // 新密碼不能含有4碼連續英文或數字
            // if (ValidatorUtils.checkConsecutive(pwd, 4)) {
            // logger.info("檢核失敗：NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR");
            // return PassRuleMsg.NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR;
            // }
            if (oldPwd.length() < 6 || oldPwd.length() > 16) {
                logger.info("檢核失敗：OLD_PASSWORD_LENGTH_ERR");
                return PassRuleMsg.OLD_PASSWORD_LENGTH_ERR;
            }
            if (!oldPwd.matches("^[A-Z0-9]+$")) {
                logger.info("檢核失敗：OLD_PASSWORD_ENG_NUM_ONLY_ERR");
                return PassRuleMsg.OLD_PASSWORD_ENG_NUM_ONLY_ERR;
            }
            // 新密碼不可為身分證字號(統一編號)
            if (StringUtils.equals(uid, pwd)) {
                logger.info("檢核失敗：COMPARE_UID_ERR");
                return PassRuleMsg.COMPARE_UID_ERR;
            }
            // 新密碼不可為身分證字號(統一編號)的子字串
            if (StringUtils.contains(uid, pwd)) {
                logger.info("檢核失敗：COMPARE_UID_ERR");
                return PassRuleMsg.COMPARE_UID_ERR;
            }
            // 新密碼不可為使用者代碼的子字串
            if (StringUtils.contains(uuid, pwd)) {
                logger.info("檢核失敗：COMPARE_UUID_ERR");
                return PassRuleMsg.COMPARE_UUID_ERR;
            }
            // 新密碼不可與原密碼相同
            if (StringUtils.equals(oldPwd, pwd)) {
                logger.info("檢核失敗：COMPARE_OLD_PASSWORD_ERR");
                return PassRuleMsg.COMPARE_OLD_PASSWORD_ERR;
            }
            // 兩次輸入之新密碼須相同
            if (!StringUtils.equals(pwd, confirmedPwd)) {
                logger.info("檢核失敗：COMPARE_CONFIRM_PASSWORD_ERR");
                return PassRuleMsg.COMPARE_CONFIRM_PASSWORD_ERR;
            }
            logger.info("檢核成功!!!");
            return PassRuleMsg.SUCCESS;
        }

        if (checkRule == PassRuleType.CHANGE_UUID_RULE) {
            // logger.info("CHANGE_PWD_RULE!!! uid:"+uid+", uuid:"+uuid+", pwd:"+pwd+", oldPwd:"+oldPwd+", confirmedPwd:"+confirmedPwd);
            if (StringUtils.isNotBlank(uuid)) {
                uuid = uuid.toUpperCase();
            }
            if (StringUtils.isBlank(pwd)) {
                logger.info("檢核失敗：BLANK_ERR");
                return PassRuleMsg.PASSWORD_BLANK_ERR;
            }
            // 新密碼不可為使用者代碼的子字串
            if (StringUtils.contains(uuid, pwd)) {
                logger.info("檢核失敗：UUID_ERR");
                return PassRuleMsg.COMPARE_UUID_ERR;
            }
            logger.info("檢核成功!!!");
            return PassRuleMsg.SUCCESS;
        }

        if (checkRule == PassRuleType.CHECK_FIRST_APPLY) {
            // logger.info("CHECK_FIRST_APPLY!!! uid:"+uid+", uuid:"+uuid+", pwd:"+pwd+", oldPwd:"+oldPwd+", confirmedPwd:"+confirmedPwd);
            if (StringUtils.isNotBlank(uid)) {
                uid = uid.toUpperCase();
            }
            if (StringUtils.isNotBlank(uuid)) {
                uuid = uuid.toUpperCase();
            }
            if (StringUtils.isNotBlank(confirmedPwd)) {
                confirmedPwd = confirmedPwd.toUpperCase();
            }
            if (StringUtils.isBlank(pwd)) {
                logger.info("檢核失敗：NEW_PASSWORD_BLANK_ERR");
                return PassRuleMsg.NEW_PASSWORD_BLANK_ERR;
            }
            if (pwd.length() < 6 || pwd.length() > 16) {
                logger.info("檢核失敗：NEW_PASSWORD_LENGTH_ERR");
                return PassRuleMsg.NEW_PASSWORD_LENGTH_ERR;
            }
            if (!pwd.matches("^[A-Z0-9]+$")) {
                logger.info("檢核失敗：NEW_PASSWORD_ENG_NUM_ONLY_ERR");
                return PassRuleMsg.NEW_PASSWORD_ENG_NUM_ONLY_ERR;
            }
            if (pwd.toUpperCase().matches("^[A-Z]+$") || pwd.toUpperCase().matches("^[0-9]+$")) {
                logger.info("檢核失敗：NEW_PASSWORD_ENG_NUM_MIX_ERR");
                return PassRuleMsg.NEW_PASSWORD_ENG_NUM_MIX_ERR;
            }
            // 新密碼不能含有4碼相同的英文或數字
            // if (ValidatorUtils.checkTheSameChar(pwd, 4)) {
            // logger.info("檢核失敗：NEW_PASSWORD_REPEAT_ENG_NUM_ERR");
            // return PassRuleMsg.NEW_PASSWORD_REPEAT_ENG_NUM_ERR;
            // }
            // 新密碼不能含有4碼連續英文或數字
            // if (ValidatorUtils.checkConsecutive(pwd, 4)) {
            // logger.info("檢核失敗：NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR");
            // return PassRuleMsg.NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR;
            // }
            // 新密碼不可為身分證字號(統一編號)的子字串
            if (StringUtils.contains(uid, pwd)) {
                logger.info("檢核失敗：COMPARE_UID_ERR");
                return PassRuleMsg.COMPARE_UID_ERR;
            }
            // 新密碼不可為使用者代碼的子字串
            if (StringUtils.contains(uuid, pwd)) {
                logger.info("檢核失敗：COMPARE_UUID_ERR");
                return PassRuleMsg.COMPARE_UUID_ERR;
            }
            // 兩次輸入之新密碼須相同
            if (!StringUtils.equals(pwd, confirmedPwd)) {
                logger.info("檢核失敗：COMPARE_CONFIRM_PASSWORD_ERR");
                return PassRuleMsg.COMPARE_CONFIRM_PASSWORD_ERR;
            }
            logger.info("檢核成功!!!");
            return PassRuleMsg.SUCCESS;
        }
        // Dead Code: Expression is Always false 外部傳入的條件，未判斷前未知
        if (checkRule == PassRuleType.NO_RULE) {
            return PassRuleMsg.SUCCESS;
        }
        logger.error("未設定PassRuleType!!!");
        return PassRuleMsg.UNDEFINED_ERR;
    }

    /**
     * 含有舊有密碼檢核邏輯 [供網路銀行/EXT使用]
     * 
     * @param platform
     *            APP平台
     * @param deviceInfo
     *            裝置資訊
     * @param checkRule
     *            密碼檢核邏輯
     * @param uid
     *            身分證字號/統編
     * @param uuid
     *            使用者代碼
     * @param pwd
     *            原文「密碼/新密碼」/RSA加密後的「密碼/新密碼」
     * @param oldPwd
     *            原文「舊密碼」/RSA加密後的「舊密碼」[更改密碼時必填/更改使用者代碼時請帶入與pwd相同值即可]
     * @param confirmedPwd
     *            原文「新密碼的確認密碼」/RSA加密後的「新密碼的確認密碼」
     * @return
     * @throws E2EEException
     */
    public PassRuleMsg validateWithPasswordRule(PassRuleType checkRule, String uid, String uuid, String pwd, String oldPwd, String confirmedPwd, List<Character> numberList, List<Character> charList) throws E2EEException {

        if (!isE2EEAvaliable()) {
            return validatePasswordOld(checkRule, uid, uuid, pwd, oldPwd, confirmedPwd);
        }

        if (checkRule == PassRuleType.DB_LOGIN_RULE_NORMAL) {
            JsonObject rqbody = getRqBody(pwd, null, true);
            rqbody.add("returnPassProcess", get3DESReturnPassProcess(uid + uuid, ""));
            rqbody.add("passRule", getPassRule(checkRule, uid, uuid, pwd, oldPwd, confirmedPwd));
            rqbody.addProperty("rtnEncoding", 2);// 2:Hex
            JsonObject rsbody = postE2EE(CHECK_CC_URI, rqbody);
            String errorCode = rsbody == null ? "" : rsbody.get("errorCode").getAsString();

            if (errorCode == null) {
                throw new E2EEException("E2EE 錯誤代碼為空");
            }

            if ("0".equals(errorCode)) {
                return PassRuleMsg.SUCCESS;
            }
            // fortify : Redundant Null Check
            else if ("66216".equals(errorCode) && rsbody != null) {
                PassRuleMsg errMsg = PassRuleMsg.UNDEFINED_ERR.getPassRuleErrMsg_OLD(rsbody.getAsJsonObject("reason"));
                return errMsg;
            }
            else {
                throw new E2EEException(errorCode);
            }
        }
        else {
            String keycode = genKeycodeFormat(numberList, charList);// 動態鍵盤帶入轉換參數

            JsonObject rqbody = getValidateRqBody(pwd, oldPwd, confirmedPwd, uid, uuid, keycode);

            rqbody.add("passRule", getValidatePassRule(checkRule));

            JsonObject rsbody = postE2EE(VALIDATE_GENERAL_URI, rqbody);// VALIDATE_GENERAL_URI
            String errorCode = rsbody == null ? "" : rsbody.get("errorCode").getAsString();

            if (errorCode == null) {
                throw new E2EEException("E2EE 錯誤代碼為空");
            }

            if ("0".equals(errorCode)) {
                return PassRuleMsg.SUCCESS;
            }
            else if ("66216".equals(errorCode)) {
                PassRuleMsg errMsg = PassRuleMsg.getPassRuleErrMsg(checkRule, rsbody);
                if (PassRuleType.LOGIN_RULE_NORMAL == checkRule && PassRuleMsg.PASSWORD_ENG_NUM_ERR == errMsg) {
                    // 確認是否為首次登入
                    logger.info("驗證是否為首次登入!!");
                    String keycode2 = genKeycodeFormat(numberList, charList);// 動態鍵盤帶入轉換參數
                    JsonObject rqbody2 = getValidateRqBody(pwd, oldPwd, confirmedPwd, uid, uuid, keycode2);
                    rqbody2.add("passRule", getValidatePassRule(PassRuleType.LOGIN_RULE_INITIAL));
                    JsonObject rsbody2 = postE2EE(VALIDATE_GENERAL_URI, rqbody2);// VALIDATE_GENERAL_URI
                    String errorCode2 = rsbody2.get("errorCode").getAsString();
                    if ("0".equals(errorCode2)) {
                        return PassRuleMsg.SUCCESS;
                    }
                }
                return errMsg;
            }
            else {
                throw new E2EEException(errorCode);
            }
        }
    }

    // 取的動態密碼轉換格式
    private String genKeycodeFormat(List<Character> numberList, List<Character> charList) {
        if (numberList != null && charList != null) {
            String num = StringUtils.join(numberList, null);
            String aph = StringUtils.join(charList, null);
            return num + "@" + aph;
        }
        return null;
    }

    /**
     * 含有舊有密碼加密邏輯 [供B2C/EXT使用]
     * 
     * @param platform
     *            APP平台
     * @param deviceInfo
     *            裝置資訊
     * @param hsmType
     *            加密類型
     * @param uid
     *            身分證字號/統編
     * @param uuid
     *            使用者代碼
     * @param pwd
     *            原文「密碼」/前端RSA加密後的密碼
     * @param numberList
     *            數字對應陣列[動態鍵盤時需傳入]
     * @param charList
     *            字元對應陣列[動態鍵盤時需傳入]
     * @return
     * @throws E2EEException
     * @throws ActionException
     */
    public String encodeWithPasswordRule(E2EEHsmType hsmType, String uid, String uuid, String pwd, List<Character> numberList, List<Character> charList) throws E2EEException, ActionException {

        // String[] pwdarr = pwd.split("@");
        JsonObject rsbody = null;
        if (E2EEHsmType.PWD_EB5556981_CP1047 == hsmType || E2EEHsmType.PWD_MPVV_CP1047 == hsmType || E2EEHsmType.PWD_3DES_CP1047 == hsmType || E2EEHsmType.PWD_MPVV_CP937 == hsmType || E2EEHsmType.DB_3DES_UTF8 == hsmType || E2EEHsmType.DB_MD5_UTF8 == hsmType) {
            JsonObject rqbody = getRqBody(pwd, genKeycodeFormat(numberList, charList), null);
            // 將呼叫新的ＡＰＩ，可以提供檢核
            String prefix = uid;
            String acnoidPrefix = null;
            // 信用卡用戶，prefix比較特別
            if (E2EEHsmType.DB_3DES_UTF8 == hsmType || E2EEHsmType.DB_MD5_UTF8 == hsmType) {
                acnoidPrefix = uid + uuid;
            }
            rqbody.add("returnPassProcess", getReturnPassProcessEB5556981(hsmType, prefix, null, acnoidPrefix, null));
            logger.info(hsmType.name() + rqbody);
            rsbody = postE2EE(ENCODE_URI, rqbody);
        }
        // 舊的API, 沒有動態鍵盤才能呼叫這個
        // else if(E2EEHsmType.DB_3DES_UTF8 == hsmType){
        // JsonObject rqbody = getRqBody(pwd, genKeycodeFormat(numberList, charList), true);
        // rqbody.add("returnPassProcess", get3DESReturnPassProcess(uid+uuid,""));
        // rqbody.add("passRule", getNoPassRule());
        // rqbody.addProperty("rtnEncoding", 2);//2:Hex
        // rsbody = postE2EE(CHECK_PWD_URI, rqbody);
        // }
        // else if(E2EEHsmType.DB_MD5_UTF8 == hsmType){
        // JsonObject rqbody = getRqBody(pwd, genKeycodeFormat(numberList, charList), true);
        // rqbody.add("returnPassProcess", getMD5SReturnPassProcess(uid+uuid,""));
        // rqbody.add("passRule", getNoPassRule());
        // rqbody.addProperty("rtnEncoding", 2);//2:Hex
        // logger.info(hsmType.name()+rqbody);
        // rsbody = postE2EE(CHECK_PWD_URI, rqbody);
        // }

        String errorCode = rsbody == null ? "" : rsbody.get("errorCode").getAsString();

        if (errorCode == null) {
            throw new E2EEException("E2EE 錯誤代碼為空");
        }

        /*
         * 回應代碼：回應代碼說明 0:成功。data有值 66001:服務無預期錯誤，請洽開發人員 66002:缺少必要參數 66003:不合法的參數 66010:登入資訊驗證失敗 66216:密碼檢核失敗，reason有值，詳細理由請參考 reason 參數區塊
         */
        // fortify: null deference
        if (rsbody != null && "0".equals(errorCode)) {
            JsonObject data = rsbody.getAsJsonObject("data");
            String encPass = "";
            if (E2EEHsmType.PWD_EB5556981_CP1047 == hsmType) {
                encPass = data.get("encodecipher").getAsString() + "@" + data.get("cipher").getAsString();
            }
            else if (E2EEHsmType.PWD_MPVV_CP1047 == hsmType || E2EEHsmType.PWD_MPVV_CP937 == hsmType) {
                encPass = data.get("encodecipher").getAsString();
            }
            else if (E2EEHsmType.PWD_3DES_CP1047 == hsmType) {
                encPass = data.get("cipher").getAsString();
            }
            else if (E2EEHsmType.DB_3DES_UTF8 == hsmType) {
                encPass = data.get("cipher").getAsString();
                encPass = encPass.toUpperCase();// 需另外轉大寫才與DB一致
            }
            else if (E2EEHsmType.DB_MD5_UTF8 == hsmType) {
                encPass = data.get("hashcipher").getAsString();
            }
            return encPass;
        }
        else {
            throw new E2EEException(errorCode);
        }
    }

    /**
     * 解密身分證字號相關RSA密文資訊
     * 
     * @param encryptText
     *            uid/uuid密文
     * @return 解密後資料
     * @throws E2EEException
     */
    public String decryptRSAEncodedText(String encryptText) throws E2EEException {

        init();
        // 如果是空白則不做解密處理
        if (StringUtils.isBlank(encryptText)) {
            return encryptText;
        }

        if (!isE2EEUidAndUuidAvaliable()) {
            logger.info("未啟用 UID / UUID加解密");
            logger.info("回傳傳入資料：" + encryptText);
            // 舊有的邏輯會針對身分證字號與使用者代碼全變為大寫
            return encryptText.toUpperCase();
        }

        logger.info("已啟用 UID / UUID加解密");
        logger.info("解密前資料：" + encryptText);

        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("cluster", CLUSTER);
        rqBody.addProperty("keyName", RSA_KEY_NAME);
        rqBody.addProperty("cipher", encryptText);
        rqBody.addProperty("rtnEncoding", "UTF-8");
        rqBody.addProperty("cipherType", 2);
        JsonObject rsbody = postE2EE(DECODE_RSA_URI, rqBody);

        String errorCode = rsbody == null ? "" : rsbody.get("errorCode").getAsString();
        // fortify: Redundant Null Check
        if (rsbody != null && "0".equals(errorCode)) {
            JsonObject data = rsbody.getAsJsonObject("data");
            String plainText = data.get("plain").getAsString();

            return plainText;
        }
        else {
            // fortify : Redundant Null Check
            String errorMessage = rsbody != null ? rsbody.get("errorMessage").getAsString() : "";
            logger.error("在解密Uid/Uuid時發生錯誤： 錯誤代碼：" + errorCode + ", 錯誤描述：" + errorMessage);
            throw new E2EEException("E2EE 錯誤代碼：" + errorCode + ", 錯誤描述：" + errorMessage);
        }
    }

    /**
     * 由TFB_BIZ_COMPONENT的ValidatorUtils.checkSecuirtyRules複製過來 登入後判斷是否需要修改密碼時使用
     * 
     * 安控規範 1.「使用者代碼」需為 6 至 10 位，「網路銀行密碼」需為 6 至 16 位（英文字一律視為大寫） 2.「使用者代碼」及「網路銀行密碼」須包括英文及數字；不得為相同的英數字、連續英文字或連號數字。(需英數字混雜) 3.使用者代碼及網路銀行密碼設定，請勿使用個人顯性資料（如生日、身份證、車號、電話號碼、帳號及相關資料號碼），以策安全。 (無法檢核) 4.「使用者代碼」或「網路銀行密碼」不得與「身份證字號╱統一編號」全部或部份重複。
     * 5.「網路銀行密碼」不得與「使用者代碼」全部或部份重複。
     *
     * @param userId
     * @param userPwd
     *            return 0 : ok, 10: 使用者代碼不符, 1 : 使用者密碼不符, 11: 皆不符
     */
    public int checkSecuirtyRules(String uid, String userId, String userPwd) {

        logger.info("使用檢核規則：checkSecuirtyRulesWithAppVersion");
        // if (!isE2EEAvaliable()) {
        // return ValidatorUtils.checkSecuirtyRules(uid, userId, userPwd);
        // }

        boolean userIdOk = true;
        boolean userPwdOk = true;
        // 1.「使用者代碼」需為 6 至 10 位，「網路銀行密碼」需為 6 至 16 位（英文字一律視為大寫）
        int length = StringUtils.length(userId);
        if (length < 6 && length > 10) {
            userIdOk = false;
        }
        // 因為全景的API只能驗證"新密碼是否為身份證字號或使用者代號一部分", 所以舊密碼userPwd也要塞到新密碼欄位才能驗證"舊密碼是否為身份證字號或使用者代號一部分"
        try {
            PassRuleMsg passRuleMsg = validateWithPasswordRule(PassRuleType.CHECK_IS_FIRST_LOGIN, uid, userId, userPwd, userPwd, null, null, null);
            if (passRuleMsg != PassRuleMsg.SUCCESS) {
                logger.error(passRuleMsg.failCodes);
                userPwdOk = false;
            }
        }
        catch (E2EEException e) {
            logger.error(e.getMessage(), e);
            userPwdOk = false;
        }
        uid = StringUtils.upperCase(uid);
        userId = StringUtils.upperCase(userId);
        // 需為 A-Z 0-9, 且不是只有 A-Z & 也不是只有 0-9
        userIdOk &= userId.matches("^[A-Z0-9]+$") && !userId.matches("^[A-Z]+$") && !userId.matches("^[0-9]+$");
        // 「使用者代碼」或「網路銀行密碼」不得與「身份證字號╱統一編號」全部或部份重複。
        userIdOk &= !StringUtils.contains(uid, userId);
        // 「網路銀行密碼」不得與「使用者代碼」全部或部份重複。
        return (userIdOk && userPwdOk) ? 0 : ((userIdOk ? 0 : 10) + (userPwdOk ? 0 : 1));
    }

    private JsonObject getReturnPassProcessEB5556981(E2EEHsmType hsmType, String pwdPrefix, String pwdPostfix, String acnoidPrefix, String acnoidPostfix) {
        String template = "{ \"encryption\":{ \"symKey\":\"%(symKey)\", \"symkeyType\":\"%(symkeyType)\", \"symKeyMode\":%(symKeyMode), \"symKeyIv\":\"%(symKeyIv)\", \"prefix\":\"%(prefix)\", \"postfix\":\"%(postfix)\", \"encPrefix\":\"%(encPrefix)\", \"encPostfix\":\"%(encPostfix)\", \"align\":\"%(align)\", \"alignChar\":\"%(alignChar)\", \"dataLength\": 16, \"encodeStringType\": \"%(encodeStringType)\" }}";
        Map<String, String> values = new HashMap<String, String>();
        /*
         * "returnPassProcess":{ "encryption":{ "symKey":"TESTKEY", "symkeyType": 2, "symKeyMode":32, "symKeyIv":"", "prefix":"A120980288", "postfix":"", "encPrefix":"A120980288TEST11", "encPostfix":"", "align":2, "alignChar":" ", "dataLength": 16,
         * "encodeStringType": "CP1047" }
         */
        if (hsmType == E2EEHsmType.DB_3DES_UTF8 || hsmType == E2EEHsmType.DB_MD5_UTF8) {
            // DB_3DES_UTF8, 信用卡會員
            // DB_MD5_UTF8, FEBO 一般用戶, 這些參數沒用, 但全景API會檢查必填, 所以隨便塞
            values.put("symKey", SYS_KEY);// TESTKEY
            values.put("align", "0");
            values.put("alignChar", "");
        }
        else {
            // 一般會員
            values.put("symKey", SYS_KEY_EB5556981);// TESTKEY
            values.put("align", "2");
            values.put("alignChar", " ");
        }
        values.put("symkeyType", SYS_KEY_TYPE_EB5556981);// 2
        values.put("symKeyMode", SYS_KEY_MODE_EB5556981);// CBC:32
        values.put("symKeyIv", SYS_KEY_IV_EB5556981);// AAAAAAAAAAA
        values.put("prefix", StringUtils.defaultString(pwdPrefix, ""));// uid
        values.put("postfix", StringUtils.defaultString(pwdPostfix, ""));
        values.put("encPrefix", StringUtils.defaultString(acnoidPrefix, ""));// uid+uuid
        values.put("encPostfix", StringUtils.defaultString(acnoidPostfix, ""));
        if (hsmType == E2EEHsmType.DB_MD5_UTF8) {
            values.put("prefix", StringUtils.defaultString(acnoidPrefix, ""));// uid
            values.put("postfix", StringUtils.defaultString(acnoidPostfix, ""));
        }

        values.put("encodeStringType", hsmType.getEncoding());// CP1047/CP937/UTF8 TODO

        JsonElement jelement = new JsonParser().parse(replacesAllValues(template, values));
        JsonObject jsonObject = jelement.getAsJsonObject();
        if (hsmType == E2EEHsmType.DB_MD5_UTF8) {
            // DB_MD5_UTF8, FEBO 一般用戶
            jsonObject.getAsJsonObject("encryption").addProperty("hashAlg", "1");
        }
        return jsonObject;
    }

    private JsonObject get3DESReturnPassProcess(String prefix, String postfix) {
        String template = "{ \"hash\":[], \"encryption\":{ \"symKey\":\"%(symKey)\", \"symKeyMode\":%(symKeyMode), \"padding\":%(padding), \"symKeyType\": 2, \"symKeyIv\":\"%(symKeyIv)\", \"prefix\":\"%(prefix)\", \"postfix\":\"%(postfix)\" } }";
        Map<String, String> values = new HashMap<String, String>();
        /*
         * "returnPassProcess":{ "hash":[], >> 沒有用到hash 所以帶這個 "encryption":{ "symKey":<加密金鑰名稱>, >> 指定加密金鑰名字，先暫訂TFB_2DES "symKeyMode":<加密模式>, >> CBC : 32 (2DES) "symKeyIv":<加密IV值>, >> 帶入 AAAAAAAAAAA= 不做IV "padding":<padding-mode> >> 帶入 ： 1 ZERO_PADDING
         * "prefix":<密碼前綴資料>, >> 帶入 uid+uuid 字串 "postfix":<密碼後綴資料>, >> 帶"" }, "rtnEncoding": <回傳格式> >>回傳資料編碼方式，1: Base64，2: Hex }
         */

        values.put("symKey", SYS_KEY);// TFB_2DES
        values.put("symKeyMode", SYS_KEY_MODE);// CBC:32
        values.put("padding", PADDING_WITH_KEY_MODE_256);// CBC_ZEROPAD
        values.put("symKeyIv", SYS_KEY_IV);// AAAAAAAAAAA
        values.put("prefix", StringUtils.defaultString(prefix, ""));// uid
        values.put("postfix", StringUtils.defaultString(postfix, ""));
        // values.put("rtnEncoding", "2");//1: Base64，2: Hex
        JsonElement jelement = new JsonParser().parse(replacesAllValues(template, values));
        return jelement.getAsJsonObject();
    }

    private JsonObject getMD5SReturnPassProcess(String prefix, String postfix) {
        String template = "{\"hash\":[{\"hashAlg\":\"%(hashAlg)\", \"prefix\":\"%(prefix)\", \"postfix\":\"%(postfix)\"}], \"encryption\":{ \"symKey\":\"\", \"symKeyMode\":\"\", \"padding\":\"\", \"symKeyType\": \"\", \"symKeyIv\":\"\", \"prefix\":\"\", \"postfix\":\"\" } }";
        Map<String, String> values = new HashMap<String, String>();
        /*
         * "returnPassProcess":{ "encryption":{ "hashAlg": <雜湊演算法>, "prefix":<前綴資料>, "postfix":<後綴資料> }, }
         */
        values.put("hashAlg", "1");// MD5
        values.put("prefix", StringUtils.defaultString(prefix, ""));// uid+uuid
        values.put("postfix", StringUtils.defaultString(postfix, ""));
        JsonElement jelement = new JsonParser().parse(replacesAllValues(template, values));
        return jelement.getAsJsonObject();
    }

    private JsonArray getPassRule(PassRuleType ruletype, String uid, String uuid, String pwd, String oldPwd, String confirmedPwd) {
        String template = ruletype.getRules();
        Map<String, String> values = new HashMap<String, String>();
        if (ruletype == PassRuleType.DB_CHANGE_PWD_RULE) {
            // symKey, symKeyMode, symKeyIv, prefix, postfix, oldPwd, uid, uuid
            values.put("symKey", SYS_KEY);
            values.put("symKeyMode", SYS_KEY_MODE);
            values.put("symKeyIv", SYS_KEY_IV);
            values.put("prefix", "");
            values.put("postfix", "");
            values.put("oldPwd", oldPwd);
            values.put("uid", uid);
            values.put("uuid", uuid);
        }
        else if (ruletype == PassRuleType.CHANGE_UUID_RULE) {
            // values.put("symKey", SYS_KEY_EB5556981);//TESTKEY
            // //values.put("symkeyType", SYS_KEY_TYPE_EB5556981);//2
            // values.put("symKeyMode", SYS_KEY_MODE_EB5556981);//CBC:32
            // values.put("symKeyIv", SYS_KEY_IV_EB5556981);//AAAAAAAAAAA

            // values.put("prefix", prefix);
            // values.put("postfix", postfix);
            // values.put("oldPwd", oldPwd);
            // values.put("uuid", uuid);
        }
        JsonElement jelement = new JsonParser().parse(replacesAllValues(template, values));
        return jelement.getAsJsonArray();
    }

    private static JsonArray getValidatePassRule(PassRuleType ruletype) {
        JsonElement jelement = new JsonParser().parse(ruletype.getRules());
        return jelement.getAsJsonArray();
    }

    private static JsonArray getNoPassRule() {
        JsonElement jelement = new JsonParser().parse(PassRuleType.NO_RULE.getRules());
        return jelement.getAsJsonArray();
    }

    // 取得密碼驗證rqbody
    private JsonObject getValidateRqBody(String rsaEncodedPwd, String rsaEncodedOldPwd, String rsaEncodedConfirmPwd, String uid, String uuid, String keycode) {

        JsonObject bodyChkPwdRule = new JsonObject();

        bodyChkPwdRule.addProperty("cluster", CLUSTER);

        JsonObject encryptionData = new JsonObject();

        // 資料格式 0: 沒加密，1: PKCS1，2: PKCS7
        encryptionData.addProperty("dataType", DATA_TYPE_PKCS7);
        encryptionData.addProperty("rsaKey", RSA_KEY_NAME);

        // 新密碼加密資料
        encryptionData.addProperty("newData", rsaEncodedPwd != null ? rsaEncodedPwd : StringUtils.EMPTY);

        // 登入或舊密碼加密資料
        if (StringUtils.isBlank(rsaEncodedOldPwd) && StringUtils.isNotBlank(rsaEncodedPwd)) {
            rsaEncodedOldPwd = rsaEncodedPwd;
        }
        encryptionData.addProperty("oldData", rsaEncodedOldPwd != null ? rsaEncodedOldPwd : StringUtils.EMPTY);

        // 新密碼確認加密資料
        encryptionData.addProperty("confirmData", rsaEncodedConfirmPwd != null ? rsaEncodedConfirmPwd : StringUtils.EMPTY);

        // 虛擬鍵盤Key值 keycode = 數字串@字元串
        encryptionData.addProperty("keycode", keycode != null ? keycode : StringUtils.EMPTY);

        bodyChkPwdRule.add("encryptionData", encryptionData);

        // 身份證字號或統編
        if (StringUtils.isNotBlank(uid)) {
            uid = uid.toUpperCase();
        }
        bodyChkPwdRule.addProperty("uid", uid);

        // 使用者編碼
        if (StringUtils.isNotBlank(uuid)) {
            uuid = uuid.toUpperCase();
        }
        bodyChkPwdRule.addProperty("uuid", uuid);

        /*
         * "cluster": <叢集名稱>, "encryptionData": { "oldData":<登入或舊密碼加密資料>, "newData":<新密碼加密資料>, "confirmData":<新密碼確認加密資料>, "dataType":<資料格式>, "rsaKey":<RSA解密金鑰>, "keycode": <虛擬鍵盤Key值> }, "uid":<身份證字號或統編>, "uuid":<使用者編碼>,
         */

        return bodyChkPwdRule;

    }

    protected JsonObject getRqBody(String rsaEncodedPwd, String keycode, Boolean ignoreCheckTime) {

        JsonObject bodyChkPwdRule = new JsonObject();

        bodyChkPwdRule.addProperty("cluster", CLUSTER);

        if (ignoreCheckTime != null) {
            bodyChkPwdRule.addProperty("ignoreCheckTime", ignoreCheckTime);
        }

        JsonObject encryptionData = new JsonObject();
        encryptionData.addProperty("withTime", 1);
        encryptionData.addProperty("dataType", DATA_TYPE_PKCS7);
        encryptionData.addProperty("rsaKey", RSA_KEY_NAME);
        encryptionData.addProperty("data", rsaEncodedPwd);

        if (StringUtils.isNotBlank(keycode)) {
            encryptionData.addProperty("keycode", keycode);
        }
        bodyChkPwdRule.add("encryptionData", encryptionData);

        /*
         * "cluster": <叢集名稱>, "encryptionData": { "data":<加密資料>, "dataType":<資料格式>, "rsaKey":<RSA解密金鑰>, "keycode": <虛擬鍵盤Key值> },
         */

        return bodyChkPwdRule;

    }

    protected JsonObject postE2EE(String urlStr, JsonObject bodyObj) {
        logger.info("bodyObj:" + bodyObj);

        init();

        InputStreamReader streamReader = null;
        BufferedReader in = null;
        HttpURLConnection con = null;
        try {
            // 初始化 http connection
            URL url = new URL(ValidateParamUtils.validParam(API_URL + urlStr));
            con = (HttpURLConnection) url.openConnection();
            // 設定 http method "POST"
            con.setRequestMethod(HTTP_METHOD_POST);
            // 設定 http header, ContentType為json, 編碼為utf-8
            con.setRequestProperty(HTTP_HEADER_CONTENTTYPE, HTTP_CONTENTTYPE_JSON_UTF8);
            con.setRequestProperty(HTTP_HEADER_AUTH, HTTP_HEADER_AUTH_BEARER + getToken());
            // 設定連線逾時，送到Server的時間
            con.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
            // 設定回應逾時，送到Server，但Server沒回, AP不想等, 想中斷的時間
            con.setReadTimeout(HTTP_READ_TIMEOUT);
            con.setDoOutput(true);
            // #4504 0823 Unreleased Resource: Streams 調整
            try (OutputStream os = con.getOutputStream()) {
                os.write(ValidateParamUtils.validParam(bodyObj.toString()).getBytes("UTF-8"));
            }
            // // 確認回傳HTTP status為200
            int status = con.getResponseCode();
            if (status == 200) {
                // 200 的話是讀取input stream
                streamReader = new InputStreamReader(con.getInputStream());
                // 將stream放入buffer reader
                in = new BufferedReader(streamReader);
                String line;
                StringBuffer content = new StringBuffer(0);
                // 一次讀取一行並加入到content後方
                try {
                    int i = 0;
                    while (true) {
                        i++;
                        // fortify: Denial of Service: readLine read MAX_LINE_SIZE
                        line = StringUtils.left(in.readLine(), MAX_LINE_SIZE);

                        if (line == null || i > MAX_COUNT) {
                            break;
                        }
                        // fortify: Denial of Service: StringBuilder - limit size
                        content.append(StringUtils.left(line, MAX_LINE_SIZE));
                    }
                }
                finally {
                    if (in != null) {
                        in.close();
                    }
                }
                // 輸出結果
                logger.info("E2EEUtils點對點加密, 呼叫" + urlStr + "成功...");
                logger.info(content.toString());

                // 解析結果
                JsonElement jelement = new JsonParser().parse(content.toString());
                JsonObject data = jelement.getAsJsonObject();

                // JSONObject response = JSONObject.parse(content.toString());
                // JSONObject data = (JSONObject)response.get("data");
                return data;
            }
            else {
                // 非200 讀取error stream
                streamReader = new InputStreamReader(con.getErrorStream());
                // 將stream放入buffer reader
                in = new BufferedReader(streamReader);
                String line;
                StringBuffer content = new StringBuffer(0);
                // 一次讀取一行並加入到content後方
                try {
                    int i = 0;
                    while (true) {
                        i++;
                        // fortify: Denial of Service: readLine read MAX_LINE_SIZE
                        line = StringUtils.left(in.readLine(), MAX_LINE_SIZE);

                        if (line == null || i > MAX_COUNT) {
                            break;
                        }
                        // fortify: Denial of Service: StringBuilder - limit size
                        content.append(StringUtils.left(line, MAX_LINE_SIZE));
                    }
                }
                finally {
                    if (in != null) {
                        in.close();
                    }
                }
                // 輸出結果
                logger.info("E2EEUtils點對點加密, 呼叫" + urlStr + "失敗...");
                logger.info(content.toString());

                JsonElement jelement = new JsonParser().parse(content.toString());
                JsonObject data = jelement.getAsJsonObject();
                String errorCode = data.get("errorCode").getAsString();
                if ("66114".equals(errorCode)) {
                    logger.info("[呼叫E2EE發生錯誤]token為空");
                    // 66114 TOKEN 過期 重取
                    loadTokenVo(false);
                    logger.info("[呼叫E2EE發生錯誤]token為空，即將重新呼叫：" + urlStr);
                    postE2EE(urlStr, bodyObj);// TODO
                }

                throw new RuntimeException("E2EEUtils點對點加密, 呼叫" + urlStr + "失敗...");
            }

        }
        catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("E2EEUtils點對點加密, 呼叫" + urlStr + "失敗...");
        }
        finally {
            // Close InputStreamReader
            try {
                if (streamReader != null)
                    streamReader.close();
            }
            catch (IOException e) {
                logger.warn("streamReader close failed", e);
            }
            // Close BufferedReader
            try {
                if (in != null)
                    in.close();
            }
            catch (IOException e) {
                logger.warn("reader close failed", e);
            }
            // Close HttpURLConnection
            if (con != null)
                con.disconnect();
        }
    }

    protected String replacesAllValues(String template, Map<String, String> values) {
        StrSubstitutor sub = new StrSubstitutor(values, "%(", ")");
        return sub.replace(template);
    }

    /**
     * 解密身分證字號相關RSA密文資訊
     * 
     * @param encryptText
     *            uid/uuid密文
     * @return 解密後資料
     * @throws E2EEException
     */
    public String decryptJSBRSAEncodedText(String encryptText) throws E2EEException {

        // 如果是空白則不做解密處理
        if (StringUtils.isBlank(encryptText)) {
            return encryptText;
        }

        if (!isE2EEUidAndUuidAvaliable()) {
            logger.info("未啟用 UID / UUID加解密");
            logger.info("回傳傳入資料：" + encryptText);
            // 舊有的邏輯會針對身分證字號與使用者代碼全變為大寫
            // 日盛不轉大寫
            return encryptText;
        }

        logger.info("已啟用 UID / UUID加解密");
        logger.info("解密前資料：" + encryptText);

        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("cluster", CLUSTER);
        rqBody.addProperty("keyName", RSA_KEY_NAME);
        rqBody.addProperty("cipher", encryptText);
        rqBody.addProperty("rtnEncoding", "UTF-8");
        rqBody.addProperty("cipherType", 2);
        JsonObject rsbody = postE2EE(DECODE_RSA_URI, rqBody);

        String errorCode = rsbody == null ? "" : rsbody.get("errorCode").getAsString();
        // fortify : Redundant Null Check
        if ("0".equals(errorCode) && rsbody != null) {
            JsonObject data = rsbody.getAsJsonObject("data");
            String plainText = data.get("plain").getAsString();

            return plainText;
        }
        else {
            // fortify : Redundant Null Check
            String errorMessage = rsbody == null ? "" : rsbody.get("errorMessage").getAsString();
            logger.error("在解密Uid/Uuid時發生錯誤： 錯誤代碼：" + errorCode + ", 錯誤描述：" + errorMessage);
            throw new E2EEException("E2EE 錯誤代碼：" + errorCode + ", 錯誤描述：" + errorMessage);
        }
    }

    /**
     * 日盛密碼加密邏輯 [供B2C/EXT使用]
     * 
     * @param rqbody
     *            request json
     * @param hsmType
     *            加密類型
     * @return
     * @throws E2EEException
     * @throws ActionException
     */
    public String encodeJSBWithPasswordRule(E2EEHsmType hsmType, String uid, String uuid, String pwd, List<Character> numberList, List<Character> charList) throws E2EEException, ActionException {

        if (!isE2EEAvaliable()) {
            return pwd;
        }

        JsonObject rsbody = null;
        if (E2EEHsmType.JSB_PWD_CP1047 == hsmType || E2EEHsmType.JSB_DB_3DES_UTF8 == hsmType) {
            JsonObject rqbody = getRqBody(pwd, null, null);
            // 將呼叫新的ＡＰＩ，可以提供檢核
            String prefix = uid; // 一般會員&信用卡會員帶入prefix=uid回傳的cipher不會有uid當前綴，但如果prefix=""會回66001
            // String prefix = null;
            String acnoidPrefix = null;
            // 信用卡用戶，prefix比較特別
            // if(E2EEHsmType.JSB_DB_3DES_UTF8 == hsmType){
            // acnoidPrefix = uid+uuid;
            // }
            rqbody.add("returnPassProcess", getReturnPassProcessEB5556983(hsmType, prefix, null, acnoidPrefix, null));
            logger.info(hsmType.name() + rqbody);
            rsbody = postE2EE(ENCODE_URI_JSB, rqbody);
        }

        String errorCode = rsbody == null ? "" : rsbody.get("errorCode").getAsString();

        if (errorCode == null) {
            throw new E2EEException("E2EE 錯誤代碼為空");
        }

        /*
         * 回應代碼：回應代碼說明 0:成功。data有值 66001:服務無預期錯誤，請洽開發人員 66002:缺少必要參數 66003:不合法的參數 66010:登入資訊驗證失敗 66216:密碼檢核失敗，reason有值，詳細理由請參考 reason 參數區塊
         */
        // fortify: null deference
        if (rsbody != null && "0".equals(errorCode)) {
            JsonObject data = rsbody.getAsJsonObject("data");
            String encPass = "";
            if (E2EEHsmType.JSB_PWD_CP1047 == hsmType) {
                encPass = data.get("cipher").getAsString();
            }
            else if (E2EEHsmType.JSB_DB_3DES_UTF8 == hsmType) {
                encPass = data.get("cipher").getAsString();
            }
            return encPass;
        }
        else {
            throw new E2EEException(errorCode);
        }
    }

    private JsonObject getReturnPassProcessEB5556983(E2EEHsmType hsmType, String pwdPrefix, String pwdPostfix, String acnoidPrefix, String acnoidPostfix) {
        // String template = "{ \"hash\": [{ \"hashAlg\": 1, \"prefix\": \"\", \"postfix\": \"\" }, "
        // + "{ \"hashAlg\": 9, \"prefix\": \"\", \"postfix\": \"\" }, { \"hashAlg\": 3, \"prefix\": \"\", \"postfix\": \"\" }], "
        // + "\"encryption\":{ \"symKey\":\"%(symKey)\", \"symkeyType\":\"%(symkeyType)\", \"symKeyMode\":%(symKeyMode), "
        // + "\"symKeyIv\":\"%(symKeyIv)\", \"prefix\":\"%(prefix)\", \"postfix\":\"%(postfix)\", \"align\":\"%(align)\", "
        // + "\"alignChar\":\"%(alignChar)\", \"dataLength\": 16, \"encodeStringType\": \"%(encodeStringType)\", "
        // + "\"hashAlg\": 1, \"extraHash\": [{ \"hashAlg\": 9, \"prefix\": \"\", \"postfix\": \"\" }, "
        // + "{ \"hashAlg\": 3, \"prefix\": \"\", \"postfix\": \"\" }] }}";

        String template = "{ \"encryption\":{ \"symKey\":\"%(symKey)\", \"symkeyType\":\"%(symkeyType)\", \"symKeyMode\":%(symKeyMode), \"symKeyIv\":\"%(symKeyIv)\", \"prefix\":\"%(prefix)\", \"postfix\":\"%(postfix)\", \"encPrefix\":\"%(encPrefix)\", \"encPostfix\":\"%(encPostfix)\", \"align\":\"%(align)\", \"alignChar\":\"%(alignChar)\", \"dataLength\": 16, \"encodeStringType\": \"%(encodeStringType)\" }}";

        Map<String, String> values = new HashMap<String, String>();
        if (hsmType == E2EEHsmType.JSB_PWD_CP1047) {// 日盛一般會員，晶片金融卡
            values.put("symKey", SYS_KEY_EB5556981);// EBANK.LOGIN.WKKEY
            values.put("align", "2");
            values.put("alignChar", " ");
        }
        else if (hsmType == E2EEHsmType.JSB_DB_3DES_UTF8) {// 日盛信用卡
            values.put("symKey", SYS_KEY);// EBANK.DATA.EBDBKEY
            values.put("align", "0");
            values.put("alignChar", "");
        }

        values.put("symkeyType", SYS_KEY_TYPE_EB5556981);// 2
        values.put("symKeyMode", SYS_KEY_MODE_EB5556981);// CBC:32
        values.put("symKeyIv", SYS_KEY_IV_EB5556981);// AAAAAAAAAAA=
        values.put("prefix", org.apache.commons.lang3.StringUtils.defaultString(pwdPrefix, ""));// uid
        values.put("postfix", org.apache.commons.lang3.StringUtils.defaultString(pwdPostfix, ""));
        values.put("encPrefix", org.apache.commons.lang3.StringUtils.defaultString(acnoidPrefix, ""));// uid+uuid
        values.put("encPostfix", org.apache.commons.lang3.StringUtils.defaultString(acnoidPostfix, ""));
        values.put("encodeStringType", hsmType.getEncoding());// CP1047/CP937/UTF8

        JsonElement jelement = new JsonParser().parse(replacesAllValues(template, values));
        JsonObject jsonObject = jelement.getAsJsonObject();

        if (hsmType == E2EEHsmType.JSB_PWD_CP1047 || hsmType == E2EEHsmType.JSB_DB_3DES_UTF8) {
            // 日盛密碼格式轉換
            // "hashPrefix": "", //會加在hashCipher前面
            // "hashPostfix": "",
            // "hashAlg": 1,
            // "extraHash": [{
            // "hashAlg": 9,
            // "prefix": "",
            // "postfix": ""
            // },
            // {
            // "hashAlg": 3,
            // "prefix": "",
            // "postfix": ""
            // }
            // ]
            JsonObject encryptionObj = jsonObject.getAsJsonObject("encryption");
            encryptionObj.addProperty("hashPrefix", "");
            encryptionObj.addProperty("hashPostfix", "");
            encryptionObj.addProperty("hashAlg", 1); // MD5 Hash

            JsonObject hash9 = new JsonObject();
            hash9.addProperty("hashAlg", 9); // Byte to Hex String
            hash9.addProperty("prefix", "");
            hash9.addProperty("postfix", "");

            JsonObject hash3 = new JsonObject();
            hash3.addProperty("hashAlg", 3);// SHA-256 Hash to Hex String
            hash3.addProperty("prefix", "");
            hash3.addProperty("postfix", "");

            JsonArray extraHashArr = new JsonArray();
            extraHashArr.add(hash9);
            extraHashArr.add(hash3);
            encryptionObj.add("extraHash", extraHashArr);
        }
        return jsonObject;
    }

}
