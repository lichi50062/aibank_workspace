package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001072Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001072Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001072Task.java 
* 
* <p>Description:記錄移轉項目</p>
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
public class NGNOT001072Task extends AbstractAIBankBaseTask<NGNOT001072Rq, NGNOT001072Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001072Task.class);

    @Override
    public void validate(NGNOT001072Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001072Rq rqData, NGNOT001072Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001072 start====");

        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache == null) {
            throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }
        cache.setNotification(rqData.isNotification() ? 1 : 0);
        cache.setQuickSearch(rqData.isQuickSearch() ? 1 : 0);
        cache.setMotpSetting(rqData.isMotpSetting() ? 1 : 0);
        cache.setNoCardwithDraw(rqData.isNoCardwithDraw() ? 1 : 0);
        cache.setPhoneTransfer(rqData.isPhoneTransfer() ? 1 : 0);
        cache.setTransferQuota(rqData.isTransferQuota() ? 1 : 0);

        cache.setTransferNeed(rqData.isNotification() || //
                rqData.isQuickSearch() || //
                rqData.isMotpSetting() || //
                rqData.isNoCardwithDraw() || //
                rqData.isPhoneTransfer() || //
                rqData.isTransferQuota()

        );

        setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);
    }

}
