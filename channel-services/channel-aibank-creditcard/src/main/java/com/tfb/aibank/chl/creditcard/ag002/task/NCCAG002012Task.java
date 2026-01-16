package com.tfb.aibank.chl.creditcard.ag002.task;

import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002CacheData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002012Rq;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002012Rs;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002DataOutput;
import com.tfb.aibank.chl.creditcard.ag002.service.NCCAG002Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CardEmailBillModel;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG002012Task.java
 * 
 * <p>Description:信用卡電子帳單設定 終止電子帳單寄送Email</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2023/08/22, Aaron	
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCAG002012Task extends AbstractAIBankBaseTask<NCCAG002012Rq, NCCAG002012Rs> {

    @Autowired
    private NCCAG002Service service;

    private NCCAG002DataOutput dataOutput = new NCCAG002DataOutput();

    @Override
    public void validate(NCCAG002012Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG002012Rq rqData, NCCAG002012Rs rsData) throws ActionException {

        AIBankUser loginUser = getLoginUser();

        NCCAG002CacheData cacheData = getCache(NCCAG002Service.CACHE_KEY, NCCAG002CacheData.class);

        if( cacheData != null){
            dataOutput.setRecommendInfo(cacheData.getRecommendInfo());
        }

        service.saveCancelCardEmailBill(loginUser, dataOutput);

        CardEmailBillModel resultModel = dataOutput.getCancelResultModel();
        ActionException error = null;
        try {
            service.sendCEW213R(loginUser, "N", "Y", dataOutput);
        }
        catch (ActionException e) {
            logger.error(e.getMessage(), e);
            error = e;
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            error = ExceptionUtils.getActionException(e);
        }

        service.saveCardEmailBillResult(loginUser, resultModel, dataOutput.isSend213RSuccess(), error, dataOutput);

        if (error != null) {
            throw error;
        }
    }

}
