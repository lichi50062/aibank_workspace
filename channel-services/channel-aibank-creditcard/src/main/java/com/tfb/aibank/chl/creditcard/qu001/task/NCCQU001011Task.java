package com.tfb.aibank.chl.creditcard.qu001.task;
import com.ibm.tw.commons.exception.ServiceException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001Bill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001011Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001011Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW205RRepeat;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001011Task.java
* 
* <p>Description:信用卡總覽 功能首頁 取得本期未出帳消費明細</p>
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
public class NCCQU001011Task extends AbstractAIBankBaseTask<NCCQU001011Rq, NCCQU001011Rs> {
    @Autowired
    private NCCQU001Service service;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private CurrencyCodeCacheManager currencyCodeCacheManager;

    @Autowired
    private NCCQU001Utils utils;

    @Override
    public void validate(NCCQU001011Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCQU001011Rq rqData, NCCQU001011Rs rsData) throws ActionException {
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        AIBankUser aiBankUser = getLoginUser();
        String custId = aiBankUser.getCustId();

        List<NCCQQU001CardData> cardInfos = cache.getCardInfos();
        List<NCCQQU001CardData> underCardInfos = cache.getUnderCardInfos();
        try {
            List<CEW205RRepeat> cew205RRepeats = cache.getCew205RRepeats();
            if (CollectionUtils.isEmpty(cew205RRepeats)) {
                cew205RRepeats = new ArrayList<>();
                List<CreditCard> creditCards = aiBankUser.getAllCreditCards();
                service.getConsumerDetails(custId, cardInfos, creditCards, cew205RRepeats);
                cache.setCew205RRepeats(cew205RRepeats);
            }

            if (CollectionUtils.isNotEmpty(cew205RRepeats)) {
                BigDecimal totalAmount = utils.getTotalAmountFromCEW205R(cew205RRepeats);
                rsData.setTotalAmount(totalAmount);

                List<NCCQQU001Bill> bills = new ArrayList<>();
                // 整理一整包消費明細
                service.transCew205R(cew205RRepeats, bills, userDataCacheService, currencyCodeCacheManager, getLocale(), cardInfos, underCardInfos);

                // 下次結帳日
                rsData.setNextCheckoutDate(cew205RRepeats.get(0).getNcCday());

                if (aiBankUser.getCustomerType().isCardMember() && aiBankUser.getCompanyKind() == 4) {
                    logger.debug("信用卡附卡使用者不需要無中生有已停用卡片與排序");
                }
                else {
                    // 無中生有已停用卡片
                    service.createSomethingNothing(cew205RRepeats, cache.getCardInfos(), cache.getUnderCardInfos());
                    // 有消費明細後 將卡片資料排序
                    service.checkCew205rHasData(cew205RRepeats, cache.getCardInfos(), cache.getUnderCardInfos());
                }
                rsData.setBills(bills);
            }

        }
        catch (ServiceException e) {
            logger.warn("取得本期未出帳消費明細 sendCEW205R 查詢失敗:", e);
            rsData.setCew205rError(true);
        }
        setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
    }

}