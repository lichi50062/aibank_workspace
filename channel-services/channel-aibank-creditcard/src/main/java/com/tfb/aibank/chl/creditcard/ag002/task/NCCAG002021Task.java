package com.tfb.aibank.chl.creditcard.ag002.task;

import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002CacheData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.i18n.I18NUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002021Rq;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002021Rs;
import com.tfb.aibank.chl.creditcard.ag002.model.NCCAG002DataOutput;
import com.tfb.aibank.chl.creditcard.ag002.service.NCCAG002Service;
import com.tfb.aibank.chl.creditcard.resource.dto.CardEmailBillModel;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 *
 * <p>Description: 信用卡電子帳單設定 條款頁 </p>
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
public class NCCAG002021Task extends AbstractAIBankBaseTask<NCCAG002021Rq, NCCAG002021Rs> {

    @Autowired
    private NCCAG002Service service;

    @Override
    public void validate(NCCAG002021Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCAG002021Rq rqData, NCCAG002021Rs rsData) throws ActionException {
        NCCAG002DataOutput dataOutput = new NCCAG002DataOutput();
        AIBankUser user = getLoginUser();

        NCCAG002CacheData cacheData = getCache(NCCAG002Service.CACHE_KEY, NCCAG002CacheData.class);
        if( cacheData != null){
            dataOutput.setRecommendInfo(cacheData.getRecommendInfo());
        }

        service.saveApplyCardEmailBill(user, dataOutput);

        CardEmailBillModel resultModel = dataOutput.getCancelResultModel();
        ActionException error = null;
        try {
            // 發送電文
            service.sendCEW213R(user, "Y", "N", dataOutput);

        }
        catch (ActionException e) {
            logger.error(e.getMessage(), e);
            error = service.handleException(getAccessLog(), e);
        }
        catch (ServiceException e) {
            logger.error(e.getMessage(), e);
            error = service.handleException(getAccessLog(), e);
        }
        // 更新結果
        service.saveCardEmailBillResult(user, resultModel, dataOutput.isSend213RSuccess(), error, dataOutput);

        // Email通知
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
