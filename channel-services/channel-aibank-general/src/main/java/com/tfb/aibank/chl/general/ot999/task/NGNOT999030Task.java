package com.tfb.aibank.chl.general.ot999.task;

import java.security.SecureRandom;
import java.util.Date;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.ibm.tw.commons.util.DataMaskUtil;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.security.otp.OtpAuthService;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthInitData;
import com.tfb.aibank.chl.component.security.otp.bean.OtpAuthKeepData;
import com.tfb.aibank.chl.component.security.otp.bean.txseed.OtpTxSeedCommon;
import com.tfb.aibank.chl.component.security.otp.model.OtpModel;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999030Rq;
import com.tfb.aibank.chl.general.ot999.model.NGNOT999030Rs;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Cache;
import com.tfb.aibank.chl.general.ot999.task.service.NGNOT999Service;
import com.tfb.aibank.chl.general.resource.dto.RetriveDeviceStatusResponse;
import com.tfb.aibank.chl.type.OtpStatusType;
import com.tfb.aibank.common.util.TxCodeUtils;

//@formatter:off
/**
* @(#)NGNOT999020Task.java 
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
public class NGNOT999030Task extends AbstractAIBankBaseTask<NGNOT999030Rq, NGNOT999030Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT999030Task.class);

    @Autowired
    private OtpAuthService otpAuthService;

    @Autowired
    private NGNOT999Service service;

    public static final int DEFAULT_COUNTDOWN_SECONDS = 300;

    public static final int DEFAULT_RESEND_COUNTDOWN_SECONDS = 60;

    @Override
    public void validate(NGNOT999030Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT999030Rq rqData, NGNOT999030Rs rsData) throws ActionException {

        NGNOT999Cache cache = getCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, NGNOT999Cache.class);
        if (cache == null) {
            service.generateCallBackUrl(rsData, "9999", null, "", "O", "0", "");
            return;
        }

        // 產生OTP驗證資料
        generateOTP(rsData, cache);

        // 設定回傳資料
        rsData.setPhone(DataMaskUtil.maskMobile(cache.getMobileNo()));
        rsData.setTxCode(cache.getKeepData().getModel().getTxCode());
        rsData.setUserEncTxCode(cache.getKeepData().getEncTxCode());
        rsData.setUserTxHash(cache.getKeepData().getHashTxFactor());
        rsData.setCountdownSeconds(OtpAuthService.DEFAULT_COUNTDOWN_SECONDS);
        rsData.setCanResend(true);
        rsData.setResendCountdownSeconds(OtpAuthService.DEFAULT_RESEND_COUNTDOWN_SECONDS);

        setCache(NGNOT999Service.FAST_VALIDATE_CACHE_KEY, cache);
    }

    /**
     * 產生OTP驗證資料
     * 
     * @param rqData
     *            請求資料
     * @param rsData
     *            回傳資料
     * @param txSecurityGuard
     *            交易安控驗證資料
     * 
     * @throws ActionException
     */
    private void generateOTP(NGNOT999030Rs rsData, NGNOT999Cache cache) throws ActionException {

        RetriveDeviceStatusResponse user = cache.getRetriveDeviceStatusResponse();

        OtpAuthKeepData keepData = buildKeepData(user, cache);

        keepData.setAesSessionKey(method);

        /** 產生加密後交易代碼並儲存加密Key */
        otpAuthService.setEncTxCode(keepData);

        /** 產生 OTP */
        setOtpCode(cache, keepData);

        /** 發送 SMS */
        otpAuthService.sendOtpSmsNotification(user.getCustId(), user.getUidDup(), user.getUserId(), user.getCompanyKind(), keepData);

        /** 建立OTP發送紀錄 */
        otpAuthService.createOtpRecord(keepData);

        cache.setKeepData(keepData);
    }

    private OtpAuthKeepData buildKeepData(RetriveDeviceStatusResponse userDeviceStatus, NGNOT999Cache cache) {

        OtpAuthKeepData keepData = new OtpAuthKeepData();
        OtpTxSeedCommon OtpTxSeed = new OtpTxSeedCommon();

        // OTP發送紀錄資訊
        OtpModel model = new OtpModel();
        model.setJrnNo(StringUtils.right(StringUtils.leftPad(String.valueOf(System.currentTimeMillis()), 13, "0"), 13));
        model.setTxId("NGNOT999");
        model.setNameCode("0001");
        
        model.setMobile(cache.getMobileNo());
        if (userDeviceStatus == null || StringUtils.isBlank(userDeviceStatus.getCustId()) ) {
            logger.debug("3層式由Cache出使用者相關參數");
            model.setUserId(cache.getUserId());
            model.setCustId(cache.getCustId());
            model.setCompanyKind(cache.getCompanyKind());
            model.setUidDup(cache.getUserIdDup());
        }else {
            model.setUserId(userDeviceStatus.getUserId());
            model.setCustId(userDeviceStatus.getCustId());
            model.setUserId(userDeviceStatus.getUserId());
            model.setCompanyKind(userDeviceStatus.getCompanyKind());
            model.setUidDup(userDeviceStatus.getUidDup());
        }
        
        model.setTxCode(TxCodeUtils.genTxCode());
        keepData.setModel(model);
        OtpAuthInitData initData = new OtpAuthInitData();
        initData.setTaskName(cache.getChannelName());
        keepData.setInitData(initData);
        keepData.setSmsMsgTemplate(OtpTxSeed.getSendMessage());

        return keepData;
    }

    /**
     * 產生OTP代碼
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     */
    private void setOtpCode(NGNOT999Cache cache, OtpAuthKeepData keepData) {

        String hashStr = "";
        /** 避免產生出來的hash內的數字少於6位，則必須重新產生 */
        while (StringUtils.isBlank(hashStr) || hashStr.length() < 6) {
            genTotalFactors(cache, keepData);
            hashStr = DigestUtils.sha256Hex(keepData.getTotalFactors());
            hashStr = hashStr.replaceAll("[\\D]", "");
        }
        String otp = StringUtils.left(hashStr, 6);

        keepData.getModel().setCreateTime(new Date());
        keepData.getModel().setExpireTime(DateUtils.addSeconds(keepData.getModel().getCreateTime(), DEFAULT_COUNTDOWN_SECONDS));
        keepData.getModel().setGenPassTime(keepData.getModel().getCreateTime());
        keepData.getModel().setOtpPass(otp);
        keepData.getModel().setTryCount(0);
        keepData.getModel().setTxData(keepData.getTotalFactors());
        keepData.getModel().setStatus(OtpStatusType.INIT_OK.getCode());
        keepData.getModel().setStatusTime(new Date());
    }

    /**
     * 組成OTP的所有資料
     * 
     * @param user
     *            使用者
     * @param keepData
     *            驗證流程暫存資料
     */
    private void genTotalFactors(NGNOT999Cache cache, OtpAuthKeepData keepData) {

        StringBuilder sb = new StringBuilder();
        sb.append(cache.getRetriveDeviceStatusResponse().getCustId());
        sb.append(cache.getRetriveDeviceStatusResponse().getUserId());
        sb.append(keepData.getModel().getTxCode());
        sb.append(System.currentTimeMillis()).append("&");
        sb.append(new SecureRandom().nextDouble());
        keepData.setTotalFactors(sb.toString());
    }

    @Override
    protected boolean isRequireLogin() {
        return false;
    }
}
