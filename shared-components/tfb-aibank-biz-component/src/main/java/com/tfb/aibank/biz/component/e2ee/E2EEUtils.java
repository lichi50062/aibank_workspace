package com.tfb.aibank.biz.component.e2ee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.gson.GsonHelper;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.ConvertUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateFormatUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProviderFactory;
import com.ibm.tw.ibmb.component.systemparam.SystemParam;
import com.ibm.tw.ibmb.component.systemparam.SystemParamResource;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.ValidateParamUtils;
import com.ibm.tw.ibmb.validate.ValidatorUtility;
import com.tfb.aibank.biz.component.etrans.E2EEUtils_AIBank.E2EEException;
import com.tfb.aibank.common.code.AIBankConstants;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.E2EEDBKeyType;
import com.tfb.aibank.common.type.E2EEHsmType;

/**
 * 使用全景E2EE點對點加密系統, 對登入密碼進行加密
 */
@Component
public class E2EEUtils {

    private static final int MAX_LINE_SIZE = 1000000;

    private final IBLog logger = IBLog.getLog(E2EEUtils.class);

    protected static final IBLog performanceLogger = IBLog.getLog("PERFORMANCE_LOGGER");

    @Autowired
    private SystemParamResource systemParamResource;

    @Value("${aibank.environment.stand-alone:false}")
    private boolean isStdAloneEnv;

    // AIBANK 在參數檔時的 CATEGORY 名稱
    private final String CATEGORY_FUBON_AIBANK_APPLICATION_NAME = AIBankConstants.CHANNEL_NAME;

    // 在 E2EE 平台註冊的 AIBANK 名稱
    private String FUBON_AIBANK_APPLICATION_NAME;
    private final String E2EE_AIBANK_API_APPLICATION_NAME = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_APPLICATION_NAME";

    // [各平台必要參數] 是否啟用E2EE加密機制
    private final String E2EE_AVALIBLE_FLAG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_AVALIBLE_FLAG";
    private final String E2EE_UID_UUID_AVALIBLE_FLAG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_UID_UUID_AVALIBLE_FLAG";

    // [各平台必要參數] 使用平台註冊在SS平台的API_KEY < API_KEY 因為key太長, 所以拆成兩段 >
    private final String SYSTEM_PARAM_NAME_1 = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_KEY_1";
    private final String SYSTEM_PARAM_NAME_2 = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_KEY_2";

    // [各平台必要參數] 全景SS主機參數
    private final String E2EE_API_CLUSTER = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_CLUSTER";// 全景SS主機連線叢集
    private final String E2EE_API_RSA_KEY_NAME = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_RSA_KEY_NAME";// RSA公鑰名稱

    // 信卡會員新密碼登入流程是否已啟用
    private final String E2EE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_FLAG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_CREDITCARD_MEMBER_LOGIN_AVALIBLE_FLAG";
    private final String E2EE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_TIME = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_CREDITCARD_MEMBER_LOGIN_AVALIBLE_TIME";

    // 信用卡會員密碼驗證方式 OLDNEW 使用舊密碼驗證登入，並檢核新密碼正確性，NEW僅以新密碼驗證登入
    private final String E2EE_CREDITCARD_MEMBER_PWDFLAG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_CREDITCARD_MEMBER_PWDFLAG";

    // 密碼安全性提升，是否可以寫入新密碼欄位，開始可異動時間
    private final String E2EE_PWD_ADVANCED_UPDATE_FLG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_PWD_ADVANCED_UPDATE_FLG";
    private final String E2EE_PWD_ADVANCED_UPDATE_TIME = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_PWD_ADVANCED_UPDATE_TIME";

    // ---提供獨立驗證環境使用者白名單
    private final String E2EE_STD_ALONE_USER_LIST = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_STD_ALONE_USER_LIST";
    // 獨立驗證環境 信卡會員新密碼登入流程是否已啟用
    private final String E2EE_STD_ALONE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_FLAG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_STD_ALONE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_FLAG";
    private final String E2EE_STD_ALONE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_TIME = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_STD_ALONE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_TIME";
    // 獨立驗證環境 信用卡會員密碼驗證方式 OLDNEW 使用舊密碼驗證登入，並檢核新密碼正確性，NEW僅以新密碼驗證登入
    private final String E2EE_STD_ALONE_CREDITCARD_MEMBER_PWDFLAG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_STD_ALONE_CREDITCARD_MEMBER_PWDFLAG";

    // 獨立驗證環境 密碼安全性提升，是否可以寫入新密碼欄位，開始可異動時間
    private final String E2EE_STD_ALONE_PWD_ADVANCED_UPDATE_FLG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_STD_ALONE_PWD_ADVANCED_UPDATE_FLG";
    private final String E2EE_STD_ALONE_PWD_ADVANCED_UPDATE_TIME = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_STD_ALONE_PWD_ADVANCED_UPDATE_TIME";

    // 密碼是否要驗證時間因子 [0:不使用時間因子驗證, 1:使用時間因子驗證]
    private final String E2EE_VALIDATE_PWD_WITH_TIME_FLG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_CHECK_PWD_WITHTIME_FLG";
    // Uid/Uuid是否要驗證時間因子 [0:不使用時間因子驗證, 1:使用時間因子驗證]
    private final String E2EE_VALIDATE_UID_UUID_WITH_TIME_FLG = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_CHECK_UID_UUID_WITHTIME_FLG";

    // 以下為PIB共用連線參數
    private final String E2EE_AIBANK_API_URL = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_URL";// 全景SS主機url
    private final String E2EE_AIBANK_API_KEY_NAME = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_KEY_NAME";// 登入加密金鑰名稱
    private final String E2EE_AIBANK_API_KEY_NAME_DB = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_KEY_NAME_DB";// DB登入加密金鑰名稱
    private final String E2EE_AIBANK_API_KEY_NAME_DB_AES256 = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_KEY_NAME_DB_AES256";
    private final String E2EE_AIBANK_API_KEY_NAME_DB_OTP_AES256 = "E2EE_" + CATEGORY_FUBON_AIBANK_APPLICATION_NAME + "_API_KEY_NAME_DB_OTP_AES256";

    // 全景加密機器，PIB共用連線參數
    public String API_URL;// = getSystemParam(E2EE_API_URL);// "http://192.168.0.90:8080/SS/rest";
    public String CLUSTER;// = getSystemParam(E2EE_API_CLUSTER);// ETOEE
    public String KEY_NAME;// = getSystemParam(E2EE_API_KEY_NAME);// EBANK.LOGIN.WKKEY
    public String KEY_NAME_DB;// = getSystemParam(E2EE_API_KEY_NAME_DB);// EBANK.DATA.EBDBKEY
    public String RSA_KEY_NAME;// = getSystemParam(E2EE_API_RSA_KEY_NAME);// TEST_RSA

    // 2024/07 使用E2EE加解密公鑰uri：AES256
    public String KEY_NAME_DB_AES256; // getSystemParam(E2EE_API_KEY_NAME_DB_AES256);// EBANK.DATA.EBDBKEY
    public String KEY_NAME_DB_OTP_AES256; // getSystemParam(E2EE_API_KRY_NAME_DB_OTP_AES256);// EBANK.DATA.EBDBKEY

    // 全景API路徑
    public final String TOKEN_URI = "/token/login";// 取得Token
    public final String RSA_PUBLIC_KEY_URI = "/AsymmetricKey/publickey";// 取得RSA公鑰
    public final String CHECK_CC_URI = "/e2e/checkPasswordRule";// 檢核信用卡會員登入密碼
    public final String VALIDATE_GENERAL_URI = "/AsymmetricKey/TFB/checkpassword"; // 檢核一般會員登入密碼
    public final String ENCODE_URI = "/AsymmetricKey/TFB/encode";// 密碼轉換與加密，EB5556981為主，提供CP1047/CP937特殊加密邏輯
    public final String DECODE_RSA_URI = "/AsymmetricKey/decrypt";// 將前端PKCS-7 加密資料解密
    public final String DEPT_PUBLIC_KEY_URI = "/SymmetricKey/decrypt";
    public final String EPT_PUBLIC_KEY_URI = "/SymmetricKey/encrypt";

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

    // 全景密碼加密參數[/e2e/checkPasswordRule]
    public final String SYS_KEY = KEY_NAME_DB;// EBANK.DATA.EBDBKEY
    public final String SYS_KEY_MODE = "32";// 32:CBC
    public final String PADDING_WITH_KEY_MODE_256 = "1";// CBC_ZEROPAD padding = 1 with sysKeyMode = 32
    public final String SYS_KEY_IV = "AAAAAAAAAAA=";// AAAAAAAAAAA ==>沒有iv

    // 全景密碼加密參數[/AsymmetricKey/TFB/encode]
    public String SYS_KEY_EB5556981 = KEY_NAME;// WKKEY_1
    public final String SYS_KEY_TYPE_EB5556981 = "2";// 2
    public final String SYS_KEY_MODE_EB5556981 = "256";// 32:CBC, 256:CBC_ZEROPAD
    public final String SYS_KEY_IV_EB5556981 = "AAAAAAAAAAA=";

    /**
     * 全景密碼加密參數 @2024/07 使用E2EE加解密參數：AES256
     */
    private static final String SYS_KEY_MODE_AES256 = "64";// 64:CBC
    private static final String SYS_KEY_TYPE_AES256 = "6";// 6：AES256加密金鑰類型

    // true: 新版加解密方法(AES256) false: 使用原來加解密方法(2DES)
    public String E2EE_AIBANK_CRYPTO_MODE_KEY = "CRYPTO_MODE_KEY";
    private boolean isNewCryptModeKey = true;

    /** 加密 Key 值 */
    private String cryptKey;

    private String otpCryptKey;

    // RSA公鑰時請重新下上util
    private String publicKey = null;
    // API連線時，所需token
    private TokenVo tokenVo = new TokenVo();
    // RSA公鑰wrapper
    public final String PUBLIC_KEY_WRAPPER = "-----BEGIN RSA PUBLIC KEY-----%s-----END RSA PUBLIC KEY-----";

    // 動態鍵盤
    public final Character[] NUMBER_ARR = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    public final Character[] LETTER_ARR = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    private synchronized void init() {
        FUBON_AIBANK_APPLICATION_NAME = getSystemParamMap().get(E2EE_AIBANK_API_APPLICATION_NAME);
        API_URL = getSystemParamMap().get(E2EE_AIBANK_API_URL);// "http://192.168.0.90:8080/SS/rest";
        CLUSTER = getSystemParamMap().get(E2EE_API_CLUSTER);// ETOEE
        KEY_NAME = getSystemParamMap().get(E2EE_AIBANK_API_KEY_NAME);// EBANK.LOGIN.WKKEY
        KEY_NAME_DB = getSystemParamMap().get(E2EE_AIBANK_API_KEY_NAME_DB);// EBANK.DATA.EBDBKEY
        RSA_KEY_NAME = getSystemParamMap().get(E2EE_API_RSA_KEY_NAME);// TEST_RSA
        KEY_NAME_DB_AES256 = getSystemParamMap().get(E2EE_AIBANK_API_KEY_NAME_DB_AES256);// EBDATAKEY
        KEY_NAME_DB_OTP_AES256 = getSystemParamMap().get(E2EE_AIBANK_API_KEY_NAME_DB_OTP_AES256);// EBOTPKEY

        // true: 新版加解密方法(AES256) false: 使用原來加解密方法(2DES)
        String cryptModeKeyStr = getSystemParamMap().get(E2EE_AIBANK_CRYPTO_MODE_KEY);// E2EE_AIBANK_CRYPTO_MODE_KEY
        if (StringUtils.isNotBlank(cryptModeKeyStr)) {
            isNewCryptModeKey = Boolean.parseBoolean(cryptModeKeyStr);
        }

        if (StringUtils.isBlank(KEY_NAME_DB)) {
            KEY_NAME_DB = "EBANK.DATA.EBDBKEY";
        }
    }

    private final int MAX_COUNT = 1000;

    private final Gson gson = GsonHelper.newInstance();

    /**
     * 取得給前端用的RSA公鑰
     * 
     * @return
     * @throws ActionException
     */
    public String getPublicRSAKey() throws ActionException {

        init();
        // Dead Code: Expression is Always false
        if (!isE2EEAvaliable()) {
            return StringUtils.EMPTY;
        }

        // 如果已經取得公鑰，直接回傳
        if (publicKey != null) {

            return String.format(PUBLIC_KEY_WRAPPER, publicKey);
        }

        try {
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
            BufferedReader in = null;
            // 確認回傳HTTP status為200
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
                        line = in.readLine();

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
                logger.info("E2EE點對點加密, 取得公鑰成功...");
                logger.info("", content);

                // 解析結果
                JsonElement jelement = JsonParser.parseString(content.toString());
                JsonObject data = jelement.getAsJsonObject();
                data = data.getAsJsonObject("data");
                publicKey = data.get("pubKey").getAsString();

                if (StringUtils.isBlank(publicKey)) {
                    logger.error("E2EE點對點加密, 取得公鑰失敗");
                    throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
                }

                return String.format(PUBLIC_KEY_WRAPPER, publicKey);
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
                        line = in.readLine();

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
                logger.error("E2EE點對點加密, 取得公鑰失敗 {} ", content);
                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
            }
        }
        catch (IOException ex) {
            logger.error("E2EE點對點加密, 取得公鑰失敗");
            throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
        }

    }

    /**
     * 取得給DB存取用的AES鑰匙
     * 
     * @return
     * @throws Exception
     * @throws ActionException
     */
    public String getDBAccessKxy() throws ActionException {
        String hint = StringUtils.EMPTY;
        if (isNewCryptModeKey) {
            try {
                hint = deptPublicKey(getKeyCipherHex(E2EEDBKeyType.DB_KEY_AES256), E2EEDBKeyType.DB_KEY_AES256);
                logger.info("New DB Key = {}", hint);
            }
            catch (ActionException e) {
                logger.error("取得DB存取用的AES鑰匙失敗", e);
                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
            }
        }
        else {
            try {
                hint = deptPublicKey(getKeyCipherHex(E2EEDBKeyType.DB_KEY), E2EEDBKeyType.DB_KEY);
                logger.info("DB Key = {}", hint);
            }
            catch (ActionException e) {
                logger.error("取得DB存取用的AES鑰匙失敗", e);
                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
            }
        }

        return hint;
    }

    /**
     * 取得給 OTP 存取用的AES鑰匙
     * 
     * @return
     * @throws Exception
     * @throws ActionException
     */
    public String getOTPAccessKxy() throws ActionException {
        String hint = StringUtils.EMPTY;

        try {
            hint = deptPublicKey(getKeyCipherHex(E2EEDBKeyType.DB_KEY_OTP_AES256), E2EEDBKeyType.DB_KEY_OTP_AES256);
            logger.info("New DB Key = {}", hint);
        }
        catch (ActionException e) {
            logger.error("取得DB存取用的AES鑰匙失敗", e);
            throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
        }

        return hint;
    }

    private String getToken() throws ActionException {
        logger.info("[取得token]讀取token：" + tokenVo);
        Calendar now = Calendar.getInstance();
        logger.info("[取得token]讀取token：now" + now + ", ExpiredTime" + tokenVo.getExpiredTime());
        logger.info("[取得token]讀取token：now.after(tokenVo.getExpiredTime())" + now.after(tokenVo.getExpiredTime()));
        if (tokenVo.getExpiredTime() != null && now.getTime().after(tokenVo.getExpiredTime())) {
            logger.info("[取得token]token已過期");
            // 過期了, 重新取得token，因 server side refresh有問題，舊版程式固定給 false
            loadTokenVo();
        }
        if (StringUtils.isBlank(tokenVo.getAccessToken())) {
            logger.info("[取得token]token為空，重新取得token");
            loadTokenVo();
        }
        return tokenVo.getAccessToken();
    }

    /**
     * 取得Token, 系統初始化時呼叫
     * 
     * @return
     * @throws ActionException
     * @throws Exception
     */
    // private synchronized void loadTokenVo() throws ActionException {
    // loadTokenVo(false);
    // }

    private synchronized void loadTokenVo() throws ActionException {
        logger.info("[取得token]開始讀取/更新token");
        InputStreamReader streamReader = null;
        BufferedReader in = null;
        HttpURLConnection con = null;
        try {
            JsonObject tobeSignData = new JsonObject();
            String applicationName = FUBON_AIBANK_APPLICATION_NAME;
            tobeSignData.addProperty("applicationName", applicationName);

            tobeSignData.addProperty("signTime", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

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
            // if (isRefresh) {
            // // refresh token要帶Bearer Token
            // con.setRequestProperty(HTTP_HEADER_AUTH, HTTP_HEADER_AUTH_BEARER + tokenVo.getAccessToken());
            // }
            // 設定連線逾時，送到Server的時間
            con.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
            // 設定回應逾時，送到Server，但Server沒回, AP不想等, 想中斷的時間
            con.setReadTimeout(HTTP_READ_TIMEOUT);
            con.setDoOutput(true);

            logger.info("[取得token]header參數設定完畢");
            JsonObject bodyGetToken = new JsonObject();

            // if (!isRefresh) {
            // 第一次取得TOKEN
            bodyGetToken.addProperty("signature", Base64.encodeBase64String(signature));
            bodyGetToken.add("data", tobeSignData);
            // }
            // else {
            // // refresh token
            // bodyGetToken.addProperty("accessToken", tokenVo.getAccessToken());
            // bodyGetToken.addProperty("refreshToken", tokenVo.getRefreshToken());
            // }

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
                        line = in.readLine();

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
                logger.info("[取得token] E2EE點對點加密, 取得TOKEN成功...");
                logger.info("", content);

                // 解析結果

                JsonElement jelement = JsonParser.parseString(content.toString());
                JsonObject data = jelement.getAsJsonObject();
                data = data.getAsJsonObject("data");
                tokenVo.setAccessToken(data.get("accessToken").getAsString());
                tokenVo.setRefreshToken(data.get("refreshToken").getAsString());
                logger.info("tokenVo:" + tokenVo);

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
                        line = in.readLine();

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
                logger.info("[取得token] E2EE點對點加密, 取得TOKEN失敗", content);
                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);

            }
        }
        catch (IOException | InvalidKeyException | NoSuchAlgorithmException | SignatureException | InvalidKeySpecException e) {
            logger.error("[取得token] E2EE點對點加密, 取得TOKEN失敗, 拋出Exception", e);
            throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
        }
        finally {
            // Close InputStreamReader
            try {
                if (streamReader != null) {
                    streamReader.close();
                }
            }
            catch (IOException e) {
                logger.warn("streamReader close failed", e);
            }
            // Close BufferedReader
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                logger.warn("BufferedReader close failed", e);
            }
            // Close HttpURLConnection
            if (con != null) {
                con.disconnect();
            }
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
        String apiKey = getSystemParamMap().get(SYSTEM_PARAM_NAME_1) + getSystemParamMap().get(SYSTEM_PARAM_NAME_2);
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

    /**
     * E2EE是否啟用
     * 
     * @return true/ false
     */
    // Dead Code: Expression is Always false : 內容由 SYSTEM_PARAM 而來，不會永遠 false
    public Boolean isE2EEAvaliable() {
        Boolean flag = Boolean.parseBoolean(getSystemParamMap().get(E2EE_AVALIBLE_FLAG));
        logger.info("AI_SYSTEM_PARAM.PARAM_KEY={}, 是否啟用E2EE={}", E2EE_AVALIBLE_FLAG, flag);
        return flag;
    }

    /**
     * 是否啟用E2E身分證、使用者代碼加密機制
     * 
     * @return true/ false
     */
    public Boolean isE2EEUidAndUuidAvaliable() {
        Boolean E2EEUidFlag = Boolean.parseBoolean(getSystemParamMap().get(E2EE_UID_UUID_AVALIBLE_FLAG));
        Boolean E2EEFlag = isE2EEAvaliable();
        logger.info(E2EE_UID_UUID_AVALIBLE_FLAG + "是否啟用=" + E2EEUidFlag);
        // Dead Code: Expression is Always false，但認為是誤判
        if (!E2EEFlag && E2EEUidFlag) {
            logger.warn(E2EE_AVALIBLE_FLAG + "尚未啟用，需設定為啟用" + E2EE_AVALIBLE_FLAG + "=true 則" + E2EE_UID_UUID_AVALIBLE_FLAG + "=true 才會生效");
        }
        return E2EEFlag && E2EEUidFlag;

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
        StringBuilder sb = new StringBuilder(8192);
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
     * @throws ActionException
     */
    public PassRuleMsg validatePasswordOld(PassRuleType checkRule, String uid, String uuid, String pwd, String oldPwd, String confirmedPwd
    /** List<Character> numberList, List<Character> charList */
    ) throws ActionException {
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
        // else if ((numberList == null && charList != null) || (numberList != null && charList == null)) {
        // logger.error("動態鍵盤輸入值有問題！");
        // throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
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
            if (ValidatorUtility.checkSameFourChar(pwd)) {
                logger.info("檢核失敗：NEW_PASSWORD_REPEAT_ENG_NUM_ERR");
                return PassRuleMsg.NEW_PASSWORD_REPEAT_ENG_NUM_ERR;
            }
            // 新密碼不能含有4碼連續英文或數字
            if (ValidatorUtility.checkConsecutiveFourChar(pwd)) {
                logger.info("檢核失敗：NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR");
                return PassRuleMsg.NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR;
            }
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
            if (ValidatorUtility.checkSameFourChar(pwd)) {
                logger.info("檢核失敗：NEW_PASSWORD_REPEAT_ENG_NUM_ERR");
                return PassRuleMsg.NEW_PASSWORD_REPEAT_ENG_NUM_ERR;
            }
            // 新密碼不能含有4碼連續英文或數字
            if (ValidatorUtility.checkConsecutiveFourChar(pwd)) {
                logger.info("檢核失敗：NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR");
                return PassRuleMsg.NEW_PASSWORD_CONSECUTIVE_ENG_NUM_ERR;
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
        logger.error("E2EE處理失敗, 未設定PassRuleType!!!");
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
    // public PassRuleMsg validateWithPasswordRule(PassRuleType checkRule, String uid, String uuid, String pwd, String oldPwd, String confirmedPwd, List<Character> numberList, List<Character> charList) throws ActionException {
    // return validateWithPasswordRule(checkRule, uid, uuid, pwd, oldPwd, confirmedPwd, numberList, charList, null);
    // }

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
     * @param isWithTime
     *            pwd 是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]
     * @return
     * @throws ActionException
     */
    public PassRuleMsg validateWithPasswordRule(PassRuleType checkRule, String uid, String uuid, String pwd, String oldPwd, String confirmedPwd, List<Character> numberList, List<Character> charList, Boolean isPwdWithTime) throws ActionException {

        init();

        if (!isE2EEAvaliable()) {
            return validatePasswordOld(checkRule, uid, uuid, pwd, oldPwd, confirmedPwd);
        }

        // 檢核時間因子，如不合規，發API之前先阻擋
        E2EEWithTimeValidType timeValidType = checkWithTimeIsValid(isPwdWithTime, isPwdNeedWithTime());
        if (!timeValidType.isValid()) {
            logger.error("TimeFactor: 發生錯誤: 發API之前先阻擋, {}, {}", isPwdWithTime, isPwdNeedWithTime());
            throw ExceptionUtils.getActionException(AIBankErrorCode.TIME_FACTOR_ERROR);// 00202：時間因子驗證失敗
        }
        logger.info("TimeFactor: validateWithPasswordRule timeValidType: {}", timeValidType);

        if (checkRule == PassRuleType.DB_LOGIN_RULE_NORMAL) {
            JsonObject rqbody = getRqBody(pwd, null, true);
            rqbody.add("returnPassProcess", get3DESReturnPassProcess(uid + uuid, ""));
            rqbody.add("passRule", getPassRule(checkRule, uid, uuid, pwd, oldPwd, confirmedPwd));
            rqbody.addProperty("rtnEncoding", 2);// 2:Hex
            JsonObject rsbody = postE2EE(CHECK_CC_URI, rqbody);
            String errorCode = rsbody.get("errorCode").getAsString();
            String failCode = rsbody.get("failCode") == null ? "" : rsbody.get("failCode").getAsString();

            if ("0".equals(errorCode)) {
                return PassRuleMsg.SUCCESS;
            }
            else if (this.isTwoFactorError(errorCode, failCode)) { // 若errorCode為66216，且failCode為020表示時間因子驗證失敗。
                logger.error("E2EE 錯誤代碼：{}", errorCode);
                throw ExceptionUtils.getActionException(AIBankErrorCode.TIME_FACTOR_ERROR);// 00202：時間因子驗證失敗
            }
            else {
                logger.error("E2EE 錯誤代碼：{}", errorCode);
                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
            }
        }
        else {
            String keycode = genKeycodeFormat(numberList, charList);// 動態鍵盤帶入轉換參數

            JsonObject rqbody = getValidateRqBody(pwd, oldPwd, confirmedPwd, uid, uuid, keycode, timeValidType);

            rqbody.add("passRule", getValidatePassRule(checkRule));

            JsonObject rsbody = postE2EE(VALIDATE_GENERAL_URI, rqbody);// VALIDATE_GENERAL_URI
            String errorCode = rsbody.get("errorCode").getAsString();
            String failCode = rsbody.get("failCode") == null ? "" : rsbody.get("failCode").getAsString();

            if ("0".equals(errorCode)) {
                return PassRuleMsg.SUCCESS;
            }
            else if (AIBankErrorCode.TIME_FACTOR_ERROR.getErrorCode().equals(errorCode)) {
                PassRuleMsg errMsg = PassRuleMsg.getPassRuleErrMsg(checkRule, rsbody);
                if (PassRuleType.LOGIN_RULE_NORMAL == checkRule && PassRuleMsg.PASSWORD_ENG_NUM_ERR == errMsg) {
                    // 確認是否為首次登入
                    logger.info("驗證是否為首次登入!!");
                    String keycode2 = genKeycodeFormat(numberList, charList);// 動態鍵盤帶入轉換參數
                    JsonObject rqbody2 = getValidateRqBody(pwd, oldPwd, confirmedPwd, uid, uuid, keycode2, timeValidType);
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
                if (this.isTwoFactorError(errorCode, failCode)) {
                    throw ExceptionUtils.getActionException(AIBankErrorCode.TIME_FACTOR_ERROR);
                }
                logger.error("E2EE 錯誤代碼： {}", errorCode);
                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
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
    // public String encodeWithPasswordRule(E2EEHsmType originalType, String uid, String uuid, String pwd, List<Character> numberList, List<Character> charList) throws ActionException {
    // return encodeWithPasswordRule(originalType, uid, uuid, pwd, numberList, charList, null);
    // }

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
     * @param isWithTime
     *            pwd 是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]
     * @return
     * @throws ActionException
     * @throws E2EEException
     */
    public String encodeWithPasswordRule(E2EEHsmType hsmType, String uid, String uuid, String pwd, List<Character> numberList, List<Character> charList, Boolean isPwdWithTime) throws ActionException {

        init();

        // 檢核時間因子，如不合規，發API之前先阻擋
        E2EEWithTimeValidType timeValidType = checkWithTimeIsValid(isPwdWithTime, isPwdNeedWithTime());
        if (!timeValidType.isValid()) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TIME_FACTOR_ERROR);// 00202：時間因子驗證失敗
        }

        SYS_KEY_EB5556981 = getSystemParamMap().get(E2EE_AIBANK_API_KEY_NAME);

        JsonObject rsbody = null;
        if (E2EEHsmType.PWD_EB5556981_CP1047 == hsmType || E2EEHsmType.PWD_MPVV_CP1047 == hsmType //
                || E2EEHsmType.PWD_3DES_CP1047 == hsmType || E2EEHsmType.PWD_MPVV_CP937 == hsmType //
                || E2EEHsmType.DB_MD5_UTF8 == hsmType || E2EEHsmType.DB_3DES_UTF8 == hsmType //
                || E2EEHsmType.DB_3DES_UTF8_PWD_ENHANCE == hsmType) {
            JsonObject rqbody = getRqBody(pwd, genKeycodeFormat(numberList, charList), timeValidType);
            // 將呼叫新的ＡＰＩ，可以提供檢核
            String prefix = uid;
            String acnoidPrefix = null;
            String secondHashPrefix = null;
            String secondHashPostfix = null;
            // 信用卡用戶，prefix比較特別
            if (E2EEHsmType.DB_3DES_UTF8 == hsmType || E2EEHsmType.DB_MD5_UTF8 == hsmType || E2EEHsmType.DB_3DES_UTF8_PWD_ENHANCE == hsmType) {
                acnoidPrefix = uid + uuid;
            }

            JsonObject returnProcessObject = getReturnPassProcessEB5556981(hsmType, prefix, null, acnoidPrefix, null);
            // [提高密碼安全性]
            // ㄧ般會員-新增額外雜湊方法 將UID右補10碼半形空白當後綴 使用SHA512再包一層
            // 信用卡會員-新增額外雜湊方法 使用SHA512再包一層
            if (hsmType.isNeedEnhancePwd()) {
				// [提高密碼安全性]一般會員-新增額外雜湊方法 將UID右補10碼半形空白當後綴
				if(E2EEHsmType.PWD_EB5556986_CP1047_PWD_ENHANCE == hsmType) {
					returnProcessObject = getReturnPassProcessEB5556986(hsmType.getEncoding(), uid, null, null, null, null, uid);
				}else if(E2EEHsmType.DB_3DES_UTF8 == hsmType) {
					// 信用卡會員-新增額外雜湊方法 使用SHA512再包一層
					returnProcessObject = getReturnPassProcessAttachAdvencedSHA512(returnProcessObject, secondHashPrefix, secondHashPostfix);
				}
			}

            rqbody.add("returnPassProcess", returnProcessObject);
            // Fortify:Log Forging //logger.info(hsmType.name() + rqbody);
            rsbody = postE2EE(ENCODE_URI, rqbody);
        }
        // 舊的API, 沒有動態鍵盤才能呼叫這個
        else if (E2EEHsmType.DB_3DES_UTF8 == hsmType) {
            JsonObject rqbody = getRqBody(pwd, genKeycodeFormat(numberList, charList), true);
            rqbody.add("returnPassProcess", get3DESReturnPassProcess(uid + uuid, ""));
            rqbody.add("passRule", getNoPassRule());
            rqbody.addProperty("rtnEncoding", 2);// 2:Hex
            rsbody = postE2EE(CHECK_CC_URI, rqbody);
        }

        String errorCode = rsbody == null ? "" : rsbody.get("errorCode").getAsString();

        /*
         * 回應代碼：回應代碼說明 0:成功。data有值 66001:服務無預期錯誤，請洽開發人員 66002:缺少必要參數 66003:不合法的參數 66010:登入資訊驗證失敗 66216:密碼檢核失敗，reason有值，詳細理由請參考 reason 參數區塊
         */
        // fortify: null deference
        if (rsbody != null && "0".equals(errorCode)) {
            JsonObject data = rsbody.getAsJsonObject("data");
            String encPass = "";
            if (E2EEHsmType.PWD_EB5556981_CP1047 == hsmType) {
                encPass = data.get("encodecipher").getAsString() + "@" + data.get("cipher").getAsString();
                if(hsmType.isNeedEnhancePwd()) {
					encPass = encPass + "@" +data.get("hashcipherAdvanced").getAsString();
				}
            }
            else if (E2EEHsmType.PWD_MPVV_CP1047 == hsmType || E2EEHsmType.PWD_MPVV_CP937 == hsmType) {
                encPass = data.get("encodecipher").getAsString();
            }
            else if (E2EEHsmType.PWD_3DES_CP1047 == hsmType) {
                encPass = data.get("cipher").getAsString();
            }
            else if (E2EEHsmType.DB_3DES_UTF8 == hsmType || E2EEHsmType.DB_3DES_UTF8_PWD_ENHANCE == hsmType) {
                encPass = data.get("cipher").getAsString();
                if (hsmType.isNeedEnhancePwd()) {
                    encPass = encPass + "@" + data.get("cipherAdvanced").getAsString();
                }
                encPass = encPass.toUpperCase();// 需另外轉大寫才與DB一致
            }
            else if (E2EEHsmType.DB_MD5_UTF8 == hsmType) {
                encPass = data.get("hashcipher").getAsString();
            }
            return encPass;
        }
        else {
            logger.error("E2EE 錯誤代碼：{}", errorCode);
            throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
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
    // public String decryptRSAEncodedText(String encryptText) throws ActionException {
    // return decryptRSAEncodedText(encryptText, null);
    // }

    /**
     * 解密身分證字號相關RSA密文資訊
     * 
     * @param encryptText
     *            uid/uuid密文
     * @param isWithTime
     *            pwd 是否含有時間因子 [null:無實作 / false:不含時間因子 / true:有時間因子]
     * @return 解密後資料
     * @throws ActionException
     */
    public String decryptRSAEncodedText(String encryptText, Boolean isWithTime) throws ActionException {

        init();

        // 如果是空白則不做解密處理
        if (StringUtils.isBlank(encryptText)) {
            return encryptText;
        }

        if (!isE2EEUidAndUuidAvaliable()) {
            logger.info("未啟用 UID / UUID加解密");
            logger.info("回傳傳入資料：{}" + encryptText);
            // 舊有的邏輯會針對身分證字號與使用者代碼全變為大寫
            return encryptText.toUpperCase();
        }

        // 檢核時間因子，如不合規，發API之前先阻擋
        E2EEWithTimeValidType timeValidType = checkWithTimeIsValid(isWithTime, isUidUuidNeedWithTime());
        if (!timeValidType.isValid()) {
            throw ExceptionUtils.getActionException(AIBankErrorCode.TIME_FACTOR_ERROR);
        }

        JsonObject rqBody = new JsonObject();
        rqBody.addProperty("cluster", CLUSTER);
        rqBody.addProperty("keyName", RSA_KEY_NAME);
        rqBody.addProperty("cipher", encryptText);
        rqBody.addProperty("rtnEncoding", "UTF-8");
        rqBody.addProperty("cipherType", 2);

        // 加入時間因子
        if (timeValidType.isValid()) {
            rqBody.addProperty("withTime", timeValidType.getIsWithTime());
            rqBody.addProperty("ignoreCheckTime", timeValidType.isIgnoreCheckTime());
        }
        logger.info("TimeFactor: decryptRSAEncodedText isValid: {}", timeValidType.isValid());

        JsonObject rsbody = postE2EE(DECODE_RSA_URI, rqBody);

        String errorCode = rsbody.get("errorCode").getAsString();
        String failCode = rsbody.get("failCode") == null ? "" : rsbody.get("failCode").getAsString();

        if ("0".equals(errorCode)) {
            JsonObject data = rsbody.getAsJsonObject("data");
            String plainText = data.get("plain").getAsString();

            return plainText;
        }
        else {
            String errorMessage = rsbody.get("errorMessage").getAsString();
            logger.error("在解密Uid/Uuid時發生錯誤： 錯誤代碼：" + errorCode + ", 錯誤描述：" + errorMessage);
            if (this.isTwoFactorError(errorCode, failCode)) {
                throw ExceptionUtils.getActionException(AIBankErrorCode.TIME_FACTOR_ERROR);
            }
            throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
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
//    public int checkSecuirtyRules(String uid, String userId, String secret, String oldSecret) {
//        return checkSecuirtyRules(uid, userId, secret, oldSecret, null);
//    }

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
    public int checkSecuirtyRules(String uid, String userId, String secret, String oldSecret, Boolean isPwdWithTime) {

        init();

        logger.info("使用檢核規則：checkSecuirtyRulesWithAppVersion");

        boolean userIdOk = true;
        boolean userPwdOk = true;
        // 1.「使用者代碼」需為 6 至 10 位，「網路銀行密碼」需為 6 至 16 位（英文字一律視為大寫）
        int length = StringUtils.length(userId);
        if (length < 6 && length > 10) {
            userIdOk = false;
        }
        // 因為全景的API只能驗證"新密碼是否為身份證字號或使用者代號一部分", 所以舊密碼userPwd也要塞到新密碼欄位才能驗證"舊密碼是否為身份證字號或使用者代號一部分"
        try {
            PassRuleMsg passRuleMsg = validateWithPasswordRule(PassRuleType.CHECK_IS_FIRST_LOGIN, uid, userId, secret, oldSecret, null, null, null, isPwdWithTime);
            if (passRuleMsg != PassRuleMsg.SUCCESS) {
                logger.error(passRuleMsg.failCodes);
                userPwdOk = false;
            }
        }
        catch (ActionException e) {
            logger.error("", e);
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

    /**
     * 加密資料
     * 
     * @param raw
     * @return
     * @throws ActionException
     */
    public String eptPublicKey(String raw, E2EEDBKeyType keyType) throws ActionException {
        if (!isE2EEAvaliable()) {
            return StringUtils.EMPTY;
        }

        init();

        KeyParam keyParam = checkKeyNameParam(keyType);

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        HttpURLConnection con = null;

        try {
            // HttpsURLConnection ignore SSL ca
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            SSLContext sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    if (StringUtils.isEmpty(hostname)) {
                        // Hostname is not trusted
                        return false;
                    }
                    return true;
                }
            });

            URL url = new URL(API_URL + EPT_PUBLIC_KEY_URI);
            con = (HttpURLConnection) url.openConnection();
            // 設定 http method "POST"
            con.setRequestMethod(HTTP_METHOD_POST);
            // 設定 http header, ContentType為json, 編碼為utf-8
            con.setRequestProperty(HTTP_HEADER_CONTENTTYPE, HTTP_CONTENTTYPE_JSON_UTF8);
            con.setRequestProperty(HTTP_HEADER_AUTH, HTTP_HEADER_AUTH_BEARER +

                    getToken());
            // 設定連線逾時，送到Server的時間
            con.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
            // 設定回應逾時，送到Server，但Server沒回, AP不想等, 想中斷的時間
            con.setReadTimeout(HTTP_READ_TIMEOUT);
            con.setDoOutput(true);

            JsonObject reqBody = new JsonObject();
            reqBody.addProperty("cluster", CLUSTER);
            reqBody.addProperty("keyName", keyParam.getKeyName());
            reqBody.addProperty("keyType", keyParam.getKeyType());
            reqBody.addProperty("b64Data", encode(raw));
            // reqBody.addProperty("encoding", "UTF-8");
            reqBody.addProperty("iv", SYS_KEY_IV);
            reqBody.addProperty("mode", keyParam.getKeyMode());

            logger.info("[加密資料] bodyGetToken:");
            logger.info(reqBody.toString());
            // #4504 0823 Unreleased Resource: Streams 調整
            try (OutputStream os = con.getOutputStream()) {
                os.write(reqBody.toString().getBytes("UTF-8"));
            }
            int status = con.getResponseCode();
            logger.info("[加密資料] 有回應了");

            // 確認回傳HTTP status為200
            if (status == 200) {
                // 200 的話是讀取input stream
                is = con.getInputStream();
                isr = new InputStreamReader(is);
                // 將stream放入buffer reader
                br = new BufferedReader(isr);
                String line;
                StringBuffer content = new StringBuffer(0);
                // 一次讀取一行並加入到content後方
                try {
                    while (true) {
                        line = br.readLine();

                        if (line == null) {
                            break;
                        }
                        // fortify: Denial of Service: StringBuilder - limit size
                        content.append(StringUtils.left(line, MAX_LINE_SIZE));
                    }
                }
                finally {
                    if (br != null) {
                        br.close();
                    }
                }
                // 輸出結果
                logger.info("加密資料成功...");

                JsonElement jelement = JsonParser.parseString(content.toString());
                JsonObject data = jelement.getAsJsonObject();

                JsonObject plainJson = data.get("data").getAsJsonObject();

                String plainText = plainJson.get("cipher").getAsString();

                byte[] bytes = Base64.decodeBase64(plainText);

                return bytesToHex(bytes).toUpperCase();

            }
            else {
                // 非200 讀取error stream
                isr = new InputStreamReader(con.getErrorStream());
                // 將stream放入buffer reader
                BufferedReader in = new BufferedReader(isr);
                String line;
                StringBuffer content = new StringBuffer(0);
                // 一次讀取一行並加入到content後方

                try {
                    int i = 0;
                    while (true) {
                        i++;
                        line = in.readLine();

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
                logger.error("E2EE點對點加密, 加密資料失敗");
                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
            }
        }
        catch (IOException | NoSuchAlgorithmException | ActionException |

                KeyManagementException ex) {
            logger.error(String.format("E2EE點對點加密失敗，%s", ex.getMessage()), ex);
            throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
        }
        finally {

            if (con != null) {
                con.disconnect();
            }
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    private static byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    private byte[] fitLength16(byte[] raw) {
        int originalLength = raw.length;
        int newLength = ((originalLength + 15) / 16) * 16;

        if (originalLength == newLength) {
            return raw;
        }
        byte[] paddedBytes = new byte[newLength];
        System.arraycopy(raw, 0, paddedBytes, 0, originalLength);
        Arrays.fill(paddedBytes, originalLength, newLength, (byte) 0);
        return paddedBytes;
    }

    private String encode(String raw) {
        byte[] originalBytes = raw.getBytes(StandardCharsets.UTF_8);
        String hexString = bytesToHex(fitLength16(originalBytes));
        byte[] hexBytes = hexToBytes(hexString);
        return Base64.encodeBase64String(hexBytes);
    }

    /**
     * 透過全景api解密公鑰
     * 
     * @param keyCipherHex
     * @return
     * @throws ActionException
     */
    public String deptPublicKey(String keyCipherHex, E2EEDBKeyType keyType) throws ActionException {
        return deptPublicKey(keyCipherHex, Charset.defaultCharset(), keyType);
    }

    /**
     * 透過全景api解密公鑰
     * 
     * @param keyCipherHex
     * @param charset
     * @return
     * @throws ActionException
     */
    public String deptPublicKey(String keyCipherHex, Charset charset, E2EEDBKeyType keyType) throws ActionException {
        if (!isE2EEAvaliable()) {
            return StringUtils.EMPTY;
        }

        init();

        KeyParam keyParam = checkKeyNameParam(keyType);

        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        HttpURLConnection con = null;

        try {
            // HttpsURLConnection ignore SSL ca
            TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            SSLContext sc = SSLContext.getInstance("TLSv1.2");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    if (StringUtils.isEmpty(hostname)) {
                        // Hostname is not trusted
                        return false;
                    }
                    return true;
                }
            });

            URL url = new URL(API_URL + DEPT_PUBLIC_KEY_URI);
            con = (HttpURLConnection) url.openConnection();
            // 設定 http method "POST"
            con.setRequestMethod(HTTP_METHOD_POST);
            // 設定 http header, ContentType為json, 編碼為utf-8
            con.setRequestProperty(HTTP_HEADER_CONTENTTYPE, HTTP_CONTENTTYPE_JSON_UTF8);
            con.setRequestProperty(HTTP_HEADER_AUTH, HTTP_HEADER_AUTH_BEARER +

                    getToken());
            // 設定連線逾時，送到Server的時間
            con.setConnectTimeout(HTTP_CONNECT_TIMEOUT);
            // 設定回應逾時，送到Server，但Server沒回, AP不想等, 想中斷的時間
            con.setReadTimeout(HTTP_READ_TIMEOUT);
            con.setDoOutput(true);

            // 十六進位字串轉byte array再base64編碼
            byte[] byteArrayCryptKey = ConvertUtils.hexString2ByteArray(keyCipherHex);
            String encodedCryptKey = Base64.encodeBase64String(byteArrayCryptKey);

            JsonObject reqBody = new JsonObject();
            reqBody.addProperty("cluster", CLUSTER);
            reqBody.addProperty("keyName", keyParam.getKeyName());
            reqBody.addProperty("keyType", keyParam.getKeyType());
            reqBody.addProperty("cipher", encodedCryptKey);
            reqBody.addProperty("iv", SYS_KEY_IV);
            reqBody.addProperty("mode", keyParam.getKeyMode());

            logger.info("[解密公鑰] bodyGetToken:");
            logger.info(reqBody.toString());
            // #4504 0823 Unreleased Resource: Streams 調整
            try (OutputStream os = con.getOutputStream()) {
                os.write(reqBody.toString().getBytes("UTF-8"));
            }
            int status = con.getResponseCode();
            logger.info("[解密公鑰] 有回應了");

            // 確認回傳HTTP status為200
            if (status == 200) {
                // 200 的話是讀取input stream
                is = con.getInputStream();
                isr = new InputStreamReader(is);
                // 將stream放入buffer reader
                br = new BufferedReader(isr);
                String line;
                StringBuffer content = new StringBuffer(0);
                // 一次讀取一行並加入到content後方
                try {
                    while (true) {
                        line = br.readLine();

                        if (line == null) {
                            break;
                        }
                        // fortify: Denial of Service: StringBuilder - limit size
                        content.append(StringUtils.left(line, MAX_LINE_SIZE));
                    }
                }
                finally {
                    if (br != null) {
                        br.close();
                    }
                }
                // 輸出結果
                logger.info("解密公鑰成功...");

                JsonElement jelement = JsonParser.parseString(content.toString());
                JsonObject data = jelement.getAsJsonObject();

                JsonObject plainJson = data.get("data").getAsJsonObject();

                String plainText = plainJson.get("plain").getAsString();

                String resultText = new String(Base64.decodeBase64(plainText), charset);

                return resultText;
            }
            else {
                // 非200 讀取error stream
                isr = new InputStreamReader(con.getErrorStream());
                // 將stream放入buffer reader
                BufferedReader in = new BufferedReader(isr);
                String line;
                StringBuffer content = new StringBuffer(0);
                // 一次讀取一行並加入到content後方

                try {
                    int i = 0;
                    while (true) {
                        i++;
                        line = in.readLine();

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
                logger.error("E2EE點對點加密, 解密公鑰失敗");
                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
            }
        }
        catch (IOException | NoSuchAlgorithmException | ActionException |

                KeyManagementException ex) {
            logger.error(String.format("E2EE點對點加密失敗，%s", ex.getMessage()), ex);
            throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
        }
        finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    private KeyParam checkKeyNameParam(E2EEDBKeyType keyType) throws ActionException {
        if (E2EEDBKeyType.DB_KEY.equals(keyType)) {
            return new KeyParam(KEY_NAME_DB, SYS_KEY_TYPE_EB5556981, SYS_KEY_IV, SYS_KEY_MODE);
        }

        if (E2EEDBKeyType.DB_KEY_AES256.equals(keyType)) {
            return new KeyParam(KEY_NAME_DB_AES256, SYS_KEY_TYPE_AES256, SYS_KEY_IV, SYS_KEY_MODE_AES256);
        }
        if (E2EEDBKeyType.DB_KEY_OTP_AES256.equals(keyType)) {
            return new KeyParam(KEY_NAME_DB_OTP_AES256, SYS_KEY_TYPE_AES256, SYS_KEY_IV, SYS_KEY_MODE_AES256);
        }
        throw new ActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
    }

    private JsonObject getReturnPassProcessEB5556981(E2EEHsmType hsmType, String pwdPrefix, String pwdPostfix, String acnoidPrefix, String acnoidPostfix) {
        String template = "{ \"encryption\":{ \"symKey\":\"%(symKey)\", \"symkeyType\":\"%(symkeyType)\", \"symKeyMode\":%(symKeyMode), \"symKeyIv\":\"%(symKeyIv)\", \"prefix\":\"%(prefix)\", \"postfix\":\"%(postfix)\", \"encPrefix\":\"%(encPrefix)\", \"encPostfix\":\"%(encPostfix)\", \"align\":\"%(align)\", \"alignChar\":\"%(alignChar)\", \"dataLength\": 16, \"encodeStringType\": \"%(encodeStringType)\" }}";
        Map<String, String> values = new HashMap<String, String>();
        /*
         * "returnPassProcess":{ "encryption":{ "symKey":"TESTKEY", "symkeyType": 2, "symKeyMode":32, "symKeyIv":"", "prefix":"A120980288", "postfix":"", "encPrefix":"A120980288TEST11", "encPostfix":"", "align":2, "alignChar":" ", "dataLength": 16,
         * "encodeStringType": "CP1047" }
         */
        if (hsmType == E2EEHsmType.DB_3DES_UTF8 || hsmType == E2EEHsmType.DB_MD5_UTF8 || hsmType == E2EEHsmType.DB_3DES_UTF8_PWD_ENHANCE) {
            // DB_3DES_UTF8, 信用卡會員
            // DB_MD5_UTF8, FEBO 一般用戶, 這些參數沒用, 但全景API會檢查必填, 所以隨便塞

            values.put("symKey", KEY_NAME_DB);// TESTKEY
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

        JsonElement jelement = JsonParser.parseString(replacesAllValues(template, values));
        JsonObject jsonObject = jelement.getAsJsonObject();
        if (hsmType == E2EEHsmType.DB_MD5_UTF8) {
            // DB_MD5_UTF8, FEBO 一般用戶
            jsonObject.getAsJsonObject("encryption").addProperty("hashAlg", "1");
        }
        return jsonObject;
    }


    /**
     * [提高密碼安全性]
     *  一般會員-新增額外雜湊方法 使用SHA512再包一層
     * @param pwdPrefix
     * @param pwdPostfix
     * @param acnoidPrefix
     * @param acnoidPostfix
     * @param secondHashPrefix
     * @param secondHashPostfix
     * @return
     */
    private JsonObject getReturnPassProcessEB5556986(String hsmTypeEncoding, String pwdPrefix, String pwdPostfix, String acnoidPrefix, String acnoidPostfix, String hashPrefix, String hashPostfix){
        String template = "{\"encryption\": {\"symKey\": \"%(symKey)\",\"symkeyType\": \"%(symkeyType)\",\"symKeyMode\":%(symKeyMode),\"symKeyIv\": \"%(symKeyIv)\",\"prefix\": \"%(prefix)\",\"postfix\": \"%(postfix)\",\"encPrefix\": \"%(encPrefix)\",\"encPostfix\": \"%(encPostfix)\",\"align\": \"%(align)\",\"alignChar\": \"%(alignChar)\",\"dataLength\": 16,\"hashAlg\": 5,\"hashPrefix\": \"%(hashPrefix)\",\"hashPostfix\": \"%(hashPostfix)\",\"encodeStringType\": \"%(encodeStringType)\"},\"encryption2\": {\"symKey\": \"%(symKey)\",\"symkeyType\": \"%(symkeyType)\",\"symKeyMode\":%(symKeyMode),\"symKeyIv\": \"%(symKeyIv)\",\"encPrefix\": \"\",\"encPostfix\": \"\",\"encodeStringType\": \"%(encodeStringType)\"}}";
        Map<String, String> values = new HashMap<String, String>();
		/*
		  {
		    "encryption": {
		        "symKey": "%(symKey)",
		        "symkeyType": "%(symkeyType)",
		        "symKeyMode":%(symKeyMode),
		        "symKeyIv": "%(symKeyIv)",
		        "prefix": "%(prefix)",
		        "postfix": "%(postfix)",
		        "encPrefix": "%(encPrefix)",
		        "encPostfix": "%(encPostfix)",
		        "align": "%(align)",
		        "alignChar": "%(alignChar)",
		        "dataLength": 16,
		        "hashAlg": 5,
		        "hashPrefix": "%(hashPrefix)",
		        "hashPostfix": "%(hashPostfix)",
		        "encodeStringType": "%(encodeStringType)"
		    },
		    "encryption2": {
		        "symKey": "%(symKey)",
		        "symkeyType": "%(symkeyType)",
		        "symKeyMode":%(symKeyMode),
		        "symKeyIv": "%(symKeyIv)",
		        "encPrefix": "",
		        "encPostfix": "",
		        "encodeStringType": "%(encodeStringType)"
		    }
		  }
		 */
        // 一般會員
        values.put("symKey", SYS_KEY_EB5556981);//TESTKEY
        values.put("align", "2");
        values.put("alignChar", " ");
        values.put("symkeyType", SYS_KEY_TYPE_EB5556981);//2
        values.put("symKeyMode", SYS_KEY_MODE_EB5556981);//256:CBC_ZEROPAD
        values.put("symKeyIv", SYS_KEY_IV_EB5556981);//AAAAAAAAAAA=
        values.put("prefix", StringUtils.defaultString(pwdPrefix, ""));//uid
        values.put("postfix", StringUtils.defaultString(pwdPostfix, ""));//空字串
        values.put("encPrefix", StringUtils.defaultString(acnoidPrefix, ""));//空字串
        values.put("encPostfix", StringUtils.defaultString(acnoidPostfix, ""));//空字串
        values.put("hashPrefix", StringUtils.defaultString(hashPrefix, ""));//空字串
        values.put("hashPostfix", StringUtils.defaultString(hashPostfix, ""));//空字串
        values.put("encodeStringType", hsmTypeEncoding);//CP1047

        JsonElement jelement = new JsonParser().parse(replacesAllValues(template, values));
        JsonObject jsonObject = jelement.getAsJsonObject();
        return jsonObject;
    }

    /**
     * 判斷傳入之時間因子參數，目前是否合規 1. 如果註記E2EE_VALIDATE_TIME_FLG==1目前為打開，isWithTime必為true，否則無法發送API驗證，則須先阻擋 a. isWithTime == true 則API參數傳入 withTime = 2, ignoreCheckTime = false -> 則 @return true b. isWithTime == false -> 則 @return false 2.
     * 如果註記E2EE_VALIDATE_TIME_FLG==0目前為關閉，則isWithTime為任意值皆可通過驗證，但API參數需對應調整 -> 則 @return true a. isWithTime == false 則API參數傳入 withTime = 1, ignoreCheckTime = true b. isWithTime == true 則API參數傳入 withTime = 2, ignoreCheckTime = true 3. 如果傳入的 isWithTime ==
     * null 表示此呼叫者尚未調整時間因子，不做時間因子認證 -> 則 @return true a. 則API參數傳入 withTime = 1, ignoreCheckTime = true，不做時間因子認證
     * 
     * @param isWithTime
     * @return
     */
    public static E2EEWithTimeValidType checkWithTimeIsValid(Boolean isInputWithTime, boolean isNeedWithTime) {
        if (isInputWithTime == null) {
            return E2EEWithTimeValidType.NOT_SETTING_WITH_TIME_IS_NULL;
        }
        // 判斷系統參數目前是否需要帶入時間因子
        if (isNeedWithTime) {
            if (isInputWithTime) {
                return E2EEWithTimeValidType.CHECK_TIME_NORMAL;
            }
            else {
                return E2EEWithTimeValidType.CHECK_TIME_BUT_PARAMS_INVALID;
            }
        }
        else {
            // 不需檢核時間因子
            if (isInputWithTime) {
                return E2EEWithTimeValidType.NOT_CHECK_TIME_BUT_WITH_TIME;
            }
            else {
                return E2EEWithTimeValidType.NOT_CHECK_TIME_NORMAL;
            }
        }
    }

    /**
     * [提高密碼安全性] 般會員-新增額外雜湊方法 將UID右補10碼半形空白當後綴 使用SHA512再包一層 信用卡會員-新增額外雜湊方法 使用SHA512再包一層
     * 
     * @param jsonObject
     * @param secondHashPrefix
     * @param secondHashPostfix
     * @return
     * @throws JsonSyntaxException
     */
    private JsonObject getReturnPassProcessAttachAdvencedSHA512(JsonObject jsonObject, String secondHashPrefix, String secondHashPostfix) throws JsonSyntaxException {
        // [提高密碼安全性]
        // 般會員-新增額外雜湊方法 將UID右補10碼半形空白當後綴 使用SHA512再包一層
        // 信用卡會員-新增額外雜湊方法 使用SHA512再包一層
        String secondHashTemplate = "{\"hashAlg\":\"%(secondHashAlg)\",\"prefix\":\"%(secondHashPrefix)\",\"postfix\":\"%(secondHashPostfix)\"}";
        Map<String, String> secondHashMap = new HashMap<String, String>();
        secondHashMap.put("secondHashAlg", "5"); // 5:SHA512
        secondHashMap.put("secondHashPrefix", StringUtils.defaultString(secondHashPrefix, ""));
        secondHashMap.put("secondHashPostfix", StringUtils.defaultString(secondHashPostfix, ""));
        JsonElement secondJsonElement = new JsonParser().parse(replacesAllValues(secondHashTemplate, secondHashMap));
        JsonArray hashArray = new JsonArray();
        hashArray.add(secondJsonElement);
        jsonObject.add("hash", hashArray);

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
        JsonElement jelement = JsonParser.parseString(replacesAllValues(template, values));
        return jelement.getAsJsonObject();
    }

    // 判斷目前環境是否為獨立驗證環境
    private boolean isProdStandAloneEnv() {
        logger.info("[E2EEUtils] 是否為獨立驗證環境：" + isStdAloneEnv);
        return isStdAloneEnv;
    }

    // 判斷目前使用者身分證字號是否在獨立驗證環境白名單中
    private boolean isProdStandAloneEnvUser(String companyUid) {
        if (companyUid == null) {
            return false;
        }
        SecretKeyProvider<?> provider = SecretKeyProviderFactory.getInstance().getProvider(AIBankConstants.CRYPTO_SECRET_KEY_PROVIDER_DEFAULT);
        AESCipherUtils aesCipherUtils = new AESCipherUtils(provider);

        String encryptUid = null;
        try {
            encryptUid = aesCipherUtils.encrypt(companyUid);
        }
        catch (CryptException e) {
            logger.error("[E2EEUtils][isProdStandAloneEnvUser]發生錯誤：", e);
            return false;
        }
        // 判斷使用者是否在白名單當中
        String userEncryptListStr = getSystemParamMap().get(E2EE_STD_ALONE_USER_LIST);
        String[] users = userEncryptListStr.split(",");
        List<String> userList = Arrays.asList(users);
        return userList.contains(encryptUid);
    }

    public boolean isPwdNeedWithTime() {
        String isNeedValidateTimeFlag = getSystemParamMap().get(E2EE_VALIDATE_PWD_WITH_TIME_FLG);
        return StringUtils.equals(isNeedValidateTimeFlag, "1");
    }

    public boolean isUidUuidNeedWithTime() {
        String isNeedValidateTimeFlag = getSystemParamMap().get(E2EE_VALIDATE_UID_UUID_WITH_TIME_FLG);
        return StringUtils.equals(isNeedValidateTimeFlag, "1");
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
        JsonElement jelement = JsonParser.parseString(replacesAllValues(template, values));
        return jelement.getAsJsonArray();
    }

    private JsonArray getValidatePassRule(PassRuleType ruletype) {
        JsonElement jelement = JsonParser.parseString(ruletype.getRules());
        return jelement.getAsJsonArray();
    }

    // Dead Code: Unused Method 該方法有被呼叫，應為誤判，不處理
    private JsonArray getNoPassRule() {
        JsonElement jelement = JsonParser.parseString(PassRuleType.NO_RULE.getRules());
        return jelement.getAsJsonArray();
    }

    // 取得密碼驗證rqbody
    private JsonObject getValidateRqBody(String rsaEncodedPwd, String rsaEncodedOldPwd, String rsaEncodedConfirmPwd, String uid, String uuid, String keycode, E2EEWithTimeValidType timeValidType) {

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

        // 加入時間因子
        if (timeValidType.isValid()) {
            encryptionData.addProperty("withTime", timeValidType.getIsWithTime());
            bodyChkPwdRule.addProperty("ignoreCheckTime", timeValidType.isIgnoreCheckTime());
        }

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

        //@formatter:off
        /*
         * "cluster": <叢集名稱>, "encryptionData": { 
         *   "oldData":<登入或舊密碼加密資料>, 
         *   "newData":<新密碼加密資料>,
         *    "confirmData":<新密碼確認加密資料>, 
         *    "dataType":<資料格式>, 
         *    "rsaKey":<RSA解密金鑰>, 
         *    "keycode": <虛擬鍵盤Key值> 
         *    "withTime": <是否帶入時間因子>
         *  }, 
         *    "uid":<身份證字號或統編>, 
         *    "uuid":<使用者編碼>,
         */
        //@formatter:on

        return bodyChkPwdRule;

    }

    private JsonObject getRqBody(String rsaEncodedPwd, String keycode, Boolean ignoreCheckTime) {
        return getRqBody(rsaEncodedPwd, keycode, E2EEWithTimeValidType.NOT_SETTING_WITH_TIME_IS_NULL);
    }

    private JsonObject getRqBody(String rsaEncodedPwd, String keycode, E2EEWithTimeValidType timeValidType) {

        JsonObject bodyChkPwdRule = new JsonObject();

        bodyChkPwdRule.addProperty("cluster", CLUSTER);

        JsonObject encryptionData = new JsonObject();
        encryptionData.addProperty("dataType", DATA_TYPE_PKCS7);
        encryptionData.addProperty("rsaKey", RSA_KEY_NAME);
        encryptionData.addProperty("data", rsaEncodedPwd);

        // 加密資訊是否含有時間因子
        if (timeValidType.isValid()) {
            encryptionData.addProperty("withTime", timeValidType.getIsWithTime());
            bodyChkPwdRule.addProperty("ignoreCheckTime", timeValidType.isIgnoreCheckTime());
        }

        if (StringUtils.isNotBlank(keycode)) {
            encryptionData.addProperty("keycode", keycode);
        }
        bodyChkPwdRule.add("encryptionData", encryptionData);

        //@formatter:off
        /*
         * "cluster": <叢集名稱>, 
         * "encryptionData": { \
         *   "data":<加密資料>, 
         *   "dataType":<資料格式>,
         *    "rsaKey":<RSA解密金鑰>, 
         *    "keycode": <虛擬鍵盤Key值> 
         *    "withTime": <是否帶有時間因子 1: 無時間因子/ 2:有時間因子>
         *    },
         */
        //@formatter:on

        return bodyChkPwdRule;

    }

    private JsonObject postE2EE(String urlStr, JsonObject bodyObj) throws ActionException {
        return postE2EE(urlStr, bodyObj, false);
    }

    private JsonObject postE2EE(String urlStr, JsonObject bodyObj, boolean isAlreadyRetry) throws ActionException {
        InputStreamReader streamReader = null;
        BufferedReader in = null;
        HttpURLConnection con = null;
        // OutputStream os = null;
        long start = System.currentTimeMillis();
        try {

            // 初始化 http connection
            URL url = new URL(API_URL + urlStr);

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
            // TODO #4504 0823 Unreleased Resource: Streams 調整
            try (OutputStream os = con.getOutputStream()) {
                String bodyStr = ValidateParamUtils.validParam(gson.toJson(bodyObj));
                os.write(bodyStr.getBytes("UTF-8"));
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
                        line = in.readLine();

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
                logger.info("E2EE點對點加密, 呼叫" + urlStr + "成功...");
                logger.info("HSM 回應 = {}", content);

                // 解析結果
                JsonElement jelement = JsonParser.parseString(content.toString());
                JsonObject data = jelement.getAsJsonObject();

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
                        line = in.readLine();

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
                logger.error("E2EE點對點加密 {} , 呼叫 {} 失敗 {}", url, status, content);

                JsonElement jelement = JsonParser.parseString(content.toString());
                JsonObject data = jelement.getAsJsonObject();
                String errorCode = data.get("errorCode").getAsString();
                if ("66114".equals(errorCode) && !isAlreadyRetry) {
                    logger.info("[呼叫E2EE發生錯誤]token為空");
                    // 66114 TOKEN 過期 重取
                    loadTokenVo();
                    logger.info("[呼叫E2EE發生錯誤]token為空，即將重新呼叫：" + urlStr);
                    return postE2EE(urlStr, bodyObj, true);
                }

                throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
            }

        }
        catch (IOException e) {
            logger.error("E2EE點對點加密失敗, 呼叫 {}", urlStr);
            throw ExceptionUtils.getActionException(AIBankErrorCode.HSM_RESPONSE_ERROR);
        }
        finally {
            // Close InputStreamReader
            try {
                if (streamReader != null) {
                    streamReader.close();
                }
            }
            catch (IOException e) {
                logger.warn("streamReader close failed", e);
            }
            // Close BufferedReader
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (IOException e) {
                logger.warn(e.getMessage(), e);
            }
            // Close HttpURLConnection
            if (con != null) {
                con.disconnect();
            }
            try {
                long cost = (System.currentTimeMillis() - start);
                MDC.put("cost", String.valueOf(cost));
                performanceLogger.debug("postE2EE: {}", cost);
                MDC.remove("cost");
            }
            catch (Exception e) {
                logger.warn(e.getMessage(), e);
            }
        }

    }

    @SuppressWarnings("deprecation")
    private String replacesAllValues(String template, Map<String, String> values) {
        StrSubstitutor sub = new StrSubstitutor(values, "%(", ")");
        return sub.replace(template);
    }

    private Map<String, String> systemParamMap;

    private Map<String, String> getSystemParamMap() {
        if (systemParamMap == null || checkTimeLimit()) {
            List<SystemParam> list = systemParamResource.getSystemParamList(CATEGORY_FUBON_AIBANK_APPLICATION_NAME);
            systemParamMap = list.stream().collect(Collectors.toMap(x -> x.getParamKey(), x -> x.getParamValue()));
        }
        return systemParamMap;
    }

    private String getKeyCipherHex(E2EEDBKeyType keyTYpe) {
        String keyName = Strings.EMPTY;
        switch (keyTYpe) {
        case DB_KEY:
            keyName = "CRYPT_KEY";
            break;
        case DB_KEY_AES256:
            keyName = "CRYPT_KEY_AES256";
            break;
        case DB_KEY_OTP_AES256:
            keyName = "CRYPT_KEY_OTP_AES256";

            if (StringUtils.isBlank(otpCryptKey) || checkTimeLimit()) {
                SystemParam systemParam = systemParamResource.getSystemParam("CRYPT", keyName);
                if (systemParam != null) {
                    otpCryptKey = systemParam.getParamValue();
                }
            }
            return otpCryptKey;
        }

        if (StringUtils.isBlank(cryptKey) || checkTimeLimit()) {
            SystemParam systemParam = systemParamResource.getSystemParam("CRYPT", keyName);
            if (systemParam != null) {
                cryptKey = systemParam.getParamValue();
            }
        }
        return cryptKey;
    }

    /** 標記時間 */
    private long markTime = System.currentTimeMillis();

    private boolean checkTimeLimit() {
        long currentTime = System.currentTimeMillis();
        boolean check = currentTime - markTime > (60 * 60 * 1000);
        if (check) {
            markTime = currentTime;
        }
        return check;
    }

    /**
     * 信用卡會員密碼驗證方式 OLDNEW 使用舊密碼驗證登入，並檢核新密碼正確性，NEW僅以新密碼驗證登入
     * 
     * @author jimmy.wu
     *
     */
    public enum E2eeCreditCardPwdFlag {
        OLDNEW, // OLDNEW 使用舊密碼驗證登入，並檢核新密碼正確性
        NEW; // NEW僅以新密碼驗證登入
    }

    public E2eeCreditCardPwdFlag getE2eeCreditCardPwdFlag(String companyUid) {
        // 判斷是否為獨立環境，如果為獨立環境必須吃獨立環境參數
        String pwdFlagName = E2EE_CREDITCARD_MEMBER_PWDFLAG;
        if (isProdStandAloneEnv() && isProdStandAloneEnvUser(companyUid)) {
            pwdFlagName = E2EE_STD_ALONE_CREDITCARD_MEMBER_PWDFLAG;
        }

        String pwdFlag = getSystemParamMap().get(pwdFlagName);
        E2eeCreditCardPwdFlag creditCardPwdFlag;
        if (StringUtils.isNotBlank(pwdFlag)) {
            creditCardPwdFlag = E2eeCreditCardPwdFlag.valueOf(pwdFlag.toUpperCase());
        }
        else {
            creditCardPwdFlag = E2eeCreditCardPwdFlag.OLDNEW;
        }
        logger.info("系統密碼驗證方式" + pwdFlagName + "=" + creditCardPwdFlag);
        return creditCardPwdFlag;
    }

    /**
     * 提供判斷此平台是否已開發新密碼驗證流程，且已到達可使用新密碼流程的時間
     * 
     * @return
     */
    public boolean isCCUserNewLoginProcessEnable(String companyUid) {
        boolean isCCUserNewLoginProcessEnable = false;

        // 判斷是否為獨立環境，如果為獨立環境必須吃獨立環境參數
        String avalibleFlagName = E2EE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_FLAG;
        String avalibleTimeName = E2EE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_TIME;
        if (isProdStandAloneEnv() && isProdStandAloneEnvUser(companyUid)) {
            avalibleFlagName = E2EE_STD_ALONE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_FLAG;
            avalibleTimeName = E2EE_STD_ALONE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_TIME;
        }

        String loginFlagStr = getSystemParamMap().get(avalibleFlagName);
        String avalibleTimeStr = getSystemParamMap().get(avalibleTimeName);
        Boolean loginFlag = false;
        Boolean isAvalibleTime = false;

        if (loginFlagStr != null) {
            loginFlag = Boolean.parseBoolean(loginFlagStr);
        }
        logger.info(avalibleFlagName + "[isCCUserNewLoginProcessEnable]新密碼登入驗證流程FLG是否可使用=" + loginFlag);
        logger.info(avalibleTimeName + "[isCCUserNewLoginProcessEnable]新密碼登入驗證流程啟用時間=" + loginFlag);
        if (avalibleTimeStr != null) {
            Date now = new Date();
            SimpleDateFormat dtF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date updatableTime = null;
            try {
                updatableTime = dtF.parse(avalibleTimeStr);
                isAvalibleTime = now.after(updatableTime);
            }
            catch (ParseException e) {
                logger.error("E2EE取得E2EE_CREDITCARD_MEMBER_LOGIN_AVALIBLE_TIME時間時，發生錯誤", e);
                isAvalibleTime = false;
            }
        }
        logger.info("[isCCUserNewLoginProcessEnable]新密碼登入流程，是否到了啟用時間(TIME)=" + isAvalibleTime);
        isCCUserNewLoginProcessEnable = loginFlag && isAvalibleTime;
        logger.info("[isCCUserNewLoginProcessEnable]新密碼登入流程，是否已啟用(判斷結果)=" + isCCUserNewLoginProcessEnable);
        return isCCUserNewLoginProcessEnable;
    }

    /**
     * 使用者是否已使用新密碼驗證流程
     * 
     * @param companyUid
     * @return
     */
    public String PWD_FLG_NEW_PASS = "Y";

    public boolean isCCUserLoginWithNewPassword(String userPwdFlg, String companyUid) {
        boolean isCCUserLoginWithNewPassword = false;
        // 新增一個開關，判斷該開關是否為true且已經到達啟用驗證新密碼驗證流程時間，
        // >>如果皆通過檢核，則繼續以下判斷，如未通過檢核，則使用舊密碼做登入驗證
        if (isCCUserNewLoginProcessEnable(companyUid)) {
            // 判斷目前開關狀態，OLDNEW驗證舊密碼登入、新密碼僅檢核，NEW僅驗證新密碼來登入
            /*
             * 判斷USER_PROFILE.PWD_FLAG為 null / 0 / N :此使用者僅驗舊密碼來登入 1:此使用者驗證舊密碼來登入，新密碼需驗證正確與否(不影響登入，並回寫入PWD_FLAG) Y:此使用者僅驗新密碼登入
             */
            E2eeCreditCardPwdFlag creditCardPwdFlag = getE2eeCreditCardPwdFlag(companyUid);
            logger.info("使用者密碼驗證方式，登入密碼註記 USER_PROFILE.PWD_FLAG=" + userPwdFlg);
            if (PWD_FLG_NEW_PASS.equals(userPwdFlg) && (E2eeCreditCardPwdFlag.NEW == creditCardPwdFlag)) {
                isCCUserLoginWithNewPassword = true;
            }
        }
        logger.info("使用者登入密碼驗證方式 IS_CC_USER_LOGIN_WITH_NEW_PWD? =" + isCCUserLoginWithNewPassword);
        return isCCUserLoginWithNewPassword;
    }

    /**
     * 新密碼是否已到可寫入時間
     * 
     * @param companyUid
     * @return
     */
    public boolean isPasswordAdvancedEnable(String companyUid) {
        // 判斷是否為獨立環境，如果為獨立環境必須吃獨立環境參數
        String avalibleUpdateFlagName = E2EE_PWD_ADVANCED_UPDATE_FLG;
        String avalibleUpdateTimeName = E2EE_PWD_ADVANCED_UPDATE_TIME;
        if (isProdStandAloneEnv() && isProdStandAloneEnvUser(companyUid)) {
            avalibleUpdateFlagName = E2EE_STD_ALONE_PWD_ADVANCED_UPDATE_FLG;
            avalibleUpdateTimeName = E2EE_STD_ALONE_PWD_ADVANCED_UPDATE_TIME;
        }

        String advenceFlagStr = getSystemParamMap().get(avalibleUpdateFlagName);
        String updatableTimeStr = getSystemParamMap().get(avalibleUpdateTimeName);
        Boolean advenceFlag = false;
        Boolean isAvalibleTime = false;
        if (advenceFlagStr != null) {
            advenceFlag = Boolean.parseBoolean(advenceFlagStr);
        }
        logger.info(avalibleUpdateFlagName + "新密碼FLG是否可寫入=" + advenceFlag);
        if (updatableTimeStr != null) {
            Date now = new Date();
            SimpleDateFormat dtF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date updatableTime = null;
            try {
                updatableTime = dtF.parse(updatableTimeStr);
                isAvalibleTime = now.after(updatableTime);
            }
            catch (ParseException e) {
                logger.error("E2EE取得E2EE_PWD_ADVENCE_UPDATE_TIME時間時，發生錯誤", e);
                isAvalibleTime = false;
            }
        }
        logger.info(avalibleUpdateTimeName + "新密碼是否已到可寫入時間=" + isAvalibleTime);
        return advenceFlag && isAvalibleTime;
    }

    /**
     * 判斷時間因子驗證成功/失敗，使用errorCode + failCode判斷。 若errorCode為66216，且failCode為020表示時間因子驗證失敗。
     * 
     * @param errorCode
     * @param failCode
     * @return
     */
    private boolean isTwoFactorError(String errorCode, String failCode) {
        return "66216".equals(errorCode) && "020".equals(failCode);
    }

    /**
     * 取得給前端用的時間因子
     * 
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getPassWithTime() {
        return DateFormatUtils.SQL_DATETIME_FORMAT_WITHOUT_MINISECOND.format(new Date());
    }
}