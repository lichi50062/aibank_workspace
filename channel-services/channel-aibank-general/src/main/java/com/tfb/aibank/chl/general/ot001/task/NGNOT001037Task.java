package com.tfb.aibank.chl.general.ot001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.log.IBLog;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001037Rq;
import com.tfb.aibank.chl.general.ot001.model.NGNOT001037Rs;
import com.tfb.aibank.chl.general.ot001.task.service.DeviceBindingCache;
import com.tfb.aibank.chl.general.resource.UserResource;
import com.tfb.aibank.chl.general.service.LoginService;

//@formatter:off
/**
* @(#)NGNOT001037Task.java 
* 
* <p>Description:已驗過OTP</p>
* 
* <p>Modify History:</p>
* <ol>1.0, 20230928, JohnChang
*  <li>初版</li>
* </ol>
* <ol>[版號累加], [日期], [作者]
*  <li>[異動說明]</li>
* </ol>
*/
//@formatter:on

@Component
@Scope("prototype")
public class NGNOT001037Task extends AbstractAIBankBaseTask<NGNOT001037Rq, NGNOT001037Rs> {

    private static final IBLog logger = IBLog.getLog(NGNOT001037Task.class);

    @Autowired
    UserResource userResource;

    @Override
    public void validate(NGNOT001037Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NGNOT001037Rq rqData, NGNOT001037Rs rsData) throws ActionException {
        logger.debug("==== NGNOT001037 start====");
        DeviceBindingCache cache = getCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, DeviceBindingCache.class);

        cache.setOtpAuthed(rqData.isOtpAuthed());

        if (rqData.isOtpAuthed()) {
            try {
                // 重置交易安控
                super.resetTxSecurity();
                // 啟動交易安控驗證
                super.initTxSecurity();
            }catch(Throwable e ) {
                logger.error("Reset TxSecurity Failed ", e);
            }
        }
        
        setCache(LoginService.DEVICE_TRANSFER_CACHE_KEY, cache);
    }

}
