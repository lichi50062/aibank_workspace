package com.tfb.aibank.chl.creditcard.ag002.task;

import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002CacheData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002013Rq;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002013Rs;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002DataOutput;
import com.tfb.aibank.chl.creditcard.ag002.service.NCCAG002Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CardEmailBillModel;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCAG002013Task.java
 * 
 * <p>Description:信用卡電子帳單設定 終止電子帳單 (終止實體帳單、申請電子帳單)</p>
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
public class NCCAG002013Task extends AbstractAIBankBaseTask<NCCAG002013Rq, NCCAG002013Rs> {

    @Autowired
    private NCCAG002Service service;

    private NCCAG002DataOutput dataOutput = new NCCAG002DataOutput();

    @Override
    public void validate(NCCAG002013Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG002013Rq rqData, NCCAG002013Rs rsData) throws ActionException {
        AIBankUser loginUser = getLoginUser();

        NCCAG002CacheData cacheData = getCache(NCCAG002Service.CACHE_KEY, NCCAG002CacheData.class);

        if( cacheData != null){
            dataOutput.setRecommendInfo(cacheData.getRecommendInfo());
        }

        service.saveCancelCardEmailBill(loginUser, dataOutput);

        CardEmailBillModel resultModel = dataOutput.getCancelResultModel();
        ActionException error = null;
        try {
            // 發送電文
            service.sendCEW213R(loginUser, "Y", "N", dataOutput);
        }
        catch (ActionException e) {
            logger.error(e.getMessage(), e);
            error = e;
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            error = ExceptionUtils.getActionException(e);
        }
        // 更新結果
        service.saveCardEmailBillResult(loginUser, resultModel, dataOutput.isSend213RSuccess(), error, dataOutput);

        // 2. 信用卡電子帳單申請結果通知
        sendMail(dataOutput, error);

        if (error != null) {
            throw error;
        }
    }

    private void sendMail(NCCAG002DataOutput dataOutput, ActionException error) {
        if (error != null) {
            service.buildFailEmailParams(error, dataOutput);
        }
        else if (dataOutput.isSend213RSuccess()) {
            service.buildEmailParams(dataOutput);
        }
        else {
            service.buildUnknownEmailParams(dataOutput);
        }

        String subject = I18NUtils.getMessage("nccag002.notification.email.subject");
        this.sendTxnResultMail(subject, getLoginUser().getUserData().getEmails(), dataOutput.getParams());

    }

}
