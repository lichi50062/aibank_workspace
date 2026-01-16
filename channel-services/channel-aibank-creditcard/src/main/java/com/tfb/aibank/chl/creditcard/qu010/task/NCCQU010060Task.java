package com.tfb.aibank.chl.creditcard.qu010.task;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.exception.ServiceException;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.userdatacache.UserDataCacheService;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010060Rq;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010060Rs;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010AnnualDetailData;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Cache;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Input;
import com.tfb.aibank.chl.creditcard.qu010.model.NCCQU010Output;
import com.tfb.aibank.chl.creditcard.qu010.service.NCCQU010Service;
import com.tfb.aibank.chl.model.creditcard.CreditCard;
import com.tfb.aibank.chl.session.AIBankUser;

// @formatter:off
/**
 * @(#)NCCQU010060Task.java
 * 
 * <p>Description:消費分析 060 搜尋結果頁</p>
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
public class NCCQU010060Task extends AbstractAIBankBaseTask<NCCQU010060Rq, NCCQU010060Rs> {

    private NCCQU010Input input = new NCCQU010Input();
    private NCCQU010Output output = new NCCQU010Output();

    @Autowired
    private NCCQU010Service service;

    @Autowired
    private UserDataCacheService userDataCacheService;

    @Override
    public void validate(NCCQU010060Rq rqData) throws ActionException {

    }

    @Override
    public void handle(NCCQU010060Rq rqData, NCCQU010060Rs rsData) throws ActionException {

        rsData.setSearchKeyword(rqData.getSearchKeyword());

        NCCQU010Cache cache = getCache(NCCQU010Service.CACHE_KEY, NCCQU010Cache.class);

        if (rqData.getSearchKeyword() == null) {
            rqData.setSearchKeyword("");
        }

        if (CollectionUtils.isNotEmpty(cache.getAnnualDetails())) {
            List<NCCQU010AnnualDetailData> foundAnnualDetails = cache.getAnnualDetails().stream().filter(x -> x.getItemName().contains(rqData.getSearchKeyword())).collect(Collectors.toList());

            if (CollectionUtils.isNotEmpty(foundAnnualDetails)) {
                IBUtils.sort(foundAnnualDetails, "txDate", true);
                rsData.setAnnualDetails(foundAnnualDetails);
                rsData.setStartDt(foundAnnualDetails.get(foundAnnualDetails.size() - 1).getTxDate());
                rsData.setEndDt(foundAnnualDetails.get(0).getTxDate());

                service.calcSearchItemSum(foundAnnualDetails, output);
                rsData.setTxnAmtSumDisplay(output.getTxnAmtSumDisplay());
                rsData.setQueryResult(cache.getQueryResult());
                rsData.setRecordLimit(cache.getRecordLimit());
                rsData.setQueryResult(NCCQU010Service.QR_SUCC_0);
                return;
            }
        }

        try {
            // 若暫存無資料，發送 整年消費明細搜尋 API 取得消費資料
            AIBankUser aibankUser = getLoginUser();

            List<CreditCard> creditCards = userDataCacheService.getAllCreditCards(aibankUser, getLocale());
            input.setCreditCards(creditCards);

            input.setSelectedYearMonth(cache.getCurrentAnalysisMonth());
            input.setLocale(getLocale());
            input.setSearchKeyword(rqData.getSearchKeyword());
            input.setSkip(0);
            input.setLocale(getLocale());

            // 發送 整年消費明細搜尋 OAuth API
            service.queryAnnualDetails(aibankUser, input, output);

            if (output.getQueryResult() != null && output.getQueryResult() == 1) {
                rsData.setQueryResult(NCCQU010Service.QR_FAIL_1);
                return;
            }
            List<NCCQU010AnnualDetailData> foundAnnualDetails = output.getAnnualDetails().stream().filter(x -> x.getItemName().contains(input.getSearchKeyword())).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(foundAnnualDetails)) {
                rsData.setQueryResult(NCCQU010Service.QR_NODATA_3);
                return;
            }

            IBUtils.sort(foundAnnualDetails, "txDate", true);
            rsData.setAnnualDetails(foundAnnualDetails);
            rsData.setStartDt(foundAnnualDetails.get(foundAnnualDetails.size() - 1).getTxDate());
            rsData.setEndDt(foundAnnualDetails.get(0).getTxDate());
            rsData.setTxnAmtSumDisplay(output.getTxnAmtSumDisplay());
            rsData.setRecordLimit(output.getRecordLimit());
            rsData.setQueryResult(output.getQueryResult());

            cache.setAnnualDetails(output.getAnnualDetails());
            cache.setRecordLimit(output.getRecordLimit());
            cache.setQueryResult(output.getQueryResult());
            setCache(NCCQU010Service.CACHE_KEY, cache);
        }
        catch (ServiceException e) {
            logger.error("取得整年消費明細資料失敗, exception: ", e.getMessage());
            rsData.setQueryResult(NCCQU010Service.QR_FAIL_1);
        }
    }
}
