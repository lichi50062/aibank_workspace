package com.tfb.aibank.chl.creditcard.ag004.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.ag004.cache.NCCAG004CacheData;
import com.tfb.aibank.chl.creditcard.ag004.model.NCCAG004030Rq;
import com.tfb.aibank.chl.creditcard.ag004.model.NCCAG004030Rs;
import com.tfb.aibank.chl.creditcard.ag004.service.NCCAG004Service;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.UpdateCardNotificationRequest;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCAG004030Task.java
* 
* <p>Description:刷卡通知設定</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/08/14, John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCAG004030Task extends AbstractAIBankBaseTask<NCCAG004030Rq, NCCAG004030Rs> {

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private UserDataCacheService userDataCacheSerivce;

    @Override
    public void validate(NCCAG004030Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCAG004030Rq rqData, NCCAG004030Rs rsData) throws ActionException {

        NCCAG004CacheData cache = getCache(NCCAG004Service.NCCAG004_CACHE_KEY, NCCAG004CacheData.class);

        CreditCard managedCard = null;
        String managedCardNo = "";
        for (CreditCard card : cache.getAllCreditCards()) {
            if (card.getCardKey().equals(rqData.getCardKey())) {
                managedCard = card;
                managedCardNo = card.getCardNo();
                break;
            }
        }

        if (StringUtils.isBlank(managedCardNo)) {
            throw new ActionException(ErrorCode.CARD_NO_NOT_PASSED_IN);
        }

        AIBankUser loginUser = getLoginUser();
        UpdateCardNotificationRequest request = new UpdateCardNotificationRequest();
        request.setCustId(loginUser.getCustId());
        request.setUserId(loginUser.getUserId());
        request.setNameCode(loginUser.getNameCode());
        request.setCustCDNO(managedCardNo);
        request.setCustANOS(rqData.isRadio() ? "Y" : "N");

        creditCardResource.updateCardNotification(request, loginUser.getCompanyKind(), loginUser.getUidDup());

        if (StringUtils.isNotBlank(request.getCustANOS()) && managedCard != null)
            managedCard.setApplyConsumeMessage(request.getCustANOS());
        userDataCacheSerivce.updateCachedCreditCard(loginUser, managedCard);
    }

}
