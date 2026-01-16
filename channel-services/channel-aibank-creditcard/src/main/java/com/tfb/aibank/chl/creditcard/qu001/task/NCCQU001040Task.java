package com.tfb.aibank.chl.creditcard.qu001.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001040Rq;
import com.tfb.aibank.chl.creditcard.qu001.model.NCCQU001040Rs;
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
* @(#)NCCQU001040Task.java
* 
* <p>Description:信用卡總覽 歷史帳單頁</p>
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
public class NCCQU001040Task extends AbstractAIBankBaseTask<NCCQU001040Rq, NCCQU001040Rs> {
    @Autowired
    private NCCQU001Utils utils;

    @Autowired
    private NCCQU001Service service;

    @Autowired
    private CurrencyCodeCacheManager currencyCodeCacheManager;

    @Override
    public void validate(NCCQU001040Rq rqData) throws ActionException {
        logger.debug("NCCQU001040 validate....");
    }

    @Override
    public void handle(NCCQU001040Rq rqData, NCCQU001040Rs rsData) throws ActionException {
        CEW303RResponse billing = null;
        AIBankUser aibankUser = getLoginUser();
        CreditCard creditCard = aibankUser.getAllCreditCards().stream().filter(card -> StringUtils.equals(card.getCardKey(), rqData.getCardKey())).findFirst().orElse(new CreditCard());
        NCCQU001Cache cache = getCache(NCCQU001Utils.NCCQU001_CACHE_KEY, NCCQU001Cache.class);
        NCCQQU001CardData cardInfo = cache.getCardInfos().stream().filter(card -> StringUtils.equals(card.getCardKey(), rqData.getCardKey())).findAny().orElse(null);

        List<NCCQQU001HistoricalBill> historicalBills = null;
        boolean isPrimaryCard = cardInfo == null || !cardInfo.isAdditional();
        boolean pageError = false;
        if (isPrimaryCard || StringUtils.isBlank(rqData.getCardKey())) {
            // 正卡
            String custId = aibankUser.getCustId();
            historicalBills = cache.getCardNoMappingBills().get(custId);
            CEW314RResponse response = new CEW314RResponse();
            if (CollectionUtils.isEmpty(historicalBills)) {
                billing = cache.getCew303r();
                // 初始化
                historicalBills = this.createHistoricalBills(billing == null ? new Date() : billing.getAcctIdCldy());
                // CEW314R
                try {
                    response = utils.sendCEW314R(custId, "2");
                    utils.genA021Data(response, historicalBills.get(0));
                    utils.createHistoricalBill(response.getB100Repeats(), historicalBills.get(0), currencyCodeCacheManager, getLocale(), cache.getCardInfos(), cache.getUnderCardInfos());
                }
                catch (ServiceException e) {
                    logger.error("正卡:帳單明細查詢 sendCEW314R 查詢失敗");
                    if (StringUtils.equals("V658", e.getErrorCode())) {
                        // 回覆V658，關帳中，直接導到共通錯誤頁，顯示電文回覆錯誤訊息
                        throw e;
                    }
                    else {
                        // 回覆非V000/V003/V658，進入頁面於對應頁籤下方顯示資料查詢失敗樣式
                        pageError = true;
                    }
                }
                historicalBills.get(0).setPageError(pageError);
                cache.getCardNoMappingBills().put(custId, historicalBills);

                if (!pageError) {

                    // 紅利、哩程回饋 改用CEW314R B500資料
                    if (CollectionUtils.isNotEmpty(response.getB500Repeats())) {
                        NCCQQU001BonusRewards bonusRewards = new NCCQQU001BonusRewards();
                        service.getBouns(response.getB500Repeats(), bonusRewards);
                        historicalBills.get(0).setBonusRewards(bonusRewards);
                    }

                    // Costco
                    try {
                        VB0051Response vb0051Response = utils.sendVB0051(custId, "2");
                        cache.setVb0051(vb0051Response);

                        historicalBills.get(0).setSignFlg3(vb0051Response.getSignFlg3());
                        historicalBills.get(0).setAddamt(vb0051Response.getAddamt() == null ? BigDecimal.ZERO : vb0051Response.getAddamt());
                    }
                    catch (ServiceException e) {
                        logger.warn("取得好多金資料 sendVB0051 查詢失敗:", e);
                        rsData.setCostcoError(true);
                    }
                }
            }
        }
        else {
            String cardNo = creditCard.getCardNo();
            // 副卡
            historicalBills = cache.getCardNoMappingBills().get(cardNo);
            if (CollectionUtils.isEmpty(historicalBills)) {
                String pageErrorCode = StringUtils.EMPTY;
                pageErrorCode = utils.getCew303rAdds(cardNo, cache, billing);
                pageError = StringUtils.isNotBlank(pageErrorCode);
                billing = cache.getCew303rAdds().get(cardNo);
                historicalBills = this.createHistoricalAddBills(billing == null ? new Date() : billing.getAcctIdCldy());
                if (!pageError) {
                    // 先查詢 第一筆 CEW218R
                    CEW218RResponse response = new CEW218RResponse();
                    try {
                        response = utils.sendCEW218R(aibankUser.getCustId(), cardNo, "1");
                        utils.createHistoricalBill(response, historicalBills.get(0), cardInfo, currencyCodeCacheManager, getLocale());
                    }
                    catch (ServiceException e) {
                        logger.error("附卡:帳單明細查詢 sendCEW218R 查詢失敗");
                        pageError = true;
                    }
                }
                historicalBills.get(0).setPageError(pageError);
                historicalBills.get(0).setPageErrorCode(pageErrorCode);
                cache.getCardNoMappingBills().put(cardNo, historicalBills);
            }
        }
        rsData.setPrimaryCard(isPrimaryCard);
        rsData.setHistoricalBills(historicalBills);
        logger.debug("歷史帳單 有{}資料", CollectionUtils.size(historicalBills));
        rsData.setCardKey(creditCard.getCardKey());
        setCache(NCCQU001Utils.NCCQU001_CACHE_KEY, cache);
    }

    /**
     * 歷史帳單 正卡 初始
     * 
     * @param date
     * @return
     */
    private List<NCCQQU001HistoricalBill> createHistoricalBills(Date date) {

        int month = utils.getMonth(date);
        // 正卡 {帳單月份}-2個月~{帳單月份}-6個月。
        List<Integer> monthArray = utils.getMonthArray(month, true);

        List<NCCQQU001HistoricalBill> historicalBills = new ArrayList<>();
        // 初始多筆歷史帳單
        historicalBills = utils.createHistoricalBills(monthArray, historicalBills);
        return historicalBills;
    }

    /**
     * 取得歷史帳單 附卡
     * 
     * @param date
     * @return
     */
    private List<NCCQQU001HistoricalBill> createHistoricalAddBills(Date date) {
        int month = utils.getMonth(date);
        // 附卡 {帳單月份}-1個月~{帳單月份}-5個月。
        List<Integer> monthArray = utils.getMonthArray(month, false);
        // 初始多筆歷史帳單
        return utils.createHistoricalBills(monthArray);
    }

}