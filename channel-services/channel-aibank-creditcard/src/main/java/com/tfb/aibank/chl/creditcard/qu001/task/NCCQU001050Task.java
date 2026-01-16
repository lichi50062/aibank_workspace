package com.tfb.aibank.chl.creditcard.qu001.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001Bill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001050Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001050Rs;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW231RResponse;

//@formatter:off
/**
* @(#)NCCQU001050Task.java
* 
* <p>Description:信用卡總覽 繳款紀錄頁</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/24, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU001050Task extends AbstractAIBankBaseTask<NCCQU001050Rq, NCCQU001050Rs> {
    @Autowired
    private NCCQU001Service service;
    @Autowired
    private NCCQU001Utils utils;

    @Override
    public void validate(NCCQU001050Rq rqData) throws ActionException {
        logger.debug("NCCQU001050 validate....");
    }

    @Override
    public void handle(NCCQU001050Rq rqData, NCCQU001050Rs rsData) throws ActionException {
        String custId = getLoginUser().getCustId();
        // 發送電文CEW230R取得最近繳款通路與明細
        CEW231RResponse response = new CEW231RResponse();
        try {
            response = utils.sendCEW231R(custId);
            List<NCCQQU001Bill> bills = new ArrayList<>();
            service.transCew231R(response, bills);
            rsData.setBills(bills);
        }
        catch (ServiceException e) {
            logger.error("取得最近繳款通路與明細 ssendCEW230R 查詢失敗:", e);
            ActionException aex = ExceptionUtils.getActionException(e);
            throw aex;
        }
        catch (Exception e) {
            throw new ActionException("取得最近繳款通路與明細 ssendCEW230R 查詢失敗", ErrorCode.PAYMENT_CHANNEL_AND_DETAILS_ERROR);
        }
    }

}