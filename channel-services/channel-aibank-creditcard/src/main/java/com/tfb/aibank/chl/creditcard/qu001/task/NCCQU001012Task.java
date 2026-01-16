package com.tfb.aibank.chl.creditcard.qu001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001012Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001012Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001012Task.java
* 
* <p>Description:信用卡總覽 功能首頁 取得信用卡帳務資料</p>
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
public class NCCQU001012Task extends AbstractAIBankBaseTask<NCCQU001012Rq, NCCQU001012Rs> {

    @Autowired
    private NCCQU001Utils utils;

    @Override
    public void validate(NCCQU001012Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU001012Rq rqData, NCCQU001012Rs rsData) throws ActionException {
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        if (cache == null) {
            throw ExceptionUtils.getActionException(CommonErrorCode.SESSION_EXPIRED);
        }
        AIBankUser aiBankUser = getLoginUser();
        boolean hasMasterCard = utils.hasPrimaryCard(cache.getCardInfos());
        try {
            // 信用卡身分別為“正卡人”時，以身分證號查詢CEW303R取得信用卡帳務資料
            if (hasMasterCard) {
                CEW303RResponse response = utils.sendCEW303R(aiBankUser.getCustId(), null);
                cache.setCew303r(response);
            }
            else {
                // 無持有正卡、以信用卡會員登入且為附卡人身分，以卡號查詢CEW303R取得信用卡帳務資料
                if (cache.isAdditionalCardholder()) {
                    CEW303RResponse response = utils.sendCEW303R(StringUtils.EMPTY, aiBankUser.getCardUserProfileVo().getCardNo());
                    cache.setCew303r(response);
                }
            }
        }
        catch (ServiceException e) {
            logger.warn("取得信用卡帳務資料 sendCEW303R 查詢失敗:", e);
            rsData.setCew303rError(hasMasterCard);
            rsData.setCew303rErrorCode(e.getErrorCode());
        }
        // 信用卡帳務資料
        rsData.setCew303r(cache.getCew303r());
        setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
    }

}