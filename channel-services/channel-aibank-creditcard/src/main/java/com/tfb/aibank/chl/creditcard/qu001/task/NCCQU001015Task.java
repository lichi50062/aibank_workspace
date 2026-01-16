package com.tfb.aibank.chl.creditcard.qu001.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.code.CommonErrorCode;
import com.ibm.tw.ibmb.util.ExceptionUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001Bill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001015Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001015Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RRepeat;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RResponse;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001015Task.java
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
public class NCCQU001015Task extends AbstractAIBankBaseTask<NCCQU001015Rq, NCCQU001015Rs> {

    @Autowired
    private NCCQU001Utils utils;

    @Autowired
    private NCCQU001Service service;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private CurrencyCodeCacheManager currencyCodeCacheManager;

    @Override
    public void validate(NCCQU001015Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCQU001015Rq rqData, NCCQU001015Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        String cardKey = rqData.getCardKey();
        List<NCCQQU001CardData> cardInfos = cache.getCardInfos();
        List<NCCQQU001CardData> underCardInfos = cache.getUnderCardInfos();
        Optional<NCCQQU001CardData> opt = cardInfos.stream().filter(cardInfo -> StringUtils.equals(cardKey, cardInfo.getCardKey())).findAny();
        if (opt.isPresent()) {
            NCCQQU001CardData additionalCard = opt.get();
            if (!additionalCard.isAdditional())
                return;

            CEW205RResponse response = new CEW205RResponse();

            // 卡號查
            try {
                CreditCard creditCard = aibankUser.getAllCreditCards().stream().filter(card -> StringUtils.equals(card.getCardKey(), additionalCard.getCardKey())).findFirst().orElse(new CreditCard());
                response = utils.sendCEW205R("", creditCard.getCardNo());
                // 設定已經發過電文
                additionalCard.setSendCew205r(true);
                if (CollectionUtils.isNotEmpty(response.getTxRepeats())) {
                    List<CEW205RRepeat> cew205RRepeats = cache.getCew205RRepeats();
                    cew205RRepeats.addAll(response.getTxRepeats());
                    List<NCCQQU001Bill> bills = new ArrayList<>();
                    // 整理一整包消費明細
                    service.transCew205R(cew205RRepeats, bills, userDataCacheService, currencyCodeCacheManager, getLocale(), cardInfos, underCardInfos);
                }
            }
            catch (ServiceException e) {
                logger.warn("取得本期未出帳消費明細 sendCEW205R 查詢失敗:", e);
                rsData.setCew205rError(true);
            }
            setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
        }
        else {
            throw ExceptionUtils.getActionException(CommonErrorCode.ARGUMENTS_NOT_FOUND);
        }
    }

}