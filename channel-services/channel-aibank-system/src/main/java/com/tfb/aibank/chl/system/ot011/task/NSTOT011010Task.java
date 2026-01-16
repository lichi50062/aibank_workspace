package com.tfb.aibank.chl.system.ot011.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ibm.tw.commons.exception.ActionException;
import com.ibm.tw.commons.util.StringUtils;
import com.ibm.tw.commons.util.time.DateUtils;
import com.ibm.tw.ibmb.util.IBUtils;
import com.tfb.aibank.chl.base.AbstractAIBankBaseTask;
import com.tfb.aibank.chl.component.currencycode.CurrencyCodeCacheManager;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistory;
import com.tfb.aibank.chl.component.exchangeratehistory.ExchangeRateHistoryCacheManager;
import com.tfb.aibank.chl.component.fxinterestrate.FxInterestRateCacheManager;
import com.tfb.aibank.chl.system.ot011.model.NSTOT011010Rq;
import com.tfb.aibank.chl.system.ot011.model.NSTOT011010Rs;
import com.tfb.aibank.chl.system.ot011.model.NSTOT011Currency;
import com.tfb.aibank.chl.system.ot011.model.NSTOT011Output;
import com.tfb.aibank.chl.system.ot011.service.NSTOT011Service;

// @formatter:off
/**
 * @(#)NSTOT011010Task.java
 * 
 * <p>Description:日終掛牌 買匯匯率popup區塊</p>
 * 
 * <p>Modify History:</p>
 * v1.0, 2024/01/24, Alex PY Li
 * <ol>
 *  <li>初版</li>
 * </ol>
 */
// @formatter:on
@Component
@Scope("prototype")
public class NSTOT011010Task extends AbstractAIBankBaseTask<NSTOT011010Rq, NSTOT011010Rs> {
    @Autowired
    private FxInterestRateCacheManager fxInterestRateCacheManager;

    @Autowired
    private CurrencyCodeCacheManager currencyCodeCacheManager;

    @Autowired
    private ExchangeRateHistoryCacheManager exRateHistoryCacheManager;

    @Autowired
    private NSTOT011Service service;

    @Override
    public void validate(NSTOT011010Rq rqData) throws ActionException {
        // nothing...
    }

    @Override
    public void handle(NSTOT011010Rq rqData, NSTOT011010Rs rsData) throws ActionException {
        Locale userLocale = getLocale();
        List<NSTOT011Currency> currencyList = new ArrayList<>();
        
        //歷史匯率資料，每一幣別(應或)會有多筆資料
        List<ExchangeRateHistory> allCurRateHists = exRateHistoryCacheManager.getExchangeRateHistoryByPredicate(getExRateHisPredicate());
        
        if (CollectionUtils.isNotEmpty(allCurRateHists)) {
            
            Map<String, List<ExchangeRateHistory>> rateHistsCurMap = allCurRateHists.stream().collect(Collectors.groupingBy(ExchangeRateHistory::getCurrencyEname1));
            Date earlyDate = null;
            Date txTime = null;
            
            for(var hists: rateHistsCurMap.entrySet()) {
                //單一幣別下的匯率集合：幣別來源ExchangeRateHistory.CurrencyEname1
                List<ExchangeRateHistory> curRateHiss = hists.getValue();
                String curCode = hists.getKey();
                String curName = currencyCodeCacheManager.getCurrencyName(curCode, userLocale);
                
                if( StringUtils.isBlank(curName)) {
                    curName = curCode;
                }
                
                NSTOT011Output output = new NSTOT011Output();
                //取幣別排序
                service.getInvestCurrencySortNum(curCode, output);
                Integer sort = output.getSort();
                BigDecimal rate = BigDecimal.ZERO;
                
                IBUtils.sort(curRateHiss, new String[] { "txTime" }, new boolean[] { true });
                
                rate = curRateHiss.get(0).getBuy();
                txTime = curRateHiss.get(0).getTxTime();
                
                if (earlyDate == null || (Objects.nonNull(earlyDate) && Objects.nonNull(txTime) && txTime.after(earlyDate))) {
                    earlyDate = txTime;
                }
                currencyList.add(new NSTOT011Currency(curCode, curName, rate, sort));
            }
            rsData.setBizDate(DateUtils.getCEDateStr(earlyDate));
            IBUtils.sort(currencyList, "sort", true);
            rsData.setCurrencyList(currencyList);
        }
    }

    private Predicate<ExchangeRateHistory> getExRateHisPredicate() {
        return (exRate) -> "0".equals(exRate.getRateFlag()) && "0".equals(exRate.getRateType());
    }
}
