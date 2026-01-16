package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001073Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001073Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.resource.SecurityResource;
import com.tfb.aibank.chl.general.resource.dto.CreateBindDeviceRequest;
import com.tfb.aibank.chl.general.resource.dto.CreateBindDeviceResponse;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001073Task.java 
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
public class NGNOT001073Task extends AbstractAIBankBaseTask<NGNOT001073Rq, NGNOT001073Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001073Task.class);

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NGNOT001073Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001073Rq rqData, NGNOT001073Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001073 start====");

        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache == null) {
            throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }

        AIBankUser loginUser = getLoginUser();

        // 建立MOTP設備綁定
        CreateBindDeviceRequest request = new CreateBindDeviceRequest();
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
        // 裝置型號
        request.setModel(getModel());
        // 語系
        request.setLocale(getLocale().toString());
        CreateBindDeviceResponse response = securityResource.createBindDevice(request);

        if (response != null) {
            cache.setMotpMidKey(response.getMotpDeviceKey());
        }
        // fortify: Redundant Null Check
        else {
            throw new IllegalStateException("createBindDevice respons is null");
        }
        // 設置回傳資料
        // 使用者已綁定
        rsData.setUserBind(response.isUserBind());
        // 查詢使用者綁定狀態失敗
        rsData.setGetUserVaildTokenFail(response.isGetUserVaildTokenFail());
        // 建立MOTP綁定帳戶失敗
        rsData.setCreateOtpUserFail(response.isCreateOtpUserFail());
        // 取得帳戶綁定資訊失敗
        rsData.setInitPushFail(response.isInitPushFail());
        // 錯誤代碼
        rsData.setErrorCode(response.getErrorCode());
        // 錯誤訊息
        rsData.setErrorMsg(response.getErrorMsg());
        // 初始金鑰
        rsData.setIk(response.getIk());
        // 加密資料之金鑰
        rsData.setPushKey(response.getPushKey());
        // 保留欄位
        rsData.setFlag(response.getFlag());
        // App profile類型
        rsData.setType(response.getType());
        // 使用者名稱
        rsData.setAccount(response.getAccount());

        // 暫存資料
        // cacheData.setCreateBindDeviceRequest(request);
        // cacheData.setCreateBindDeviceResponse(response);
        // setCache(NDSAG003Utils.CACHE_KEY, cacheData);

        // 設置回傳資料
        // 使用者已綁定

        rsData.setMotpInitFail(response.isGetUserVaildTokenFail() || response.isCreateOtpUserFail() || response.isInitPushFail());

        // 暫存資料
        setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);

    }

}
