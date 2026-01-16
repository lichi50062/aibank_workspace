package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001070Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001070Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001070Task.java 
* 
* <p>Description:移轉設定動畫頁</p>
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
public class NGNOT001070Task extends AbstractAIBankBaseTask<NGNOT001070Rq, NGNOT001070Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001070Task.class);

    @Override
    public void validate(NGNOT001070Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001070Rq rqData, NGNOT001070Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001070 start====");

        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache == null) {
            throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }
        rsData.setTransferNeed(cache.isTransferNeed());

        cache.setNotificationAll(rqData.isNotificationAll());
        if (rqData.isNotificationAll()) {
            cache.setTransferNeed(true);
        }
        setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);

        // MOTP if true 須在動畫背景進行綁定
        rsData.setMotpSetting(cache.getMotpSetting() == LoginService.IS_TRANSFER);
        rsData.setOnboardingType(getLoginUser().getOnboardingType());
    }

}
