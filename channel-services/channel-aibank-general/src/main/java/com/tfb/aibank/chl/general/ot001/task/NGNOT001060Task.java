package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001060Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001060Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.service.LoginService;
import com.tfb.aibank.common.error.AIBankErrorCode;

//@formatter:off
/**
* @(#)NGNOT001060Task.java 
* 
* <p>Description:自訂項目移轉頁</p>
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
public class NGNOT001060Task extends AbstractAIBankBaseTask<NGNOT001060Rq, NGNOT001060Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001060Task.class);

    @Override
    public void validate(NGNOT001060Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001060Rq rqData, NGNOT001060Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001060 start====");

        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);
        if (cache == null) {
            throw new ActionException(AIBankErrorCode.DATA_NOT_FOUND);
        }

        rsData.setBindingStatus(cache.isOtpAuthed());
        rsData.setNotification(cache.getNotification() == LoginService.IS_TRANSFER);
        rsData.setQuickSearch(cache.getQuickSearch() == LoginService.IS_TRANSFER);
        rsData.setNoCardwithDraw(cache.getNoCardwithDraw() == LoginService.IS_TRANSFER);
        rsData.setMotpSetting(cache.getMotpSetting() == LoginService.IS_TRANSFER);
        rsData.setPhoneTransfer(cache.getPhoneTransfer() == LoginService.IS_TRANSFER);
        rsData.setTransferQuota(cache.getTransferQuota() == LoginService.IS_TRANSFER);
    }

}
