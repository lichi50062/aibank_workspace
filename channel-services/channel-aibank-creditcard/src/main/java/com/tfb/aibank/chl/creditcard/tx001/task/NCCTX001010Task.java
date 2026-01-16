package com.tfb.aibank.chl.creditcard.tx001.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.ibmb.base.model.BaseServiceResponse;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContent;
import com.tfb.aibank.chl.component.remarkcontent.RemarkContentCacheManager;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.error.ErrorCode;
import com.tfb.aibank.chl.creditcard.resource.CreditCardResource;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW302RRes;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW302RResRep;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001010Rq;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001010Rs;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001CardInfo;
import com.tfb.aibank.chl.creditcard.tx001.model.NCCTX001Output;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001CacheVo;
import com.tfb.aibank.chl.creditcard.tx001.service.NCCTX001Service;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.chl.type.RemarkContentType;

//@formatter:off
/**
* @(#)NCCTX001010Task.java
*
* <p>Description:預借現金 功能首頁</p>
*
* <p>Modify History:</p>
* v1.0, 2023/09/22 John Chang
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCTX001010Task extends AbstractAIBankBaseTask<NCCTX001010Rq, NCCTX001010Rs> {
    @Autowired
    protected NCCTX001Service service;

    @Autowired
    private CreditCardResource creditCardResource;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Autowired
    private RemarkContentCacheManager remarkContentCacheManager;

    private NCCTX001Output dataOutput = new NCCTX001Output();

    @Override
    public void validate(NCCTX001010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCTX001010Rq rqData, NCCTX001010Rs rsData) throws ActionException {

        AIBankUser aibankUser = getLoginUser();

        /** 是否為特殊戶或未有信用卡 */
        userDataCacheService.validateCreditCardStatus(getLoginUser());

        /** 未持有信用卡 */
        userDataCacheService.getAllCreditCards(aibankUser, getLocale());

        List<CreditCard> availableCards = userDataCacheService.getEffectiveCreditCards(aibankUser, getLocale());

        if (availableCards == null || availableCards.size() == 0) {
            throw new ActionException(ErrorCode.NO_AVAILABLE_CEEDIT_CARD);
        }
        boolean isHaveActiveCard = false;
        for (CreditCard card : availableCards) {
            if ("Y".equals(card.getCardActiveCode())) {
                isHaveActiveCard = true;
                break;
            }
        }
        // 沒有已開卡的卡
        if (!isHaveActiveCard) {
            throw new ActionException(ErrorCode.CREDIT_CARD_NOT_FOUND);
        }

        // 發查CEW302R，取得信用卡的可用預借現金額度
        BaseServiceResponse<CEW302RRes> res = creditCardResource.sendCEW302R(aibankUser.getCustId());
        if (res.getStatus().isError()) {
            throw new ActionException(ErrorCode.NO_MONEY_CREDIT_CARD);
        }

        List<NCCTX001CardInfo> ncctx001Cards = new ArrayList<NCCTX001CardInfo>();
        List<CreditCard> cards = new ArrayList<CreditCard>();

        double maxBalanceAvailable = 0;
        CreditCard maxCreditCard = null;

        for (CreditCard card : availableCards) {
            for (CEW302RResRep aRes : res.getData().getCards()) {
                if (card.getCardNo().equals(aRes.getCardNo())) {
                    NCCTX001CardInfo one = new NCCTX001CardInfo();
                    one.setCardKey(card.getCardKey());
                    one.setCardName(card.getCardName());
                    one.setDisplayCardName(card.getCardName() + " ···· " + card.getCardNo().substring(12));
                    one.setCardNo(card.getCardNo());
                    double balanceAvailable = aRes.getDomesticCashAdvanceBalanceAvailable().intValue();

                    if (balanceAvailable < 1000) {
                        continue;
                    }

                    balanceAvailable = Math.floor(balanceAvailable / 1000) * 1000;
                    one.setAvailableBalance((int) balanceAvailable);
                    one.setDisplayAvailableBalance(IBUtils.formatMoney(new BigDecimal(balanceAvailable), 0));
                    if (balanceAvailable > maxBalanceAvailable) {
                        maxBalanceAvailable = balanceAvailable;
                        maxCreditCard = card;
                    }
                    cards.add(card);
                    ncctx001Cards.add(one);
                }
            }
        }
        // 篩選後無資料
        if (ncctx001Cards.size() == 0) {
            throw new ActionException(ErrorCode.NO_MONEY_CREDIT_CARD);
        }

        // CEW303R
        service.sendCEW303R(aibankUser, dataOutput);
        CEW303RResponse cew303RRes = dataOutput.getCew303RRes();
        if (cew303RRes != null) {
            rsData.setMaxBalanceDomestic(IBUtils.formatMoney(cew303RRes.getAcctIdCcbl(), 0));
            rsData.setMaxCreditDomestic(IBUtils.formatMoney(cew303RRes.getAcctIdTmmh(), 0));

            rsData.setMaxBalanceForeign(IBUtils.formatMoney(cew303RRes.getAcctIdCURCcbl(), 0));
            rsData.setMaxCreditForeign(IBUtils.formatMoney(cew303RRes.getAcctIdCurTmmh(), 0));
        }

        NCCTX001CacheVo cache = new NCCTX001CacheVo(ncctx001Cards, cards, maxCreditCard, maxBalanceAvailable);
        cache.setBirthday(res.getData().getBirthd().getTime());
        setCache(NCCTX001Service.NCCTX001_CACHE_KEY, cache);

        rsData.setMaxBalanceAvailable((int) maxBalanceAvailable);
        rsData.setDisplayMaxBalanceAvailable(IBUtils.formatMoney(new BigDecimal(maxBalanceAvailable), 0));

        try {
            RemarkContent remarkContent = remarkContentCacheManager.getRemarkContent(RemarkContentType.TERMS.getType(), "NCCTX001_010_HANDLING_FEE_INFO", getLocale().toString());
            rsData.setFeeRemarkTitle(remarkContent.getTitle());
            rsData.setFeeRemarkContent(remarkContent.getContent());
        }
        catch (ServiceException ex) {
            logger.warn("remarkContentCacheManager HANDLING_FEE_INFO 無資料");
            rsData.setFeeRemarkTitle("手續費説明");
            rsData.setFeeRemarkContent("手續費説明");
        }

    }

}