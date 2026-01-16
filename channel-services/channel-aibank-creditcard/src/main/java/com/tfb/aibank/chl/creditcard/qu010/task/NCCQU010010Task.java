package com.tfb.aibank.chl.creditcard.qu010.task;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.commons.id.IBSystemId;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.ibmb.component.errorcode.ErrorCodeData;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010010Rq;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010010Rs;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Cache;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CardSummary;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CategoryType;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010CreditCardRecommendation;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Input;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Output;
import com.tfb.aibank.chl.creditcard.qu010.service.NCCQU010Service;
import com.tfb.aibank.chl.creditcard.qu010.type.ErrorMessageType;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;
import com.tfb.aibank.common.error.AIBankErrorCode;

// @formatter:off
/**
 * @(#)NCCQU010010Task.java
 * 
 * <p>Description:消費分析 010 功能首頁</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/06/25, Warren Lin
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NCCQU010010Task extends AbstractAIBankBaseTask<NCCQU010010Rq, NCCQU010010Rs> {

    private NCCQU010Input input = new NCCQU010Input();
    private NCCQU010Output output = new NCCQU010Output();

    @Autowired
    private NCCQU010Service service;

    @Autowired
    protected UserDataCacheService userDataCacheService;

    @Override
    public void validate(NCCQU010010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NCCQU010010Rq rqData, NCCQU010010Rs rsData) throws ActionException {

        // 檢查客戶信用卡持有狀態，並將結果寫入至loginUser
        AIBankUser aibankUser = getLoginUser();
        Locale userLocale = getLocale();

        // 檢核是否未持有信用卡
        if (!userDataCacheService.haveCreditCard(aibankUser)) {
            rsData.setQueryResult(NCCQU010Service.QR_NO_CARD_2);

            // 取得錯誤資訊
            rsData.setErrorTitle(ErrorMessageType.NO_CREDIT_CARD.getTitle());
            rsData.setErrorMessage(ErrorMessageType.NO_CREDIT_CARD.getDesc());

            // 取得信用卡推薦資訊
            service.getCreditCardRecommendationInfo(output);
            NCCQU010CreditCardRecommendation creditCardRecommendation = output.getCreditCardRecommendation();
            rsData.setCreditCardRecommendation(creditCardRecommendation);
            return;
        }

        // 檢核是否為信用卡特殊戶
        if (userDataCacheService.isSpecialCreditCard(aibankUser)) {
            rsData.setQueryResult(NCCQU010Service.QR_NODATA_3);
            rsData.setErrorTitle(ErrorMessageType.SPECIAL_CARD.getTitle());
            rsData.setErrorMessage(ErrorMessageType.SPECIAL_CARD.getDesc());
            return;
        }

        // 檢核是否為信用卡正卡持有人
        if (!userDataCacheService.isPrimaryCard(aibankUser, userLocale)) {
            rsData.setQueryResult(NCCQU010Service.QR_NON_PRIMARY_CARD);
            return;
        }

        List<CreditCard> creditCards = userDataCacheService.getAllCreditCards(aibankUser, getLocale());
        input.setCreditCards(creditCards);

        NCCQU010Cache cache = new NCCQU010Cache();

        // 取得近一年分析月
        service.getLatestYearAnalysisMonths(output);
        rsData.setCurrentAnalysisMonth(output.getEndYearMonth());

        input.setStartYearMonth(output.getStartYearMonth());
        input.setEndYearMonth(output.getEndYearMonth());
        input.setLatestYearAnalysisYearMonths(output.getLatestYearAnalysisYearMonths());
        input.setCategoryGroup(NCCQU010CategoryType.LIFE.getType());
        input.setLocale(getLocale());
        try {
            // 發送消費金額彙總資訊查詢API以及各消費類別金額查詢API，取得牌卡資料
            service.getConsumptionSummary(getLoginUser(), input, output);

            if (output.getQueryResult() == NCCQU010Service.QR_NODATA_3) {
                rsData.setQueryResult(NCCQU010Service.QR_NODATA_3);
                // 取得錯誤資訊
                rsData.setErrorTitle(ErrorMessageType.NO_DATA.getTitle());
                rsData.setErrorMessage(ErrorMessageType.NO_DATA.getDesc());
                return;
            }

            cache.setLastYearMonth(output.getLastYearMonth());
            cache.setCurrentAnalysisMonth(output.getEndYearMonth());
            cache.setStartYearMonth(output.getStartYearMonth());
            cache.setEndYearMonth(output.getEndYearMonth());
            cache.setLatestSixMonthAnalysisYearMonths(output.getLatestSixMonthYearMonths());
            cache.setLatestYearAnalysisYearMonths(output.getLatestYearAnalysisYearMonths());
            cache.setTrendSummary(output.getTrendSummary());
            cache.setRatioSummary(output.getRatioSummary());
            cache.setDistributionSummary(output.getDistributionSummary());
            // 消費金額匯總資料
            cache.setLatestTwoYearConsumptionData(output.getLatestTwoYearConsumptionData());
            cache.setLatestSixMonthConsumptionAvg(output.getLatestSixMonthConsumptionAvg());
            cache.setLatestYearConsumptionAvg(output.getLatestYearConsumptionAvg());

            cache.setCardType(output.getTrendSummary().getCardType());

            setCache(NCCQU010Service.CACHE_KEY, cache);

            rsData.setTrendSummary(output.getTrendSummary());
            rsData.setRatioSummary(output.getRatioSummary());
            rsData.setDistributionSummary(output.getDistributionSummary());
            rsData.setQueryResult(0);
        }
        catch (ServiceException e) {
            logger.error("取得牌卡資料失敗, exception: ", e.getMessage());

            NCCQU010CardSummary cardSummary = new NCCQU010CardSummary();
            cardSummary.setQueryResult(1);
            rsData.setTrendSummary(cardSummary);
            rsData.setRatioSummary(cardSummary);
            rsData.setDistributionSummary(cardSummary);
        }
    }
}
