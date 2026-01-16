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
package com.tfb.aibank.chl.component.security.otp;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.CryptException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.AESUtils;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.component.crypto.AESCipherUtils;
import com.ibm.tw.ibmb.component.crypto.SecretKeyProvider;
import com.ibm.tw.ibmb.component.systemparam.SystemParamCacheManager;
import com.tfb.aibank.chl.component.notification.NotificationResource;
import com.tfb.aibank.chl.component.notification.model.CreateOtpRecordRequest;
import com.tfb.aibank.chl.component.notification.model.CreateOtpRecordResponse;
import com.tfb.aibank.chl.component.notification.model.SMSNotify;
import com.tfb.aibank.chl.component.notification.model.UpdateOtpRecordRequest;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthInitData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthVerifyData;
import com.tfb.aibank.chl.component.security.otp.bean.txseed.OtpTxSeed;
import com.tfb.aibank.chl.component.security.otp.model.OtpModel;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.type.OtpStatusType;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.type.NotificationType;
import com.tfb.aibank.common.type.TxSourceType;
import com.tfb.aibank.common.util.TxCodeUtils;
import com.tfb.aibank.component.crypto.OTPSecretKeyProviderViaE2EE;

// @formatter:off
/**
 * @(#)OtpAuthService.java
 * 
 * <p>Description:OTP驗證服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 * 
 * @see TFB-SA-共通業務辦法.docx - 2.2.3.簡訊動態密碼驗證(OTP)
 */
// @formatter:on
@Service
public class OtpAuthService {

    private static final IBLog logger = IBLog.getLog(OtpAuthService.class);

    private static final String A_E_S = new String(Base64.getDecoder().decode("QUVT"), StandardCharsets.UTF_8);

    private static final int MAX_ERROR_COUNT = 3;

    public static final int DEFAULT_COUNTDOWN_SECONDS = 300;

    public static final int DEFAULT_RESEND_COUNTDOWN_SECONDS = 60;

    @Autowired
    private NotificationResource notificationResource;

    @Autowired
    private SystemParamCacheManager systemParamCacheManager;

    /**
     * 初始化並發送OTP簡訊
     * 
     * @param user
     *            使用者
     * @param initData
     *            驗證初始資料
     * @return
     * @throws ActionException
     */
    public OtpAuthKeepData sendOTP(AIBankUser user, OtpAuthInitData initData) throws ActionException {

        // 建立驗證流程暫存資料
        OtpAuthKeepData keepData = buildKeepData(user, initData);
        // 初始化成功
        setOtpStatus(OtpStatusType.INIT_OK, keepData);

        // 產生交易代碼
        setTxCode(keepData);
        // 產生加密後交易代碼並儲存加密Key
        setEncTxCode(keepData);
        // 產生交易生成碼雜湊值
        setTxFactorsHash(user, keepData);
        // 設定簡訊動態密碼生成因子成功
        setOtpStatus(OtpStatusType.ADD_SEED_OK, keepData);

        // 產生OTP
        setOtpCode(user, keepData);

        // 是否做otp寄送
        if (Boolean.TRUE.equals(initData.getIsSendOtp())) {
            // 發送OTP簡訊
            sendOtpSmsNotification(user, keepData);
            // 建立OTP發送紀錄
            createOtpRecord(keepData);
        }

        return keepData;
    }

    /**
     * 重新發送OTP簡訊
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     * @return
     * @throws ActionException
     */
    public OtpAuthKeepData resendOTP(AIBankUser user, OtpAuthKeepData keepData) throws ActionException {

        // 更新前次發送紀錄
        setOtpStatus(OtpStatusType.RESEND, keepData);
        if (Boolean.TRUE.equals(keepData.getInitData().getIsSendOtp())) {
            updateOtpRecord(keepData);
        }
        return sendOTP(user, keepData.getInitData());
    }

    /**
     * 建立驗證流程暫存資料
     * 
     * @param user
     *            使用者
     * @param initData
     *            驗證初始資料
     * @return
     */
    private OtpAuthKeepData buildKeepData(AIBankUser user, OtpAuthInitData initData) {

        OtpAuthKeepData keepData = new OtpAuthKeepData();

        // 保留驗證初始資料
        keepData.setInitData(initData);

        // OTP發送紀錄資訊
        OtpModel model = new OtpModel();
        model.setJrnNo(StringUtils.right(StringUtils.leftPad(String.valueOf(System.currentTimeMillis()), 13, "0"), 13));
        model.setTxId(initData.getTaskId());
        model.setNameCode(user.getNameCode());
        model.setUserId(user.getUserId());
        model.setMobile(initData.getOtpMobile());
        model.setCustId(user.getCustId());
        model.setUserId(user.getUserId());
        model.setCompanyKind(user.getCompanyKind());
        model.setUidDup(user.getUidDup());
        keepData.setModel(model);

        // 簡訊內容樣板
        keepData.setSmsMsgTemplate(initData.getSendMessage());

        return keepData;
    }

    /**
     * 產生交易代碼
     * 
     * @param keepData
     *            驗證流程暫存資料
     */
    private void setTxCode(OtpAuthKeepData keepData) {

        String txCode = keepData.getInitData().getTxCode();

        // 沒有指定交易代碼才產生
        if (StringUtils.isBlank(txCode)) {
            try {
                txCode = TxCodeUtils.genTxCode();
            }
            catch (Exception ex) {
                logger.error("### OtpVerify ### generate txCode error", ex);
                throw new IllegalArgumentException(OtpStatusType.GENERATE_TXCODE_ERROR.getCode());
            }
        }

        keepData.getModel().setTxCode(txCode);
    }

    /**
     * 產生加密後交易代碼並儲存加密Key
     * 
     * @param keepData
     *            驗證流程暫存資料
     */
    public void setEncTxCode(OtpAuthKeepData keepData) {

        if (StringUtils.isBlank(keepData.getModel().getTxCode())) {
            logger.error("### OtpVerify ### txCode empty error");
            throw new IllegalArgumentException(OtpStatusType.GENERATE_TXCODE_ERROR.getCode());
        }

        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(A_E_S);
            keyGen.init(128);
            SecretKey key = keyGen.generateKey();
            SecretKeySpec aesSessionKey = new SecretKeySpec(key.getEncoded(), A_E_S);
            String encodedAesSessionKey = Base64.getEncoder().encodeToString(aesSessionKey.getEncoded());
            keepData.setAesSessionKey(encodedAesSessionKey);
            byte[] encryptBytes = AESUtils.encryptBytes(keepData.getModel().getTxCode().getBytes(), aesSessionKey);
            String encTxCode = new String(Hex.encodeHex(encryptBytes));
            keepData.setEncTxCode(encTxCode);
        }
        catch (Exception ex) {
            logger.error("### OtpVerify ### encrypt txCode error", ex);
            throw new IllegalArgumentException(OtpStatusType.GENERATE_TXCODE_ERROR.getCode());
        }
    }

    /**
     * 產生交易生成碼雜湊值
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     * @return
     */
    private void setTxFactorsHash(AIBankUser user, OtpAuthKeepData keepData) {

        byte[] bHashArr = DigestUtils.sha256(genTxFactors(user, keepData.getInitData()));
        String hashTxFactor = new String(Hex.encodeHex(bHashArr));

        keepData.setHashTxFactor(hashTxFactor);
    }

    /**
     * 組合該次交易相關參數
     * 
     * @param user
     *            使用者
     * @param initData
     *            驗證初始資料
     * @return
     */
    private String genTxFactors(AIBankUser user, OtpAuthInitData initData) {

        StringBuilder sb = new StringBuilder();
        sb.append(user.getCustId()).append("&");
        sb.append(user.getNameCode()).append("&");
        sb.append(user.getUserId()).append("&");
        sb.append(initData.getTxFactors());

        return sb.toString();
    }

    /**
     * 產生OTP代碼
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     * @throws ActionException
     */
    private void setOtpCode(AIBankUser user, OtpAuthKeepData keepData) throws ActionException {
        setOtpCode(user, keepData, 6);
    }

    private void setOtpCode(AIBankUser user, OtpAuthKeepData keepData, int otpLength) throws ActionException {

        if (!StringUtils.equals(keepData.getModel().getStatus(), OtpStatusType.ADD_SEED_OK.getCode())) {
            logger.error("### OtpVerify ### genOTPCode status error");
            throw new IllegalArgumentException(OtpStatusType.ERROR.getCode());
        }

        String hashStr = "";
        /** 避免產生出來的hash內的數字少於6位，則必須重新產生 */
        while (StringUtils.isBlank(hashStr) || hashStr.length() < otpLength) {
            genTotalFactors(user, keepData);
            hashStr = DigestUtils.sha256Hex(keepData.getTotalFactors());
            hashStr = hashStr.replaceAll("[\\D]", "");
        }

        String cryptModeOtp = systemParamCacheManager.getValue("AIBANK", "CRYPTO_MODE_OTP");

        String otp = null;
        if (Boolean.parseBoolean(cryptModeOtp)) {
            try {
                AESCipherUtils aesCipherUtils = initAesCipherUtils();
                String encryptStr = aesCipherUtils.encrypt(hashStr);
                String encryptStrHash = DigestUtils.sha256Hex(encryptStr).replaceAll("[\\D]", "");
                otp = StringUtils.left(encryptStrHash, otpLength);
            }
            catch (Exception e) {
                logger.error("加密 OTP 失敗", e);
                throw new ActionException(AIBankErrorCode.TX_SECURITY_INIT_ERROR);
            }
        }
        else {
            otp = StringUtils.left(hashStr, otpLength);
        }

        keepData.getModel().setCreateTime(new Date());
        keepData.getModel().setExpireTime(DateUtils.addSeconds(keepData.getModel().getCreateTime(), DEFAULT_COUNTDOWN_SECONDS));
        keepData.getModel().setGenPassTime(keepData.getModel().getCreateTime());
        keepData.getModel().setOtpPass(otp);
        keepData.getModel().setTryCount(0);
        keepData.getModel().setTxData(keepData.getTotalFactors());
    }

    /**
     * 2024/07 E2EE AES256 SMS encrypt upgrade
     * 
     * @param applicationName
     *            來源平台名稱：B2C、EXT、BFS、CME
     * @return
     */
    private static AESCipherUtils initAesCipherUtils() {
        SecretKeyProvider<?> provider = new OTPSecretKeyProviderViaE2EE();
        return new AESCipherUtils(provider);
    }

    /**
     * 組成OTP的所有資料
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     */
    private void genTotalFactors(AIBankUser user, OtpAuthKeepData keepData) {

        StringBuilder sb = new StringBuilder();
        sb.append(genTxFactors(user, keepData.getInitData())).append("&");
        sb.append(System.currentTimeMillis()).append("&");
        sb.append(new SecureRandom().nextDouble());
        keepData.setTotalFactors(sb.toString());
    }

    /**
     * 發送OTP簡訊
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     * @return
     * @throws ActionException
     */
    private void sendOtpSmsNotification(AIBankUser user, OtpAuthKeepData keepData) throws ActionException {
        SMSNotify request = new SMSNotify();
        request.setTxSource(TxSourceType.AI_BANK_FOR_NOTIFICATION.getCode());
        request.setNotifyType(NotificationType.SMS.getCode());
        request.setTxId(keepData.getModel().getTxId());
        request.setUserId(keepData.getModel().getUserId());
        request.setPhoneNumber(keepData.getModel().getMobile());

        // 組裝OTP簡訊訊息
        String otpPass = keepData.getModel().getOtpPass();
        String txCode = keepData.getModel().getTxCode();
        String txName = keepData.getInitData().getTaskName();
        String sendMessage = keepData.getSmsMsgTemplate();
        sendMessage = StringUtils.replace(sendMessage, OtpTxSeed.OTP_REPLACER, otpPass);
        sendMessage = StringUtils.replace(sendMessage, OtpTxSeed.TX_CODE_REPLACER, txCode);
        sendMessage = StringUtils.replace(sendMessage, OtpTxSeed.TX_NAME_REPLACER, StringUtils.defaultString(txName));
        request.setMessage(sendMessage);

        // 簡訊發送
        notificationResource.sendSMS(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), request);

    }

    public void sendOtpSmsNotification(String custId, String uidDup, String userId, Integer companyKind, OtpAuthKeepData keepData) throws ActionException {
        SMSNotify request = new SMSNotify();
        request.setTxSource(TxSourceType.AI_BANK_FOR_NOTIFICATION.getCode());
        request.setNotifyType(NotificationType.SMS.getCode());
        request.setTxId(keepData.getModel().getTxId());
        request.setUserId(keepData.getModel().getUserId());
        request.setPhoneNumber(keepData.getModel().getMobile());

        // 組裝OTP簡訊訊息
        String otpPass = keepData.getModel().getOtpPass();
        String txCode = keepData.getModel().getTxCode();
        String txName = keepData.getInitData().getTaskName();
        String sendMessage = keepData.getSmsMsgTemplate();
        sendMessage = StringUtils.replace(sendMessage, OtpTxSeed.OTP_REPLACER, otpPass);
        sendMessage = StringUtils.replace(sendMessage, OtpTxSeed.TX_CODE_REPLACER, txCode);
        sendMessage = StringUtils.replace(sendMessage, OtpTxSeed.TX_NAME_REPLACER, StringUtils.defaultString(txName));
        request.setMessage(sendMessage);

        // 簡訊發送
        try {
            notificationResource.sendSMS(custId, uidDup, userId, companyKind, request);
            logger.info("OK");
        }
        catch (ServiceException ex) {
            logger.error("", ex);
        }

    }

    /**
     * 建立OTP發送紀錄
     * 
     * @param keepData
     *            驗證流程暫存資料
     * @return
     */
    public void createOtpRecord(OtpAuthKeepData keepData) {
        CreateOtpRecordRequest request = new CreateOtpRecordRequest();
        request.setAcessLogKey(keepData.getModel().getAcessLogKey());
        request.setCreateTime(keepData.getModel().getCreateTime());
        request.setExpireTime(keepData.getModel().getExpireTime());
        request.setGenPassTime(keepData.getModel().getGenPassTime());
        request.setJrnNo(keepData.getModel().getJrnNo());
        request.setMobile(keepData.getModel().getMobile());
        request.setNameCode(keepData.getModel().getNameCode());
        request.setOtpPass(keepData.getModel().getOtpPass());
        request.setPicVerify(keepData.getModel().getPicVerify());
        request.setStatus(keepData.getModel().getStatus());
        request.setStatusTime(keepData.getModel().getStatusTime());
        request.setTryCount(keepData.getModel().getTryCount());
        request.setTxCode(keepData.getModel().getTxCode());
        request.setTxData(keepData.getModel().getTxData());
        request.setTxId(keepData.getModel().getTxId());
        request.setCustId(keepData.getModel().getCustId());
        request.setUserId(keepData.getModel().getUserId());
        request.setCompanyKind(keepData.getModel().getCompanyKind());
        request.setUidDup(keepData.getModel().getUidDup());
        CreateOtpRecordResponse response = notificationResource.createOtpRecord(request);
        keepData.getModel().setOtpKey(response.getOtpKey());
    }

    /**
     * 設定各時期OTP狀態與更新時間
     * 
     * @param otpStatus
     *            OTP狀態
     * @param keepData
     *            驗證流程暫存資料
     */
    private void setOtpStatus(OtpStatusType otpStatus, OtpAuthKeepData keepData) {

        keepData.getModel().setStatus(otpStatus.getCode());
        keepData.getModel().setStatusTime(new Date());
        logger.debug("### OtpVerify ### Status:{}, UpdateTime:{}", keepData.getModel().getStatus(), DateUtils.getISODateTimeStr(keepData.getModel().getStatusTime()));
    }

    /**
     * 驗證安控
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     * @param verifyData
     *            前端上送驗證資料
     */
    public void validateOTP(AIBankUser user, OtpAuthKeepData keepData, OtpAuthVerifyData verifyData) {

        // 主機發送OTP的交給主機驗證
        if (keepData.getInitData().isTxnSendFlag()) {
            // 檢核通過
            setOtpStatus(OtpStatusType.VERIFIED_OK, keepData);
            return;
        }

        if (!verifyTimeValid(keepData)) {
            // 簡訊動態密碼逾時
            setOtpStatus(OtpStatusType.EXPIRED_ERROR, keepData);
        }
        else if (!verifyTxCode(keepData, verifyData.getUserEncTxCode())) {
            // 檢核交易代碼錯誤
            setOtpStatus(OtpStatusType.VERIFY_TXCODE_ERROR, keepData);
        }
        else if (!verifyHashTxFactor(user, keepData, verifyData.getUserTxHash())) {
            // 檢核簡訊動態密碼生成因子失敗
            setOtpStatus(OtpStatusType.VERIFY_SEED_ERROR, keepData);
        }
        else if (!verifyOtpCode(keepData, verifyData.getUserOtp())) {
            keepData.getModel().setTryCount(keepData.getModel().getTryCount() + 1);
            if (keepData.getModel().getTryCount() >= MAX_ERROR_COUNT) {
                // 檢核交易驗證碼錯誤超過上限
                setOtpStatus(OtpStatusType.VERIFY_OTP_OVER_LIMIT_ERROR, keepData);
            }
            else {
                // 檢核交易驗證碼錯誤
                setOtpStatus(OtpStatusType.VERIFY_OTP_ERROR, keepData);
            }
        }
        else {
            // 檢核通過
            setOtpStatus(OtpStatusType.VERIFIED_OK, keepData);
        }
        if (Boolean.TRUE.equals(keepData.getInitData().getIsSendOtp())) {
            // 更新OTP發送紀錄
            updateOtpRecord(keepData);
        }

    }

    /**
     * 驗證安控
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     * @param verifyData
     *            前端上送驗證資料
     * @param maxErrorCount
     *            最大錯誤次數
     */
    public void validateOTP(AIBankUser user, OtpAuthKeepData keepData, OtpAuthVerifyData verifyData, int maxErrorCount) {

        // 主機發送OTP的交給主機驗證
        if (keepData.getInitData().isTxnSendFlag()) {
            // 檢核通過
            setOtpStatus(OtpStatusType.VERIFIED_OK, keepData);
            return;
        }

        if (!verifyTimeValid(keepData)) {
            // 簡訊動態密碼逾時
            setOtpStatus(OtpStatusType.EXPIRED_ERROR, keepData);
        }
        else if (!verifyTxCode(keepData, verifyData.getUserEncTxCode())) {
            // 檢核交易代碼錯誤
            setOtpStatus(OtpStatusType.VERIFY_TXCODE_ERROR, keepData);
        }
        else if (!verifyHashTxFactor(user, keepData, verifyData.getUserTxHash())) {
            // 檢核簡訊動態密碼生成因子失敗
            setOtpStatus(OtpStatusType.VERIFY_SEED_ERROR, keepData);
        }
        else if (!verifyOtpCode(keepData, verifyData.getUserOtp())) {
            keepData.getModel().setTryCount(keepData.getModel().getTryCount() + 1);
            if (keepData.getModel().getTryCount() >= maxErrorCount) {
                // 檢核交易驗證碼錯誤超過上限
                setOtpStatus(OtpStatusType.VERIFY_OTP_OVER_LIMIT_ERROR, keepData);
            }
            else {
                // 檢核交易驗證碼錯誤
                setOtpStatus(OtpStatusType.VERIFY_OTP_ERROR, keepData);
            }
        }
        else {
            // 檢核通過
            setOtpStatus(OtpStatusType.VERIFIED_OK, keepData);
        }
        if (Boolean.TRUE.equals(keepData.getInitData().getIsSendOtp())) {
            // 更新OTP發送紀錄
            updateOtpRecord(keepData);
        }

    }

    /**
     * 驗證OTP是否已逾時
     * 
     * @param keepData
     *            驗證流程暫存資料
     * @return
     */
    public boolean verifyTimeValid(OtpAuthKeepData keepData) {

        return keepData.getModel().getExpireTime().after(new Date());
    }

    /**
     * 驗證交易代碼是否一致
     * 
     * @param keepData
     *            驗證流程暫存資料
     * @param userEncTxCode
     *            前端回傳的交易代碼
     * @return
     */
    public boolean verifyTxCode(OtpAuthKeepData keepData, String userEncTxCode) {

        if (!StringUtils.equals(keepData.getEncTxCode(), userEncTxCode)) {
            return false;
        }

        try {
            byte[] decodedAesSessionKey = Base64.getDecoder().decode(keepData.getAesSessionKey());
            SecretKeySpec aesSessionKey = new SecretKeySpec(decodedAesSessionKey, 0, decodedAesSessionKey.length, A_E_S);
            byte[] bPlain = AESUtils.decryptBytes(Hex.decodeHex(userEncTxCode.toCharArray()), aesSessionKey);
            return StringUtils.equals(new String(bPlain, StandardCharsets.UTF_8), keepData.getModel().getTxCode());
        }
        catch (CryptException | DecoderException ex) {
            logger.error("### OtpVerify ### decrypt txCode error", ex);
            return false;
        }

    }

    /**
     * 驗證交易生成碼雜湊值
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     * @param userTxHash
     *            前端回傳的交易生成碼雜湊值
     * @return
     */
    private boolean verifyHashTxFactor(AIBankUser user, OtpAuthKeepData keepData, String userTxHash) {

        // 用留存的交易資訊再Hash一次, 與頁面傳回的TxHash比對
        setTxFactorsHash(user, keepData);

        return StringUtils.equals(keepData.getHashTxFactor(), userTxHash);
    }

    /**
     * 驗證交易驗證碼
     * 
     * @param keepData
     *            驗證流程暫存資料
     * @param userOTP
     *            前端上送OTP值
     * @return
     */
    private boolean verifyOtpCode(OtpAuthKeepData keepData, String userOTP) {

        return StringUtils.equals(keepData.getModel().getOtpPass(), userOTP);
    }

    /**
     * 更新OTP發送紀錄
     * 
     * @param keepData
     *            驗證流程暫存資料
     * @return
     */
    public void updateOtpRecord(OtpAuthKeepData keepData) {
        UpdateOtpRecordRequest request = new UpdateOtpRecordRequest();
        request.setOtpKey(keepData.getModel().getOtpKey());
        request.setAcessLogKey(keepData.getModel().getAcessLogKey());
        request.setCreateTime(keepData.getModel().getCreateTime());
        request.setExpireTime(keepData.getModel().getExpireTime());
        request.setGenPassTime(keepData.getModel().getGenPassTime());
        request.setJrnNo(keepData.getModel().getJrnNo());
        request.setMobile(keepData.getModel().getMobile());
        request.setNameCode(keepData.getModel().getNameCode());
        request.setOtpPass(keepData.getModel().getOtpPass());
        request.setPicVerify(keepData.getModel().getPicVerify());
        request.setStatus(keepData.getModel().getStatus());
        request.setStatusTime(keepData.getModel().getStatusTime());
        request.setTryCount(keepData.getModel().getTryCount());
        request.setTxCode(keepData.getModel().getTxCode());
        request.setTxData(keepData.getModel().getTxData());
        request.setTxId(keepData.getModel().getTxId());
        request.setCustId(keepData.getModel().getCustId());
        request.setUserId(keepData.getModel().getUserId());
        request.setCompanyKind(keepData.getModel().getCompanyKind());
        request.setUidDup(keepData.getModel().getUidDup());
        notificationResource.updateOtpRecord(request);
    }

    /**
     * SHA256
     * 
     * @param rawData
     * @return
     */
    public String sha256Hex(String rawData) {
        return DigestUtils.sha256Hex(rawData);
    }

}
