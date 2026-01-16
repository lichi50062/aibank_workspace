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
package com.tfb.aibank.chl.component.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.component.task.Task;
import com.tfb.aibank.chl.component.task.TaskCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.type.SecurityOtpType;
import com.tfb.aibank.chl.type.SecurityType;
import com.tfb.aibank.chl.type.TxSecurityType;
import com.tfb.aibank.common.error.AIBankErrorCode;
import com.tfb.aibank.common.error.MbErrorCode;

// @formatter:off
/**
 * @(#)TxSecurityService.java
 * 
 * <p>Description:交易功能與適用安控項目服務</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/05/27, HankChan
 * <ol>
 *  <li>初版</li>
 * </ol>
 * 
 * @see TFB-SA-共通業務辦法.docx - 2.2.2. 交易功能與適用安控項目定義
 */
// @formatter:on
@Service
public class TxSecurityService {

    private static final IBLog logger = IBLog.getLog(TxSecurityService.class);

    @Autowired
    private TaskCacheManager taskCacheManager;

    @Autowired
    private UserDataCacheService userDataCacheService;

    /**
     * 取得交易安控機制
     * 
     * @param txSecurityGuard
     *            交易安控驗證資料
     * @param user
     *            使用者
     * @param forceCheck
     *            是否強制檢查
     * 
     * @throws ActionException
     */
    public void checkTxSecurityType(TxSecurityGuard txSecurityGuard, AIBankUser user, boolean forceCheck) throws ActionException {
        logger.info("＃checkTxSecurityType[have forceCheck]＃, txSecurityGuard：{}", IBUtils.attribute2Str(txSecurityGuard));
        Task task = taskCacheManager.getTaskById(txSecurityGuard.getTaskId());

        if (!forceCheck && SecurityType.findByType(task.getSecurityTypes()).isPassInitCheck()) {
            txSecurityGuard.setLazyInit(true);
        }
        else {
            checkTxSecurityType(txSecurityGuard, user);
        }

    }

    /**
     * 取得交易安控機制
     * 
     * @param txSecurityGuard
     *            交易安控驗證資料
     * @param user
     *            使用者
     * 
     * @throws ActionException
     */
    public void checkTxSecurityType(TxSecurityGuard txSecurityGuard, AIBankUser user) throws ActionException {
        logger.info("＃checkTxSecurityType＃, txSecurityGuard：{}", IBUtils.attribute2Str(txSecurityGuard));
        Task task = taskCacheManager.getTaskById(txSecurityGuard.getTaskId());

        SecurityType securityType = SecurityType.findByType(task.getSecurityTypes());
        TxSecurityType txSecurityType;
        logger.info("＃checkTxSecurityType＃, securityType：{}，", IBUtils.attribute2Str(securityType));

        SecurityOtpType securityOtpType = SecurityOtpType.findByType(task.getSecurityOtpTypes());
        txSecurityGuard.setSecurityOtpType(securityOtpType);

        if (securityType.isNone()) {
            txSecurityType = TxSecurityType.NONE;
        }
        else if (securityType.isOtp() || securityType.isOtpPass()) {

            if (checkOTP(txSecurityGuard, user)) {
                txSecurityType = TxSecurityType.OTP;
            }
            else {
                throw ExceptionUtils.getActionException(txSecurityGuard.getError());
            }
        }
        else if (securityType.isMotp() || securityType.isMotpPass()) {
            // #1294 [共用] 安控機制檢核邏輯調整
            if (user.getCustomerType().isGeneral() && checkMOTP(txSecurityGuard, user)) {
                txSecurityType = TxSecurityType.MOTP;
            }
            else {
                throw ExceptionUtils.getActionException(txSecurityGuard.getError());
            }
        }
        else if (securityType.isOtpAndMotp() || securityType.isOtpAndMotpPass()) {
            // #1294 [共用] 安控機制檢核邏輯調整
            if (user.getCustomerType().isGeneral()) {
                // 一般會員
                if (checkMOTP(txSecurityGuard, user)) {
                    txSecurityType = TxSecurityType.MOTP;
                }
                else if (checkOTP(txSecurityGuard, user)) {
                    txSecurityType = TxSecurityType.OTP;
                }
                else {
                    logger.error("MOTP、OTP無法使用");

                    if (txSecurityGuard.getError().equals(MbErrorCode.MB3504.getError())) {
                        throw ExceptionUtils.getActionException(txSecurityGuard.getError());
                    }

                    throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_INIT_MOTP_ERROR);
                }
            }
            else {
                // #7668 [TASK.SECURITY_TYPES] = 3 or 13 且 為信用卡會員登入 > 固定寫死 AS400
                txSecurityGuard.setSecurityOtpType(SecurityOtpType.AS400);
                if (checkOTP(txSecurityGuard, user)) {
                    txSecurityType = TxSecurityType.OTP;
                }
                else {
                    logger.error("卡會員OTP無法使用");
                    throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_INIT_OTP_AS400_ERROR);
                }
            }

        }
        else {
            logger.error("[TASK.SECURITY_TYPES]設定有誤");
            throw ExceptionUtils.getActionException(AIBankErrorCode.TX_SECURITY_INIT_ERROR);
        }

        txSecurityGuard.setTxSecurityType(txSecurityType);
    }

    /**
     * 驗證是否可使用交易指定之OTP使用類型
     * 
     * @param txSecurityGuard
     *            交易安控驗證資料
     * @param user
     *            使用者
     * @param taskId
     *            交易代號
     * @return
     */
    private boolean checkOTP(TxSecurityGuard txSecurityGuard, AIBankUser user) {

        SecurityOtpType securityOtpType = txSecurityGuard.getSecurityOtpType();

        if (securityOtpType.isOnSite()) {
            // 取得OTP申請註記：EB552170SvcRs.OTPFLG
            // OTPFLG = Y，表示已申請，取得發送簡訊OTP的行動電話：EB552170SvcRs.OTP_MOBILE
            // OTPFLG = N，表示未申請，導至「共通錯誤頁」
            String otpFlag = userDataCacheService.getOtpFlagFromEB552170(user);
            String otpMobile = userDataCacheService.getOtpMobileFromEB552170(user);
            if (StringUtils.equals("Y", otpFlag) && StringUtils.isNotBlank(otpMobile)) {
                txSecurityGuard.setOtpMobile(otpMobile);
                return true;
            }
            else {
                logger.error("OTP未啟用或OTP行動電話沒有資料");
                txSecurityGuard.setError(AIBankErrorCode.TX_SECURITY_INIT_OTP_ONSITE_ERROR.getError());
                return false;
            }
        }
        else if (securityOtpType.isCif()) {
            // 取得CIF留存的行動電話：EB5556981SvcRs.MOBILE_NO
            // 若有值，進入交易
            // 若無值，導至「共通錯誤頁」
            if (StringUtils.isNotBlank(user.getLoginMsgRs().getMobileNo())) {
                txSecurityGuard.setOtpMobile(user.getLoginMsgRs().getMobileNo());
                return true;
            }
            else {
                logger.error("CIF留存的行動電話沒有資料");
                txSecurityGuard.setError(MbErrorCode.MB3504.getError());
                return false;
            }
        }
        else if (securityOtpType.isAs400()) {
            // 取得AS400留存的行動電話：CEW013R.MOBILE
            // 若有值，進入交易
            // 若無值，導至「共通錯誤頁」
            String mobileNo = userDataCacheService.getOtpMobileFromCEW013R(user);
            if (StringUtils.isNotBlank(mobileNo)) {
                txSecurityGuard.setOtpMobile(mobileNo);
                return true;
            }
            else {
                logger.error("AS400留存的行動電話沒有資料");
                txSecurityGuard.setError(AIBankErrorCode.TX_SECURITY_INIT_OTP_AS400_ERROR.getError());
                return false;
            }
        }
        else if (securityOtpType.isCifAndAs400()) {
            if (user.getCustomerType().isGeneral()) {
                // 取得CIF留存的行動電話：EB5556981SvcRs.MOBILE_NO
                // 若有值，進入交易
                // 若無值，導至「共通錯誤頁」
                if (StringUtils.isNotBlank(user.getLoginMsgRs().getMobileNo())) {
                    txSecurityGuard.setOtpMobile(user.getLoginMsgRs().getMobileNo());
                    return true;
                }
                else {
                    logger.error("CIF留存的行動電話沒有資料");
                    txSecurityGuard.setError(MbErrorCode.MB3504.getError());
                    return false;
                }
            }
            else if (user.getCustomerType().isCardMember()) {
                // 取得AS400留存的行動電話：CEW013R.MOBILE
                // 若有值，進入交易
                // 若無值，導至「共通錯誤頁」
                String mobileNo = userDataCacheService.getOtpMobileFromCEW013R(user);
                if (StringUtils.isNotBlank(mobileNo)) {
                    txSecurityGuard.setOtpMobile(mobileNo);
                    return true;
                }
                else {
                    logger.error("AS400留存的行動電話沒有資料");
                    txSecurityGuard.setError(AIBankErrorCode.TX_SECURITY_INIT_OTP_AS400_ERROR.getError());
                    return false;
                }
            }
            return false;
        }
        else {
            logger.error("[TASK.SECURITY_OTP_TYPES]設定有誤");
            txSecurityGuard.setError(AIBankErrorCode.TX_SECURITY_INIT_ERROR.getError());
            return false;
        }

    }

    /**
     * 驗證是否可使用MOTP
     * 
     * @param txSecurityGuard
     *            交易安控驗證資料
     * @param user
     *            使用者
     * @return
     */
    private boolean checkMOTP(TxSecurityGuard txSecurityGuard, AIBankUser user) {

        if (StringUtils.equals("Y", userDataCacheService.getMotpFlag(user, txSecurityGuard.getDeviceIxd()))) {
            return true;
        }
        else {
            logger.error("MOTP未啟用");
            txSecurityGuard.setError(AIBankErrorCode.TX_SECURITY_INIT_MOTP_ERROR.getError());
            return false;
        }
    }

}
