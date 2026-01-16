package com.tfb.aibank.chl.creditcard.qu001.task;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.util.StringUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001BonusRewards;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001CardData;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQQU001HistoricalBill;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001041Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001041Rs;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001Cache;
import com.tfb.aibank.chl.creditcard.qu001.service.NCCQU001Service;
import com.tfb.aibank.chl.creditcard.qu001.utils.NCCQU001Utils;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW218RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW303RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.CEW314RResponse;
import com.tfb.aibank.chl.creditcard.resource.dto.VB0051Response;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

//@formatter:off
/**
* @(#)NCCQU001041Task.java
* 
* <p>Description:信用卡總覽 歷史帳單頁 續傳資料</p>
* 
* <p>Modify History:</p>
* v1.0, 2023/05/30, Alex PY Li
* <ol>
*  <li>初版</li>
* </ol>
*/
//@formatter:on
@Component
@Scope("prototype")
public class NCCQU001041Task extends AbstractAIBankBaseTask<NCCQU001041Rq, NCCQU001041Rs> {
    @Autowired
    private NCCQU001Utils utils;

    @Autowired
    private NCCQU001Service service;

    @Autowired
    private CurrencyCodeCacheManager currencyCodeCacheManager;

    @Override
    public void validate(NCCQU001041Rq rqData) throws ActionException {
        logger.debug("NCCQU001041 validate....");
    }

    @Override
    public void handle(NCCQU001041Rq rqData, NCCQU001041Rs rsData) throws ActionException {
        AIBankUser aibankUser = getLoginUser();

        String cardKey = rqData.getCardKey();
        int queryMonth = rqData.getQueryMonth();
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        List<NCCQQU001HistoricalBill> historicalBills = null;
        NCCQQU001HistoricalBill historicalBill = null;
        CEW303RResponse billing = null;
        boolean pageError = false;

        CreditCard creditCard = aibankUser.getAllCreditCards().stream().filter(card -> StringUtils.equals(card.getCardKey(), cardKey)).findFirst().orElse(new CreditCard());

        if (creditCard.isPositive()) {
            // 正卡
            String custId = aibankUser.getCustId();
            historicalBills = cache.getCardNoMappingBills().get(custId);
            Optional<NCCQQU001HistoricalBill> opt = historicalBills.stream().filter(bills -> bills.getQueryMonth() == queryMonth).findAny();
            if (opt.isPresent()) {
                historicalBill = opt.get();
                if (CollectionUtils.isEmpty(historicalBill.getBills())) {
                    this.createHistoricalBill(rsData, aibankUser, creditCard.getCardNo(), billing, queryMonth, historicalBills, historicalBill, cache.getCardInfos(), cache.getUnderCardInfos());
                }
                if (!historicalBill.isPageError() && CollectionUtils.isNotEmpty(historicalBill.getBills())) {
                    try {
                        VB0051Response vb0051Response = utils.sendVB0051(custId, String.valueOf(queryMonth));
                        historicalBill.setSignFlg3(vb0051Response.getSignFlg3());
                        historicalBill.setAddamt(vb0051Response.getAddamt() == null ? BigDecimal.ZERO : vb0051Response.getAddamt());
                    }
                    catch (ServiceException e) {
                        logger.warn("取得好多金資料 sendVB0051 查詢失敗:", e);
                        rsData.setCostcoError(true);
                    }
                }
                cache.getCardNoMappingBills().put(custId, historicalBills);
            }
        }
        else {
            NCCQQU001CardData cardInfo = cache.getCardInfos().stream().filter(card -> StringUtils.equals(card.getCardKey(), creditCard.getCardKey())).findAny().orElse(null);
            if (cardInfo.isAdditional()) {
                String cardNo = creditCard.getCardNo();
                // 附卡
                historicalBills = cache.getCardNoMappingBills().get(cardNo);
                String pageErrorCode = StringUtils.EMPTY;
                pageErrorCode = utils.getCew303rAdds(cardNo, cache, billing);
                pageError = StringUtils.isNotBlank(pageErrorCode);
                historicalBill = historicalBills.stream().filter(bill -> bill.getQueryMonth() == queryMonth).findFirst().orElse(null);
                historicalBill.setPageError(pageError);
                historicalBill.setPageErrorCode(pageErrorCode);
                if (!pageError) {
                    this.createHistoricalAddBills(aibankUser, cardNo, queryMonth, historicalBill, cardInfo);
                }
                cache.getCardNoMappingBills().put(cardNo, historicalBills);
            }
        }
        rsData.setHistoricalBill(historicalBill);
        setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
    }

    /**
     * 取得歷史帳單 正卡
     * 
     * @param rsData
     * @param aibankUser
     * @param cardNo
     * @param billing
     * @param queryMonth
     * @param historicalBills
     * @param historicalBill
     * @param cardInfos
     * @param underCardInfos
     * @throws ActionException
     */
    private void createHistoricalBill(NCCQU001041Rs rsData, AIBankUser aibankUser, String cardNo, CEW303RResponse billing, int queryMonth, List<NCCQQU001HistoricalBill> historicalBills, NCCQQU001HistoricalBill historicalBill, List<NCCQQU001CardData> cardInfos, List<NCCQQU001CardData> underCardInfos) throws ActionException {
        boolean pageError = false;
        String custId = aibankUser.getCustId();
        // CEW314R
        CEW314RResponse response = new CEW314RResponse();
        try {
            response = utils.sendCEW314R(custId, Integer.toString(queryMonth));
        }
        catch (ServiceException e) {
            logger.warn("正卡:帳單明細查詢 sendCEW314R 查詢失敗:", e);
            if (StringUtils.equals("V658", e.getErrorCode())) {
                // 回覆V658，關帳中，直接導到共通錯誤頁，顯示電文回覆錯誤訊息
                throw e;
            }
            else {
                // 回覆非V000/V003/V658，進入頁面於對應頁籤下方顯示資料查詢失敗樣式
                pageError = true;
            }
        }

        historicalBill.setPageError(pageError);

        if (!pageError && CollectionUtils.isNotEmpty(response.getB100Repeats())) {
            // 初始多筆歷史帳單
            utils.createHistoricalBill(response.getB100Repeats(), historicalBill, currencyCodeCacheManager, getLocale(), cardInfos, underCardInfos);
            utils.genA021Data(response, historicalBill);
            // 紅利、哩程回饋 改用CEW314R B500資料
            if (CollectionUtils.isNotEmpty(response.getB500Repeats())) {
                NCCQQU001BonusRewards bonusRewards = new NCCQQU001BonusRewards();
                service.getBouns(response.getB500Repeats(), bonusRewards);
                historicalBill.setBonusRewards(bonusRewards);
            }
        }

    }

    /**
     * 取得歷史帳單 附卡
     *
     * @param aibankUser
     * @param cardNo
     * @param queryMonth
     * @param historicalBill
     * @param cardInfo
     */
    private void createHistoricalAddBills(AIBankUser aibankUser, String cardNo, int queryMonth, NCCQQU001HistoricalBill historicalBill, NCCQQU001CardData cardInfo) {
        boolean pageError = false;
        // 先查詢 第一筆 CEW218R
        CEW218RResponse response = new CEW218RResponse();
        try {
            response = utils.sendCEW218R(aibankUser.getCustId(), cardNo, Integer.toString(queryMonth));
        }
        catch (ServiceException e) {
            logger.warn("附卡:帳單明細查詢 sendCEW218R 查詢失敗", e);
            pageError = true;
        }
        // 初始多筆歷史帳單
        utils.createHistoricalBill(response, historicalBill, cardInfo, currencyCodeCacheManager, getLocale());
        historicalBill.setPageError(pageError);
    }

}