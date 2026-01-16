package com.tfb.aibank.chl.general.ot999.task;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthVerifyData;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999040Rq;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999040Rs;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Cache;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Service;
import com.tfb.aibank.chl.type.OtpStatusType;
import com.tfb.aibank.chl.type.TxSecurityStepType;

//@formatter:off
/**
* @(#)NGNOT999040Task.java 
* 
* <p>Description:快速身份認證</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 2024/01/17, JohnChang
*  <li>初版</li6
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT999040Task extends AbstractAIBankBaseTask<NGNOT999040Rq, NGNOT999040Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT999040Task.class);

    @Autowired
    private OtpAuthService otpAuthService;

    @Autowired
    private NGNOT999Service service;

    private static final int MAX_ERROR_COUNT = 3;

    /** OTP驗證結果 成功:0 */
    private static final String OTP_RESULT_SUCCESS = "0";
    /** OTP驗證結果 驗證失敗:1 */
    private static final String OTP_RESULT_VALIDATE_ERROR = "1";
    /** OTP驗證結果 OTP失敗:2 , 不再繼續驗證 */
    private static final String OTP_RESULT_ERROR = "2";

    @Override
    public void validate(NGNOT999040Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT999040Rq rqData, NGNOT999040Rs rsData) throws ActionException {

        NGNOT999Cache cache = getCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, NGNOT999Cache.class);
        if (cache == null) {
            service.generateCallBackUrl(rsData, "9999", null, "", "O", "0", "");
            return;
        }

        // 驗證OTP
        validateOTP(rqData, rsData, cache);

        logger.debug("### TxSecurityGuard validateTxAuth ### {}", cache.getTxSecurityStepType());

    }

    /**
     * 驗證OTP
     * 
     * @param rqData
     *            OTP驗證請求資料
     * @param rsData
     *            OTP驗證回傳資料
     * @param txSecurityGuard
     *            交易安控驗證資料
     * 
     * @throws ActionException
     */
    private void validateOTP(NGNOT999040Rq rqData, NGNOT999040Rs rsData, NGNOT999Cache cache) throws ActionException {

        try {

            // 驗證安控
            OtpAuthKeepData otpAuthKeepData = cache.getKeepData();
            OtpAuthVerifyData verifyData = new OtpAuthVerifyData();
            verifyData.setUserEncTxCode(rqData.getUserEncTxCode());
            verifyData.setUserTxHash(rqData.getUserTxHash());
            verifyData.setUserOtp(rqData.getUserOtp());
            validateOTP(cache, otpAuthKeepData, verifyData);

            // 驗證後回應的StatusType
            OtpStatusType statusType = OtpStatusType.findByCode(otpAuthKeepData.getModel().getStatus());

            // OTP 驗證錯誤處理
            if (!OtpStatusType.VERIFIED_OK.equals(statusType)) {
                if (OtpStatusType.EXPIRED_ERROR.equals(statusType)) {
                    // OTP驗證逾時，不可繼續驗證
                    rsData.setOtpResult(OTP_RESULT_ERROR);
                    // 驗證碼已逾時
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.expired"));
                    cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證逾時");
                }
                else if (OtpStatusType.VERIFY_OTP_OVER_LIMIT_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(已達錯誤次數上限)，不可繼續驗證
                    rsData.setOtpResult(OTP_RESULT_ERROR);
                    // 驗證碼錯誤次數已達本次上限
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.over-limit"));
                    cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證錯誤(已達錯誤次數上限)");
                }
                else if (OtpStatusType.VERIFY_OTP_ERROR.equals(statusType)) {
                    // OTP驗證錯誤(未達錯誤次數上限)
                    rsData.setOtpResult(OTP_RESULT_VALIDATE_ERROR);
                    // 驗證失敗
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.normal"));
                    cache.setKeepData(otpAuthKeepData);
                    logger.error("OTP驗證錯誤(未達錯誤次數上限)");
                }
                else {
                    // 特殊錯誤(檢核交易代碼錯誤、檢核簡訊動態密碼生成因子失敗)，不可繼續驗證
                    rsData.setOtpResult(OTP_RESULT_ERROR);
                    // 無法執行簡訊動態密碼驗證，請重新執行交易
                    rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
                    cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
                    logger.error("OTP驗證異常,Code={},Desc={}", statusType.getCode(), statusType.getDesc());
                }
            }
            else {
                // 驗證成功
                rsData.setOtpResult(OTP_RESULT_SUCCESS);
                cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_SUCCESS);
                logger.debug("OTP驗證成功");
            }
        }
        catch (ServiceException e) {
            rsData.setOtpResult(OTP_RESULT_ERROR);
            // 無法執行簡訊動態密碼驗證，請重新執行交易
            rsData.setOtpErrorDesc(I18NUtils.getMessage("otp.auth.error.unknown"));
            cache.setTxSecurityStepType(TxSecurityStepType.OTP_AUTH_FAIL);
            logger.error("OTP驗證未知錯誤", e);
        }
        finally {
            setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);
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
     */
    public void validateOTP(NGNOT999Cache cache, OtpAuthKeepData keepData, OtpAuthVerifyData verifyData) {

        // 主機發送OTP的交給主機驗證
        if (keepData.getInitData().isTxnSendFlag()) {
            // 檢核通過
            setOtpStatus(OtpStatusType.VERIFIED_OK, keepData);
            return;
        }

        if (!otpAuthService.verifyTimeValid(keepData)) {
            // 簡訊動態密碼逾時
            setOtpStatus(OtpStatusType.EXPIRED_ERROR, keepData);
        }
        else if (!otpAuthService.verifyTxCode(keepData, verifyData.getUserEncTxCode())) {
            // 檢核交易代碼錯誤
            setOtpStatus(OtpStatusType.VERIFY_TXCODE_ERROR, keepData);
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
            otpAuthService.updateOtpRecord(keepData);
        }

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
     * 驗證OTP是否已逾時
     * 
     * @param keepData
     *            驗證流程暫存資料
     * @return
     */
    private boolean verifyTimeValid(OtpAuthKeepData keepData) {

        return keepData.getModel().getExpireTime().after(new Date());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
