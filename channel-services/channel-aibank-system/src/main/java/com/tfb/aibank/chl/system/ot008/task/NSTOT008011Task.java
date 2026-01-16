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
package com.tfb.aibank.chl.system.ot008.task;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.devicebinding.DeviceBindingService;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusCondition;
import com.tfb.aibank.chl.component.devicebinding.model.CheckUserDeviceStatusResult;
import com.tfb.aibank.chl.component.userdatacache.SecurityResource;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagRequest;
import com.tfb.aibank.chl.component.userdatacache.model.BindingAuthFlagResponse;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008011Rq;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008011Rs;
import com.tfb.aibank.chl.system.ot008.model.NSTOT008CacheData;

// @formatter:off
/**
 * @(#)NSTOT008011Task.java
 * 
 * <p>Description:裝置綁定完成通知</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/09/11, HankChan	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT008011Task extends AbstractAIBankBaseTask<NSTOT008011Rq, NSTOT008011Rs> {

    @Autowired
    private DeviceBindingService deviceBindingService;

    @Autowired
    private SecurityResource securityResource;

    @Override
    public void validate(NSTOT008011Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NSTOT008011Rq rqData, NSTOT008011Rs rsData) throws ActionException {

        // 重新檢查使用者與裝置綁定狀態
        CheckUserDeviceStatusCondition condition = new CheckUserDeviceStatusCondition();
        condition.setDeviceIxd(getDeviceIxd());
        condition.setLoginUser(getLoginUser());
        condition.setLocale(getLocale().toString());
        CheckUserDeviceStatusResult result = new CheckUserDeviceStatusResult();
        deviceBindingService.checkUserDeviceStatus(condition, result);

        // 發送Email通知
        sendEmailNotify(rqData, result.getUserBinding().getDeviceModel());

        NSTOT008CacheData cache = getCache("NSTOT008_CACHE", NSTOT008CacheData.class);

        if (cache == null) {
            cache = new NSTOT008CacheData();
        }

        if (cache.getAuthType() > 0) {
            AIBankUser user = getLoginUser();

            BindingAuthFlagRequest request = new BindingAuthFlagRequest();
            request.setAction(1);
            request.setCustId(user.getCustId());
            request.setUserId(user.getUserId());
            request.setUidDup(user.getUidDup());
            request.setCompanyKind(user.getCompanyKind());

            // 無卡提款
            if (cache.getAuthType() == 1) {
                request.setWithdrawalFlag(1);
            }
            // 手機號碼轉帳
            if (cache.getAuthType() == 2) {
                request.setPhoneTransferFlag(1);
            }
            // 變更轉帳額度
            if (cache.getAuthType() == 3) {
                request.setRaiseTransferFlag(1);
            }

            BindingAuthFlagResponse response = securityResource.getBindingAuthFlag(request);

            rsData.setStatus(response.getStatus());
        }

    }

    /**
     * 發送Email通知
     * 
     * @param error
     * @throws
     */
    private void sendEmailNotify(NSTOT008011Rq rqData, String deviceModel) {
        // 產生Email通知的內容
        Map<String, String> params = new HashMap<>();
        // 標題
        params.put("title", rqData.isChangeDevice() ? I18NUtils.getMessage("nstot008.email.title.changebind") : I18NUtils.getMessage("nstot008.email.title.bind"));
        // 交易結果
        params.put("result", I18NUtils.getMessage("nstot008.email.result"));
        // 交易時間
        params.put("txTime", DateUtils.getDateTimeStr(new Date()));
        // 裝置型號
        params.put("deviceModel", deviceModel);
        // 交易名稱
        params.put("txnName", rqData.isChangeDevice() ? I18NUtils.getMessage("nstot008.email.txnname.changebind") : I18NUtils.getMessage("nstot008.email.txnname.bind"));
        // 主旨
        // 台北富邦行動銀行「裝置綁定」結果通知
        // 台北富邦行動銀行變更「裝置綁定」結果通知
        String subject = rqData.isChangeDevice() ? I18NUtils.getMessage("nstot008.email.subject.changebind") : I18NUtils.getMessage("nstot008.email.subject.bind");

        // 寄送交易結果Email通知
        this.sendTxnResultMail(subject, getLoginUser().getUserData().getEmails(), params);
    }

}