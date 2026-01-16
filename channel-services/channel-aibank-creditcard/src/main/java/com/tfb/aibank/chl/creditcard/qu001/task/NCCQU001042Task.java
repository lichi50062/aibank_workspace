package com.tfb.aibank.chl.creditcard.qu001.task;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001042Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001042Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0051Response;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001042Task.java
* 
* <p>Description:信用卡總覽 歷史帳單頁 costco refresh</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/31, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU001042Task extends AbstractAIBankBaseTask<NCCQU001042Rq, NCCQU001042Rs> {
    @Autowired
    private NCCQU001Utils utils;

    @Override
    public void validate(NCCQU001042Rq rqData) throws ActionException {
        logger.debug("NCCQU001042 validate....");
        if (StringUtils.isBlank(rqData.getMonthType()) || StringUtils.notAllEquals(rqData.getMonthType(), "1", "2", "3", "4", "5", "6", "0")) {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

    @Override
    public void handle(NCCQU001042Rq rqData, NCCQU001042Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        String monthType = rqData.getMonthType();
        // Costco
        VB0051Response vb0051Response = cache.getVb0051();
        if (vb0051Response == null) {
            try {
                vb0051Response = utils.sendVB0051(aibankUser.getCustId(), monthType);
                cache.setVb0051(vb0051Response);
                rsData.setSignFlg3(vb0051Response.getSignFlg3());
                rsData.setAddamt(vb0051Response.getAddamt() == null ? BigDecimal.ZERO : vb0051Response.getAddamt());
            }
            catch (ServiceException e) {
                logger.warn("取得好多金資料 sendVB0051 查詢失敗:", e);
                rsData.setCostcoError(true);
            }
        }
        setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
    }

}