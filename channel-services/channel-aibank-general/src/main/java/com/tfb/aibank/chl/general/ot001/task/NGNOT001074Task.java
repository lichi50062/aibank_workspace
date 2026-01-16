package com.tfb.aibank.chl.general.ot001.task;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001074Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001074Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.dto.ConfirmBindDeviceRequest;
import com.tfb.aibank.chl.general.resource.dto.ConfirmBindDeviceResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001074Task.java 
* 
* <p>Description:移轉MOTP</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230622, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001074Task extends AbstractAIBankBaseTask<NGNOT001074Rq, NGNOT001074Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001074Task.class);

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NGNOT001074Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001074Rq rqData, NGNOT001074Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001074 start====");

        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache == null) {
            throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }

        AIBankUser loginUser = getLoginUser();

        // 確認建立MOTP設備綁定
        ConfirmBindDeviceRequest request = new ConfirmBindDeviceRequest();
        // 身分證字號
        request.setCustId(loginUser.getCustId());
        // 使用者代號
        request.setUserId(loginUser.getUserId());
        // 公司類型
        request.setCompanyKind(loginUser.getCompanyKind());
        // 誤別碼
        request.setUidDup(loginUser.getUidDup());
        // 裝置ID
        request.setDeviceIxd(getDeviceIxd());
        // SDK addProfile response deviceID
        request.setDeviceId(rqData.getDeviceId());
        // SDK addProfile response ClientID
        request.setClientId(rqData.getClientId());
        // SDK addProfile response SN
        request.setSn(rqData.getSn());
        // SDK addProfile response pushID
        request.setPushId(rqData.getPushId());
        // 裝置代碼
        request.setModel(getModel());
        // 裝置作業系統
        request.setDevicePlatform(this.getPlatformDisplay());
        // 裝置作業系統版本
        request.setDevicePlatformVersion(getVersion());
        // ClientIP
        request.setClientIp(getClientIp());
        // SessionID
        request.setSessionId(getSessionId());
        // MOTP MID認證資料鍵值
        request.setMotpMidDataKeyList(Arrays.asList(cache.getMotpMidKey()));
        // MOTP綁定時約定條款版本
        // TODO
        request.setMotpTermsVer("1.0");

        ConfirmBindDeviceResponse response = securityResource.confirmBindDevice(request);

        // 成功，發送通知
        if (!response.isRegisterTokenFail()) {
            cache.setMotpSetting(LoginService.TRANSFER_SUCC);
        }
        else {
            cache.setMotpSetting(LoginService.TRANSFER_FAIL);
        }

        // 暫存資料
        setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);

    }

}
