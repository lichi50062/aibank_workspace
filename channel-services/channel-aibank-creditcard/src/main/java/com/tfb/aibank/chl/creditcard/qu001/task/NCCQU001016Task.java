package com.tfb.aibank.chl.creditcard.qu001.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001BonusRewards;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001016Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001016Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;

//@formatter:off
/**
* @(#)NCCQU001016Task.java
* 
* <p>Description:信用卡總覽 功能首頁 取得紅利回饋</p>
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
public class NCCQU001016Task extends AbstractAIBankBaseTask<NCCQU001016Rq, NCCQU001016Rs> {

    @Autowired
    private NCCQU001Service service;

    @Override
    public void validate(NCCQU001016Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCQU001016Rq rqData, NCCQU001016Rs rsData) throws ActionException {
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        // 紅利、哩程回饋
        try {
            NCCQQU001BonusRewards bonusRewards = new NCCQQU001BonusRewards();
            service.getBouns(cache.getCew314r().getB500Repeats(), bonusRewards);
            rsData.setBonusRewards(bonusRewards);
        }
        catch (ServiceException e) {
            logger.warn("取得紅利、哩程回饋 sendCEW306R 查詢失敗:", e);
            rsData.setCew306rError(true);
        }
    }

}